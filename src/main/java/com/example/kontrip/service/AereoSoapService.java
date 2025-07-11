package com.example.kontrip.service;

import com.example.kontrip.aereo.*;
import com.example.kontrip.aereo.ArrayOfClasse;
import com.example.kontrip.aereo.ArrayOfTrechoPesquisaDTO;
import com.example.kontrip.aereo.ArrayOfstring;
import com.example.kontrip.aereo.Classe;
import com.example.kontrip.aereo.Horario;
import com.example.kontrip.aereo.HorarioDTO;
import com.example.kontrip.aereo.ObjectFactory;
import com.example.kontrip.aereo.PeriodoHoraDTO;
import com.example.kontrip.aereo.PesquisaDTO;
import com.example.kontrip.aereo.TrechoPesquisaDTO;
import com.example.kontrip.dto.SessaoResponse;
import com.example.kontrip.sessao.*;
import com.example.kontrip.util.ApiException;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.ws.soap.AddressingFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@Service
public class AereoSoapService {

    private final ObjectFactory OBJECT_FACTORY_AEREO = new ObjectFactory();

    private final com.example.kontrip.sessao.ObjectFactory OBJECT_FACTORY_SESSAO = new com.example.kontrip.sessao.ObjectFactory();

    @Value("${usuario}")
    private String usuario;

    @Value("${senha}")
    private String senha;

    public ResponseDisponibilidadeAereo getDisponibilidade(String origem, String destino, Integer adultos, Integer criancas, Integer bebes, Boolean executiva, LocalDate dataIda, LocalDate dataVolta) {
        SessaoResponse sessaoResponse = obterSessao();
        ResponseDisponibilidadeAereo responseDisponibilidadeAereo = obterDisponibilidade(sessaoResponse.getGuidSessao(), origem, destino, adultos, criancas, bebes, executiva, dataIda, dataVolta);
        encerrarSessao(sessaoResponse.getSessao());

        return responseDisponibilidadeAereo;
    }

    private ResponseDisponibilidadeAereo obterDisponibilidade(String guidSessao, String origem, String destino, Integer adultos, Integer criancas, Integer bebes, Boolean executiva, LocalDate dataIda, LocalDate dataVolta) {
        try {
            Aereo serviceAereo = new Aereo();
            IAereo clientAereo = serviceAereo.getWSHttpBindingIAereo(new AddressingFeature(true, true));

            RequestDisponibilidadeAereo requestDisponibilidadeAereo = createRequestDisponibilidadeAereo(guidSessao, origem, destino, adultos, criancas, bebes, executiva, dataIda, dataVolta);
            ResponseDisponibilidadeAereo responseDisponibilidadeAereo = clientAereo.disponibilidadeAereo(requestDisponibilidadeAereo);

            if (responseDisponibilidadeAereo.isErro()) {
                throw new ApiException("ERRO AO OBTER DISPONIBILIDADE - " + responseDisponibilidadeAereo.getMensagem().getValue());
            }

            return responseDisponibilidadeAereo;

        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("ERRO AO OBTER DISPONIBILIDADE - " + e.getMessage(), e);
        }
    }

    private SessaoResponse obterSessao() {
        try {
            Sessao serviceSessao = new Sessao();
            ISessao clientSessao = serviceSessao.getWSHttpBindingISessao(new AddressingFeature(true, true));

            RequestObter requestObter = createRequestObter();
            ResponseObter responseObter = clientSessao.obterSessao(requestObter);

            if (responseObter.isErro()) {
                throw new ApiException("ERRO AO OBTER SESSAO - " + responseObter.getMensagem().getValue());
            }

            return new SessaoResponse(clientSessao, responseObter.getGuidSessao());

        } catch (ApiException e) {
            throw e;
        } catch (Exception ex) {
            throw new ApiException("ERRO AO OBTER SESSAO - " + ex.getMessage(), ex);
        }
    }

    private void encerrarSessao(ISessao clientSessao) {
        try {
            RequestEncerrar requestEncerrar = new RequestEncerrar();
            ResponseEncerrar responseEncerrar = clientSessao.encerrarSessao(requestEncerrar);

            if (responseEncerrar.isErro()) {
                throw new ApiException("ERRO AO ENCERRAR SESSAO - " + responseEncerrar.getMensagem().getValue());
            }

        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("ERRO AO ENCERRAR SESSAO - " + ex.getMessage(), ex);
        }
    }
    
    private RequestObter createRequestObter() {
        RequestObter requestObter = new RequestObter();

        JAXBElement<String> senha = OBJECT_FACTORY_SESSAO.createRequestObterSenha(this.senha);
        JAXBElement<String> usuario = OBJECT_FACTORY_SESSAO.createRequestObterUsuario(this.usuario);

        requestObter.setSenha(senha);
        requestObter.setUsuario(usuario);
        return requestObter;
    }

    private RequestDisponibilidadeAereo createRequestDisponibilidadeAereo(String guidSessao, String origem, String destino, Integer adultos, Integer criancas, Integer bebes, Boolean executiva, LocalDate dataIda, LocalDate dataVolta) {
        try {
            RequestDisponibilidadeAereo requestDisponibilidadeAereo = new RequestDisponibilidadeAereo();

            PesquisaDTO pesquisaDTO = createPesquisaDTO(origem, destino, adultos, criancas, bebes, executiva, dataIda, dataVolta);
            JAXBElement<PesquisaDTO> requestDisponibilidadeAereoPesquisa = OBJECT_FACTORY_AEREO.createRequestDisponibilidadeAereoPesquisa(pesquisaDTO);

            requestDisponibilidadeAereo.setPesquisa(requestDisponibilidadeAereoPesquisa);
//        requestDisponibilidadeAereo.setAcordos();
//        requestDisponibilidadeAereo.setTempoMaximoPorSourceEmMilisegundos();
            requestDisponibilidadeAereo.setSomenteSourcesValidos(true);
            requestDisponibilidadeAereo.setGuidSessao(guidSessao);

            return requestDisponibilidadeAereo;
        } catch (Exception e) {
            throw new ApiException("ERRO AO CRIAR REQUEST DISPONIBILIDADE - " + e.getMessage(), e);
        }
    }

    private PesquisaDTO createPesquisaDTO(String origem, String destino, Integer adultos, Integer criancas, Integer bebes, Boolean executiva, LocalDate dataIda, LocalDate dataVolta) throws DatatypeConfigurationException {
        PesquisaDTO pesquisaDTO = new PesquisaDTO();

        ArrayOfClasse arrayOfClasses = createArrayOfClasses(executiva);
        JAXBElement<ArrayOfClasse> pesquisaDTOClasses = OBJECT_FACTORY_AEREO.createPesquisaDTOClasses(arrayOfClasses);
        pesquisaDTO.setClasses(pesquisaDTOClasses);

        ArrayOfstring arrayOfCias = createArrayOfCias();
        JAXBElement<ArrayOfstring> pesquisaDTOCompanhias = OBJECT_FACTORY_AEREO.createPesquisaDTOCompanhias(arrayOfCias);
        pesquisaDTO.setCompanhias(pesquisaDTOCompanhias);

        pesquisaDTO.setIsentarTaxaDU(false);
        pesquisaDTO.setMultiplasOpcoesPorTrecho(true);
        pesquisaDTO.setMultiplasTarifasPorTrecho(true);
        pesquisaDTO.setPermitirMaisOpcoesBagagem(true);
        pesquisaDTO.setQuantidadeAdultos(adultos);
        pesquisaDTO.setQuantidadeCriancas(criancas);
        pesquisaDTO.setQuantidadeBebes(bebes);
//        pesquisaDTO.setQuantidadeParadas(0);
        pesquisaDTO.setSomenteDiretos(false);

        JAXBElement<String> moeda = OBJECT_FACTORY_AEREO.createPesquisaDTOSiglaMoeda("BRL");
        pesquisaDTO.setSiglaMoeda(moeda);

        pesquisaDTO.setSomenteIda(dataVolta == null);
        pesquisaDTO.setSomenteOpcaoMaisBarataPorVoo(false);
        pesquisaDTO.setTodosResultadosIncentivo(false);

        ArrayOfTrechoPesquisaDTO arrayOfTrechoPesquisaDTO = createArrayOfTrechoPesquisaDTO(origem, destino, dataIda, dataVolta);
        JAXBElement<ArrayOfTrechoPesquisaDTO> pesquisaDTOTrechos = OBJECT_FACTORY_AEREO.createPesquisaDTOTrechos(arrayOfTrechoPesquisaDTO);
        pesquisaDTO.setTrechos(pesquisaDTOTrechos);

        return pesquisaDTO;
    }

    private ArrayOfTrechoPesquisaDTO createArrayOfTrechoPesquisaDTO(String origem, String destino, LocalDate dataIda, LocalDate dataVolta) throws DatatypeConfigurationException {
        ArrayOfTrechoPesquisaDTO arrayOfTrechoPesquisaDTO = new ArrayOfTrechoPesquisaDTO();

        int ano = dataIda.getYear();
        int mes = dataIda.getMonth().getValue();
        int diaMes = dataIda.getDayOfMonth();

        TrechoPesquisaDTO trechoPesquisaIda = createTrechoPesquisa(origem, destino, ano, mes, diaMes);
        arrayOfTrechoPesquisaDTO.getTrechoPesquisaDTO().add(trechoPesquisaIda);

        if (dataVolta != null) {
            int anoVolta = dataVolta.getYear();
            int mesVolta = dataVolta.getMonth().getValue();
            int diaMesVolta = dataVolta.getDayOfMonth();

            TrechoPesquisaDTO trechoPesquisaVolta = createTrechoPesquisa(destino, origem, anoVolta, mesVolta, diaMesVolta);
            arrayOfTrechoPesquisaDTO.getTrechoPesquisaDTO().add(trechoPesquisaVolta);
        }

        return arrayOfTrechoPesquisaDTO;
    }

    private TrechoPesquisaDTO createTrechoPesquisa(String origem, String destino, int year, int month, int day) throws DatatypeConfigurationException {
        TrechoPesquisaDTO trechoPesquisaDTO = new TrechoPesquisaDTO();

        XMLGregorianCalendar dataPartidaXMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        dataPartidaXMLGregorianCalendar.setYear(year);
        dataPartidaXMLGregorianCalendar.setMonth(month);
        dataPartidaXMLGregorianCalendar.setDay(day);
        dataPartidaXMLGregorianCalendar.setHour(0);
        dataPartidaXMLGregorianCalendar.setMinute(0);
        dataPartidaXMLGregorianCalendar.setSecond(0);

        trechoPesquisaDTO.setDataPartida(dataPartidaXMLGregorianCalendar);

        trechoPesquisaDTO.setOrigem(OBJECT_FACTORY_AEREO.createTrechoPesquisaDTOOrigem(origem));
        trechoPesquisaDTO.setDestino(OBJECT_FACTORY_AEREO.createTrechoPesquisaDTODestino(destino));
        trechoPesquisaDTO.setHorarioPartida(Horario.TODOS);

        PeriodoHoraDTO periodoHoraDTO = createPeriodoHoraDTO();
        JAXBElement<PeriodoHoraDTO> trechoPesquisaDTOHoraPartida = OBJECT_FACTORY_AEREO.createTrechoPesquisaDTOHoraPartida(periodoHoraDTO);
        trechoPesquisaDTO.setHoraPartida(trechoPesquisaDTOHoraPartida);

        JAXBElement<PeriodoHoraDTO> trechoPesquisaDTOHoraChegada = OBJECT_FACTORY_AEREO.createTrechoPesquisaDTOHoraChegada(periodoHoraDTO);
        trechoPesquisaDTO.setHoraChegada(trechoPesquisaDTOHoraChegada);

//        trechoPesquisaDTO.setRaioDistanciaMilhasOrigem(0);
//        trechoPesquisaDTO.setRaioDistanciaMilhasDestino(0);
        return trechoPesquisaDTO;
    }

    private PeriodoHoraDTO createPeriodoHoraDTO() {
        PeriodoHoraDTO periodoHoraDTO = new PeriodoHoraDTO();

        HorarioDTO horarioInicioDTO = createHorarioInicioDTO();
        JAXBElement<HorarioDTO> periodoHoraDTOInicio = OBJECT_FACTORY_AEREO.createPeriodoHoraDTOInicio(horarioInicioDTO);
        periodoHoraDTO.setInicio(periodoHoraDTOInicio);

        HorarioDTO horarioFimDTO = createHorarioFimDTO();
        JAXBElement<HorarioDTO> periodoHoraDTOFim = OBJECT_FACTORY_AEREO.createPeriodoHoraDTOFim(horarioFimDTO);
        periodoHoraDTO.setFim(periodoHoraDTOFim);

        return periodoHoraDTO;
    }

    private HorarioDTO createHorarioInicioDTO() {
        HorarioDTO horarioDTO = new HorarioDTO();
        horarioDTO.setHora(0);
        horarioDTO.setMinuto(0);

        return horarioDTO;
    }

    private HorarioDTO createHorarioFimDTO() {
        HorarioDTO horarioDTO = new HorarioDTO();
        horarioDTO.setHora(23);
        horarioDTO.setMinuto(59);

        return horarioDTO;
    }

    private ArrayOfstring createArrayOfCias() {
        ArrayOfstring arrayOfstring = new ArrayOfstring();
        arrayOfstring.getString().add("LA");
        arrayOfstring.getString().add("G3");

        return arrayOfstring;
    }

    private ArrayOfClasse createArrayOfClasses(Boolean executiva) {
        ArrayOfClasse arrayOfClasse = new ArrayOfClasse();
        arrayOfClasse.getClasse().add(executiva ? Classe.EXECUTIVA : Classe.ECONOMICA);

        return arrayOfClasse;
    }

}

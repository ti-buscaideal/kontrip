package com.example.kontrip.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jakarta.xml.bind.JAXBElement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .modules(jaxbElementModule())
                .build();
    }

    @Bean
    public SimpleModule jaxbElementModule() {
        SimpleModule module = new SimpleModule("JAXBElementModule");

        // Custom serializer for JAXBElement - use raw type to avoid generic issues
        module.addSerializer(new JAXBElementSerializer());

        return module;
    }

    /**
     * Custom serializer that extracts only the 'value' from JAXBElement
     */
    @SuppressWarnings("rawtypes")
    public static class JAXBElementSerializer extends StdSerializer<JAXBElement> {

        public JAXBElementSerializer() {
            super(JAXBElement.class);
        }

        @Override
        public void serialize(JAXBElement jaxbElement, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {

            if (jaxbElement == null || jaxbElement.getValue() == null) {
                gen.writeNull();
            } else {
                // Serialize only the value, not the entire JAXBElement structure
                Object value = jaxbElement.getValue();
                serializers.defaultSerializeValue(value, gen);
            }
        }

        @Override
        public boolean isEmpty(SerializerProvider provider, JAXBElement value) {
            return value == null || value.getValue() == null;
        }
    }
}

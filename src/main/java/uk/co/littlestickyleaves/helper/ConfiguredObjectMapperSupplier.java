package uk.co.littlestickyleaves.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.function.Supplier;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 */
// TODO fill in Javadoc
public class ConfiguredObjectMapperSupplier implements Supplier<ObjectMapper> {

    private ObjectMapper objectMapper;
    @Override
    public ObjectMapper get() {
        return objectMapper == null ? configuredObjectMapper() : objectMapper;
    }

    private ObjectMapper configuredObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}

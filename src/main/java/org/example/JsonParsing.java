package org.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.example.Model.Data;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class JsonParsing {
    private final ObjectMapper mapper = new ObjectMapper();

    public void readJson(String fileName, Consumer<Data> processRow) throws IOException {
        JsonFactory factory = new JsonFactory();

        try (JsonParser parser = factory.createParser(new File(fileName))) {
            parser.setCodec(mapper);

            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IOException("Ожидается начало массива JSON");
            }

            while (parser.nextToken() == JsonToken.START_OBJECT) {

                Data data = parser.readValueAs(Data.class);
                processRow.accept(data);
            }
        } catch (JsonMappingException e) {
            System.err.println("Ошибка при разборе JSON: " + e.getMessage());
        }
    }
}
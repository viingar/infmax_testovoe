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

    private static final long MAX_OBJECTS = 10_000_000;
    private final ObjectMapper mapper = new ObjectMapper();

    public boolean readJson(String fileName, Consumer<Data> processRow) throws IOException {
        JsonFactory factory = new JsonFactory();

        try (JsonParser parser = factory.createParser(new File(fileName))) {
            parser.setCodec(mapper);

            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IOException("Ожидается начало массива JSON");
            }

            long count = 0;
            while (parser.nextToken() == JsonToken.START_OBJECT) {
                if (++count > MAX_OBJECTS) {
                    System.out.println("Файл содержит более 10 миллионов объектов. Пожалуйста, используйте файл меньшего объема.");
                    return false;
                }

                Data data = parser.readValueAs(Data.class);
                processRow.accept(data);
            }
        } catch (JsonMappingException e) {
            System.err.println("Ошибка при разборе JSON: " + e.getMessage());
        }
        return true;
    }
}
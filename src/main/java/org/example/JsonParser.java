package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.Data;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser {

    public List<Data> readJson(String fileName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName),mapper.getTypeFactory().constructCollectionType(List.class,Data.class));

    }
}

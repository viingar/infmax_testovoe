import org.example.CsvParser;
import org.example.Model.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvParserTest {

    @Test
    public void testReadCsvWithValidData() throws IOException {
        CsvParser parser = new CsvParser();
        List<Data> dataList = parser.readCsv("src/test/resources/test.csv");

        assertEquals(3, dataList.size());

        Data first = dataList.get(0);
        assertEquals("Group1", first.getGroup());
        assertEquals("TypeA", first.getType());
        assertEquals(12345L, first.getNumber());
        assertEquals(100L, first.getWeight());
    }

    @Test
    public void testReadCsvWithEmptyData() throws IOException {
        CsvParser parser = new CsvParser();
        List<Data> dataList = parser.readCsv("src/test/resources/empty.csv");

        assertEquals(0, dataList.size());

    }
}
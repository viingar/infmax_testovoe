import org.example.JsonParser;
import org.example.Model.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    @Test
    public void testReadJsonWithValidData() throws IOException {
        JsonParser jsonParser = new JsonParser();
        List<Data> dataList = jsonParser.readJson("src/test/resources/test.json");

        assertNotNull(dataList);
        assertEquals(3, dataList.size());

        Data data = dataList.get(0);
        assertEquals("Group1", data.getGroup());
        assertEquals("TypeA", data.getType());
        assertEquals(12345L, data.getNumber());
        assertEquals(100, data.getWeight());
    }

    @Test
    public void testReadJsonWithEmptyFile() throws IOException {

        JsonParser jsonParser = new JsonParser();
        List<Data> dataList = jsonParser.readJson("src/test/resources/empty.json");

        assertNotNull(dataList);
        assertTrue(dataList.isEmpty());
    }

    @Test
    public void testReadJsonWithInvalidData() {
        JsonParser jsonParser = new JsonParser();

        assertThrows(IOException.class, () -> {
            jsonParser.readJson("src/test/resources/invalid.json");
        });
    }

}
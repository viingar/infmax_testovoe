import org.example.JsonParsing;
import org.example.Model.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    @Test
    public void testReadJsonWithValidData() throws IOException {
        JsonParsing jsonParser = new JsonParsing();
        List<Data> dataList = new ArrayList<>();

        jsonParser.readJson("src/test/resources/test.json", dataList::add);

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
        JsonParsing jsonParser = new JsonParsing();
        List<Data> dataList = new ArrayList<>();

        jsonParser.readJson("src/test/resources/empty.json", dataList::add);

        assertNotNull(dataList);
        assertTrue(dataList.isEmpty());
    }
}
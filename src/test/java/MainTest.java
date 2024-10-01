import org.example.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;


import static org.junit.jupiter.api.Assertions.*;
public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("exit\n".getBytes());

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @Test
    public void testMainExitCommand() {
        System.setIn(new ByteArrayInputStream("exit\n".getBytes()));
        Main.main(new String[]{});

        assertTrue(outContent.toString().contains("Введите путь до файла (или 'exit' для выхода):"));
    }

    @Test
    public void testMainWithValidJsonFile() throws IOException {

        File tempFile = createTempJsonFile();

        String input = tempFile.getAbsolutePath() + "\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        assertTrue(outContent.toString().contains("Суммарный вес объектов в каждой группе:"));
        assertTrue(outContent.toString().contains("Максимальный вес:"));
        assertTrue(outContent.toString().contains("Минимальный вес:"));
    }


    private File createTempJsonFile() throws IOException {
        File tempFile = File.createTempFile("test", ".json");
        tempFile.deleteOnExit();
        String jsonData = "[{\"group\":\"Group1\",\"type\":\"TypeA\",\"number\":12345,\"weight\":100}]";
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(jsonData);
        }
        return tempFile;
    }

    @Test
    public void testMainWithValidCsvFile() throws IOException {
        File tempFile = createTempCsvFile();

        String input = tempFile.getAbsolutePath() + "\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        assertTrue(outContent.toString().contains("Суммарный вес объектов в каждой группе:"));
        assertTrue(outContent.toString().contains("Максимальный вес:"));
        assertTrue(outContent.toString().contains("Минимальный вес:"));
    }

    private File createTempCsvFile() throws IOException {
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit();
        String csvData = "group,type,number,weight\nGroup1,TypeA,12345,100\n";
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(csvData);
        }
        return tempFile;
    }
}
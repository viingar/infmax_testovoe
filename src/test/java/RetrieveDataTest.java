import org.example.Model.Data;
import org.example.RetrieveData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RetrieveDataTest {

    @Test
    public void testAnalyze_withData() {
        List<Data> objects = Arrays.asList(
                new Data("Group1", "TypeA", 1, 100),
                new Data("Group1", "TypeA", 2, 150),
                new Data("Group2", "TypeB", 3, 200)
        );

        RetrieveData retrieveData = new RetrieveData();

        retrieveData.extraction(objects);

    }


    @Test
    public void testAnalyze_withSingleObject() {
        List<Data> objects = Arrays.asList(
                new Data("Group1", "TypeA", 1, 50)
        );

        RetrieveData retrieveData = new RetrieveData();
        retrieveData.extraction(objects);

    }
}
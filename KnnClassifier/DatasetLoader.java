import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DatasetLoader {
    public List<Iris> getIrisDataset(String fileName) {
        String line;
        List<Iris> iris = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Iris newIris = new Iris(Integer.parseInt(data[0]), Double.parseDouble(data[1]),
                        Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), data[5]);

                iris.add(newIris);
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return iris;
    }

    public List<Iris> getTrainingDataset(List<Iris> fullDataset) {
        List<Iris> trainingDataset = new ArrayList<Iris>();

        trainingDataset.addAll(fullDataset.subList(0, 30));
        trainingDataset.addAll(fullDataset.subList(50, 80));
        trainingDataset.addAll(fullDataset.subList(100, 130));

        return trainingDataset;
    }

    public List<Iris> getTestDataset(List<Iris> fullDataset) {
        List<Iris> testDataset = new ArrayList<Iris>();

        testDataset.addAll(fullDataset.subList(30, 50));
        testDataset.addAll(fullDataset.subList(80, 100));
        testDataset.addAll(fullDataset.subList(130, 150));

        return testDataset;
    }
}

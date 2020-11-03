import java.io.*;
import java.util.*;

class Main {

    public static void registerPerformance(String data) throws IOException {
        File performanceFile = new File("./comparison/performances.txt");
        FileWriter fr = new FileWriter(performanceFile, true);
        BufferedWriter br = new BufferedWriter(fr);

        br.append(data);
        br.close();
        fr.close();
    }

    public static void main(String[] args) throws Exception {
        DatasetLoader loader = new DatasetLoader();
        List<Iris> irisDataset = loader.getIrisDataset("./dataset/Iris.csv");
        List<Iris> testDataset = loader.getTestDataset(irisDataset);
        List<Iris> trainingDataset = loader.getTrainingDataset(irisDataset);
        int count = 0;
        int tests = 0;

        for (int k = 3; k < 33; k += 3) {
            // Serial KNN Classifier
            long startTime = System.currentTimeMillis();
            SerialKnnClassifier skn = new SerialKnnClassifier();

            for (Iris iris : testDataset) {
                String classification = skn.classify(iris, trainingDataset, k);

                if (classification.equals(iris.species)) {
                    count += 1;
                }

                tests += 1;
            }
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);

            String performance = "Serial Knn Classifier - Iteration with k = " + k + "\n";
            performance += "Serial Accuracy : " + ((count * 100) / tests) + "%" + "\n";
            performance += "Serial KNN Classifier duration: " + (duration / 1000.0) + " seconds\n";
            performance += "*********************************\n\n";

            System.out.println("Registering performance for Serial Knn Classifier - Iteration with k = " + k);
            registerPerformance(performance);

            // Concurrent KNN Classifier
            long startConcurrentTime = System.currentTimeMillis();
            ConcurrentKnnClassifier ckn = new ConcurrentKnnClassifier();
            count = 0;
            tests = 0;

            for (Iris iris : testDataset) {
                String classification = ckn.classify(iris, trainingDataset, k);

                if (classification.equals(iris.species)) {
                    count += 1;
                }

                tests += 1;
            }
            long endConcurrentTime = System.currentTimeMillis();
            long concurrentDuration = (endConcurrentTime - startConcurrentTime);

            String cPerformance = "Concurrent Knn Classifier - Iteration with k = " + k + "\n";
            cPerformance += "Concurrent Accuracy : " + ((count * 100) / tests) + "%" + "\n";
            cPerformance += "Concurrent KNN Classifier duration: " + (concurrentDuration / 1000.0) + " seconds\n";
            cPerformance += "*********************************\n\n";

            System.out.println("Registering performance for Concurrent Knn Classifier - Iteration with k = " + k);
            registerPerformance(cPerformance);
        }
    }
}

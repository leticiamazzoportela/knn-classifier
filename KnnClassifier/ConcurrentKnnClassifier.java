import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.*;

class DistanceTask implements Runnable {
    Map<Integer, Double> distances = new HashMap();
    Iris testInstance;
    Iris trainingInstance;
    CountDownLatch endController;

    DistanceTask(Map<Integer, Double> distances, Iris testInstance, Iris trainingInstance, CountDownLatch endController) {
        this.distances = distances;
        this.testInstance = testInstance;
        this.trainingInstance = trainingInstance;
        this.endController = endController;
    }

    @Override
    public void run() {
        double distance = testInstance.calculateDistance(trainingInstance, testInstance);
        distances.put(trainingInstance.id, distance);

        endController.countDown();
    }
}

class ConcurrentKnnClassifier {
    Map<Integer, Double> distances = new HashMap();

    String classify(Iris testInstance, List<Iris> trainingDataset, int kn) throws Exception {
        final int numberOfThreads = Runtime.getRuntime().availableProcessors();
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch endController = new CountDownLatch(trainingDataset.size());

        for (Iris trainingInstance : trainingDataset) {
            DistanceTask dt = new DistanceTask(distances, testInstance, trainingInstance, endController);
            executor.execute(dt);
        }
        endController.await();

        try {
            Map<Integer, Double> sortedDistances = distances.entrySet().parallelStream().sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

            int count = 0;
            Vector<String> kFirstSpecies = new Vector<>();
            for (Map.Entry<Integer, Double> entry : sortedDistances.entrySet()) {
                if (count == kn)
                    break;

                if (entry.getKey() < trainingDataset.size()) {
                    kFirstSpecies.addElement(trainingDataset.get(entry.getKey()).species);
                    count++;
                }
            }

            String species = kFirstSpecies.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                    .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

            executor.shutdown();

            return species;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return "";
    }
}

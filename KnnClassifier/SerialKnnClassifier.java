import java.util.*;
import java.util.stream.Collectors;

class SerialKnnClassifier {
    Map<Integer, Double> distances = new HashMap();

    String classify(Iris testInstance, List<Iris> trainingDataset, int kn) {
        for (Iris trainingInstance : trainingDataset) {
            double distance = testInstance.calculateDistance(trainingInstance, testInstance);
            distances.put(trainingInstance.id, distance);
        }

        Map<Integer, Double> sortedDistances = distances.entrySet().stream().sorted(Map.Entry.comparingByValue())
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

        String species = kFirstSpecies.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting())).entrySet()
                .stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

        return species;
    }
}

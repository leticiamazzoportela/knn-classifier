class Iris {
    final Integer id;
    final double sepalLength;
    final double sepalWidth;
    final double petalLength;
    final double petalWidth;
    final String species;

    public Iris(Integer id, double sepalLength, double sepalWidth, double petalLength, double petalWidth, String species) {
        this.id = id;
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.species = species;
    }

    public double calculateDistance(Iris iris1, Iris iris2) {
        double distance =
				Math.pow((iris2.petalLength - iris1.petalLength), 2) +
				Math.pow((iris2.petalWidth - iris1.petalWidth), 2) +
				Math.pow((iris2.sepalLength - iris1.sepalLength), 2) +
				Math.pow((iris2.sepalWidth - iris1.sepalWidth), 2);
        
		return Math.sqrt(distance);
    }
}
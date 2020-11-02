package KnnClassifier;

class Iris {
    private final double sepalLength;
    private final double sepalWidth;
    private final double petalLength;
    private final double petalWidth;
    private final String name;

    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String name) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.name = name;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }

    @Override
    public String toString() {
        return "Iris { " + "sepal_length = " + sepalLength + ", sepal_width = " + sepalWidth + ", petal_length = "
                + petalLength + ", petal_width = " + petalWidth + ", species = '" + name + '\'' + " }";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        Iris iris = (Iris) object;
        return Double.compare(iris.sepalLength, sepalLength) == 0 && Double.compare(iris.sepalWidth, sepalWidth) == 0
                && Double.compare(iris.petalLength, petalLength) == 0
                && Double.compare(iris.petalWidth, petalWidth) == 0 && name.equals(((Iris) object).name);
    }
}
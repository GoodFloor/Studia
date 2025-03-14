package laboratories.lab03;
public class GeometricStatistics<T extends Number> {
    private double currentProduct;
    private int insertedElements;
    private T currentMax;
    private T currentMin;
    private boolean nothingInsertedYet;
    GeometricStatistics() {
        currentProduct = 1;
        insertedElements = 0;
        nothingInsertedYet = true;
    }

    public void addElement(T newElement) throws InvalidStatisticsException {
        if (newElement.doubleValue() <= 0.) {
            throw new InvalidStatisticsException(newElement + " isn't a positive number");
        }
        currentProduct = currentProduct * newElement.doubleValue();
        insertedElements++;
        if (nothingInsertedYet || newElement.doubleValue() < currentMin.doubleValue() || newElement.longValue() < currentMin.longValue()) {
            currentMin = newElement;
        }
        if (nothingInsertedYet || newElement.doubleValue() > currentMax.doubleValue() || newElement.longValue() > currentMax.longValue()) {
            currentMax = newElement;
        }
        nothingInsertedYet = false;
    }
    public double getProduct() throws InvalidStatisticsException {
        if (nothingInsertedYet) {
            throw new InvalidStatisticsException("No elements inserted");
        }
        return currentProduct;
    }
    public double getMean() throws InvalidStatisticsException {
        if (nothingInsertedYet) {
            throw new InvalidStatisticsException("No elements inserted");
        }
        return Math.pow(currentProduct, 1. / insertedElements);
    }
    public T getMax() throws InvalidStatisticsException {
        if (nothingInsertedYet) {
            throw new InvalidStatisticsException("No elements inserted");
        }
        return currentMax;
    }
    public T getMin() throws InvalidStatisticsException {
        if (nothingInsertedYet) {
            throw new InvalidStatisticsException("No elements inserted");
        }
        return currentMin;
    }
}

package hr.fer.oop;

public class Solution implements CarManager{

    private double[] averageYear;
    private int[] brandCounter;

    @Override
    public int processDataset(String input) {
        averageYear = new double[Brand.values().length];
        brandCounter = new int[Brand.values().length];
        int count = 0;
        int currentPosition = input.indexOf('\n');
        while (currentPosition != -1) {
            try {
                int lineStart = input.indexOf(',', currentPosition);
                if (lineStart == -1) {
                    return count;
                }
                int lineEnd = input.indexOf(',', lineStart + 1);
                if (lineEnd == -1) {
                    return count;
                }
                lineEnd += 4;
                String line = input.substring(lineStart + 1, lineEnd + 1);
                CarDescription newCar = processLine(line);
                count++;
                int brandOrd = newCar.getBrand().ordinal();
                averageYear[brandOrd] += newCar.getYear();
                brandCounter[brandOrd]++;
            } catch (ParseException e) {
                // System.out.println(e);
            }
            currentPosition = input.indexOf('\n', currentPosition + 1);
        }
        return count;
    }

    @Override
    public CarDescription processLine(String line) throws ParseException {
        CarDescription car = null;
        try {
            int dash = line.indexOf('-');
            int comma = line.indexOf(',');
            if (dash == -1 || comma == -1) {
                throw new ParseException("'" + line + "' is in wrong format.");
            }
            String brand = line.substring(0, dash);
            Brand brandE = Brand.valueOf(brand.toUpperCase());
            String model = line.substring(dash + 1, comma);
            int year = Integer.parseInt(line.substring(comma + 1));
            if (year <= 1885) {
                throw new ParseException("'" + line + "' doesn't contain correct year.");
            }
            car = new CarDescription(brandE, model, year);
        } catch (NumberFormatException e) {
            throw new ParseException("'" + line + "' doesn't contain correct year.");
        } catch (IllegalArgumentException e) {
            throw new ParseException("'" + line + "' doesn't contain any known brand.");
        }
        return car;
    }

    @Override
    public double averageYear(Brand brand) {
        if (brandCounter[brand.ordinal()] == 0) {
            throw new ParseException("No cars of brand " + brand);
        }
        return averageYear[brand.ordinal()] / brandCounter[brand.ordinal()];
    }

    @Override
    public int brandCount(Brand brand) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unimplemented method 'brandCount'");
    }
    
}

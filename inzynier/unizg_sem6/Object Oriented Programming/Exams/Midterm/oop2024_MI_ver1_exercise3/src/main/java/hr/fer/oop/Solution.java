package hr.fer.oop;

public class Solution implements DisabilityDatasetManager {

    private double[] maxRatings;
    private int[] disabilityCounter;
    boolean dataProcessed;

    @Override
    public int processDataset(String input) {
        String[] inputLines = input.split("\n");
        int counter = 0;
        maxRatings = new double[Disability.values().length];
        disabilityCounter = new int[Disability.values().length];
        for (int i = 1; i < inputLines.length; i++) {
            try {
                DisabilityRecord record = processLine(inputLines[i]);
                int disabilityId = record.getDisability().ordinal();
                if (maxRatings[disabilityId] < record.getRating()) {
                    maxRatings[disabilityId] = record.getRating();
                }
                disabilityCounter[disabilityId]++;
            } catch (DisabilityException e) {
                System.out.println(e.getMessage());
                counter++;
            }
        }
        dataProcessed = true;
        return counter;
    }

    @Override
    public DisabilityRecord processLine(String line) throws DisabilityException {
        int dash = line.indexOf(" -> ");
        int hash = line.indexOf(" #");
        if (dash == -1 || hash == -1 || hash < dash + 4) {
            throw new DisabilityException(line);
        }
        String name;
        String disabilityType;
        try {
            name = line.substring(0, dash);
            disabilityType = line.substring(dash + 4, hash).toUpperCase();
        } catch (Exception e) {
            throw new DisabilityException(line);
        }
        Disability disabilityTypeE;
        try {
            disabilityTypeE = Disability.valueOf(disabilityType);
        } catch (IllegalArgumentException e) {
            throw new DisabilityException(line);
        }

        double rating;
        try {
            rating = Double.parseDouble(line.substring(hash + 2));
        } catch (NumberFormatException e) {
            throw new DisabilityException(line);
        }
        if (rating < 0. || rating > 100.) {
            throw new DisabilityException(line);
        }
        return new DisabilityRecord(name, disabilityTypeE, rating / 100);
    }

    @Override
    public String maxRating(Disability disability) {
        if (dataProcessed && disabilityCounter[disability.ordinal()] > 0) {
            return Math.round(maxRatings[disability.ordinal()] * 1000) / 10. + "%";
        }
        return "0.0%";
    }

    @Override
    public String share(Disability disability) throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'share'");
    }
    
}

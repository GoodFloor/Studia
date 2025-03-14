package hr.fer.oop;

public class Documentary extends Movie {
    private boolean forProfit;

    public Documentary(String title, Director director, Rating rating, boolean forProfit) {
        super(title, director, new Person[0], rating);
        this.forProfit = forProfit;
    }

    @Override
    public String toString() {
        return "Documentary '" + title + "' by " + director + " rated " + rating + " (for profit: " + forProfit + ")";
    }

    public boolean isForProfit() {
        return forProfit;
    }
    
}

package laboratories.lab05.task1;

public class Player {
    private String name;
    private int rating;
    private double points;
    private int standing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }

    public Player(String name, int rating, double points) {
        this.name = name;
        this.rating = rating;
        this.points = points;
        this.standing = 0;
    }

    @Override
    public String toString() {
        return String.format("%s - points: %.1f, standing: %d", name, points, standing);
    }
}

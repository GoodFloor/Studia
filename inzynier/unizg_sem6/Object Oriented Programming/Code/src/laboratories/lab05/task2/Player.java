package laboratories.lab05.task2;

import java.util.Objects;

public class Player implements Comparable<Player> {
    private String firstName;
    private String lastName;
    private int rating;

    public Player(String firstName, String lastName, int rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s %s - rating: %d", firstName, lastName, rating);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player o = (Player)obj;
        return this.firstName == o.firstName && this.lastName == o.lastName;
    }

    @Override
    public int compareTo(Player o) {
        return this.rating - o.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

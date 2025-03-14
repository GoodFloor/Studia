package hr.fer.oop.ZI2024.zad2;

import java.util.Comparator;

public class ChessPlayer implements Comparable<ChessPlayer> {
    private String firstname;
    private String lastname;
    private Country country;
    private int rating;
    private int birthyear;

    public ChessPlayer(String firstname, String lastname, Country country, int rating, int birthyear) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.rating = rating;
        this.birthyear = birthyear;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Country getCountry() {
        return country;
    }

    public int getRating() {
        return rating;
    }

    public int getBirthyear() {
        return birthyear;
    }

    @Override
    public String toString() {
        return String.format("(%s) %s %s %d: %d", country, firstname, lastname, birthyear, rating);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + birthyear;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChessPlayer other = (ChessPlayer) obj;
        return firstname == other.firstname && lastname == other.lastname && birthyear == other.birthyear;
    }

    public static final Comparator<ChessPlayer> BY_LASTNAME = (p1, p2) -> p1.lastname.compareTo(p2.lastname);
    public static final Comparator<ChessPlayer> BY_FIRSTNAME = (p1, p2) -> p1.firstname.compareTo(p2.firstname);
    public static final Comparator<ChessPlayer> BY_BIRTHYEAR = (p1, p2) -> p1.birthyear - p2.birthyear;

    @Override
    public int compareTo(ChessPlayer other) {
        if (lastname.compareTo(other.lastname) != 0) {
            return lastname.compareTo(other.lastname);
        }
        if (firstname.compareTo(other.firstname) != 0) {
            return firstname.compareTo(other.firstname);
        }
        return other.birthyear - birthyear;
    }
}

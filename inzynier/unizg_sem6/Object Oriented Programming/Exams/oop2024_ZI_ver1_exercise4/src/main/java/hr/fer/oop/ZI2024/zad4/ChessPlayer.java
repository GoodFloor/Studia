package hr.fer.oop.ZI2024.zad4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class ChessPlayer {
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

    public static Stream<ChessPlayer> getOldChessPlayers(List<ChessPlayer> lplayers, int year) {
        return lplayers.stream()
            .filter(player -> player.birthyear < year)
            .sorted((p1, p2) -> p1.rating - p2.rating);
    }

    public static int getBestRating(Stream<ChessPlayer> cpstream, Country country) {
        Optional<ChessPlayer> result = cpstream
            .filter(player -> player.country == country)
            .sorted((p1, p2) -> p2.rating - p1.rating)
            .findFirst();
        if (result.isPresent()) {
            return result.get().rating;
        }
        return -1;
    }

    public static String getGoodPlayers(Stream<ChessPlayer> cpstream, int ratingthreshold) {
        StringBuilder sb = new StringBuilder();
        cpstream
            .filter(player -> player.rating > ratingthreshold)
            .forEach(player -> {
                if (!sb.isEmpty()) {
                    sb.append("; ");
                }
                sb.append(player.lastname + ", " +  player.firstname);
            });
        return sb.toString();
    }
    
    public static Map<Country, Integer> getPlayersForCountry(Stream<ChessPlayer> cpStream) {
        Map<Country, Integer> result = new HashMap<>();
        cpStream.forEach(player -> {
            int friends = 0;
            if (result.containsKey(player.country)) {
                friends = result.get(player.country);
            }
            result.put(player.country, friends + 1);
        });
        return result;
    }
}

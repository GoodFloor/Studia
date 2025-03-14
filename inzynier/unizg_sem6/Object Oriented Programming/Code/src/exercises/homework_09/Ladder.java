package exercises.homework_09;

import java.util.ArrayList;

public class Ladder {
    private ArrayList<String> ranking;
    
    public Ladder(String... playerNames) {
        ranking = new ArrayList<>(playerNames.length);
        for (String player : playerNames) {
            ranking.add(player);
        }
    }
}

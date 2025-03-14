package laboratories.lab05.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Demo {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();

        players.add(new Player("Caruana", 2820, 0.5));
        players.add(new Player("Carlsen", 2847, 1.5));
        players.add(new Player("Radjabov", 2765, 1.0));

        players.forEach(player -> {
            int standing = 1;
            for (Player player2 : players) {
                if (player2.getPoints() > player.getPoints()) {
                    standing++;
                }
            }
            player.setStanding(standing);
        });

        for (Player player2 : players) {
            System.out.println(player2);
        }
    }
}

package laboratories.lab05.task2;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        Set<Player> playerSet = new HashSet<>();

        playerSet.add(new Player("Magnus", "Carlsen", 2863));
        playerSet.add(new Player("Fabiano", "Caruana", 2835));
        playerSet.add(new Player("Liren", "Ding", 2791));

        System.out.println(playerSet.contains(new Player("Fabiano", "Caruana", 2835)));
    }
}

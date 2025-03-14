package hr.fer.oop.ZI2024.zad2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Demo {
    public static void main(String[] args) {
        List<ChessPlayer> lplayers = ChessPlayerData.loadChessPlayersSmall();
        ChessPlayer testplayer1 = new ChessPlayer("Tom", "Cruise", Country.USA, 1600, 1992);
        ChessPlayer testplayer2 = new ChessPlayer("Tom", "Cruise", Country.USA, 1605, 2024);

        System.out.println("\nPLAYERS (ListTest):");
        System.out.println(lplayers.contains(testplayer1) ? "true" : "false");
        System.out.println(lplayers.contains(testplayer2) ? "true" : "false");

        System.out.println("\nPLAYERS (HashSetTest):");
        Set<ChessPlayer> splayers1 = new HashSet<>(lplayers);
        System.out.println(splayers1.contains(testplayer1) ? "true" : "false");
        System.out.println(splayers1.contains(testplayer2) ? "true" : "false");

        System.out.println("\nPLAYERS (TreeSetTest):");
        Set<ChessPlayer> splayers2 = new TreeSet<>(lplayers);
        for (ChessPlayer player : splayers2) {
            System.out.println(player);
        }
    }
}

package hr.fer.oop.ZI2024.zad3;

import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        STEMGames stemGames = new STEMGames();

        stemGames.addStudent("Ana Horvat", 98, 94);
        stemGames.addStudent("Marko Pehar", 78, 77);
        stemGames.addStudent("Ivan Kralj", 94, 90);
        stemGames.addStudent("Lucija Rukavina", 69, 83);
        stemGames.addStudent("Petar Medak", 92, 75);
        stemGames.addStudent("Maja Posavec", 84, 86);
        stemGames.addStudent("Jure Herceg", 80, 87);
        stemGames.addStudent("Martina Car", 95, 97);
        stemGames.addStudent("Luka Horvat", 88, 90);
        stemGames.addStudent("Karla Salamun", 73, 79);
        stemGames.addStudent("Nina Kolar", 79, 82);
        stemGames.addStudent("Tomislav Trnski", 90, 89);
        stemGames.addStudent("Marta Novosel", 77, 81);
        stemGames.addStudent("Zvonimir Knez", 91, 92);
        stemGames.addStudent("Ivana Krolo", 79, 94);
        stemGames.addStudent("Filip Mlinar", 86, 92);

        int highesteng = stemGames.getHighestScoreInEng();
        int highestmath = stemGames.getHighestScoreInMath();
        int highesttotal = stemGames.getHighestScoreInTotal();

        System.out.println("Highest score in engineering: " + highesteng);
        System.out.println("Highest score in mathematics: " + highestmath);
        System.out.println("Highest total score : " + highesttotal);

        Map<String, List<String>> topstudents = stemGames.getTopStudents();

        for (String k : topstudents.keySet()) {
            List<String> v = topstudents.get(k);
            System.out.println(k + ": " + v);
        }
    }
}

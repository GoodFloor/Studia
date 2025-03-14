package hr.fer.oop;

public class Demo {
    public static void main(String[] args) {
        Director director = new Director("Robert Zemeckis", 1952, false);
        System.out.println(director.getName());
        System.out.println(director.getBirthYear());
        System.out.println(director.toString());

        Person[] actors = new Person[]{new Actor("Tom Hanks", 1956, 150), new Actor("Robin Wright", 1966, 350), new Actor("Gary Sinise", 1955, 85)};
        System.out.println(actors[0].toString());

        Movie movie = new Movie("Forrest Gump", director, actors, Rating.PG13);
        System.out.println(movie.author());
        System.out.println(movie.toString());

        Documentary documentary = new Documentary("Oceans", new Director("Jacques Perrin", 1941, true), Rating.G, false);
        System.out.println(documentary.author());
        System.out.println(documentary.toString());

        System.out.println("director instanceof Director: " + (director instanceof Director));
        System.out.println("director instanceof Person: " + (director instanceof Person));
        System.out.println("actors[0] instanceof Actor: " + (actors[0] instanceof Actor));
        System.out.println("actors[0] instanceof Person: " + (actors[0] instanceof Person));

        System.out.println("movie instanceof Movie: " + (movie instanceof Movie));
        System.out.println("movie instanceof Art: " + (movie instanceof Art));

        System.out.println("documentary instanceof Documentary: " + (documentary instanceof Documentary));
        System.out.println("documentary instanceof Movie: " + (documentary instanceof Movie));
        System.out.println("documentary instanceof Art: " + (documentary instanceof Art));
    }
}

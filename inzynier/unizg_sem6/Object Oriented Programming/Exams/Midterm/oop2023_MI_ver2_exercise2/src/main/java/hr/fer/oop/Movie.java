package hr.fer.oop;

public class Movie implements Art {
    protected String title;
    protected Director director;
    protected Person[] actors;
    protected Rating rating;

    public Movie(String title, Director director, Person[] actors, Rating rating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
    }

    @Override
    public Person author() {
       return director;
    }

    @Override
    public String toString() {
        return "Movie '" + title + "' by " + director + " rated " + rating;
    }    
}

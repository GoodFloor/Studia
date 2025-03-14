package laboratories.lab04.task2;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        Trainer trainer = new Trainer();

        Set<Move> pikachuMoveset = new HashSet<>();
        pikachuMoveset.add(new Move("Quick Attack", 40));
        pikachuMoveset.add(new Move("Thunderbolt", 90));
        Pokemon pikachu = new Pokemon("Pikachu", 25, pikachuMoveset);

        Set<Move> charmanderMoveset = new HashSet<>();
        charmanderMoveset.add(new Move("Ember", 40));
        charmanderMoveset.add(new Move("Fire Fang", 65));
        Pokemon charmander = new Pokemon("Charmander", 4, charmanderMoveset);

        Set<Move> squirtleMoveset = new HashSet<>();
        squirtleMoveset.add(new Move("Water Gun", 40));
        squirtleMoveset.add(new Move("Water Pulse", 60));
        Pokemon squirtle = new Pokemon("Squirtle", 7, squirtleMoveset);

        trainer.catchPokemon(pikachu);
        trainer.catchPokemon(charmander);
        trainer.catchPokemon(squirtle);

        System.out.println("By Pokedex numbers:");
        for (Pokemon pokemon: trainer.getTeamByPokedexNumber())
            System.out.println(pokemon.getPokedexNumber() + ": " + pokemon.getName());

        System.out.println("\nPokemon strongest moves:");
        for(Map.Entry<Pokemon, Move> entry: trainer.getStrongestMoves().entrySet())
            System.out.println(entry.getKey().getName() + ": " + entry.getValue().getName());
    }
}

package laboratories.lab04.task2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Trainer {
    Map<Integer, Pokemon> inventory = new TreeMap<>();

    public void catchPokemon(Pokemon pokemon) {
        inventory.put(pokemon.getPokedexNumber(), pokemon);
    }

    public List<Pokemon> getTeamByPokedexNumber() {
        List<Pokemon> result = new LinkedList<>();
        for (var entry : inventory.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public Map<Pokemon, Move> getStrongestMoves() {
        Map<Pokemon, Move> result = new HashMap<>();
        for (var entry : inventory.entrySet()) {
            Set<Move> moves = entry.getValue().getMoveset();
            Move strongestMove = null;
            for (Move move : moves) {
                if (strongestMove == null || move.compareTo(strongestMove) > 0) {
                    strongestMove = move;
                }
            }
            result.put(entry.getValue(), strongestMove);
        }
        return result;
    }
}

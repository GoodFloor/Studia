package hr.fer.oop.pair;

import java.util.Objects;

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        Object firstHash = first;
        Object secondHash = second;
        if (first == null) {
            firstHash = second;
        } else if (second == null) {
            firstHash = first;
        } else if (first.hashCode() > second.hashCode()) {
            firstHash = second;
            secondHash = first;
        }
        return Objects.hash(firstHash, secondHash);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        return (Objects.equals(first, other.first) && Objects.equals(second, other.second)) || (Objects.equals(second, other.first) && Objects.equals(first, other.second));
    }

    
}

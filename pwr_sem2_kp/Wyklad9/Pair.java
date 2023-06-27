// Klasa do testowania drzewa. Musi implementowac Comparable
public class Pair implements Comparable<Pair> {
  private double x;
  private double y;
  public Pair(double x, double y) { this.x = x; this.y = y; }
  public String toString() { return "<"+x+","+y+">"; }
  public int compareTo(Pair o) {
    if( x<o.x ) return -1;
    if( x==o.x && y<o.y ) return -1;
    if( x==o.x && y==o.y ) return 0;
    return 1;
  }
}

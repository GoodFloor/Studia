import javafx.scene.layout.Pane;

/**
 * Klasa pola na którym wyświetla się drzewo
 */
public class MyPane extends Pane{
    int[] shownNodes;
    boolean[][] isNull;
    int maximumNodes;
    /**
     * Główny konstruktor
     */
    MyPane() {
        super();
        shownNodes = new int[15];
        maximumNodes = 1;
        for (int i = 0; i < 15; i++) {
            maximumNodes*= 2;
        }
        isNull = new boolean[15][maximumNodes];
        for (int i = 0; i < 15; i++) {
            shownNodes[i] = 0;
            for (int j = 0; j < maximumNodes; j++) {
                isNull[i][j] = false;
            }
        }
    }
    /**
     * Funkcja rysująca węzeł drzewa o podanej wartości
     * @param nodeValue Wartość węzła wraz z formatowaniem wskazującym na pozycję w drzewie
     */
    public void draw(String nodeValue) {
        int depth = 0;
        while (nodeValue.length() != depth && nodeValue.charAt(depth) == '%') {
            depth ++;
        }
        nodeValue = nodeValue.substring(depth);
        if (nodeValue.isEmpty()) {
            return;
        }

        while (shownNodes[depth] < Math.pow(2, depth) && isNull[depth][shownNodes[depth]]) {
            shownNodes[depth]++;
        }
        if (shownNodes[depth] == Math.pow(2, depth)) {
            return;
        }
        if (nodeValue.equals("#")) {
            setNull(depth, shownNodes[depth]);
        }
        else {
            getChildren().add(new MyNode(depth, shownNodes[depth]));
            getChildren().add(new MyText(depth, shownNodes[depth], nodeValue));
        }
        shownNodes[depth] ++;
    }
    /**
     * Funkcja czyszcząca zawartość panelu
     */
    public void clear() {
        for (int i = 0; i < 15; i++) {
            shownNodes[i] = 0;
            for (int j = 0; j < maximumNodes; j++) {
                isNull[i][j] = false;
            }
        }
        getChildren().clear();
    }
    /**
     * Funkcja wywoływana po natrafieniu na pusty liść - zapamiętuje że liść na tej pozycji jest pusty i że nie istnieją jego potencjalne "dzieci"
     * @param i rząd pustego elementu
     * @param j kolumna pustego elementu
     */
    private void setNull(int i, int j) {
        if (i >= 15 || j >= maximumNodes) {
            return;
        }
        isNull[i][j] = true;
        setNull(i + 1, 2 * j);
        setNull(i + 1, 2 * j + 1);
    }
}

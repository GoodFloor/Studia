import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Klasa wyświetlająca tekst z wartością danego węzła
 */
public class MyText extends Text{
    /**
     * Konstruktor główny
     * @param depth rząd w którym węzeł ma zostać narysowany [0; 15]
     * @param alreadyInRow kolumna w której ma zostać narysowany [0; depth]
     * @param value wyświetlana wartość
     */
    MyText(int depth, int alreadyInRow, String value) {
        super(alreadyInRow * (800 / (Math.pow(2, depth))), depth * 50 + 30, value);
        setWrappingWidth(800 / (Math.pow(2, depth)));
        setTextAlignment(TextAlignment.CENTER);
    }
}

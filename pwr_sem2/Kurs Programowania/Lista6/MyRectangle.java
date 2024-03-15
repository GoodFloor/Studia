import java.util.Random;

import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Klasa kwadratowych pól
 */
public class MyRectangle extends Rectangle{
    private int redValue;
    private int greenValue;
    private int blueValue;
    /**
     * Wątek przypisany do pola
     */
    public fieldHandler myThread;
    /**
     * Główny konstruktor pól
     */
    MyRectangle(Random randomGenerator) {
        super(0, 0, 100, 100);
        redValue = randomGenerator.nextInt(256);
        greenValue = randomGenerator.nextInt(256);
        blueValue = randomGenerator.nextInt(256);
        setFill(Color.rgb(redValue, greenValue, blueValue));
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    if (getStroke() == Color.BLACK) {
                        setStroke(Color.WHITE);
                    }
                    else {
                        setStroke(Color.BLACK);
                    }
                    pause();
                }

            }
        });
    }
    /**
     * Metoda zmieniająca kolor pola
     */
    public void changeColor(int red, int green, int blue) {
        setFill(Color.rgb(red, green, blue));
        redValue = red;
        greenValue = green;
        blueValue = blue;
    }
    /**
     * Metoda zwracająca poziom czerwieni w obecnym kolorze
     */
    public int getRed() {
        return redValue;
    }
    /**
     * Metoda zwracająca poziom zieleni w obecnym kolorze
     */
    public int getGreen() {
        return greenValue;
    }
    /**
     * Metoda zwracająca poziom niebieskiego w obecnym kolorze
     */
    public int getBlue() {
        return blueValue;
    }
    /**
     * Metoda zapisująca wątek obsługujący dane pole
     * @param thread wątek obsługujący pole
     */
    public void addThread(fieldHandler thread) {
        myThread = thread;
    }
    /**
     * Metoda wstrzymująca działanie wątku
     */
    public void pause() {
        myThread.pauseThread();
    }
}

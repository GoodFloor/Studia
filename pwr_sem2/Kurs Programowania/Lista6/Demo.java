import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Klasa obsługująca wątki
 */
class fieldHandler implements Runnable {
    private Random randomGenerator;
    private MyRectangle[][] elementsArray;
    private MyRectangle handledElement;
    private int posX;
    private int posY;
    private double probability;
    private int period;
    private boolean run;
    private boolean paused;
    /**
     * Główny generator
     * @param elementsArray tablica wszystkich pól
     * @param rowIndex rząd obsługiwanego przez wątek pola
     * @param columnIndex kolumna obsługiwanego przez wątek pola
     * @param probability prawdopodobieństwo zmiany koloru
     * @param timePeriod odstęp czasowy między kolejnymi losowaniami zmian koloru określony przez użytkownika
     */
    public fieldHandler(MyRectangle[][] elementsArray, int rowIndex, int columnIndex, double probability, int timePeriod, Random randomGenerator) {
        this.randomGenerator = randomGenerator;
        this.elementsArray = elementsArray;
        this.handledElement = elementsArray[rowIndex][columnIndex];
        posX = rowIndex;
        posY = columnIndex;
        this.probability = probability;
        this.period = timePeriod;
        run = true;
        paused = false;
    }
    /**
     * Główna metoda wątku - zsynchronizowana aby wykonała się w całości na raz
     */
    synchronized public void run() {
        while(run) {
            try {
                wait((int)((randomGenerator.nextDouble() + 0.5) * period));
            } catch (Exception e) {
                System.out.println("Błąd");
            }
            if (!paused) {
            synchronized(this) {
                System.out.println("Zaczynam zmianę koloru " + posX + ", " + posY);
                if(randomGenerator.nextDouble() < probability) {
                    handledElement.changeColor(randomGenerator.nextInt(256), randomGenerator.nextInt(256), randomGenerator.nextInt(256));
                }
                else {
                    MyRectangle rightElement = elementsArray[(posX + 1) % elementsArray.length][(posY) % elementsArray[0].length];
                    MyRectangle leftElement = elementsArray[(posX - 1 + elementsArray.length) % elementsArray.length][(posY) % elementsArray[0].length];
                    MyRectangle topElement = elementsArray[(posX) % elementsArray.length][(posY + 1) % elementsArray[0].length];
                    MyRectangle bottomElement = elementsArray[(posX) % elementsArray.length][(posY - 1 + elementsArray[0].length) % elementsArray[0].length];
                    int notPaused = 0;
                    int newRed = 0;
                    int newGreen = 0;
                    int newBlue = 0;
                    if (!rightElement.myThread.paused) {
                        notPaused++;
                        newRed += rightElement.getRed();
                        newGreen += rightElement.getGreen();
                        newBlue += rightElement.getBlue();
                    }
                    if (!leftElement.myThread.paused) {
                        notPaused++;
                        newRed += leftElement.getRed();
                        newGreen += leftElement.getGreen();
                        newBlue += leftElement.getBlue();
                    }
                    if (!topElement.myThread.paused) {
                        notPaused++;
                        newRed += topElement.getRed();
                        newGreen += topElement.getGreen();
                        newBlue += topElement.getBlue();
                    }
                    if (!bottomElement.myThread.paused) {
                        notPaused++;
                        newRed += bottomElement.getRed();
                        newGreen += bottomElement.getGreen();
                        newBlue += bottomElement.getBlue();
                    }
                    if (notPaused > 0) {
                        handledElement.changeColor(newRed / notPaused, newGreen / notPaused, newBlue / notPaused);
                    }
                }
                System.out.println("Kończę zmianę koloru " + posX + ", " + posY);
            }
            }
        }
    }
    /**
     * Metoda kończąca wątek
     */
    public void killThread() {
        run = false;
    }
    /**
     * Metoda wstrzymująca wątek
     */
    public void pauseThread() {
        if (paused) {
            paused = false;
        }
      	else {
    	    paused = true;
    	}
    }
}

/**
 * Główna klasa programu
 */
public class Demo extends Application{
    static int rows;
    static int columns;
    static int period;
    static double probability;
    /**
     * Metoda uruchamiająca GUI
     * @param mainStage Główne okno
     */
    @Override
    public void start(Stage mainStage) {
        Random randomGenerator = new Random();
        MyRectangle[][] fieldsList;
        fieldHandler[][] threadsList;
        fieldsList = new MyRectangle[rows][];
        threadsList = new fieldHandler[rows][];
        mainStage.setTitle("Symulacja");
        GridPane layout = new GridPane();
        for(int i = 0; i < rows; i++) {
            fieldsList[i] = new MyRectangle[columns];
            threadsList[i] = new fieldHandler[columns];
            for(int j = 0; j < columns; j++) {
                fieldsList[i][j] = new MyRectangle(randomGenerator);
                threadsList[i][j] = new fieldHandler(fieldsList, i, j, probability, period, randomGenerator);
                fieldsList[i][j].addThread(threadsList[i][j]);
                Thread thread = new Thread(threadsList[i][j]);
                thread.start();
                layout.add(fieldsList[i][j], j, i);
            }
        }

        Scene mainScene = new Scene(layout, 102 * columns, 102 * rows);
        mainStage.setScene(mainScene);
        mainStage.show();
        mainStage.setOnCloseRequest(event -> {
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    threadsList[i][j].killThread();
                }
            }
        });
    }
    /**
     * Główna metoda programu
     * @param args Argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        if(args.length < 4) {
            System.out.println("Podano za mało argumentów");
            System.exit(0);
            return;
        }
        try {
            rows = Integer.parseInt(args[0]);
            columns = Integer.parseInt(args[1]);
            period = Integer.parseInt(args[2]);
            probability = Double.parseDouble(args[3]);
            // rows = 9;
            // columns = 18;
            // period = 500;
            // probability = 0.1;
            if(rows < 1 || columns < 1 || period < 2 || probability < 0 || probability > 1) {
                System.out.println("Błędne argumenty");
                return;
            }
            launch(args);
        } catch (Exception e) {
            System.out.println("Błędne argumenty");
        }
    }
}

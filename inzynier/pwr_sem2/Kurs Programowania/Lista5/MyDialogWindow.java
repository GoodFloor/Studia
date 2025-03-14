import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * Klasa implementująca okno dialogowe z informacjami o programie
 */
public class MyDialogWindow extends Dialog<String> {
    /**
     * Enum pozwalający określić typ okna dialogowego
     */
    public enum Type {
        /**
         * Typ pokazujący informacje o programie
         */
        INFO, 
        /**
         * Typ pokazujący instrukcję użytkowania
         */
        INSTRUCTIONS;
    }
    /**
     * Główny konstruktuor klasy okna dialogowego
     */
    MyDialogWindow(MyDialogWindow.Type type) {
        if(type == MyDialogWindow.Type.INFO) {
            setTitle("Informacje");
            setContentText("Super painter\nProgram służący do malowania prostych figur.\nAutor: Łukasz Machnik\nWersja: 0.9");
        }
        else if(type == MyDialogWindow.Type.INSTRUCTIONS) {
            setTitle("Instrukcja użytkowania");
            setContentText("Aby wstawić daną figurę wybierz z górnego menu \"Wstaw\" odpowiedni rodzaj. \n\nJeżeli wybierzesz \"Okrąg\" lub \"Prostokąt\" odpowiednia figura wstawi się w lewym górnym rogu. Następnie należy ją przeciągnąć w pożądane miejsce a następnie przesuwając myszą wybrać wymiary zatwierdzając kliknięciem. \n\nJeżeli wybierzesz \"Trójkąt\" powinieneś wybrać poprzez kliknięcie 3 kolejne punkty będące jego wierzchołkami. \n\nFigury można edytować. Jeśli są otoczone czarną obwódką to znak że są w trybie edycji. Można je wtedy przeciągać aby zmienić ich pozycję oraz zmieniać ich rozmiar za pomocą kółka myszy. Oprócz tego figury można obracać a także zmieniać ich kolor za pomocą menu kontekstowego.");
        }
        ButtonType accept = new ButtonType("Zamknij", ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(accept);
    }
}

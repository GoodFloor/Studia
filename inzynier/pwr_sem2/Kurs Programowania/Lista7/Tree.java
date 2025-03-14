/**
 * Klasa węzła drzewa
 */
class TreeElement<Type extends Comparable<Type>> {
    final Type value;
    TreeElement<Type> leftLeaf;
    TreeElement<Type> rightLeaf;
    /**
     * Konstruktor nowego węzła
     * @param element wartość elementu w nowym węźle
     */
    TreeElement(Type element) {
        value = element;
        leftLeaf = null;
        rightLeaf = null;
    }
}

/**
 * Klasa drzewa
 */
public class Tree<Type extends Comparable<Type>> {
    private TreeElement<Type> root;

    /**
     * Bazowy konstruktor nowego drzewa
     */
    public Tree() {
        root = null;
    }

    /**
     * Funkcja sprawdzająca czy drzewo jest puste
     * @return true jeśli drzewo jest puste, false w przeciwnym wypadku
     */
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * Funkcja dodająca element drzewa
     * @param element dodawany element
     */
    public void insert(Type element) {
        if(root == null)
            root = new TreeElement<Type>(element);
        else {
            TreeElement<Type> next = root;
            while(element.compareTo(next.value) != 0) {
                if(element.compareTo(next.value) < 0) {
                    if(next.leftLeaf == null) {
                        next.leftLeaf = new TreeElement<Type>(element);
                        return;
                    }
                    else
                        next = next.leftLeaf;
                }
                else if(element.compareTo(next.value) > 0) {
                    if(next.rightLeaf == null) {
                        next.rightLeaf = new TreeElement<Type>(element);
                        return;
                    }
                    else
                        next = next.rightLeaf;
                }
            } 
        }
    }
    /**
     * Funkcja usuwająca węzeł zawierający dany element i obsługująca jego ewentualne poddrzewo
     * @param element wartość elementu do usunięcia
     */
    public void delete(Type element){
        TreeElement<Type> node = root;
        if(node.value.compareTo(element) == 0) {
            //Jeśli nie ma dzieci
            if (root.leftLeaf == null && root.rightLeaf == null) {
                root = null;
            }
            //Jeśli ma tylko jedno dziecko
            else if ((root.leftLeaf != null && root.rightLeaf == null)) {
                root = root.leftLeaf;
            }
            else if (root.leftLeaf == null && root.rightLeaf != null) {
                root = root.rightLeaf;
            }
            //Jeśli ma dwoje dzieci 
                //Lewe dziecko nie ma prawego dziecka więc prawe dziecko przepinamy do lewego dziecka i zastępujemy nim węzeł
            else if (root.leftLeaf != null && root.leftLeaf.rightLeaf == null) {
                root.leftLeaf.rightLeaf = root.rightLeaf;
                root = root.leftLeaf;
            }
                //Prawe dziecko nie ma lewego dziecka więc lewe dziecko przepinamy do prawego dziecka i zastępujemy nim węzeł
            else if (root.rightLeaf != null && root.rightLeaf.leftLeaf == null) {
                root.rightLeaf.leftLeaf = root.leftLeaf;
                root = root.rightLeaf;
            }
                //Inne przypadki
            else {
                TreeElement<Type> temp = root.rightLeaf.leftLeaf;
                this.delete(root.rightLeaf.leftLeaf.value);
                temp.leftLeaf = root.leftLeaf;
                temp.rightLeaf = root.rightLeaf;
                root = temp;
            }
            return;
        }
        while (node.leftLeaf != null || node.rightLeaf != null) {
            if (node.leftLeaf != null && node.leftLeaf.value.compareTo(element) == 0) {
                //Jeśli nie ma dzieci
                if (node.leftLeaf.leftLeaf == null && node.leftLeaf.rightLeaf == null) {
                    node.leftLeaf = null;
                }
                //Jeśli ma tylko jedno dziecko
                else if ((node.leftLeaf.leftLeaf != null && node.leftLeaf.rightLeaf == null)) {
                    node.leftLeaf = node.leftLeaf.leftLeaf;
                }
                else if (node.leftLeaf.leftLeaf == null && node.leftLeaf.rightLeaf != null) {
                    node.leftLeaf = node.leftLeaf.rightLeaf;
                }
                //Jeśli ma dwoje dzieci 
                    //Lewe dziecko nie ma prawego dziecka więc prawe dziecko przepinamy do lewego dziecka i zastępujemy nim węzeł
                else if (node.leftLeaf.leftLeaf != null && node.leftLeaf.leftLeaf.rightLeaf == null) {
                    node.leftLeaf.leftLeaf.rightLeaf = node.leftLeaf.rightLeaf;
                    node.leftLeaf = node.leftLeaf.leftLeaf;
                }
                    //Prawe dziecko nie ma lewego dziecka więc lewe dziecko przepinamy do prawego dziecka i zastępujemy nim węzeł
                else if (node.leftLeaf.rightLeaf != null && node.leftLeaf.rightLeaf.leftLeaf == null) {
                    node.leftLeaf.rightLeaf.leftLeaf = node.leftLeaf.leftLeaf;
                    node.leftLeaf = node.leftLeaf.rightLeaf;
                }
                    //Inne przypadki
                else {
                    TreeElement<Type> temp = node.leftLeaf.rightLeaf.leftLeaf;
                    this.delete(node.leftLeaf.rightLeaf.leftLeaf.value);
                    temp.leftLeaf = node.leftLeaf.leftLeaf;
                    temp.rightLeaf = node.leftLeaf.rightLeaf;
                    node.leftLeaf = temp;
                }
                return;
            }
            else if (node.rightLeaf != null && node.rightLeaf.value.compareTo(element) == 0) {
                //Jeśli nie ma dzieci
                if (node.rightLeaf.leftLeaf == null && node.rightLeaf.rightLeaf == null) {
                    node.rightLeaf = null;
                }
                //Jeśli ma tylko jedno dziecko
                else if ((node.rightLeaf.leftLeaf != null && node.rightLeaf.rightLeaf == null)) {
                    node.rightLeaf = node.rightLeaf.leftLeaf;
                }
                else if (node.rightLeaf.leftLeaf == null && node.rightLeaf.rightLeaf != null) {
                    node.rightLeaf = node.rightLeaf.rightLeaf;
                }
                //Jeśli ma dwoje dzieci 
                    //Lewe dziecko nie ma prawego dziecka więc prawe dziecko przepinamy do lewego dziecka i zastępujemy nim węzeł
                else if (node.rightLeaf.leftLeaf != null && node.rightLeaf.leftLeaf.rightLeaf == null) {
                    node.rightLeaf.leftLeaf.rightLeaf = node.rightLeaf.rightLeaf;
                    node.rightLeaf = node.rightLeaf.leftLeaf;
                }
                    //Prawe dziecko nie ma lewego dziecka więc lewe dziecko przepinamy do prawego dziecka i zastępujemy nim węzeł
                else if (node.rightLeaf.rightLeaf != null && node.rightLeaf.rightLeaf.leftLeaf == null) {
                    node.rightLeaf.rightLeaf.leftLeaf = node.rightLeaf.leftLeaf;
                    node.rightLeaf = node.rightLeaf.rightLeaf;
                }
                    //Inne przypadki
                else {
                    TreeElement<Type> temp = node.rightLeaf.rightLeaf.leftLeaf;
                    this.delete(node.rightLeaf.rightLeaf.leftLeaf.value);
                    temp.leftLeaf = node.rightLeaf.leftLeaf;
                    temp.rightLeaf = node.rightLeaf.rightLeaf;
                    node.rightLeaf = temp;
                }
                return;
            }
            //Jeśli jest taka możliwość to przechodzimy do kolejnego węzła
            if(node.value.compareTo(element) == 1) {
                if (node.leftLeaf != null) {
                    node = node.leftLeaf;
                }
                else {
                    //System.out.println("Nie ma takiego elementu");
                    return;
                }
            }
            else {
                if (node.rightLeaf != null) {
                    node = node.rightLeaf;
                }
                else {
                    //System.out.println("Nie ma takiego elementu");
                    return;
                }
            }
        }
    }
    /**
     * Funkcja rekurencyjna obsługująca wyświetlanie drzewa
     * @param node obecnie wyświetlany węzeł
     * @param prefix prefix dodawany przy wyświetlaniu = poziom głębokości w drzewie
     */
    private String printNode(TreeElement<Type> node, String prefix) {
        if(node == null)
            return "\n" + prefix + "#";
        else {
            return "\n" + prefix + node.value + printNode(node.leftLeaf, prefix + "%") + printNode(node.rightLeaf, prefix + "%");
        }
    }
    /**
     * Funkcja wyświetlająca drzewo - najpierw wyświetla węzeł, następnie jego lewe poddrzewo jeśli istnieje, a na końcu jego prawe poddrzewo jeśli istnieje
     * @return Ciąg znaków z kolejnymi węzłami po enterach
     */
    public String draw() {
        return printNode(root, "") + "\nend";
    }
    /**
     * Funkcja sprawdzająca czy podana wartość istnieje w drzewie
     * @param searched szukana wartość
     * @return true jeśli wartość istnieje, false w przeciwnym wypadku
     */
    public boolean search(Type searched) {
        TreeElement<Type> node = root;
        while(node != null) {
            if(searched.compareTo(node.value) == 0)
                return true;
            else if(searched.compareTo(node.value) == -1)
                node = node.leftLeaf;
            else 
                node = node.rightLeaf;
        }
        return false;
    }
}

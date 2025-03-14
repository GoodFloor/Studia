#include <string>
#include <iostream>
#include <typeinfo>

using namespace std;

template <typename Type>
class TreeElement {
public:
    Type value;
    TreeElement<Type>* leftLeaf;
    TreeElement<Type>* rightLeaf;
    TreeElement(Type element) {
        value = element;
        leftLeaf = NULL;
        rightLeaf = NULL;
    }
};

template <typename Type>
class Tree {
private:
    TreeElement<Type>* root;
public:
    Tree() {
        root = NULL;
    }
    bool isEmpty() {
        return root == NULL;
    }
    bool insertElement(Type element) {
        if(root == NULL) {
            root = new TreeElement<Type>(element);
            return true;
        }
        else {
            TreeElement<Type>* next = root;
            while(element != next->value) {
                if(element < next->value) {
                    if(next->leftLeaf == NULL) {
                        next->leftLeaf = new TreeElement<Type>(element);
                        return true;
                    }
                    else
                        next = next->leftLeaf;
                }
                else if(element > next->value) {
                    if(next->rightLeaf == NULL) {
                        next->rightLeaf = new TreeElement<Type>(element);
                        return true;
                    }
                    else
                        next = next->rightLeaf;
                }
            } 
        }
        return false;
    }
    void deleteElement(Type element) {
        TreeElement<Type>* node = root;
        if(node->value == element) {
            //Jeśli nie ma dzieci
            if (root->leftLeaf == NULL && root->rightLeaf == NULL) {
                root = NULL;
            }
            //Jeśli ma tylko jedno dziecko
            else if ((root->leftLeaf != NULL && root->rightLeaf == NULL)) {
                root = root->leftLeaf;
            }
            else if (root->leftLeaf == NULL && root->rightLeaf != NULL) {
                root = root->rightLeaf;
            }
            //Jeśli ma dwoje dzieci 
                //Lewe dziecko nie ma prawego dziecka więc prawe dziecko przepinamy do lewego dziecka i zastępujemy nim węzeł
            else if (root->leftLeaf != NULL && root->leftLeaf->rightLeaf == NULL) {
                root->leftLeaf->rightLeaf = root->rightLeaf;
                root = root->leftLeaf;
            }
                //Prawe dziecko nie ma lewego dziecka więc lewe dziecko przepinamy do prawego dziecka i zastępujemy nim węzeł
            else if (root->rightLeaf != NULL && root->rightLeaf->leftLeaf == NULL) {
                root->rightLeaf->leftLeaf = root->leftLeaf;
                root = root->rightLeaf;
            }
                //Inne przypadki
            else {
                TreeElement<Type>* temp = root->rightLeaf->leftLeaf;
                this->deleteElement(root->rightLeaf->leftLeaf->value);
                temp->leftLeaf = root->leftLeaf;
                temp->rightLeaf = root->rightLeaf;
                root = temp;
            }
            cout << "Pomyślnie usunięto element" << endl;
            return;
        }
        while (node->leftLeaf != NULL || node->rightLeaf != NULL) {
            if (node->leftLeaf != NULL && node->leftLeaf->value == element) {
                //Jeśli nie ma dzieci
                if (node->leftLeaf->leftLeaf == NULL && node->leftLeaf->rightLeaf == NULL) {
                    node->leftLeaf = NULL;
                }
                //Jeśli ma tylko jedno dziecko
                else if ((node->leftLeaf->leftLeaf != NULL && node->leftLeaf->rightLeaf == NULL)) {
                    node->leftLeaf = node->leftLeaf->leftLeaf;
                }
                else if (node->leftLeaf->leftLeaf == NULL && node->leftLeaf->rightLeaf != NULL) {
                    node->leftLeaf = node->leftLeaf->rightLeaf;
                }
                //Jeśli ma dwoje dzieci 
                    //Lewe dziecko nie ma prawego dziecka więc prawe dziecko przepinamy do lewego dziecka i zastępujemy nim węzeł
                else if (node->leftLeaf->leftLeaf != NULL && node->leftLeaf->leftLeaf->rightLeaf == NULL) {
                    node->leftLeaf->leftLeaf->rightLeaf = node->leftLeaf->rightLeaf;
                    node->leftLeaf = node->leftLeaf->leftLeaf;
                }
                    //Prawe dziecko nie ma lewego dziecka więc lewe dziecko przepinamy do prawego dziecka i zastępujemy nim węzeł
                else if (node->leftLeaf->rightLeaf != NULL && node->leftLeaf->rightLeaf->leftLeaf == NULL) {
                    node->leftLeaf->rightLeaf->leftLeaf = node->leftLeaf->leftLeaf;
                    node->leftLeaf = node->leftLeaf->rightLeaf;
                }
                    //Inne przypadki
                else {
                    TreeElement<Type>* temp = node->leftLeaf->rightLeaf->leftLeaf;
                    this->deleteElement(node->leftLeaf->rightLeaf->leftLeaf->value);
                    temp->leftLeaf = node->leftLeaf->leftLeaf;
                    temp->rightLeaf = node->leftLeaf->rightLeaf;
                    node->leftLeaf = temp;
                }
                cout << "Pomyślnie usunięto element" << endl;
                return;
            }
            else if (node->rightLeaf != NULL && node->rightLeaf->value == element) {
                //Jeśli nie ma dzieci
                if (node->rightLeaf->leftLeaf == NULL && node->rightLeaf->rightLeaf == NULL) {
                    node->rightLeaf = NULL;
                }
                //Jeśli ma tylko jedno dziecko
                else if ((node->rightLeaf->leftLeaf != NULL && node->rightLeaf->rightLeaf == NULL)) {
                    node->rightLeaf = node->rightLeaf->leftLeaf;
                }
                else if (node->rightLeaf->leftLeaf == NULL && node->rightLeaf->rightLeaf != NULL) {
                    node->rightLeaf = node->rightLeaf->rightLeaf;
                }
                //Jeśli ma dwoje dzieci 
                    //Lewe dziecko nie ma prawego dziecka więc prawe dziecko przepinamy do lewego dziecka i zastępujemy nim węzeł
                else if (node->rightLeaf->leftLeaf != NULL && node->rightLeaf->leftLeaf->rightLeaf == NULL) {
                    node->rightLeaf->leftLeaf->rightLeaf = node->rightLeaf->rightLeaf;
                    node->rightLeaf = node->rightLeaf->leftLeaf;
                }
                    //Prawe dziecko nie ma lewego dziecka więc lewe dziecko przepinamy do prawego dziecka i zastępujemy nim węzeł
                else if (node->rightLeaf->rightLeaf != NULL && node->rightLeaf->rightLeaf->leftLeaf == NULL) {
                    node->rightLeaf->rightLeaf->leftLeaf = node->rightLeaf->leftLeaf;
                    node->rightLeaf = node->rightLeaf->rightLeaf;
                }
                    //Inne przypadki
                else {
                    TreeElement<Type>* temp = node->rightLeaf->rightLeaf->leftLeaf;
                    this->deleteElement(node->rightLeaf->rightLeaf->leftLeaf->value);
                    temp->leftLeaf = node->rightLeaf->leftLeaf;
                    temp->rightLeaf = node->rightLeaf->rightLeaf;
                    node->rightLeaf = temp;
                }
                cout << "Pomyślnie usunięto element" << endl;
                return;
            }
            //Jeśli jest taka możliwość to przechodzimy do kolejnego węzła
            if(node->value > element) {
                if (node->leftLeaf != NULL) {
                    node = node->leftLeaf;
                }
                else {
                    cout << "Podany element nie istnieje!" << endl;
                    return;
                }
            }
            else {
                if (node->rightLeaf != NULL) {
                    node = node->rightLeaf;
                }
                else {
                    cout << "Podany element nie istnieje!" << endl;
                    return;
                }
            }
        }
    }
    void printNode(TreeElement<Type>* node, string prefix) {
        if(node != NULL) {
            cout << endl << prefix << node->value;
            printNode(node->leftLeaf, prefix + "-");
            printNode(node->rightLeaf, prefix + "-");
        }
    }
    void draw() {
		cout << "Każdy - oznacza jeden poziom głębokości w drzewie, najpierw wypisuje się wartość węzła, potem jego całe lewe poddrzewo, potem jego całe prawe poddrzewo: ";
        printNode(root, "");
        cout << endl;
        return;
    }
    bool search(Type searched) {
        TreeElement<Type>* node = root;
        while(node != NULL) {
            if(searched == node->value)
                return true;
            else if(searched < node->value)
                node = node->leftLeaf;
            else 
                node = node->rightLeaf;
        }
        return false;
    }
};

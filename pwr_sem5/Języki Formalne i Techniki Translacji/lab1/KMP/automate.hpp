#include <string>

class Automate
{
private:
    int currentState;
    std::string pattern;
    int* pi;
public:
    bool nextLetter(char t);
    Automate(std::string pattern);
    ~Automate();
};

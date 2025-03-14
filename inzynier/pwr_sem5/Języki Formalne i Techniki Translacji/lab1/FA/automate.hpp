#include <string>

class Automate
{
private:
    int currentState;
    std::string pattern;
    std::string alphabet;
    int** delta;
    bool isSuffix(std::string a, std::string b);
public:
    bool nextLetter(unsigned char c);
    Automate(std::string pattern);
    ~Automate();
};

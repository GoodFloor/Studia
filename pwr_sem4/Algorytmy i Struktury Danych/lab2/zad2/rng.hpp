#include <random>

class Rng
{
private:
    std::mt19937 gen;
    std::uniform_int_distribution<> dist;
public:
    Rng(int minValue, int maxValue);
    ~Rng();
    int nextInt();
};

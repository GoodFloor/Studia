#include <random>
#include "rng.hpp"

Rng::Rng(int minValue, int maxValue)
{
    std::random_device rd;
    gen = std::mt19937(rd());
    dist = std::uniform_int_distribution<>(minValue, maxValue);
}

Rng::~Rng()
{
}

int Rng::nextInt()
{
    return dist(gen);
}

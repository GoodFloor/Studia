# Łukasz Machnik
include("Approximation.jl")

function a(x)
    exp(x)
end

function b(x)
    x^2 * sin(x)
end

for n = 5:5:15
    Approximation.rysujNnfx(a, 0.0, 1.0, n)
    Approximation.rysujNnfx(b, -1.0, 1.0, n)
end
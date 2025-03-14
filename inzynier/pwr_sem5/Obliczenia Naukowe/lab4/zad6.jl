# Łukasz Machnik
include("Approximation.jl")

function a(x)
    abs(x)
end

function b(x)
    1 / (1 + x^2)
end

for n = 5:5:15
    Approximation.rysujNnfx(a, -1.0, 1.0, n)
end
for n = 5:5:15
    Approximation.rysujNnfx(b, -5.0, 5.0, n)
end
# Łukasz Machnik
include("Approximation.jl")
using Plots

function f(x)
    (x - 1) * (x + 1)
end
function g(x) # (x-1)(x-2)(x-3)(x-4)
    x^4 - 10 * x^3 + 35 * x^2 - 50 * x + 24
end
function h(x)
    1/x
end
function i(x)
    sin(x)
end
function testFunction(f, x)
    println(f)
    y = f.(x)
    x = collect(x)
    println("X: ", x)
    println("ilorazyRoznicowe: ", Approximation.ilorazyRoznicowe(x, y))
    testX = range(x[1], x[length(x)], length=1000)
    minDelta = floatmax()
    maxDelta = 0.0
    for t in testX
        delta = abs(f(t)-Approximation.warNewton(x, y, t))
        if delta < minDelta
            minDelta = delta
        end
        if delta > maxDelta
            maxDelta = delta
        end
    end
    # println("max|f(x)-p(x)|:", maxDelta)
    println("warNewton: minDelta = $minDelta, maxDelta = $maxDelta")
    println("naturalna: ", Approximation.naturalna(x, y))
    p = Approximation.rysujNnfx(f, x[1], x[length(x)], length(x))
    title!(string(f))
    # savefig(string(f))
    println()
end

x = range(-1.0, 1.0, length=5)
# testFunction(f, x)

# x = range(1.0, 4.0, length=5)
# testFunction(g, x)

# x = range(4.0, 16.0, length=5)
# testFunction(h, x)

# x = range(0.0, pi, length=5)
# testFunction(i, x)

x = [-1.0, 0.0, 1.0, 2.0]
y = [-1.0, 0.0, -1.0, 2.0]
println(Approximation.naturalna(x, y))

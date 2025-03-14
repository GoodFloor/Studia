# Łukasz Machnik

# Funkcja zwracająca wartość f(x)
function f(x)
    T = typeof(x)
    return (sqrt(x * x + T(1)) - T(1))
end

# Funkcja zwracająca wartość g(x)
function g(x)
    T = typeof(x)
    return ((x * x) / (sqrt(x * x + T(1)) + T(1)))
end

x = Float64(1 / 8) # Pierwsza sprawdzana wartość x
c = -1 # Początkowa potęga 8
while c > -180
    println("8^($(c)):")
    a = f(x)
    b = g(x)
    if a == b
        println("\tf(x) = g(x) = $(a)")
    else
        println("\tf(x) = $(a)")
        println("\tg(x) = $(b)")
    end
    global x /= 8
    global c -= 1
end

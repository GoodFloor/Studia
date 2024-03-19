# Łukasz Machnik

delta = Float64(2^-52) # Krok
x = Float64(1) + delta # Pierwsza sprawdzana liczba

while Float64(x * Float64(1.0 / x)) == 1 && x < 2.0
    global x += delta
end

println(x)

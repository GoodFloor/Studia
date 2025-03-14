# Łukasz Machnik

floatTypes = [Float16, Float32, Float64] # Wszystkie testowane typy zmiennych - tablica wykorzystywana w pętlach aby wykonać zadanie dla każdego typu

# MACHEPS
println("Macheps:")

for T in floatTypes
    x = T(1) 
    oldX = T(1) # Zapamiętuje wartość x z poprzedniej iteracji
    while x + T(1) > T(1)
        oldX = x 
        x /= T(2)
    end
    println("\t$(T):  $(oldX)")
    println("\tExpected: $(eps(T))")
end

# ETA
println("Eta:")

for T in floatTypes
    x = T(1)
    oldX = T(1) # Zapamiętuje wartość x z poprzedniej iteracji
    while x > T(0)
        oldX = x 
        x /= T(2)
    end
    println("\t$(T):  $(oldX)")
    println("\tExpected: $(nextfloat(T(0)))")
end

# FLOATMIN
println("Floatmin:")
println(string("\tFloat16: ", floatmin(Float16)))
println(string("\tFloat32: ", floatmin(Float32)))
println(string("\tFloat64: ", floatmin(Float64)))


# FLOATMAX
println("Floatmax:")

for T in floatTypes
    x = T(T(2) - eps(T))
    oldX = T(1) # Zapamiętuje wartość x z poprzedniej iteracji
    while !isinf(x)
        oldX = x 
        x *= 2
    end
    println("\t$(T):  $(oldX)")
    println("\tExpected: $(floatmax(T))")
end
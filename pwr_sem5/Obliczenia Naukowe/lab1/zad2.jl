# Łukasz Machnik

floatTypes = [Float16, Float32, Float64] # Wszystkie testowane typy zmiennych - tablica wykorzystana w pętli aby obliczyć wynik dla każdego typu

for T in floatTypes
    x = T(T(3) * (T(4) / T(3) - T(1)) - T(1))
    println("$(T):  $(x)")
    println("Expected: $(eps(T))")
end
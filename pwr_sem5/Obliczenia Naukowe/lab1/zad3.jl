# Łukasz Machnik

function bitRep(x::Float64) # Funkcja zwracająca reprezentację bitową liczby x przetworzoną dla łatwiejszego odczytu
    str = bitstring(x)
    separator = " "
    # return [str[1], str[2:12], str[13:64]]
    return string(str[1], separator, str[2:12], separator, str[13:64])
end

delta = Float64(2^-51) # Krok
x = Float64(2) # Pierwsza liczba przedziału który sprawdzamy
intervalsK = [0:4, 2^52 - 4:2^52 - 1] # Zakresy k dla których chcemy wyświetlić wyniki

for interval in intervalsK
    for k in interval
        println(bitRep(x + k * delta))
    end
end

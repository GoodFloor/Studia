# Łukasz Machnik

# Funkcja obliczająca iloczyn skalarny metodą (a)
#   T - typ danych dla jakiego wykonujemy obliczenia
#   x - 1. wektor
#   y - 2. wektor
function a(T, x, y) 
    sum = T(0) # Zmienna przechowująca dotychczas obliczoną sumę
    size = length(x) # Rozmiar wektora x (żeby funkcja była skalowalna dla różnych rozmiarów wektorów)
    for i = 1:size
        sum += T(x[i]) * T(y[i])
    end
    return sum
end

# Funkcja obliczająca iloczyn skalarny metodą (b)
#   T - typ danych dla jakiego wykonujemy obliczenia
#   x - 1. wektor
#   y - 2. wektor
function b(T, x, y)
    sum = T(0) # Zmienna przechowująca dotychczas obliczoną sumę
    size = length(x) # Rozmiar wektora x (żeby funkcja była skalowalna dla różnych rozmiarów wektorów)
    for i = 0:(size - 1)
        sum += T(x[size - i]) * T(y[size - i])
    end
    return sum
end

# Funkcja obliczająca iloczyn skalarny metodą (c)
#   T - typ danych dla jakiego wykonujemy obliczenia
#   x - 1. wektor
#   y - 2. wektor
function c(T, x, y)
    size = length(x) # Rozmiar wektora x (żeby funkcja była skalowalna dla różnych rozmiarów wektorów)
    tempPos = zeros(T, size) # Tablica przechowująca dotychczas policzone iloczyny >= 0
    tempNeg = zeros(T, size) # Tablica przechowująca dotychczas policzone iloczyny < 0
    currPos = 1 # ID pierwszego wolnego miejsca w tablicy tempPos
    currNeg = 1 # ID pierwszego wolnego miejsca w tablicy tempNeg
    for i = 1:size
        temp = T(T(x[i]) * T(y[i]))
        if temp < T(0)
            tempNeg[currNeg] = temp
            currNeg += 1
        else
            tempPos[currPos] = temp
            currPos += 1
        end
    end
    sort(tempPos, rev = true)
    sort(tempNeg)
    sumPos = T(0) # Zmienna przechowująca dotychczas policzoną sumę liczb z tablicy tempPos
    sumNeg = T(0) # Zmienna przechowująca dotychczas policzoną sumę liczb z tablicy tempNeg
    for i = 1:(currPos - 1)
        sumPos += tempPos[i]
    end
    for i = 1:(currNeg - 1)
        sumNeg += tempNeg[i]
    end
    return sumPos + sumNeg
end

# Funkcja obliczająca iloczyn skalarny metodą (d)
#   T - typ danych dla jakiego wykonujemy obliczenia
#   x - 1. wektor
#   y - 2. wektor
function d(T, x, y)
    size = length(x) # Rozmiar wektora x (żeby funkcja była skalowalna dla różnych rozmiarów wektorów)
    tempPos = zeros(T, size) # Tablica przechowująca dotychczas policzone iloczyny >= 0
    tempNeg = zeros(T, size) # Tablica przechowująca dotychczas policzone iloczyny < 0
    currPos = 1 # ID pierwszego wolnego miejsca w tablicy tempPos
    currNeg = 1 # ID pierwszego wolnego miejsca w tablicy tempNeg
    for i = 1:size
        temp = T(T(x[i]) * T(y[i]))
        if temp < T(0)
            tempNeg[currNeg] = temp
            currNeg += 1
        else
            tempPos[currPos] = temp
            currPos += 1
        end
    end
    sort(tempPos)
    sort(tempNeg, rev = true)
    sumPos = T(0) # Zmienna przechowująca dotychczas policzoną sumę liczb z tablicy tempPos
    sumNeg = T(0) # Zmienna przechowująca dotychczas policzoną sumę liczb z tablicy tempNeg
    for i = 1:size
        sumPos += tempPos[i]
        sumNeg += tempNeg[i]
    end
    return sumPos + sumNeg
end

for T in [Float64]
    x1 = [2.718281828, -3.141592654, 1.414213562, 0.5772156649, 0.3010299957]
    y1 = [1486.2497, 878366.9879, -22.37492, 4773714.647, 0.000185049]
    x2 = [2.718281828, -3.141592654, 1.414213562, 0.577215664, 0.301029995]
    y2 = [1486.2497, 878366.9879, -22.37492, 4773714.647, 0.000185049]
    sa1 = a(T, x1, y1)
    sb1 = b(T, x1, y1)
    sc1 = c(T, x1, y1)
    sd1 = d(T, x1, y1)
    sa2 = a(T, x2, y2)
    sb2 = b(T, x2, y2)
    sc2 = c(T, x2, y2)
    sd2 = d(T, x2, y2)
    println("\t(a1) = $(sa1);\t(a2) = $(sa2); delta = $(sa1 - sa2)")
    println("\t(b1) = $(sb1);\t(b2) = $(sb2); delta = $(sb1 - sb2)")
    println("\t(c1) = $(sc1);\t(c2) = $(sc2); delta = $(sc1 - sc2)")
    println("\t(d1) = $(sd1);\t(d2) = $(sd2); delta = $(sd1 - sd2)")
end

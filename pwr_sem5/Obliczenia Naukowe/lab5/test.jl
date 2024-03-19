# Łukasz Machnik
# include("blocksys.jl")
using LinearAlgebra

mutable struct MyMatrix
    n::Int
    l::Int
    A::Matrix{Float64}
    B::Vector{Float64}
    C::Matrix{Float64}
end

function createMatrix(n::Int, l::Int)
    return MyMatrix(n, l, zeros(Float64, l, n), zeros(Float64, n-l), zeros(Float64, l, n-l))
end

function printMatrix(M::MyMatrix)
    l = M.l
    n = M.n
    aColumnId = 1
    bId = 1
    cColumnId = 1
    for bigRow = 1:(n/l)
        blanks = (bigRow - 1) * 4 - 1
        for smallRow = 1:l
            for b = 1:blanks
                print("$(Float64(0))\t")
            end
            if bigRow > 1
                print("$(round(M.B[bId], sigdigits=2))\t")
                bId += 1
            end
            for i in 1:l
                print("$(round(M.A[smallRow, aColumnId], sigdigits=2))\t")
                aColumnId += 1;
            end
            if smallRow < l 
                aColumnId -= l
            end
            if bigRow < n/l
                for i in 1:l
                    print("$(round(M.C[smallRow, cColumnId], sigdigits=2))\t")
                    cColumnId += 1
                end
            end
            if smallRow < l 
                cColumnId -= l
            end
            zerosLeft = n - l - l
            if bigRow > 1  
                zerosLeft -= 1
                zerosLeft -= blanks
            end
            for b = 1:zerosLeft
                print("$(Float64(0))\t")
            end
            println()
        end
    end
end

function setindex(M::MyMatrix, i::Int, j::Int, v::Float64)
    l = M.l
    if div(i-1, l) == div(j-1, l) 
        actualI = i % l
        if actualI == 0
            actualI = l
        end
        M.A[actualI, j] = v
    elseif j % l == 0 && j < i && i <= j + l 
        M.B[i - l] = v
    elseif div(i-1, l) == div(j-l-1, l) 
        actualI = i % l
        if actualI == 0
            actualI = l
        end
        M.C[actualI, j-l] = v
    else
        throw("Edytujesz nieedytowalną komórkę (M[$i, $j])")
    end
end

function getindex(M::MyMatrix, i::Int64, j::Int64)
    l = M.l
    if div(i-1, l) == div(j-1, l) 
        actualI = i % l
        if actualI == 0
            actualI = l
        end
        return M.A[actualI, j]
    elseif j % l == 0 && i > j && i < j + l 
        return M.B[i - l]
    elseif div(i-1, l) == div(j-l-1, l) 
        actualI = i % l
        if actualI == 0
            actualI = l
        end
        return M.C[actualI, j-l]
    else
        return 0.0
    end
end

function solvePhase1(A::MyMatrix, b::Vector, withPartialChoice::Bool=false)
    n = A.n
    l = A.l 
    P = ones(Int64, n)
    for i = 2:n 
        P[i] = i 
    end

    for i = 1:(n-1)
        endI = i + l - i % l    # Ostatni rząd potencjalnie mający w i-tej kolumnie coś innego niż 0 
        if endI > n 
            endI = n
        end
        
        # Wybór częściowy (wykonywany dla pierwszych l-1 elementów każdej l-elementowej "sekcji")
        if withPartialChoice && i % l != 0
            biggestEl = abs(getindex(A, i, i)) # Największy element (na moduł) z dotychczas przejrzanych
            rowId = i                           # Rząd w którym ten element się znajduje
            # Szukanie największego elementu w danej "sekcji"
            for ti = (i+1):endI 
                if abs(getindex(A, ti, i)) > biggestEl
                    biggestEl = abs(getindex(A, ti, i))
                    rowId = ti 
                end
            end

            # Jeżeli największy element w i-tej kolumnie nie jest w i-tym rzędzie to należy zamienić odpowiednie rzędy miejscami
            if rowId != i 
                endJ = endI + l
                if endJ > n 
                    endJ = n 
                end
                for j = i:endJ
                    e = getindex(A, i, j)
                    f = getindex(A, rowId, j)
                    setindex(A, i, j, f)
                    setindex(A, rowId, j, e)
                    t = P[i]
                    P[i] = P[rowId]
                    P[rowId] = t
                end

                # Należy również zamienić rzędy w wektorze prawych stron
                t = b[i]
                b[i] = b[rowId]
                b[rowId] = t
            end
        end

        # Eliminacja 
        currItem = getindex(A, i, i)

        if abs(currItem) < eps()
            throw("Niepoprawna macierz: A[$i, $i] = $currItem")
        end

        for k = (i+1):endI
            I = getindex(A, k, i) / currItem

            endJ = endI + l
            if endJ > n 
                endJ = n 
            end
            for j = (i+1):endJ
                v = getindex(A, i, j) * I 
                c = getindex(A, k, j)
                setindex(A, k, j, c - v)
            end

            b[k] = b[k] - I * b[i]

            # Zapis współczynnika I w macierzy
            setindex(A, k, i, I)
        end
    end
    return P
end

function solvePhase2(A::MyMatrix, b::Vector)
    n = A.n 
    l = A.l 
    x = zeros(Float64, n)

    lastJ = n
    for i = 1:n 
        row = n - i + 1

        x[row] = b[row]                             # x_n = b_n

        for j = (row+1):(lastJ)
            x[row] -= x[j] * getindex(A, row, j)   # x_n = b_n - a_{n+1}x_{n+1} - ...
        end

        x[row] /= getindex(A, row, row)            # x_n = (b_n - a_{n+1}x_{n+1} - ...) / a_n

        if row % l == 1 && row < n - l 
            lastJ -= l 
        end
    end
    return x
end

function readMatrix(file)
    f = open(file, "r")
    
    line = readline(f)
    s = split(line, " ")

    n::Int = parse(Int, s[1])
    l::Int = parse(Int, s[2])

    M = createMatrix(n, l)
    while !eof(f)
        line = readline(f)
        s = split(line, " ")
        
        i::Int = parse(Int, s[1])
        j::Int = parse(Int, s[2])
        v::Float64 = parse(Float64, s[3])

        setindex(M, i, j, v)
    end
    return M
end

function readVector(file)
    f = open(file, "r")
    
    line = readline(f)

    n::Int = parse(Int, line)

    V = zeros(Float64, n)
    for i in 1:n 
        line = readline(f)
        V[i] = parse(Float64, line)
    end
    return V
end

function calculateVector(M::MyMatrix)
    l = M.l 
    n = M.n 
    b = zeros(Float64, n)

    firstJ = 1
    lastJ = l + l
    for i = 1:n 
        for j = firstJ:lastJ
            b[i] += getindex(M, i, j)
        end
        if i % l == 0
            firstJ += l 
            lastJ += l 
            if i == l 
                firstJ -= 1
            end
            if lastJ > n 
                lastJ = n 
            end
        end
    end
    return b
end

function saveResult(file, x::Vector)
    f = open(file, "w")

    for a in x 
        println(f, a)
    end
end

function saveResult(file, x::Vector, actualX::Vector)
    error = norm(actualX - x) / norm(actualX)

    f = open(file, "w")

    println(f, error)
    println(f, "")
    for a in x 
        println(f, a)
    end
end

function badSolve(dataSize)
    # Wczytanie macierzy A
    f = open("./Dane$(dataSize)_1_1/A.txt", "r")
    
    line = readline(f)
    s = split(line, " ")

    n::Int = parse(Int, s[1])

    A = zeros(Float64, n, n)
    while !eof(f)
        line = readline(f)
        s = split(line, " ")
        
        i::Int = parse(Int, s[1])
        j::Int = parse(Int, s[2])
        v::Float64 = parse(Float64, s[3])

        A[i, j] = v
    end

    close(f)

    # Obliczenie wektora b
    f = open("./Dane$(dataSize)_1_1/b.txt", "r")
    
    line = readline(f)

    b = zeros(Float64, n)
    for i in 1:n 
        line = readline(f)
        b[i] = parse(Float64, line)
    end

    # Wyznaczenie rozwiązania
    x = inv(A) * b
end

function main()
    dataSize = 16
    # badSolve(dataSize)

    A = readMatrix("./Dane$(dataSize)_1_1/A.txt")

    b = readVector("./Dane$(dataSize)_1_1/b.txt")

    # b = calculateVector(A)
    solvePhase1(A, b, false)
    res = solvePhase2(A, b)

    # println(res)
    printMatrix(A)
    # saveResult("result$(dataSize).txt", res, ones(A.n))
end

main()
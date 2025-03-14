# Łukasz Machnik
include("matrixstruct.jl")
include("blocksys.jl")
using LinearAlgebra

function readMatrix(file)
    f = open(file, "r")
    
    line = readline(f)
    s = split(line, " ")

    n::Int = parse(Int, s[1])
    l::Int = parse(Int, s[2])

    M = matrixstruct.MyMatrix(n, l)
    while !eof(f)
        line = readline(f)
        s = split(line, " ")
        
        i::Int = parse(Int, s[1])
        j::Int = parse(Int, s[2])
        v::Float64 = parse(Float64, s[3])

        M[i, j] = v
    end

    close(f)
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

    close(f)
    return V
end

function calculateVector(M::matrixstruct.MyMatrix, x::Vector)
    l = M.l 
    n = M.n 
    b = zeros(Float64, n)

    firstJ = 1
    lastJ = l + l
    for i = 1:n 
        for j = firstJ:lastJ
            b[i] += M[i, j] * x[j]
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

function saveResult(file, calculatedX::Vector, actualX::Vector = [])
    f = open(file, "w")

    if !isempty(actualX)
        error = norm(actualX - calculatedX) / norm(actualX)
        println(f, "$error\n")
    end
    for x in calculatedX 
        println(f, x)
    end

    close(f)
end

function printMatrix(M::matrixstruct.MyMatrix)
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
                print("$(round(M.B[bId], digits=3))\t")
                # print("$(M.B[bId])\t")
                bId += 1
            end
            for i in 1:l
                print("$(round(M.A[smallRow, aColumnId], digits=3))\t")
                # print("$(M.A[smallRow, aColumnId])\t")
                aColumnId += 1;
            end
            if smallRow < l 
                aColumnId -= l
            end
            if bigRow < n/l
                for i in 1:l
                    print("$(round(M.C[smallRow, cColumnId], digits=3))\t")
                    # print("$(M.C[smallRow, cColumnId])\t")
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

function main()
    
    
    # dataSize = 200000
    for dataSize in [10000, 50000, 100000, 300000, 500000]
        A = readMatrix("./Dane$(dataSize)_1_1/A.txt")
        # A = readMatrix("./$(dataSize).txt")
        # b = readVector("./Dane$(dataSize)_1_1/b.txt")
        ax = ones(Float64, dataSize)
        b = calculateVector(A, ax)
        x = blocksys.solveAxb(A, b, true)
        # P = blocksys.LUdistribution(A, true)
        # x = blocksys.solveLUxb(A, b, P)
        # printMatrix(A)
        # println(x)
        saveResult("result$(dataSize)_1.txt", x, ax)
    end
end

main()

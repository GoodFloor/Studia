#Łukasz Machnik

using LinearAlgebra

function hilb(n::Int)
    # Function generates the Hilbert matrix  A of size n,
    #  A (i, j) = 1 / (i + j - 1)
    # Inputs:
    #	n: size of matrix A, n>=1
    #
    #
    # Usage: hilb(10)
    #
    # Pawel Zielinski
    if n < 1
        error("size n should be >= 1")
    end
    return [1 / (i + j - 1) for i in 1:n, j in 1:n]
end

function matcond(n::Int, c::Float64)
    # Function generates a random square matrix A of size n with
    # a given condition number c.
    # Inputs:
    #	n: size of matrix A, n>1
    #	c: condition of matrix A, c>= 1.0
    #
    # Usage: matcond(10, 100.0)
    #
    # Pawel Zielinski
    if n < 2
        error("size n should be > 1")
    end
    if c< 1.0
        error("condition number  c of a matrix  should be >= 1.0")
    end
    (U,S,V)=svd(rand(n,n))
    return U*diagm(0 =>[LinRange(1.0,c,n);])*V'
end

function relativeError(calculated::Vector{Float64}, exact::Vector{Float64})
    return norm(exact - calculated) / norm(exact)
end

function main()
    println("(a) - Macierz Hilberta\n\tn\twsk. uwar. A\terror metody Gaussa\terror metody 2")
    for n = 2:2:20
        A = hilb(n)
        println(cond(A))
        x = ones(n)
        b = A*x 
        result1 = A \ b
        result2 = inv(A) * b
        println("\t\$$(n)\$ & \$$(round(cond(A); sigdigits = 4))\$ & \$$(round(relativeError(result1, x); sigdigits = 4))\$ & \$$(round(relativeError(result2, x); sigdigits = 4))\$ \\\\")
    end
    println("(b) - Macierz losowa\n\tn\tc\terror metody Gaussa\terror metody 2")
    for n in [5, 10, 20]
        for c in [1.0, 10.0, 1e3, 1e7, 1e12, 1e16]
            A = matcond(n, c)
            x = ones(n)
            b = A*x 
            result1 = A \ b
            result2 = inv(A) * b
            println("\t\$$(n)\$ & \$$(c)\$ & \$$(round(relativeError(result1, x); sigdigits = 4))\$ & \$$(round(relativeError(result2, x); sigdigits = 4))\$ \\\\")
        end
    end
end

main()
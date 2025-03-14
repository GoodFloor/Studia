using LinearAlgebra

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

# Zofia Wiora

function solve(A, n, c :: Float64)
    x = ones(n)
    b = A*x

    println(cond(A))

    x1 = A\b
    err1 = norm(x1-x)/norm(x)   

    x2 = inv(A)*b
    err2 = norm(x2-x)/norm(x)

    if c > -1
        println(n, " & \$10^{", Int(c), "}\$ & ", err1,  " & ", err2, " & ", cond(A),  " & ", rank(A), " \\\\ \\hline")
    else
        println(n, " & ", err1,  " & ", err2, " & ", cond(A),  " & ", rank(A), " \\\\ \\hline")
    end
end

for n in 2:20
    A = hilb(n)
    println(cond(A))
    solve(A, n, -1.0)
end

for n in [5, 10, 20]
    for c in [0.0,1.0,3.0,7.0,12.0,16.0]
        A = matcond(n, Float64(10.0^c))
        solve(A, n, c)
    end
end
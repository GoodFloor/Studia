# Łukasz Machnik
module matrixstruct
    export MyMatrix

    struct MyMatrix <: AbstractMatrix{Float64}
        n::Int
        l::Int
        A::Matrix{Float64}
        B::Vector{Float64}
        C::Matrix{Float64}
    end
    
    MyMatrix(n::Int, l::Int) = MyMatrix(n, l, zeros(Float64, l, n), zeros(Float64, n-l), zeros(Float64, l, n-l))
    Base.size(M::MyMatrix) = (M.n, M.n)
    Base.IndexStyle(::Type{<:MyMatrix}) = IndexLinear()
    Base.getindex(M::MyMatrix, i::Int, j::Int) = (
        l = M.l;
        if div(i-1, l) == div(j-1, l) 
            actualI = i % l
            if actualI == 0
                actualI = l
            end
            return M.A[actualI, j]
        elseif j % l == 0 && j < i && i <= j + l 
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
    )
    Base.setindex!(M::MyMatrix, v::Float64, i::Int, j::Int) = (
        l = M.l;
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
    )
end
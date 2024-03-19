# Łukasz Machnik
module Approximation
    export ilorazyRoznicowe
    export warNewton
    export naturalna
    export rysujNnfx

    using Combinatorics
    using Plots

    function ilorazyRoznicowe(x::Vector{Float64}, f::Vector{Float64})
        n = length(x)
        if length(f) != n
            throw("ilorazyRoznicowe: Wektory muszą być tego samego rozmiaru")
        end
        
        result = Vector{Float64}(undef, n)
        for i in 1:n
            result[i] = f[i]
        end
        for i in 2:n
            for j in n:-1:i
                result[j] = (result[j] - result[j - 1]) / (x[j] - x[j - i + 1])
            end
        end
        return result
    end

    function warNewton(x::Vector{Float64}, fx::Vector{Float64}, t::Float64)
        n = length(x)

        w = Vector{Float64}(undef, n)
        ir = fx
        w[n] = ir[n]
        for i = (n - 1):-1:1
            w[i] = ir[i] + (t - x[i]) * w[i + 1]
        end
        return w[1]
    end

    function sumOfProducts(v)
        result = 0
        for row in v
            rowResult = 1
            for element in row
                rowResult *= element
            end
            result += rowResult
        end
        return result
    end


    function naturalna(x::Vector{Float64}, fx::Vector{Float64})
        ir = fx
        n = length(x)
        a = Vector{Float64}(undef, n)
        for k in 1:n
            a[k] = ir[k]
            sign = -1
            for i in (k + 1):n
                a[k] += sign * ir[i] * sumOfProducts(combinations(x[1:(i - 1)], (i - k)))
                sign *= -1
            end
        end
        return a
    end

    function naturalFunction(a::Vector{Float64}, x::Float64)
        result = a[1]
        for i in 2:length(a)
            result += a[i] * x ^ (i - 1)
        end
        return result
    end

    function rysujNnfx(f,  a::Float64, b::Float64, n::Int)
        x = Vector{Float64}(undef, n + 1)
        y = Vector{Float64}(undef, n + 1)
        h = (b - a) / n 
        for k in 1:(n + 1)
            x[k] = a + (k - 1) * h
            y[k] = f(x[k])
        end

        precision = 100
        fx = range(a, b, length=precision)
        fy1 = f.(fx)

        fy2 = Vector{Float64}(undef, precision)
        ir = ilorazyRoznicowe(x, y)

        for t = 1:precision
            fy2[t] = warNewton(x, ir, fx[t])
        end
        # for t = 1:precision
        #     w = Vector{Float64}(undef, n + 1)
        #     w[n+1] = ir[n+1]
        #     for i = n:-1:1
        #         w[i] = ir[i] + (fx[t] - x[i]) * w[i + 1]
        #     end
        #     fy2[t] = w[1]
        # end

        # ir = ilorazyRoznicowe(x, y)
        # for k = 1:precision
        #     w = Vector{Float64}(undef, precision)
        #     w[n] = ir[n]
        #     for i = (n - 1):-1:1
        #         w[i] = ir[i] + (fx[k] - x[i]) * w[i + 1]
        #     end
        #     fy2[k] = w[1]
        # end
        
        display(plot(fx, [fy1, fy2], title="n=$n", label=["f-cja interpolowana" "wielomian interpolacyjny"]))
        # name = string(Integer(a), "_", Integer(b), "_", n, ".png")
        name = string(f, "_", n)
        savefig(name)
    end
end
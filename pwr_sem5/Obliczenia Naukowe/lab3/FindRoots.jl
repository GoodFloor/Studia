module FindRoots
    export mbisekcji
    export mstycznych
    export msiecznych
    
    function mbisekcji(f, a::Float64, b::Float64, delta::Float64, epsilon::Float64)
        if(abs(f(a)) <= epsilon)
            return (a, f(a), 0, 0)
        elseif(abs(f(b)) <= epsilon)
            return (b, f(b), 0, 0)
        elseif(sign(f(a)) == sign(f(b)))
            return (0, 0, 0, 1)
        end
        it = 0
        while true
            c = (b - a) / 2 + a
            if b - a <= delta || abs(f(c)) <= epsilon
                return (c, f(c), it, 0)
            end
            it = it + 1
            if(sign(f(a)) != sign(f(c)))
                b = c
            else
                a = c
            end
        end
    end

    function mstycznych(f, pf, x0::Float64, delta::Float64, epsilon::Float64, maxit::Int)
        v = f(x0)
        if abs(v) < epsilon
            return (x0, v, 0, 0)
        end
        for k = 1:maxit
            if(pf(x0) == 0)
                return (0, 0, k, 2)
            end
            x1 = x0 - v / pf(x0)
            v = f(x1)
            d = abs(x1 - x0)
            if(d < delta || abs(v) < epsilon)
                return(x1, v, k, 0)
            end
            x0 = x1
        end
        return (x0, v, maxit, 1)
    end

    function msiecznych(f, x0::Float64, x1::Float64, delta::Float64, epsilon::Float64, maxit::Int)
        fa = f(x0)
        if(abs(fa) < epsilon)
            return (x0, fa, 0, 0)
        end
        fb = f(x1)
        if(abs(fb) < epsilon)
            return (x1, fb, 0, 0)
        end
        for k = 1:maxit
            if(abs(fa) > abs(fb))
                t = x0
                x0 = x1
                x1 = t
                t = fa 
                fa = fb
                fb = t
            end
            s = (x1 - x0)/(fb - fa)
            x1 = x0
            fb = fa
            x0 = x0 - fa * s
            fa = f(x0)
            d = abs(x1 - x0)
            if(d < delta || abs(fa) < epsilon)
                return (x0, fa, k, 0)
            end
        end
        return (x0, fa, maxit, 1)
    end
end

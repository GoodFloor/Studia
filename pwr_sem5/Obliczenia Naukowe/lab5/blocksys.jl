# Łukasz Machnik
module blocksys
    using ..matrixstruct
    export solveAxb
    export solveLUxb
    export LUdistribution

    function swapRow(M::matrixstruct.MyMatrix, p::Int64, q::Int64)
        n = M.n 
        l = M.l 
        if div(p - 1, l) != div(q - 1, l)
            throw("Rzędy $p i $q są różnych rozmiarów!")
        end

        section = div(p - 1, l) + 1
        firstJ = (section - 1) * l 
        lastJ = (section + 1) * l 
        if firstJ < 1
            firstJ = 1
        end
        if lastJ > n 
            lastJ = n
        end

        for j = firstJ:lastJ
            t = M[p, j]
            M[p, j] = M[q, j]
            M[q, j] = t 
        end 
    end

    function swapRow(V::Vector, p::Int64, q::Int64)
        t = V[p]
        V[p] = V[q]
        V[q] = t
    end

    function unpermutate(P::Vector, x::Vector)
        for i = 1:length(x)
            while P[i] != i 
                swapRow(x, i, P[i])
                swapRow(P, i, P[i])
            end
        end
    end

    function solveAxb(A::matrixstruct.MyMatrix, b::Vector, withPartialChoice::Bool = false)
        # Inicjalizacja zmiennych
        n = A.n
        l = A.l 
        P = ones(Int64, n)  # Tablica do zapamiętywania permutacji
        for i = 2:n
            P[i] = i
        end

        lastI = l # Ostatni rząd potencjalnie mający w i-tej kolumnie coś innego niż 0 
        for i = 1:(n-1)
            if i % l == 0 && lastI < n
                lastI += l 
            end

            # Wybór częściowy (wykonywany dla pierwszych l-1 elementów każdej "sekcji")
            if withPartialChoice && i % l != 0
                biggestElement = abs(A[i, i])       # Największy element (na moduł) z dotychczas przejrzanych
                rowId = i                           # Rząd w którym ten element się znajduje
                
                # Szukanie największego elementu w danej "sekcji"
                for ti = (i+1):lastI 
                    if abs(A[ti, i]) > biggestElement
                        biggestElement = abs(A[ti, i])
                        rowId = ti 
                    end
                end

                # Jeżeli największy element nie jest w i-tym rzędzie to następuje zamiana rzędów w macierzy A i wektorze prawych stron
                if rowId != i 
                    swapRow(A, rowId, i)
                    swapRow(b, rowId, i)
                    swapRow(P, rowId, i)
                end
            end

            # Eliminacja Gaussa
            if abs(A[i, i]) < eps()
                throw("Niepoprawna macierz: A[$i, $i] = 0")
            end

            for k = (i+1):lastI
                I = A[k, i] / A[i, i]

                section = div(k - 1, l) + 1
                firstJ = (section - 1) * l 
                lastJ = (section + 1) * l 
                if firstJ <= i
                    firstJ = i
                end
                if lastJ > n 
                    lastJ = n
                end 

                for j = firstJ:lastJ
                    A[k, j] = A[k, j] - A[i, j] * I
                end
                A[k, i] = 0.0

                b[k] = b[k] - b[i] * I 
            end
        end
        
        # Wyznaczenie wektora x
        x = zeros(Float64, n)
        lastJ = n 

        for i = 1:n 
            row = n - i + 1
            x[row] = b[row]                 # x_n = b_n
            
            for j = (row+1):lastJ
                x[row] -= x[j] * A[row, j]  # x_n = b_n - a_{n+1}x_{n+1} - ...
            end

            x[row] /= A[row, row]           # x_n = (b_n - a_{n+1}x_{n+1} - ...) / a_n
            if row % l == 1 && row < n - l 
                lastJ -= l
            end
        end

        # Odpermutowanie wektora x
        if withPartialChoice
            unpermutate(P, x)
        end

        return x
    end
    
    function solveLUxb(LU::matrixstruct.MyMatrix, b::Vector, Permutation::Vector = [])
        n = LU.n 
        l = LU.l 

        # Jeżeli rozkład na macierz LU był z częściowym wyborem to odpowiednio permutuję wektor prawych stron
        if !isempty(Permutation)
            for i = 1:n 
                next = i 
                while Permutation[next] >= 1
                    temp = Permutation[next]
                    swapRow(b, i, temp)
                    Permutation[next] -= n 
                    next = temp 
                end
            end

            for i = 1:n 
                if Permutation[i] < 1
                    Permutation[i] += n 
                end
            end
        end

        # Ly = b
        firstJ = 1

        for i = 1:n 
            sum = 0.0
            for j = firstJ:(i-1)
                sum += b[j] * LU[i, j]
            end
            b[i] -= sum

            if i % l == 0
                firstJ += l
                if i == l 
                    firstJ -= 1
                end
            end
        end

        # Ux = y
        x = zeros(Float64, n)
        lastJ = n 

        for i = 1:n 
            row = n - i + 1
            x[row] = b[row]                 # x_n = b_n
            
            for j = (row+1):lastJ
                x[row] -= x[j] * LU[row, j]  # x_n = b_n - a_{n+1}x_{n+1} - ...
            end

            x[row] /= LU[row, row]           # x_n = (b_n - a_{n+1}x_{n+1} - ...) / a_n
            if row % l == 1 && row < n - l 
                lastJ -= l
            end
        end

        # Odpermutowanie wektora x
        if !isempty(Permutation)
            unpermutate(Permutation, x)
        end
        return x
    end

    function LUdistribution(M::matrixstruct.MyMatrix, withPartialChoice::Bool = false)
        # Inicjalizacja zmiennych
        n = M.n
        l = M.l 
        P = ones(Int64, n)  # Tablica do zapamiętywania permutacji
        for i = 2:n
            P[i] = i
        end

        lastI = l # Ostatni rząd potencjalnie mający w i-tej kolumnie coś innego niż 0 
        for i = 1:(n-1)
            if i % l == 0 && lastI < n
                lastI += l 
            end

            # Wybór częściowy (wykonywany dla pierwszych l-1 elementów każdej "sekcji")
            if withPartialChoice && i % l != 0
                biggestElement = abs(M[i, i])       # Największy element (na moduł) z dotychczas przejrzanych
                rowId = i                           # Rząd w którym ten element się znajduje
                
                # Szukanie największego elementu w danej "sekcji"
                for ti = (i+1):lastI 
                    if abs(M[ti, i]) > biggestElement
                        biggestElement = abs(M[ti, i])
                        rowId = ti 
                    end
                end

                # Jeżeli największy element nie jest w i-tym rzędzie to następuje zamiana rzędów w macierzy M i wektorze prawych stron
                if rowId != i 
                    swapRow(M, rowId, i)
                    swapRow(P, rowId, i)
                end
            end

            # Eliminacja Gaussa
            if abs(M[i, i]) < eps()
                throw("Niepoprawna macierz: M[$i, $i] = 0")
            end
            for k = (i+1):lastI
                I = M[k, i] / M[i, i]

                section = div(k - 1, l) + 1
                firstJ = (section - 1) * l 
                lastJ = (section + 1) * l 
                if firstJ <= i
                    firstJ = i + 1
                end
                if lastJ > n 
                    lastJ = n
                end 

                for j = firstJ:lastJ
                    M[k, j] = M[k, j] - M[i, j] * I
                end
                M[k, i] = I
            end
        end
        return P
    end
end





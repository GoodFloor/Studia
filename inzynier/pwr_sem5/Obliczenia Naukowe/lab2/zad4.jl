#Łukasz Machnik

using Polynomials

function main()
    println("(a)")
    coefficients = [
        1.0, 
        -210.0, 
        20615.0, 
        -1256850.0, 
        53327946.0, 
        -1672280820.0, 
        40171771630.0, 
        -756111184500.0, 
        11310276995381.0, 
        -135585182899530.0, 
        1307535010540395.0, 
        -10142299865511450.0, 
        63030812099294896.0, 
        -311333643161390640.0, 
        1206647803780373360.0, 
        -3599979517947607200.0, 
        8037811822645051776.0, 
        -12870931245150988800.0, 
        13803759753640704000.0, 
        -8752948036761600000.0, 
        2432902008176640000.0]
    P = Polynomial(reverse(coefficients))
    R = roots(P)
    println("\tk\tp(x_k)\tP(x_k)\t|x_k - k|")
    for k in 1:20 
        r = R[k]
        m = 1.0
        for l in 1:20
            m *= (r-(20.0 - l + 1.0))
        end
        println("\t\$$(k)\$ & \$$(round(m;sigdigits=4))\$ & \$$(round(abs(P(r)); sigdigits=4))\$ & \$$(round(abs(r - k); sigdigits=4))\$")
    end

    println("(b)")
    coefficients[2] = -210 - 2 ^ (-23)
    P = Polynomial(reverse(coefficients))
    R = roots(P)
    println("\tk\tx_k\tP(x_k)\t|x_k - k|")
    for k in 1:20 
        r = R[k]
        m = 1.0
        for l in 1:20
            m *= (r-(20.0 - l + 1.0))
        end
        # println("\t\$$(k)\$ & \$$(round(m;sigdigits=4))\$ & \$$(round(abs(P(r)); sigdigits=4))\$ & \$$(round(abs(r - k); sigdigits=4))\$")
        println("\t\$$(k)\$ & \$$(r)\$ & \$$(abs(P(r)))\$ & \$$(r - ComplexF64(k))\$")
    end
end

main()

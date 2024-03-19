#Łukasz Machnik

function nextP(p, r)
    T = typeof(p)
    return p + T(r) * p * (1 - p)
end

function main()
    println("n\tFloat64(40 iteracji)\tFloat32(40 iteracji)\tFloat32(10 + 30 iteracji)")
    result1 = zeros(Float32, 41)
    result2 = zeros(Float32, 41)
    result64 = zeros(Float64, 41)
    result1[1] = 0.01
    result2[1] = 0.01
    result64[1] = 0.01
    r = 3
    for i in 2:41
        result1[i] = nextP(result1[i - 1], r)
        result2[i] = nextP(result2[i - 1], r)
        result64[i] = nextP(result64[i - 1], r)
        if i == 11
            result2[i] = round(result2[i], RoundDown; digits = 3)
        end
        # println("\$$(i - 1)\$ & \$$(result64[i])\$ & \$$(result1[i])\$ & \$$(result2[i])\$")
    end
    for i in 2:41
        println("($(i - 1), $(result64[i]))")
    end
    for i in 2:41
        println("($(i - 1), $(result1[i]))")
    end
    for i in 2:41
        println("($(i - 1), $(result2[i]))")
    end
end

main()
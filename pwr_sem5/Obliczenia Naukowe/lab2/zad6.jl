# Łukasz Machnik

function x1(n)
    if n == 0
        return 1.0
    end
    return x1(n - 1) ^ 2 - 2.0
end

function nextX(x, c)
    return x * x + c
end

function main()
    r1 = zeros(40)
    r2 = zeros(40)
    r3 = zeros(40)
    r4 = zeros(40)
    r5 = zeros(40)
    r6 = zeros(40)
    r7 = zeros(40)
    c1 = -2
    c2 = -2
    c3 = -2
    c4 = -1
    c5 = -1
    c6 = -1
    c7 = -1
    r1[1] = nextX(1.0, c1)
    r2[1] = nextX(2.0, c2)
    r3[1] = nextX(1.99999999999999, c3)
    r4[1] = nextX(1.0, c4)
    r5[1] = nextX(-1.0, c5)
    r6[1] = nextX(0.75, c6)
    r7[1] = nextX(0.25, c7)
    for i in 2:40
        r1[i] = nextX(r1[i - 1], c1)
        r2[i] = nextX(r2[i - 1], c2)
        r3[i] = nextX(r3[i - 1], c3)
        r4[i] = nextX(r4[i - 1], c4)
        r5[i] = nextX(r5[i - 1], c5)
        r6[i] = nextX(r6[i - 1], c6)
        r7[i] = nextX(r7[i - 1], c7)
    end
    for i in 1:40
        println("$(r1[i])\t$(r2[i])\t$(r3[i])\t$(r4[i])\t$(r5[i])\t$(r6[i])\t$(r7[i])")
    end
end

main()
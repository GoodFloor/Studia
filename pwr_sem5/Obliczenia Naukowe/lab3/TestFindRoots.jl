import .FindRoots

function f(x)   # Miejsca zerowe: x ze zbioru {1, 2, 3, 4, 5, 6, 7, 8}, ciągłość dla liczb rzeczywistych
    (x - 1) * (x - 2) * (x - 3) * (x - 4) * (x - 5) * (x - 6) * (x - 7) * (x - 8)
end
function df(x)
    4 * (2 * x^7 - 63 * x^6 + 819 * x^5 - 5670 * x^4 + 22449 * x^3 - 50463 * x^2 + 59062 * x - 27396)
end
function g(x)   # Miejsca zerowe: k * pi, ciągłość na przedziałach (-pi / 2 + k * pi; pi/2 + k * pi)
    tan(x)
end
function dg(x)
    sec(x)^2
end
function h(x)   # Miejsce zerowe: x = 1, ciągłość na przedziale (0; inf)
    log(x)      
end
function dh(x)
    1 / x
end
function i(x)   # Miejsce zerowe: x = 0, ciągłość dla liczb rzeczywistych
    exp(x) - 1
end
function di(x)
    exp(x)
end

delta = 1.0e-5
epsilon = 1.0e-5
maxit = 64

println("Metoda bisekcji")
println("funkcja\t& x0\t& x1\t&r\t&f(r)\t&it")

x0 = 3.25
x1 = 4.6
wynik = FindRoots.mbisekcji(f, x0, x1, delta, epsilon)
println("\$f(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$ \\\\")

x0 = 1.6
x1 = 4.0
wynik = FindRoots.mbisekcji(g, x0, x1, delta, epsilon)
println("\$g(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$ \\\\")

x0 = 0.0001
x1 = 913.0
wynik = FindRoots.mbisekcji(h, x0, x1, delta, epsilon)
println("\$h(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$ \\\\")

x0 = -4.0
x1 = 1024.0
wynik = FindRoots.mbisekcji(i, x0, x1, delta, epsilon)
println("\$i(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$ \\\\")




println("\nMetoda stycznych")
println("funkcja\t& x0\t&r\t&f(r)\t&it\t&err")

x0 = 3.25
wynik = FindRoots.mstycznych(f, df, x0, delta, epsilon, maxit)
println("\$f(x)\$\t& \$", x0, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")

x0 = 1.6
wynik = FindRoots.mstycznych(g, dg, x0, delta, epsilon, maxit)
println("\$g(x)\$\t& \$", x0, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")

x0 = 0.0001
wynik = FindRoots.mstycznych(h, dh, x0, delta, epsilon, maxit)
println("\$h(x)\$\t& \$", x0, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")

x0 = 100.0
wynik = FindRoots.mstycznych(i, di, x0, delta, epsilon, maxit)
println("\$i(x)\$\t& \$", x0, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")




println("\nMetoda siecznych")
println("funkcja\t& x0\t& x1\t&r\t&f(r)\t&it\t&err")

x0 = 3.25
x1 = 3.5
wynik = FindRoots.msiecznych(f, x0, x1, delta, epsilon, maxit)
println("\$f(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")

x0 = 1.6
x1 = 1.5
wynik = FindRoots.msiecznych(g, x0, x1, delta, epsilon, maxit)
println("\$g(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")

x0 = 0.00001
x1 = 0.0001
wynik = FindRoots.msiecznych(h, x0, x1, delta, epsilon, maxit)
println("\$h(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")

x0 = 10.0
x1 = 25.0
wynik = FindRoots.msiecznych(i, x0, x1, delta, epsilon, maxit)
println("\$i(x)\$\t& \$", x0, "\$\t& \$", x1, "\$\t& \$", round(wynik[1]; sigdigits=4), "\$\t& \$", round(wynik[2]; sigdigits=4), "\$\t& \$", wynik[3], "\$\t& \$", wynik[4], "\$ \\\\")

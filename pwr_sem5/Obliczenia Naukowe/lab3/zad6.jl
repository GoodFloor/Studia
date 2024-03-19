import .FindRoots

function f1(x)
    exp(1 - x) - 1
end
function df1(x)
    -exp(1 - x)
end
function f2(x)
    x * exp(-x)
end
function df2(x)
    -exp(-x) * (x - 1)
end

delta = 1.0e-5
epsilon = 1.0e-5
maxit = 64

println("f_1:")
println("Metoda & \$x0\$ & \$x1\$ & \$r\$ & \$|f(r)|\$ & \$it\$ & \$err\$")

x0 = 2.0
x1 = 4.0
wynik = FindRoots.mbisekcji(f1, x0, x1, delta, epsilon)
println("bisekcji & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 0.0
x1 = 8.0
wynik = FindRoots.mbisekcji(f1, x0, x1, delta, epsilon)
println("bisekcji & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 0.0
wynik = FindRoots.mstycznych(f1, df1, x0, delta, epsilon, maxit)
println("stycznych & \$", x0, "\$ & N/A & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 2.0
wynik = FindRoots.mstycznych(f1, df1, x0, delta, epsilon, maxit)
println("stycznych & \$", x0, "\$ & N/A & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 8.0
wynik = FindRoots.mstycznych(f1, df1, x0, delta, epsilon, maxit)
println("stycznych & \$", x0, "\$ & N/A & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 4.0
x1 = 8.0
wynik = FindRoots.msiecznych(f1, x0, x1, delta, epsilon, maxit)
println("siecznych & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 2.0
x1 = 4.0
wynik = FindRoots.msiecznych(f1, x0, x1, delta, epsilon, maxit)
println("siecznych & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")




println("\nf_2:")
println("Metoda & \$x0\$ & \$x1\$ & \$r\$ & \$|f(r)|\$ & \$it\$ & \$err\$")

x0 = 2.0
x1 = 4.0
wynik = FindRoots.mbisekcji(f2, x0, x1, delta, epsilon)
println("bisekcji & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = -2.5
x1 = 13.0
wynik = FindRoots.mbisekcji(f2, x0, x1, delta, epsilon)
println("bisekcji & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = -20.0
wynik = FindRoots.mstycznych(f2, df2, x0, delta, epsilon, maxit)
println("stycznych & \$", x0, "\$ & N/A & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 0.5
wynik = FindRoots.mstycznych(f2, df2, x0, delta, epsilon, maxit)
println("stycznych & \$", x0, "\$ & N/A & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 1.0
wynik = FindRoots.mstycznych(f2, df2, x0, delta, epsilon, maxit)
println("stycznych & \$", x0, "\$ & N/A & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 2.0
wynik = FindRoots.mstycznych(f2, df2, x0, delta, epsilon, maxit)
println("stycznych & \$", x0, "\$ & N/A & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = 4.0
x1 = 8.0
wynik = FindRoots.msiecznych(f2, x0, x1, delta, epsilon, maxit)
println("siecznych & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

x0 = -4.0
x1 = -8.0
wynik = FindRoots.msiecznych(f2, x0, x1, delta, epsilon, maxit)
println("siecznych & \$", x0, "\$ & \$", x1, "\$ & \$", round(wynik[1]; digits=5), "\$ & \$", round(wynik[2]; digits=5), "\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$\\\\")

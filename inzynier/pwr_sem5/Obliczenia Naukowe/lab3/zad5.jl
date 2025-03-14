import .FindRoots

function f(x) # Miejsca zerowe: x0~0.6190612867359, x1~1.5121345516578
    exp(x) - 3 * x
end

println("\$x0\$ & \$x1\$ & \$r\$ & \$|f(r)|\$ & \$it\$ & \$err\$")

delta = 1.0e-4
epsilon = 1.0e-4
x0 = 0.0
x1 = 1.0
wynik = FindRoots.mbisekcji(f, x0, x1, delta, epsilon)
println("\$", x0, "\$ & \$", x1, "\$ & \$", wynik[1], "\$ & \$", wynik[2],"\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$")

x0 = 0.0
x1 = 2.0
wynik = FindRoots.mbisekcji(f, x0, x1, delta, epsilon)
println("\$", x0, "\$ & \$", x1, "\$ & \$", wynik[1], "\$ & \$", wynik[2],"\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$")

x0 = 1.5
x1 = 10.0
wynik = FindRoots.mbisekcji(f, x0, x1, delta, epsilon)
println("\$", x0, "\$ & \$", x1, "\$ & \$", wynik[1], "\$ & \$", wynik[2],"\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$")

x0 = 1.0
x1 = 100.0
wynik = FindRoots.mbisekcji(f, x0, x1, delta, epsilon)
println("\$", x0, "\$ & \$", x1, "\$ & \$", wynik[1], "\$ & \$", wynik[2],"\$ & \$", wynik[3], "\$ & \$", wynik[4], "\$")

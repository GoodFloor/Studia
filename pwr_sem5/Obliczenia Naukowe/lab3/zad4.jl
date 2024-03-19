import .FindRoots

function f(x)
    sin(x) - (x / 2) ^ 2
end
function df(x)
    cos(x) - x / 2
end

println("Metoda bisekcji:  \t", FindRoots.mbisekcji(f, 1.5, 2.0, 0.5e-5, 0.5e-5))
println("Metoda Newtona:   \t", FindRoots.mstycznych(f, df, 1.5, 0.5e-5, 0.5e-5, 64))
println("Metoda siecznych: \t", FindRoots.msiecznych(f, 1.0, 2.0, 0.5e-5, 0.5e-5, 64))
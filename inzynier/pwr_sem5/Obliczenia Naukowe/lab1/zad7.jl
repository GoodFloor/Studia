# Łukasz Machnik

# Funkcja zwracająca wartość f(x)
function f(x)
    return Float64(sin(x)) + Float64(cos(Float64(3) * x))
end

# Funkcja zwracająca przybliżoną wartość pochodnej funkcji f w punkcie x z parametrem h
function daf(x, h)
    return (f(x + h) - f(x)) / Float64(h)
end

# Funkcja zwracająca wartość f'(x)
function df(x)
    return Float64(cos(x)) - Float64(Float64(3) * sin(Float64(3) * x))
end

x0 = Float64(1) # Punkt w którym sprawdzana jest wartość pochodnej
ydf = df(x0) # Wartość dokładnej pochodnej w punkcie x0
println("df(1) = $(ydf)")
println("n; |daf(1)-df(1)|")
h = Float64(1) # Początkowa wartość parametru h
for n in 0:54
    ydaf = daf(x0, h) # Wartość przybliżonek pochodnej w punkcie x0
    if ydaf >= ydf # Wyświetlanie błędu bezwzględnego
        println("($(n), $(ydaf - ydf))")
    else
        println("($(n), $(ydf - ydaf))")
    end
    global h /= Float64(2)
end

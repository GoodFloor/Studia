import numpy as np

# Stopień macierzy Hilberta
n = 20

# Tworzenie macierzy Hilberta H_n
H_n = np.array([[1 / (i + j + 1) for j in range(n)] for i in range(n)])

# Obliczenie wskaźnika uwarunkowania
cond_H_n = np.linalg.cond(H_n)

print(f"Wskaźnik uwarunkowania macierzy Hilberta H_{n} = {cond_H_n}")

import matplotlib.pyplot as plt
import numpy as np
import math

X = np.arange(0, 50, 0.1).tolist()
Y = [pow(math.e, x)*math.log(1+pow(math.e, -x)) for x in X]
plt.plot(X, Y)
plt.savefig("./zad2.png")
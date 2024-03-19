import matplotlib.pyplot as plt

for i in range(10):
    fileName = "./wyniki/dfs{}.txt".format(i)
    outputName = "./wyniki/dfs{}.jpg".format(i)
    file = open(fileName, "r")
    size = int(file.readline())
    xStart = 0
    yStart = 0
    for j in range(size):
        cords = file.readline().split()
        x1 = int(cords[0])
        y1 = int(cords[1])
        x2 = int(cords[2])
        y2 = int(cords[3])
        if j == 0:
            xStart = x1
            yStart = y1
        plt.plot([x1, x2], [y1, y2], marker='.', color='blue', linewidth=0.3, markersize = 4)
    # plt.show()
    plt.title("|V| = {}".format(size))
    plt.savefig(outputName)
    plt.clf()
    file.close()

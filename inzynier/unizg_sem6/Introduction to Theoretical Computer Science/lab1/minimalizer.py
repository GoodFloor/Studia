inputFile = open("input0.txt", "r")

states = inputFile.readline()
symbols = inputFile.readline()
acceptingStates = inputFile.readline()
initialState = inputFile.readline()

states = states[:-1].split(",")
symbols = symbols[:-1].split(",")
acceptingStates = acceptingStates[:-1].split(",")
initialState = initialState[:-1]

transitionFunction = [[inputFile.readline()[:-1] for _ in range(len(symbols))] for _ in range(len(states))]

# Unreachable states
isReachable = [False for _ in range(len(states))]
queue = []
queue.append(initialState)
isReachable[states.index(initialState)] = True
while len(queue) > 0:
    curr = queue.pop(0)
    currId = states.index(curr)
    for i in range(len(symbols)):
        if not isReachable[states.index(transitionFunction[currId][i])]:
            queue.append(transitionFunction[currId][i])
            isReachable[states.index(transitionFunction[currId][i])] = True

newStates = []
newAcceptingStates = []
newTransitionFunction = []
for i in range(len(states)):
    if isReachable[i]:
        newStates.append(states[i])
        newTransitionFunction.append(transitionFunction[i])
for i in range(len(acceptingStates)):
    if isReachable[states.index(acceptingStates[i])]:
        newAcceptingStates.append(acceptingStates[i])
states = newStates
acceptingStates = newAcceptingStates
transitionFunction = newTransitionFunction

# Indistinguishable states
isAccepting = [False for _ in range(len(states))]
for x in acceptingStates:
    isAccepting[states.index(x)] = True

isMarked = [[False for _ in range(len(states))] for _ in range(len(states))]
for i in range(len(states) - 1):
    for j in range(i+1, len(states)):
        if isAccepting[i] != isAccepting[j]:
            isMarked[i][j] = True
            isMarked[j][i] = True

somethingChanged = True
while somethingChanged:
    somethingChanged = False
    for i in range(len(states) - 1):
        for j in range(i+1, len(states)):
            if not isMarked[i][j]:
                for k in range(len(symbols)):
                    d1 = transitionFunction[i][k]
                    d2 = transitionFunction[j][k]
                    d1 = states.index(d1)
                    d2 = states.index(d2)
                    if d1 != d2 and isMarked[d1][d2]:
                        isMarked[i][j] = True
                        isMarked[j][i] = True
                        somethingChanged = True

isUnique = [True for _ in range(len(states))]
newAlias = [x for x in states]
for i in range(len(states) - 1):
    for j in range(i+1, len(states)):
        if not isMarked[i][j]:
            isUnique[j] = False
            newAlias[j] = states[i]

newStates = []
newAcceptingStates = []
newTransitionFunction = []
for i in range(len(states)):
    if isUnique[i]:
        newStates.append(states[i])
        newTransitionFunction.append(transitionFunction[i])
for i in range(len(acceptingStates)):
    if isUnique[states.index(acceptingStates[i])]:
        newAcceptingStates.append(acceptingStates[i])

for i in range(len(newStates)):
    for j in range(len(symbols)):
        newTransitionFunction[i][j] = newAlias[states.index(newTransitionFunction[i][j])]
initialState = newAlias[states.index(initialState)]
states = newStates
acceptingStates = newAcceptingStates
transitionFunction = newTransitionFunction

first = True
for x in states:
    if not first:
        print(",", end="")
    print(x, end="")
    first = False
print()

first = True
for x in symbols:
    if not first:
        print(",", end="")
    print(x, end="")
    first = False
print()

first = True
for x in acceptingStates:
    if not first:
        print(",", end="")
    print(x, end="")
    first = False
print()

print(initialState)

for row in transitionFunction:
    for x in row:
        print(x)



    
line = input("States: ")
states = line.split(",")

line = input("Input symbols: ")
inputSymbols = line.split(",")

line = input("Stack symbols: ")
stackSymbols = line.split(",")

currentState = input("Initial state: ")

stack = []
line = input("Initial stack symbol: ")
stack.append(line)

line = input("Accepting states: ")
acceptingStates = line.split(",")

print("Transition function: ")
transitionFunction = dict()
try:
    while True:
        data = input()
        if not data:
            break
        data = data.split("->")
        transitionFunction[data[0]] = data[1]
except EOFError:
    pass

inputText = input("Test string: ")
currentPosition = 0
print()

while len(stack) > 0 and not ((currentPosition >= len(inputText)) and (currentState in acceptingStates)):
    print(currentState, end="#")
    for c in reversed(stack):
        print(c, end="")
    print("$")

    currentChar = "$"
    if currentPosition < len(inputText):
        currentChar = inputText[currentPosition]
    currentStack = stack.pop()
    arg = currentState + "," + currentChar + "," + currentStack
    transition = ""
    try:
        transition = transitionFunction[arg]
        currentPosition += 1
    except KeyError:
        try:
            arg = currentState + ",$," + currentStack
            transition = transitionFunction[arg]
            currentChar = "$"
        except KeyError:
            pass
    # print("Used transition: " + arg + "->" + transition)
    # print("Input char: " + currentChar)
    # print("Current state: " + currentState)
    if not transition: 
        break
    transition = transition.split(",")
    currentState = transition[0]
    newStack = transition[1]
    for i in reversed(range(len(newStack))):
        if(newStack[i] != "$"):
            stack.append(newStack[i])
    # print("Current stack: ", end="")
    # print(stack)

print(currentState, end="#")
for c in reversed(stack):
    print(c, end="")
print("$")

if (currentPosition >= len(inputText)) and (currentState in acceptingStates):
    print("String is accepted")
else:
    print("String is not accepted")


# print(inputText[currentPosition])

# print()
# print(states)
# print(inputSymbols)
# print(stackSymbols)
# print(currentState)
# print(stack)
# print(acceptingStates)

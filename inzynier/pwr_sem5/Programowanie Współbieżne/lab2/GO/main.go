package main

import (
	"fmt"
	"math/rand"
	"time"
)

type printerS struct {
	getVal   chan int
	getEast  chan bool
	getSouth chan bool
}

type nodeS struct {
	drawRequest    chan bool
	incomingAgent  chan [2]int // Id agenta, kierunek w którym idzie
	incomingRogue  chan [2]int
	incomingDanger chan int
	leavingAgent   chan int
	leavingRogue   chan int
	leavingDanger  chan bool
	rogueResponse  chan int
}

type agentS struct {
	freeze   chan bool
	response chan bool
	remove   chan bool
	init     chan [2]int
}

type rogueS struct {
	moveOut  chan bool
	response chan bool
}

type dangerS struct {
	response chan bool
	remove   chan bool
}

const m = 5
const n = 4
const k = 4
const d = 16
const z = 16
const probabilityOfBirth = 0.5
const probabilityOfTravel = 0.75
const probabilityOfRogue = 0.5
const probabilityOfDanger = 0.5

const drawCross = "┼"
const drawHorizontalSolid = "─"
const drawHorizontalBlured = " "
const drawVerticalSolid = "│"
const drawVerticalBlured = " "
const drawRogue = " *"
const drawDanger = " #"

var printer printerS
var grid [m][n]nodeS
var agentArray map[int]agentS
var rogueArray map[int]rogueS
var dangerArray map[int]dangerS

func printerT() {
	printer = printerS{getVal: make(chan int), getEast: make(chan bool), getSouth: make(chan bool)}
	var isHorizontalBlured [n]bool
	for i := 0; i < n; i++ {
		isHorizontalBlured[i] = false
	}
	for {

		fmt.Println("\nObraz systemu:")

		// Zatrzymuje wszystkich podróżników
		for _, v := range agentArray {
			v.freeze <- true
		}
		// Drukowanie
		for i := 0; i <= 3*n; i++ {
			switch i % 3 {
			case 0:
				fmt.Print(drawCross)
			default:
				fmt.Print(drawHorizontalSolid)
			}
		}
		fmt.Println()
		for _, row := range grid {
			fmt.Print(drawVerticalSolid)

			for i, n := range row {
				n.drawRequest <- true
				val := <-printer.getVal
				isEastBlured := <-printer.getEast
				isSouthBlured := <-printer.getSouth

				// Zawartość węzła
				if val == -3 {
					fmt.Print(drawDanger)
				} else if val == -2 {
					fmt.Print(drawRogue)
				} else if val == -1 {
					fmt.Print("  ")
				} else if val < 10 {
					fmt.Print("0", val)
				} else {
					fmt.Print(val)
				}

				// Krawędź po prawej
				if isEastBlured {
					fmt.Print(drawVerticalBlured)
				} else {
					fmt.Print(drawVerticalSolid)
				}

				// Krawędź na dole
				if isSouthBlured {
					isHorizontalBlured[i] = true
				} else {
					isHorizontalBlured[i] = false
				}
			}
			fmt.Println()

			for i := 0; i <= 3*n; i++ {
				switch i % 3 {
				case 0:
					fmt.Print(drawCross)
				default:
					if isHorizontalBlured[i/3] {
						fmt.Print(drawHorizontalBlured)
					} else {
						fmt.Print(drawHorizontalSolid)
					}
				}
			}
			fmt.Println()
		}

		// Wznawia wszystkich podróżników
		for _, v := range agentArray {
			v.freeze <- false
		}
		time.Sleep(time.Second)
	}
}

func nodeT(xPos int, yPos int) {
	grid[xPos][yPos] = nodeS{
		drawRequest:    make(chan bool),
		incomingAgent:  make(chan [2]int),
		incomingRogue:  make(chan [2]int),
		incomingDanger: make(chan int),
		leavingAgent:   make(chan int),
		leavingRogue:   make(chan int),
		leavingDanger:  make(chan bool),
		rogueResponse:  make(chan int)}
	myChannels := grid[xPos][yPos]
	content := -1
	dangerId := 0
	rogueId := 0
	crossedEast := false
	crossedSouth := false
	for {
		select {
		case <-myChannels.drawRequest:
			printer.getVal <- content
			printer.getEast <- crossedEast
			printer.getSouth <- crossedSouth
			crossedEast = false
			crossedSouth = false
		case incoming := <-myChannels.incomingAgent:
			incomingId := incoming[0]
			incomingDirection := incoming[1]
			if content == -3 {
				agentArray[incomingId].response <- false
				agentArray[incomingId].remove <- true
				dangerArray[dangerId].remove <- true
				content = -1
				if incomingDirection == 3 {
					crossedEast = true
				} else if incomingDirection == 0 {
					crossedSouth = true
				}
			} else if content == -2 {
				rogueArray[rogueId].moveOut <- true
				rogueDirection := <-myChannels.rogueResponse
				if rogueDirection == -1 {
					agentArray[incomingId].response <- false
				} else {
					agentArray[incomingId].response <- true
					content = incomingId
					if incomingDirection == 3 {
						crossedEast = true
					} else if incomingDirection == 0 {
						crossedSouth = true
					}
				}
			} else if content == -1 {
				agentArray[incomingId].response <- true
				content = incomingId
				if incomingDirection == 3 {
					crossedEast = true
				} else if incomingDirection == 0 {
					crossedSouth = true
				}
			} else {
				agentArray[incomingId].response <- false
			}
		case incoming := <-myChannels.incomingRogue:
			incomingId := incoming[0]
			incomingDirection := incoming[1]
			if content == -1 {
				rogueArray[incomingId].response <- true
				content = -2
				rogueId = incomingId
				if incomingDirection == 3 {
					crossedEast = true
				} else if incomingDirection == 0 {
					crossedSouth = true
				}
			} else {
				rogueArray[incomingId].response <- false
			}
		case incoming := <-myChannels.incomingDanger:
			if content == -1 {
				dangerArray[incoming].response <- true
				content = -3
				dangerId = incoming
			} else {
				dangerArray[incoming].response <- false
			}
		case direction := <-myChannels.leavingAgent:
			content = -1
			if direction == 1 {
				crossedEast = true
			} else if direction == 2 {
				crossedSouth = true
			}
		case direction := <-myChannels.leavingRogue:
			content = -1
			if direction == 1 {
				crossedEast = true
			} else if direction == 2 {
				crossedSouth = true
			}
		case <-myChannels.leavingDanger:
			if content == -3 {
				content = -1
			}
		}
	}
}

func agentT(id int) {
	myId := id
	myChannels := agentArray[myId]
	isAlive := false
	isFrozen := false
	myX := -1
	myY := -1
	for !isAlive {
		select {
		case f := <-myChannels.freeze:
			isFrozen = f
		case initData := <-myChannels.init:
			myX = initData[0]
			myY = initData[1]
			isAlive = true
		}
	}
	for isAlive {
		select {
		case f := <-myChannels.freeze:
			isFrozen = f
		case <-myChannels.remove:
			delete(agentArray, myId)
			grid[myX][myY].leavingAgent <- -1
			isAlive = false
		case <-time.After(time.Second):
			if !isFrozen && rand.Float64() <= probabilityOfTravel {
				newX := myX
				newY := myY
				goingDirection := -1
				r := rand.Intn(4)
				switch r {
				case 0:
					if myX > 0 {
						newX -= 1
						goingDirection = 0
					} else {
						newX += 1
						goingDirection = 2
					}
				case 1:
					if myY > 0 {
						newY -= 1
						goingDirection = 3
					} else {
						newY += 1
						goingDirection = 1
					}
				case 2:
					if myX < m-1 {
						newX += 1
						goingDirection = 2
					} else {
						newX -= 1
						goingDirection = 0
					}
				case 3:
					if myY < n-1 {
						newY += 1
						goingDirection = 1
					} else {
						newY -= 1
						goingDirection = 3
					}
				}
				grid[newX][newY].incomingAgent <- [2]int{myId, goingDirection}
				if <-myChannels.response {
					grid[myX][myY].leavingAgent <- goingDirection
					myX = newX
					myY = newY
				}
			}
		}
	}
}

func rogueT(id int, xPos int, yPos int) {
	myId := id
	myX := xPos
	myY := yPos
	myChannels := rogueArray[myId]

	ttl := d
	for ttl > 0 {
		select {
		case <-myChannels.moveOut:
			moveDirection := -1
			newX := myX
			newY := myY
			if moveDirection < 0 && myX > 0 {
				newX = myX - 1
				newY = myY
				select {
				case grid[newX][newY].incomingRogue <- [2]int{myId, 0}:
					if <-myChannels.response {
						moveDirection = 0
					}
				case <-time.After(time.Millisecond * 250):

				}
			}
			if moveDirection < 0 && myX < m-1 {
				newX = myX + 1
				newY = myY
				select {
				case grid[newX][newY].incomingRogue <- [2]int{myId, 2}:
					if <-myChannels.response {
						moveDirection = 2
					}
				case <-time.After(time.Millisecond * 250):

				}
			}
			if moveDirection < 0 && myY > 0 {
				newX = myX
				newY = myY - 1
				select {
				case grid[newX][newY].incomingRogue <- [2]int{myId, 3}:
					if <-myChannels.response {
						moveDirection = 3
					}
				case <-time.After(time.Millisecond * 250):

				}
			}
			if moveDirection < 0 && myY < n-1 {
				newX = myX
				newY = myY + 1
				select {
				case grid[newX][newY].incomingRogue <- [2]int{myId, 1}:
					if <-myChannels.response {
						moveDirection = 1
					}
				case <-time.After(time.Millisecond * 250):

				}
			}
			grid[myX][myY].rogueResponse <- moveDirection
			if moveDirection >= 0 {
				myX = newX
				myY = newY
			}
		case <-time.After(time.Second):
			ttl--
		}
	}
	grid[myX][myY].leavingRogue <- -1
	// delete(rogueArray, myId)
}

func dangerT(id int, xPos int, yPos int) {
	// Inicjalizacja zmiennych
	myId := id
	myX := xPos
	myY := yPos
	myChannels := dangerArray[myId]

	// Rozpoczęcie procesu
	ttl := z
	for ttl > 0 {
		select {
		case <-myChannels.remove:
			ttl = 0
		case <-time.After(time.Second):
			ttl--
		}
	}
	grid[myX][myY].leavingDanger <- true
	// delete(dangerArray, myId)
}

func agentMaker() {
	currentNumberOfTravellers := 0
	agentArray[currentNumberOfTravellers] = agentS{freeze: make(chan bool), response: make(chan bool), remove: make(chan bool), init: make(chan [2]int)}
	go agentT(currentNumberOfTravellers)
	for currentNumberOfTravellers < k {
		if rand.Float64() <= probabilityOfBirth {
			x := rand.Intn(m)
			y := rand.Intn(n)
			grid[x][y].incomingAgent <- [2]int{currentNumberOfTravellers, -1}
			if <-agentArray[currentNumberOfTravellers].response {
				agentArray[currentNumberOfTravellers].init <- [2]int{x, y}
				currentNumberOfTravellers++
				if currentNumberOfTravellers < k {
					agentArray[currentNumberOfTravellers] = agentS{freeze: make(chan bool), response: make(chan bool), remove: make(chan bool), init: make(chan [2]int)}
					go agentT(currentNumberOfTravellers)
				}
			}
		}
		time.Sleep(time.Second)
	}
}

func rogueMaker() {
	currentId := 0
	rogueArray[currentId] = rogueS{moveOut: make(chan bool), response: make(chan bool)}
	for {
		time.Sleep(time.Second)
		if rand.Float64() <= probabilityOfRogue {
			x := rand.Intn(m)
			y := rand.Intn(n)
			grid[x][y].incomingRogue <- [2]int{currentId, -1}
			if <-rogueArray[currentId].response {
				go rogueT(currentId, x, y)
				currentId++
				rogueArray[currentId] = rogueS{moveOut: make(chan bool), response: make(chan bool)}
			}
		}
	}
}

func dangerMaker() {
	currentId := 0
	dangerArray[currentId] = dangerS{remove: make(chan bool), response: make(chan bool)}

	for {
		time.Sleep(time.Second)
		if rand.Float64() <= probabilityOfDanger {
			x := rand.Intn(m)
			y := rand.Intn(n)
			grid[x][y].incomingDanger <- currentId
			if <-dangerArray[currentId].response {
				go dangerT(currentId, x, y)
				currentId++
				dangerArray[currentId] = dangerS{remove: make(chan bool), response: make(chan bool)}
			}
		}
	}
}

func main() {
	// Inicjalizacja
	agentArray = make(map[int]agentS)
	rogueArray = make(map[int]rogueS)
	dangerArray = make(map[int]dangerS)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			go nodeT(i, j)
		}
	}
	go printerT()
	go agentMaker()
	go rogueMaker()
	go dangerMaker()
	// go agentT(0, 1, 1)
	/*
		imageResponseChan = make(chan [3]int)
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				imageRequestChans[i][j] = make(chan bool)
				gridNodeInputChans[i][j] = make(chan [2]int)
				go nodeThread(i, j)
			}
		}
		// Wystartowanie obserwatora
		go printerThread()

		// Startowanie nowych procesów dopóki nie będzie ich odpowiednio dużo
		currentNumberOfTravellers := 0
		travellerResponseChans = append(travellerResponseChans, make(chan bool))
		for currentNumberOfTravellers < k {
			if rand.Float64() <= probabilityOfBirth {
				x := rand.Intn(m)
				y := rand.Intn(n)
				msg := [2]int{currentNumberOfTravellers, -1}
				gridNodeInputChans[x][y] <- msg
				if <-travellerResponseChans[currentNumberOfTravellers] {
					go travellerThread(currentNumberOfTravellers, x, y)
					currentNumberOfTravellers += 1
					travellerFreezeChans = append(travellerFreezeChans, make(chan bool))
					if currentNumberOfTravellers < k {
						travellerResponseChans = append(travellerResponseChans, make(chan bool))
					}
				}
			}
			time.Sleep(time.Second)
		}*/
	time.Sleep(time.Minute)
}

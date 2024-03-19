package main

import (
	"fmt"
	"math/rand"
	"time"
)

const m = 5
const n = 4
const k = 4
const probabilityOfBirth = 0.2
const probabilityOfTravel = 0.75
const drawCross = "┼"
const drawHorizontalSolid = "─"
const drawHorizontalBlured = " "
const drawVerticalSolid = "│"
const drawVerticalBlured = " "

var travellerResponseChans []chan bool
var travellerFreezeChans []chan bool
var imageResponseChan chan [3]int
var imageRequestChans [m][n]chan bool
var gridNodeInputChans [m][n]chan [2]int

func gridThread(x int, y int) {
	var currentTraveller = -1
	crossedEast := false
	crossedSouth := false
	for {
		select {
		case r := <-gridNodeInputChans[x][y]:
			if r[0] == currentTraveller {
				currentTraveller = -1
				if r[1] == 1 {
					crossedEast = true
				}
				if r[1] == 2 {
					crossedSouth = true
				}
			} else if currentTraveller == -1 {
				travellerResponseChans[r[0]] <- true
				currentTraveller = r[0]
				if r[1] == 3 {
					crossedEast = true
				}
				if r[1] == 0 {
					crossedSouth = true
				}
			} else {
				travellerResponseChans[r[0]] <- false
			}
		case <-imageRequestChans[x][y]:
			var img [3]int
			if crossedEast {
				img[0] = 1
				crossedEast = false
			}
			if crossedSouth {
				img[1] = 1
				crossedSouth = false
			}
			img[2] = currentTraveller
			imageResponseChan <- img
		}
	}
}

func travellerThread(id int, x int, y int) {
	isFrozen := false
	for {
		select {
		case f := <-travellerFreezeChans[id]:
			isFrozen = f
			travellerFreezeChans[id] <- true
		case <-time.After(time.Second):
			if !isFrozen && rand.Float64() <= probabilityOfTravel {
				newX := x
				newY := y
				goingDirection := -1
				r := rand.Intn(4)
				switch r {
				case 0:
					if x > 0 {
						newX -= 1
						goingDirection = 0
					} else {
						newX += 1
						goingDirection = 2
					}
				case 1:
					if y > 0 {
						newY -= 1
						goingDirection = 3
					} else {
						newY += 1
						goingDirection = 1
					}
				case 2:
					if x < m-1 {
						newX += 1
						goingDirection = 2
					} else {
						newX -= 1
						goingDirection = 0
					}
				case 3:
					if y < n-1 {
						newY += 1
						goingDirection = 1
					} else {
						newY -= 1
						goingDirection = 3
					}
				}
				msgD := [2]int{id, goingDirection}
				gridNodeInputChans[newX][newY] <- msgD
				if <-travellerResponseChans[id] {
					msgS := [2]int{id, goingDirection}
					gridNodeInputChans[x][y] <- msgS
					x = newX
					y = newY
				}
			}
		}
	}
}

func printerThread() {
	for {
		fmt.Println("\nObraz systemu:")
		// Zatrzymuje wszystkich podróżników
		for _, t := range travellerFreezeChans {
			t <- true
		}
		// Czeka aż wszyscy potwierdzą zatrzymanie
		for _, t := range travellerFreezeChans {
			<-t
		}
		// var edgeStatus [m][n][2]bool
		// Wysyła prośbę o przesłanie statusu węzła i wypisuje go
		for i := 0; i <= 3*n; i++ {
			switch i % 3 {
			case 0:
				fmt.Print(drawCross)
			default:
				fmt.Print(drawHorizontalSolid)
			}
		}
		fmt.Println()
		for _, row := range imageRequestChans {
			fmt.Print(drawVerticalSolid)
			var isHorizontalBlured [n]bool
			for i := 0; i < n; i++ {
				isHorizontalBlured[i] = false
			}
			for i, ch := range row {
				ch <- true
				c := <-imageResponseChan
				nextBorderBlured := false
				if c[0] == 1 {
					nextBorderBlured = true
				}
				if c[1] == 1 {
					isHorizontalBlured[i] = true
				}
				if -1 < c[2] {
					if c[2] < 10 {
						fmt.Print("0")
					}
					fmt.Print(c[2])
				} else {
					fmt.Print("  ")
				}
				if nextBorderBlured {
					fmt.Print(drawVerticalBlured)
				} else {
					fmt.Print(drawVerticalSolid)
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
		for _, t := range travellerFreezeChans {
			t <- false
		}
		// Czeka aż wszyscy potwierdzą wznowienie
		for _, t := range travellerFreezeChans {
			<-t
		}
		time.Sleep(time.Second)
	}
}

func main() {
	// Inicjalizacja kanałów
	imageResponseChan = make(chan [3]int)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			imageRequestChans[i][j] = make(chan bool)
			gridNodeInputChans[i][j] = make(chan [2]int)
			go gridThread(i, j)
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
	}
	time.Sleep(time.Minute)
}

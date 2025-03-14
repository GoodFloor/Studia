apps = game_bot
all: ${apps}

# Main app
game_bot: game_bot.o game_model.o
	g++ -o game_bot game_bot.o game_model.o
game_bot.o: game_bot.cpp
	g++ -c game_bot.cpp

# Dependencies
game_model.o: game_model.cpp
	g++ -c game_model.cpp

# Cleaning
exe: ${apps}
	rm -f *.o *.~
clean: 
	rm -f *.o *.~ ${apps}

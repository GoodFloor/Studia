#include <iostream>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include "game_model.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    int socketDesc;
    sockaddr_in serverAddr;
    char serverMessage[16], clientMessage[16];
    bool gameOn;
    int player, msg, move;

    if (argc != 5)
    {
        cout << "Poprawna składnia: ./game_bot <ip serwera> <numer portu> <gracz> <głębokość przeszukiwania>\n\t\e[1mgracz\e[0m =\t1 - dla X\n\t\t2 - dla O" << endl;
        return 44;
    }
    int searchDepth = atoi(argv[4]);
    if (searchDepth < 1 || searchDepth > 10)
    {
        cout << "Dopuszczalna głębokość przeszukiwania to od 1 do 10" << endl;
        return 45;
    }
    

    // Stworzenie socketu
    socketDesc = socket(AF_INET, SOCK_STREAM, 0);
    if (socketDesc < 0)
    {
        cout << "Nieudana próba utworzenia socketu" << endl;
        return 51;
    }
    cout << "Pomyślnie utworzono socket" << endl;
    
    // Wybranie podanego adresu IP
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(atoi(argv[2]));
    serverAddr.sin_addr.s_addr = inet_addr(argv[1]);

    // Próba połączenia z serwerem
    if (connect(socketDesc, (sockaddr*)&serverAddr, sizeof(serverAddr)) < 0)
    {
        cout << "Próba połączenia z serwerem nieudana" << endl;
        return 52;
    }
    cout << "Pomyślnie połączono z serwerem" << endl;

    // Oczekiwanie na komunikat potwierdzający 
    memset(serverMessage, '\0', sizeof(serverMessage));
    if (recv(socketDesc, serverMessage, sizeof(serverMessage), 0) < 0)
    {
        cout << "Błąd odczytu wiadomości z serwera" << endl;
        return 53;
    }
    cout << "Wiadomość z serwera: " << serverMessage << endl;

    // Odpowiedzenie serwerowi numerem gracza
    memset(clientMessage, '\0', sizeof(clientMessage));
    strcpy(clientMessage, argv[3]);
    if (send(socketDesc, clientMessage, strlen(clientMessage), 0) < 0)
    {
        cout << "Błąd wysyłania wiadomości do serwera" << endl;
        return 54;
    }

    // Rozpoczynanie gry
    ull board = generateBoard();
    gameOn = true;
    player = atoi(argv[3]);
    srand(time(NULL));

    while (gameOn)
    {
        memset(serverMessage, '\0', sizeof(serverMessage));
        if (recv(socketDesc, serverMessage, sizeof(serverMessage), 0) < 0)
        {
            cout << "Błąd odczytu wiadomości z serwera" << endl;
            return 53;
        }
        cout << "Wiadomość od serwera: " << serverMessage << endl;
        msg = atoi(serverMessage);
        move = msg % 100;
        cout << "move: " << move << endl;
        msg /= 100;
        if (move != 0)
        {
            board = setField(board, move, 3 - player);
            printBoard(board);
        }
        if (msg == 0 || msg == 6)
        {
            int myMove = findNextMove(board, searchDepth, player);
            cout << "Mój ruch: " << myMove << endl;
            board = setField(board, myMove, player);
            printBoard(board);
            memset(clientMessage, '\0', sizeof(clientMessage));
            sprintf(clientMessage, "%d", myMove);
            if (send(socketDesc, clientMessage, strlen(clientMessage), 0) < 0)
            {   
                cout << "Błąd wysyłania wiadomości do serwera" << endl;
                return 54;
            }
        }
        else
        {
            gameOn = false;
            switch (msg)
            {
            case 1:
                cout << "Wygrana!" << endl;
                break;
            case 2:
                cout << "Przegrana!" << endl;
                break;
            case 3:
                cout << "Remis!" << endl;
                break;
            case 4:
                cout << "Wygrana! Przeciwnik popełnił błąd" << endl;
                break;
            case 5:
                cout << "Przegrana! Popełniono błąd" << endl;
                break;
            default:
                break;
            }
        }        
    }

    // Zamykanie socketu
    close(socketDesc);

    return 0;
}

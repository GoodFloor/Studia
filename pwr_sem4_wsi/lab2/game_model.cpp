#include <iostream>
#include "game_model.hpp"

const int winSequence[28][4] = {
    {11, 12, 13, 14},
    {12, 13, 14, 15},
    {21, 22, 23, 24},
    {22, 23, 24, 25},
    {31, 32, 33, 34},
    {32, 33, 34, 35},
    {41, 42, 43, 44},
    {42, 43, 44, 45},
    {51, 52, 53, 54},
    {52, 53, 54, 55},
    {11, 21, 31, 41},
    {21, 31, 41, 51},
    {12, 22, 32, 42},
    {22, 32, 42, 52},
    {13, 23, 33, 43},
    {23, 33, 43, 53},
    {14, 24, 34, 44},
    {24, 34, 44, 54},
    {15, 25, 35, 45},
    {25, 35, 45, 55},
    {11, 22, 33, 44},
    {22, 33, 44, 55},
    {15, 24, 33, 42},
    {24, 33, 42, 51},
    {12, 23, 34, 45},
    {21, 32, 43, 54},
    {14, 23, 32, 41},
    {25, 34, 43, 52}
};
const int loseSequence[48][3] = {
    {11, 12, 13}, {12, 13, 14}, {13, 14, 15}, 
    {21, 22, 23}, {22, 23, 24}, {23, 24, 25}, 
    {31, 32, 33}, {32, 33, 34}, {33, 34, 35}, 
    {41, 42, 43}, {42, 43, 44}, {43, 44, 45}, 
    {51, 52, 53}, {52, 53, 54}, {53, 54, 55}, 
    {11, 21, 31}, {21, 31, 41}, {31, 41, 51}, 
    {12, 22, 32}, {22, 32, 42}, {32, 42, 52}, 
    {13, 23, 33}, {23, 33, 43}, {33, 43, 53}, 
    {14, 24, 34}, {24, 34, 44}, {34, 44, 54}, 
    {15, 25, 35}, {25, 35, 45}, {35, 45, 55}, 
    {13, 24, 35}, {12, 23, 34}, {23, 34, 45}, 
    {11, 22, 33}, {22, 33, 44}, {33, 44, 55}, 
    {21, 32, 43}, {32, 43, 54}, {31, 42, 53}, 
    {13, 22, 31}, {14, 23, 32}, {23, 32, 41}, 
    {15, 24, 33}, {24, 33, 42}, {33, 42, 51}, 
    {25, 34, 43}, {34, 43, 52}, {35, 44, 53}
};

ull generateBoard() {
    return 0;
}
int valueAt(ull board, int x, int y) {
    // std::cout << std::hex << board << std::dec << std::endl;
    ull shift = x * 2 + y * 10;
    ull mask = 0x3;
    mask = mask << shift;
    return (board & mask) >> shift;
}
int valueAt(ull board, int pos) {
    return valueAt(board, pos % 10 - 1, pos / 10 - 1);
}
ull setField(ull board, int x, int y, int value) {
    ull mask = 0x3;
    value %= 3;
    ull newValue = value;
    ull shift = x * 2 + y * 10;

    mask = mask << shift;
    newValue = newValue << shift;
    mask = ~mask;
    board = board & mask;
    board += newValue;
    return board;
}
ull setField(ull board, int pos, int value)
{
    std::cout << "pos: " << pos << std::endl; 
    return setField(board, pos % 10 - 1, pos / 10 - 1, value);
}
void printBoard(ull board)
{
    ull shift = 0;
    ull mask = 0x3;
    std::cout << "  1 2 3 4 5" << std::endl;
    for (int i = 0; i < 5; i++)
    {
        std::cout << i + 1;
        for (int j = 0; j < 5; j++)
        {
            ull content = (board & mask) >> shift;
            switch (content)
            {
            case 0:
                std::cout << " -";
                break;
            case 1:
                std::cout << " X";
                break;
            case 2:
                std::cout << " O";
                break;
            default:
                std::cout << " e";
                break;
            }
            shift += 2;
            mask = mask << 2;
        }
        std::cout << std::endl;
    }
}
int boardHeuristic(ull board, int player)
{
    int heuristic = 0;
    for (int i = 0; i < 28; i++)
    {
        int alliesInSequence = 0;
        int opponentsInSequence = 0;
        for (int j = 0; j < 4; j++)
        {
            if (valueAt(board, winSequence[i][j]) == player)
                alliesInSequence++;
            else if (valueAt(board, winSequence[i][j]) == 3 - player)
                opponentsInSequence++;
        }
        if (alliesInSequence == 4)
            return 10000;
        if (opponentsInSequence == 4)
            return -10000;
        if (opponentsInSequence == 0)
        {
            if (alliesInSequence == 3)
            {
                heuristic += 100;
            }
            else if (alliesInSequence == 2)
            {
                heuristic += 10;
            }
            else if (alliesInSequence == 1)
            {
                heuristic += 1;
            }
        }
        if (alliesInSequence == 0)
        {
            if (opponentsInSequence == 3)
            {
                heuristic -= 100;
            }
            else if (opponentsInSequence == 2)
            {
                heuristic -= 10;
            }
            else if (opponentsInSequence == 1)
            {
                heuristic -= 1;
            }
        }
    }
    for (int i = 0; i < 48; i++)
    {
        int alliesInSequence = 0;
        int opponentsInSequence = 0;
        for (int j = 0; j < 3; j++)
        {
            if (valueAt(board, loseSequence[i][j]) == player)
                alliesInSequence++;
            else if (valueAt(board, loseSequence[i][j]) == 3 - player)
                opponentsInSequence++;
        }
        if (alliesInSequence == 3)
            return -10000;
        if (opponentsInSequence == 3)
            return 10000;
    }
        
        
        
        
    //     if (valueAt(board, winSequence[i][0]) == player && valueAt(board, winSequence[i][1]) == player && valueAt(board, winSequence[i][2]) == player && valueAt(board, winSequence[i][3]) == player)
    //     {
    //         heuristic += 1000;
    //     }
    //     else if (valueAt(board, winSequence[i][0]) == player && valueAt(board, winSequence[i][1]) == 0 && valueAt(board, winSequence[i][2]) == player && valueAt(board, winSequence[i][3]) == player || valueAt(board, winSequence[i][0]) == player && valueAt(board, winSequence[i][1]) == player && valueAt(board, winSequence[i][2]) == 0 && valueAt(board, winSequence[i][3]) == player)
    //     {
    //         heuristic += 100;
    //     }
    //     else if (valueAt(board, winSequence[i][0]) != 3 - player && valueAt(board, winSequence[i][1]) != 3 - player && valueAt(board, winSequence[i][2]) != 3 - player && valueAt(board, winSequence[i][3]) != 3 - player && valueAt(board, winSequence[i][0]) + valueAt(board, winSequence[i][1]) + valueAt(board, winSequence[i][2]) + valueAt(board, winSequence[i][3]) == 2 * player)
    //     {
    //         heuristic += 10;
    //     }
    //     else if (valueAt(board, winSequence[i][0]) != 3 - player && valueAt(board, winSequence[i][1]) != 3 - player && valueAt(board, winSequence[i][2]) != 3 - player && valueAt(board, winSequence[i][3]) != 3 - player && valueAt(board, winSequence[i][0]) + valueAt(board, winSequence[i][1]) + valueAt(board, winSequence[i][2]) + valueAt(board, winSequence[i][3]) == player)
    //     {
    //         heuristic += 1;
    //     } 
    // }
    // for (int i = 0; i < 48; i++)
    // {
    //     if (valueAt(board, loseSequence[i][0]) == player && valueAt(board, loseSequence[i][1]) == player && valueAt(board, loseSequence[i][2]) == player)
    //     {
    //         heuristic -= 100;
    //     }
    // }
    // std::cout << "Heurystyka dla: " << std::endl;
    // printBoard(board);
    // std::cout << "wynosi " << heuristic << std::endl;
    return heuristic;
}
bool winCheck(ull board, int player)
{
    for (int i = 0; i < 28; i++)
    {
        if (valueAt(board, winSequence[i][0]) == player && valueAt(board, winSequence[i][1]) == player && valueAt(board, winSequence[i][2]) == player && valueAt(board, winSequence[i][3]) == player)
        {
            return true;
        }
    }
    return false;
}
bool loseCheck(ull board, int player)
{
    for (int i = 0; i < 48; i++)
    {
        if (valueAt(board, loseSequence[i][0]) == player && valueAt(board, loseSequence[i][1]) == player && valueAt(board, loseSequence[i][2]) == player)
        {
            return true;
        }
    }
    return false;   
}
int minMax(ull board, int searchingDepth, int freeSpaces, int player, bool maximizing, int alpha, int beta)
{
    // Jeśli to liść
    if (searchingDepth == 0 || freeSpaces == 0 || winCheck(board, player) || winCheck(board, 3 - player) || loseCheck(board, player) || loseCheck(board, 3 - player))
    {
        return boardHeuristic(board, player);
    }

    // W p. p.
    int bestHeuristic = maximizing ? INT32_MIN : INT32_MAX;
    for (int x = 0; x < 5; x++)
    {
        for (int y = 0; y < 5; y++)
        {
            if (valueAt(board, x, y) == 0)
            {
                int tempPlayer = maximizing ? player : 3 - player;
                int tempH = minMax(setField(board, x, y, tempPlayer), searchingDepth - 1, freeSpaces - 1, player, !maximizing, alpha, beta);
                if ((maximizing && tempH > bestHeuristic) || (!maximizing && tempH < bestHeuristic))
                    bestHeuristic = tempH;
                if (maximizing && alpha < bestHeuristic)
                    alpha = bestHeuristic;
                if (!maximizing && beta > bestHeuristic)
                    beta = bestHeuristic;
                if (alpha >= beta)
                    return bestHeuristic;                
            }
        }
    }
    return bestHeuristic;   
}

int findNextMove(ull board, int searchingDepth, int player)
{
    int bestHeuristic = INT32_MIN;
    int bestX = 0;
    int bestY = 0;
    int emptySpaces = 0;
    for (int x = 0; x < 5; x++)
    {
        for (int y = 0; y < 5; y++)
        {
            if (valueAt(board, x, y) == 0)
            {
                emptySpaces++;
            }
            else
            {
                bestX = x;
                bestY = y;
            }
            
        }
    }
    if (emptySpaces == 25)
    {
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                if (valueAt(board, x, y) == 0)
                {
                    int tempH = minMax(setField(board, x, y, player), searchingDepth - 1, emptySpaces - 1, player, true, INT32_MIN, INT32_MAX);
                    // std::cout << "Heurystyka dla " << (y + 1) * 10 + (x + 1) << " = " << tempH << std::endl;
                    if (tempH > bestHeuristic)
                    {
                        bestHeuristic = tempH;
                        bestX = x;
                        bestY = y;
                    }
                }
            }
        }
        return (bestY + 1) * 10 + (bestX + 1);
    }
    if (emptySpaces == 24)
    {
        if (bestX == bestY)
        {
            for (int x = 0; x < 5; x++)
            {
                for (int y = x; y < 5; y++)
                {
                    if (valueAt(board, x, y) == 0)
                    {
                        int tempH = minMax(setField(board, x, y, player), searchingDepth - 1, emptySpaces - 1, player, true, INT32_MIN, INT32_MAX);
                        // std::cout << "Heurystyka dla " << (y + 1) * 10 + (x + 1) << " = " << tempH << std::endl;
                        if (tempH > bestHeuristic)
                        {
                            bestHeuristic = tempH;
                            bestX = x;
                            bestY = y;
                        }
                    }
                }
            }
            return (bestY + 1) * 10 + (bestX + 1);
        }
        if (bestX == 2)
        {
            for (int x = 0; x < 3; x++)
            {
                for (int y = 0; y < 5; y++)
                {
                    if (valueAt(board, x, y) == 0)
                    {
                        int tempH = minMax(setField(board, x, y, player), searchingDepth - 1, emptySpaces - 1, player, true, INT32_MIN, INT32_MAX);
                        // std::cout << "Heurystyka dla " << (y + 1) * 10 + (x + 1) << " = " << tempH << std::endl;
                        if (tempH > bestHeuristic)
                        {
                            bestHeuristic = tempH;
                            bestX = x;
                            bestY = y;
                        }
                    }
                }
            }
            return (bestY + 1) * 10 + (bestX + 1);
        }
        if (bestY == 2)
        {
            for (int x = 0; x < 5; x++)
            {
                for (int y = 0; y < 3; y++)
                {
                    if (valueAt(board, x, y) == 0)
                    {
                        int tempH = minMax(setField(board, x, y, player), searchingDepth - 1, emptySpaces - 1, player, true, INT32_MIN, INT32_MAX);
                        // std::cout << "Heurystyka dla " << (y + 1) * 10 + (x + 1) << " = " << tempH << std::endl;
                        if (tempH > bestHeuristic)
                        {
                            bestHeuristic = tempH;
                            bestX = x;
                            bestY = y;
                        }
                    }
                }
            }
            return (bestY + 1) * 10 + (bestX + 1);
        }        
    }
    
    
    for (int x = 0; x < 5; x++)
    {
        for (int y = 0; y < 5; y++)
        {
            if (valueAt(board, x, y) == 0)
            {
                int tempH = minMax(setField(board, x, y, player), searchingDepth - 1, emptySpaces - 1, player, true, INT32_MIN, INT32_MAX);
                // std::cout << "Heurystyka dla " << (y + 1) * 10 + (x + 1) << " = " << tempH << std::endl;
                if (tempH > bestHeuristic)
                {
                    bestHeuristic = tempH;
                    bestX = x;
                    bestY = y;
                }
            }
        }
    }
    return (bestY + 1) * 10 + (bestX + 1);
    // Porównanie czasu wykonania dla głębokości przeszukiwania = 5
    // Bez alfa-beta cięć
    // real    2m39,262s
    // user    1m8,790s
    // sys     0m0,112s
    // Z alfa-beta cięciami
    // real    0m19,737s
    // user    0m9,211s
    // sys     0m0,025s
    // Ze sprawdzeniem symetrii
    // real    0m15,381s
    // user    0m7,659s
    // sys     0m0,056s
    
    // int x = rand() % 5;
    // int y = rand() % 5 ;
    // while (valueAt(board, x, y) != 0)
    // {
    //     x = rand() % 5;
    //     y = rand() % 5 ;
    // }
    // return (y + 1) * 10 + x + 1;
}

using ull = unsigned long long;
ull generateBoard();
int valueAt(ull board, int x, int y);
int valueAt(ull board, int pos);
ull setField(ull board, int x, int y, int value);
ull setField(ull board, int pos, int value);
void printBoard(ull board);
int findNextMove(ull board, int searchingDepth, int player);
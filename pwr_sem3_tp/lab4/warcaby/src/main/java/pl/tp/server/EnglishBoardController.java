package pl.tp.server;

import pl.tp.PieceColorEnum;

/**
 * Klasa obsługująca działania na planszy
 * 
 * Klasa konkretna we wzorcu Template method
 */
public class EnglishBoardController extends BoardController {

    @Override
    protected void resetBoard() {
        // board[0][0] to lewy dolny róg, pierwsza współrzędna to wiersz, druga to
        // kolumna
        AbstractPiece[][] temp = new EnglishPiece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    temp[i][j] = null;
                } else if (i < 3) {
                    temp[i][j] = new EnglishPiece();
                    temp[i][j].setColor(PieceColorEnum.White);
                } else if (i > 4) {
                    temp[i][j] = new EnglishPiece();
                    temp[i][j].setColor(PieceColorEnum.Red);
                } else {
                    temp[i][j] = null;
                }
                // temp[i][j] = null;
            }
        }
        // temp[0][0] = new EnglishPiece();
        // temp[0][0].setColor(PieceColorEnum.White);
        // temp[0][4] = new EnglishPiece();
        // temp[0][4].setColor(PieceColorEnum.White);
        // temp[1][1] = new EnglishPiece();
        // temp[1][1].setColor(PieceColorEnum.White);
        // temp[1][3] = new EnglishPiece();
        // temp[1][3].setColor(PieceColorEnum.White);
        // temp[6][0] = new EnglishPiece();
        // temp[6][0].setColor(PieceColorEnum.White);
        // temp[3][3] = new EnglishPiece();
        // temp[3][3].setColor(PieceColorEnum.White);

        // temp[7][7] = new EnglishPiece();
        // temp[7][7].setColor(PieceColorEnum.Red);
        // temp[7][5] = new EnglishPiece();
        // temp[7][5].setColor(PieceColorEnum.Red);
        // temp[7][3] = new EnglishPiece();
        // temp[7][3].setColor(PieceColorEnum.Red);
        // temp[6][2] = new EnglishPiece();
        // temp[6][2].setColor(PieceColorEnum.Red);
        // temp[6][4] = new EnglishPiece();
        // temp[6][4].setColor(PieceColorEnum.Red);
        // temp[3][7] = new EnglishPiece();
        // temp[3][7].setColor(PieceColorEnum.Red);
        // temp[4][2] = new EnglishPiece();
        // temp[4][2].setColor(PieceColorEnum.Red);

        board.setHeight(8);
        board.setWidth(8);
        board.setPieces(temp);
        board.setMandatoryUsePieces(new int[0][0]);
        board.setNoWhiteRemaining(12);
        board.setNoRedRemaining(12);
        board.setWhiteTurn(true);
    }

    @Override
    protected boolean canKill(int posX, int posY) {
        AbstractPiece[][] tempBoard = board.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // jeśli na sprawdzanym miejscu stoi pionek
                if (tempBoard[j][i] != null) {
                    continue;
                }
                int[][] killableEnemyPlace;
                try {
                    killableEnemyPlace = tempBoard[posY][posX].canGoTo(posX, posY, i, j);
                } catch (IncorrectPositionException e) {
                    continue;
                } catch (Exception e) {
                    continue;
                }
                for (int k = 0; k < killableEnemyPlace.length; k++) {
                    int enemyX = killableEnemyPlace[k][0];
                    int enemyY = killableEnemyPlace[k][1];
                    if (tempBoard[enemyY][enemyX] != null
                            && tempBoard[enemyY][enemyX].getColor() != tempBoard[posY][posX].getColor()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void calculateMandatory() {
        AbstractPiece[][] tempBoard = board.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tempBoard[i][j] != null
                        && ((board.isWhiteTurn() && tempBoard[i][j].getColor() == PieceColorEnum.White)
                                || (!board.isWhiteTurn() && tempBoard[i][j].getColor() == PieceColorEnum.Red))
                        && this.canKill(j, i)) {
                    this.addMandatory(j, i);
                    System.out.println("Mandatory: " + i + "; " + j);
                }
            }
        }
    }

    @Override
    protected boolean canBecomeQueen() {
        if (board.isTurnOver()) {
            return true;
        }
        return false;
    }
}

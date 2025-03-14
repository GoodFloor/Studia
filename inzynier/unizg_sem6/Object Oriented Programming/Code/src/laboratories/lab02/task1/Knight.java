package laboratories.lab02.task1;

public class Knight extends Figure {

    public Knight(int posX, int posY, FigureColor color) {
        super(posX, posY, color);
    }

    @Override
    public boolean canMoveBy(int x, int y) {
        if ((Math.abs(x) == 2 && Math.abs(y) == 1) || (Math.abs(x) == 1 && Math.abs(y) == 2)) {
            return true;
        }
        return false;
    }

    @Override
    public String getFigureName() {
        return "Knight";
    }
    
}

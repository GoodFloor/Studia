package laboratories.lab02.task1;
public abstract class Figure implements Moveable {
    protected int posX;
    protected int posY;
    private final FigureColor color;

    public Figure(int posX, int posY, FigureColor color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }
    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public abstract boolean canMoveBy(int x, int y);
    public abstract String getFigureName();

    @Override
    public void move(int x, int y) {
        if (canMoveBy(x, y)) {
            posX += x;
            posY += y;
        }
    }
    @Override
    public String toString() {
        return color + "-" + getFigureName() + "(" + posX + ", " + posY + ")";
    }
}

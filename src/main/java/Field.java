import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

class Field {

    private int[][] rocks;
    private Painter painter;
    private GameProcessor gameProcessor;
    private boolean isBlack = true;

    Field(GraphicsContext graphicsContext) {
        painter = new Painter(graphicsContext);
        painter.drawField();
        rocks = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                rocks[i][j] = -1;
            }
        }
        gameProcessor = new GameProcessor(rocks);
    }

    void canvasListener(MouseEvent event) {
        if (Painter.gameOver) {
            Painter.gameOver = false;
            Main.newGame();
        }

        int x = (int) event.getX() / 35;
        int y = (int) event.getY() / 35;

        if (!gameProcessor.checkRoles(x, y, isBlack)) return;

        rocks[x][y] = isBlack ? 1 : 0;

        isBlack = !isBlack;
        painter.drawRock(isBlack, x, y);

        if (gameProcessor.checkWin(x, y)) painter.drawWinWindow(gameProcessor.isWhiteWin());
    }
}
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

class Painter {

    private GraphicsContext graphicsContext;

    static boolean gameOver = false;

    Painter(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    void drawField() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, 525, 525);
        graphicsContext.setLineWidth(1);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setStroke(Color.BLACK);

        for (int i = 0; i < 16; i++) {
            int rX = i * 35 + 35 / 2;
            int rY = i * 35 + 35 / 2;
            drawLine(rX, 0);
            drawLine(0, rY);
        }
    }


    private void drawLine(int x, int y) {
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x == 0 ? 525 : x, y == 0 ? 525 : y);
        graphicsContext.stroke();
    }

    void drawRock(boolean isWhite, int x, int y) {
        Color checkerColor = isWhite ? Color.WHITE : Color.DARKGRAY;
        x *= 35;
        y *= 35;

        graphicsContext.setFill(checkerColor);
        graphicsContext.fillOval(x, y, 35, 35);
    }

    void drawWinWindow(boolean whiteWin) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(50, 100, 400, 200);
        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeRect(50, 100, 400, 200);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.fillText((whiteWin ? "Белые" : "Черные") + " выиграли!\n" +
                "Нажмите в любом месте, \nчтобы начать новую игру", 250, 150);
        gameOver = true;
    }

}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        newGame();
    }

    static void newGame() {
        Painter.gameOver = false;

        GridPane grid = new GridPane();

        Scene scene = new Scene(grid, 525, 525);

        Canvas canvas = new Canvas(525, 525);

        Field field = new Field(canvas.getGraphicsContext2D());

        canvas.setOnMouseClicked(field::canvasListener);
        grid.add(canvas, 0, 0);

        if (stage.isShowing()) stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}

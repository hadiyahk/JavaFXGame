package application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeView extends Pane {
    private static final int TILE_SIZE = 20;
    private final SnakeModel model;

    public SnakeView(SnakeModel model) {
        this.model = model;
        setPrefSize(400, 400);
        draw();
    }

    public void draw() {
        getChildren().clear(); // Clear previous frame

        // Draw the snake
        for (int[] segment : model.getBody()) {
            Rectangle rect = new Rectangle(segment[1] * TILE_SIZE, segment[0] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            rect.setFill(Color.GREEN);
            getChildren().add(rect);
        }

        // Draw the food
        int[] food = model.getFood();
        Rectangle foodRect = new Rectangle(food[1] * TILE_SIZE, food[0] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        foodRect.setFill(Color.RED);
        getChildren().add(foodRect);
    }
}

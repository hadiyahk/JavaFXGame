package application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class SnakeController {
    private final SnakeModel model;

    public SnakeController(SnakeModel model) {
        this.model = model;

        // Set up key event handling
        setupKeyHandling();
    }

    private void setupKeyHandling() {
        // Handle key press to change the snake's direction
        EventHandler<KeyEvent> keyHandler = event -> {
        	
            switch (event.getCode()) {
                case UP:    if (model.getDirection() != 2) model.setDirection(0); break; // Up
                case RIGHT: if (model.getDirection() != 3) model.setDirection(1); break; // Right
                case DOWN:  if (model.getDirection() != 0) model.setDirection(2); break; // Down
                case LEFT:  if (model.getDirection() != 1) model.setDirection(3); break; // Left
				default:
					break;
            }
        };

        // Add event handler to the scene
        
        model.getView().setOnKeyPressed(keyHandler);
    }

	public void handleKeyPress(KeyEvent event) {
		
		switch (event.getCode()) {
        case UP:    if (model.getDirection() != 2) model.setDirection(0); break; // Up
        case RIGHT: if (model.getDirection() != 3) model.setDirection(1); break; // Right
        case DOWN:  if (model.getDirection() != 0) model.setDirection(2); break; // Down
        case LEFT:  if (model.getDirection() != 1) model.setDirection(3); break; // Left
		default:
			break;
    }
	}
}

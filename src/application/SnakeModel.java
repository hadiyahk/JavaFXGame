package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class SnakeModel {
    private final LinkedList<int[]> body;
    private final IntegerProperty score;
    private int highScore;
    private int direction; // 0: UP, 1: RIGHT, 2: DOWN, 3: LEFT
    private int[] food;
    private final int width = 20;
    private final int height = 20;


    public SnakeModel() {
        body = new LinkedList<>();
        body.add(new int[]{10, 10}); // Initial position
        direction = 1; // Start moving right
        score = new SimpleIntegerProperty(0);
        highScore = loadHighScore(); // highscore load 
        spawnFood();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public int getScore() {
        return score.get();
    }

    
    public int getHighScore () {
    	return highScore;
    }
    
    public void move() {
        int[] head = body.getFirst();
        int[] newHead = new int[2];

        // Determine the new head position based on the current direction
        
        switch (direction) {
            case 0: newHead = new int[]{head[0] - 1, head[1]}; break; // UP
            case 1: newHead = new int[]{head[0], head[1] + 1}; break; // RIGHT
            case 2: newHead = new int[]{head[0] + 1, head[1]}; break; // DOWN
            case 3: newHead = new int[]{head[0], head[1] - 1}; break; // LEFT
        }

        // Check if snake collides with wall or itself
   
        final int[] test = newHead;
       
        if (newHead[0] < 0 || newHead[1] < 0 || newHead[0] >= height || newHead[1] >= width ||  body.stream().anyMatch(arr -> Arrays.equals(arr, test))) {
            // Game Over
            body.clear();
            body.add(new int[]{10, 10});
            score.set(0);
            direction = 1;
            if (score.get() > highScore) {
                highScore = score.get(); // Update high score
                saveHighScore(); // Save high score to file
            }
            score.set(0);
            spawnFood();
        } else {
            body.addFirst(newHead);

            // Check if snake eats food
            if (newHead[0] == food[0] && newHead[1] == food[1]) {
                score.set(score.get() + 1);
                spawnFood();
            } else {
                body.removeLast();
            }
        }
    }

    public LinkedList<int[]> getBody() {
        return body;
    }

    public int[] getFood() {
        return food;
    }

    private void spawnFood() {
        food = new int[]{(int) (Math.random() * height), (int) (Math.random() * width)};
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

	public int getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	public SnakeView getView() {
		// TODO Auto-generated method stub
		return new SnakeView(this);
	}
	
	   // Load high score from file
    private int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0; // Default high score if the file doesn't exist or is invalid
        }
    }
    
    
    // Save high score to file
    private void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            System.out.println("Error saving high score: " + e.getMessage());
        }
    }
}

    


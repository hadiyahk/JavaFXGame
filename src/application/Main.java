package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	
	
	 int score = 0; 
	 int highscore = 0 ; 
	 Text scoreText; 
	 Text highScore;

    @Override
    public void start(Stage primaryStage) {
    	String musicFile = getClass().getResource("/In-the-hall-of-the-mountain-king.mp3").toExternalForm();
    	Media media = new Media(musicFile);
    
    	
    	

        // Create MediaPlayer object
        AudioClip mediaPlayer = new AudioClip(media.getSource());
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // To loop the music

       

        // Optionally, you can control the volume
        mediaPlayer.setVolume(1.0); // Set volume (0.0 to 1.0)
        mediaPlayer.play();

    	
        
    	
    	
    	
        SnakeModel model = new SnakeModel();
        SnakeView view = new SnakeView(model);
        SnakeController controller = new SnakeController(model);
      

   		
        Scene scene = new Scene(view, Color.BLACK);
        
       
        scene.setOnKeyPressed(event -> controller.handleKeyPress(event));

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        
        primaryStage.show();

        // Game loop to continuously update the snake's movement
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            model.move();
            view.draw();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

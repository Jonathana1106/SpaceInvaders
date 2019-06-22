/**
 * 
 */
package GUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * GameOver: Clase que realiza la animacion de GameOver del Juego.
 * 
 * @author Jonathan
 * @version 4.0
 *
 */
public class GameOver extends Application {

	private int level;
	private int score;

	@Override
	public void start(Stage GOStage) throws Exception {

		GOStage.setTitle("Space Invaders");
		GOStage.getIcons().add(new Image("/Images/space-invadersD.png"));
		GOStage.setWidth(500);
		GOStage.setHeight(300);
		GOStage.setResizable(false);
		GOStage.centerOnScreen();

		Group root = new Group();
		Scene theScene = new Scene(root);
		theScene.setFill(Color.BLACK);
		GOStage.setScene(theScene);

		Label label = new Label("EndGame!");
		label.setFont(Font.font("Berlin Sans FB", 75));
		label.setTextFill(Color.RED);
		label.setTranslateX(110);
		label.setTranslateY(45);
		root.getChildren().add(label);

		Label label2 = new Label("Score: " + score + "  |  Reached Level: " + level);
		label2.setFont(Font.font("Berlin Sans FB", 25));
		label2.setTextFill(Color.WHITE);
		label2.setTranslateX(130);
		label2.setTranslateY(130);
		root.getChildren().add(label2);

		Button button = new Button("Revenge?");
		button.setFont(Font.font("Berlin Sans FB", 20));
		button.setMaxWidth(200);
		button.setMaxHeight(20);
		button.setTranslateX(195);
		button.setTranslateY(175);
		root.getChildren().add(button);

		button.setOnAction(e -> {
			InitGame initGame = new InitGame();
			try {
				initGame.start(new Stage());
				GOStage.close();
			} catch (Exception ex) {
			}
		});

		GOStage.show();
	}

	public void stopGame(Stage theStage) {
		theStage.close();
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
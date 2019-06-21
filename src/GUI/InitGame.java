package GUI;

import Rows.BasicEnemyRow;
import Rows.EnemyRow;
import Rows.EnemyRowA;
import SpaceShips.UserShip;
import Structures.LinkedList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author Jonathan
 * @version 3.0
 */
public class InitGame extends Application {

	/**
	 * Variables de la clase.
	 */
	Scene scene;
	Group root;
	Canvas canvas = new Canvas(1280, 720);
	GraphicsContext gc;

	EnemyRow row = null;
	LinkedList enemyRow = null;

	/**
	 * Ancho y largo de la ventana.
	 */
	double WIDTH = 1280;
	double HEIGHT = 720;

	/*
	 * Posicion inicial de la nave jugador.
	 */
	double defCoordX = 640;
	double defCoordY = 600;

	/**
	 * Velocidad y tamano de la nave del jugador.
	 */
	double lasserSpeed = 10;
	double iconSize = 75;

	/**
	 * Variables del juego.
	 */
	String currentEnemyRow = "";
	String nextEnemyRow = "Unknown";
	int countRows = 0;
	int level = 0;
	int score = 0;

	/**
	 * Main: Inicia el juego.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		/**
		 * Definiendo la ventana.
		 */
		stage.setTitle("Space Invaders");
		stage.getIcons().add(new Image("/Images/spaceInvaders.png"));
		stage.setMaxHeight(HEIGHT);
		stage.setMaxWidth(WIDTH);
		stage.setResizable(false);

		root = new Group();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		/**
		 * Agregando imagen de fondo e icono de la ventana.
		 */
		Image background = new Image("/Images/battleField.jpg");
		ImageView bg = new ImageView(background);
		bg.setFitWidth(WIDTH);
		bg.setFitHeight(HEIGHT);
		root.getChildren().add(bg);

		/**
		 * Dibujando linea para separar la seccion de juego de la seccion de
		 * informacion.
		 */
//		Line line = new Line();
//		line.setStartX(0);
//		line.setStartY(615);
//		line.setEndX(WIDTH);
//		line.setEndY(615);
//		line.setStroke(Color.DARKCYAN);
//		root.getChildren().add(line);

		/**
		 * Creando la nave del usuario.
		 */
		UserShip user = new UserShip("/Images/userShip.png", defCoordX, defCoordY);
		ImageView usr = new ImageView(user.getLogo());
		usr.setX(defCoordX);
		usr.setY(defCoordY);
		usr.setFitHeight(iconSize);
		usr.setFitWidth(iconSize);
		controlUserShip(scene, usr);
		root.getChildren().add(usr);

		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, WIDTH, HEIGHT);

		Label label = new Label("");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("AgencyFB", 20));
		label.setTranslateX(10);
		label.setTranslateY(10);
	}

	/**
	 * Metodo que se encarga de controlar los movimientos de la nave del usuario
	 * mediante la ayuda del teclado.
	 * 
	 * @param scene: Escena donde se muestra el juego.
	 * @param usr:   Imagen de la nave del usuario.
	 */
	private void controlUserShip(Scene scene, ImageView usr) {
		scene.setOnKeyPressed((KeyEvent event) -> {
			if (event.getCode() == KeyCode.RIGHT && (defCoordX + iconSize) < WIDTH) {
				defCoordX = defCoordX + 10;
				usr.setX(defCoordX);
			} else if (event.getCode() == KeyCode.LEFT && (defCoordX) > 0) {
				defCoordX = defCoordX - 10;
				usr.setX(defCoordX);
			} else if (event.getCode() == KeyCode.UP && (defCoordY) > 0) {
				defCoordY = defCoordY - 10;
				usr.setY(defCoordY);
			} else if (event.getCode() == KeyCode.DOWN && (defCoordY + iconSize) < HEIGHT) {
				defCoordY = defCoordY + 10;
				usr.setY(defCoordY);
			}
		});
	}

	public void chooseEnemyRow() {
		// int enemyCase = 1 + (int) (Math.random() * 3);
		switch (level) {
		case 1:
			row = new BasicEnemyRow();
			currentEnemyRow = "Basico";
			break;
		case 2:
			row = new EnemyRowA();
			currentEnemyRow = "Clase A";
		}
		row.setEnemyRow();
		enemyRow = row.getEnemyRow();
		row.setBool(true);
		row.createEnemyRow(gc);
	}

}
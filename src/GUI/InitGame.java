package GUI;

import Rows.BasicEnemyRow;
import Rows.EnemyRow;
import Rows.EnemyRowA;
import Rows.EnemyRowB;
import Rows.EnemyRowC;
import SpaceShips.Bullets;
import SpaceShips.EnemyShip;
import SpaceShips.UserShip;
import Structures.LinkedList;
import Structures.Node;
import javafx.animation.AnimationTimer;
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
import javafx.scene.layout.HBox;
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
	Canvas canvas = new Canvas(1280, 700);
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
	double iconSize = 80;

	/**
	 * Variables del juego.
	 */
	String currentEnemyRow = "";
	String nextEnemyRow = "";
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

		// ###################################################################//
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
		label.setFont(Font.font("Berlin Sans FB", 20));
		label.setTranslateX(10);
		label.setTranslateY(10);

		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				if (row != null) {
					if (row.isEndOfGame()) {
						stop();
						GameOver gameOver = new GameOver();
						gameOver.setLevel(level);
						gameOver.setScore(score);
						gameOver.stopGame(stage);
						try {
							gameOver.start(new Stage());
						} catch (Exception e) {
						}
					}
				}
				if (enemyRow != null) {
					try {
						if (enemyRow.getFlag().getData().getCoordY() == 50) {
							countRows += 1;
						}
					} catch (NullPointerException e) {
						countRows += 1;
					}
				}
				if (countRows % 4 == 0) {
					level += 1;
					chooseEnemyRow();
					if (countRows == 4) {
						countRows = 0;
					}
				}
				label.setText("Level: " + level + "  Score:  " + score + "  |  Current Row: " + currentEnemyRow
						+ " Next Row: " + nextEnemyRow);
			}
		}.start();

		HBox hBox = new HBox(11);
		hBox.setSpacing(12);
		hBox.getChildren().add(label);
		root.getChildren().add(hBox);
		root.getChildren().add(canvas);
		stage.show();
	}

	public void chooseEnemyRow() {
		int list = 1 + (int) (Math.random() * ((3 - 1) + 1));
		switch (level) {
		case 1:
			row = new BasicEnemyRow();
			currentEnemyRow = "Basic";
			nextEnemyRow = "Class A";
			break;
		case 2:
			if (list != 3) {
				row = new EnemyRowA();
				currentEnemyRow = "Class A";
			} else {
				row = new EnemyRowB();
				currentEnemyRow = "Class B";
			}
			break;
		case 3:
			if (list != 3) {
				row = new EnemyRowB();
				currentEnemyRow = "Class B";
			} else {
				row = new EnemyRowC();
				currentEnemyRow = "Class C";
			}
			break;
		default:
			switch (list) {
			case 1:
				row = new EnemyRowC();
				currentEnemyRow = "Class C";
				break;
			case 2:
				row = new EnemyRowA();
				currentEnemyRow = "Class D";
				break;
			case 3:
				row = new BasicEnemyRow();
				currentEnemyRow = "Class E";
				break;
			}
		}
		row.setEnemyRow();
		enemyRow = row.getEnemyRow();
		row.setBool(true);
		row.createEnemyRow(gc);
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
			} else if (event.getCode() == KeyCode.SPACE) {
				Bullets bullet = new Bullets(defCoordX + iconSize / 2, defCoordY - iconSize / 2);
				root.getChildren().add(bullet.getBullets());
				animateBullets(enemyRow, bullet);
			}
		});
	}

	/**
	 * Metodo encargado de realizar las animaciones de las balas.
	 * 
	 * @param enemyRow: Lista enlazada donde se encuetran las naves emenigas.
	 * @param bullet
	 */
	public void animateBullets(LinkedList enemyRow, Bullets bullet) {
		AnimationTimer animator = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				bullet.setCoordY(bullet.getCoordY() - lasserSpeed);
				bullet.getBullets().setCenterY(bullet.getCoordY());
				if (bullet.getCoordY() <= 0) {
					bullet.deleteBullets();
					stop();
				} else {
					try {
						Node currentNode = enemyRow.getFlag();
						for (int i = 1; i <= enemyRow.getSize(); i++) {
							EnemyShip enemy = currentNode.getData();
							if (bullet.intersects(enemy)) {
								bullet.deleteBullets();
								stop();
								if (enemy.getIsBoss()) {
									enemy.setShootsReceived(enemy.getShootsReceived() + 1);
									if (enemy.getShootsRequired() == enemy.getShootsReceived()) {
										row.executeBossDestroyer();
										score += 75;
									}
								} else {
									if (enemy.getShootsRequired() != 0) {
										enemy.setShootsReceived(enemy.getShootsReceived() + 1);
										if (enemy.getShootsRequired() == enemy.getShootsReceived()) {
											enemy.setLogo(null);
											enemyRow.deleteNode(enemy);
											score += 50;
										}
									} else {
										enemy.setLogo(null);
										enemyRow.deleteNode(enemy);
										score += 25;
									}
								}
								if ((countRows + 1) % 4 != 0 && enemyRow.getFlag() == null) {
									chooseEnemyRow();
								}
							}
							currentNode = currentNode.getNext();
						}
					} catch (NullPointerException ex) {
					}
				}
			}
		};
		animator.start();
	}
}
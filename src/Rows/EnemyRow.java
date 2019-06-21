/**
 * 
 */
package Rows;

import SpaceShips.EnemyShip;
import Structures.LinkedList;
import Structures.Node;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * EnemyRow: Clase de las hileras enemigas.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public abstract class EnemyRow {
	/**
	 * Variables de la clase.
	 */
	protected LinkedList enemyRow;
	protected EnemyShip boss;
	protected double enemyXSpeed;
	protected double enemyYSpeed;
	protected boolean bool;
	protected boolean endOfGame;

	/**
	 * Metodo que se encarga de crear una hilera de enemigos.
	 * 
	 * @param graphics: GraphicsContext donde se encuentran la hilera de enemigos.
	 */
	public abstract void createEnemyRow(GraphicsContext graphics);

	/**
	 * Metodo que se encarga de animar graficamente la hilera de naves enemigas.
	 * 
	 * @param enemy:    Nave enemiga.
	 * @param graphics: GraphicsContext donde se dibujaran las naves.
	 */
	public void animateEnemyRow(EnemyShip enemy, GraphicsContext graphics) {
		double initPoss = enemy.getCoordX();
		AnimationTimer animationTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				graphics.clearRect(enemy.getCoordX(), enemy.getCoordY(), 1280, 720);

				enemy.setCoordX(enemy.getCoordX() + enemyXSpeed);
				enemy.setCoordY(enemy.getCoordY() + enemyYSpeed);

				if (enemy.getCoordX() > (initPoss + 75)) {
					enemy.setCoordX(initPoss + 75 - enemyXSpeed);
					enemy.setCoordY(initPoss + 75 - enemyYSpeed);
					enemyXSpeed = enemyXSpeed * -1;
				} else if (enemy.getCoordX() < initPoss) {
					enemy.setCoordX(initPoss);
					enemyXSpeed = enemyXSpeed * -1;
				}
				if (enemy.getCoordY() >= 540) {
					setEndOfGame(true);
					stop();
				}
				if (enemy.getLogo() == null) {
					stop();
				}
				enemy.render(graphics);
			}
		};
		animationTimer.start();
	}

	/**
	 * Metodo que se encarga de crear una hilera (lista) de enemigos.
	 */
	public void setEnemyRow() {
		int size = 4 + (int) (Math.random() * ((8 - 4) + 1));
		int enemyCoordX = -10 * size + 60;
		int enemyCoordY = 50;
		for (int i = 1; i <= size; i++) {
			EnemyShip enemy = new EnemyShip("/Images/space-invadersBasic.png", enemyCoordX, enemyCoordY, i);
			enemyRow.insertEnd(enemy);
			enemyCoordX += 1280 / size;
		}
	}

	/**
	 * Metodo encargado de escoger al jefe de una hilera de enemigos.
	 * 
	 * @param enemyRow: Lista enlazada de naves enemigas.
	 * @return EnemyShip: Nave enemiga.
	 */
	public EnemyShip chooseBoss(LinkedList enemyRow) {
		int shipPos = 1 + (int) (Math.random() * ((enemyRow.getSize() - 1) + 1));
		Node current = enemyRow.getFlag();
		while (current != null) {
			if (current.getData().getShipPoss() == shipPos) {
				current.getData().setIsBoss(true);
				current.getData().setLogo(new Image("/Images/space-invadersBoss.png"));
				return current.getData();
			}
			current = current.getNext();
		}
		return null;
	}

	/**
	 * Metodo que elimina el jefe.
	 */
	public void executeBossDestroyer() {
		enemyRow.deleteAllNodes();
	}

	/**
	 * Getter: Obtiene la lista donde se encuentran las naves enemigas.
	 * 
	 * @return LinkedList enemyRow: Lista enlazada que contiene las naves enemigas.
	 */
	public LinkedList getEnemyRow() {
		return enemyRow;
	}

	/**
	 * Setter: Establece una lista de naves enemigas.
	 * 
	 * @param enemyRow: Lista de naves enemigas.
	 */
	public void setEnemyRow(LinkedList enemyRow) {
		this.enemyRow = enemyRow;
	}

	/**
	 * Getter: Obtiene el jefe de una hilera de naves enemigas.
	 * 
	 * @return EnemyShip boos: Jefe de la hilera de naves enemigas.
	 */
	public EnemyShip getBoss() {
		return boss;
	}

	/**
	 * Setter: Establece el jefe de una hilera de naves enemigas.
	 * 
	 * @param boss: Jefe de la hilera.
	 */
	public void setBoss(EnemyShip boss) {
		this.boss = boss;
	}

	/**
	 * Getter: Obtiene el valor de la velicidad de las naves en X.
	 * 
	 * @return double enemyXSpeed: Velocidad de las naves enemigas en X.
	 */
	public double getEnemyXSpeed() {
		return enemyXSpeed;
	}

	/**
	 * Setter: Establece el valor de la velicidad de las naves en X.
	 * 
	 * @param enemyXSpeed: Velocidad de las naves enemigas en X.
	 */
	public void setEnemyXSpeed(double enemyXSpeed) {
		this.enemyXSpeed = enemyXSpeed;
	}

	/**
	 * Getter: Obtiene el valor de la velicidad de las naves en Y.
	 * 
	 * @return double enemyYSpeed: Velocidad de las naves enemigas en Y.
	 */
	public double getEnemyYSpeed() {
		return enemyYSpeed;
	}

	/**
	 * Setter: Establece el valor de la velicidad de las naves en Y.
	 * 
	 * @param enemyYSpeed: Velocidad de las naves enemigas en Y.
	 */
	public void setEnemyYSpeed(double enemyYSpeed) {
		this.enemyYSpeed = enemyYSpeed;
	}

	/**
	 * Getter: Obtiene el valor de la variable bool.
	 * 
	 * @return Boolean: Valor de la variable.
	 */
	public boolean getBool() {
		return bool;
	}

	/**
	 * Setter: Establece el valor de la variable bool.
	 * 
	 * @param bool: Valor de la variable.
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}

	/**
	 * Getter: Obtiene el valor de la variable endGame para conocer el estado del
	 * juego.
	 * 
	 * @return Boolean: Verdadero o falso, dependiendo del estado del juego.
	 */
	public boolean isEndOfGame() {
		return endOfGame;
	}

	/**
	 * Setter: Establece el valor de la variable endGame.
	 * 
	 * @param endOfGame: Verdadero o falso, dependiendo de si el juego ha finalizado
	 *                   o no.
	 */
	public void setEndOfGame(boolean endOfGame) {
		this.endOfGame = endOfGame;
	}
}
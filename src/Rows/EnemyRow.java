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
	protected EnemyShip boosDirector;
	protected double speedX;
	protected double speedY;
	protected boolean bool;
	protected boolean endGame;

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

				enemy.setCoordX(enemy.getCoordX() + speedX);
				enemy.setCoordY(enemy.getCoordY() + speedY);

				if (enemy.getCoordX() > (initPoss + 75)) {
					enemy.setCoordX(initPoss + 75 - speedX);
					enemy.setCoordY(initPoss + 75 - speedY);
					speedX = speedX * -1;
				} else if (enemy.getCoordX() < initPoss) {
					enemy.setCoordX(initPoss);
					speedX = speedX * -1;
				}
				if (enemy.getCoordY() >= 540) {
					System.out.println("Game Over!!!!!!");
					setEndGame(true);
					stop();
				}
				if (enemy.getLogo() == null) {
					stop();
					System.out.println("No se que hacer.");
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
			EnemyShip enemy = new EnemyShip("/Images/space-invadersE.png", enemyCoordX, enemyCoordY, i);
			enemyRow.addEnd(enemy);
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
			if (current.getEnemyShip().getShipPoss() == shipPos) {
				current.getEnemyShip().setIsBoss(true);
				current.getEnemyShip().setLogo(new Image("/Images/space-invadersA.png"));
				return current.getEnemyShip();
			}
			current = current.getNext();
		}
		return null;
	}

	/**
	 * Metodo que elimina el jefe.
	 */
	public void bossDestroyer() {
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
	 * @return EnemyShip boosDirector: Jefe de la hilera de naves enemigas.
	 */
	public EnemyShip getBoosDirector() {
		return boosDirector;
	}

	/**
	 * Setter: Establece el jefe de una hilera de naves enemigas.
	 * 
	 * @param boosDirector: Jefe de la hilera.
	 */
	public void setBoosDirector(EnemyShip boosDirector) {
		this.boosDirector = boosDirector;
	}

	/**
	 * Getter: Obtiene el valor de la velicidad de las naves en X.
	 * 
	 * @return double speedX: Velocidad de las naves enemigas en X.
	 */
	public double getSpeedX() {
		return speedX;
	}

	/**
	 * Setter: Establece el valor de la velicidad de las naves en X.
	 * 
	 * @param speedX: Velocidad de las naves enemigas en X.
	 */
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	/**
	 * Getter: Obtiene el valor de la velicidad de las naves en Y.
	 * 
	 * @return double speedY: Velocidad de las naves enemigas en Y.
	 */
	public double getSpeedY() {
		return speedY;
	}

	/**
	 * Setter: Establece el valor de la velicidad de las naves en Y.
	 * 
	 * @param speedY: Velocidad de las naves enemigas en Y.
	 */
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
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
	public boolean isEndGame() {
		return endGame;
	}

	/**
	 * Setter: Establece el valor de la variable endGame.
	 * 
	 * @param endGame: Verdadero o falso, dependiendo de si el juego ha finalizado o
	 *                 no.
	 */
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}
}
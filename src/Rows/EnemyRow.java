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
 *
 */
public abstract class EnemyRow {
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
	 * Metodo que se encarga de crear una hilera de enemigos.
	 */
	public void setEnemyRow() {
		int size = 4 + (int) (Math.random() * ((6 - 4) + 1));// Obtiene un num random entre 4 y 6 incluyendo 6
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
			if (current.getData().getShipPoss() == shipPos) {
				current.getData().setBoss(true);
				current.getData().setLogo(new Image("/Images/space-invadersA.png"));
				return current.getData();
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
	 * 
	 * @return
	 */
	public LinkedList getEnemyRow() {
		return enemyRow;
	}

	/**
	 * 
	 * @param enemyRow
	 */
	public void setEnemyRow(LinkedList enemyRow) {
		this.enemyRow = enemyRow;
	}

	/**
	 * 
	 * @return
	 */
	public EnemyShip getBoosDirector() {
		return boosDirector;
	}

	/**
	 * 
	 * @param boosDirector
	 */
	public void setBoosDirector(EnemyShip boosDirector) {
		this.boosDirector = boosDirector;
	}

	/**
	 * 
	 * @return
	 */
	public double getSpeedX() {
		return speedX;
	}

	/**
	 * 
	 * @param speedX
	 */
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	/**
	 * 
	 * @return
	 */
	public double getSpeedY() {
		return speedY;
	}

	/**
	 * 
	 * @param speedY
	 */
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getBool() {
		return bool;
	}

	/**
	 * 
	 * @param bool
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEndGame() {
		return endGame;
	}

	/**
	 * 
	 * @param endGame
	 */
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}
}
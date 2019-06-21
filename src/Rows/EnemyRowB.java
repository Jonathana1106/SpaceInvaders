/**
 * 
 */
package Rows;

import SpaceShips.EnemyShip;
import Structures.DoublyLinkedList;
import Structures.Node;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * EnemyRowB: Hilera de tipo B de naves enemigas.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public class EnemyRowB extends EnemyRow {

	public EnemyRowB() {
		this.enemyRow = new DoublyLinkedList();
		this.boosDirector = null;
		this.speedX = 5;
		this.speedY = 1;
	}

	@Override
	public void createEnemyRow(GraphicsContext graphics) {
		if (enemyRow.getFlag() == null) {
			this.setEnemyRow();
			this.setSpeedY(this.speedY + 0.05);
			enemyRow = this.getEnemyRow();
			bool = true;
		}

		if (bool == true) {
			if (this.getBoosDirector() != null) {
				this.getBoosDirector().setLogo(new Image("Images/space-invadersB.png"));
				this.getBoosDirector().setIsBoss(false);
			}

			this.setBoosDirector(this.chooseBoss(enemyRow));
			this.getBoosDirector().setShootsRequired();
		}

		Node currentNode = enemyRow.getFlag();
		while (currentNode != null) {
			EnemyShip enemy = currentNode.getEnemyShip();
			enemy.render(graphics);
			if (bool == true) {
				this.animateEnemyRow(enemy, graphics);
			}
			currentNode = currentNode.getNext();
		}
		this.bool = false;
	}

	/**
	 * Metodo que se encarga de buscar el nodo de la hilera que contiene al jefe de
	 * la misma.
	 * 
	 * @return Node boss: Nodo que contiene al jefe de la hilera.
	 */
	public Node searchBoss() {
		Node currentNode = enemyRow.getFlag();
		while (currentNode.getNext() != null) {
			if (currentNode.getEnemyShip().getIsBoss()) {
				return currentNode;
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return enemyRow.getFlag();
	}

	/**
	 * Metodo que se encarga de cambiar de posicion al jefe en una hilera.
	 * 
	 * @param oldBoss: Antiguo jefe.
	 * @param newBoss: Nuevo jefe.
	 */
	public void changeBoss(EnemyShip oldBoss, EnemyShip newBoss) {
		if (enemyRow.getSize() > 1) {
			int shootsReceived = oldBoss.getShootsReceived();
			int shootsRequired = oldBoss.getShootsRequired();
			Image bossLogo = oldBoss.getLogo();
			Image enemyLogo = newBoss.getLogo();

			oldBoss.setIsBoss(false);
			oldBoss.setLogo(enemyLogo);
			oldBoss.setShootsRequired(0);
			oldBoss.setShootsReceived(0);

			newBoss.setIsBoss(true);
			newBoss.setLogo(bossLogo);
			newBoss.setShootsReceived(shootsReceived);
			newBoss.setShootsRequired(shootsRequired);
			this.setBoosDirector(newBoss);
		}
	}

	@Override
	public void animateEnemyRow(EnemyShip enemy, GraphicsContext graphics) {
		double initPoss = enemy.getCoordX();
		AnimationTimer animator = new AnimationTimer() {
			private long lastUpdate = 0;

			@Override
			public void handle(long arg0) {
				graphics.clearRect(enemy.getCoordX(), enemy.getCoordY(), 1280, 720);

				if (arg0 - lastUpdate >= (2056 * 1000000)) {
					EnemyShip newBoss;
					if (enemy.getIsBoss() && enemyRow.getSize() > 1) {
						try {
							newBoss = enemyRow.chooseRandomNode().getEnemyShip();
						} catch (NullPointerException ex) {
							if (enemy == enemyRow.getFlag().getEnemyShip()) {
								newBoss = enemyRow.getFlag().getNext().getEnemyShip();
							} else {
								newBoss = enemyRow.getFlag().getEnemyShip();
							}
						}
						if (!newBoss.getIsBoss()) {
							changeBoss(enemy, newBoss);
						}
					}
					lastUpdate = arg0;
				}

				enemy.setCoordX(enemy.getCoordX() + speedX);
				enemy.setCoordY(enemy.getCoordY() + speedY);

				if (enemy.getCoordX() > (initPoss + 85)) {
					enemy.setCoordX((initPoss + 85) - speedX);
					speedX *= -1;
				} else if (enemy.getCoordX() < initPoss) {
					enemy.setCoordX(initPoss);
					speedX *= -1;
				}

				if (enemy.getCoordY() >= 540) {
					setEndGame(true);
					stop();
				}

				if (enemy.getLogo() == null) {
					stop();
				}
				enemy.render(graphics);
			}

		};
		animator.start();
	}

}
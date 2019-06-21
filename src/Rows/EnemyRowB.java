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
		this.boss = null;
		this.enemyXSpeed = 2;
		this.enemyYSpeed = 0.5;
	}

	@Override
	public void createEnemyRow(GraphicsContext graphics) {
		if (enemyRow.getFlag() == null) {
			this.setEnemyRow();
			this.setEnemyYSpeed(this.enemyYSpeed + 0.05);
			enemyRow = this.getEnemyRow();
			bool = true;
		}

		if (bool == true) {
			if (this.getBoss() != null) {
				this.getBoss().setLogo(new Image("Images/space-invadersB.png"));
				this.getBoss().setIsBoss(false);
			}

			this.setBoss(this.chooseBoss(enemyRow));
			this.getBoss().setShootsRequired();
		}

		Node currentNode = enemyRow.getFlag();
		while (currentNode != null) {
			EnemyShip enemy = currentNode.getData();
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
			if (currentNode.getData().getIsBoss()) {
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
			this.setBoss(newBoss);
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
							newBoss = enemyRow.chooseRandomNode().getData();
						} catch (NullPointerException ex) {
							if (enemy == enemyRow.getFlag().getData()) {
								newBoss = enemyRow.getFlag().getNext().getData();
							} else {
								newBoss = enemyRow.getFlag().getData();
							}
						}
						if (!newBoss.getIsBoss()) {
							changeBoss(enemy, newBoss);
						}
					}
					lastUpdate = arg0;
				}

				enemy.setCoordX(enemy.getCoordX() + enemyXSpeed);
				enemy.setCoordY(enemy.getCoordY() + enemyYSpeed);

				if (enemy.getCoordX() > (initPoss + 85)) {
					enemy.setCoordX((initPoss + 85) - enemyXSpeed);
					enemyXSpeed *= -1;
				} else if (enemy.getCoordX() < initPoss) {
					enemy.setCoordX(initPoss);
					enemyXSpeed *= -1;
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
		animator.start();
	}

}
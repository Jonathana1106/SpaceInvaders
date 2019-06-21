/**
 * 
 */
package Rows;

import SpaceShips.EnemyShip;
import Structures.CircularLinkedList;
import Structures.LinkedList;
import Structures.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * @author Jonathan
 *
 */
public class EnemyRowC extends EnemyRow {

	/**
	 * Constructor de la clase EnemyRowC.
	 */
	public EnemyRowC() {
		this.enemyRow = new CircularLinkedList();
		this.boss = null;
		this.enemyXSpeed = 2;
		this.enemyYSpeed = 0.6;
	}

	@Override
	public void createEnemyRow(GraphicsContext graphics) {
		if (enemyRow.getFlag() == null) {
			this.setEnemyRow();
			this.setEnemyYSpeed(this.getEnemyYSpeed() + 0.05);
			enemyRow = this.getEnemyRow();
			bool = true;
		}

		if (bool == true) {
			this.setBoss(this.chooseBoss(this.enemyRow));
			this.getBoss().setShootsRequired();
		}

		Node current = enemyRow.getFlag().getNext();
		while (current != enemyRow.getFlag()) {
			EnemyShip enemy = current.getData();
			enemy.render(graphics);
			if (bool == true) {
				this.animateEnemyRow(enemy, graphics);
			}
			current = current.getNext();
		}
		EnemyShip enemy = current.getData();
		enemy.render(graphics);
		if (bool == true) {
			this.animateEnemyRow(enemy, graphics);
		}
		this.bool = false;

	}

	@Override
	public EnemyShip chooseBoss(LinkedList enemyRow) {
		int shipPos = 1 + (int) (Math.random() * ((enemyRow.getSize() - 1) + 1));
		Node current = enemyRow.getFlag();
		do {
			if (current.getData().getShipPoss() == shipPos) {
				current.getData().setIsBoss(true);
				current.getData().setLogo(new Image("/Images/space-invadersBoss.png"));
				return current.getData();
			}
			current = current.getNext();
		} while (current != enemyRow.getFlag());
		return current.getData();
	}

	@Override
	public void executeBossDestroyer() {
		if (enemyRow.getSize() > 1) {
			EnemyShip oldBoss = this.boss;
			Image logoBoss = this.boss.getLogo();
			Node newBoss = enemyRow.chooseRandomNode();

			oldBoss.setLogo(null);
			enemyRow.deleteNode(oldBoss);

			this.setBoss(newBoss.getData());
			newBoss.getData().setShootsRequired();
			newBoss.getData().setIsBoss(true);
			newBoss.getData().setLogo(logoBoss);
		} else {
			enemyRow.deleteNode(this.boss);
		}
	}
}
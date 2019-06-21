/**
 * 
 */
package Rows;

import SpaceShips.EnemyShip;
import Structures.Node;
import Structures.SimpleLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 * EnemyRowA: Hilera de tipo A de naves enemigas.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public class EnemyRowA extends EnemyRow {

	/**
	 * Constructor de la clase.
	 */
	public EnemyRowA() {
		this.enemyRow = new SimpleLinkedList();
		this.boss = null;
		this.enemyXSpeed = 2;
		this.enemyYSpeed = 0.4;
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
			this.setBoss(this.chooseBoss(enemyRow));
			this.getBoss().setShootsRequired();
		}

		Node current = enemyRow.getFlag();
		while (current != null) {
			EnemyShip enemy = current.getData();
			enemy.render(graphics);
			if (bool == true) {
				this.animateEnemyRow(enemy, graphics);
			}
			current = current.getNext();
		}
		this.bool = false;
	}
}
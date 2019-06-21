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
		this.boosDirector = null;
		this.speedX = 2;
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
			this.setBoosDirector(this.chooseBoss(enemyRow));
			this.getBoosDirector().setShootsRequired();
		}

		Node current = enemyRow.getFlag();
		while (current != null) {
			EnemyShip enemy = current.getEnemyShip();
			enemy.render(graphics);
			if (bool == true) {
				this.animateEnemyRow(enemy, graphics);
			}
			current = current.getNext();
		}
		this.bool = false;
	}
}
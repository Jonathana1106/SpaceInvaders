/**
 * 
 */
package Rows;

import SpaceShips.EnemyShip;
import Structures.Node;
import Structures.SimpleLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Jonathan
 *
 */
public class BasicEnemyRow extends EnemyRow {

	/**
	 * 
	 */
	public BasicEnemyRow() {
		this.enemyRow = new SimpleLinkedList();
		this.speedX = 2;
		this.speedY = 1;
	}

	@Override
	public void createEnemyRow(GraphicsContext graphics) {
		if (enemyRow.getFlag() == null) {
			this.setEnemyRow();
			this.setSpeedY(this.getSpeedY() + 0.05);
			enemyRow = this.getEnemyRow();
			bool = true;
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
}
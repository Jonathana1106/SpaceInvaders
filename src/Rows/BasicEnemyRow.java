/**
 * 
 */
package Rows;

import SpaceShips.EnemyShip;
import Structures.Node;
import Structures.SimpleLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 * BasicEnemyRow: Hilera basica de naves enemigas.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public class BasicEnemyRow extends EnemyRow {

	/**
	 * Constructor de la clase.
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
			EnemyShip enemy = currentNode.getEnemyShip();
			enemy.render(graphics);
			if (bool == true) {
				this.animateEnemyRow(enemy, graphics);
			}
			currentNode = currentNode.getNext();
		}
		this.bool = false;
	}
}
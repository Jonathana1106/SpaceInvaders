/**
 * 
 */
package Structures;

import SpaceShips.EnemyShip;

/**
 * CircularLinkedList: Lista circular que hereda de la clase abstracta
 * LinkedList.
 * 
 * @author Jonathan
 * @version 3.0
 */
public class CircularLinkedList extends LinkedList {

	/**
	 * Constructor de la clase CircularLinkedList.
	 */
	public CircularLinkedList() {
		this.tail = null;
		this.size = 0;
	}

	@Override
	public Node getFlag() {
		return this.tail;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public EnemyShip getData(int shipInfo) {
		Node currentNode = tail;
		if (currentNode.getEnemyShip().getShipPoss() == shipInfo) {
			return currentNode.getEnemyShip();
		} else {
			/**
			 * do while;
			 */
			while (currentNode != tail) {
				if (currentNode.getEnemyShip().getShipPoss() == shipInfo) {
					return currentNode.getEnemyShip();
				} else {
					currentNode = currentNode.getNext();
				}
			}
		}
		return null;
	}

	@Override
	public void addEnd(EnemyShip ship) {
		Node newNode = new Node(ship, null);
		if (tail != null) {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
		} else {
			newNode.setNext(newNode);
		}
		tail = newNode;
		size++;
	}

	@Override
	public void deleteNode(EnemyShip ship) {
		try {
			Node currentNode = tail;
			if (currentNode.getEnemyShip() == ship && currentNode.getNext() == tail) {
				tail = null;
				size--;
			} else {
				do {
					if (currentNode.getNext().getEnemyShip() == ship && currentNode.getNext() == tail) {
						currentNode.setNext(currentNode.getNext().getNext());
						tail = currentNode;
						size--;
					} else if (currentNode.getNext().getEnemyShip() == ship) {
						currentNode.setNext(currentNode.getNext().getNext());
						size--;
					} else {
						currentNode = currentNode.getNext();
					}
				} while (currentNode != tail);
			}
		} catch (NullPointerException e) {
		}
	}
}
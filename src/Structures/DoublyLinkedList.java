/**
 * 
 */
package Structures;

import SpaceShips.EnemyShip;

/**
 * DoublyLinkedList: Lista doblemente enlazada que hereda de la clase abstracta
 * LinkedList.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public class DoublyLinkedList extends LinkedList {

	/**
	 * Constructor de la clase DoublyLinkedList.
	 */
	public DoublyLinkedList() {
		this.head = null;
		this.size = 0;
	}

	@Override
	public Node getFlag() {
		return this.head;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public EnemyShip getData(int shipInfo) {
		Node currentNode = head;
		while (currentNode != null) {
			if (currentNode.getEnemyShip().getShipPoss() == shipInfo) {
				return currentNode.getEnemyShip();
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}

	@Override
	public void addEnd(EnemyShip ship) {
		Node newNode = new Node(ship, null, null);
		if (head == null) {
			head = newNode;
		} else {
			Node currentNode = head;
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(newNode);
			newNode.setPrev(currentNode);
		}
		size++;
	}

	@Override
	public void deleteNode(EnemyShip ship) {
		try {
			Node currentNode = head;
			if (currentNode.getNext() == null && currentNode.getEnemyShip() == ship) {
				head = null;
				size--;
			} else if (currentNode.getNext() != null && currentNode.getEnemyShip() == ship) {
				head = currentNode.getNext();
				head.setPrev(null);
				currentNode.setNext(null);
				size--;
			} else {
				while (currentNode.getNext() != null) {
					if (currentNode.getNext().getEnemyShip() == ship) {
						currentNode.getNext().setPrev(null);
						if (currentNode.getNext().getNext() != null) {
							currentNode.getNext().getNext().setPrev(currentNode);
//							currentNode.setNext(currentNode.getNext().getNext());
//							size--;
						}
						currentNode.setNext(currentNode.getNext().getNext());
						size--;
						/**
						 * else {currentNode.setNext(null); }
						 */
					} else {
						currentNode = currentNode.getNext();
					}
				}
			}
		} catch (Exception e) {
		}
	}
}
/**
 * 
 */
package Structures;

import SpaceShips.EnemyShip;

/**
 * SimpleLinkedList: Lista enlazada simple que hereda de la clase abstracta
 * LinkedList.
 * 
 * @author Jonathan
 * @version 3.0
 */
public class SimpleLinkedList extends LinkedList {

	/**
	 * Constructor de la clase SimpleLinkedList.
	 */
	public SimpleLinkedList() {
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
			if (currentNode.getData().getShipPoss() == shipInfo) {
				return currentNode.getData();
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}

	@Override
	public void addEnd(EnemyShip ship) {
		Node newNode = new Node(ship, null);
		if (head == null) {
			head = newNode;
		} else {
			Node currentNode = head;
			while (currentNode.getNext() != null) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(newNode);
		}
		size++;
	}

	@Override
	public void deleteNode(EnemyShip ship) {
		Node currentNode = head;
		if (currentNode.getNext() == null && currentNode.getData() == ship) {
			head = null;
			size--;
		} else if (currentNode.getNext() != null && currentNode.getData() == ship) {
			head = currentNode.getNext();
			currentNode.setNext(null);
			size--;
		} else {
			while (currentNode.getNext() != null) {
				if (currentNode.getNext().getData() == ship) {
					currentNode.setNext(currentNode.getNext().getNext());
					size--;
				} else {
					currentNode = currentNode.getNext();
				}
			}
		}
	}
}
/**
 * 
 */
package Structures;

import SpaceShips.EnemyShip;

/**
 * @author Jonathan
 *
 */
public class DoublyLinkedList extends LinkedList {

	/**
	 * Constructor de la clase DoublyLinkedList
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
		Node currentNode = head;
		if (currentNode.getNext() == null && currentNode.getData() == ship) {
			head = null;
			size--;
		} else if (currentNode.getNext() != null && currentNode.getData() == ship) {
			head = currentNode.getNext();
			head.setPrev(null);
			currentNode.setNext(null);
			size--;
		} else {
			while (currentNode.getNext() != null) {
				if (currentNode.getNext().getData() == ship) {
					currentNode.getNext().setPrev(null);
					if (currentNode.getNext().getNext() != null) {
						currentNode.getNext().getNext().setPrev(currentNode);
						currentNode.setNext(currentNode.getNext().getNext());
						size--;
					}
					/**
					 * Prueba
					 */
					else {
						currentNode.setNext(null);
					}
				} else {
					currentNode = currentNode.getNext();
				}
			}
		}
	}
}
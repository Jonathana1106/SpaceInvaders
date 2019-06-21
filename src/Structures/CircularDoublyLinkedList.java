/**
 * 
 */
package Structures;

import SpaceShips.EnemyShip;

/**
 * CircularDoublyLinkedList: Lista circular doblemente enlazada que hereda de la
 * clase LinkedList.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public class CircularDoublyLinkedList extends LinkedList {

	/**
	 * Constructor de la clase CircularDoublyLinkedList.
	 */
	public CircularDoublyLinkedList() {
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
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}

	@Override
	public void insertEnd(EnemyShip ship) {
		Node newNode = new Node(ship, null, null);
		if (head == null) {
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			head = newNode;
		} else if (head.getNext() == head && head.getPrev() == head) {
			head.setNext(newNode);
			head.setPrev(newNode);

			newNode.setNext(head);
			newNode.setPrev(head);
		} else {
			Node currentNode = head;
			while (currentNode.getNext() != head) {
				currentNode = currentNode.getNext();
			}
			newNode.setPrev(currentNode);
			currentNode.setNext(newNode);
			newNode.setNext(head);
			head.setPrev(newNode);
		}
		size++;
	}

	@Override
	public void deleteNode(EnemyShip ship) {
		try {
			Node currentNode = head;
			if (currentNode.getNext() == head && currentNode.getData() == ship) {
				head = null;
				size--;
			} else if (currentNode.getNext() != head && currentNode.getData() == ship) {
				currentNode = currentNode.getPrev();
				currentNode.setNext(head.getNext());
				head.getNext().setPrev(currentNode);
				head.setNext(null);
				head.setPrev(null);
				head = currentNode.getNext();
				size--;
			} else {
				while (currentNode.getNext() != head) {
					if (currentNode.getNext().getData() == ship) {
						currentNode.getNext().setPrev(null);
						currentNode.setNext(currentNode.getNext().getNext());
						currentNode.getNext().getPrev().setNext(null);
						currentNode.getNext().setPrev(currentNode);
						size--;
					} else {
						currentNode = currentNode.getNext();
					}
				}
			}
		} catch (NullPointerException ex) {
		}
	}
}
/**
 * 
 */
package Structures;

import SpaceShips.EnemyShip;

/**
 * Node: Clase nodo que se encarga de crear los nodos de una lista.
 * 
 * @author Jonathan
 * @version 3.0
 */
public class Node {

	/**
	 * Variables de la clase.
	 */
	private Node prev;
	private Node next;
	private EnemyShip data;

	/**
	 * Constructor de la clase Nodo.
	 * 
	 * @param data: Nave enemiga.
	 */
	public Node(EnemyShip data) {
		this.data = data;
	}

//	public Node(EnemyShip data, Node prev) {
//		this.data =  data;
//		this.prev = prev;
//	}
//	
	/**
	 * Constructor Node.
	 * 
	 * @param data: Nave enemiga.
	 * @param next: Referencia al proximo nodo.
	 */
	public Node(EnemyShip data, Node next) {
		this.data = data;
		this.prev = next;
	}

	/**
	 * Constructor de la clase nodo.
	 * 
	 * @param data: Nave enemiga.
	 * @param prev: Referencia al nodo anterior.
	 * @param next: Referencia al nodo siguiente.
	 */
	public Node(EnemyShip data, Node prev, Node next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	/**
	 * 
	 * @return
	 */
	public Node getPrev() {
		return prev;
	}

	/**
	 * 
	 * @param prev
	 */
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	/**
	 * 
	 * @return
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * 
	 * @param next
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * 
	 * @return
	 */
	public EnemyShip getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(EnemyShip data) {
		this.data = data;
	}

}
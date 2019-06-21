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
	public Node(EnemyShip enemyShip) {
		this.data = enemyShip;
	}

	/**
	 * Constructor Node.
	 * 
	 * @param data: Nave enemiga.
	 * @param next: Referencia al proximo nodo.
	 */
	public Node(EnemyShip enemyShip, Node next) {
		this.data = enemyShip;
		this.prev = next;
	}

	/**
	 * Constructor de la clase nodo.
	 * 
	 * @param data: Nave enemiga.
	 * @param prev: Referencia al nodo anterior.
	 * @param next: Referencia al nodo siguiente.
	 */
	public Node(EnemyShip enemyShip, Node prev, Node next) {
		this.data = enemyShip;
		this.prev = prev;
		this.next = next;
	}

	/**
	 * Getter: Obtiene el nodo anterior.
	 * 
	 * @return Node prev: Nodo anterior.
	 */
	public Node getPrev() {
		return prev;
	}

	/**
	 * Sstter: Establece el nodo anterior.
	 * 
	 * @param prev: Nodo anterior.
	 */
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	/**
	 * Getter: Obtiene el nodo siguiente.
	 * 
	 * @return Node next: Nodo sucesor.
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Setter: Establece el nodo siguiente.
	 * 
	 * @param next: Nodo siguiente.
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * Getter: Obtiene la nave contenida en el nodo.
	 * 
	 * @return EnemyShip enemyShip: Nave emeniga.
	 */
	public EnemyShip getData() {
		return this.data;
	}

	/**
	 * Setter: Establece una nave que se guardara en el nodo.
	 * 
	 * @param enemyShip : Nave enemiga.
	 */
	public void setData(EnemyShip enemyShip) {
		this.data = enemyShip;
	}

}
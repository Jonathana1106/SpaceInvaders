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
	private EnemyShip enemyShip;

	/**
	 * Constructor de la clase Nodo.
	 * 
	 * @param data: Nave enemiga.
	 */
	public Node(EnemyShip enemyShip) {
		this.enemyShip = enemyShip;
	}

	/**
	 * Constructor Node.
	 * 
	 * @param data: Nave enemiga.
	 * @param next: Referencia al proximo nodo.
	 */
	public Node(EnemyShip enemyShip, Node next) {
		this.enemyShip = enemyShip;
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
		this.enemyShip = enemyShip;
		this.prev = prev;
		this.next = next;
	}

	/**
	 * Getter: obtiene el nodo anterior.
	 * 
	 * @return Node prev: nodo anterior.
	 */
	public Node getPrev() {
		return prev;
	}

	/**
	 * Sstter: establece el nodo anterior.
	 * 
	 * @param prev: nodo anterior.
	 */
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	/**
	 * Getter: obtiene el nodo siguiente.
	 * 
	 * @return Node next: nodo sucesor.
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Setter: establece el nodo siguiente.
	 * 
	 * @param next: nodo siguiente.
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * Getter: obtiene la nave contenida en el nodo.
	 * 
	 * @return EnemyShip enemyShip: nave emeniga.
	 */
	public EnemyShip getEnemyShip() {
		return enemyShip;
	}

	/**
	 * Setter: establece una nave que se guardara en el nodo.
	 * 
	 * @param enemyShip : nave enemiga.
	 */
	public void setEnemyShip(EnemyShip enemyShip) {
		this.enemyShip = enemyShip;
	}

}
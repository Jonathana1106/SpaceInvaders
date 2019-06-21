/**
 * 
 */
package Structures;

import SpaceShips.EnemyShip;

/**
 * LinkedList: Clase abstracta de las listas enlazadas.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public abstract class LinkedList {

	protected Node head;
	protected Node tail;
	protected int size;

	/**
	 * Se encarga de obtener el indicador bandera de la lista enlazada, puede ser
	 * head o tail, segun del tipo de lista que se este utilizando.
	 * 
	 * @return Node Flag: El nodo de referencia de la lista, head o tail.
	 */
	public abstract Node getFlag();

	/**
	 * Getter: Tamano de la lista.
	 * 
	 * @return int size: Tamano de la lista enlazada.
	 */
	public abstract int getSize();

	/**
	 * Metodo que obtiene el dato de un nodo segun el codigo que se solicita.
	 * 
	 * @param shipInfo: Informacion propia de cada nave.
	 * @return EnemyShip: Dato que contiene el nodo.
	 */
	public abstract EnemyShip getData(int shipInfo);

	/**
	 * Metodo que agrega un nuevo nodo al final de la lista.
	 * 
	 * @param ship: Nave que contiene el nuevo nodo.
	 */
	public abstract void insertEnd(EnemyShip ship);

	/**
	 * Metodo que se encarga de eliminar un nodo.
	 * 
	 * @param ship: Nave que se encuentra en el nodo a eliminar.
	 */
	public abstract void deleteNode(EnemyShip ship);

	/**
	 * Metodo que se encarga de escoger un nodo de la lista de manera aleatoria.
	 * 
	 * @return Node: Nodo escogido.
	 */
	public Node chooseRandomNode() {
		int random = 1 + (int) (Math.random() * ((size - 1) + 1));
		Node currentNode = this.getFlag();

		for (int i = 1; i <= this.size; i++) {
			if (currentNode.getData().getShipPoss() == random) {
				if (currentNode.getData().getIsBoss()) {
					if (currentNode.getNext() == null && currentNode.getPrev() != null) {
						currentNode = currentNode.getPrev();
					} else if (currentNode.getPrev() == null && currentNode.getNext() != null) {
						currentNode = currentNode.getNext();
					}
					break;
				} else {
					break;
				}
			}
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	/**
	 * Elimina todos los nodos de una lista enlazada.
	 */
	public void deleteAllNodes() {
		try {
			while (this.getFlag() != null) {
				Node currentNode = this.getFlag();
				currentNode.getData().setLogo(null);
				this.deleteNode(currentNode.getData());
			}
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Metodo que se encarga de buscar un nodo a partir de un dato deseado.
	 * 
	 * @param enemyShip : Dato buscado en el nodo.
	 * @return Node: Nodo encontrado.
	 */
	public Node searchNode(EnemyShip enemyShip) {
		Node currentNode = this.getFlag();
		for (int i = 1; i <= this.size; i++) {
			if (currentNode.getData() == enemyShip) {
				break;
			} else {
				currentNode = currentNode.getNext();
			}
		}
		return currentNode;
	}
}
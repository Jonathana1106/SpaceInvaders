/**
 * 
 */
package SpaceShips;

import javafx.scene.image.Image;

/**
 * EnemyShip: Clase de las naves enemigas.
 *
 * @author Jonathan
 * @version 3.0
 *
 */
public class EnemyShip extends Ship {

	/**
	 * Variables de la clase.
	 */
	private int shipPos;
	private boolean isBoss;
	private int shootsRequired;
	private int shootsReceived;

	/**
	 * Constructor de EnemyShip.
	 * 
	 * @param logo:    Imagen de la nave enemiga.
	 * @param coordX:  Coordenada en X de la nave enemiga.
	 * @param coordY:  Coordenada en Y de la nave enemiga.
	 * @param shipPos: Posicion de la nave dentro de la hilera de naves enemigas.
	 */
	public EnemyShip(String logo, double coordX, double coordY, int shipPoss) {
		this.logo = new Image(logo);
		this.coordX = coordX;
		this.coordY = coordY;
		this.shipPos = shipPoss;
		this.isBoss = false;
		this.shootsRequired = 0;
		this.shootsReceived = 0;
	}

	/**
	 * Getter: Obtiene la posicion de la nave enemiga dentro de la lista enlazada
	 * (hilera).
	 * 
	 * @return int shipPos: Posicion de la nave en la hilera.
	 */
	public int getShipPoss() {
		return shipPos;
	}

	/**
	 * Setter: Establece la posicion de la nave dentro de la hilera.
	 * 
	 * @param shipPoss: Posicion de la nave dentro de la hilera.
	 */
	public void setShipPoss(int shipPoss) {
		this.shipPos = shipPoss;
	}

	/**
	 * Getter: Obtiene un valor si la nave es jefe o no.
	 * 
	 * @return Boolean: Verdadero o falso, dependiendo si la nave es jefe o no.
	 */
	public boolean getIsBoss() {
		return isBoss;
	}

	/**
	 * Setter: Establece si la nave es jefe o no.
	 * 
	 * @param isBoss: Verdadero o falso, dependiendo si la nave es jefe o no.
	 */
	public void setIsBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	/**
	 * Getter: Obtiene la cantidad de disparos que se requieren para destruir al
	 * jefe.
	 * 
	 * @return int shootsRequired: Cantidad de disparos requeridos para eliminar al
	 *         jefe.
	 */
	public int getShootsRequired() {
		return shootsRequired;
	}

	/**
	 * Setter: Establece la cantidad de disparos que se requieren para eliminar el
	 * jefe.
	 * 
	 * @param shootsRequired : Cantidad de disparos para eliminar el jefe.
	 */
	public void setShootsRequired(int shootsRequired) {
		this.shootsRequired = shootsRequired;
	}

	/**
	 * Setter: Establece de manera aleatoria la cantidad de disparos que se
	 * requieren para eliminar el jefe.
	 */
	public void setShootsRequired() {
		this.shootsRequired = 2 + (int) (Math.random() * ((5 - 2) + 1));
	}

	/**
	 * Getter: Obtiene los disparos recibidos por el jefe.
	 * 
	 * @return int shootsReceived: Disparos que ha recibido el jefe.
	 */
	public int getShootsReceived() {
		return shootsReceived;
	}

	/**
	 * Setter: Establece la cantidad de dispros recibidos por el jefe.
	 * 
	 * @param shootsReceived: Disparos recibidos por el jefe
	 */
	public void setShootsReceived(int shootsReceived) {
		this.shootsReceived = shootsReceived;
	}
}
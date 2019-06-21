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

	private int shipPoss;
	private boolean isBoss;
	private int shootsRequired;
	private int shootsReceived;

	/**
	 * Constructor de EnemyShip.
	 * 
	 * @param logo:     Imagen de la nave enemiga.
	 * @param coordX:   Coordenada en X de la nave enemiga.
	 * @param coordY:   Coordenada en Y de la nave enemiga.
	 * @param shipPoss: Posicion de la navde dentro de la hilera de naves enemigas.
	 */
	public EnemyShip(String logo, double coordX, double coordY, int shipPoss) {
		this.logo = new Image(logo);
		this.coordX = coordX;
		this.coordY = coordY;
		this.shipPoss = shipPoss;
		this.isBoss = false;
	}

	/**
	 * 
	 * @return
	 */
	public int getShipPoss() {
		return shipPoss;
	}

	/**
	 * 
	 * @param shipPoss
	 */
	public void setShipPoss(int shipPoss) {
		this.shipPoss = shipPoss;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getIsBoss() {
		return isBoss;
	}

	/**
	 * 
	 * @param isBoss
	 */
	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	/**
	 * 
	 * @return
	 */
	public int getShootsRequired() {
		return shootsRequired;
	}

	/**
	 * 
	 * @param shootsRequired
	 */
	public void setShootsRequired(int shootsRequired) {
		this.shootsRequired = shootsRequired;
	}

	/**
	 * 
	 */
	public void setShootsRequired() {
		this.shootsRequired = 2 + (int) (Math.random() * ((5 - 2) + 1));
	}

	/**
	 * 
	 * @return
	 */
	public int getShootsReceived() {
		return shootsReceived;
	}

	/**
	 * 
	 * @param shootsReceived
	 */
	public void setShootsReceived(int shootsReceived) {
		this.shootsReceived = shootsReceived;
	}
}
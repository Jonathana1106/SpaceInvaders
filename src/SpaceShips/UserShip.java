/**
 * 
 */
package SpaceShips;

import javafx.scene.image.Image;

/**
 * UserShip: Clase de la nave controlada por el usuario.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public class UserShip extends Ship {

	/**
	 * Constructor de UserShip.
	 * 
	 * @param logo:   Imagen de la nave del usuario.
	 * @param coordX: Coordenada en X de la nave del usuario.
	 * @param coordY: Coordenada en Y de la nave del usuario.
	 */
	public UserShip(String logo, double coordX, double coordY) {
		this.logo = new Image(logo);
		this.coordX = coordX;
		this.coordY = coordY;
	}
}
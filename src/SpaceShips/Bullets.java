/**
 * 
 */
package SpaceShips;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Bullets: Clase que se encarga de darle las propiedades necesarias a los
 * disparos de la nave del usuario.
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public class Bullets {

	/**
	 * Variables de la clase.
	 */
	private Ellipse bullet;
	private double coordX;
	private double coordY;
	private final double height = 12;
	private final double width = 2;

	/**
	 * Contructor de las balas.
	 * 
	 * @param coordX: Coordenada X de la bala.
	 * @param coordY: coordenada Y de la bala.
	 */
	public Bullets(double coordX, double coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.bullet = new Ellipse(this.coordX, this.coordY, width, height);
		this.bullet.setFill(Color.ALICEBLUE);
	}

	/**
	 * Metodo que se encarga de eliminar las balas del usuario
	 */
	public void deleteBullets() {
		bullet.setVisible(false);
	}

	/**
	 * Getter: Obtiene las balas del usuario.
	 * 
	 * @return bullets : Figura de las balas.
	 */
	public Ellipse getBullets() {
		return bullet;
	}

	/**
	 * Setter: Establece las balas del usuario.
	 * 
	 * @param bullets: Figura de las balas.
	 */
	public void setBullets(Ellipse bullets) {
		this.bullet = bullets;
	}

	/**
	 * Getter: Obtiene los limites de las balas del usuario.
	 * 
	 * @return Rectangle2D: Rectangulo en el que se encuentra la bala.
	 */
	public Rectangle2D getBounds() {
		return new Rectangle2D(coordX, coordY, width, height);
	}

	/**
	 * Metodo que se encarga de confirmas si los limites de una bala se intersecan
	 * con los de una nave enemiga.
	 * 
	 * @param enemyShip : Nave enemiga.
	 * @return Boolean: Verdadero o falso dependiendo si interseca.
	 */
	public boolean intersects(EnemyShip enemyShip) {
		return enemyShip.getBounds().intersects(this.getBounds());
	}

	/**
	 * Getter: Obtiene la coordenada en X de la bala.
	 * 
	 * @return coordX: Coordenada X de la bala.
	 */
	public double getCoordX() {
		return coordX;
	}

	/**
	 * Setter: Establece la coordenada X de la bala.
	 * 
	 * @param coordX : Coordenada X de la bala.
	 */
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	/**
	 * Getter: Obtiene la coordenada Y de la bala.
	 * 
	 * @return coordY: Coordenada Y de la bala.
	 */
	public double getCoordY() {
		return coordY;
	}

	/**
	 * Setter: Establece la coordenada Y de la bala.
	 * 
	 * @param coordY: Coordenada Y de la bala.
	 */
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}
}
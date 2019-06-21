/**
 * 
 */
package SpaceShips;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Ship: Clase que se encarga de establecer los requerimientos necesarios de una
 * nave cualquiera (enemiga o usuario).
 * 
 * @author Jonathan
 * @version 3.0
 *
 */
public abstract class Ship {

	/**
	 * Variables de la clase.
	 */
	protected Image logo;
	protected double coordX;
	protected double coordY;

	/**
	 * Getter: Obtiene la coordenada X de la nave.
	 * 
	 * @return coordX: Coordenada X de la nave.
	 */
	public double getCoordX() {
		return coordX;
	}

	/**
	 * Setter: Establece la coordenada X de la Nave.
	 * 
	 * @param coordX: Coordenada X de la nave.
	 */
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	/**
	 * Getter: Obtiene la coordenada Y de la nave.
	 * 
	 * @return coordY: Coordenada Y de la nave.
	 */
	public double getCoordY() {
		return coordY;
	}

	/**
	 * Setter: Establece la coordenada Y de la nave.
	 * 
	 * @param coordY: Coordenada Y de la nave.
	 */
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	/**
	 * Getter: Obtiene la imagen (logo) de la nave.
	 * 
	 * @return Image logo: Imagen de la nave.
	 */
	public Image getLogo() {
		return logo;
	}

	/**
	 * Setter: Establece la imagen (logo) de la nave.
	 * 
	 * @param logo: Imagen de la nave.
	 */
	public void setLogo(Image logo) {
		this.logo = logo;
	}

	/**
	 * Metodo que se encarga de dibujar una nave dentro de GraphicsContext.
	 * 
	 * @param graphics: GraphicsContext.
	 */
	public void render(GraphicsContext graphics) {
		graphics.drawImage(logo, coordX, coordY, 75, 75);
	}

	/**
	 * Metodo que se encarga de obtener los limintes de la nave.
	 * 
	 * @return Rectangle2D: Rectangulo en el que se encuentra la nave.
	 */
	public Rectangle2D getBounds() {
		return new Rectangle2D(coordX, coordY, 70, 75);
	}

	/**
	 * Metodo que se encarga de determinar si la nave y otro objeto estan
	 * compartiendo el mismo espacio, lo que es igual a que se estan intersecando.
	 * 
	 * @param enemyShip: Nave enemiga.
	 * @return Boolean: Verdadero o falso si esta intersecando o no.
	 */
	public boolean intersects(EnemyShip enemyShip) {
		return enemyShip.getBounds().intersects(getBounds());
	}
}
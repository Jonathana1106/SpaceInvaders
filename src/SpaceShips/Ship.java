package SpaceShips;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Ship: Clase que se encarga de establecer los requerimientos necesarios de una
 * nave cualquiera (enemiga o usuario).
 * 
 * @author Jonathan
 *
 */
public abstract class Ship {

	protected Image logo;
	protected double coordX;
	protected double coordY;

	/**
	 * 
	 * @return
	 */
	public double getCoordX() {
		return coordX;
	}

	/**
	 * 
	 * @param coordX
	 */
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}

	/**
	 * 
	 * @return
	 */
	public double getCoordY() {
		return coordY;
	}

	/**
	 * 
	 * @param coordY
	 */
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}

	/**
	 * 
	 * @return
	 */
	public Image getLogo() {
		return logo;
	}

	/**
	 * 
	 * @param logo
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
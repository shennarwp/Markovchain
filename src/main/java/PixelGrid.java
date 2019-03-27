import java.awt.*;
import javax.swing.*;

/**
 * this class is used for representing the drop in the 10x10 grid
 *
 * @author Shenna RWP
 * @author Agra Bimantara
 */
public class PixelGrid extends JFrame
{
	private Color fill;
	private Diffusion diffusion;

	/**
	 * constructor
	 */
	PixelGrid() {
		fill = new Color(0.0f, 0.0f, 0.0f, 0.1f);
		diffusion = new Diffusion();
		setTitle("Diffusion process, start at: [" +
				 diffusion.getCurrentRow() + ", " +
				 diffusion.getCurrentColumn() +"]");
		setSize(600, 580);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
	}

	/**
	 * function to represent the diffusion process of the drop / tropfen
	 * @param g this graphic
	 */
	public void paint(Graphics g) {
		//draw the 10x10 initial grid
		for (int x = 50; x < 550; x += 50)
			for (int y = 50; y < 550; y += 50)
				g.drawRect(x, y, 50, 50);

		//indicating where the current drop is
		int currentX;
		int currentY;
		g.setColor(fill);
		try {
			//recalculate the position every 60ms, draw it on the grid
			for (int i = 0; i < 100; i++) {
				Thread.sleep(60);
				currentX = ((diffusion.getCurrentColumn() - 1) * 50) + 50;
				currentY = ((diffusion.getCurrentRow() - 1) * 50) + 50;
				g.fillRect(currentX, currentY, 50, 50);
				diffusion.calculatePosition();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
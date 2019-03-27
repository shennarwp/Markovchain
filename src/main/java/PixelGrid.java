import java.awt.*;
import javax.swing.*;
public class PixelGrid extends JFrame
{
	private Color fill;
	private Diffusion diffusion;

	PixelGrid() {
		fill = new Color(0.0f, 0.0f, 0.0f, 0.1f);
		diffusion = new Diffusion();
		setSize(600, 580);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
	}

	public void paint(Graphics g) {
		for (int x = 50; x < 550; x += 50)
			for (int y = 50; y < 550; y += 50)
				g.drawRect(x, y, 50, 50);

		int currentX;
		int currentY;
		g.setColor(fill);
		try {
			for (int i = 0; i < 1000; i++) {
				Thread.sleep(70);
				currentX = ((diffusion.getCurrentColumn() - 1) * 50) + 50;
				currentY = ((diffusion.getCurrentRow() - 1) * 50) + 50;
				g.fillRect(currentX, currentY, 50, 50);
				diffusion.calculatePosition();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PixelGrid application = new PixelGrid();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
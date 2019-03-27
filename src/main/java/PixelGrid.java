import java.awt.*;
import javax.swing.*;
public class PixelGrid extends JFrame
{
	Color fill;
	Diffusion diffusion;
	pos currentPosition;
	int x = 0;
	int y = 0;

	public PixelGrid() {
		setSize(600, 580);
		setVisible(true);
		fill = new Color(0.0f, 0.0f, 0.0f, 0.1f);
		diffusion = new Diffusion();
		currentPosition = new pos();
		add(currentPosition);
		//pack();
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
	}


	public void paint(Graphics g) {
		for (int x = 50; x < 550; x += 50)
			for (int y = 50; y < 550; y += 50)
				g.drawRect(x, y, 50, 50);

//		currentPosition.repaint();
		int currentX;
		int currentY;
		g.setColor(fill);
		//g.fillRect(currentX, currentY, 50, 50);
		try {
			for (int i = 0; i < 1000; i++) {
				Thread.sleep(50);

				currentX = ((diffusion.getCurrentColumn() - 1) * 50) + 50;
				currentY = ((diffusion.getCurrentRow() - 1) * 50) + 50;
				g.fillRect(currentX, currentY, 50, 50);
				diffusion.calculatePosition();
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}
	public static void main( String args[] ) {
		PixelGrid application = new PixelGrid();
		//application.add(currentPosition)
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}


	private class pos extends JPanel {
		private int SIZE = 30;

		public pos() {
			super.setPreferredSize(new Dimension(SIZE, SIZE));
			setSize(SIZE, SIZE);
			setLocationRelativeTo(null);
			setLayout(null);
			setVisible(true);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.setColor(Color.RED);
			//g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.fillRect(x+10, y+10, SIZE, SIZE);
			setSize(SIZE, SIZE);
			setLocationRelativeTo(null);
			setLayout(null);
			setVisible(true);
		}
	}
}
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;

import java.util.Random;


public class Diffusion
{
	//private static int startColumn = (int)(Math.random() * ((10 - 1) + 1)) + 1;
	private Random random;
	@Getter
	private int currentRow;
	@Getter
	private int currentColumn;
	private boolean LEFTEDGE = currentColumn == 1;
	private boolean RIGHTEDGE = currentColumn == 10;
	private boolean BOTTOM = currentRow == 10;
	//private boolean BOTTOMLEFTEDGE = LEFTEDGE && currentRow == 10;
	//private boolean BOTTOMRIGHTEDGE = RIGHTEDGE && currentRow == 10;


	public Diffusion() {
		currentColumn = (int)(Math.random() * ((10 - 1) + 1)) + 1;
		currentRow = 1;
		random = new Random();
	}

	public void calculatePosition() {
		if (currentColumn == 1) {							//BOTTOM
			if (currentRow == 10) {							//BOTTOM + LEFT EDGE
				if (random.nextInt(2) == 1)
					currentColumn += 1;                 		//GO TO THE RIGHT
			} else {										//LEFT EDGE ONLY
				int move = random.nextInt(3);
				if (move == 1)
					currentColumn += 1;                         //GO TO THE RIGHT
				if (move == 2)
					currentRow += 1;                            //GO DOWN
			}
		} else if (currentColumn == 10) {					//BOTTOM
			if (currentRow == 10) {							//BOTTOM + RIGHT EDGE
				if (random.nextInt(2) == 1)
					currentColumn -= 1;                 		//GO TO THE LEFT
			} else {
				int move = random.nextInt(3);        //LEFT EDGE ONLY
				if (move == 1)
					currentColumn -= 1;                         //GO TO THE LEFT
				if (move == 2)
					currentRow += 1;                            //GO DOWN
			}
		} else if (currentRow == 10) {                     //BOTTOM + MIDDLE
			if (random.nextInt(2) == 0)
				currentColumn -= 1;                        	   //GO TO THE LEFT
			if (random.nextInt(2) == 1)
				currentColumn += 1;                            //GO TO THE RIGHT
		} else {
			int move = random.nextInt(4);          //IN THE MIDDLE
			if (move == 1)
				currentColumn -= 1;                            //GO TO THE LEFT
			if (move == 2)
				currentRow += 1;                               //GO DOWN
			if (move == 3)
				currentColumn += 1;                            //GO TO THE RIGHT
		}

	}


	public static void main(String[] args) {
//		for(int i=0; i<20; i++ )
//			System.out.println((int)(Math.random() * ((10 - 1) + 1)) + 1);
		Diffusion d = new Diffusion();
		for(int i=0; i<20; i++ )
			System.out.println(d.random.nextInt(4));
	}
}

import lombok.Getter;

import java.util.Random;


class Diffusion
{
	private Random random;
	@Getter
	private int currentRow;
	@Getter
	private int currentColumn;


	Diffusion() {
		currentColumn = (int)(Math.random() * ((10 - 1) + 1)) + 1;
		currentRow = 1;
		random = new Random();
	}

	void calculatePosition() {
		if (currentColumn == 1) {							//LEFT EDGE
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
		} else if (currentColumn == 10) {					//RIGHT EDGE
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
			if (random.nextInt(3) == 1)
				currentColumn -= 1;                        	   //GO TO THE LEFT
			if (random.nextInt(3) == 2)
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
}

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.Scanner;

/**
 * class for demonstrating the stationary markov chain with help of transition matrix
 *
 * @author Shenna RWP
 * @author Agra Bimantara
 */
public class Markov
{
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * this function will calculate probabilities of each state after multiple runs
	 * @param transitionMatrix matrix containing the probability of transitioning between states.
	 * @param startState indicates which state is the first
	 * @param dimension dimension of the matrix / number of states
	 * @param run array containing how many times will the probabilities be calculated
	 */
	private void calculateProbability(RealMatrix transitionMatrix, int startState, int dimension, int[] run) {
		//create the start state as Vector
		double[] startArray 		= new double[dimension];
		startArray[startState-1] 	= 1;
		RealVector start 			= new ArrayRealVector(startArray);

		RealVector endVector 		= new ArrayRealVector(dimension);	//holding the end result
		for(Integer r : run) {
			RealVector startVector = start.copy();						//copy the start vector for each run
			for (int i = 0; i < r; i++) {
				endVector.mapMultiplyToSelf(0);							//reset the result
				for (int j = 0; j < dimension; j++) {					//dot product between start and column in transition matrix
					endVector.addToEntry(j, startVector.dotProduct(transitionMatrix.getColumnVector(j)));
				}
				startVector = endVector.copy();							//copy the result to start for next loop
			}
			System.out.println("n = " + r + "\t\tP = " + endVector);
		}
		System.out.println("startvector: " + start);

	}

	/**
	 * this function will calculate the probabilities based on transition matrix in Aufgabe 2
	 */
	private void runTestSpiel() {
		int DIMENSION 				= 6;
		int[] RUN 					= new int[] {1000};
		RealMatrix transitionMatrix = new Array2DRowRealMatrix(
				new double[][]{{1.0,	 0.0, 	   0.0, 	0.0,	 0.0,  	  0.0	 },
							   {5.0/6.0, 0.0,	   0.0,		1.0/6.0, 0.0, 	  0.0	 },
							   {0.0, 	 5.0/6.0,  0.0, 	0.0,	 1.0/6.0, 0.0	 },
							   {0.0, 	 0.0,      5.0/6.0, 0.0,	 0.0, 	  1.0/6.0},
							   {0.0, 	 0.0, 	   0.0, 	0.0,	 1.0, 	  0.0	 },
							   {0.0, 	 0.0, 	   0.0, 	0.0, 	 0.0, 	  1.0	 }}
				, false
		);

		System.out.println("calculation of probabilities after 1000 runs for each state as start:");
		//Startguthaben als Zustände, startguthaben = i-1
		int[] starts = new int[] {1,2,3,4,5,6};
		for(Integer i : starts)
			calculateProbability(transitionMatrix, i, DIMENSION, RUN);
	}

	/**
	 * this function will calculate the probabilities based on transition matrix in Aufgabe 1
	 * the probabilities will be calculated 3 times, each with different start vector
	 */
	private void runTestLeitung() {
		int DIMENSION 				= 3;
		int[] RUN 					= new int[] {1, 2, 3, 10, 20, 30, 100, 200, 300};
		RealMatrix transitionMatrix = new Array2DRowRealMatrix(
				new double[][]{{0.5,  0.5, 0.0 },
							   {0.25, 0.5, 0.25},
							   {0.25, 0.0, 0.75}}
				, false
		);

		//i = 1 Leitung frei, i = 2 Leitung besetzt, i = 3 Leitung in Wartung
		int[] starts = new int[] {1,2,3};
		for(Integer i : starts)
			calculateProbability(transitionMatrix, i, DIMENSION, RUN);
	}

	/**
	 * calculate the probabilities based on transition matrix input by user
	 */
	private void runInputMatrix() {
		System.out.println("number of matrix dimension:");
		int DIMENSION 				= scanner.nextInt();
		double[][] matrixArray 		= new double[DIMENSION][DIMENSION];
		enterMatrixData(matrixArray, DIMENSION);
		RealMatrix transitionMatrix = new Array2DRowRealMatrix(matrixArray, false);

		//selecting the start state / element
		int startElement = -1;
		while(startElement < 1 || startElement > DIMENSION) {
			System.out.println("select start element: 1 - " + DIMENSION);
			startElement = scanner.nextInt();
		}

		int[] RUN = new int[] {1, 2, 3, 10, 20, 30, 100, 200, 300};
		calculateProbability(transitionMatrix, startElement, DIMENSION, RUN);
	}

	/**
	 * read double input by user to be added into transition matrix
	 * @param matrixArray the matrix to be inputted
	 * @param dimension the dimension of the matrix
	 */
	private void enterMatrixData(double[][] matrixArray, int dimension){
		System.out.println("Enter Matrix Data");
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++)
				matrixArray[i][j] = scanner.nextDouble();
	}

	/**
	 * switch and run between multiple functions in this class
	 * @param func selected option by user
	 */
	private void runFunc(int func) {
		switch(func) {
			case 1 :
				runTestLeitung();
				System.out.println("----------------------------------------------\n");
				break;
			case 2 :
				runInputMatrix();
				System.out.println("----------------------------------------------\n");
				break;
			case 3 :
				runTestSpiel();
				System.out.println("----------------------------------------------\n");
				break;
			case 0 :
				System.out.println("program end.");
				System.out.println("----------------------------------------------\n");
				System.exit(0);
				break;
			default:
				System.out.println("Not an option!");
				System.out.println("----------------------------------------------\n");
				break;
		}
	}

	/**
	 * print menu
	 */
	private void start() {
		int func = -1;
		while(func != 0) {
			System.out.println("What to do?");
			System.out.println("1. Run transition matrix from aufgabe 1 (Leitung)");
			System.out.println("2. Enter custom matrix");
			System.out.println("3. Run transition matrix from aufgabe 2 (Würfelspiel)");
			System.out.println("0. end");
			System.out.println("----------------------------------------------\n");
			func = scanner.nextInt();
			runFunc(func);
		}
	}

	/**
	 * main function
	 * @param args null
	 */
	public static void main(String[] args) {
		//new Markov().start();
		new Markov().runTestSpiel();
	}
}

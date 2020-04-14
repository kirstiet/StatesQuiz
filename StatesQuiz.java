import java.io.File;
import java.util.Scanner;

public class StatesQuiz {

	public static void main(String[] args) throws Exception {
		System.out.println("Do you want to test your knowledge of the capitals of the United States?" 
			+ "\nType in \"Yes\" or \"Y\" to test your knowledge.");
		String userResponseToStart = userInput();

		if(userResponseToStart.equalsIgnoreCase("yes") || userResponseToStart.equalsIgnoreCase("y")) {
			System.out.println("Do you want to review first?" 
				+ "\nType in \"Yes\" or \"Y\" if you would like to review all 50 states.");
			String userResponseReview = userInput();

			if(userResponseReview.equalsIgnoreCase("yes") || userResponseReview.equalsIgnoreCase("y")) {
				System.out.println("Do you want to review the capitals or the states?");
				String selectStateOrCapital = userInput();

				if(selectStateOrCapital.equalsIgnoreCase("states")) {
					reviewStates();

				} else if(selectStateOrCapital.equalsIgnoreCase("capitals")) {
					reviewCapitals();

				} else {
					System.out.println("You did not select states or capitals.");
				}

			} else {
				System.out.println("Okay, let's jump into the quiz!" 
					+ "\nHow many states or capitals do you want to test?"
					+ "\nChoose a number from 1-50.");

				try {
					int userResponseNumber = Integer.parseInt(userInput());

					while (userResponseNumber >= 51) {
						System.out.println("Please select a number less than 50."
						+ "\nHow many states do you want to test? Please select a number between 1-50.");
						userResponseNumber = Integer.parseInt(userInput());

				}

				int quizPointCounter = 0;
				long startQuizTime = System.currentTimeMillis();
				for(int i = 0 ; i < userResponseNumber ; i++) {
					int randomNumber= (int)(Math.random() * 50);
					quizPointCounter = quizPointCounter + quizQuestion(randomNumber);

				}

				long endQuizTime = System.currentTimeMillis();
				float totalQuizTimeElapsed = ((float)(int)(endQuizTime - startQuizTime))/1000;
				System.out.println("Total Points: " + quizPointCounter
					+ "\nTotal Time: " + totalQuizTimeElapsed);


				} catch (Exception integerError) {
					System.out.println("You did not enter a number between 1-50.");

				}

			}

		} else {
			System.out.println("You did not want to play. Closing program.");
			System.exit(0);

		}

	}


	public static String userInput() {
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();

		return userInput;

	}	

	public static String[] fileOpener(String filename) {
		String[] array = new String[50];

		try {
			File inFile = new File(filename);
			Scanner fileReader = new Scanner(inFile);

			while(fileReader.hasNextLine()) {
				for(int i = 0 ; i < array.length ; i++) {
					array[i] = fileReader.nextLine();

				}

			}

			fileReader.close();

		} catch (Exception fileError) {
			System.out.println("File cannot be found.");

		}

		return array;

	}

	public static int quizQuestion(int randomNumber) {
		String[] statesArray = fileOpener("states.txt");
		String[] capitalsArray = fileOpener("capitals.txt");
		int quizPointEarned = 0;

		System.out.println("What is the capital of " + statesArray[randomNumber] + "?");
		String userResponse = userInput();

		if(userResponse.equalsIgnoreCase(capitalsArray[randomNumber])) {
			System.out.println("Correct! You get one point!" + "\n");
			quizPointEarned = 1;

		} else {
			System.out.println("Your answer was wrong. You do not get a point." + "\n");
			quizPointEarned = 0;

		}

		return quizPointEarned;

	}

	public static int reviewStates() {
		String[] statesArray = fileOpener("states.txt");
		String[] capitalsArray = fileOpener("capitals.txt");
		int reviewPointCounter = 0;

		System.out.println("Let's review the states and their capitals.");

		long startReviewTime = System.currentTimeMillis();
		for(int i = 0 ; i < statesArray.length ; i++ ) {
			System.out.println("\nWhat is the capital of " + statesArray[i] + "?");
			String userResponse = userInput();

			if(userResponse.equalsIgnoreCase(capitalsArray[i])) {
				reviewPointCounter++;

			} else {
				System.out.println("That is not correct." 
					+ "\nThe correct answer is " + capitalsArray[i]);

			}

		}
		long endReviewTime = System.currentTimeMillis();
		float totalReviewTimeElapsed = ((float)(int)(endReviewTime - startReviewTime))/1000;

		System.out.println("\nYou got " + reviewPointCounter + " correct out of 50." 
			+ "\nThe total time elapsed was: " + totalReviewTimeElapsed + " seconds.");
		return reviewPointCounter;

	}


	public static int reviewCapitals() {
		String[] statesArray = fileOpener("states.txt");
		String[] capitalsArray = fileOpener("capitals.txt");
		int reviewPointCounter = 0;

		System.out.println("Let's review the states and their capitals.");

		long startReviewTime = System.currentTimeMillis();
		for(int i = 0 ; i < capitalsArray.length ; i++ ) {
			System.out.println("\nWhich state has the capital of " + capitalsArray[i] + "?");
			String userResponse = userInput();

			if(userResponse.equalsIgnoreCase(statesArray[i])) {
				reviewPointCounter++;

			} else {
				System.out.println("That is not correct." 
					+ "\nThe correct answer is " + statesArray[i]);

			}

		}
		long endReviewTime = System.currentTimeMillis();
		float totalReviewTimeElapsed = ((float)(int)(endReviewTime - startReviewTime))/1000;

		System.out.println("\nYou got " + reviewPointCounter + " correct out of 50." 
			+ "\nThe total time elapsed was: " + totalReviewTimeElapsed + " seconds.");
		return reviewPointCounter;

	}

}
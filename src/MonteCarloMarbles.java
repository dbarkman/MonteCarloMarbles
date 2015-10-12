import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by david on 10/11/15.
 */
public class MonteCarloMarbles {

	static boolean allPicksMatch;
	static Random randomGenerator = new Random();
	static int numberOfMarbles = 6;

	public static void main(String[] args) {

		pickTheMarbles(10);
		pickTheMarbles(100);
		pickTheMarbles(1000);
		pickTheMarbles(10000);
		pickTheMarbles(100000);
		pickTheMarbles(1000000);
		pickTheMarbles(10000000);
	}

	private static void pickTheMarbles(int attempts) {
		int successes = 0;
		for (int i = 0; i < attempts; i++) {
			ArrayList<String> bowel = new ArrayList<String>();
			addMarbles(bowel, "blue");
			addMarbles(bowel, "white");
			ArrayList<String> picks = new ArrayList<String>();

			for (int j = 0; j < (numberOfMarbles / 2); j++) {
				int number = randomGenerator.nextInt(bowel.size());
				picks.add(bowel.get(number));
				bowel.remove(number);
			}

			checkPicks(picks);
			if (allPicksMatch == true) successes++;
		}

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		System.out.println(numberFormat.format(attempts) + " attempts with " + numberFormat.format(successes) + " successes.");
		int successRate = (successes * 100) / attempts;
		System.out.println("Success rate: " + successRate + "%");
	}

	private static void addMarbles(ArrayList bowel, String color) {
		for (int i = 0; i < (numberOfMarbles / 2); i++) {
			bowel.add(color);
		}
	}

	private static void checkPicks(ArrayList picks) {
		if (picks.size() == 1) {
			allPicksMatch = true;
			return;
		}

		if (picks.get(0).equals(picks.get(1))) {
			picks.remove(0);
			checkPicks(picks);
		} else {
			allPicksMatch = false;
			return;
		}
	}
}

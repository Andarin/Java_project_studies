package monopolKonkurrenz;

import java.util.Random;

public class Simulation {
	
	private static double wage = 0.5;

	private static double averagePrice;

	private static double aggregateDemand = 1;
	
	final private static double theta = 1; // Achtung! fuer theta kleiner 1 muss die Preissetzung der Firmen veraendert werden
	final private static int numberCompanies = 10;
	
	private static Firma[] list;

	public static void main(String[] args) {
		
		// Standards of the beginning
		double workHoursPerCompany = 2;
		double epsilon = 2;
		double technology = 1;
		
		createListOfCompanies(workHoursPerCompany, epsilon, technology);
		
		for (int period = 1; period < 2; period++ ) {
			calcAveragePrice();
			makeAveragePricePublicToCompanies();
			
			int[] chosenOnes = appointCompaniesWhichCanChooseTheirPrices();
			for (int i = 0; i < chosenOnes.length; i++) {
				list[chosenOnes[i]].setNewPrice();
			}
			
			calcAveragePrice();
			makeAveragePricePublicToCompanies();
			
			calculateProfits();
			printProfits();
		}
		
	}

	private static void printProfits() {
		for (int i = 0; i < numberCompanies; i++) {
			System.out.print(i+": ");
			System.out.println(list[i].getProfit());
		}	
	}

	private static void calculateProfits() {
		for (int i = 0; i < numberCompanies; i++) {
			list[i].calcProfit();
		}
	}

	private static int[] appointCompaniesWhichCanChooseTheirPrices() {
		
		// erzeugt Array mit laufender Zahl von 1 bis Anzahl Firmen
		int[] numbers = new int[numberCompanies];
		for (int i = 0; i < numberCompanies; i++) { numbers[i] = i; }
		
		numbers = shuffle(numbers);
		
		int numberOfCompaniesChoosingTheirPrice = (int)(theta*numberCompanies + 0.99);
		int[] chosenOnes = new int[numberOfCompaniesChoosingTheirPrice];
		for (int i = 0; i < numberOfCompaniesChoosingTheirPrice; i++) {
			chosenOnes[i] = numbers[i];
		}
		
		return chosenOnes;
	}

	private static int[] shuffle(int[] numbers) {
		for (int i = 0; i < numbers.length; i++ ) {
			   int r = i + (int) (Math.random() * (numbers.length - i));
			   int temp = numbers[r];
			   numbers[r] = numbers[i];
			   numbers[i] = temp;
		}
		return numbers;
	}

	private static void makeAveragePricePublicToCompanies() {
		for (int i = 0; i < numberCompanies; i++) {
			list[i].setAveragePrice(averagePrice);
		}
	}

	private static void calcAveragePrice() {
		
		double sum = 0;		
		for (int i = 0; i < numberCompanies; i++) {
			sum += list[i].getPrice();
		}
		averagePrice = sum / numberCompanies;
	}

	private static void createListOfCompanies(double workHoursPerCompany, double epsilon, double technology) {
		list = new Firma[numberCompanies];
		for (int i = 0; i < numberCompanies; i++) {
			Firma f = new Firma(workHoursPerCompany, epsilon, technology, wage, aggregateDemand);
			list[i] = f;
		}
	}

}

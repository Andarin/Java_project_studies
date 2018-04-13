package monopolKonkurrenz;

public class Firma {

	private double workHours, epsilon, technology, wage, output, profit,
					price = 1, averagePrice, aggregateDemand;
	
	public Firma (double workHours, double epsilon, double technology, 
					double wage, double aggregateDemand) {
		this.workHours = workHours;
		this.epsilon = epsilon;
		this.technology = technology;
		this.wage = wage;
		this.aggregateDemand = aggregateDemand;
	}
	
	public double getPrice () {	return price; }
	
	public double getProfit () { return profit; }

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
		
	}

	public void setNewPrice() {
		double mc = wage / technology;
		price = mc * epsilon / (epsilon - 1);
	}

	public void calcProfit() {
		double y = Math.pow(price / averagePrice, -epsilon) * aggregateDemand;
		profit = y * price - wage*workHours;
	}
}

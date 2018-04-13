package de.hu_berlin.andarin_frank_MarktPlot;

import gdp.stdlib.StdDraw;

public class MarktPlot {
	
	final double top_border = 1000000;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String supplyFunctionString = get(FunctionType.supply);
		IFunction supplyFunction = new ConcreteFunction(supplyFunctionString);
		
		String demandFunctionString = get(FunctionType.demand);
		IFunction demandFunction = new ConcreteFunction(demandFunctionString);		
		
		
		double saturation = demandFunction.valueAt(0); // maybe NaN
		
		double price;
		for (price = 0.001; demandFunction.valueAt(price) > 0 && price < top_border; price *= 2) {}
		if (price > top_border);

	}

}

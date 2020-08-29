package com.sapient.revenue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.text.DecimalFormat;

public class Application {
	
	private static DecimalFormat df = new DecimalFormat("#.###");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	     countryRevenues();		

	}
	
	public static List<CountryRevenue> countryRevenues(){
		Scanner scanner = null;
		FileInputStream stream = null;
		try {
			stream = new FileInputStream("E:\\courses\\jsp\\revenue\\src\\main\\resources\\input.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner = new Scanner(stream);
		scanner.nextLine();
		String string = null;
		List<Revenue> revenues = new ArrayList<>();
		
		while(scanner.hasNextLine()) {
			String[] strings =scanner.nextLine().split(",");
			Revenue revenue = new Revenue();
			revenue.setCity(strings[0].trim());
			revenue.setCountry(strings[1].trim());
			revenue.setGender(strings[2].trim());
			revenue.setCurrency(strings[3].trim());
			revenue.setIncome(Integer.parseInt(strings[4].trim()));
			revenues.add(revenue);
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,List<Revenue> > countryRevenues = new HashMap<>();
		for(Revenue revenue: revenues) {	
		   if(revenue.getCountry()!="") {	
		   if(countryRevenues.containsKey(revenue.getCountry()+revenue.getGender()))
			  countryRevenues.get(revenue.getCountry()+revenue.getGender()).add(revenue);
		   else {
			   List<Revenue> temp = new ArrayList<>();
			   temp.add(revenue);
			  countryRevenues.put(revenue.getCountry()+revenue.getGender(), temp ); 
		   }
		   }else {
			   if(countryRevenues.containsKey(revenue.getCity()+revenue.getGender()))
					  countryRevenues.get(revenue.getCity()+revenue.getGender()).add(revenue);
				   else {
					   List<Revenue> temp = new ArrayList<>();
					   temp.add(revenue);
					  countryRevenues.put(revenue.getCity()+revenue.getGender(), temp ); 
				   }   
		   
		   }
		}
		
		Map<String,Double> currencyConvert = new HashMap<>();
		currencyConvert.put("INR", 0.01515151515);
		currencyConvert.put("GBP", 1.49253731343);
		currencyConvert.put("SGP", 0.66666666666);
		currencyConvert.put("HKD", 0.125);
		currencyConvert.put("USD", 1.0);
		
		
		List<CountryRevenue> countryRevenuesList = new ArrayList<>();
		for(String code : countryRevenues.keySet()) {
			CountryRevenue countryRevenue = new CountryRevenue();
			double sum =0;
			for(Revenue revenue: countryRevenues.get(code)) {
				sum += revenue.getIncome();
			}
			sum /= countryRevenues.get(code).size();
			Revenue revenue = countryRevenues.get(code).get(0);
			if(revenue.getCountry().equals(""))
			countryRevenue.setCountry(revenue.getCity());
			else
			countryRevenue.setCountry(revenue.getCountry());	
			countryRevenue.setGender(revenue.getGender());
			countryRevenue.setRevenue(round(sum*(currencyConvert.get(revenue.getCurrency())),2));
			countryRevenuesList.add(countryRevenue);
		}
		
		FileOutputStream istream = null;
		string = "country/city,gender,revenue\n";
		try {
			 istream = new FileOutputStream("E:\\courses\\jsp\\revenue\\src\\main\\resources\\output.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			istream.write(string.getBytes());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(CountryRevenue revenue: countryRevenuesList) {
	         try {
				istream.write(revenue.toString().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			istream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countryRevenuesList;
		
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}

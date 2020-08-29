package revenue;

import org.junit.Test;

import com.sapient.revenue.Application;
import com.sapient.revenue.CountryRevenue;
import com.sapient.revenue.Revenue;

import static org.junit.Assert.*;
import java.util.*;

public class TestApplication {

	
	@Test
	public void testRevenues() {
		List<CountryRevenue> revenues = Application.countryRevenues();
		assertNotNull(revenues);
		assertEquals(12,revenues.size());
		for(CountryRevenue revenue: revenues) {
			if(revenue.getCountry().equals("IND")&&revenue.getGender().equals("F"))
				assertEquals(Double.valueOf(2560.64),revenue.getRevenue());
			else if(revenue.getCountry().equals("IND")&&revenue.getGender().equals("M"))
				assertEquals(Double.valueOf(3660.36),revenue.getRevenue());
			else if(revenue.getCountry().equals("HONG KONG")&&revenue.getGender().equals("F"))
				assertEquals(Double.valueOf(22744.13),revenue.getRevenue());
			else if(revenue.getCountry().equals("HONG KONG")&&revenue.getGender().equals("M"))
				assertEquals(Double.valueOf(32118.75),revenue.getRevenue());
			else if(revenue.getCountry().equals("NEW DELHI")&&revenue.getGender().equals("F"))
				assertEquals(Double.valueOf(1718.68),revenue.getRevenue());
			else if(revenue.getCountry().equals("NEW DELHI")&&revenue.getGender().equals("M"))
				assertEquals(Double.valueOf(3652.19),revenue.getRevenue());
			else if(revenue.getCountry().equals("SINGAPORE")&&revenue.getGender().equals("F"))
				assertEquals(Double.valueOf(40228.67),revenue.getRevenue());
			else if(revenue.getCountry().equals("SINGAPORE")&&revenue.getGender().equals("M"))
				assertEquals(Double.valueOf(51240.00),revenue.getRevenue());
			else if(revenue.getCountry().equals("UK")&&revenue.getGender().equals("F")) {
				assertEquals(Double.valueOf(30516.42),revenue.getRevenue());
			}
			else if(revenue.getCountry().equals("UK")&&revenue.getGender().equals("M"))
				assertEquals(Double.valueOf(41091.04),revenue.getRevenue());
			else if(revenue.getCountry().equals("USA")&&revenue.getGender().equals("F"))
				assertEquals(Double.valueOf(53760.00),revenue.getRevenue());
			else if(revenue.getCountry().equals("USA")&&revenue.getGender().equals("M")) 
					assertEquals(Double.valueOf(66564.33),revenue.getRevenue());
			
		};

	
	}
}

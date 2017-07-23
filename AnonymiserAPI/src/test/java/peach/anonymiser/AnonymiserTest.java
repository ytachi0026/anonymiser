package peach.anonymiser;
import static org.junit.Assert.*;

import org.junit.Test;

import peach.anonymiser.bean.DATA_SET;

public class AnonymiserTest {

	@Test
	public void test() {
		boolean match = "ytalo.csvd".matches(".*.csv");
		assertTrue(match);
	}
	
	@Test
	public void identityCancerType() {
		DATA_SET cancerType = DATA_SET.valueOf("SACT");
		
		switch(cancerType) {
			case CWT: System.out.println("cwt");break;
			case COSD:System.out.println("cods");break;
			case SACT: System.out.println("sact");break;
		}
		
		assertTrue(true);
	}

}

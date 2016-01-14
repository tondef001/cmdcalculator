import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Tests {
	
	Main main;
	
	/**
	 * Clears the history before each test method.
	 */
	@Before
	public void setup(){
		main = new Main("clear");
	}
	
	@Test
	public void validAddTests(){
		main = new Main("add 3 4 1");
		assertEquals("Added Correctly", 8, main.getResult());
		assertTrue("Validcommand", main.prints());
		
		main = new Main("add 5 -3");
		assertEquals("Added Correctly", 2, main.getResult());
		assertTrue("Validcommand", main.prints());
		
		main = new Main("add 3");
		assertEquals("Added Correctly", 3, main.getResult());
		assertTrue("Validcommand", main.prints());
		
		main = new Main("add !1");
		assertEquals("Added Correctly", 8, main.getResult());
		assertTrue("Validcommand", main.prints());
	}
	
	@Test
	public void invalidAddTests(){
		main = new Main("add");
		assertFalse("Needs Arguments", main.prints());
		
		main = new Main("add !m");
		assertFalse("Invalid variable", main.prints());
		
		main = new Main("add !1000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("add 2000000000 2000000000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("add -2000000000 -2000000000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("add 100000000000000000 1");
		assertFalse("Greater than Max Integer", main.prints());
		
		main = new Main("add -10000000000000000 -1");
		assertFalse("Less than Min Integer", main.prints());
		
		main = new Main("add 10000000000000000");
		assertFalse("Integer greater than Max Integer", main.prints());
		
		assertEquals("No commands were added", 0, main.getHistory().size());
		main = new Main("clear");
		assertEquals("History is cleared", 0, main.getHistory().size());
	}
	
	@Test
	public void validSubTests(){
		main = new Main("sub 10 3 1");
		assertEquals("Subtracted correctly", 6, main.getResult());
		assertTrue("Valid command", main.prints());

		main = new Main("sub 10 7 3");
		assertEquals("Subtracted correctly", 0, main.getResult());
		assertTrue("Valid command", main.prints());

		main = new Main("sub 10 -5");
		assertEquals("Subtracted correctly", 15, main.getResult());
		assertTrue("Valid command", main.prints());

		main = new Main("sub 10 5 7");
		assertEquals("Subtracted correctly", -2, main.getResult());
		assertTrue("Valid command", main.prints());

		main = new Main("sub 3");
		assertEquals("Subtracted correctly", 3, main.getResult());
		assertTrue("Valid command", main.prints());

		main = new Main("sub 10 3 1");
		assertEquals("Subtracted correctly", 6, main.getResult());
		assertTrue("Valid command", main.prints());
	}
	
	@Test
	public void invalidSubTests(){
		main = new Main("sub");
		assertFalse("Needs Arguments", main.prints());
		
		main = new Main("sub !m");
		assertFalse("Invalid variable", main.prints());
		
		main = new Main("sub !1000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("sub -1000000000 2000000000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("sub 2000000000 -2000000000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("sub 100000000000000000 -1");
		assertFalse("Greater than Max Integer", main.prints());
		
		main = new Main("sub -10000000000000000 1");
		assertFalse("Less than Min Integer", main.prints());
		
		main = new Main("sub 10000000000000000");
		assertFalse("Integer greater than Max Integer", main.prints());
		
		assertEquals("No commands were added", 0, main.getHistory().size());
		main = new Main("clear");
		assertEquals("History is cleared", 0, main.getHistory().size());
	}
	
	@Test
	public void validMulTests(){
		main = new Main("mul 1 2 3");
		assertEquals("Multiplied correctly", 6, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("mul 2 -7");
		assertEquals("Multiplied correctly", -14, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("mul 10 5 0");
		assertEquals("Multiplied correctly", 0, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("mul !1");
		assertEquals("Multiplied correctly", 6, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("history");
		assertFalse("History restricts printing an integer result", main.prints());
		assertEquals("History has the correct number of commands", 4, main.getHistory().size());
		
		main = new Main("clear");
		assertEquals("History is cleared", 0, main.getHistory().size());
	}
	
	@Test
	public void invalidMulTests(){
		main = new Main("mul");
		assertFalse("Needs Arguments", main.prints());
		
		main = new Main("mul !m");
		assertFalse("Invalid variable", main.prints());
		
		main = new Main("mul !0");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("mul 10000000 10000000");
		assertFalse("Greater than Max Integer", main.prints());
		
		main = new Main("mul 10000000 -10000000");
		assertFalse("Less than Min Integer", main.prints());
		
		main = new Main("mul 10000000000000000");
		assertFalse("Integer greater than Max Integer", main.prints());
		
		assertEquals("No commands were added", 0, main.getHistory().size());
		main = new Main("clear");
		assertEquals("History is cleared", 0, main.getHistory().size());
	}
	
	@Test
	public void validDivTests(){
		main = new Main("div 30 3 2");
		assertEquals("Divided correctly", 5, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("div 20 3");
		assertEquals("Divided correctly", 6, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("div 14 -7");
		assertEquals("Divided correctly", -2, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("div !1");
		assertEquals("Divided correctly", 5, main.getResult());
		assertTrue("Valid command", main.prints());
		
		main = new Main("history");
		assertFalse("History restricts printing an integer result", main.prints());
		assertEquals("History has the correct number of commands", 4, main.getHistory().size());
		
		main = new Main("clear");
		assertEquals("History is cleared", 0, main.getHistory().size());
	}
	
	@Test
	public void invalidDivTests(){
		main = new Main("div");
		assertFalse("Needs Arguments", main.prints());
		
		main = new Main("div !m");
		assertFalse("Invalid variable", main.prints());
		
		main = new Main("div !1000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("div 10 0");
		assertFalse("Can not divide by 0", main.prints());
		
		main = new Main("div 10000000000000000");
		assertFalse("Integer greater than Max Integer", main.prints());
		
		assertEquals("No commands were added", 0, main.getHistory().size());
		main = new Main("clear");
		assertEquals("History is cleared", 0, main.getHistory().size());
	}
	
	@Test
	public void validSqrTests(){
		main = new Main("square 5");
		assertEquals("Squared Correctly", 25, main.getResult());
		assertTrue("Validcommand", main.prints());
		
		main = new Main("square -5");
		assertEquals("Squared Correctly", 25, main.getResult());
		assertTrue("Validcommand", main.prints());
		
		main = new Main("square 0");
		assertEquals("Squared Correctly", 0, main.getResult());
		assertTrue("Validcommand", main.prints());
		
		main = new Main("square !1");
		assertEquals("Added Correctly", 625, main.getResult());
		assertTrue("Validcommand", main.prints());
	}
	
	@Test
	public void invalidSqrTests(){
		main = new Main("square");
		assertFalse("Needs Arguments", main.prints());
		
		main = new Main("square !m");
		assertFalse("Invalid variable", main.prints());
		
		main = new Main("square !1000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("square 100000");
		assertFalse("Variable out of bounds", main.prints());
		
		main = new Main("square 10000000000000000");
		assertFalse("Integer greater than Max Integer", main.prints());
		
		assertEquals("No commands were added", 0, main.getHistory().size());
		main = new Main("clear");
		assertEquals("History is cleared", 0, main.getHistory().size());
	}
	
	@Test
	public void invalidCommands(){
		main = new Main("not a command");
		assertFalse("Invalid Command", main.prints());
		
		main = new Main(null);
		assertFalse("Invalid Command", main.prints());
	}
}

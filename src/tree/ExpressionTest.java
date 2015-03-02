package tree;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tree API assignment for CIT594, Spring 2015.
 * @author James Park
 *
 */
public class ExpressionTest {

	@Test
	public void testExpression() {
		new Expression("+ (5 10 -( *(15 20) 25) 30)");
		new Expression("+ (5 10 90)");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal1() {
		new Expression("+ 30)");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal2() {
		new Expression("-( )");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal3() {
		new Expression("BAD");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal4() {
		new Expression("/(30)");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal5() {
		new Expression("*(2)");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal6() {
		new Expression("/(75 100 42)");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal7() {
		new Expression("+ (5 10 -( /(15 3 20) 25) 30)");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExpressionIllegal8() {
		new Expression("+ (5 10 -( /(TEST test) 25) 30)");
	}
	
	@Test
	public void testEvaluate() {
		Expression testExpress1 = new Expression("+ (5 10 -( *(15 20) 25) 30)");
		Expression testExpress2 = new Expression("- (20 10)");		
		Expression testExpress3 = new Expression("* (20 10 39)");		
		
		assertTrue(testExpress1.evaluate() == 320);
		assertTrue(testExpress2.evaluate() == 10);
		assertFalse(testExpress3.evaluate() == 90);
	}

	@Test
	public void testToString() {
		Expression testExpress1 = new Expression("+ (5 10 -( *(15 20) 25) 30)");
		Expression testExpress2 = new Expression("+ (5 10 20)");
		Expression testExpress3 = new Expression("- (20 10)");		
		
		assertTrue(testExpress1.toString().equals("( 5 + 10 + ( ( 15 * 20 ) - 25 ) + 30 )"));
		assertTrue(testExpress2.toString().equals("( 5 + 10 + 20 )"));
		assertTrue(testExpress3.toString().equals("( 20 - 10 )"));
	}
}

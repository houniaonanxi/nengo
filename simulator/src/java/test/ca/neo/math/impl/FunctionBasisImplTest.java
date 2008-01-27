/*
 * Created on 24-May-2006
 */
package ca.neo.math.impl;

import org.apache.commons.lang.NotImplementedException;

import ca.neo.config.Configuration;
import ca.neo.math.Function;
import ca.neo.math.FunctionBasis;
import ca.neo.math.impl.FunctionBasisImpl;
import junit.framework.TestCase;

/**
 * Unit tests for FunctionBasisImpl. 
 * 
 * TODO: test Function methods 
 * 
 * @author Bryan Tripp
 */
public class FunctionBasisImplTest extends TestCase {

	private FunctionBasis myFunctionBasis;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		Function[] functions = new Function[]{new MockFunction(-1), new MockFunction(1)};
		myFunctionBasis = new FunctionBasisImpl(functions);
	}

	/*
	 * Test method for 'ca.bpt.cn.math.impl.FunctionBasisImpl.getDimensions()'
	 */
	public void testGetDimensions() {
		assertEquals(2, myFunctionBasis.getBasisDimension());
	}

	/*
	 * Test method for 'ca.bpt.cn.math.impl.FunctionBasisImpl.getFunction(int)'
	 */
	public void testGetFunction() {
		float[] from = new float[]{0, 0};
		assertTrue(myFunctionBasis.getFunction(1).map(from) < 0);
		assertTrue(myFunctionBasis.getFunction(2).map(from) > 0);
	}
	
	private static class MockFunction implements Function {

		private static final long serialVersionUID = 1L;
		
		float myConstantResult;
		
		public MockFunction(float constantResult) {
			myConstantResult = constantResult;
		}
		
		public int getDimension() {
			return 1;
		}

		public float map(float[] from) {
			return myConstantResult;
		}

		public float[] multiMap(float[][] from) {
			throw new NotImplementedException("not implemented");
		}
		
	}

}

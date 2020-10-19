package lab7;

public class MainStackDriver {
	
	public static void testStack()
	{
		int numTests = 0;
		int passedTests = 0;
		
		try { 
			
			ArrayStack<Integer> stack = new ArrayStack<>();

			numTests += 1;
			
			for (int i = 0; i < 100; i += 1) {
				stack.push(i);
			}
			for (int i = 99; i >= 0; i -= 1) {
				Integer intobj = stack.pop();
				if (!intobj.equals(Integer.valueOf(i))) {
					throw new Exception("Order of pop incorrect!");
				}
			
			}
			
			
			
			stack.push(0);
			Integer topVal = stack.top();
			
			if (!topVal.equals(Integer.valueOf(0))) {
				throw new Exception("top should be 0!");
			}
			
			passedTests += 1;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		// TODO
		//Test "random" pushes and pops
		
		//Integer[] testData = new Integer[] {0, 1, 5, 8, 10,};
		
		// push(2) push(20)
		// pop(4);
		//....
		
//		try { 
//			numTests += 1;
//			
//			
//		} catch (Exception ex) {
//			
//		}
//		try { 
//			numTests += 1;
//		} catch (Exception ex) {
//			
//		}
		
		System.out.printf("TESTS PASSED: %d / %d\n", passedTests, numTests);
	}
	
	public static void main(String[] args) {
		testStack();
	}

}

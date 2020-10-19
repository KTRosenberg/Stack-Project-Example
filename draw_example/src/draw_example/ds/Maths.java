package draw_example.ds;

// mathematics operations and containers
public final class Maths {
	
	
	// 2D vector container
	public static final class Vector2 {
		public int x;
		public int y;
		
		public Vector2(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() 
		{
			return "["
					+ this.x +", " + this.y + "]";
		}
	}
	

	
}

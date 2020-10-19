package draw_example.ds;

import java.util.Arrays;

// describes discrete operations
public final class CanvasCommands {
	
	// types of commands
	public static enum COMMAND_TYPE {
		ADD_CURVE,
		SET_COLOR,
	}
	
	// tells the system what to do
	public static abstract class CommandBase {
		public COMMAND_TYPE type;
	}
	public static final class CommandAddCurve 
	extends CommandBase {
		public int curveRef;
		
		public CommandAddCurve(int ref)
		{
			this.type = COMMAND_TYPE.ADD_CURVE;
			this.curveRef = ref;
		}
		
		@Override
		public String toString()
		{
			return "CommandAddCurve[type=" + 
					this.type + " ref=" + this.curveRef+ "\n"; 
		}
	}
	
	public static final class CommandSetColor 
	extends CommandBase {
		public int[] color;
		public int[] colorPrev;
		
		public CommandSetColor(int r, int g, int b, int rOld, int gOld, int bOld)
		{
			this.type  = COMMAND_TYPE.SET_COLOR;
			this.color = new int[] {r, g, b};
			this.colorPrev = new int[] {rOld, gOld, bOld};
		}
		
		@Override
		public String toString()
		{
			return "CommandSetColor[type=" + 
					this.type + " color=" + Arrays.toString(this.color)+ "\n"; 
		}
	}
}

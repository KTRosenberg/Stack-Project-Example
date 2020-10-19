package draw_example.ds;

import java.util.ArrayList;
import java.util.Arrays;

import draw_example.ds.Maths.Vector2;

public final class SketchCanvas {

	int[] currentColor = new int[] { 255, 255, 255 };

	public class Curve {
		public ArrayList<Vector2> points;
		public int[] color;

		public Curve() {
			this.points = new ArrayList<>();
			this.color = new int[] { 255, 255, 255 };
		}

		public void reuse() {
			this.points.clear();
			this.color[0] = 255;
			this.color[1] = 255;
			this.color[2] = 255;
		}

		public void setColor(int[] color) {
			System.arraycopy(color, 0, this.color, 0, this.color.length);
		}
	}

	// stores all curves and the current count
	public class CurveInfo {

		// we store a separate count
		// because we may want to reuse
		// curve objects
		public int count;
		public ArrayList<Curve> curves;

		public CurveInfo() {
			this.count = 0;
			this.curves = new ArrayList<>();
		}

		public Curve lastCurve() {
			return this.curves.get(this.count - 1);
		}

		public Curve pushCurve() {
			Curve curve = null;

			if (this.count == this.curves.size()) {
				curve = new Curve();
				this.curves.add(curve);
			} else {
				curve = this.curves.get(count);
				curve.reuse();
			}

			this.count += 1;

			return curve;
		}

		public Curve popCurve() {
			if (count > 0) {
				Curve c = this.lastCurve();
				count -= 1;
				return c;
			}

			return null;
		}

	}

	public CurveInfo curveInfo;

	public HistoryStack<CanvasCommands.CommandBase> history;

	// initialization
	public SketchCanvas() {
		this.curveInfo = new CurveInfo();
		this.history = new HistoryStack<>();

		this.setColor(this.currentColor);
	}

	// handle input

	public void inputBegan(int x, int y) {
		CurveInfo ci = this.curveInfo;

		Curve curve = ci.pushCurve();
		curve.setColor(this.currentColor);

		curve.points.add(new Vector2(x, y));

		// push curve created command
		CanvasCommands.CommandBase cmd = new CanvasCommands.CommandAddCurve(ci.count - 1);
		this.history.push(cmd);
	}

	public void inputMoved(int x, int y) {
		Curve curve = this.curveInfo.lastCurve();

		curve.points.add(new Vector2(x, y));
	}

	public void inputEnded(int x, int y) {
		Curve curve = this.curveInfo.lastCurve();

		curve.points.add(new Vector2(x, y));
	}

	public void setColor(int[] color) {

		// copy-in the new color

		CanvasCommands.CommandBase cmd = new CanvasCommands.CommandSetColor(color[0], color[1], color[2],
				this.currentColor[0], this.currentColor[1], this.currentColor[2]);

		this.history.push(cmd);

		System.arraycopy(color, 0, this.currentColor, 0, this.currentColor.length);

	}

	static class HistoryStack<E> extends ArrayStack<E> {

		public HistoryStack() {
			super();
		}

		public HistoryStack(int initialSize) {
			super(initialSize);
		}

		@Override
		public String toString() {
			StringBuilder out = new StringBuilder("{");
			for (int i = 0; i < this.cursor; i += 1) {
				out.append("\t").append(this.array[i]);
			}
			out.append("}");

			return out.toString();
		}
	}

//	static class HistoryStack<E> {
//		public Object[] array;
//		public int cursor;
//		public int maxCursor;
//		
//		public int     size()     { return 0; }
//		public E       top()      { return null; }
//		public void    push(E el) { }
//		public E       pop()      { return null; }
//		public void    clear()    {}
//		public boolean isEmpty()  { return true; }
//	}

	// TODO undoes a command
	public void undo() {

		if (this.history.size() == 0) {

			return;
		}

		switch (this.history.top().type) {
		case ADD_CURVE: {

			this.curveInfo.popCurve();

			this.history.pop();
			break;
		}
		case SET_COLOR: {

			int[] colorPrev = ((CanvasCommands.CommandSetColor) this.history.top()).colorPrev;

			System.arraycopy(colorPrev, 0, this.currentColor, 0, colorPrev.length);

			if (this.history.size() > 1) {
				this.history.pop();
			}

			break;
		}
		default: {
			break;
		}
		}
	}

	// TODO redoes a command
	public void redo() {
		// TODO now we need another cursor for "max capacity used"
		// if (this.history.cursor == this.history.maxCursor) {
		// return;
		// }
		// this.history.cursor += 1;
	}
}

package draw_example.ds;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.glass.events.KeyEvent;

import draw_example.ds.Maths.Vector2;
import draw_example.ds.SketchCanvas.Curve;
import processing.core.*;
//import java.util.Arrays;


public final class ChalkboardTime
extends PApplet {

	public SketchCanvas canvas;
	
	public static void main(String[] args) {
		PApplet.main(ChalkboardTime.class.getName());
	}
	
	// black background color by default
	int[] clearColor = new int[] {0, 0, 0};
	// white curve color by default
	int[] curveColor = new int[] {255, 255, 255};
	
	@Override
	public void settings() 
	{
		this.size(1280, 720);
	}
	
	public void clearBackground()
	{
		this.background(clearColor[0], clearColor[1], clearColor[2]);
	}
	
	@Override
	public void setup()
	{	
		this.frameRate(60);
		this.shapeMode(CORNER);
		
		this.canvas = new SketchCanvas();
		
	}
	
	@Override
	public void mousePressed()
	{
		// we only want to handle the left mouse cursor
		if (this.mouseButton != PConstants.LEFT) {
			return;
		}
		
		//System.out.println("BEFORE");
		
		//System.out.println(this.canvas.history);

		
		this.canvas.inputBegan(this.mouseX, this.mouseY);
		
		//System.out.println(this.canvas.history);
		
		//System.out.println("AFTER");
	}
	
	@Override
	public void mouseDragged() 
	{
		// we only want to handle the left mouse cursor
		if (this.mouseButton != PConstants.LEFT) {
			return;
		}
		
		this.canvas.inputMoved(this.mouseX, this.mouseY);

	}
	
	@Override
	public void mouseReleased()
	{
		// we only want to handle the left mouse cursor
		if (this.mouseButton != PConstants.LEFT) {
			return;
		}
		
		this.canvas.inputEnded(this.mouseX, this.mouseY);
	}
	
	
	@Override
	// called once per frame, for animation
	public void draw() 
	{
		// reset the background to a solid color
		// and draw atop the background for this 
		// animation frame
		this.clearBackground();
		this.noStroke();
		
		// iterate through all curves and draw them as filled shapes
		ArrayList<Curve> curves = this.canvas.curveInfo.curves;
		final int curveCount = this.canvas.curveInfo.count;
		
		for (int i = 0; i < curveCount; i += 1) {
			
			Curve c = curves.get(i);
			ArrayList<Vector2> points = c.points;
			int[] individualCurveColor = curves.get(i).color;
			
			this.fill(
				individualCurveColor[0],
				individualCurveColor[1],
				individualCurveColor[2]
			);

			
			this.beginShape(POLYGON);
			for (Vector2 point : points) {
				this.vertex(point.x, point.y);
			}
			this.endShape();			
		}	
	}
	
	@Override
	public void keyPressed() 
	{
		//System.out.println("BEFORE");
		//System.out.println(this.canvas.history);

		switch (this.keyCode) {
		// left arrow
		case PApplet.LEFT: {			
			this.canvas.undo();
			
			break;
		}
		// right arrow
		case PApplet.RIGHT: {			
			this.canvas.redo();
			
			break;
		}
		// C key
		case KeyEvent.VK_C: {
			// press C to pick a new RGB color at random
			this.curveColor[0] = (int)(Math.random() * 256);
			this.curveColor[1] = (int)(Math.random() * 256);
			this.curveColor[2] = (int)(Math.random() * 256);
			
			this.canvas.setColor(this.curveColor);
			
			break;
		}
		default: {
			break;
		}
		}
		
		//System.out.println(this.canvas.history);
		
		//System.out.println("AFTER");
	}
	

// other input methods and fields that Processing provides
//	@Override
//	mouseClicked()
//	mouseDragged()
//	mouseMoved()
//	mousePressed()
//	mousePressed
//	mouseReleased()
//	mouseWheel()
//	mouseX
//	mouseY
//	pmouseX
//	pmouseY
	
}

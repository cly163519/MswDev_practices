import ecs100.*;
import java.awt.Color;

public class Drawer {
	public static final double x = 300;
	public static final double y = 180;
	public static final double size = 80;
	public static final double stick = 200;
	
	public static final double HOUSE_WD = 46;
	
	/** Constructor: Set up interface */
	public Drawer() {
		UI.addButton("Draw lollipops", this::drawLots);
		//UI.addButton("Where to draw lollipop", this::drawLots);
		UI.addButton("Draw house", this::drawMores);
		UI.addButton("Draw gardens", this::drawMoreBeauty);
	}
	

	public void drawLots() {
		this.drawLollipop(300,180,80,200);
		this.drawLollipop(50,60,40,90);
		this.drawLollipop(400,100,90,70);
		//int x = UI.askInt("Lollipop x: ");
		//int y = UI.askInt("Lollipop y: ");
		//this.drawLollipop(x, y, 90, 70);
		
		//int left = UI.askInt("House x: ");
		//int top = UI.askInt("House y: ");
		//int ht = UI.askInt("House_ht");
	}	
		public void drawMores() {
		this.drawHouse(50, 400, (50+Math.random()*70) );
		this.drawHouse(150, 400, (50+Math.random()*70) );
		this.drawHouse(250, 400, (50+Math.random()*70) );
		this.drawHouse(350, 400, (50+Math.random()*70) );
		this.drawHouse(450, 400, (50+Math.random()*70) );
		this.drawHouse(550, 400, (50+Math.random()*70) );
		this.drawHouse(650, 400, (50+Math.random()*70) );
		this.drawHouse(750, 400, (50+Math.random()*70) );
		
	}
		public void drawMoreBeauty() {
			this.drawGardens(50, 460, 46);
			this.drawGardens(150, 460, 46);
			this.drawGardens(250, 460, 46);
			this.drawGardens(350, 460, 46);
			this.drawGardens(450, 460, 46);
			this.drawGardens(550, 460, 46);
			this.drawGardens(650, 460, 46);
			this.drawGardens(750, 460, 46);
		}
		
		public void drawGardens(double mid, double bot, double ht) {//Why mid,bot,why not x,y position directly?
			double left = mid - HOUSE_WD/2;
			double right = mid + HOUSE_WD/2;
			double top = bot - ht;
			double tip = top - HOUSE_WD*2/3;
			UI.setColor(Color.DARK_GRAY);
			UI.setLineWidth(2);
			UI.drawRect(left, top, HOUSE_WD, ht);
			//UI.drawLine( left,  top,  mid,  tip);
			//UI.drawLine( right,  top,  mid, tip);
			
			//Draw lollipops on garderns
			
			this.drawLollipopOnGarden(38, 415, 10,15);
			this.drawLollipop(150, 437, 10,15);
			this.drawLollipop(250, 437, 10,15);
			this.drawLollipop(350, 437, 10,15);
			this.drawLollipop(450, 437, 10,15);
			this.drawLollipop(550, 437, 10,15);
			this.drawLollipop(650, 437, 10,15);
			this.drawLollipop(750, 437, 10,15);
			
		}
		public void drawLollipopOnGarden(double midX, double midY, double radius, double stickS) {
			double left = midX - radius;
			double top = midY + radius;
			UI.setColor(Color.black);
			UI.setLineWidth(2);
			UI.drawLine(midX, midY, midX, midY+15);
			UI.setColor(Color.red);
			
			UI.fillOval(left,top,radius,radius);
		}
	
	
	//Draw a house
	public void drawHouse(double mid, double bot, double ht) {
		double left = mid - HOUSE_WD/2;
		double right = mid + HOUSE_WD/2;
		double top = bot - ht;
		double tip = top - HOUSE_WD*2/3;
		UI.setColor(Color.DARK_GRAY);
		UI.setLineWidth(2);
		UI.drawRect(left, top, HOUSE_WD, ht);
		UI.drawLine( left,  top,  mid,  tip);
		UI.drawLine( right,  top,  mid, tip);
		
		//Draw window
		double winL = mid - HOUSE_WD*0.25;
		double winR = mid + HOUSE_WD*0.25;
		double winWd = HOUSE_WD*0.33;
		double lev1 = bot - ht*0.25;
		double lev2 = bot - ht*0.75;
		
		this.drawWindow(winL, lev1, winWd);
		this.drawWindow(winR, lev1, winWd);
		this.drawWindow(winL, lev2,  winWd);
		this.drawWindow(winR, lev2,  winWd);

		
	}
	public void drawWindow(double midX, double midY, double sz ) {
		double rad = sz/2;
		UI.setColor(Color.darkGray);
		UI.setLineWidth(1);
		UI.drawRect(midX-rad, midY-rad, sz, sz);
		UI.drawLine(midX-rad, midY, midX+rad, midY);
		UI.drawLine(midX, midY-rad, midX, midY+rad);
	}
	
	
	
	//Draw lollipop
	public void drawLollipop(double x, double y, double size, double stick) {
		/*
		 *  Convert this to Java (look up the documentation
		 *  as necessary):
		 */
		double left = x - size/2;
		double top = y - size/2;
		double bot = y + stick;
		UI.setColor(Color.black);
		// set line width to 10
		UI.setLineWidth(2);
		// draw line  (300, 200) to (300, 400)
		UI.drawLine(x,y,x,bot);
		// set line width to 1
		UI.setLineWidth(1);
		// set colour to red
		UI.setColor(Color.red);
		// fill oval at  (260, 160) that is 80x80
		UI.fillOval(left, top, size, size);
	}
	
	

	public static void main(String[] args) {
		UI.initialise();
		new Drawer();
	}
}

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Pendulum extends JPanel implements MouseListener, MouseMotionListener{

	private int SCREEN_WIDTH = 600;
	private int SCREEN_HEIGHT = 600;
	private Integer x = 600;
	private Integer y = 300;
	private double len = 200;
	private Thread animationThread;
	private double angle = 360;
	private Double aVel = 0.0;
	private Double aAcc = 0.0;
	boolean dragging = false;
	float ball_r = 40;
	
	public Pendulum()
	{
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		addMouseListener(this);
	    addMouseMotionListener(this);
		start();
	}
	
	public Pendulum(int len)
	{
		this.len = len;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		addMouseListener(this);
	    addMouseMotionListener(this);
		start();
	}
	
	public void start()
	{
		animationThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    try {Thread.sleep(10);} catch (Exception ex) {}
                }
            }
        });
		animationThread.start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D gr = (Graphics2D) g;
		super.paintComponent(gr);
		drawGraph(gr);
		drawPendulum(gr);
	}
	public void update()
	{
		if(!dragging)
		{
			double G = 0.4;
			aAcc = (-1.0 * G / len) * Math.sin(angle);				
			angle += aVel;
			aVel += aAcc;
			aVel *= 0.99; // damping
		}
	}
	
	public void drawPendulum(Graphics2D g)
	{		
		g.setColor(Color.RED);
				
		x = (int) (len * Math.sin(angle)) + 300;
		y = (int) (len * Math.cos(angle)) + 300;
		
		g.drawLine(300, 300, x, y);	
		Ellipse2D.Double circle = new Ellipse2D.Double(x-20, y-20, ball_r, ball_r);
		g.fill(circle);
		update();
		
	}
	
	public void drawGraph(Graphics2D gr)
	{
		gr.setColor(Color.WHITE);
		gr.setStroke(new BasicStroke(2));
		gr.drawLine(300,0,300,600);
		gr.drawLine(0,300,600,300);
		
		String aAcceleration = aAcc.toString();
		String aVelocity = aVel.toString();
		String p_x = x.toString();
		String p_y = y.toString();
		
		gr.drawString("Angle Acceleration: " + aAcceleration, 5, 10);
		gr.drawString("Angle Velocity: " + aVelocity, 5, 25);
		gr.drawString("x: " + p_x, 5, 40);
		gr.drawString("y: " + p_y, 5, 55);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int xDiff = 300 - e.getX();
		int yDiff = 300 - e.getY();
		angle = Math.atan2(-1*yDiff, xDiff) - Math.toRadians(90);
		dragging = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int xDiff = 300 - e.getX();
		int yDiff = 300 - e.getY();
		angle = Math.atan2(-1*yDiff, xDiff) - Math.toRadians(90);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		aVel = 0.0;
		dragging = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

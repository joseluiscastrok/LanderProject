

package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -1285670934175365101L;
	
	public static final int WIDTH = 900, HEIGHT = 600;
	public static final int LIMIT_X1=5, LIMIT_Y1=5 ,LIMIT_X2 =WIDTH-10, LIMIT_Y2=HEIGHT-32;
	
	public static final double GRAVITY = 0.018;
	public static boolean reinit = false;
	
	public Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random randomgen1;
	public LandingZone landingZone1; 
	
	public Game() {
		randomgen1 = new Random();

		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window (WIDTH, HEIGHT, "Lander # 1", this);
		// x,y,speedX,speedY,shipDirection,rotationSpeed,acceleration,id
	//	handler.addObject(new ShipV(100,100,0,0,0,270,0,0,0,1));
	//  GameObject (double x, double y, double speedX, double speedY,double travelDirection ,
	//	double direction, double directionSpeed, double acceleration ,double accelerationSpeed,
	//	int id)
		
				
//     			handler.addObject(new ShipV(WIDTH/2,10,randomgen1.nextDouble()*2-1 ,0,0,270,randomgen1.nextDouble()*5,0,0,1));;
//			    landingZone1 = new LandingZone(WIDTH/2, HEIGHT -100, 40);
	
		initObjects();
		
		//handler.addObject(new ShipV(350,100,150,1));
		//handler.addObject(new ShipV(50,150,0,1));

	}
	
	
	public synchronized void start() {
		Thread thread = new Thread(this);
		thread.start();
		running= true;
	}
		
	public void stop() {
		try {
			thread.join();
			running = false;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void run() {
        //init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
        	this.setFocusable(true);
        	
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
               render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//               System.out.println(updates + " Ticks, Fps " + frames);
//               System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner());
                updates = 0;
                frames = 0;
            }

        }
        stop();
	}
	private void render() {
		// TODO Auto-generated method stub
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
		return;
		} 
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		/// draw borders
		g.setColor(Color.ORANGE);
		g.drawRect(LIMIT_X1,LIMIT_Y1, LIMIT_X2,LIMIT_Y2);
		
		g.setColor(Color.WHITE);
		g.drawString("Gravity : ", WIDTH - 200, 20);
		g.drawString(Double.toString(GRAVITY),WIDTH -100, 20);
		
		//render landingzone
		g.setColor(Color.GREEN);
		g.drawLine((int)landingZone1.getX(), (int)landingZone1.getY(), (int)landingZone1.getX1(), (int)landingZone1.getY1());
		
		handler.render(g);

		g.dispose();
		bs.show();

	}

	private void tick() {
		// probably check collision here
		handler.tick();
		if (reinit) {initObjects(); reinit = false;}
		
	}
	
	private void initObjects() {
		
		    if (handler.objects.size()>0) {
		    handler.removeObject(handler.objects.get(0));
		    }
			handler.addObject(new ShipV(WIDTH/2,10,randomgen1.nextDouble()*2-1 ,0,0,270,randomgen1.nextDouble()*5,0,0,1));;
		    landingZone1 = new LandingZone(WIDTH/2+randomgen1.nextInt(WIDTH/2)-WIDTH/4, HEIGHT - randomgen1.nextInt(HEIGHT-100), 20);
		
	}
	

	public static void main (String[] args)  {
		new Game();
	}
	
}

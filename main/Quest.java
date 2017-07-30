import indieQuest.entity.mob.Player;
import indieQuest.graphics.Screen;
import indieQuest.graphics.SpriteSheet;
import indieQuest.input.Keyboard;
import indieQuest.input.Mouse;
import indieQuest.level.Level;
import indieQuest.level.LoadLevel;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Quest extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static boolean running;	
	private Screen screen;	
	private Thread t;	
	private Level level;	
	public JFrame gameFrame;
	private Player player;
	
	public static int width = 400;
	public static int height = width * 12 / 16; // school comp aspect ratio 16 : 12
	public static int scale = 5;
	
	// Scale of 4 gives fullscreen at school 
	private Keyboard input;
	
	// Makes an array (pixels) that allows us to write and change each and every pixel
	// on the screen. Complicated thing is just a given, don't question it.
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage cursor;
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Quest() {
		screen = new Screen(width, height);
		gameFrame = new JFrame();
		//gameFrame.setUndecorated(true); // HAS TO BE UNCOMMENTED FOR FULLSCREEN (SCALE 5)
		input = new Keyboard();
		level = new LoadLevel("/textures/map/map_100x50.png");
		
		try {
			cursor = ImageIO.read(SpriteSheet.class.getResource("/textures/misc/crosshairs.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player = new Player(16, 16, input);
		//level.add(player);
		player.init(level);
		
		Mouse mouse = new Mouse();
		
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(input);
		
		// Set the size of the window to our width and height * scale which is the "preferred" size
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
	}

	// Creates a thread for the game to run on, called "Display". Starts the thread
	public synchronized void start() {
		running = true;
		//thread = new Thread(this, "Display");
		t = new Thread(this, "Display");
		t.start();
	}
	
	// Terminates the thread by joining all current threads
	public synchronized void stop() {
		try {
			t.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// The render method. Creates buffer and calls anything that deals with drawing onto the screen
	public void render() {
		// Look for an existing bufferStrategy, if there's none, create one
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		// Clear the screen before drawing anything
		screen.clear();
		
		int xPos = player.x - (screen.width /2);
		int yPos = player.y - (screen.height /2);
		
		// Call the methods of what needs to be drawn, thus setting screens pixel array to whatever is below
		// Order matters!
		level.render(xPos, yPos, screen);
		player.render(screen);
		
		// Take every pixel in this class's pixel array and set it equal to the screen class's pixel array
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		// Get graphics object from bufferStrategy
		Graphics g = bs.getDrawGraphics();
		
		// Draw our image, which is equal to the pixel array of this class. Essentially drawing the array.
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(cursor, Mouse.getX(), Mouse.getY(), cursor.getWidth(), cursor.getHeight(), null);
		
		// Dispose of system resources we might be taking up (memory and such)
		g.dispose();
		
		// Display the bufferStrategy
		bs.show();
	}
	
	@Override
	public void run() {		
		// FPS variable, set to desired value
		// Lots of variables, all for controlling the speed of execution of both our update() and render() methods
		final double FPS = 60.0;		
		long timeStart = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double NANOCONSTANT = Math.pow(10.0, 9) / FPS;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		// Requests that the window we create already be selected when it opens so you don't have to click inside it to play
		requestFocus();
		
		// Main game loop. Runs the all important render() and update() methods and keeps track of the current FPS and UPS
		while(running) {
			long timeNow = System.nanoTime();
			delta += (timeNow - timeStart) / NANOCONSTANT;
			
			timeStart = timeNow;
			
			while(delta >= 1) {
				update();
				updates ++;
				render();
				frames ++;
				delta--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.gameFrame.setTitle((updates + " UPS " + frames + " FPS" ));
				frames = 0;
				updates = 0;
			}
		}
		// Fail safe. In case we unexpectedly exit out of our game loop, it will stop the thread.
		this.stop();
	}
	
	// Updates the logic of the game including position and physics 
	public void update() {
		input.update();
		player.update();
		level.update();
		level.entities.size();
	}

	// The main method of the whole thing. The domino effect that is this game is set off right here. 
	public static void main(String[] args) {
		// Creates a new object of this class's type (allows us to call it's methods)
		final Quest quest = new Quest();
		
		// Add the object to the window object. Prevents window from being resizable. Sets the window size to the preferred size specified earlier.
		// Centers the window on our screen. Ensures that when the 'x' of the window is clicked, it exits the program and terminates execution.
		quest.gameFrame.add(quest);
		quest.gameFrame.setResizable(false);
		quest.gameFrame.pack();
		quest.gameFrame.setLocationRelativeTo(null);
		quest.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		quest.gameFrame.setVisible(true);
		quest.gameFrame.setCursor(quest.gameFrame.getToolkit().createCustomCursor(
				new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "null"));
		// Start the main thread ("Display")
		quest.start();
	}
}

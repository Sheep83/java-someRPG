package someRPG;

import java.awt.Canvas;
import java.awt.Color;
//import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;





public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7436840021209078721L;
	public static final int WIDTH = 1280, HEIGHT = 1024;
	private Thread thread;
	private static BufferedImage faunaSpriteSheet, playerSpriteSheet, wolfSpriteSheet, iconSpriteSheet = null;
	private BufferedImage background = null;
	private boolean running = false;
//	private Random r = new Random();
	private static Handler handler;
	private static HUD hud;
	private Level level;
	private static Textures textures;
	private SoundPlayer soundPlayer;
	private static int levelCount;
	private Dice d10, d100;
	public static enum STATE {
		Menu,
		Game
	};
	public static STATE gameState;
	private Menu menu;
	public Collision collision;
	public static Camera camera;
	public static float offsetX, offsetY;
	
	
	public Game() {
		
		gameState = STATE.Menu;
		new Window(WIDTH, HEIGHT, "someRPG", this);
		camera = new Camera(0, 0);
		handler = new Handler();
		menu = new Menu(this, handler, soundPlayer);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
//		soundPlayer = new SoundPlayer();
		
		setupSprites();
//		initGame();
		level = new Level(textures);
		level.build(handler, 1);
//		collision = new Collision();
		d100 = new Dice(100);
		d10 = new Dice(10);
		handler.addDice(d100);
//		handler.addDice(d10);
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT - 384, ID.Player, handler, textures));
//		handler.addObject(new Monster(WIDTH/2 - 128, 240, ID.Monster, handler, textures, "Wolf", 1));
//		handler.addObject(new Monster(WIDTH/2 - 256, 128, ID.Fauna, handler, textures, "Rabbit", 2));
//		handler.addObject(new Monster(WIDTH/2 - 512, 240, ID.Monster, handler, textures, "Wolf", 3));
//		handler.addObject(new InventoryObject(700, 512, ID.Potion, "Potion"));
		hud = new HUD(handler, textures);
		levelCount = 1;
//		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
//		menu.tick();
	}
	
	private void setupSprites() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			playerSpriteSheet = loader.loadImage("/sheep.png");
			faunaSpriteSheet = loader.loadImage("/rabbits.png");
			wolfSpriteSheet = loader.loadImage("/wolves.png");
			iconSpriteSheet = loader.loadImage("/IconSet.png");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textures = new Textures(this);
		
	}
	
	public static BufferedImage getPlayerSpriteSheet() {
		return playerSpriteSheet;
	}
	
	public static BufferedImage getFaunaSpriteSheet() {
		return faunaSpriteSheet;
	}
	
	public static BufferedImage getWolfSpriteSheet() {
		return wolfSpriteSheet;
	}
	
	public static BufferedImage getIconSpriteSheet() {
		return iconSpriteSheet;
	}
	
	
	private void initGame() {
		gameState = STATE.Menu;
		Collision collision = new Collision();
		setLevel(1);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT - 32, ID.Player, handler, textures));
		hud = new HUD(handler, textures);
		level = new Level(textures);
		level.build(handler, levelCount);
		menu.tick();
	}
	
	private void gameOverCheck() {
		Player player = (Player) handler.getPlayer();
		if(player.getLives() <= 0) {
			gameState = STATE.Menu;
			initGame();
		}
	}
	
//	private void brickCheck() {
//		int brickCount = 0;
//		for (int i = 0; i < handler.objects.size(); i++) {
//			if (handler.objects.get(i).getId() == ID.Brick) {
//				brickCount ++;
//				}
//			}
//		if(brickCount == 0) {
//			
//			level = new Level();
//			levelCount ++;
//			level.build(handler, levelCount);
//		}
//	}
	
	public static int getLevel() {
		return levelCount;
	}
	
	public static void setLevel(int level) {
		levelCount = level;
	}
	
	public static void setState(STATE state) {
		gameState = state;
	}
	
	public static Textures getTextures() {
		return textures;
	}
	
	public static Handler getHandler() {
		return handler;
	}
	
	public synchronized void start() {
	
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
	
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run()
    {
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while(running){
        	long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1){
            	tick();
            	updates ++;
                delta--;
                }
            
//            if(running)
            	render();
                frames++;
                            
                if(System.currentTimeMillis() - timer > 1000){
                	timer += 1000;
                	System.out.println("FPS: "+ frames + "   Ticks: " + updates);
                	updates = 0;
                	frames = 0;
                 }
        }
        stop();	
	}
	
	private void tick() {
		
		if(gameState == STATE.Menu) {
			menu.tick();
		}else if(gameState == STATE.Game) {
//			soundPlayer.stopSound();
//			soundPlayer.currentClip = null;
//			gameOverCheck();
			hud.tick();	
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if(gameState == STATE.Game) {
			g.drawImage(background, 0, 0, this);
			handler.render(g);
			hud.render(g);	
		}else if (gameState == STATE.Menu) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max) {
			return var = max;
		}else if (var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	

	public static void main(String[] args) {
		new Game();
	}
	
	public static HUD getHUD() {
		return hud;
	}

}



package main;

public class Game implements Runnable {
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	
	//Main Game Constructor
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();

	}
	//Begins main Loop on a seperate thread
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		//FPS Counter - [Don't change, will condense this next commit]
		double timePerFrame = 1000000000.0/FPS_SET; //A billion s
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		while(true) {
			
			now = System.nanoTime();
			if(System.nanoTime() - lastFrame >= timePerFrame) {
				
				gamePanel.repaint();
				lastFrame = System.nanoTime();
				frames++;
			}
			if (System.currentTimeMillis() - lastCheck >=1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
}

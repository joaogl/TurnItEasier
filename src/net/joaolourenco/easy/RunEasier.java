package net.joaolourenco.easy;

import java.awt.Canvas;

public abstract class RunEasier extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	static Thread thread;
	static boolean running;

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		int ups = 0;
		//Display.getFrame().requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				ticks = 0;
				frames = 0;
				ups = 0;
			}
		}
	}

	public abstract void render();

}
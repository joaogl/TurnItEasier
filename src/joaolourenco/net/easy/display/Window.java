package joaolourenco.net.easy.display;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import joaolourenco.net.easy.exceptions.window.FailedBuildPointerException;

public class Window extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	static Thread thread;
	static boolean running;
	static int tick;
	static int fps;
	static int updates;
	boolean useTicks = false;
	boolean useUpdates = false;
	boolean useRenders = false;
	boolean useBufferStrategy = false;
	boolean useClearScreen = false;
	boolean returnGraphics = false;
	static boolean usegetFocus = true;
	String[] classname = new String[3];
	String[] methodname = new String[3];

	private static BufferedImage image = new BufferedImage(Display.getWidth(), Display.getHeight(), BufferedImage.TYPE_INT_RGB);
	private static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public static void create() {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("PascalLike");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		Display.setFrame(frame);
	}

	public static void create(boolean useDefaults) {
		if (useDefaults) create();
		else {
			JFrame frame = new JFrame();
			Display.setFrame(frame);
		}
	}

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
		Display.getFrame().requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				if (useTicks) {
					invokeClass(2);
					ticks++;
				}
				delta--;
			}
			if (useRenders) {
				render();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (useRenders) fps = frames;
				if (useTicks) tick = ticks;
				if (useUpdates) {
					ups++;
					updates = ups;
					invokeClass(0);
				}
				ticks = 0;
				frames = 0;
				ups = 0;
			}
		}
	}

	private void invokeClass(int id) {
		try {
			Method main = Class.forName(classname[id]).getDeclaredMethod(methodname[id]);
			main.invoke(null);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			try {
				throw new FailedBuildPointerException();
			} catch (FailedBuildPointerException ee) {
				ee.printStackTrace();
			}
			if (id == 0) useUpdates = false;
			else if (id == 1) useRenders = false;
			else if (id == 2) useTicks = false;
		}
	}

	public void useUpdate(boolean use, String classn, String methodn) {
		classname[0] = classn;
		methodname[0] = methodn;
		useUpdates = use;
	}

	public void useRender(boolean use, String classn, String methodn) {
		classname[1] = classn;
		methodname[1] = methodn;
		useRenders = use;
	}

	public void useTick(boolean use, String classn, String methodn) {
		classname[2] = classn;
		methodname[2] = methodn;
		useTicks = use;
	}

	public static void useGetFocus(boolean use) {
		usegetFocus = use;
	}

	public static int getFrames() {
		return fps;
	}

	public static int getTicks() {
		return tick;
	}

	public static int getUpdates() {
		return updates;
	}

	public static Thread getThread() {
		return thread;
	}

	public static int[] getPixels() {
		return pixels;
	}

	public static void setPixels(int[] pixel) {
		pixels = pixel;
	}

	public static void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}

	public void render() {
		if (useBufferStrategy) {
			BufferStrategy bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
				return;
			}

			Graphics g = bs.getDrawGraphics();
			if (useClearScreen) clear();

			if (returnGraphics) {
				try {
					Method main = Class.forName(classname[1]).getDeclaredMethod(methodname[1]);
					main.invoke(null, g);
				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					try {
						throw new FailedBuildPointerException();
					} catch (FailedBuildPointerException ee) {
						ee.printStackTrace();
					}
					useRenders = false;
				}
			} else invokeClass(1);

			g.dispose();
			bs.show();
		} else invokeClass(1);
	}

	public void useReturnGraphics(boolean use) {
		returnGraphics = use;
	}

}
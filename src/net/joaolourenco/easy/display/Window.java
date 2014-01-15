package net.joaolourenco.easy.display;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Window extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	static Thread thread;
	static Logger logger = Logger.getLogger("TurnItEasier");
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
	String[] classname = new String[4];
	String[] methodname = new String[4];
	Method[] method = new Method[4];
	Class<?>[] methodargs;
	int id = 0;
	boolean done = false;

	private static BufferedImage image = new BufferedImage(Display.getWidth(), Display.getHeight(), BufferedImage.TYPE_INT_RGB);
	private static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public static void create() {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("TurnItEasier");
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
			if (useRenders || returnGraphics) {
				render();
				frames++;
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (useRenders || returnGraphics) fps = frames;
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
			if (method[id] == null) {
				Method main = Class.forName(classname[id]).getDeclaredMethod(methodname[id]);
				method[id] = main;
				main.invoke(null);
			} else method[id].invoke(null);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			logger.severe("The requested pointer is not possible to be stablished.");
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
			if (useRenders) invokeClass(1);

			g.drawImage(image, 0, 0, Display.getWidth(), Display.getHeight(), null);

			if (returnGraphics) {
				try {
					if (method[3] == null) {
						Class<?> func = Class.forName(classname[3]);
						Method[] allMethods = func.getDeclaredMethods();
						for (Method m : allMethods) {
							if (methodname[3].equals(m.getName()) && !done) {
								methodargs = m.getParameterTypes();
								if (id == 0) done = true;
							}
						}
						Method m = func.getDeclaredMethod(methodname[3], methodargs);
						m.invoke(null, (Object) g);
						method[3] = m;
					} else method[3].invoke(null, (Object) g);

				} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					if (id == 0) {
						id = 1;
						method[3] = null;
						done = false;
					} else {
						e.printStackTrace();
						logger.severe("The requested pointer is not possible to be stablished.");
						returnGraphics = false;
					}
				}

			}
			g.dispose();
			bs.show();
		} else if (useRenders) invokeClass(1);
	}

	public void useBufferStrategy(boolean use) {
		useBufferStrategy = use;
	}

	public void useClearScreen(boolean use) {
		useClearScreen = use;
	}

	public void useRenderWithGraphics(boolean use, String args, String args2) {
		returnGraphics = use;
		classname[3] = args;
		methodname[3] = args2;
	}

	public static int getKey() {
		// TODO Auto-generated method stub
		return 0;
	}

}
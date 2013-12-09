package joaolourenco.net.pascal.display;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

public class Window implements Runnable {

	Thread thread;
	static boolean running;
	static int tick;
	static int fps;
	static int updates;
	boolean useTicks = false;
	boolean useUpdates = false;
	boolean useRenders = false;
	static boolean usegetFocus = true;
	String[] classname = new String[3];
	String[] methodname = new String[3];

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
				invokeClass(1);
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
			stop();
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

}
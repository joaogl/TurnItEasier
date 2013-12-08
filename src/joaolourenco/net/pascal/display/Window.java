package joaolourenco.net.pascal.display;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

public class Window implements Runnable {

	Thread thread;
	static boolean running;
	int tick;
	int fps;
	boolean useTicks = false;
	boolean useUpdates = false;
	boolean useRenders = false;
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
		long timer2 = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		Display.getFrame().requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				if (useTicks) invokeClass(2);
				ticks++;
				delta--;
			}
			if (useRenders) invokeClass(1);
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				tick = ticks;
				ticks = 0;
				frames = 0;
			}
			if (System.currentTimeMillis() - timer2 < 2500) if (useRenders) invokeClass(0);
		}
	}

	private void invokeClass(int id) {
		try {
			System.out.println(classname[id] + " " + methodname[id] + " " + id);
			Class<?> c = Class.forName(classname[id]);
			Method main = c.getDeclaredMethod(methodname[id]);
			main.invoke(null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			stop();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			stop();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
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

}
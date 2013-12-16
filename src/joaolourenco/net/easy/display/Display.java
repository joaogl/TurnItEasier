package joaolourenco.net.easy.display;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import joaolourenco.net.easy.TurnItEasier.DisplayType;
import joaolourenco.net.easy.exceptions.InvalidDisplayTypeException;
import joaolourenco.net.easy.exceptions.console.ImpossibleActionException;
import joaolourenco.net.easy.exceptions.console.NullPointException;

public class Display {

	private static DisplayType currentDisplayType;
	private static int width = 500;
	private static int height = 500;
	private static JFrame frame;
	private static JPanel window;
	private static Window windowRunnable;

	/**
	 * Returns an Image object that can then be painted on the screen. The url argument must specify an absolute {@link URL}. The name argument is a specifier that is relative to the url argument.
	 * <p>
	 * This method always returns immediately, whether or not the image exists. When this applet attempts to draw the image on the screen, the data will be loaded. The graphics primitives that draw the image will incrementally paint on the screen.
	 * 
	 * @param url
	 *            an absolute URL giving the base location of the image
	 * @param name
	 *            the location of the image, relative to the url argument
	 * @return the image at the specified URL
	 * @see Image
	 */
	public static void create(DisplayType a) {
		if (a == DisplayType.Console) {
		} else if (a == DisplayType.DynamicConsole) DynamicConsole.create();
		else if (a == DisplayType.Window) Window.create();
		else {
			try {
				throw new InvalidDisplayTypeException(a.toString());
			} catch (InvalidDisplayTypeException e) {
				e.printStackTrace();
			}
		}
		if (a == DisplayType.Console || a == DisplayType.DynamicConsole || a == DisplayType.Window) setDisplayType(a);
		else setDisplayType(DisplayType.DynamicConsole);
	}

	public static void create(DisplayType a, boolean usedefaults) {
		if (a == DisplayType.Console) {
		} else if (a == DisplayType.DynamicConsole) DynamicConsole.create(usedefaults);
		else if (a == DisplayType.Window) Window.create(usedefaults);
		else {
			try {
				throw new InvalidDisplayTypeException(a.toString());
			} catch (InvalidDisplayTypeException e) {
				e.printStackTrace();
			}
		}
		if (a == DisplayType.Console || a == DisplayType.DynamicConsole || a == DisplayType.Window) setDisplayType(a);
		else setDisplayType(DisplayType.DynamicConsole);
	}

	public static boolean isDisplayCreated() {
		if (currentDisplayType == null) return false;
		else return true;
	}

	public static boolean isDisplayType(DisplayType a) {
		return (currentDisplayType == a);
	}

	public static void setDisplayType(DisplayType args) {
		currentDisplayType = args;
	}

	public static DisplayType getDisplayType() {
		return currentDisplayType;
	}

	public static void updateWindow() {
		frame.setSize(width, height);
	}

	public static void setFrame(JFrame args) {
		frame = args;
	}

	public static int getWidth() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("width");
			return 0;
		} else return width;
	}

	public static int getHeight() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("height");
			return 0;
		} else return height;
	}

	public static void setSize(int w, int h) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setSize");
		else {
			width = w;
			height = h;
			updateWindow();
		}
	}

	public static void setWidth(int w) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setWidth");
		else {
			width = w;
			updateWindow();
		}
	}

	public static void setHeight(int h) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setHeight");
		else {
			height = h;
			updateWindow();
		}
	}

	public static void setAlwaysOnTop(boolean use) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setAlwaysOnTop");
		else frame.setAlwaysOnTop(use);
	}

	public static void setLocation(String where) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setLocation");
		else {
			if (where == null) frame.setLocationRelativeTo(null);
		}
	}

	public static void setLocation(int x, int y) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setLocation");
		else frame.setLocation(x, y);
	}

	public static void setTitle(String title) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setTitle");
		else frame.setTitle(title);
	}

	public static void add(Component comp) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.add(comp);
	}

	public static void add(Component comp, Font font) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			comp.setFont(font);
			frame.add(comp);
		}
	}

	public static void add(Component comp, int x, int y, int widht, int height) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			comp.setBounds(x, y, widht, height);
			frame.add(comp);
		}
	}

	public static void add(Component comp, int x, int y, int widht, int height, Font f) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			comp.setFont(f);
			comp.setBounds(x, y, widht, height);
			frame.add(comp);
		}
	}

	public static void add(Component comp, boolean panel) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			if (panel) window.add(comp);
			else frame.add(comp);
		}
	}

	public static void add(Component comp, boolean panel, Font f) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			comp.setFont(f);
			if (panel) window.add(comp);
			else frame.add(comp);
		}
	}

	public static void add(Component comp, int x, int y, int widht, int height, boolean panel) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			comp.setBounds(x, y, widht, height);
			if (panel) window.add(comp);
			else frame.add(comp);
		}
	}

	public static void add(Component comp, int x, int y, int widht, int height, boolean panel, Font f) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			comp.setFont(f);
			comp.setBounds(x, y, widht, height);
			if (panel) window.add(comp);
			else frame.add(comp);
		}
	}

	public static void addPanel() {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			window = new JPanel();
			frame.getContentPane().add(window);
			window.setLayout(null);
			window.setBackground(Color.WHITE);
		}
	}

	public static void setBackground(Color c) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else {
			if (window != null) window.setBackground(c);
			if (frame != null) frame.setBackground(c);
		}
	}

	public static void add(FocusListener l) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addFocusListener(l);
	}

	public static void add(MouseListener l) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addMouseListener(l);
	}

	public static void add(ComponentListener l) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addComponentListener(l);
	}

	public static void add(ContainerListener l) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addContainerListener(l);
	}

	public static void add(MouseMotionListener l) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addMouseMotionListener(l);
	}

	public static void add(KeyListener l) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addKeyListener(l);
	}

	public static void add(MouseWheelListener l) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addMouseWheelListener(l);
	}

	public static void add(WindowListener list) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("add");
		else frame.addWindowListener(list);
	}

	public static void useRunnable(boolean use) {
		if (windowRunnable == null) {
			if (use) {
				windowRunnable = new Window();
				windowRunnable.start();
			} else {
				try {
					throw new joaolourenco.net.easy.exceptions.window.NullPointException(1, "useRun");
				} catch (ImpossibleActionException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (use) windowRunnable.start();
			else windowRunnable.stop();
		}
	}

	public static void useUpdate(boolean use, String classn, String methodn) {
		if (windowRunnable == null) {
			windowRunnable = new Window();
			windowRunnable.useUpdate(use, classn, methodn);
		} else windowRunnable.useUpdate(use, classn, methodn);
	}

	public static void useRender(boolean use, String classn, String methodn) {
		if (windowRunnable == null) {
			System.out.println("New");
			windowRunnable = new Window();
			windowRunnable.useRender(use, classn, methodn);
		} else windowRunnable.useRender(use, classn, methodn);
	}

	public static void useTick(boolean use, String classn, String methodn) {
		if (windowRunnable == null) {
			windowRunnable = new Window();
			windowRunnable.useTick(use, classn, methodn);
		} else windowRunnable.useTick(use, classn, methodn);
	}

	public static void pack() {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("pack");
		else frame.pack();
	}

	public static void setDefaultCloseOperation(int op) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setDefaultCloseOperation");
		else frame.setDefaultCloseOperation(op);
	}

	public static void setResizable(boolean resizable) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setResizable");
		else frame.setResizable(resizable);
	}

	public static void setUndecorated(boolean Undecorated) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setUndecorated");
		else {
			frame.dispose();
			frame.setUndecorated(Undecorated);
			frame.setVisible(true);
		}
	}

	public static void setUndecorated(boolean Undecorated, boolean visibility) {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("setUndecorated");
		else {
			frame.dispose();
			frame.setUndecorated(Undecorated);
			frame.setVisible(visibility);
		}
	}

	public static JPanel getPanel() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("a panel");
			return null;
		} else return window;
	}

	public static JFrame getFrame() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("a frame");
			return null;
		} else return frame;
	}

	public static int getFrames() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("frames");
			return 0;
		} else return Window.getFrames();
	}

	public static int getTicks() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("ticks");
			return 0;
		} else return Window.getTicks();
	}

	public static int getUpdates() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("updates");
			return 0;
		} else return Window.getUpdates();
	}

	public static Thread getThread() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("thread");
			return null;
		} else return Window.getThread();
	}

	public static void show() {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("show");
		else frame.setVisible(true);
	}

	public static void hide() {
		if (isDisplayType(DisplayType.Console)) ThrowImpossible("hide");
		else frame.setVisible(false);
	}

	public static void useRenderWithGraphics(boolean use, String agrs, String args2) {
		if (windowRunnable == null) {
			windowRunnable = new Window();
			windowRunnable.useRenderWithGraphics(use, agrs, args2);
		} else windowRunnable.useRenderWithGraphics(use, agrs, args2);
	}

	public static void useBufferStrategy(boolean use) {
		if (windowRunnable == null) {
			windowRunnable = new Window();
			add(windowRunnable);
			windowRunnable.useBufferStrategy(use);
		} else {
			add(windowRunnable);
			windowRunnable.useBufferStrategy(use);
		}
	}

	public static void useClearScreen(boolean use) {
		if (windowRunnable == null) {
			windowRunnable = new Window();
			windowRunnable.useClearScreen(use);
		} else windowRunnable.useClearScreen(use);
	}

	public static void repaintFrame() {
		frame.repaint();
	}

	public static void repaintPanel() {
		window.repaint();
	}

	@SuppressWarnings("static-access")
	public static int[] getPixels() {
		if (windowRunnable == null) {
			windowRunnable = new Window();
			return windowRunnable.getPixels();
		} else return windowRunnable.getPixels();
	}

	@SuppressWarnings("static-access")
	public static void setPixels(int[] pixels) {
		windowRunnable.setPixels(pixels);
	}

	public static void ThrowNull(String message) {
		try {
			throw new NullPointException(message);
		} catch (NullPointException e) {
			e.printStackTrace();
		}
	}

	public static void ThrowImpossible(String message) {
		try {
			throw new ImpossibleActionException(message);
		} catch (ImpossibleActionException e) {
			e.printStackTrace();
		}
	}

}
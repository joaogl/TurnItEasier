package joaolourenco.net.pascal.display;

import java.awt.Component;

import joaolourenco.net.pascal.PascalLike.DisplayType;
import javax.swing.JFrame;
import joaolourenco.net.pascal.exceptions.InvalidDisplayTypeException;
import joaolourenco.net.pascal.exceptions.console.ImpossibleActionException;
import joaolourenco.net.pascal.exceptions.console.NullPointException;

public class Display {

	private static DisplayType currentDisplayType;
	private static int width = 250;
	private static int height = 250;
	private static JFrame frame;

	public static void create(DisplayType a) {
		if (a == DisplayType.Console)
			Console.create();
		else if (a == DisplayType.DynamicConsole)
			DynamicConsole.create();
		else if (a == DisplayType.Window)
			Window.create();
		else {
			try {
				throw new InvalidDisplayTypeException(a.toString());
			} catch (InvalidDisplayTypeException e) {
				e.printStackTrace();
			}
		}
		if (a == DisplayType.Console || a == DisplayType.DynamicConsole
				|| a == DisplayType.Window)
			setDisplayType(a);
		else
			setDisplayType(DisplayType.DynamicConsole);
	}

	public static void setDisplayType(DisplayType args) {
		currentDisplayType = args;
	}

	public static boolean isDisplayType(DisplayType a) {
		return (currentDisplayType == a);
	}

	public static DisplayType getDisplayType() {
		return currentDisplayType;
	}

	public static int getWidth() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("width");
			return 0;
		} else
			return width;
	}

	public static int getHeight() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("height");
			return 0;
		} else
			return height;
	}

	public static void setSize(int w, int h) {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("setSize");
		else {
			width = w;
			height = h;
		}
	}

	public static void setWidth(int w) {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("setWidth");
		else
			width = w;
	}

	public static void setHeight(int h) {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("setHeight");
		else
			height = h;
	}

	public static void setLocation(String where) {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("setLocation");
		else {
			if (where == null)
				frame.setLocationRelativeTo(null);
		}
	}

	public static void setLocation(int x, int y) {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("setLocation");
		else
			frame.setLocation(x, y);
	}

	public static void setTitle(String title) {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("setTitle");
		else
			frame.setTitle(title);
	}

	public static void add(Component comp) {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("add");
		else
			frame.add(comp);
	}

	public static void show() {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("show");
		else
			frame.setVisible(true);
	}

	public static void hide() {
		if (isDisplayType(DisplayType.Console))
			ThrowImpossible("hide");
		else
			frame.setVisible(false);
	}

	public static JFrame getFrame() {
		if (isDisplayType(DisplayType.Console)) {
			ThrowNull("a frame");
			return null;
		} else
			return frame;
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
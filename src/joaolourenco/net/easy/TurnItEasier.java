package joaolourenco.net.easy;

import java.awt.Color;
import java.io.Console;
import java.util.Scanner;

import joaolourenco.net.easy.display.Display;
import joaolourenco.net.easy.display.DynamicConsole;
import joaolourenco.net.easy.exceptions.NotIntegerException;
import joaolourenco.net.easy.exceptions.console.ImpossibleActionException;
import joaolourenco.net.easy.exceptions.console.NullPointException;

public class TurnItEasier {

	static Scanner scanIn = new Scanner(System.in);

	public enum DisplayType {
		Console, DynamicConsole, Window;
	}

	public static Byte readKey() {
		if (Display.isDisplayType(DisplayType.Console)) {
			ThrowImpossibleC("readKey");
			return null;
		} else if (Display.isDisplayType(DisplayType.Window)) {
			ThrowImpossibleW("readKey");
			return null;
		} else return DynamicConsole.readKey();

	}

	public static String read() {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read("a");
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			return out;
		}
	}

	public static String readln() {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read("a");
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			return out;
		}
	}

	public static String readPW() {
		Console c = System.console();
		if (c != null) return String.valueOf(System.console().readPassword());
		else {
			try {
				throw new NullPointException("No Java console found.(It as to be exported in order to run properly)", "");
			} catch (ImpossibleActionException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public static int read(int args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read();
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			if (out != null) {
				try {
					return Integer.parseInt(out);
				} catch (Exception e) {
					try {
						throw new NotIntegerException();
					} catch (NotIntegerException ee) {
						ee.printStackTrace();
					}
					return 0;
				}
			} else return 0;
		}
	}

	public static int readln(int args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read();
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			if (out != null) {
				try {
					return Integer.parseInt(out);
				} catch (Exception e) {
					return 0;
				}
			} else return 0;
		}
	}

	public static int readPW(int args) {
		Console c = System.console();
		if (c != null) return Integer.parseInt(String.valueOf(System.console().readPassword()));
		else {
			try {
				throw new NullPointException("No Java console found.(It as to be exported in order to run properly)", "");
			} catch (ImpossibleActionException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	public static void writeln(byte args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	public static void write(byte args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	public static void writeln(char args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	public static void write(char args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	public static void writeln(int args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	public static void write(int args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	public static void writeln(String args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	public static void write(String args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	public static void TextBackground(int color) {
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextBackground");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextBackground");
		else DynamicConsole.TextBackground(color);
	}

	public static void TextBackground(Color color) {
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextBackground");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextBackground");
		else DynamicConsole.TextBackground(color);
	}

	public static void TextColor(int color) {
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextColor");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextColor");
		else DynamicConsole.TextColor(color);
	}

	public static void TextColor(Color color) {
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextColor");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextColor");
		else DynamicConsole.TextColor(color);
	}

	public static void GotoXY(int x, int y) {
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("GotoXY");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("GotoXY");
		else DynamicConsole.GotoXY();
	}

	public static void clrscr() {
		if (Display.isDisplayType(DisplayType.Console)) {
			for (int i = 0; i < 200; i++)
				System.out.println("");
		} else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("clrscr");
		else DynamicConsole.slrscr();
	}

	public static void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void ThrowImpossibleC(String message) {
		try {
			throw new ImpossibleActionException(message);
		} catch (ImpossibleActionException e) {
			e.printStackTrace();
		}
	}

	public static void ThrowImpossibleW(String message) {
		try {
			throw new joaolourenco.net.easy.exceptions.window.ImpossibleActionException(message);
		} catch (ImpossibleActionException e) {
			e.printStackTrace();
		}
	}

	public static void exit(boolean output) {
		if (output) writeln("");
		if (output) writeln("\\*===---===---===---===---===*/");
		if (output) writeln("Closing Program...");
		if (output) writeln("Closing streams...");
		scanIn.close();
		if (output) writeln("Program Closed...");
	}

	public static void exit() {
		scanIn.close();
	}

}
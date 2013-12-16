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

	static Scanner scanIn;

	/**
	 * It contains all the possible DisplayTypes from the TIE API.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @category DisplayType
	 */
	public enum DisplayType {
		Console, DynamicConsole, Window;
	}

	/**
	 * It creates the console streams to reads.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @category Streams
	 */
	public static void createConsoleStreams() {
		Display.create(DisplayType.Console);
		scanIn = new Scanner(System.in);
	}

	/**
	 * It is used to get the pressed key.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @return the key that as been pressed in Bytes.
	 * @throw ImpossibleActionException Thrown when the type console is being used or window.
	 * @category Read
	 */
	public static Byte readKey() {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) {
			ThrowImpossibleC("readKey");
			return null;
		} else if (Display.isDisplayType(DisplayType.Window)) {
			ThrowImpossibleW("readKey");
			return null;
		} else return DynamicConsole.readKey();

	}

	/**
	 * It is used to get text from the user.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @return the user input text as a String.
	 * @category Read
	 */
	public static String read() {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read("a");
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			return out;
		}
	}

	/**
	 * It is used to get text from the user and go to the next line.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @return the user input text as a String.
	 * @category Read
	 */
	public static String readln() {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read("a");
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			return out;
		}
	}

	/**
	 * It is used to get a password from the user.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @return the user input password as a String.
	 * @throw NullPointException Thrown where the Java console isn't found.
	 * @category Read
	 */
	public static String readPW() {
		if (!Display.isDisplayCreated()) createConsoleStreams();
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

	/**
	 * It is used to get numbers from the user.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            type in any number to assume the return as an integer.
	 * @return the user input number as a Integer.
	 * @throw NotIntegerException Thrown when the return value isn't a integer.
	 * @category Read
	 */
	public static int read(int args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
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

	/**
	 * It is used to get numbers from the user and go to the next line.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            type in any number to assume the return as an integer.
	 * @return the user input number as a Integer.
	 * @throw NotIntegerException Thrown when the input is not an integer.
	 * @category Read
	 */
	public static int readln(int args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
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

	/**
	 * It is used to get password from the user as an Integer.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            type in any number to assume the return as an integer.
	 * @return the user input number as a Integer.
	 * @throw NullPointException Thrown when the Java console isn't available.
	 * @category Read
	 */
	public static int readPW(int args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
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

	/**
	 * It is used to write on the screen and go to the next line.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a byte.
	 * @category Write
	 */
	public static void writeln(byte args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	/**
	 * It is used to write on the screen.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a byte.
	 * @category Write
	 */
	public static void write(byte args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	/**
	 * It is used to write on the screen and go to the next line.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a char.
	 * @category Write
	 */
	public static void writeln(char args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	/**
	 * It is used to write on the screen.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a char.
	 * @category Write
	 */
	public static void write(char args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	/**
	 * It is used to write on the screen and go to the next line.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a integer.
	 * @category Write
	 */
	public static void writeln(int args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	/**
	 * It is used to write on the screen.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a integer.
	 * @category Write
	 */
	public static void write(int args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	/**
	 * It is used to write on the screen and go to the next line.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a String.
	 * @category Write
	 */
	public static void writeln(String args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	/**
	 * It is used to write on the screen.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param args
	 *            it is what you want to write as a String.
	 * @category Write
	 */
	public static void write(String args) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	/**
	 * It is used to change the background color.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param color
	 *            the desired color for the background as integer.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category BackGroundColor
	 */
	public static void TextBackground(int color) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextBackground");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextBackground");
		else DynamicConsole.TextBackground(color);
	}

	/**
	 * It is used to change the background color.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param color
	 *            the desired color for the background as Color.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category BackGroundColor
	 */
	public static void TextBackground(Color color) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextBackground");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextBackground");
		else DynamicConsole.TextBackground(color);
	}

	/**
	 * It is used to change the text color.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param color
	 *            the desired color for the background as integer.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category TextColor
	 */
	public static void TextColor(int color) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextColor");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextColor");
		else DynamicConsole.TextColor(color);
	}

	/**
	 * It is used to change the text color.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param color
	 *            the desired color for the background as integer.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category TextColor
	 */
	public static void TextColor(Color color) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("TextColor");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("TextColor");
		else DynamicConsole.TextColor(color);
	}

	/**
	 * It is used to go to a certain location in the console.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param x
	 *            the x value of where to go.
	 * @param y
	 *            y the y value of where to go.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category WindowNavigation
	 */
	public static void GotoXY(int x, int y) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) ThrowImpossibleC("GotoXY");
		else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("GotoXY");
		else DynamicConsole.GotoXY();
	}

	/**
	 * It is used to clear the console.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @throw ImpossibleActionException Thrown when the DisplayType is window.
	 * @category ConsoleText
	 */
	public static void clrscr() {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) {
			for (int i = 0; i < 200; i++)
				System.out.println("");
		} else if (Display.isDisplayType(DisplayType.Window)) ThrowImpossibleW("clrscr");
		else DynamicConsole.slrscr();
	}

	/**
	 * It is used to add a delay on the aplication.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param time
	 *            value of time as integer in milliseconds.
	 * @throw InterruptedException Thrown when the Thread is Interrupted for somereason.
	 * @category AplicationControl
	 */
	public static void delay(int time) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * It is to make sure the scanners are closed.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @param output
	 *            true or false to use or not the output text.
	 * @category AplicationControl
	 */
	public static void exit(boolean output) {
		if (output) writeln("");
		if (output) writeln("\\*===---===---===---===---===*/");
		if (output) writeln("Closing Program...");
		if (output && (scanIn != null)) writeln("Closing streams...");
		if (scanIn != null) scanIn.close();
		if (output) writeln("Program Closed...");
	}

	/**
	 * It is to make sure the scanners are closed without output text.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @category AplicationControl
	 */
	public static void exit() {
		if (scanIn != null) scanIn.close();
	}

	/**
	 * It is used to throw ImpossibleActionExceptions.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @throw ImpossibleActionException Thrown always.
	 * @category AplicationControl
	 */
	private static void ThrowImpossibleC(String message) {
		try {
			throw new ImpossibleActionException(message);
		} catch (ImpossibleActionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * It is used to throw ImpossibleActionExceptions.
	 * <p>
	 * 
	 * @author Jo�o Louren�o
	 * @throw ImpossibleActionException Thrown always.
	 * @category AplicationControl
	 */
	private static void ThrowImpossibleW(String message) {
		try {
			throw new joaolourenco.net.easy.exceptions.window.ImpossibleActionException(message);
		} catch (ImpossibleActionException e) {
			e.printStackTrace();
		}
	}

}
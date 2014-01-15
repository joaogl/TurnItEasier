package net.joaolourenco.easy;

import java.awt.Color;
import java.io.Console;
import java.util.Scanner;
import java.util.logging.Logger;

import net.joaolourenco.easy.display.Display;
import net.joaolourenco.easy.display.DynamicConsole;
import net.joaolourenco.easy.exceptions.NotIntegerException;
import net.joaolourenco.easy.exceptions.console.ImpossibleActionException;
import net.joaolourenco.easy.exceptions.console.NullPointException;

/**
 * This is the main class of the API. It is used for all the console functions.
 * <p>
 * 
 * @author João Lourenço
 * @category Class
 */
public class TurnItEasier {

	/**
	 * It is the scanner that the API uses to read the input from the user.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @category Variables
	 */
	static Scanner scanIn;
	static Logger logger = Logger.getLogger("TurnItEasier");

	/**
	 * It contains all the possible DisplayTypes from the TIE API.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @category DisplayType
	 */
	public enum DisplayType {
		Console, DynamicConsole, Window;
	}

	/**
	 * It creates the console streams to reads.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @category Streams
	 */
	private static void createConsoleStreams() {
		Display.create(DisplayType.Console);
		scanIn = new Scanner(System.in);
	}

	/**
	 * It is used to get the pressed key.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @return the key that as been pressed in Bytes.
	 * @throw ImpossibleActionException Thrown when the type console is being used or window.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW()
	 * @see #readPW(int)
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
	 * @author João Lourenço
	 * @return the user input text as a String.
	 * @category Read
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW()
	 * @see #readPW(int)
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
	 * @author João Lourenço
	 * @return the user input text as a String.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln(int)
	 * @see #readPW()
	 * @see #readPW(int)
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
	 * @author João Lourenço
	 * @return the user input password as a String.
	 * @throw NullPointException Thrown where the Java console isn't found.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW(int)
	 */
	public static String readPW() {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		Console c = System.console();
		if (c != null) return String.valueOf(System.console().readPassword());
		else {
			try {
				throw new NullPointException("No Java console found.(It as to be exported in order to run properly)", "");
			} catch (NullPointException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * It is used to get numbers from the user.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @param args
	 *            type in any number to assume the return as an integer.
	 * @return the user input number as a Integer.
	 * @throw NotIntegerException Thrown when the return value isn't a integer.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW()
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
					logger.severe("The input is not an Integer!");
					return 0;
				}
			} else return 0;
		}
	}

	/**
	 * It is used to get numbers from the user and go to the next line.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @param args
	 *            type in any number to assume the return as an integer.
	 * @return the user input number as a Integer.
	 * @throw NotIntegerException Thrown when the input is not an integer.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readPW()
	 * @see #readPW(int)
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
	 * @author João Lourenço
	 * @param args
	 *            type in any number to assume the return as an integer.
	 * @return the user input number as a Integer.
	 * @throw NullPointException Thrown when the Java console isn't available.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW()
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

	
	
	
	public static void read(String varToAssignTo) {
		if (!Display.isDisplayCreated()) createConsoleStreams();
		String out = null;
		if (scanIn.hasNextLine()) {
			varToAssignTo = scanIn.nextLine();
			System.out.println("A: " + varToAssignTo);
		}
	}
	
	
	
	

	/**
	 * It is used to write on the screen and go to the next line.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a byte.
	 * @category Write
	 * @see #write(byte)
	 * @see #write(char)
	 * @see #write(int)
	 * @see #write(String)
	 * @see #writeln(char)
	 * @see #writeln(int)
	 * @see #writeln(String)
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
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a byte.
	 * @category Write
	 * @see #write(char)
	 * @see #write(int)
	 * @see #write(String)
	 * @see #writeln(byte)
	 * @see #writeln(char)
	 * @see #writeln(int)
	 * @see #writeln(String)
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
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a char.
	 * @category Write
	 * @see #write(byte)
	 * @see #write(char)
	 * @see #write(int)
	 * @see #write(String)
	 * @see #writeln(byte)
	 * @see #writeln(int)
	 * @see #writeln(String)
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
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a char.
	 * @category Write
	 * @see #write(byte)
	 * @see #write(int)
	 * @see #write(String)
	 * @see #writeln(byte)
	 * @see #writeln(char)
	 * @see #writeln(int)
	 * @see #writeln(String)
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
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a integer.
	 * @category Write
	 * @see #write(byte)
	 * @see #write(char)
	 * @see #write(int)
	 * @see #write(String)
	 * @see #writeln(byte)
	 * @see #writeln(char)
	 * @see #writeln(String)
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
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a integer.
	 * @category Write
	 * @see #write(byte)
	 * @see #write(char)
	 * @see #write(String)
	 * @see #writeln(byte)
	 * @see #writeln(char)
	 * @see #writeln(int)
	 * @see #writeln(String)
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
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a String.
	 * @category Write
	 * @see #write(byte)
	 * @see #write(char)
	 * @see #write(int)
	 * @see #write(String)
	 * @see #writeln(byte)
	 * @see #writeln(char)
	 * @see #writeln(int)
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
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a String.
	 * @category Write
	 * @see #write(byte)
	 * @see #write(char)
	 * @see #write(int)
	 * @see #writeln(byte)
	 * @see #writeln(char)
	 * @see #writeln(int)
	 * @see #writeln(String)
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
	 * @author João Lourenço
	 * @param color
	 *            the desired color for the background as integer.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category BackGroundColor
	 * @see #TextBackground(Color)
	 * @see #TextColor(Color)
	 * @see #TextColor(int)
	 * 
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
	 * @author João Lourenço
	 * @param color
	 *            the desired color for the background as Color.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category BackGroundColor
	 * @see #TextBackground(int)
	 * @see #TextColor(Color)
	 * @see #TextColor(int)
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
	 * @author João Lourenço
	 * @param color
	 *            the desired color for the background as integer.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category TextColor
	 * @see #TextBackground(Color)
	 * @see #TextBackground(int)
	 * @see #TextColor(Color)
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
	 * @author João Lourenço
	 * @param color
	 *            the desired color for the background as integer.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category TextColor
	 * @see #TextBackground(Color)
	 * @see #TextBackground(int)
	 * @see #TextColor(int)
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
	 * @author João Lourenço
	 * @param x
	 *            the x value of where to go.
	 * @param y
	 *            y the y value of where to go.
	 * @throw ImpossibleActionException Thrown when the DisplayType is console or window.
	 * @category WindowNavigation
	 * @see #clrscr()
	 * @see #delay(int)
	 * @see #exit()
	 * @see #exit(boolean)
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
	 * @author João Lourenço
	 * @throw ImpossibleActionException Thrown when the DisplayType is window.
	 * @category ConsoleText
	 * @see #delay(int)
	 * @see #GotoXY(int, int)
	 * @see #exit()
	 * @see #exit(boolean)
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
	 * @author João Lourenço
	 * @param time
	 *            value of time as integer in milliseconds.
	 * @throw InterruptedException Thrown when the Thread is Interrupted for somereason.
	 * @category AplicationControl
	 * @see #clrscr()
	 * @see #GotoXY(int, int)
	 * @see #exit()
	 * @see #exit(boolean)
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
	 * @author João Lourenço
	 * @param output
	 *            true or false to use or not the output text.
	 * @category AplicationControl
	 * @see #clrscr()
	 * @see #delay(int)
	 * @see #GotoXY(int, int)
	 * @see #exit()
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
	 * @author João Lourenço
	 * @category AplicationControl
	 * @see #clrscr()
	 * @see #delay(int)
	 * @see #GotoXY(int, int)
	 * @see #exit(boolean)
	 */
	public static void exit() {
		if (scanIn != null) scanIn.close();
	}

	/**
	 * It is used to throw ImpossibleActionExceptions.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @throw ImpossibleActionException Thrown always.
	 * @category AplicationControl
	 * @see #ThrowImpossibleW(String)
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
	 * @author João Lourenço
	 * @throw ImpossibleActionException Thrown always.
	 * @category AplicationControl
	 * @see #ThrowImpossibleC(String)
	 */
	private static void ThrowImpossibleW(String message) {
		try {
			throw new net.joaolourenco.easy.exceptions.window.ImpossibleActionException(message);
		} catch (ImpossibleActionException e) {
			e.printStackTrace();
		}
	}

}
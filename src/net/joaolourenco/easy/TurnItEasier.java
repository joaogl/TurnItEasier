/**
 * This file is part of TurnItEasier.

    TurnItEasier is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    TurnItEasier is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Hygienic.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.joaolourenco.easy;

import java.awt.Color;
import java.io.Console;
import java.util.Scanner;
import java.util.logging.Logger;

import net.joaolourenco.easy.display.Display;
import net.joaolourenco.easy.display.DynamicConsole;
import net.joaolourenco.easy.display.Window;

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

	/**
	 * This is the API Name
	 * <p>
	 * 
	 * @author João Lourenço
	 * @category Variables
	 */
	public static String NAME = "TurnItEasier";

	/**
	 * This is the API Version
	 * <p>
	 * 
	 * @author João Lourenço
	 * @category Variables
	 */
	public static String VERSION = "BETA V1.0.1";

	/**
	 * It is the logger that the API uses to print errors to the user.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @category Variables
	 */
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
		if (!Display.isDisplayCreated()) {
			Display.create(DisplayType.Console);
			scanIn = new Scanner(System.in);
		}
	}

	/**
	 * It is used to get the pressed key.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @return the key that as been pressed in Int.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW()
	 * @see #readPW(int)
	 */
	public static int readKey() {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) {
			logger.severe("The requested action(readKey) is not possible on default console.");
			return 0;
		} else if (Display.isDisplayType(DisplayType.Window)) return Window.getKey();
		else return DynamicConsole.readKey();
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
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read();
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			return out;
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
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW()
	 */
	public static int read(int args) {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read(args);
		else if (Display.isDisplayType(DisplayType.Window)) logger.severe("The requested action(readKey) is not possible on default console.");
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
			}
		}
		return 0;
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
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read();
		else {
			String out = null;
			if (scanIn.hasNextLine()) out = scanIn.nextLine();
			return out;
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
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readPW()
	 * @see #readPW(int)
	 */
	public static int readln(int args) {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read(args);
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
	 * It is used to get a password from the user.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @return the user input password as a String.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW(int)
	 */
	public static String readPW() {
		createConsoleStreams();
		Console c = System.console();
		if (c != null) return String.valueOf(System.console().readPassword());
		else logger.severe("No Java console found.(It as to be exported in order to run properly)");
		return null;
	}

	/**
	 * It is used to get password from the user as an Integer.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @param args
	 *            type in any number to assume the return as an integer.
	 * @return the user input number as a Integer.
	 * @category Read
	 * @see #read()
	 * @see #read(int)
	 * @see #readKey()
	 * @see #readln()
	 * @see #readln(int)
	 * @see #readPW()
	 */
	public static int readPW(int args) {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.readPW(args);
		else {
			Console c = System.console();
			if (c != null) return Integer.parseInt(String.valueOf(System.console().readPassword()));
			else {
				logger.severe("No Java console found.(It as to be exported in order to run properly)");
				return 0;
			}
		}
	}

	/**
	 * It is used to write on the screen and go to the next line.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @param args
	 *            it is what you want to write as a Object.
	 * @category Write
	 * @see #write(Object)
	 */
	public static void writeln(Object args) {
		createConsoleStreams();
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
	 * @see #writeln(Object)
	 */
	public static void write(Object args) {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

	/**
	 * It is used to change the background color.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @param color
	 *            the desired color for the background as Integer or Color.
	 * @category BackGroundColor
	 * @see #TextBackground(int)
	 * @see #TextColor(Object)
	 */
	public static void TextBackground(Object color) {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) logger.severe("The requested action(TextBackground(Object)) is not possible on default console.");
		else if (Display.isDisplayType(DisplayType.Window)) logger.severe("The requested action(TextBackground(Object)) is not possible on windows.");
		else {
			try {
				if (color instanceof Color) DynamicConsole.TextBackground((Color) color);
				else DynamicConsole.TextBackground(new Color((int) color));
			} catch (Exception e) {
				logger.severe("The requested color: " + color + " doesnt exist or is in the wrong format. It as to be an int or Color format.");
			}
		}
	}

	/**
	 * It is used to change the text color.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @param color
	 *            the desired color for the background as intege or Colorr.
	 * @category TextColor
	 * @see #TextBackground(Color)
	 * @see #TextBackground(int)
	 */
	public static void TextColor(Object color) {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) logger.severe("The requested action(TextColor(Object)) is not possible on default console.");
		else if (Display.isDisplayType(DisplayType.Window)) logger.severe("The requested action(TextColor(Object)) is not possible on windows.");
		else {
			try {
				if (color instanceof Color) DynamicConsole.TextColor((Color) color);
				else DynamicConsole.TextColor(new Color((int) color));
			} catch (Exception e) {
				logger.severe("The requested color: " + color + " doesnt exist or is in the wrong format. It as to be an int or Color format.");
			}
		}
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
	 * @category WindowNavigation
	 * @see #clrscr()
	 * @see #delay(int)
	 * @see #exit()
	 * @see #exit(boolean)
	 */
	public static void GotoXY(int x, int y) {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) logger.severe("The requested action(GotoXY(int, int)) is not possible on default console.");
		else if (Display.isDisplayType(DisplayType.Window)) logger.severe("The requested action(GotoXY(int, int)) is not possible on windows.");
		else DynamicConsole.GotoXY();
	}

	/**
	 * It is used to clear the console.
	 * <p>
	 * 
	 * @author João Lourenço
	 * @category ConsoleText
	 * @see #delay(int)
	 * @see #GotoXY(int, int)
	 * @see #exit()
	 * @see #exit(boolean)
	 */
	public static void clrscr() {
		createConsoleStreams();
		if (Display.isDisplayType(DisplayType.Console)) {
			for (int i = 0; i < 200; i++)
				System.out.println("");
		} else if (Display.isDisplayType(DisplayType.Window)) logger.severe("The requested action(clrscr) is not possible on windows.");
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
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	public static String version() {
		return NAME + " - " + VERSION;
	}

}
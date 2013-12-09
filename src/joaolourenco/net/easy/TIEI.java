package joaolourenco.net.easy;

import java.io.Console;
import java.util.Scanner;

import joaolourenco.net.easy.TurnItEasier.DisplayType;
import joaolourenco.net.easy.display.Display;
import joaolourenco.net.easy.display.DynamicConsole;
import joaolourenco.net.easy.exceptions.NotIntegerException;
import joaolourenco.net.easy.exceptions.console.ImpossibleActionException;
import joaolourenco.net.easy.exceptions.console.NullPointException;

public class TIEI {

	public static int read() {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read();
		else {
			String out = null;
			Scanner scanIn = new Scanner(System.in);
			out = scanIn.nextLine();
			scanIn.close();
			if (!out.isEmpty()) {
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

	public static int readln() {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read();
		else {
			String out = null;
			Scanner scanIn = new Scanner(System.in);
			out = scanIn.nextLine();
			scanIn.close();
			if (!out.isEmpty()) {
				try {
					return Integer.parseInt(out);
				} catch (Exception e) {
					return 0;
				}
			} else return 0;
		}
	}

	public static int readPW(String args) {
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

	public static void writeln(int args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	public static void write(int args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

}
package joaolourenco.net.easy;

import java.io.Console;
import java.util.Scanner;

import joaolourenco.net.easy.TurnItEasier.DisplayType;
import joaolourenco.net.easy.display.Display;
import joaolourenco.net.easy.display.DynamicConsole;
import joaolourenco.net.easy.exceptions.console.ImpossibleActionException;
import joaolourenco.net.easy.exceptions.console.NullPointException;

public class TIES {

	public static String read() {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read("a");
		else {
			String out = null;
			Scanner scanIn = new Scanner(System.in);
			out = scanIn.nextLine();
			scanIn.close();
			return out;
		}
	}

	public static String readln() {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) return DynamicConsole.read("a");
		else {
			String out = null;
			Scanner scanIn = new Scanner(System.in);
			out = scanIn.nextLine();
			scanIn.close();
			return out;
		}
	}

	public static String readPW(String args) {
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

	public static void writeln(String args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.writeln(args);
		else System.out.println(args);
	}

	public static void write(String args) {
		if (Display.isDisplayType(DisplayType.DynamicConsole)) DynamicConsole.write(args);
		else System.out.print(args);
	}

}
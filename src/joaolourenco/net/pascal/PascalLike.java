package joaolourenco.net.pascal;

import java.awt.Color;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import joaolourenco.net.pascal.display.Display;
import joaolourenco.net.pascal.display.DynamicConsole;
import joaolourenco.net.pascal.exceptions.console.ImpossibleActionException;

public class PascalLike {

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
		} else
			return DynamicConsole.readKey();
	}

	public static String read() {
		if (Display.isDisplayType(PascalLike.DisplayType.DynamicConsole))
			return DynamicConsole.read();
		else {
			String out = null;
			Scanner scanIn = new Scanner(System.in);
			out = scanIn.nextLine();
			scanIn.close();
			return out;
		}
	}

	public static String readPW() {
		Console c = System.console();
		if (c != null)
			return String.valueOf(System.console().readPassword());
		else {
			String out = null;
			try {
				InputStream in = System.in;
				byte[] b = new byte[50];
				int l = in.read(b);
				l--;
				if (l > 0) {
					byte[] e = new byte[l];
					System.arraycopy(b, 0, e, 0, l);
					out = new String(e);
				} else
					out = "No password to read.";
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return out;
		}
	}

	public static void writeln(String args) {
		if (Display.isDisplayType(PascalLike.DisplayType.DynamicConsole))
			DynamicConsole.writeln(args);
		else
			System.out.println(args);
	}

	public static void write(String args) {
		if (Display.isDisplayType(PascalLike.DisplayType.DynamicConsole))
			DynamicConsole.write(args);
		else
			System.out.print(args);
	}

	public static void TextBackground(int color) {
		if (Display.isDisplayType(DisplayType.Console))
			ThrowImpossibleC("TextBackground");
		else if (Display.isDisplayType(DisplayType.Window))
			ThrowImpossibleW("TextBackground");
		else
			DynamicConsole.TextBackground(color);
	}

	public static void TextBackground(Color color) {
		if (Display.isDisplayType(DisplayType.Console))
			ThrowImpossibleC("TextBackground");
		else if (Display.isDisplayType(DisplayType.Window))
			ThrowImpossibleW("TextBackground");
		else
			DynamicConsole.TextBackground(color);
	}

	public static void TextColor(int color) {
		if (Display.isDisplayType(DisplayType.Console))
			ThrowImpossibleC("TextColor");
		else if (Display.isDisplayType(DisplayType.Window))
			ThrowImpossibleW("TextColor");
		else
			DynamicConsole.TextColor(color);
	}

	public static void TextColor(Color color) {
		if (Display.isDisplayType(DisplayType.Console))
			ThrowImpossibleC("TextColor");
		else if (Display.isDisplayType(DisplayType.Window))
			ThrowImpossibleW("TextColor");
		else
			DynamicConsole.TextColor(color);
	}

	public static void GotoXY(int x, int y) {
		if (Display.isDisplayType(DisplayType.Console))
			ThrowImpossibleC("GotoXY");
		else if (Display.isDisplayType(DisplayType.Window))
			ThrowImpossibleW("GotoXY");
		else
			DynamicConsole.GotoXY();
	}

	public static void clrscr() {
		if (Display.isDisplayType(DisplayType.Console))
			ThrowImpossibleC("clrscr");
		else if (Display.isDisplayType(DisplayType.Window))
			ThrowImpossibleW("clrscr");
		else
			DynamicConsole.slrscr();
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
			throw new joaolourenco.net.pascal.exceptions.window.ImpossibleActionException(
					message);
		} catch (ImpossibleActionException e) {
			e.printStackTrace();
		}
	}

}
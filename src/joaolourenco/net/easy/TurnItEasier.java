package joaolourenco.net.easy;

import java.awt.Color;

import joaolourenco.net.easy.display.Display;
import joaolourenco.net.easy.display.DynamicConsole;
import joaolourenco.net.easy.exceptions.console.ImpossibleActionException;

public class TurnItEasier {

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

	public static void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
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

}
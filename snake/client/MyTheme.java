package snake.client;

import java.awt.Color;
import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class MyTheme extends DefaultMetalTheme {
	Color color;
	MyTheme() {
		color = new Color(166, 221, 247);
	}
	@Override
	public ColorUIResource getWindowTitleInactiveBackground() {
		return new ColorUIResource(color);
	}
	
	@Override
	public ColorUIResource getWindowTitleInactiveForeground() {
		return new ColorUIResource(color);
	}

	public ColorUIResource getWindowTitleBackground() {
		return new ColorUIResource(color);
	}
	
	public ColorUIResource getPrimaryControlInfo() {
		return new ColorUIResource(color);
	}
	
	public ColorUIResource getPrimaryControlHighlight() {
		return new ColorUIResource(color);
	}
	
	public ColorUIResource getPrimaryControlDarkShadow() {
		return new ColorUIResource(color);
	}
	
	public ColorUIResource getPrimaryControl() {
		return new ColorUIResource(Color.WHITE);
	}
	
	public ColorUIResource getControlHighlight() {
		return new ColorUIResource(color);
	}
	
	public ColorUIResource getControlDisabled() {
		return new ColorUIResource(color);
	}
	
	public ColorUIResource getControlDarkShadow() {
		return new ColorUIResource(color);
	}
	
	public ColorUIResource getControl() {
		return new ColorUIResource(Color.white);
	}
	public ColorUIResource getPrimary1() {
		return new ColorUIResource(color);
	}
	public ColorUIResource getPrimary2() {
		return new ColorUIResource(color);
	}
	public ColorUIResource getBlack() {
		return new ColorUIResource(color);
	}
	public ColorUIResource getSystemTextColor() {
		return new ColorUIResource(color);
	}
}

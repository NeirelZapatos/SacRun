package com.csus.csc133;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;

public class ModifiedButton extends Button{
	public ModifiedButton(Command cmd) {
		super(cmd);
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(0x006400);
		getAllStyles().setBorder(Border.createLineBorder(2, 0xFFD700));
		getAllStyles().setFgColor(0xB8860B);
	}
}

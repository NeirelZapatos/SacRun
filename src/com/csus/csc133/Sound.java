package com.csus.csc133;

import com.codename1.ui.Display;
import java.io.InputStream;
import com.codename1.media.*;

public class Sound{
	private Media m;
	
	public Sound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			m = MediaManager.createMedia(is, "audio/wave");
			m.setVolume(5);
		}
		catch(Exception err) {
			err.printStackTrace();
		}	
	}
	
	public void play() {
		if(m.getTime() >= m.getDuration() - 1000) {
			m.setTime(0);
		}
		m.play();
	}
	
	public void pause() {
		m.pause();
	}
}

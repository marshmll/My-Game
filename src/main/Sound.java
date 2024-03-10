package main;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	private Clip clip;
	private Map<String, URL> soundURLs = new HashMap<String, URL>();

	public Sound() {

		soundURLs.put("digestive_biscuit", getClass().getResource("/sounds/Kubbi - Digestive biscuit.wav"));

		soundURLs.put("underclocked",
				getClass().getResource("/sounds/Eric Skiff - Underclocked (underunderclocked mix).wav"));

		soundURLs.put("dungeon_boss", getClass().getResource("/sounds/Kevin MacLeod - 8bit Dungeon Boss.wav"));

		soundURLs.put("pop", getClass().getResource("/sounds/pop.wav"));
	}

	public void setFile(String name) {

		try {

			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURLs.get(name));
			clip = AudioSystem.getClip();
			clip.open(ais);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {

		clip.loop(1); // Loop method is used to avoid not playing sound bug
					  // when running background music.
	}

	public void setVolume(float volume) {

		if (volume < 0f || volume > 1f)
			throw new IllegalArgumentException("Volume not valid: " + volume);

		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(20f * (float) Math.log10(volume));

	}

	public void loop() {

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {

		clip.stop();
	}

}

package prop;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PROP_Antidote extends Prop {

	public PROP_Antidote() {
		
		this.name = "Antidote";
		try {

			this.image = ImageIO.read(getClass().getResourceAsStream("/props/antidote.png"));

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		this.isCollideable = true;
	}
}

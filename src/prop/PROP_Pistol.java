package prop;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PROP_Pistol extends Prop {

	public PROP_Pistol() {
		
		this.name = "Pistol";
		try {

			this.image = ImageIO.read(getClass().getResourceAsStream("/props/pistol_0.png"));

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		this.isCollideable = true;
	}
}

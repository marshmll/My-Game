package prop;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PROP_Chest extends Prop {

	public PROP_Chest() {
		
		this.name = "Chest";
		try {

			this.image = ImageIO.read(getClass().getResourceAsStream("/props/chest.png"));

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		this.isCollideable = true;
	}
}

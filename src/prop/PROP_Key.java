package prop;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PROP_Key extends Prop {
	
	public PROP_Key() {
		
		this.name = "Key";
		try {
			
			this.image = ImageIO.read(getClass().getResourceAsStream("/props/key.png"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		this.isCollideable = true;
	}
}

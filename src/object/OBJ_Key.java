package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key {

	public OBJ_Key() {
		
		name = "Key";
		try {
			ImageIO.read(getClass().getResourceAsStream("/objects/cilantro.png"));
	
		}catch(IOException e) {
			e.printStackTrace();
		
		}
	}
}

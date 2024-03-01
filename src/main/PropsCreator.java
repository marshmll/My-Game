package main;

import prop.PROP_Antidote;
import prop.PROP_Chest;
import prop.PROP_Key;
import prop.PROP_Pistol;

public class PropsCreator {

	GamePanel gp;

	public PropsCreator(GamePanel gp) {
		this.gp = gp;
	}

	public void createMapProps() {

		gp.props[0] = instantiateKeyProp(46, 65);
		gp.props[1] = instantiateKeyProp(125, 4);
		gp.props[2] = instantiatePistolProp(133, 82);
		gp.props[3] = instantiateAntidoteProp(69, 80);
		gp.props[4] = instantiatePistolProp(98, 50);
		gp.props[5] = instantiateKeyProp(99, 50);
		gp.props[6] = instantiateAntidoteProp(100, 50);
		gp.props[7] = instantiateChestProp(96, 50);
	}

	private PROP_Antidote instantiateAntidoteProp(int x, int y) {
		PROP_Antidote antidote = new PROP_Antidote();
		antidote.worldX = x * gp.tileSize;
		antidote.worldY = y * gp.tileSize;
		return antidote;
	}

	private PROP_Chest instantiateChestProp(int x, int y) {
		PROP_Chest chest = new PROP_Chest();
		chest.worldX = x * gp.tileSize;
		chest.worldY = y * gp.tileSize;
		return chest;
	}

	private PROP_Key instantiateKeyProp(int x, int y) {
		PROP_Key key = new PROP_Key();
		key.worldX = x * gp.tileSize;
		key.worldY = y * gp.tileSize;
		return key;
	}

	private PROP_Pistol instantiatePistolProp(int x, int y) {
		PROP_Pistol pistol = new PROP_Pistol();
		pistol.worldX = x * gp.tileSize;
		pistol.worldY = y * gp.tileSize;
		return pistol;
	}
}

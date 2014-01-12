package org.hyperion.rs2.util;

public class Misc {

	public static final boolean goodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
        int deltaX = objectX - playerX;
        int deltaY = objectY - playerY;
        int trueDistance = ((int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
        return trueDistance <= distance;
	}
	
	public static int random(int range) {
		int number = (int) (Math.random() * (range + 1));
		return number < 0 ? 0 : number;
	}
	
}

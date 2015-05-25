/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwaroline;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author czw07
 */
public class Explode {
	int x, y;

	private int[] diameters = { 4, 7, 12, 18, 26, 32, 49, 30, 14, 6 };

	private boolean live = true;

	private TankClient tc;

	int step = 0;
	
	/**
	 * new explode 
	 * @param x  axle of explode
	 * @param y axle of explode
	 * @param tc spot of explode
	 * @see TankClient
	 */
	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	/**
	 * explode circle
	 * @param g 
	 * @see java.awt.Graphics
	 */
	public void draw(Graphics g) {
		if (!live) {
			tc.explodes.remove(this);
			return;
		}

		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameters[step], diameters[step]);
		g.setColor(c);

		step++;
		if (step == diameters.length) {
			live = false;
		}
	}
}

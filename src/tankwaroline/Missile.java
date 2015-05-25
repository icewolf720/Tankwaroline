package tankwaroline;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Missile {
	/**
	 * missile speed of X
	 */
	public static final int XSPEED = 10;
	/**
	 * missile speed of Y
	 */
	public static final int YSPEED = 10;
	/**
	 * wide of missile
	 */
	public static final int WIDTH = 10;
	/**
	 * height of missile
	 */
	public static final int HEIGHT = 10;

	private static int ID = 1;

	TankClient tc;

	int tankId;

	int id;

	int x, y;

	Dir dir = Dir.R;

	boolean live = true;

	boolean good;
	
	/**
	 * 
	 * @param tankId 
	 * @param x 
	 * @param y 
	 * @param good   good missile or not
	 * @param dir  missile direction
	 * @see Dir
	 */
	
	public Missile(int tankId, int x, int y, boolean good, Dir dir) {
		this.tankId = tankId;
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir = dir;
		this.id = ID++;
	}
	
	/**
	 * 
	 * @param tankId
	 * @param x
	 * @param y
	 * @param good
	 * @param dir
	 * @param tc 
	 * @see TankClient
	 */
	public Missile(int tankId, int x, int y, boolean good, Dir dir,
			TankClient tc) {
		this(tankId, x, y, good, dir);
		this.tc = tc;
	}
	
	/**
	 * draw missile
	 * @param g 
	 */
	public void draw(Graphics g) {
		if (!live) {
			tc.missiles.remove(this);
			return;
		}

		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);

		move();
	}

	private void move() {
		switch (dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}

		if (x < 0 || y < 0 || x > TankClient.GAME_WIDTH
				|| y > TankClient.GAME_HEIGHT) {
			live = false;
		}
	}
	
	/**
	 * get rectangle of missle
	 * @return Rectangle
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * heck whether missile shoot the tank
	 * @param t  tank 
	 * @return true or false
	 */
	public boolean hitTank(Tank t) {
		if (this.live && t.isLive() && this.good != t.good
				&& this.getRect().intersects(t.getRect())) {
			this.live = false;
			t.setLive(false);
			tc.explodes.add(new Explode(x, y, tc));
			return true;
		}
		return false;
	}
	
	/**
	 * 检测是否撞到一系列坦克中的一个
	 * @param tanks 被检测的坦克序列
	 * @return 如果撞到其中一个,返回true,否则返回false
	 
	public boolean hitTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			if (this.hitTank(tanks.get(i))) {
				return true;
			}
		}
		return false;
	}
        * */
}

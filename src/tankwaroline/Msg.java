/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwaroline;

import java.io.DataInputStream;
import java.net.DatagramSocket;

/**
 *
 * @author czw07
 */
public interface Msg {
	
	public static final int TANK_NEW_MSG = 1;
	
	
	public static final int TANK_MOVE_MSG = 2;
	
	
	public static final int MISSILE_NEW_MSG = 3;
	
	
	public static final int TANK_DEAD_MSG = 4;
	
	
	public static final int MISSILE_DEAD_MSG = 5;
	
	/**
	 * send data
	 * @param ds
	 * @param IP
	 * @param udpPort
	 */
	public void send(DatagramSocket ds, String IP, int udpPort);
	
	/**
	 * receive data
	 * @param dis
	 */
	public void parse(DataInputStream dis);
}

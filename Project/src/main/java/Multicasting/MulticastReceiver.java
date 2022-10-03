package Multicasting;

import java.net.*;
import java.io.*;

public class MulticastReceiver {
	public static final int PORT = 1201;

	public static void main(String args[]) throws Exception {
		MulticastSocket socket;
		DatagramPacket packet;
		InetAddress address;
		
		String group = "225.4.5.6";
		address = InetAddress.getByName(group);
		socket = new MulticastSocket(MulticastSender.PORT);

		// join a Multicast group and send the group
		System.out.println("Starting multicast receiver in group " + group + " on port " + PORT);
		
		socket.joinGroup(address);
		byte[] buf = new byte[1024];
		packet = new DatagramPacket(buf, buf.length);

		// receive the packets
		socket.receive(packet);
		String str = new String(packet.getData());
		String inetaddress = (packet.getAddress().getHostAddress());
		System.out.println("Received message from IP address: " + packet.getAddress().toString() + ":" + packet.getPort()
				+ " with length: " + packet.getLength());
		System.out.write(packet.getData(), 0, packet.getLength());
		System.out.println();


		//Leave group and close the socket connection
		socket.leaveGroup(InetAddress.getByName(group));
		socket.close();
 
	}// end main

}// end class MulticastReceiver

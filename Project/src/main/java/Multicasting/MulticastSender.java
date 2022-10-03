package Multicasting;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;

public class MulticastSender {
	public static final int PORT = 1201;

	public static void main(String args[]) throws Exception {
		MulticastSocket socket;
		DatagramPacket packet;
		InetAddress address;

		//address 
		String group = "225.4.5.6";
		//ttl 
		int ttl = 1;

		address = InetAddress.getByName(group);
		socket = new MulticastSocket();

		// join a Multicast group
		socket.joinGroup(address);
		byte[] data = null;
		System.out.println("Sending ");
		String str = "Hello! Multicasting.";
		data = str.getBytes();
		packet = new DatagramPacket(data, str.length(), address, PORT);
		// Sends the packet
		socket.send(packet);
		socket.close();

	}// end main
}// end class MulticastSender

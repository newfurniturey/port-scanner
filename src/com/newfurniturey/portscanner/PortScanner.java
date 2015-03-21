package com.newfurniturey.portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;

class PortScanner {
    
    public static void main(String[] args) {
        final int timeout = 200;
        final String host = args[0];
        
        for (int port = 1; port <= 65535; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), timeout);
                socket.close();
                
                // if we made it here, the port is open!
                System.out.printf("Port %d is open%n", port);
            } catch (Exception e) {
                // safely ignore... port is closed
            }
        }
    }
    
}


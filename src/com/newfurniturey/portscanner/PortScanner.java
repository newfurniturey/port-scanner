package com.newfurniturey.portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;

class PortScanner {
    
    public static void main(String[] args) {
        final int timeout = 200;
        final String host = args[0];
        
        for (int port = 1; port <= 65535; port++) {
            if (scanPort(host, port, timeout)) {
                System.out.printf("Port %d is open%n", port);
            }
        }
    }
    
    public static boolean scanPort(final String host, final int port, final int timeout) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), timeout);
                socket.close();
                return true;
            } catch (Exception e) {
                return false;
            }
    }
    
}


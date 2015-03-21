package com.newfurniturey.portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class PortScanner {
    
    public static void main(String[] args) {
        final String host = args[0];
        final int timeout = 200;
        final ExecutorService service = Executors.newFixedThreadPool(15);
        final List<Future<PortStatus>> portStatuses = new ArrayList<Future<PortStatus>>();
        
        for (int port = 1; port <= 65535; port++) {
            portStatuses.add(scanPort(service, host, port, timeout));
        }
        service.shutdown();
        
        try {
            for (final Future<PortStatus> result : portStatuses) {
                PortStatus portStatus = result.get();
                if (portStatus.getStatus()) {
                    System.out.printf("Port %d is open%n", portStatus.getPort());
                }
            }
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }
    
    public static Future<PortStatus> scanPort(final ExecutorService service, final String host, final int port, final int timeout) {
        return service.submit(new Callable<PortStatus>() {
            @Override public PortStatus call() {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, port), timeout);
                    socket.close();
                    return new PortStatus(port, true);
                } catch (Exception e) {
                    return new PortStatus(port, false);
                }
            }
        });
    }
    
}


package com.newfurniturey.portscanner;

public final class PortStatus {
    private final int port;
    private final boolean isOpen;
    
    public PortStatus(int port, boolean isOpen) {
        this.port = port;
        this.isOpen = isOpen;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public boolean getStatus() {
        return this.isOpen;
    }
}

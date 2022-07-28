package message;

public class MessageToServer {
    String sender;
    String receiver;
    int port;
    boolean isSent;

    public MessageToServer(String sender, String receiver, int port, boolean isSent) {
        this.sender = sender;
        this.receiver = receiver;
        this.port = port;
        this.isSent = isSent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }
}

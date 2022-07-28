package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final PrintWriter out;
    private final int id;
    String name;
    boolean isHost;
    List<Integer> hand;

    public ClientHandler(Socket socket , int id) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream());
        this.id=id;
        this.isHost = false;
        hand=new ArrayList<>();
        name="";
    }

    public void sendMessage(String message){
        out.println(message);
        out.flush();
    }

    @Override
    public void run() {
        System.out.println("New ClientHandler is running...");
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            while (true){
                String messageFromClient=in.next();
                //TODO
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kill() throws IOException {
        socket.close();
    }

    public void setHost() {
        isHost = true;
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public List<Integer> getHand() {
        return hand;
    }

    public void setHand(List<Integer> hand) {
        this.hand = hand;
    }
}
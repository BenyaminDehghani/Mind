package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void init() throws IOException {
        Socket socket = new Socket("localhost",8080);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream());

        while (true){
            String input=in.nextLine();
        }
    }
}

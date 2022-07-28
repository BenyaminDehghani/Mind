import com.google.gson.Gson;
import message.MessageToServer;

public class Main {
    public static void main(String[] args) {
        Gson gson=new Gson();
        MessageToServer message=new MessageToServer("1","2",8080,true);
        String json=gson.toJson(message);
        System.out.println(json);
        MessageToServer message1=gson.fromJson(json, MessageToServer.class);
        System.out.println(message1.getReceiver());
    }
}

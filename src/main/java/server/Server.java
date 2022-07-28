package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    private final List<ClientHandler> clientHandlers;
    private ServerStatus serverStatus;
    int level;
    int heartsAmount;
    int ninjasAmount;
    List<Integer> cards;
    Random random;

    public Server() {
        this.clientHandlers = new ArrayList<>();
        this.serverStatus = ServerStatus.WAITING_FOR_PLAYERS;
        this.level=1;
        this.heartsAmount=0;
        this.ninjasAmount=0;
        this.cards=new ArrayList<>();
        for(int i=1;i<101;i++){
            cards.add(i);
        }
        random=new Random();
    }

    public void init() throws IOException {
        System.out.println("Server is running...");
        ServerSocket serverSocket=new ServerSocket(8080);
        while (true){
            System.out.println("Waiting for a connection...");
            Socket socket=serverSocket.accept();
            addNewClientHandler(socket);
        }
    }

    private void addNewClientHandler(Socket socket) throws IOException {
        ClientHandler clientHandler=new ClientHandler(socket,clientHandlers.size());
        if(serverStatus.equals(ServerStatus.WAITING_FOR_PLAYERS)) {
            System.out.println("New connection accepted.");
            clientHandlers.add(clientHandler);
            if(clientHandlers.size()==1){
                clientHandler.setHost();
            }
            heartsAmount++;
            new Thread(clientHandler).start();
        }
        else {
            System.out.println("Game is already started.");
            clientHandler.kill();
        }
    }

    public void startGame(){
        serverStatus=ServerStatus.GAME_STARTED;
    }
    public void decreaseHeart(){
        heartsAmount=heartsAmount-1;
    }

    public void decreaseNinja(){
        ninjasAmount=ninjasAmount-1;
    }

    public void shuffleCards(){
        for (ClientHandler clientHandler:clientHandlers){
            for(int i=0;i<level;i++){
                int card=cards.get(random.nextInt(cards.size()));
                clientHandler.hand.add(card);
                cards.remove(card);
            }
        }
    }

    public void performRound(){
        //TODO deciding whether hearts decrease by one or remain constant
        level++;
    }

    public void useNinja(){
        //TODO
    }

    public void initRound(){
        shuffleCards();
    }

    public List<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHeartsAmount() {
        return heartsAmount;
    }

    public void setHeartsAmount(int heartsAmount) {
        this.heartsAmount = heartsAmount;
    }

    public int getNinjasAmount() {
        return ninjasAmount;
    }

    public void setNinjasAmount(int ninjasAmount) {
        this.ninjasAmount = ninjasAmount;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}

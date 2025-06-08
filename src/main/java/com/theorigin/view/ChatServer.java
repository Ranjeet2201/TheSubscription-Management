package main.java.com.theorigin.view;


import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;
public class ChatServer {
    private static ConcurrentHashMap<String,ClientHandler>userMap=new ConcurrentHashMap<>();
    public static void main(String [] args)throws IOException{
        ServerSocket server=new ServerSocket(1234);
        System.out.println("Server Ready for connection");
        while(true){
            Socket cilentSocket=server.accept();
            System.out.println("new client Connected");
            new ClientHandler(cilentSocket).start();
        }
    }
    static class ClientHandler extends Thread{
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String userName;
        ClientHandler(Socket clientSocket){
            this.clientSocket=clientSocket;
        }
        public void run(){
            try{
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out=new PrintWriter(clientSocket.getOutputStream(),true);
            out.println("Enter UserName");
            userName=in.readLine();
            if(userMap.containsKey(userName)){
                System.out.println("UserName already Taken ");
                clientSocket.close();
            }else{
                userMap.put(userName,this);

            }
            String receivedMsg;
            while ((receivedMsg=in.readLine())!=null) {
                if(receivedMsg.contains(":")){
                    String [] spiltMessage=receivedMsg.split(":",2);
                    String recipient=spiltMessage[0];
                    String message=spiltMessage[1];
                    if(userMap.containsKey(recipient)){
                        userMap.get(recipient).out.println(userName+":"+message);
                    }else {
                        out.println("User " + recipient + " is not online.");
                    }
                }else{
                    broadcastMessage(userName + ":"+ receivedMsg, this);
                }
            
            }
            }catch(IOException ie){
                ie.printStackTrace();
            }
            finally{
                try{
                    clientSocket.close();
                    userMap.remove(userName);
                    System.out.println(userName + " has left the chat.");
                    broadcastMessage("Server: " + userName + " has left the chat.", null);

                }catch(IOException io){

                }

            }
        }
}
private static void broadcastMessage(String message, ClientHandler excludeUser) {
    for (ClientHandler client : userMap.values()) {
        if (client != excludeUser) {
            client.out.println(message);
        }
    }
}
}
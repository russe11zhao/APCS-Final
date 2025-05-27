import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import processing.core.PApplet;

public class Server {
    public static Socket socket;
    public static InputStreamReader inputStreamReader;
    public static OutputStreamWriter outputStreamWriter;
    public static BufferedReader clientInput;
    public static BufferedWriter clientOutput;


    public static void main(String[] args) throws IOException {


        try{
            ServerSocket serverSocket = new ServerSocket(1234);
            socket = serverSocket.accept();

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            clientInput = new BufferedReader(inputStreamReader);
            clientOutput = new BufferedWriter(outputStreamWriter);

            String[] processingArgs = {"SnakeGame"};
            SnakeGame.isServer = true;
            PApplet.runSketch(processingArgs, new SnakeGame());

//            while (true){
//
//                String msgFromClient = bufferedReader.readLine();
//
//                System.out.println("Client: "+msgFromClient);
//
//                bufferedWriter.write("Message Received");
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//
//                if (msgFromClient.equalsIgnoreCase("BYE")){
//                    break;
//                }
//            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerLauncher {

    public static void main(String[] args) {
        Socket client = null;
        ServerSocket server = null;
        int port = 10789;
        BufferedReader inputFromClient;
        PrintWriter printWriter;
        String inputText = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd, a hh:mm:ss");

        try {
            server = new ServerSocket(port);
            client = server.accept();

            if (client != null) {
                System.out.println(client.toString());
            }

            printWriter = new PrintWriter(client.getOutputStream(), true);
            inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));

            long startTime = System.currentTimeMillis(); // start time of connection

            while (true) {
                Date date = new Date();
                System.out.println("Connected time: " + simpleDateFormat.format(date).toString());

                inputText = inputFromClient.readLine();
                System.out.println("Client Message: " + inputText);

                if(inputText.equals("exit") || inputText.equals("q") || inputText.equals("bye")) {
                    System.out.println("Client disconnected");
                    break;
                }
                printWriter.println("Message(" + inputText + ") is received.");
            }
            long endTime = System.currentTimeMillis();

            printWriter.println("Server disconnected");
            System.out.println("total time: " + (endTime - startTime) / 1000.0 + "sec");

            client.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

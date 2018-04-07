import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientLauncher {

    public static void main(String[] args) {
        int serverPort = 10789;
        String serverIP = "127.0.0.1";
        Socket server = null;
        BufferedReader inputFromUser; // get input from the user
        PrintWriter printWriter; // output to server
        BufferedReader inputFromServer; // get input from the server
        String inputText = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd, a hh:mm:ss");

        try {
            server = new Socket(serverIP, serverPort); // connect to server socket

            // init I/O object
            inputFromUser = new BufferedReader(new InputStreamReader(System.in));
            printWriter = new PrintWriter(server.getOutputStream(), true);
            inputFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));


            if (server != null) {
                System.out.println(server.toString());
                System.out.println("Server connected");
            }

            long startTime = System.currentTimeMillis();

            while (true) {
                Date date = new Date();
                System.out.println("Connected time: " + simpleDateFormat.format(date).toString()); // print connected time
                System.out.println("Type any text: ");
                inputText = inputFromUser.readLine();

                printWriter.println(inputText);

                // exit when user type 'exit' or 'q' or 'bye'
                if(inputText.equals("exit") || inputText.equals("q") || inputText.equals("bye")) {
                    inputText = inputFromServer.readLine();
                    break;
                }
                inputText = inputFromServer.readLine();
                System.out.println(inputText);
            }
            long endTime = System.currentTimeMillis();

            System.out.println(inputText);
            System.out.println("Server disconnected");
            System.out.println("total time: " + ((endTime - startTime) / 1000.0 + "sec"));

            server.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

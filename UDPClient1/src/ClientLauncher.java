import java.io.IOException;
import java.net.*;

public class ClientLauncher {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        String text = "Hello, Socket programming!";
        byte[] buffer = text.getBytes(); // length of data to send

        String serverIP = "127.0.0.1";
        int serverPORT = 10789;

        try {
            socket = new DatagramSocket();

            // create UDP packet(evince where to send)
            packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(serverIP), serverPORT);
            socket.send(packet); // send packet

            System.out.println("sended Massege: " + text);

            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

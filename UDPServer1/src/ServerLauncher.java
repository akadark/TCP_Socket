import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerLauncher {

    public static void main(String[] args) {

        int port = 10789; // local port num
        DatagramSocket socket; // UDP socket
        DatagramPacket packet; // UDP packet
        byte[] buffer = new byte[1024]; // array for data

        try {
            socket = new DatagramSocket(port);
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String text = new String(packet.getData());
            System.out.println("reeived Message :" + text);
            System.out.println("packet length: " + text.length());

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

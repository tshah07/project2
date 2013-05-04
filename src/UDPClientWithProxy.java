
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClientWithProxy {

    public static void main(String args[]) throws Exception {

        int result = 0;
       
        //Simple loop to count upto 100
        //1 + 2 + ... + 100
        result = add(result,1);
        for (int i = 2; i <= 100; i++) {
            result = add(result,i);
           
        }
        System.out.println(result);
        
    }

    public static int add(int x, int y) throws Exception {




        //Creating instance of DatagramSocket
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        // Preparing sentence to send to server
        String sentence = x + " + " + y;
        System.out.println(sentence);
        sendData = sentence.getBytes();


        //send packet set up
        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);




        //Receive packet set up
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
        clientSocket.receive(receivePacket);
        String receivedSentence = new String(receivePacket.getData());




        clientSocket.close();
        // Converting resulting string to Double then casting it to integer

        return (int) (Double.parseDouble(receivedSentence));

    }
}


import java.io.*;
import java.net.*;

class UDPClientWithProxy {

    public static void main(String args[]) throws Exception {
        int result =0;
        for(int i =1;i<=100;i++){
            result = add(result,i);
        }
        System.out.println(result);
    }

    public static int add(int x, int y) throws Exception {
        int result = 0;
        String sentence = x+" + "+y;
        String newSentence = socket(sentence);    
        System.out.println("Sentence = "+newSentence);
        double temp = Double.parseDouble(newSentence);
        result = (int)temp;
        return result;
    }
    
    
    public static String socket(String sentence) throws Exception{
        try {

            
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

           
            
            sendData = sentence.getBytes();

            //Constructing Packet to be sent 
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            clientSocket.setSoTimeout(2000);


            //Constructing Received Packet
            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            clientSocket.receive(receivePacket);



            String outputSentence = new String(receivePacket.getData()).trim();
            System.out.println("FROM SERVER:" + outputSentence.trim());

            
            
            clientSocket.close();
            return outputSentence;
            
            
        } catch (SocketTimeoutException e1) {
            System.out.println("Socket closed " + e1 + "Trying Again");
            socket(sentence);
        }
        return null;
        
    }
}
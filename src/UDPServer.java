
import java.io.*;
import java.net.*;

class UDPServer {

    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            serverSocket.receive(receivePacket);

            //Get Data out of packet and convert it from byte to String sentence
            String sentence = new String(receivePacket.getData());


















            System.out.println("RECEIVED: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();



            sentence = simpleArithmatic(sentence);

            String capitalizedSentence = sentence.toUpperCase();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);








        }
    }

    /*
     * Function For simple Arithmatic Calculation
     * Input Format : n1 + n2
     * Permitted operators : +,-,x,/,^
     * input : 1 + 2
     * Output : 3
     */
    static String simpleArithmatic(String sentence) {
        double result = 0;



        // Split the input string by space character " "

        String[] tokens = sentence.split(" ");
        double n1 = Double.parseDouble(tokens[0]);
        double n2 = Double.parseDouble(tokens[2]);
        String operator = tokens[1];



        switch (operator) {
            // Note that there  is no defalult case added here 
            // As we have assume that we have well behaved USER on the othre side
            case "+":
                result = n1 + n2;
                break;

            case "-":
                result = n1 - n2;
                break;

            case "x":
                result = n1 * n2;
                break;

            case "/":
                result = n1 / n2;
                break;

            case "^":
                result = 1;
                for (int i = 1; i <= n2; i++) {
                    result = result * n1;
                }
                break;

        }

        //Converting result(Double) to string and return back 
        return String.valueOf(result);
    }
}
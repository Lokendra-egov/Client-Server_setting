import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ClientProgram {
    public static void main(String[] args) {

//        String names[] = {"Ansh","Lokendra","Aaradhya","Rishabh","Nitish","Himanshu","Ashish","Rachana","Prerna","Swathi"};
//        String request = "GET a/a1.txt";

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter his name: ");
            String name = scanner.nextLine();
            Socket socket = new Socket("172.16.2.177", 12346); // Replace with the server's IP
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println(name);
            String ipAddress = reader.readLine();
            System.out.println("IP Address for " + name + ": " + ipAddress);
            // Remove the leading "/"
            if (ipAddress.startsWith("/")) {
                ipAddress = ipAddress.substring(1);
            }
            System.out.print("Enter the file request (e.g., GET aaa/bb/yt.jpg): ");
            String request = scanner.nextLine();
            socket = new Socket(ipAddress, 12345); // Use the corrected IP address
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(request);
            writer.println(request);
            String response = reader.readLine();
            System.out.println("Server response: " + response);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void showImagePopup(ImageIcon imageIcon) {
        JFrame frame = new JFrame("Image Popup");
        JLabel label = new JLabel(imageIcon);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
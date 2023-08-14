import java.io.*;
import java.net.*;
public class ServerProgram {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Use an available port
            System.out.println("Server listening...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String request = reader.readLine();
                System.out.println("Received request: " + request);
                String response = processRequest(request);
                writer.println(response);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String processRequest(String request) {
        String[] parts = request.split(" ");
        if (parts.length == 2 && parts[0].equals("GET")) {
            File requestedFile = new File("/home/admin1/Downloads/HTML/" + parts[1]);
            if (requestedFile.exists() && requestedFile.isFile()) {
                return readFileContent(requestedFile);
            } else {
                return "File does not exist.";
            }
        }
        return "Invalid request.";
    }
    private static String readFileContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            return "Error reading file.";
        }
    }
}
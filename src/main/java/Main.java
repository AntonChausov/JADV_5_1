import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static long[] faster;
    int first, second;

    public static void main(String[] args) throws IOException {
        ServerSocket servSocket = new ServerSocket(23444);
        try (Socket socket = servSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            boolean flag = true;
            String line;
            while (flag) {
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        flag = false;
                        break;
                    } else if (!line.matches("[-+]?\\d+")) {
                        out.println("Give me number.");
                        break;
                    }
                    int number = Integer.parseInt(line);
                    faster = new long[number + 1];
                    faster[1] = 1;
                    long result = fibonacci(Math.abs(number));
                    if (number < 0) {
                        result = (int) (result * Math.pow(-1, number + 1));
                    }
                    out.println(result);
                    faster = new long[0];
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static long fibonacci(int n) {
        if (faster[n] != 0) {
            return faster[n];
        } else if (n == 0) {
            return 0;
        } else {
            faster[n] = fibonacci(n - 1) + fibonacci(n - 2);
            return faster[n];
        }
    }
}


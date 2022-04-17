package Chat;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author mohdr
 */
public class Client {

    private static InetAddress host;
    static int port;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Port You Want To connect: ");
        port = input.nextInt();
        host = InetAddress.getLocalHost();

        sendMsg();
    }

    private static void sendMsg() throws IOException {
        Socket s = null;
        String msg, res;
        s = new Socket(host, port);
        Scanner serv = new Scanner(s.getInputStream());
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Enter msg to send ['QUIT' to Exit.].. ");
            msg = in.nextLine();
            pw.println(msg);
            res = serv.nextLine();
        } while (!msg.equals("QUIT"));

        s.close();

    }
}

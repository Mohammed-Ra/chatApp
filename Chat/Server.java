package Chat;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author mohdr
 */
public class Server {

    private static ServerSocket ss;
    private static int port;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        
        System.out.println("------------------------------------------------------\n"
                         + "               WELCOME TO MY CHAT APP ^_^              \n"
                         + "------------------------------------------------------\n");
        System.out.println("Enter port to open: ");
        port = input.nextInt();
        ss = new ServerSocket(port);
        Socket sender = null, receiver = null;
        do {
            ClientHandler1 person1 = new ClientHandler1(sender);
            ClientHandler2 person2 = new ClientHandler2(receiver);

            person1.start();
            person2.start();
        } while (true);
    }

    static class ClientHandler1 extends Thread {

        private Socket client1;
        private Scanner serv1;
        private PrintWriter out1;

        public ClientHandler1(Socket s) throws IOException, IOException {
            client1 = s;
            client1 = ss.accept();
            System.out.println("NEW client accepted!!");
            serv1 = new Scanner(client1.getInputStream());
            out1 = new PrintWriter(client1.getOutputStream(), true);
        }

        public void run() {
            String receive1;
            int pause;

            try {
                do {
                    receive1 = serv1.nextLine();
                    out1.println("person1:" + receive1);
                    System.out.println("person " + ClientHandler1.currentThread().getName() + " :" + receive1);
                    pause = (int) (Math.random() * 300);
                    sleep(pause);
                } while (!receive1.equals("QUIT"));

                if (client1 != null) {
                    System.out.println("GOOD BYE!! :( ...");
                    client1.close();
                }
            } catch (IOException ex) {
                System.out.println("Unable to disconnect!");
            } catch (InterruptedException interruptEx) {
                System.out.println(interruptEx);
            }
        }
    }

    static class ClientHandler2 extends Thread {

        private Socket client2;
        private Scanner serv2;
        private PrintWriter out2;

        public ClientHandler2(Socket s) throws IOException, IOException {
            client2 = s;
            client2 = ss.accept();
            System.out.println("NEW client accepted!!");
            serv2 = new Scanner(client2.getInputStream());
            out2 = new PrintWriter(client2.getOutputStream(), true);
        }

        public void run() {
            String receive2;
            int pause;

            try {
                do {
                    receive2 = serv2.nextLine();
                    out2.println("person2:" + receive2);
                    System.out.println("person " + Thread.currentThread().getName() + " :" + receive2);
                    pause = (int) (Math.random() * 300);
                    sleep(pause);
                } while (!receive2.equals("QUIT"));

                if (client2 != null) {
                    System.out.println("GOOD BYE!! :( ...");
                    client2.close();
                }
            } catch (IOException ex) {
                System.out.println("Unable to disconnect!");
            } catch (InterruptedException interruptEx) {
                System.out.println(interruptEx);
            }
        }

    }
}

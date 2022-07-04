import java.io.*;
import java.net.*;
import java.util.ArrayList;

// Server class
public class Server {

  public static void main(String[] args) {
    ServerSocket server = null;

    try {

      // server is listening on port 1234
      server = new ServerSocket(1234);
      server.setReuseAddress(true);

      // running infinite loop for getting
      // client request
      while (true) {

        // socket object to receive incoming client
        // requests
        Socket client = server.accept();

        // Displaying that new client is connected
        // to server
        System.out.println("New client connected" +
          client.getInetAddress()
          .getHostAddress());

        // create a new thread object
        ClientHandler clientSock
          = new ClientHandler(client);

        // This thread will handle the client
        // separately
        new Thread(clientSock).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (server != null) {
        try {
          server.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // ClientHandler class
  private static class ClientHandler implements Runnable {

    private final Socket clientSocket;

    // Constructor
    public ClientHandler(Socket socket) {
      this.clientSocket = socket;
    }

    public void run() {
      PrintWriter out = null;
      BufferedReader in = null;
      try {

        // get the outputstream of client
        out = new PrintWriter(
          clientSocket.getOutputStream(), true);

        // get the inputstream of client
        in = new BufferedReader(
          new InputStreamReader(
            clientSocket.getInputStream()));

        String line;

        while ((line = in .readLine()) != null) {
          if (line.equals("exit")) {
            break;

          }

          switch (Integer.parseInt(line)) {
          case (11): { //food

            Functions f = new Functions();
            ArrayList < String > a = new ArrayList < > ();

            f.caseFood(a);
            out.println(String.valueOf(a.size()));

            for (int i = 0; i < a.size(); i++) {
              out.println(a.get(i));
            }
          }

          case (12): { //food

            Functions f = new Functions();
            ArrayList < String > a = new ArrayList < > ();

            f.caseCloth(a);
            out.println(String.valueOf(a.size()));

            for (int i = 0; i < a.size(); i++) {
              out.println(a.get(i));
            }
          }
          case (13): { //food

            Functions f = new Functions();
            ArrayList < String > a = new ArrayList < > ();

            f.caseElec(a);
            out.println(String.valueOf(a.size()));

            for (int i = 0; i < a.size(); i++) {
              out.println(a.get(i));
            }
          }
          case (14): { //food
            Functions f = new Functions();
            ArrayList < String > a = new ArrayList < > ();

            f.caseHA(a);
            out.println(String.valueOf(a.size()));

            for (int i = 0; i < a.size(); i++) {
              out.println(a.get(i));
            }
          }
          case (20): {
            Functions f = new Functions();
            int sum = 0;
            out.println(f.case20(sum));
            sum = 0;
            out.println(f.case21(sum));
          }

          case (0): //login 
          {
            String USER_ID = in .readLine();
            String password = in .readLine();
            /*check at DATABASE*/
            Functions f = new Functions();
            out.println(f.case0(USER_ID, password));

          }
          case (1): {
            String USER_ID = in .readLine();
            String password = in .readLine();
            String fname = in .readLine();
            String lname = in .readLine();
            Functions f = new Functions();
            String s = f.case1(USER_ID, password, fname, lname);
            out.println(s);
          }

          case (5): { //view balance
            String id = in .readLine();
            Functions f = new Functions();
            out.println(f.case5(id));
          }
          case (52): { //view balance
            String id = in .readLine();
            Functions f = new Functions();
            out.println(f.case5(id));

            ArrayList < String > oid = new ArrayList < > ();
            ArrayList < String > tot = new ArrayList < > ();
            f.case52(id, oid, tot);
            out.println(String.valueOf(oid.size()));
            for (int i = 0; i < oid.size(); i++) {
              out.println(oid.get(i));
              out.println(tot.get(i));
            }
          }
          case (7): { //search
            String iname = in .readLine();
            /*check the DATABASE for the item name*/
            Functions f = new Functions();
            out.println(f.case7(iname));
          }
          case (8): { //deposit
            String id = in .readLine();
            String amount = in .readLine();
            Functions f = new Functions();
            out.println(f.case8(id, amount));
          }
          case (9): {
            String iname = in .readLine();
            //database check
            Functions f = new Functions();
            ArrayList < String > IP = new ArrayList < > ();
            f.case9(iname, IP);
            out.println(IP.get(0)); //id
            out.println(IP.get(1)); //price
            //                            out.println();
          }
          case (10): {
            System.out.println("in case 6 now");
            String USER_ID = in .readLine();
            Functions f = new Functions();
            int USER_balance = Integer.parseInt(f.case61(USER_ID));
            float totalprice = 0;
            System.out.println("f.case61 now");
            int array_size = Integer.parseInt( in .readLine());
            ArrayList < String > items_ID = new ArrayList < > ();
            ArrayList < String > quantity = new ArrayList < > ();
            ArrayList < String > price = new ArrayList < > ();

            for (int i = 0; i < array_size; i++) {

              items_ID.add( in .readLine());
              quantity.add( in .readLine());
              price.add( in .readLine());

            }

            System.out.println("after for loop now");
            for (int i = 0; i < array_size; i++) {
              price.set(i, price.get(i).substring(1, price.get(i).length()));

            }

            for (int i = 0; i < array_size; i++) {
              System.out.println(price.get(i));

            }
            for (int i = 0; i < array_size; i++) {
              totalprice += Float.parseFloat(price.get(i)) * Float.parseFloat(quantity.get(i));
            }
            /*check the DATABASE for the available cash*/
            if ((float) USER_balance >= totalprice) {
              /*decrease qty of items from database*/
              f.case62(USER_ID, items_ID, quantity, (int) totalprice, USER_ID);
              out.println("okay");
              System.out.println("b>t now");
              //decrease balance in database
              //insert orders
            } else {
              out.println("not okay");
              System.out.println("b<t");
            }
          }
          default: {}
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (out != null) {
            out.close();
          }
          if ( in != null) {
            in .close();
            clientSocket.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

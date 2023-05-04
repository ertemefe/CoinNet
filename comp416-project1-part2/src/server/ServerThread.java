package server;

import api.RESTfulAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class ServerThread extends Thread {
    protected BufferedReader is;
    protected PrintWriter os;
    protected Socket s;
    protected RESTfulAPI api = new RESTfulAPI();

    /**
     * Creates a server thread on the input socket
     *
     * @param s input socket to create a thread on
     */
    public ServerThread(Socket s) {
        this.s = s;
    }

    /**
     * The server thread, echos the client until it receives the QUIT string from the client
     */
    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(s.getOutputStream());

        } catch (IOException e) {
            System.err.println("Server Thread. Run. IO error in server thread");
        }

        String line;

        try {
            line = is.readLine();
            while (line.compareTo("QUIT") != 0) {
                byte[] bytes = line.getBytes(StandardCharsets.UTF_8);
                if (bytes.length > 0x64) {
                    System.out.println("protocol breached");
                    break;
                }
                if (line.equals("list")) {
                    os.println(api.listing(line));
                    os.flush();
                } else if (!line.equals("")) {
                    os.println(api.currency(line));
                    os.flush();
                }
                line = is.readLine();
            }
        } catch (IOException e) {
            line = this.getName(); //reused String line for getting thread name
            System.err.println("Server Thread. Run. IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.err.println("Server Thread. Run.Client " + line + " Closed");
        } finally {
            try {
                System.out.println("Closing the connection");
                if (is != null) {
                    is.close();
                    System.err.println(" Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    System.err.println("Socket Out Closed");
                }
                if (s != null) {
                    s.close();
                    System.err.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.err.println("Socket Close Error");
            }
        }
    }
}

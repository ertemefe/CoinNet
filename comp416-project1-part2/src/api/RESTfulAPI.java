/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import org.json.JSONArray;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
//import sun.net.www.http.HttpClient;

/**
 * @author AbdulrazakZakieh
 */
public class RESTfulAPI {


    public String listing(String arg) throws IOException {

        URL url = new URL("https://api.coingecko.com/api/v3/coins/list");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }

        scanner.close();

        Gson gson = new Gson();

        List[] listArr = gson.fromJson(inline, List[].class);

        String response = "";
        for (List list : listArr) {
            System.out.println("ID: " + list.getId() + ", SYMBOL: " + list.getSymbol() + ", NAME: " + list.getName());
            response += ("ID: " + list.getId() + ", SYMBOL: " + list.getSymbol() + ", NAME: " + list.getName());
        }

        return response;

    }


    public String currency(String arg) throws IOException {

        URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=" + arg + "&vs_currencies=try");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        //Close the scanner
        scanner.close();
        System.out.println(inline);

        /*
        Gson gson = new Gson();
        Currency curr = gson.fromJson(inline, Currency.class);
        String response = "";

        System.out.println("ID: " + curr.getId() + ", PRICE: " + curr.getPrice());
        response = curr.getPrice().price;
        */

        return inline;
    }

}

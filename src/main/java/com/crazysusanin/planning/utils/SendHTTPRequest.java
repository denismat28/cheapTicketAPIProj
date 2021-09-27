package com.crazysusanin.planning.utils;


import com.crazysusanin.planning.model.AviaTicketInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendHTTPRequest {
    public static AviaTicketInfo callMe(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Edge");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print
        System.out.println(response.toString());
        //work with response
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(response.toString());
        String s = String.valueOf(strBuf.substring(strBuf.indexOf("price") - 2, strBuf.indexOf("}") + 1));
        //make pojo
        AviaTicketInfo ticket = new ObjectMapper().readerFor(AviaTicketInfo.class)
                              .readValue(s);
        System.out.println(s);
        //AviaTicketInfo ticket = new ObjectMapper().readerFor(AviaTicketInfo.class)
        //        .readValue(String.valueOf(response));
        System.out.println(ticket);
        return ticket;
    }

    public static void main(String[] args) {
        String url = "https://api.travelpayouts.com/v1/prices/direct?origin=MOW&Destination=London&depart_date=2021-09-29&return_date=2021-09-30&token=321d6a221f8926b5ec41ae89a3b2ae7b";
        try {
            callMe(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

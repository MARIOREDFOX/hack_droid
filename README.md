# Hack Droid

> An Android Studio / Java educational project that demonstrates how an Android application can collect selected device metadata and transmit a summary by SMS.

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Language](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.oracle.com/java/)
[![IDE](https://img.shields.io/badge/IDE-Android%20Studio-blue.svg)](https://developer.android.com/studio)

## Overview

**Hack Droid** is a Java-based Android application created for learning and ethical-security experimentation.

The application demonstrates Android APIs that can access selected device information after the required runtime permissions have been granted. It collects a small set of metadata, formats the results, and sends the resulting report through SMS.

The current project description identifies the following data sources:

- **Contacts** — counts contacts available through `ContactsContract`.
- **Gallery / Images** — counts images available through `MediaStore`.
- **Mobile carrier** — obtains the network/operator name through `TelephonyManager`.
- **Call log** — attempts to obtain the phone number associated with the most recent outgoing call.
- **SMS reporting** — sends the collected summary through Android's SMS API.
- **User feedback** — displays a toast after attempting to send the report.

## Data Flow

```text
┌──────────────────────┐
│   Android Device     │
└──────────┬───────────┘
           │
           ├── ContactsProvider
           │      └── Contact count
           │
           ├── MediaStore
           │      └── Image count
           │
           ├── TelephonyManager
           │      └── Carrier name
           │
           └── CallLog
                  └── Last outgoing number
                         │
                         ▼
              ┌────────────────────┐
              │  Format report     │
              │  into one string   │
              └─────────┬──────────┘
                        │
                        ▼
              ┌────────────────────┐
              │ Android SMS API    │
              │    sendText...     │
              └─────────┬──────────┘
                        │
                        ▼
                 Configured number
```

## Android APIs Used

| Purpose | Android API |
|---|---|
| Read contacts | `ContactsContract` |
| Read media/images | `MediaStore` |
| Read carrier information | `TelephonyManager` |
| Read call history | `CallLog` |
| Send SMS | `SmsManager` |
| User notification | `Toast` |

## Permissions

The application requires access to sensitive Android capabilities.

The project documentation specifically references:

```xml
READ_CONTACTS
READ_CALL_LOG
SEND_SMS
```

Depending on the Android version and implementation, additional media/telephony permissions may be required.

### Important

These permissions provide access to potentially sensitive personal information. Only install, test, or modify this application on devices and data for which you have explicit authorization.

For modern Android versions, dangerous permissions must generally be requested at runtime in addition to being declared in `AndroidManifest.xml`.

## Project Setup

### Prerequisites

Install:

1. **Android Studio**
2. **Android SDK**
3. A compatible **JDK**
4. An Android emulator or an authorized physical Android test device

### Clone the repository

```bash
git clone https://github.com/MARIOREDFOX/hack_droid.git
cd hack_droid
```

Then open the project directory in Android Studio.

### Build and run

1. Open the repository in Android Studio.
2. Allow Gradle to synchronize the project.
3. Connect an authorized Android test device or start an emulator.
4. Build the application.
5. Run the application from Android Studio.
6. Grant only the permissions required by the application when prompted.

> Exact Gradle, Android Gradle Plugin, SDK, and Java versions should be taken from the project's build configuration rather than assumed from this README.

## Application Workflow

At a high level, the application performs the following sequence:

```text
Start Application
       │
       ▼
Request / Verify Permissions
       │
       ▼
Read Contact Count
       │
       ▼
Read Image Count
       │
       ▼
Read Carrier Name
       │
       ▼
Read Last Outgoing Call
       │
       ▼
Build Report String
       │
       ▼
Send Report by SMS
       │
       ▼
Display Completion Toast
```

## Example Report Structure

The collected values are combined into a single SMS payload. Conceptually, the message contains:

```text
Contacts: <count>
Gallery Images: <count>
Carrier: <carrier>
Last Dialed Number: <number>
```

The exact formatting should be treated as an implementation detail of the current source code.

## Security and Privacy Considerations

This project accesses information that can be highly sensitive, including:

- Contact-related information
- Call-history information
- Device/network information
- Potentially identifiable phone numbers

It also sends collected information externally through SMS.

For ethical testing:

- Use a dedicated test device where possible.
- Use test contacts and test call history.
- Use a test SIM/number.
- Do not collect information belonging to other people without authorization.
- Do not deploy the application to devices you do not own or administer.
- Do not disguise the application's data collection behavior.
- Keep test credentials, phone numbers, and other private data out of source control.

## Educational Use

The project can be useful for learning about:

- Android permissions
- Android content providers
- `Cursor`-based data access
- `MediaStore`
- `ContactsContract`
- `TelephonyManager`
- `CallLog`
- Android SMS APIs
- Mobile application security and privacy

It can also serve as a small laboratory project for understanding why Android permission controls and user consent are important.

## Repository

**GitHub:** https://github.com/MARIOREDFOX/hack_droid

## Disclaimer

This project is intended for **authorized security research, Android development education, and controlled laboratory testing**.

Do not use it to access, collect, transmit, or monitor another person's private information without their explicit authorization. You are responsible for complying with applicable laws, organizational policies, and Android platform requirements.

## License

This project is licensed under the **GNU General Public License v3.0 (GPL-3.0)**.

See the [LICENSE](LICENSE) file for the full license text.

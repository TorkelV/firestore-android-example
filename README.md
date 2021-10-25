# Firebase examples

A simple app to showcase Firebase auth and Firestore.

Setup:

0. Clone the project
1. Change the applicationId in app/build.gradle to something unique to you.
2. Go to https://console.firebase.google.com/
3. Create a new Firebase project
4. When prompted for sha1, find it using this command in your terminal: `keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android `
5. Add an app to the firebase project
6. download the google-services.json file and add it to the app/ directory.
7. Enable Authentication and enable Email/Password provider
8. Enable Firestore, pick Europe west as location(optional)

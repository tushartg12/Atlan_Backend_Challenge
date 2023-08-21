# Atlan_Backend_Challenge

Techstack used: 

Springboot 
MongoDB 
Junit , Mockito (for unit testing)

Services used: 

Whatsmate API (for slang words) 
Twilio SMS service (for SMS generation) 
Google Sheets API (for Google Sheet Generation)

Prerequisites:

Java 17 SDK 
MongoDB installed locally 
Account on Whatsmate API - https://whatsmate.github.io/2016-08-18-translate-text-java/ 
Account on Twilio - https://www.twilio.com/en-us 
Google Sheets API enabled (Do it from Google Cloud)

APIs:

For getting all customers - http://localhost:8080/getAllCustomers
For adding new customer - http://localhost:8080/addCustomer
For creating Google Sheet - http://localhost:8080/createSheet
For getting the slang word - http://localhost:8080/giveSlang
For sending SMS - http://localhost:8080/sendSMS
For validating the customer details - http://localhost:8080/validateCustomer


Important Notes

Since we are using some third party services, please create accounts on the respective platforms and use your credentials.
In Twilio SMS service please add the caller ID and verify the number on which you want to send the SMS.
I have created a package named as “Credentials”, inside it paste Whatsmate API credentials (CLIENT_ID and CLIENT_SECRET) in “SlangServiceCredentials” file and Twilio credentials (ACCOUNT_SID and AUTH_TOKEN) in “SmsServiceCredentials” file.
Paste the credentials of the Google Sheets API (generated in JSON format) in the “credentials.json” file.
It is strongly suggested to replace your credentials with the given ones but for your convenience i have provided my credentials, so you can test this project. JSON inputs are given for every POST API in the documentation, you can use it for smooth happy path testing.

For more details about the project read the documentation.
package com.atlan.backend.service;

import com.atlan.backend.entity.CustomerDetails;
import com.atlan.backend.entity.SheetResponsePojo;
import com.atlan.backend.repository.CustomerRepository;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class GoogleSheetsService {
    @Autowired
    CustomerRepository customerRepository;
    private static final String APPLICATION_NAME = "Atlan Backend Challenge";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart. If modifying these
     * scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS, SheetsScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleSheetsService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(
                        new java.io.File(System.getProperty("user.home"), TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline").build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    private Sheets getSheetService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Sheets.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME).build();
    }

    public SheetResponsePojo createSheet()
            throws GeneralSecurityException, IOException {
        Sheets service = getSheetService();
        SpreadsheetProperties spreadsheetProperties = new SpreadsheetProperties();
        //The sheet name will be the date and time when it is created
        String sheetName = String.valueOf(new Date());
        spreadsheetProperties.setTitle(sheetName);
        SheetProperties sheetProperties = new SheetProperties();
        sheetProperties.setTitle(sheetName);
        Sheet sheet = new Sheet().setProperties(sheetProperties);
        Spreadsheet spreadsheet = new Spreadsheet().setProperties(spreadsheetProperties)
                .setSheets(Collections.singletonList(sheet));
        Spreadsheet createdResponse = service.spreadsheets().create(spreadsheet).execute();
        SheetResponsePojo sheetResponsePojo = new SheetResponsePojo();
        //Creating heading row
        List<Object> headingRow=new ArrayList<>();
        headingRow.add("id");
        headingRow.add("Email");
        headingRow.add("Name");
        headingRow.add("Monthly Income");
        headingRow.add("Monthly Savings");
        headingRow.add("Mobile Number");
        List<List<Object>> entries=new ArrayList<>();
        entries.add(headingRow);
        //Fetching data from  DB
        List<CustomerDetails> customerDetails = customerRepository.findAll();
        //Adding data in the Google sheet
        for (CustomerDetails customerDetail:
             customerDetails) {
            List<Object> entry=new ArrayList<>();
            entry.add(customerDetail.getId());
            entry.add(customerDetail.getEmail());
            entry.add(customerDetail.getName());
            entry.add(customerDetail.getMonthlyIncome());
            entry.add(customerDetail.getMonthlySavings());
            entry.add(customerDetail.getMobileNumber());
            entries.add(entry);
        }
        ValueRange valueRange = new ValueRange().setValues(entries);
        service.spreadsheets().values().update(createdResponse.getSpreadsheetId(), "A1", valueRange)
                .setValueInputOption("RAW").execute();
        sheetResponsePojo.setSpreadSheetId(createdResponse.getSpreadsheetId());
        sheetResponsePojo.setSpreadSheetUrl(createdResponse.getSpreadsheetUrl());
        return sheetResponsePojo;
    }
}
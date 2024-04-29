package com.o2htechnology.utils.read_files;

import com.o2htechnology.utils.common_utils.CommonUtils;
import com.o2htechnology.utils.common_utils.PageData;
import com.o2htechnology.utils.logging.Logs;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

public class CSVFileReader {
    private static Map<String, String> CREDENTIALS = new HashMap<String, String>();
    private static String[] HEADERS = {"username", "password"};
    public static List<String> users;

    private static Map<String, String> readFromFile() {
        try {
            Reader credentialsFileReader = new FileReader(CommonUtils.getCsvFilePath());
            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(HEADERS).withSkipHeaderRecord(true);
            Iterable<CSVRecord> csvRecords = csvFormat.parse(credentialsFileReader);
            for (CSVRecord record : csvRecords) {
                String username = record.get(HEADERS[0]);
                String password = record.get(HEADERS[1]);
                CREDENTIALS.put(username, password);
            }
        } catch (IOException e) {
            Logs.logErrorMessage(PageData.Messages.Error.FILE_READ_ERROR + e.getMessage());
        }
        return CREDENTIALS;
    }

    public static List<String> getUsersListFromCSVFile() {
        readFromFile();
        users = new ArrayList<>();
        users = CREDENTIALS.keySet().stream().toList();
        return users;
    }

    public static String getPasswordFromCSVFile(String username) {
        getUsersListFromCSVFile();
        return CREDENTIALS.get(username);
    }
}

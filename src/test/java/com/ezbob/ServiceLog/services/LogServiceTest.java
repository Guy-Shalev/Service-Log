package com.ezbob.ServiceLog.services;

import com.ezbob.ServiceLog.models.LogRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private LogService logService = new LogService();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void logRequest() {
        LogRequest logRequest = new LogRequest(new int[]{1, 2, 3, 4, 5, 6}, new Date());

        SimpleDateFormat formatter = new SimpleDateFormat(LogService.DATE_FORMAT);
        String expectedLog = String.format(LogService.SHUFFLE_LOG_MASSAGE, logRequest.getArray().length, formatter.format(logRequest.getRequestDate()), Arrays.toString(logRequest.getArray()));
        logService.logRequest(logRequest);

        assertEquals(expectedLog, outContent.toString().trim());
    }

    @Test
    void logRequestEmptyArray() {
        LogRequest logRequest = new LogRequest(new int[]{}, new Date());

        SimpleDateFormat formatter = new SimpleDateFormat(LogService.DATE_FORMAT);
        String expectedLog = String.format(LogService.SHUFFLE_LOG_MASSAGE, logRequest.getArray().length, formatter.format(logRequest.getRequestDate()), Arrays.toString(logRequest.getArray()));
        logService.logRequest(logRequest);

        assertEquals(expectedLog, outContent.toString().trim());
    }

    @Test
    void logRequestNoDate() {
        LogRequest logRequest = new LogRequest(new int[]{1, 2, 3, 4, 5, 6}, null);

        String expectedLog = String.format(LogService.SHUFFLE_LOG_MASSAGE, logRequest.getArray().length, LogService.NO_DATE_RECEIVED, Arrays.toString(logRequest.getArray()));
        logService.logRequest(logRequest);

        assertEquals(expectedLog, outContent.toString().trim());
    }

    @Test
    void logRequestNoRequest() {
        String expectedLog = LogService.NO_REQUEST_RECEIVED;
        logService.logRequest(null);

        assertEquals(expectedLog, outContent.toString().trim());
    }

    @Test
    void logRequestNoArray() {
        LogRequest logRequest = new LogRequest(null, new Date());

        String expectedLog = LogService.NO_ARRAY_RECEIVED;
        logService.logRequest(logRequest);

        assertEquals(expectedLog, outContent.toString().trim());
    }
}
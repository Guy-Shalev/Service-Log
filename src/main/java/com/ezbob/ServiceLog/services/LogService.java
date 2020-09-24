package com.ezbob.ServiceLog.services;

import com.ezbob.ServiceLog.models.LogRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class LogService {

    static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    static final String SHUFFLE_LOG_MASSAGE = "A \'Shuffle\' request for number %s was received on %s. \nA response was sent: %s";
    static final String NO_REQUEST_RECEIVED = "A \'Shuffle\' request call was made to this service, but received no request.";
    static final String NO_ARRAY_RECEIVED = "A \'Shuffle\' request received, but no array received.";
    static final String NO_DATE_RECEIVED = "Date unavailable";

    /**
     * Get a {@code LogRequest} object and log it for the console.
     *
     * @param request - LogRequest a request from "service-shuffle"
     */
    public void logRequest(LogRequest request) {
        if (request != null) {
            String log;
            if (request.getArray() == null) {
                log = NO_ARRAY_RECEIVED;
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                String date = request.getRequestDate() != null ? formatter.format(request.getRequestDate()) : NO_DATE_RECEIVED;
                log = String.format(SHUFFLE_LOG_MASSAGE, request.getArray().length, date, Arrays.toString(request.getArray()));
            }
            System.out.println(log);
        } else {
            System.out.println(NO_REQUEST_RECEIVED);
        }
    }
}

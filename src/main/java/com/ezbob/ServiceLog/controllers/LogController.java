package com.ezbob.ServiceLog.controllers;

import com.ezbob.ServiceLog.models.LogRequest;
import com.ezbob.ServiceLog.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/log")
    public void log(@RequestBody LogRequest request) {
        logService.logRequest(request);
    }

}

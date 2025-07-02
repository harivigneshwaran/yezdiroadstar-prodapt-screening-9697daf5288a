package com.example.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/remove")
    public ResponseEntity<?> removeContent(@RequestParam (required = false) String value) {
        if(value!=null)
            logger.info("Length:" +value.length());

        if (value== null || value.isBlank() || StringUtils.length(value) < 2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (StringUtils.length(value) == 2) {
            return ResponseEntity.ok("");
        } else if (StringUtils.length(value) >= 2 && value.contains("%")) {
            String tempVal = value.replaceAll("%", "_%");
            return ResponseEntity.ok(StringUtils.substring(tempVal, 0, tempVal.length()-1));
        } else {
            return ResponseEntity.ok(StringUtils.substring(value, 1, value.length() - 1));
        }
    }
}

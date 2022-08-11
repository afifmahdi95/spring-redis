package com.example.springredis.controller.restcontroller;

import com.example.springredis.dto.MasterPriceDto;
import com.example.springredis.service.MasterPriceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private MasterPriceImpl masterPrice;

    @PostMapping("/add")
    public ResponseEntity<?> addData(@RequestBody MasterPriceDto masterPriceDto){
        logger.info("Request Add Data Received");
        ResponseEntity<?> res = masterPrice.saveData(masterPriceDto);
        logger.info("Request Add Data Finished");
        return res;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getData(){
        logger.info("Request Get Data Received");
        ResponseEntity<?> res = masterPrice.getData();
        logger.info("Request Get Data Finished");
        return res;
    }

    @GetMapping("/get/{price}")
    public ResponseEntity<?> getData(@PathVariable("price") BigDecimal price){
        logger.info("Request Get Data Received");
        ResponseEntity<?> res = masterPrice.getData(price);
        logger.info("Request Get Data Finished");
        return res;
    }
}

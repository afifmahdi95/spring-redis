package com.example.springredis.service;

import com.example.springredis.dto.MasterPriceDto;
import com.example.springredis.entity.MasterPrice;
import com.example.springredis.repository.MasterPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class MasterPriceImpl {

    Logger logger = LoggerFactory.getLogger(MasterPriceImpl.class);

    @Autowired
    private MasterPriceRepository masterPriceRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String key = "MasterPrice";


    @Transactional
    public ResponseEntity saveData(MasterPriceDto masterPriceDto){

        logger.info("Service Add Data START");

        try {
            //mapping data
            MasterPrice masterPrice = new MasterPrice();
            masterPrice.setOriginCode(masterPriceDto.getOriginCode());
            masterPrice.setDestinationCode(masterPriceDto.getDestinationCode());
            masterPrice.setPrice(masterPriceDto.getPrice());
            masterPrice.setProduct(masterPriceDto.getProduct());

            //save to redis
            logger.info("Insert to Redis START");

            String str = masterPriceDto.getOriginCode() + ", " + masterPriceDto.getDestinationCode() +", " + masterPriceDto.getProduct() ;
            redisTemplate.opsForHash().put(key, masterPriceDto.getPrice().toString(), str);
            logger.info("Insert to Redis END");

            //save to DB
            logger.info("Insert to DB START");
            masterPriceRepository.save(masterPrice);
            logger.info("Insert to DB END");

            logger.info("Service Add Data END");
            return ResponseEntity.ok().body(new HashMap<String, Object>(){{
                put("status", "OK");
                put("message", "success");
            }});

        }
        catch (Exception e){

            //catch error
            logger.error("Error when operate : " + e.getMessage());
            return ResponseEntity.internalServerError().body(new HashMap<String, Object>(){{
                put("status", "Error");
                put("message", e.getMessage());
            }});
        }

    }

    public ResponseEntity getData(){

        logger.info("Service Get Data All START");

        try {
            //consume data from redis
            Map dataRedis = redisTemplate.opsForHash().entries(key);

            logger.info("Service Get Data All END");
            return ResponseEntity.ok().body(new HashMap<String, Object>(){{
                put("status", "OK");
                put("message", "success");
                put("data", dataRedis);
            }});

        }
        catch (Exception e){
            //catch error
            logger.error("Error when operate : " + e.getMessage());
            return ResponseEntity.internalServerError().body(new HashMap<String, Object>(){{
                put("status", "Error");
                put("message", e.getMessage());
            }});
        }

    }


    public ResponseEntity getData(BigDecimal price){

        logger.info("Service Get Data by Price START");

        try {

            //consume data from redis
            Object dataRedis = redisTemplate.opsForHash().get(key, price.toString());

            logger.info("Service Get Data by Price END");
            return ResponseEntity.ok().body(new HashMap<String, Object>(){{
                put("data", dataRedis != null ? price.toString() + " : " + dataRedis : "");
                put("message", "success");
                put("status", "OK");
            }});

        }
        catch (Exception e){
            //catch error
            logger.error("Error when operate : " + e.getMessage());
            return ResponseEntity.internalServerError().body(new HashMap<String, Object>(){{
                put("message", e.getMessage());
                put("status", "Error");
            }});
        }

    }
}

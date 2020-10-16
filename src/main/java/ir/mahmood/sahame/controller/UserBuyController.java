package ir.mahmood.sahame.controller;

import ir.mahmood.sahame.dto.UserBuyDto;
import ir.mahmood.sahame.entity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/buy/")
public class UserBuyController {

    @PostMapping("/")
    public void buy(@RequestBody UserBuyDto userBuyDto) {
        log.info(userBuyDto);
    }
}

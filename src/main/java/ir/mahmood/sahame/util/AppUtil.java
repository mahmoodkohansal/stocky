package ir.mahmood.sahame.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AppUtil {
    public String generateOTPCode() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(9999));
    }
}

package ir.mahmood.sahame.service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransportService {
    @Value("${transport.sms.url}")
    private String transportSmsUrl;

    @Value("${transport.sms.from}")
    private String transportSmsFrom;

    @Value("${transport.sms.username}")
    private String transportSmsUsername;

    @Value("${transport.sms.password}")
    private String transportSmsPassword;

    @Value("${transport.sms.domain}")
    private String transportSmsDomain;

    public void sendSms(String smsTo, String smsText) {
        HttpResponse<String> response = Unirest
                .get(transportSmsUrl)
                .queryString("FROM", transportSmsFrom)
                .queryString("USERNAME", transportSmsUsername)
                .queryString("PASSWORD", transportSmsPassword)
                .queryString("DOMAIN", transportSmsDomain)
                .queryString("TO", smsTo)
                .queryString("TEXT", smsText)
                .asString();
    }
}

package com.ramy.twilio;



import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Record;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class SMSService {


   // @Value("#{systemEnvironment['ACb894092308b5f10e49858b61bb260c13']}")
    private String ACCOUNT_SID = "ACb894092308b5f10e49858b61bb260c13";

   // @Value("#{systemEnvironment['916cb0dad670061bdca58420a13267d5']}")
    private String AUTH_TOKEN = "916cb0dad670061bdca58420a13267d5";

    //@Value("#{systemEnvironment['+14087912638']}")
    private String FROM_NUMBER = "+14087912638";


    public void makeCall(SMS callTo) throws URISyntaxException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Call call = Call.creator(
                new PhoneNumber(callTo.getTo()),
                new PhoneNumber(FROM_NUMBER),
                new URI("http://demo.twilio.com/docs.voice.xml")




        ).setMethod(HttpMethod.GET).setSendDigits("1234#")
                .setRecord(true)


                .create();

        Record record = new Record.Builder().transcribe(true)
                .build();
        VoiceResponse response = new VoiceResponse.Builder().record(record)
                .build();
        System.out.println(response.toXml());
        System.out.println(call.getSid());
    }

    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}

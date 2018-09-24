package com.shohiebsense.fintechtemplate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.shohiebsense.fintechtemplate.model.SmsVerification;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shohiebsense on 11/01/2018.
 */

public class SampleSmsCodeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from = "";
            String msg_body = "";
            String code = "";
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msg_body = msgs[i].getMessageBody();

                        if (msg_from.equals("sender ... ") || msg_from.equals("number ...")) {
                            Pattern digits = Pattern.compile("\\d+");
                            Matcher matcher = digits.matcher(msg_body);
                            while (matcher.find()) {
                                code = matcher.group();
                            }

                            System.out.println("SMS Verification : " + msg_from + " ~> " + msg_body);
                            EventBus.getDefault().post(new SmsVerification(code, msg_body));
                        }
                    }
                }
                catch(Exception e) {
                    Log.e("Exception caught",e.getMessage());
                }
            }

    }
}

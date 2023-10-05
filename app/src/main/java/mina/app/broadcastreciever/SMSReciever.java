package mina.app.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            String sender = "";
            String message = "";

            if (bundle != null){
                 Object [] pdusObj = (Object[]) bundle.get("pdus");
                 String format = bundle.getString("format");

                 for (int i = 0; i < pdusObj.length; i++ ){
                     SmsMessage currentMessage = SmsMessage.createFromPdu( (byte[]) pdusObj[i], format);
                     message = currentMessage.getDisplayMessageBody();
                     sender = currentMessage.getDisplayOriginatingAddress();

                     Intent activityIntent = new Intent(context, MainActivity.class);
                     activityIntent.putExtra("sms" , message);
                     activityIntent.setAction(Intent.ACTION_SEND);
                     activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                     context.startActivity(activityIntent);

                 }
            }
        }
    }
}
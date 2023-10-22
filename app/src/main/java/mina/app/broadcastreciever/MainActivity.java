package mina.app.broadcastreciever;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.OnNewIntentProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
//
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "mypref";

    SharedPreferences pref;

    private TickerViewModel sharedModel;
    String blub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Intent intent = getIntent();


            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                String[] perms = new String[]{Manifest.permission.RECEIVE_SMS};
                ActivityCompat.requestPermissions(this, perms, 101);
                requestPermissions(perms, 101);
            }
            sharedModel = new ViewModelProvider(this).get(TickerViewModel.class);
            pref = getSharedPreferences(SHARED_PREF_NAME, 0);



    }
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setMessage(intent.getStringExtra("sms"));
    }

    public void setMessage(String message) {
        if (message != null && message.contains("Ticker:<<") && message.contains(">>")) {
            blub = message.substring(9, message.indexOf(">"));
            blub = blub.toUpperCase();

            if (blub.matches("[a-zA-Z]+")) {
                sharedModel.addTickerData(blub);
            } else {
                Toast.makeText(getApplicationContext(), "Not correct format of message.", Toast.LENGTH_LONG).show();
            }

        } else if (message != null) {
            Toast.makeText(getApplicationContext(), "No valid watchlist entry was found. ", Toast.LENGTH_LONG).show();
        }

    }


}
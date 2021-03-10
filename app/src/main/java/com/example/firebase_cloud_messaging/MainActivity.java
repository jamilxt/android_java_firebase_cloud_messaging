package com.example.firebase_cloud_messaging;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText etTopicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTopicName = findViewById(R.id.topicName);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    String token = task.getResult();

                    Log.d(TAG, token);
                    TextView tvToken = findViewById(R.id.token);
                    tvToken.setText(token);
                });
    }

    public void subscribeToTopic(View view) {
        FirebaseMessaging.getInstance().subscribeToTopic(etTopicName.getText().toString())
                .addOnCompleteListener(task -> {
                    String msg = "Subscribe successful";
                    if (!task.isSuccessful()) {
                        msg = "Subscribe failed";
                    }
                    Log.d(TAG, msg);
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                });
    }

    public void unsubscribeFromTopic(View view) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(etTopicName.getText().toString())
                .addOnCompleteListener(task -> {
                    String msg = "Unsubscribe successful";
                    if (!task.isSuccessful()) {
                        msg = "Unsubscribe failed";
                    }
                    Log.d(TAG, msg);
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                });
    }
}
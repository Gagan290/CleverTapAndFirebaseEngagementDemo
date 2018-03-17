package com.example.praveen.appengagementdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;
import com.example.praveen.appengagementdemo.firebaseAnalytics.FirebaseAnalyticsActivity;

import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    CleverTapAPI cleverTapAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            cleverTapAPI = CleverTapAPI.getInstance(getApplicationContext());
        } catch (CleverTapMetaDataNotFoundException e) {
            e.printStackTrace();
        } catch (CleverTapPermissionsNotSatisfied pe) {
            pe.printStackTrace();
        }

        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
        profileUpdate.put("Name", "Gagan");                  // String
        profileUpdate.put("Identity", 61026032);                    // String or number
        profileUpdate.put("Email", "gagan290@gmail.com");               // Email address of the user
        profileUpdate.put("Phone", "12345688");                 // Phone (with the country code, starting with +)
        profileUpdate.put("Gender", "M");                           // Can be either M or F
        profileUpdate.put("Employed", "Y");                         // Can be either Y or N
        profileUpdate.put("Education", "Graduate");                 // Can be either Graduate, College or School
        profileUpdate.put("Married", "N");                          // Can be either Y or N
        profileUpdate.put("DOB", new Date());                       // Date of Birth. Set the Date object to the appropriate value first
        profileUpdate.put("Age", 25);                               // Not required if DOB is set
        profileUpdate.put("Tz", "Asia/Delhi");
        cleverTapAPI.profile.push(profileUpdate);

        HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
        prodViewedAction.put("Product Name", "Mobile ");
        prodViewedAction.put("Category", "electronics");
        prodViewedAction.put("Price", 10000);
        prodViewedAction.put("Date", new Date());

        cleverTapAPI.event.push("Product viewed", prodViewedAction);

        findViewById(R.id.btn_firebaseAnalytics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FirebaseAnalyticsActivity.class));
            }
        });
    }
}

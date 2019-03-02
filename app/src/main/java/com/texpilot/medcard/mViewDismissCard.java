package com.texpilot.medcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class mViewDismissCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_view_dismiss_card);
        Intent intent = getIntent(); // gets the previously created intent
        Integer MedCardId;
        MedCardId = intent.getIntExtra("MedCardId", -1);

        MedCardId = 5;
    }
}

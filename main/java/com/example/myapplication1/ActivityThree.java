package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityThree extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        TextView calculatedText = findViewById(R.id.textViewActivityThree);
        calculatedText.setText(
                "Now in memory: " +
                        (String)getIntent().getSerializableExtra("memoryMessage"));
    }
    public void back(View view) {
        Intent intent = new Intent(ActivityThree.this, MainActivity.class);
        startActivity(intent);
    }
}

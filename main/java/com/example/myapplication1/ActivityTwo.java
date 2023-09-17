package com.example.myapplication1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTwo extends Activity {
    EditText mEditText;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        TextView calculatedText = findViewById(R.id.calculatedView);
        calculatedText.setText(
                "Now calculated: " +
                (String)getIntent().getSerializableExtra("calculatedMessage"));
    }
    public void back(View view) {
        mEditText = findViewById(R.id.editText);
        String message = mEditText.getText().toString();
        Intent intent = new Intent(ActivityTwo.this, MainActivity.class);
        intent.putExtra("message",message);
        startActivity(intent);
    }
}

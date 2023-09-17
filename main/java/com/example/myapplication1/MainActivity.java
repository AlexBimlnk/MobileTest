package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {
    Button mButton0, mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9,
            mButtonPoint, mButtonAdd, mButtonSub, mButtonDiv, mButtonMul, mButtonEq,
            mButtonProcent, mButtonM, mButtonMC;
    EditText mEditText;
    float mValueOne , mValueTwo;
    Optional<Float> memory = null;
    boolean isAddition, isSubtract, isMultiplication, isDivision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callSecondActivity = findViewById(R.id.bCallSecondActivity);
        callSecondActivity.setOnClickListener(this::GoToSecondActivity);
        TextView mTextView = findViewById(R.id.textViewActivity);
        mTextView.setText((String)getIntent().getSerializableExtra("message"));

        Button callThirdActivity = findViewById(R.id.bCallThirdActivity);
        callThirdActivity.setOnClickListener(this::GoToThirdActivity);

        mButton0 = (Button) findViewById(R.id.b0);
        mButton1 = (Button) findViewById(R.id.b1);
        mButton2 = (Button) findViewById(R.id.b2);
        mButton3 = (Button) findViewById(R.id.b3);
        mButton4 = (Button) findViewById(R.id.b4);
        mButton5 = (Button) findViewById(R.id.b5);
        mButton6 = (Button) findViewById(R.id.b6);
        mButton7 = (Button) findViewById(R.id.b7);
        mButton8 = (Button) findViewById(R.id.b8);
        mButton9 = (Button) findViewById(R.id.b9);
        mButtonPoint = (Button) findViewById(R.id.bPoint);
        mButtonAdd = (Button) findViewById(R.id.bPlus);
        mButtonSub = (Button) findViewById(R.id.bMinus);
        mButtonMul = (Button) findViewById(R.id.bMulti);
        mButtonDiv = (Button) findViewById(R.id.bDivide);
        mButtonEq = (Button) findViewById(R.id.bEquals);
        mEditText = (EditText) findViewById(R.id.editText1);
        mButtonProcent = (Button) findViewById(R.id.bProcent);
        mButtonM = (Button) findViewById(R.id.bM);
        mButtonMC = (Button) findViewById(R.id.bMC);

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "1");
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "2");
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "3");
            }
        });
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "4");
            }
        });
        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "5");
            }
        });
        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "6");
            }
        });
        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "7");
            }
        });
        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "8");
            }
        });
        mButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "9");
            }
        });
        mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "0");
            }
        });
        mButtonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + ".");
            }
        });

        mButtonAdd.setOnClickListener(v -> {
            if (mEditText.getText().toString().isEmpty() && memory.isPresent()){
                mValueOne = memory.get();
                isAddition = true;
            }
            else if(mEditText.getText() != null &&
                Character.isDigit(mEditText.getText().charAt(mEditText.getText().length() - 1))){
                mValueOne = Float.parseFloat(mEditText.getText() + "");
                isAddition = true;
                mEditText.setText(null);
            }
        });
        mButtonSub.setOnClickListener(v -> {
            if (mEditText.getText().toString().isEmpty() && memory.isPresent()){
                mValueOne = memory.get();
                isSubtract = true;
            }
            else if(mEditText.getText() != null &&
                    Character.isDigit(mEditText.getText().charAt(mEditText.getText().length() - 1))){
                mValueOne = Float.parseFloat(mEditText.getText() + "");
                isSubtract = true;
                mEditText.setText(null);
            }
        });
        mButtonMul.setOnClickListener(v -> {
            if (mEditText.getText().toString().isEmpty() && memory.isPresent()){
                mValueOne = memory.get();
                isMultiplication = true;
            }
            else if(mEditText.getText() != null &&
                    Character.isDigit(mEditText.getText().charAt(mEditText.getText().length() - 1))){
                mValueOne = Float.parseFloat(mEditText.getText() + "");
                isMultiplication = true;
                mEditText.setText(null);
            }
        });
        mButtonDiv.setOnClickListener(v -> {
            if (mEditText.getText().toString().isEmpty() && memory.isPresent()){
                mValueOne = memory.get();
                isDivision = true;
            }
            else if(mEditText.getText() != null &&
                    Character.isDigit(mEditText.getText().charAt(mEditText.getText().length() - 1))){
                mValueOne = Float.parseFloat(mEditText.getText() + "");
                isDivision = true;
                mEditText.setText(null);
            }
        });

        mButtonEq.setOnClickListener(v -> {
            if (!Character.isDigit(mEditText.getText().charAt(mEditText.getText().length() - 1))){
                return;
            }
            mValueTwo = Float.parseFloat(mEditText.getText() + "");
            if (isAddition == true ) {
                mEditText.setText(mValueOne + mValueTwo + "");
                isAddition = false;
            }
            if (isSubtract == true ) {
                mEditText.setText(mValueOne - mValueTwo + "");
                isSubtract = false;
            }
            if (isMultiplication == true ) {
                mEditText.setText(mValueOne * mValueTwo + "");
                isMultiplication = false;
            }
            if (isDivision == true ) {
                mEditText.setText(mValueOne / mValueTwo + "");
                isDivision = false;
            }
        });

        mButtonProcent.setOnClickListener(v -> {
            if (!Character.isDigit(mEditText.getText().charAt(mEditText.getText().length() - 1))){
                return;
            }

            mValueTwo = Float.parseFloat(mEditText.getText() + "");
            float procentValue = mValueOne * mValueTwo / 100;
            if (isAddition) {
                mEditText.setText(mValueOne + procentValue + "");
                isAddition = false;
            }
            if (isSubtract) {
                mEditText.setText(mValueOne - procentValue + "");
                isSubtract = false;
            }
        });

        mButtonM.setOnClickListener(v -> {
            if (!Character.isDigit(mEditText.getText().charAt(mEditText.getText().length() - 1))){
                return;
            }
            if (mEditText.getText() != null) {
                memory = Optional.of(Float.parseFloat(mEditText.getText() + ""));
                mEditText.setText(null);
            }
        });
        mButtonMC.setOnClickListener(v -> {
            memory = Optional.empty();
        });
    }

    public void GoToSecondActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
        intent.putExtra("calculatedMessage", mEditText.getText().toString());
        startActivity(intent);
    }

    public void GoToThirdActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityThree.class);
        intent.putExtra("memoryMessage", memory.get().toString());
        startActivity(intent);
    }
}
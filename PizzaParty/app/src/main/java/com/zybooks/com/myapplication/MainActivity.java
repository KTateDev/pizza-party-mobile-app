package com.zybooks.com.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final int SLICES_PER_PIZZA = 8;

    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the widgets to class variables
        mNumAttendEditText = findViewById(R.id.attendEditText);
        mNumPizzasTextView = findViewById(R.id.answerTextView);
        mHowHungryRadioGroup = findViewById(R.id.hungryRadioGroup);
    }
    private static final String TAG = "Pizza Party";
    public void calculateClick(View view) {
       
        // Get how many are attending the party
        int numAttend;

        Log.v(TAG, "Ran Calculation for pizzas needed");
        try {
            String numAttendStr = mNumAttendEditText.getText().toString();
            numAttend = Integer.parseInt(numAttendStr);

        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }

        // Get hunger level selection
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        PizzaCalculator.HungerLevel hungerLevel = PizzaCalculator.HungerLevel.RAVENOUS;
        if (checkedId == R.id.lightRadioButton) {
            hungerLevel = PizzaCalculator.HungerLevel.LIGHT;
        }
        else if (checkedId == R.id.mediumRadioButton) {
            hungerLevel = PizzaCalculator.HungerLevel.MEDIUM;
        }

        // Show the number of pizzas needed
        PizzaCalculator calc = new PizzaCalculator(numAttend, hungerLevel);
        int totalPizzas = calc.totalPizzas();
        String totalText = getString(R.string.total_pizzas);
        mNumPizzasTextView.setText(totalText + " " + totalPizzas);
    }
}
package com.example.unitx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Objects;

public class CalculationDisplay extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private EditText value1, value2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_display);
        Intent intent = getIntent();
        String clicked_name = intent.getStringExtra("Calculation Unit");
        String category_name = intent.getStringExtra("Passing Category");
        button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView3);
        textView1 = findViewById(R.id.unit1);
        textView2 = findViewById(R.id.unit2);
        textView3 = findViewById(R.id.ResultText);
        textView4 = findViewById(R.id.FormulaText);
        value1 = findViewById(R.id.editTextNumber1);
        value2 = findViewById(R.id.editTextNumber2);
        textView.setText(clicked_name);
        String unit1, unit2;
        double n;
        JSONObject double_inner_object = new JSONHandler(this).getInnerJSONValues(category_name, clicked_name);
        try {
            unit1 = double_inner_object.getString("unit1");
            unit2 = double_inner_object.getString("unit2");
            n = double_inner_object.getDouble("n");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        assert category_name != null;
        if (category_name.equals("Temperature")) {
            switch (Objects.requireNonNull(clicked_name)) {
                case "Celsius <=> Kelvin":
                    textView1.setText(unit1);
                    textView2.setText(unit2);
                    textView4.setVisibility(View.GONE);
                    button.setOnClickListener(v -> {
                        disableSoftKeyboard();
                        String s1 = value1.getText().toString();
                        String s2 = value2.getText().toString();
                        if (s1.isEmpty() && s2.isEmpty()) {
                            textView3.setText(R.string.enter_value_convert);
                            textView4.setVisibility(View.GONE);
                        }
                        if (s1.isEmpty()) {
                            double u1 = Double.parseDouble(s2);
                            double u2 = u1 - 273.15;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u1, unit2, u2, unit1));
                            textView4.setText(MessageFormat.format("FORMULA,\n\n{0}={1}-{2}", unit1, unit2, 273.15));
                            textView4.setVisibility(View.VISIBLE);
                        } else if (s2.isEmpty()) {
                            double u2 = Double.parseDouble(s1);
                            double u1 = u2 + 273.15;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u2, unit1, u1, unit2));
                            textView4.setText(MessageFormat.format("FORMULA,\n{0}={1}+{2}", unit2, unit1, 273.15));
                            textView4.setVisibility(View.VISIBLE);
                        } else {
                            textView3.setText(R.string.invalid_input);
                            textView4.setVisibility(View.GONE);
                        }
                    });
                    break;
                case "Fahrenheit <=> Kelvin":
                    textView1.setText(unit1);
                    textView2.setText(unit2);
                    textView4.setVisibility(View.GONE);
                    button.setOnClickListener(v -> {
                        disableSoftKeyboard();
                        String s1 = value1.getText().toString();
                        String s2 = value2.getText().toString();
                        if (s1.isEmpty() && s2.isEmpty()) {
                            textView3.setText(R.string.enter_value_convert);
                            textView4.setVisibility(View.GONE);
                        } else if (s1.isEmpty()) {
                            double u1 = Double.parseDouble(s2);
                            double u2 = (u1 - 273.15) * (9.0 / 5.0) + 32;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u1, unit2, u2, unit1));
                            textView4.setText(MessageFormat.format("FORMULA,\n\n{0}=({1}-273.15)*(9.0/5.0)+32", unit1, unit2));
                            textView4.setVisibility(View.VISIBLE);
                        } else if (s2.isEmpty()) {
                            double u2 = Double.parseDouble(s1);
                            double u1 = (u2 - 32) * (5.0 / 9.0) + 273.15;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u2, unit1, u1, unit2));
                            textView4.setText(MessageFormat.format("FORMULA,\n{0}=({1}-32)*(5.0/9.0)+273.15", unit2, unit1));
                            textView4.setVisibility(View.VISIBLE);
                        } else {
                            textView3.setText(R.string.invalid_input);
                            textView4.setVisibility(View.GONE);
                        }
                    });
                    break;
                case "Celsius <=> Fahrenheit":
                    textView1.setText(unit1);
                    textView2.setText(unit2);
                    textView4.setVisibility(View.GONE);
                    button.setOnClickListener(v -> {
                        disableSoftKeyboard();
                        String s1 = value1.getText().toString();
                        String s2 = value2.getText().toString();
                        if (s1.isEmpty() && s2.isEmpty()) {
                            textView3.setText(R.string.enter_value_convert);
                            textView4.setVisibility(View.GONE);
                        } else if (s1.isEmpty()) {
                            double u1 = Double.parseDouble(s2);
                            double u2 = (u1 - 32) * (5.0 / 9.0);
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u1, unit2, u2, unit1));
                            textView4.setText(MessageFormat.format("FORMULA,\n\n{0}=({1}-32)*(5.0/9.0)", unit1, unit2));
                            textView4.setVisibility(View.VISIBLE);
                        } else if (s2.isEmpty()) {
                            double u2 = Double.parseDouble(s1);
                            double u1 = u2 * 1.8 + 32;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u2, unit1, u1, unit2));
                            textView4.setText(MessageFormat.format("FORMULA,\n{0}={1}*1.8+32", unit2, unit1));
                            textView4.setVisibility(View.VISIBLE);
                        } else {
                            textView3.setText(R.string.invalid_input);
                            textView4.setVisibility(View.GONE);
                        }
                    });
                    break;
                case "Delisle <=> Kelvin":
                    textView1.setText(unit1);
                    textView2.setText(unit2);
                    textView4.setVisibility(View.GONE);
                    button.setOnClickListener(v -> {
                        disableSoftKeyboard();
                        String s1 = value1.getText().toString();
                        String s2 = value2.getText().toString();
                        if (s1.isEmpty() && s2.isEmpty()) {
                            textView3.setText(R.string.enter_value_convert);
                            textView4.setVisibility(View.GONE);
                        } else if (s1.isEmpty()) {
                            double u1 = Double.parseDouble(s2);
                            double u2 = (u1 - 273.15) * 1.5 - 100;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u1, unit2, u2, unit1));
                            textView4.setText(MessageFormat.format("FORMULA,\n\n{0}=({1}-273.15)*1.5=100", unit1, unit2));
                            textView4.setVisibility(View.VISIBLE);
                        } else if (s2.isEmpty()) {
                            double u2 = Double.parseDouble(s1);
                            double u1 = (373.15 - u2) * (2.0 / 3.0);
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u2, unit1, u1, unit2));
                            textView4.setText(MessageFormat.format("FORMULA,\n{0}=(373.15-{1})*(2.0/3.0)", unit2, unit1));
                            textView4.setVisibility(View.VISIBLE);
                        } else {
                            textView3.setText(R.string.invalid_input);
                            textView4.setVisibility(View.GONE);
                        }
                    });
                    break;
                case "Newton <=> Kelvin":
                    textView1.setText(unit1);
                    textView2.setText(unit2);
                    textView4.setVisibility(View.GONE);
                    button.setOnClickListener(v -> {
                        disableSoftKeyboard();
                        String s1 = value1.getText().toString();
                        String s2 = value2.getText().toString();
                        if (s1.isEmpty() && s2.isEmpty()) {
                            textView3.setText(R.string.enter_value_convert);
                            textView4.setVisibility(View.GONE);
                        } else if (s1.isEmpty()) {
                            double u1 = Double.parseDouble(s2);
                            double u2 = (u1 - 273.15) * 0.33;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u1, unit2, u2, unit1));
                            textView4.setText(MessageFormat.format("FORMULA,\n\n{0}=({1}-273.15)*0.33", unit1, unit2));
                            textView4.setVisibility(View.VISIBLE);
                        } else if (s2.isEmpty()) {
                            double u2 = Double.parseDouble(s1);
                            double u1 = (u2 / 0.33) + 273.15;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u2, unit1, u1, unit2));
                            textView4.setText(MessageFormat.format("FORMULA,\n{0}=({1}/0.33)+273.15", unit2, unit1));
                            textView4.setVisibility(View.VISIBLE);
                        } else {
                            textView3.setText(R.string.invalid_input);
                            textView4.setVisibility(View.GONE);
                        }
                    });
                    break;
                case "Newton <=> Celsius":
                    methodX("Newton", "Celsius", 3.03);
                    break;
                case "Newton <=> Fahrenheit":
                    textView1.setText(unit1);
                    textView2.setText(unit2);
                    textView4.setVisibility(View.GONE);
                    button.setOnClickListener(v -> {
                        disableSoftKeyboard();
                        String s1 = value1.getText().toString();
                        String s2 = value2.getText().toString();
                        if (s1.isEmpty() && s2.isEmpty()) {
                            textView3.setText(R.string.enter_value_convert);
                            textView4.setVisibility(View.GONE);
                        } else if (s1.isEmpty()) {
                            double u1 = Double.parseDouble(s2);
                            double u2 = (u1 - 32) * 0.18333;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u1, unit2, u2, unit1));
                            textView4.setText(MessageFormat.format("FORMULA,\n\n{0}=({1}-{2})*0.18333", unit1, unit2, 32));
                            textView4.setVisibility(View.VISIBLE);
                        } else if (s2.isEmpty()) {
                            double u2 = Double.parseDouble(s1);
                            double u1 = u2 * 5.4545 + 32;
                            textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u2, unit1, u1, unit2));
                            textView4.setText(MessageFormat.format("FORMULA,\n{0}={1}*5.4545+{2}", unit2, unit1, 32));
                            textView4.setVisibility(View.VISIBLE);
                        } else {
                            textView3.setText(R.string.invalid_input);
                            textView4.setVisibility(View.GONE);
                        }
                    });
                    break;
                case "Rankine <=> Kelvin":
                    methodX("Rankine", "Kelvin", 0.555556);
                case "Rankine <=> Fahrenheit":
                    methodX("Rankine", "Fahrenheit", -458.67);
                    break;
                case "Rankine <=> Celsius":
                    methodX("Rankine", "Celsius", -272.594);
                    break;
            }
        } else {
            methodX(unit1, unit2, n);
        }
    }

    private void methodX(String st1, String st2, double n) {
        textView1.setText(st1);
        textView2.setText(st2);
        textView4.setVisibility(View.GONE);
        try {
            button.setOnClickListener(v -> {
                disableSoftKeyboard();
                String s1 = value1.getText().toString();
                String s2 = value2.getText().toString();

                if (s1.isEmpty() && s2.isEmpty()) {
                    textView3.setText(R.string.enter_value_convert);
                    textView4.setVisibility(View.GONE);
                } else if (s1.isEmpty()) {
                    double u1 = Double.parseDouble(s2);
                    double u2 = u1 / n;
                    textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u1, st2, BigDecimal.valueOf(u2).toEngineeringString(), st1));
                    textView4.setVisibility(View.VISIBLE);
                    textView4.setText(MessageFormat.format("FORMULA,\n{0}={1}/{2}", st1, st2, BigDecimal.valueOf(n).toEngineeringString()));
                } else if (s2.isEmpty()) {
                    double u2 = Double.parseDouble(s1);
                    double u1 = u2 * n;
                    textView3.setText(MessageFormat.format("{0} {1} = {2} {3}", u2, st1, BigDecimal.valueOf(u1).toEngineeringString(), st2));
                    textView4.setVisibility(View.VISIBLE);
                    textView4.setText(MessageFormat.format("FORMULA,\n{0}={1}*{2}", st2, st1, BigDecimal.valueOf(n).toEngineeringString()));
                } else {
                    textView3.setText(R.string.invalid_input);
                    textView4.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            textView3.setText(R.string.invalid_input);
            textView4.setVisibility(View.GONE);
        }
    }

    private void disableSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null)
            view = new View(this);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
package com.example.creditcard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    EditText cardNumberEditText;
    EditText dateEditText;
    EditText CVVEditText;
    EditText firstNameEditText;
    EditText lastNameEditText;
    Button submitButton;
    TextView cardNumberText, dateText, cvvText, nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardNumberEditText = findViewById(R.id.editTextCardNumber);
        CVVEditText = findViewById(R.id.editTextTextCVVNumber);
        dateEditText = findViewById(R.id.editTextTextExpireDate);
        firstNameEditText = findViewById(R.id.editTextTextFirstName);
        lastNameEditText = findViewById(R.id.editTextTextLastName);
        submitButton = findViewById(R.id.submitButton);
        cardNumberText = findViewById(R.id.cardNumberText);
        dateText = findViewById(R.id.dateText);
        cvvText = findViewById(R.id.cvvText);
        nameText = findViewById(R.id.nameText);

        cardNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    cardNumberEditText.setBackground(getDrawable(R.drawable.success_bg));
                } else {
                    cardNumberEditText.setBackground(getDrawable(R.drawable.edit_text_bacground));
                }
            }
        });

        CVVEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    CVVEditText.setBackground(getDrawable(R.drawable.success_bg));
                } else {
                    CVVEditText.setBackground(getDrawable(R.drawable.edit_text_bacground));
                }
            }
        });

        cardNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    cardNumberEditText.setBackground(getDrawable(R.drawable.success_bg));
                } else {
                    cardNumberEditText.setBackground(getDrawable(R.drawable.edit_text_bacground));
                }
            }
        });

        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    dateEditText.setBackground(getDrawable(R.drawable.success_bg));
                } else {
                    dateEditText.setBackground(getDrawable(R.drawable.edit_text_bacground));
                }
            }
        });
        firstNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    firstNameEditText.setBackground(getDrawable(R.drawable.success_bg));
                } else {
                    firstNameEditText.setBackground(getDrawable(R.drawable.edit_text_bacground));
                }
            }
        });
        lastNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    lastNameEditText.setBackground(getDrawable(R.drawable.success_bg));
                } else {
                    lastNameEditText.setBackground(getDrawable(R.drawable.edit_text_bacground));
                }
            }
        });

        submitButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Payment Successful");
            builder.setCancelable(false);

            builder
                    .setPositiveButton(
                            "OK",
                            new DialogInterface
                                    .OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

//                                    finish();
                                    dialog.dismiss();


                                }
                            });
            AlertDialog alertDialog = builder.create();

            boolean flag = true;

            if (cardNumberEditText.getText().toString().equals("") || !isValid(Long.parseLong(cardNumberEditText.getText().toString()))) {

                cardNumberEditText.setBackground(getDrawable(R.drawable.error_bg));
                cardNumberText.setVisibility(View.VISIBLE);
//                alertDialog.setMessage(isValid(Long.parseLong("79927398713")).toString());
//                alertDialog.show();
                flag = false;
            } else {
                cardNumberEditText.setBackground(getDrawable(R.drawable.success_bg));
                cardNumberText.setVisibility(View.INVISIBLE);
            }
            if (!isValidCVVNumber(CVVEditText.getText().toString())) {
                cvvText.setVisibility(View.VISIBLE);
                CVVEditText.setBackground(getDrawable(R.drawable.error_bg));
                flag = false;
            } else {
                cvvText.setVisibility(View.INVISIBLE);
                CVVEditText.setBackground(getDrawable(R.drawable.success_bg));
            }
            if (firstNameEditText.getText().toString().equals("")) {
                nameText.setVisibility(View.VISIBLE);
                firstNameEditText.setBackground(getDrawable(R.drawable.error_bg));
                flag = false;
            } else {
                nameText.setVisibility(View.INVISIBLE);
                firstNameEditText.setBackground(getDrawable(R.drawable.success_bg));
            }
            if (!isValidDate(dateEditText.getText().toString())) {
                dateText.setVisibility(View.VISIBLE);
                dateEditText.setBackground(getDrawable(R.drawable.error_bg));
                flag = false;
            } else {
                dateText.setVisibility(View.INVISIBLE);
                dateEditText.setBackground(getDrawable(R.drawable.success_bg));
            }
            if (flag) {
                alertDialog.show();
            }


        });

    }

    int getDigit(int number) {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    int getSize(long d) {
        String num = d + "";
        return num.length();
    }

    long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }

    Boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    int sumOfOddPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    Boolean isValid(long number) {

        return (getSize(number) >= 13 &&
                getSize(number) <= 16) &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0);
    }

    public static boolean isValidCVVNumber(String str) {
        String regex = "^[0-9]{3,4}$";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isValidDate(String str) {
        String regex = "(0[1-9]|10|11|12)/[0-9]{2}$";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
package com.jose.homework1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class new_entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_element);

    }


    public void submitClick(View view) {
        EditText taskTitleEditTxt = findViewById(R.id.taskTitle);
        EditText taskDescriptionEditText = findViewById(R.id.taskDescription);
        EditText taskBdayEditText = findViewById(R.id.taskBirthday);
        EditText taskTelephoneEditText = findViewById(R.id.taskPhone);
        Spinner drawableSpinner = findViewById(R.id.drawableSpinner);
        String taskNewTitle = taskTitleEditTxt.getText().toString();
        String taskNewDescription = taskDescriptionEditText.getText().toString();
        String newSelectedSound = drawableSpinner.getSelectedItem().toString();
        String taskNewPhone = taskTelephoneEditText.getText().toString();
        String taskNewBday = taskBdayEditText.getText().toString();


        Intent data = new Intent();
        data.putExtra(MainActivity.taskName, taskNewTitle);
        data.putExtra(MainActivity.selectedSound, newSelectedSound);
        data.putExtra(MainActivity.taskPhone, taskNewPhone);
        data.putExtra(MainActivity.taskBday, taskNewBday);
        data.putExtra(MainActivity.taskSurname, taskNewDescription);

        Toast.makeText(getApplicationContext(), taskNewTitle +" - "+ taskNewDescription +" - "+ newSelectedSound, Toast.LENGTH_SHORT).show();




        taskTitleEditTxt.setText("");
        taskDescriptionEditText.setText("");
        taskBdayEditText.setText("");
        taskTelephoneEditText.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        setResult(RESULT_OK, data);
        finish();

    }
}


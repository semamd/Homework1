package com.jose.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jose.homework1.tasks.TaskListContent;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener, DeleteDialog.OnFragmentInteractionListener {
    public static final int BUTTON_REQUEST = 1;
    public static final int TASK_INFO = 0;
    public static String taskName="";
    public static String taskSurname="";
    public static String taskBday="";
    public static String taskPhone="";
    public static String selectedSound="";
    public static int taskImage;
    public int n;
    public static String imageString;
    private int currentItemPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public boolean onClickButton(View view){
        Intent new_entry = new Intent(getApplicationContext(), new_entry.class);
        startActivityForResult(new_entry, BUTTON_REQUEST);
        return true;
    }


    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        if (resCode == RESULT_OK) {
            if (reqCode == BUTTON_REQUEST) {
                taskName = data.getStringExtra(taskName);
                taskSurname = data.getStringExtra(taskSurname);
                taskBday = data.getStringExtra(taskBday);
                taskPhone = data.getStringExtra(taskPhone);
                selectedSound = data.getStringExtra(selectedSound);


                Toast.makeText(getApplicationContext(), "Name: "+ taskName + "Surname: "+ taskSurname + "Phone: " + taskPhone + "Birthday "+ taskBday + "Sound: " + selectedSound, Toast.LENGTH_SHORT).show();


                if (taskName.isEmpty() && taskSurname.isEmpty()) {
                    TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1, getString(R.string.defaultName), getString(R.string.defaultSurname), selectedSound));
                } else {
                    if (taskName.isEmpty()) {
                        taskName = getString(R.string.defaultName);
                    }
                    if (taskSurname.isEmpty()) {
                        taskSurname = getString(R.string.defaultSurname);
                    }

                    TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1, taskName, taskSurname, selectedSound, taskBday, taskPhone));

                    ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();


                }
            }
            if (resCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), getText(R.string.back_message), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onListDeleteClick(int position) {
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
        currentItemPosition = position;
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        n = task.getN();
        switch (n){
            case 1:
                taskImage = R.drawable.pic1;
                break;
            case 2:
                taskImage = R.drawable.pic2;
                break;
            case 3:
                taskImage = R.drawable.pic3;
                break;
            case 4:
                taskImage = R.drawable.pic4;
                break;
            case 5:
                taskImage = R.drawable.pic5;
                break;
            case 6:
                taskImage = R.drawable.pic6;
                break;
        }
        Intent taskinfo = new Intent(getApplicationContext(), show_details.class);
        taskinfo.putExtra(taskName, task.username);
        taskinfo.putExtra(taskPhone, task.telephone);
        taskinfo.putExtra(taskBday,task.bday);
        taskinfo.putExtra(selectedSound,task.sound);
        taskinfo.putExtra(imageString,taskImage);
        startActivityForResult(taskinfo, TASK_INFO);
        Toast.makeText(getApplicationContext(), "Name: "+ taskName + "Surname: "+ taskSurname + "Phone: " + taskPhone + "Birthday "+ taskBday + "Sound: " + selectedSound, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        Toast.makeText(getApplicationContext(), "Audio not avaible yet...", Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition< TaskListContent.ITEMS.size()){
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}

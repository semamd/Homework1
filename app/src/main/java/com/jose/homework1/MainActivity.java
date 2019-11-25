package com.jose.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jose.homework1.tasks.TaskListContent;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener {
    public static final int BUTTON_REQUEST = 1;
    public static final int TASK_INFO = 0;
    public static String taskName;
    public static String taskSurname;
    public static String taskBday;
    public static String taskPhone;
    public static String selectedSound;
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
        taskName = task.username;
        taskinfo.putExtra(taskName, task.username);
        taskPhone = task.telephone;
        taskinfo.putExtra(taskPhone, task.telephone);
        taskBday = task.bday;
        taskinfo.putExtra(taskBday,task.bday);
        selectedSound = task.sound;
        taskinfo.putExtra(selectedSound,task.sound);
        taskinfo.putExtra(imageString,taskImage);
        startActivityForResult(taskinfo, TASK_INFO);
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        Toast.makeText(getApplicationContext(), "Audio not avaible yet...", Toast.LENGTH_SHORT).show();


    }

    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

}

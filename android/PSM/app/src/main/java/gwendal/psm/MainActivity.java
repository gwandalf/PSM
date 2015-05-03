package gwendal.psm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import gwendal.psm.controllers.GroupViewList;
import gwendal.psm.listeners.CreateGroupListener;
import model.GroupList;
import model.GroupListStub;


public class MainActivity extends Activity {

    /**
     * Save file name.
     */
    public static final String FILE_NAME = "Save.ser";

    public Button addGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> fileList = Arrays.asList(fileList());
        if(fileList.contains(FILE_NAME)) {
            Log.d("FACTORY", "file is present");
            try {
                Log.d("FACTORY", "loadInstance");
                FileInputStream fis = openFileInput(FILE_NAME);
                GroupList.INSTANCE = GroupListStub.load(fis);
            }catch(Exception e){
                DialogFactory.showErrorDialog("La liste des groupes ne peut pas être chargée.", this);
                GroupList.INSTANCE = new GroupListStub();
            }
        } else {
            Log.d("FACTORY", "file is absent");
            GroupList.INSTANCE = new GroupListStub();
        }
        LinearLayout layout = (LinearLayout) findViewById(R.id.group_list);
        GroupViewList.INSTANCE.observed = GroupList.INSTANCE;
        GroupViewList.INSTANCE.observed.addObserver(GroupViewList.INSTANCE);
        GroupViewList.INSTANCE.parentContext = this;
        GroupViewList.INSTANCE.parentLayout = layout;
        GroupViewList.INSTANCE.forceUpdate();
        addGroup = (Button) findViewById(R.id.add_group);
        CreateGroupListener command = new CreateGroupListener();
        addGroup.setOnClickListener(command);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            GroupList.INSTANCE.save(fos);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problème de sauvegarde", Toast.LENGTH_SHORT).show();
        }
        super.onDestroy();
    }
}

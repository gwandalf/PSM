package gwendal.psm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import gwendal.psm.controllers.ContactGroupControllerList;
import gwendal.psm.controllers.ContactGroupFactory;
import gwendal.psm.controllers.GroupButtonList;
import gwendal.psm.listeners.CreateGroupListener;
import gwendal.psm.controllers.ContactGroupController;
import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;
import model.GroupList;

import static gwendal.psm.ContactGroupActivity.CONTACT_GROUP;


public class MainActivity extends Activity {

    /**
     * ContactGroupController set.
     */
    public ContactGroupControllerList contactGroupCtrlList;

    /**
     * ContactGroupFactory.
     */
    public static ContactGroupFactory factory;

    /**
     * Layout.
     */
    private LinearLayout layout;

    public Button addGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GroupList.INSTANCE.addObserver(GroupButtonList.INSTANCE);
        this.layout = (LinearLayout) findViewById(R.id.group_list);
        List<String> fileList = Arrays.asList(fileList());
        if(fileList.contains(ContactGroupFactory.FILE_NAME)) {
            Log.d("FACTORY", "file is present");
            try {
                factory = ContactGroupFactory.loadInstance(this);
                Log.d("FACTORY", "loadInstance");
                this.contactGroupCtrlList = factory.load(this);
            }catch(Exception e){
                DialogFactory.showErrorDialog("La liste des groupes ne peut pas être chargée.", this);
            }
        } else {
            Log.d("FACTORY", "file is absent");
            factory = new ContactGroupFactory();
            this.contactGroupCtrlList = new ContactGroupControllerList();
        }
        addGroup = (Button) findViewById(R.id.add_group);
        CreateGroupListener command = new CreateGroupListener(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode >= 0 && resultCode == RESULT_OK && data != null && data.getExtras() != null) {
            Bundle extras = data.getExtras();
            ContactGroup group = (ContactGroup)extras.get(CONTACT_GROUP);
            try {
                factory.save(group, this);
                Toast.makeText(this, "Le groupe a été sauvegardé.", Toast.LENGTH_SHORT).show();
                ContactGroupController ctrl = this.contactGroupCtrlList.get(requestCode);
                ctrl.setModel(group);
            } catch (IOException e) {
                Toast.makeText(this, "Le groupe n'a pas été sauvegardé correctement.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        try {
            factory.saveInstance(this);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problème de sauvegarde", Toast.LENGTH_SHORT).show();
        }
        super.onDestroy();
    }

    /**
     * Gets the current layout.
     * @return LinearLayout.
     */
    public LinearLayout getLayout() {
        return layout;
    }
}
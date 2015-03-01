package gwendal.psm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;

import gwendal.psm.commands.AddContactGroupCommand;
import model.ContactGroup;


public class MainActivity extends Activity {

    //Contact Groups.
    private static ContactGroupListController contactGroupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.group_list);
        try {
            contactGroupList = new ContactGroupListController(this, layout);
        } catch (Exception e) {
            DialogFactory.showErrorDialog("La liste des groupes ne peut pas être chargée.", this);
        }
        Button addGroup = (Button) findViewById(R.id.add_group);
        AddContactGroupCommand command = new AddContactGroupCommand(this);
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

    public static void register(ContactGroup cg) throws IOException {
       contactGroupList.register(cg);
    }

}

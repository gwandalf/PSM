package gwendal.psm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import model.ContactGroup;


public class ContactGroupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_group);
        Button createGroup = (Button) findViewById(R.id.create_group);
        EditText et = (EditText) findViewById(R.id.group_name);
        RegisterContactGroupCommand command = new RegisterContactGroupCommand(this, et);
        createGroup.setOnClickListener(command);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_group, menu);
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

    /**
     * Launch the activity on the specified Contact Group.
     * @param cg ContactGroup to be displayed.
     */
    public static void launchOnContactGroup(ContactGroup cg, Context ctx) {
        Intent intent = new Intent(ctx, ContactGroupActivity.class);
        intent.putExtra("cg", cg);
        ctx.startActivity(intent);
    }
}

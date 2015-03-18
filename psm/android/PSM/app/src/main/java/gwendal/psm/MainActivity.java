package gwendal.psm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import gwendal.psm.controllers.ContactGroupControllerSet;
import gwendal.psm.listeners.CreateGroupListener;
import gwendal.psm.controllers.ContactGroupController;
import gwendal.psm.listeners.OpenGroupListener;
import model.ContactGroup;


public class MainActivity extends Activity {

    /**
     * ContactGroupController set.
     */
    public ContactGroupControllerSet contactGroupCtrlSet;

    /**
     * ContactGroup in creation.
     */
    public ContactGroup inCreation;

    /**
     * ContactGroup in modification.
     */
    public ContactGroup inModif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.group_list);
        this.contactGroupCtrlSet = new ContactGroupControllerSet();
        try {
            for (String file : this.fileList()) {
                ContactGroup cg = load(file);
                ContactGroupController cgc = new ContactGroupController(cg, this, file);
                this.contactGroupCtrlSet.add(cgc);
                layout.addView(cgc.getView());
                OpenGroupListener open = new OpenGroupListener(this, cg);
                cgc.getView().setOnClickListener(open);
            }
        }catch(Exception e){
            DialogFactory.showErrorDialog("La liste des groupes ne peut pas être chargée.", this);
        }
        Button addGroup = (Button) findViewById(R.id.add_group);
        CreateGroupListener command = new CreateGroupListener(this);
        addGroup.setOnClickListener(command);
    }

    @Override
    protected void onRestart() {
       super.onRestart();
       if(this.inModif != null) {
           try {
               save(this.inModif);
               Toast.makeText(this, "Le groupe a été sauvegardé.", Toast.LENGTH_LONG);
           } catch (IOException e) {
               Toast.makeText(this, "Le groupe n'a pas été sauvegardé correctement.", Toast.LENGTH_LONG);
           }
       }
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

    /**
     * Saves the specified ContactGroup.
     * @param cg ContactGroup
     * @throws IOException in case of error while saving.
     */
    public void save(ContactGroup cg) throws IOException {
        ContactGroupController ctrl = this.contactGroupCtrlSet.getControllerOfModel(cg);
        if(ctrl != null) {
            FileOutputStream fos = this.openFileOutput(ctrl.getFileName(), Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(cg);
            os.close();
            fos.close();
        }
        //TODO if null
    }

    /**
     * Loads the ContactGroup saved in the file of name fileName.
     * @param fileName File name.
     * @return Saved ContactGroup.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ContactGroup load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = this.openFileInput(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ContactGroup res = (ContactGroup) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return res;
    }
}

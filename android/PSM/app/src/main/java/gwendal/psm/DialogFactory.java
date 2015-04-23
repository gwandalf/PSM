package gwendal.psm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by gwendal on 15/02/15.
 */
public class DialogFactory {

    /**
     * Shows a dialog indicating that an error occurred.
     * @param message Error message.
     * @param ctx Context displaying this dialog.
     */
    public static void showErrorDialog(String message, Context ctx) {
        new AlertDialog.Builder(ctx)
                .setTitle("Erreur")
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    /**
     * Shows a dialog giving an information.
     * @param message Informative message.
     * @param ctx Context displaying this dialog.
     */
    public static void showInformationDialog(String message, Context ctx) {
        new AlertDialog.Builder(ctx)
                .setTitle("Information")
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

}

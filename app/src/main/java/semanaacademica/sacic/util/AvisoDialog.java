package semanaacademica.sacic.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Mauricio on 25/07/2016.
 */
public class AvisoDialog extends DialogFragment{

    private static final String TITULO = "TITULO";
    private static final String MSG = "MSG";
    private static final String OK = "OK";


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(bundle.getString(TITULO))
                .setMessage(bundle.getString(MSG))
                .setPositiveButton("Ok", (DialogInterface.OnClickListener) bundle.get(OK));
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public DialogFragment createDialog(String titulo, String msg, ButtonClickDialog ok){
        Bundle bundle = getArguments();
        bundle.putString(TITULO, titulo);
        bundle.putString(MSG, msg);
        bundle.putSerializable(OK, ok);
        return this;
    }

}

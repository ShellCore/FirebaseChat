package com.shellcore.android.firebasechat.addContact.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shellcore.android.firebasechat.R;
import com.shellcore.android.firebasechat.addContact.AddContactPresenter;
import com.shellcore.android.firebasechat.addContact.AddContactPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 27/06/2017.
 */

public class AddContactFragment extends DialogFragment implements DialogInterface.OnShowListener, AddContactView {

    // Services
    private AddContactPresenter presenter;

    // Components
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    public AddContactFragment() {
        presenter = new AddContactPresenterImpl(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.add_contact_dialog, null, false);
        ButterKnife.bind(this, v);

        builder.setView(v);
        builder.setTitle(R.string.add_contact_message_title);
        builder.setPositiveButton(R.string.add_contact_message_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton(R.string.add_contact_message_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }


    @Override
    public void onShow(DialogInterface dialogInterface) {
        AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.addContact(edtEmail.getText().toString());
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showInput() {
        edtEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        edtEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void contactAdded() {
        Toast.makeText(getActivity(), R.string.addcontact_message_contactadded, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void contactNotAdded() {
        edtEmail.setText("");
        edtEmail.setError(getString(R.string.addcontact_error_message));
    }
}

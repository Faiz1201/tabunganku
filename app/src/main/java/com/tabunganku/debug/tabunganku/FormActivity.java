package com.tabunganku.debug.tabunganku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText etUsername;
    EditText etPassword;
    TextView tvErrorName;
    TextView tvErrorPass;

    static final int CUT_ID=2211;
    static final int COPY_ID=2212;
    static final int PASTE_ID=2213;
    private MenuItem cut_n;
    private MenuItem copy_n;
    private MenuItem paste_n;

    String username;
    String password;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btnSubmit = findViewById(R.id.btn_submit);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        tvErrorName = findViewById(R.id.tv_error_username);
        tvErrorPass = findViewById(R.id.tv_error_password);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save(){
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            tvErrorName.setText("Username Harus Diisi !");
            etUsername.requestFocus();
            return;
        } else {
            tvErrorName.setText(null);
        }

        if (password.isEmpty()) {
            tvErrorPass.setText("Username dan Password Tidak Sama");
            etPassword.requestFocus();
            return;
        } else {
            tvErrorPass.setText(null);
        }

        openHomepage();
        return;
    }

     public void openHomepage(){
         Intent intent = new Intent(this, HomepageActivity.class);
         startActivity(intent);
     }

     public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CUT_ID,0, "Cut");
        menu.add(0, COPY_ID,1, "Copy");
        menu.add(0, PASTE_ID,2, "Paste");
        cut_n=menu.getItem(0);
        copy_n=menu.getItem(1);
        paste_n=menu.getItem(2);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        String editUser=etUsername.getText().toString();
        String editPass=etPassword.getText().toString();

        if (editUser.matches("") && !editPass.matches("")){
            if (etUsername.hasFocus()) {
                cut_n.setEnabled(true);
                copy_n.setEnabled(true);
                paste_n.setEnabled(false);
            } else {
                cut_n.setEnabled(false);
                copy_n.setEnabled(false);
                paste_n.setEnabled(true);
            }
        } else if (!editUser.matches("") && editPass.matches("")){
            cut_n.setEnabled(false);
            copy_n.setEnabled(false);
            paste_n.setEnabled(true);

         } else if (editUser.matches("") && editPass.matches("")){
            cut_n.setEnabled(false);
            copy_n.setEnabled(false);
            paste_n.setEnabled(true);

        } else if (!editUser.matches("") && editPass.matches("")){
            cut_n.setEnabled(false);
            copy_n.setEnabled(false);
            paste_n.setEnabled(true);
        } else {
            cut_n.setEnabled(false);
            copy_n.setEnabled(false);
            paste_n.setEnabled(true);
        }

     }

     public String editUser;
     public String editPass;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CUT_ID:

                editUser = etUsername.getText().toString();
                etUsername.setText("");
                if (etUsername.hasFocus()) {
                    cut_n.setEnabled(false);
                    copy_n.setEnabled(false);
                    paste_n.setEnabled(false);
                } else {
                    cut_n.setEnabled(false);
                    copy_n.setEnabled(false);
                    paste_n.setEnabled(true);
                }

                break;

            case COPY_ID:

                editUser = etUsername.getText().toString();
                editPass = etPassword.getText().toString();
                break;

            case PASTE_ID:
                etUsername.setText(editUser);
                etPassword.setText(editPass);
        }
        return true;
    }
}

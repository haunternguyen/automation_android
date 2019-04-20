package com.example.hoangdang.autodroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtTaiKhoan;
    EditText txtMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtTaiKhoan = (EditText) findViewById(R.id.txtTaiKhoan);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk = txtTaiKhoan.getText().toString();
                String mk = txtMatKhau.getText().toString();
                if (tk.equals("captain") && mk.equals("america")) {
                    setContentView(R.layout.activity_home);
                } else {
                    Toast.makeText(getApplicationContext(), "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

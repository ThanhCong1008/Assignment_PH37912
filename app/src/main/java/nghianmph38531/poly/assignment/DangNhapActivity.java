package nghianmph38531.poly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import nghianmph38531.poly.assignment.DAO.NguoidDungDAO;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangnhap;
    TextView tvDangky;
    TextInputEditText edUsername, edMatkhau;
    NguoidDungDAO nguoidDungDAO;
    CheckBox cbRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        tvDangky = findViewById(R.id.tvDangky);
        edUsername = findViewById(R.id.edUsername);
        edMatkhau = findViewById(R.id.edPassword);
        cbRemember = findViewById(R.id.cbRemember);
        nguoidDungDAO = new NguoidDungDAO(DangNhapActivity.this);

        //put data to SharedPre
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = preferences.getString("username", "");
        String pass = preferences.getString("password", "");
        boolean rem = preferences.getBoolean("remember", false);

        edUsername.setText(user);
        edMatkhau.setText(pass);
        cbRemember.setChecked(rem);

        //xu ly dang nhap
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edUsername.getText().length() == 0 || edMatkhau.getText().length() ==0){
                    Toast.makeText(DangNhapActivity.this, "Hay nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }else{
                    if (nguoidDungDAO.checkLogin(edUsername.getText().toString(), edMatkhau.getText().toString()) > 0){
                        Toast.makeText(DangNhapActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        remember(edUsername.getText().toString().trim(), edMatkhau.getText().toString().trim(), cbRemember.isChecked());
                        startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(DangNhapActivity.this, "Dang nhap khong thanh cong "+nguoidDungDAO.getAll().get(3).getTenDangNhap()
                                +nguoidDungDAO.getAll().get(3).getMatKhau(), Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        //chuyen sang ma hinh dang ky
        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });

    }

    public void remember(String user, String pass, boolean status){
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(!status){
            //xoa luu tru truoc do
            editor.clear();
        }else{
            editor.putString("username", user);
            editor.putString("password", pass);
            editor.putBoolean("remember", status);
        }
        editor.commit();
    }

}
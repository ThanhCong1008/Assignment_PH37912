package nghianmph38531.poly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import nghianmph38531.poly.assignment.DAO.NguoidDungDAO;
import nghianmph38531.poly.assignment.model.NguoiDung;

public class DangKyActivity extends AppCompatActivity {

    TextInputEditText edHoten, edDangkypassword, edDangkyusername, edNhaplaipassword;
    Button btnDangky;
    NguoidDungDAO nguoidDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edHoten = findViewById(R.id.edHoten);
        edDangkypassword = findViewById(R.id.edDangkypassword);
        edDangkyusername = findViewById(R.id.edDangkyusername);
        edNhaplaipassword = findViewById(R.id.edNhaplaipassword);
        btnDangky = findViewById(R.id.btnDangky);
        nguoidDungDAO = new NguoidDungDAO(DangKyActivity.this);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()>0){
                    NguoiDung nguoiDung = new NguoiDung();

                    nguoiDung.setTenDangNhap(edDangkyusername.getText().toString().trim());
                    nguoiDung.setMatKhau(edDangkypassword.getText().toString().trim());
                    nguoiDung.setHoTen(edHoten.getText().toString().trim());
                    if(nguoidDungDAO.insert(nguoiDung)>0){
                        Toast.makeText(DangKyActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));
                    }else {
                        Toast.makeText(DangKyActivity.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                    }
                }
             /*   startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));*/
            }
        });


    }

    public int validate(){
        int check = 1;
        if (edHoten.getText().length() == 0 || edDangkyusername.getText().length() == 0 || edDangkypassword.getText().length() == 0 || edNhaplaipassword.getText().length() == 0){
            Toast.makeText(this, "Khong bo trong", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (!edDangkypassword.getText().toString().trim().matches(edNhaplaipassword.getText().toString().trim())){
            Toast.makeText(this, "Mat khau khong trung khop", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
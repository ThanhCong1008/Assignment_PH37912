package nghianmph38531.poly.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import nghianmph38531.poly.assignment.fragment.GioiThieuFragment;
import nghianmph38531.poly.assignment.fragment.QuanLySanPhamFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View mHeaderView;
    boolean isSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        NavigationView nvView = findViewById(R.id.nvView);
        //show user in header
        mHeaderView = nvView.getHeaderView(0);

        //set home activity
        FragmentManager manager = getSupportFragmentManager();
        QuanLySanPhamFragment phieuMuonFragment = new QuanLySanPhamFragment();
        manager.beginTransaction()
                .replace(R.id.fl_content, phieuMuonFragment).commit();

        nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                if(item.getItemId() == R.id.nav_quanlysanpham){
                    setTitle("Quan ly san pham");
                    QuanLySanPhamFragment quanLySanPhamFragment = new QuanLySanPhamFragment();
                    manager.beginTransaction()
                            .replace(R.id.fl_content, quanLySanPhamFragment).commit();
                    drawerLayout.close();
                    isSelected = true;
                } else if (item.getItemId() == R.id.nav_gioithieu) {
                    setTitle("Gioi thieu");
                    GioiThieuFragment gioiThieuFragment = new GioiThieuFragment();
                    manager.beginTransaction()
                            .replace(R.id.fl_content, gioiThieuFragment).commit();
                    drawerLayout.close();
                    isSelected = true;
                } else if (item.getItemId() == R.id.sub_caidat) {
                    setTitle("Cai dat");

                    drawerLayout.close();
                    isSelected = true;
                }else if (item.getItemId() == R.id.sub_dangxuat) {
                    startActivity(new Intent(MainActivity.this, DangNhapActivity.class));
                    finish();
                }
                drawerLayout.closeDrawers();

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    public void exit(){
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Do you want to exit the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kết thúc ứng dụng khi người dùng xác nhận thoát
                        startActivity(new Intent(MainActivity.this, DangNhapActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (isSelected) {
                drawerLayout.openDrawer(GravityCompat.START); // nếu isSelected = true, hiển thị NavigationView khi ấn back
                isSelected = false; // reset lại giá trị của biến isSelected
            } else {
                exit(); // nếu isSelected = false, thoát ứng dụng khi ấn back
            }
        }
    }

}
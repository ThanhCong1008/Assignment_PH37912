package nghianmph38531.poly.assignment.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import nghianmph38531.poly.assignment.DAO.SanPhamDAO;
import nghianmph38531.poly.assignment.R;
import nghianmph38531.poly.assignment.adapter.SanPhamAdapter;
import nghianmph38531.poly.assignment.model.SanPham;


public class QuanLySanPhamFragment extends Fragment {

    RecyclerView recyclerView;
    SanPhamDAO sanPhamDAO;
    List<SanPham> list;
    SanPhamAdapter sanPhamAdapter;
    FloatingActionButton fab;
    //khai bao cho dialog
    EditText edTensanpham, edGiasanpham, edSoluong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_san_pham, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        fab = view.findViewById(R.id.fab);
        list = new ArrayList<>();
        sanPhamDAO = new SanPhamDAO(getContext());

        list = sanPhamDAO.getData();
        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
        recyclerView.setAdapter(sanPhamAdapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
//                    fab.hide();
//                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
//                    fab.show();
//                }
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemVaSuaSanPham();
            }
        });

        return view;
    }

    public void dialogThemVaSuaSanPham(){
        SanPham sanPham = new SanPham();
        Dialog dialog1 = new Dialog(getContext());
        dialog1.setContentView(R.layout.dialog_them_san_pham);

        Button btnThemsp = dialog1.findViewById(R.id.btnThemsanpham);
        edTensanpham = dialog1.findViewById(R.id.edTensanpham);
        edGiasanpham = dialog1.findViewById(R.id.edGiaban);
        edSoluong = dialog1.findViewById(R.id.edSoluong);

        btnThemsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bat dau them san pham
                if(validate()>0){
                    sanPham.setTenSp(edTensanpham.getText().toString());
                    sanPham.setGiaBan(Integer.parseInt(edGiasanpham.getText().toString()));
                    sanPham.setSoLuong(Integer.parseInt(edSoluong.getText().toString()));
                    if(sanPhamDAO.insert(sanPham)>0){
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                        capNhat();
                        dialog1.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog1.show();
    }

    public  int validate(){
        int check = 1;
        if(edTensanpham.getText().length() == 0 ||
        edGiasanpham.getText().length() == 0 ||
        edSoluong.getText().length() == 0){
            Toast.makeText(getContext(), "Khong bo trong", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhat(){
        list = sanPhamDAO.getData();
        sanPhamAdapter = new SanPhamAdapter(getContext(), list);
        recyclerView.setAdapter(sanPhamAdapter);
    }

}
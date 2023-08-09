package nghianmph38531.poly.assignment.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import nghianmph38531.poly.assignment.DAO.SanPhamDAO;
import nghianmph38531.poly.assignment.R;
import nghianmph38531.poly.assignment.model.SanPham;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    Context context;
    List<SanPham> list;
    SanPhamDAO sanPhamDAO;
    EditText edTensanpham, edGiasanpham, edSoluong;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHolder holder, int position) {
        holder.tvTensp.setText(list.get(position).getTenSp());
        holder.tvGiasp.setText(""+list.get(position).getGiaBan());
        holder.tvSoluong.setText(""+list.get(position).getSoLuong());
        holder.tvChinhsua.setPaintFlags(holder.tvChinhsua.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.tvXoa.setPaintFlags(holder.tvXoa.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.tvChinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemVaSuaSanPham(holder.getAdapterPosition());
            }
        });
        holder.tvXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaSanPham(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTensp, tvGiasp, tvSoluong, tvChinhsua, tvXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTensp = itemView.findViewById(R.id.tvTensanpham);
            tvGiasp = itemView.findViewById(R.id.tvGiasanpham);
            tvSoluong = itemView.findViewById(R.id.tvSoluong);
            tvChinhsua = itemView.findViewById(R.id.tvChinhsua);
            tvXoa = itemView.findViewById(R.id.tvXoa);
        }
    }

    public void dialogThemVaSuaSanPham(int viTri){
        SanPham sanPham = list.get(viTri);
        Dialog dialog1 = new Dialog(context);
        dialog1.setContentView(R.layout.dialog_them_san_pham);
        TextView tvDialog = dialog1.findViewById(R.id.tvDialog);
        Button btnSua = dialog1.findViewById(R.id.btnThemsanpham);
        btnSua.setText("Chinh sua");
        tvDialog.setText("Chinh sua san pham");
        edTensanpham = dialog1.findViewById(R.id.edTensanpham);
        edGiasanpham = dialog1.findViewById(R.id.edGiaban);
        edSoluong = dialog1.findViewById(R.id.edSoluong);

        edTensanpham.setText(sanPham.getTenSp());
        edGiasanpham.setText(""+sanPham.getGiaBan());
        edSoluong.setText(""+sanPham.getSoLuong());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bat dau chinh sua san pham
                sanPhamDAO = new SanPhamDAO(context);
                if(validate()>0){
                    sanPham.setTenSp(edTensanpham.getText().toString());
                    sanPham.setGiaBan(Integer.parseInt(edGiasanpham.getText().toString()));
                    DecimalFormat decimalFormat = new DecimalFormat("#,##");
                    sanPham.setSoLuong(Integer.parseInt(edSoluong.getText().toString()));
                    if(sanPhamDAO.update(sanPham)>0){
                        getDS();
                        dialog1.dismiss();
                        Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        dialog1.show();
    }

    public void xoaSanPham(int viTri){
        new AlertDialog.Builder(context)
                .setTitle("Confirm")
                .setMessage("Ban co muon xoa san pham '"+list.get(viTri).getTenSp()+"'?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // xoa
                        sanPhamDAO = new SanPhamDAO(context);
                        if(sanPhamDAO.delete(String.valueOf(list.get(viTri).getMaSP()))>0){
                            getDS();
                            Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void getDS() {
        list.clear();
        list = sanPhamDAO.getData();
        notifyDataSetChanged();
    }

    public  int validate(){
        int check = 1;
        if(edTensanpham.getText().length() == 0 ||
                edGiasanpham.getText().length() == 0 ||
                edSoluong.getText().length() == 0){
            Toast.makeText(context, "Khong bo trong", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}

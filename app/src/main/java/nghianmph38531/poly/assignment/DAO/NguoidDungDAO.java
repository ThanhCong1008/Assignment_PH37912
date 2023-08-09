package nghianmph38531.poly.assignment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nghianmph38531.poly.assignment.fragment.DbHelper.DbHelper;
import nghianmph38531.poly.assignment.model.NguoiDung;

public class NguoidDungDAO{
    private SQLiteDatabase db;

    public NguoidDungDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("tendangnhap", nd.getTenDangNhap());
        values.put("matkhau", nd.getMatKhau());
        values.put("hoten", nd.getHoTen());

        return db.insert("nguoidung", null, values);
    }

    public int checkLogin(String tendangnhap, String matkhau){
        String sql = "select * from nguoidung where tendangnhap=? and matkhau=?";
        List<NguoiDung> list = getData(sql, tendangnhap, matkhau);
        if(list.size() == 0){
            return -1;
        }else{
            return 1;
        }
    }

    @SuppressLint("Range")
    public List<NguoiDung> getData(String sql, String...selectinArgs){
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectinArgs);
        while (cursor.moveToNext()){
            NguoiDung thuThu = new NguoiDung();
            thuThu.setTenDangNhap(cursor.getString(cursor.getColumnIndex("tendangnhap")));
            thuThu.setMatKhau(cursor.getString(cursor.getColumnIndex("matkhau")));
            thuThu.setHoTen(cursor.getString(cursor.getColumnIndex("hoten")));

            list.add(thuThu);
        }
        return list;
    }

    public List<NguoiDung> getAll(){
        String sql = "select * from nguoidung";
        return getData(sql);
    }
}

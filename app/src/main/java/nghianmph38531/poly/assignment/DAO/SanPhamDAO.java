package nghianmph38531.poly.assignment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nghianmph38531.poly.assignment.fragment.DbHelper.DbHelper;
import nghianmph38531.poly.assignment.model.SanPham;

public class SanPhamDAO {
    private SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SanPham sp){
        ContentValues values = new ContentValues();
        values.put("tensanpham", sp.getTenSp());
        values.put("giasanpham", sp.getGiaBan());
        values.put("soluong", sp.getSoLuong());

        return db.insert("sanpham", null, values);
    }

    public long update(SanPham sp){
        ContentValues values = new ContentValues();
        values.put("tensanpham", sp.getTenSp());
        values.put("giasanpham", sp.getGiaBan());
        values.put("soluong", sp.getSoLuong());
        return db.update("sanpham", values, "masp = ?", new String[]{String.valueOf(sp.getMaSP())});
    }

    public long delete(String id){
        return db.delete("sanpham", "masp = ?", new String[]{id});
    }

    @SuppressLint("Range")
    public ArrayList<SanPham> getData(){
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * From sanpham",null);
        while (cursor.moveToNext()){

            list.add(new SanPham(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
        }
        return list;
    }





}

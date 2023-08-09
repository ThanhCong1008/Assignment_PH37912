package nghianmph38531.poly.assignment.fragment.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, "QuanLySanPham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table nguoidung(tendangnhap text primary key not null, " +
                "matkhau text not null, " +
                "hoten text not null)");
        db.execSQL("create table sanpham(masp integer primary key autoincrement, " +
                "tensanpham text not null, " +
                "giasanpham integer not null, " +
                "soluong integer not null)");
        db.execSQL("insert into sanpham(tensanpham, giasanpham, soluong) values ('Banh bong lan huong que', '45000', '100')," +
                "('Xuc xich an lien vi heo nuong', '12000', '200'), " +
                "('Mi tom hao hao chua cay', '70000', '90'), " +
                "('Sua gao Han Quoc len men', '30000', '50')");
        db.execSQL("insert into nguoidung(tendangnhap, matkhau, hoten) values ('longphi2001','2001','Phi Dinh Long')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists sanpham");
        db.execSQL("drop table if exists nguoidung");

        onCreate(db);
    }
}

package nghianmph38531.poly.assignment.model;

public class SanPham {
    private int maSP;
    private String tenSp;
    private int giaBan;
    private int soLuong;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSp, int giaBan, int soLuong) {
        this.maSP = maSP;
        this.tenSp = tenSp;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication15
{
    public class Lop
    {
        public string MaLop;
        public int SiSoToiDa;
        public DanhSachLop dsLop = new DanhSachLop();
        public bool DangKyHocPhan(SinhVien sv)
        {
            ChiTietDKLop ctietDK = ChiTietDKLop.DangKySvMoi(this, sv);//new ChiTietDKLop(this, sv);
            dsLop.chiTiet.Add(ctietDK);
        }
    }
}
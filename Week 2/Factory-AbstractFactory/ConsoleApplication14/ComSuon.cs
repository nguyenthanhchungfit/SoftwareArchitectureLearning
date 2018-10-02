using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication14
{
    public class ComSuon : MonAn
    {
        public override MonAn Clone()
        {
            ComSuon monan = new ComSuon();
            monan.DonGia = this.DonGia;
            monan.TenMonAn = this.TenMonAn;
            return monan;
        }

    }
}
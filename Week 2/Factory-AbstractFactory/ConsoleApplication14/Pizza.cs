using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication14
{
    public class Pizza : MonAn
    {
        public override MonAn Clone()
        {
            Pizza monan = new Pizza();
            monan.DonGia = this.DonGia;
            monan.TenMonAn = this.TenMonAn;
            return monan;
        }
    }
}
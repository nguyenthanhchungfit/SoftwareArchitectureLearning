using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication14
{
    public class HuTieu : MonAn
    {
        public override MonAn Clone()
        {
            HuTieu monan = new HuTieu();
            monan.DonGia = this.DonGia;
            monan.TenMonAn = this.TenMonAn;
            return monan;
        }

    }
}
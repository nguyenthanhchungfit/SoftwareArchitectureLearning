using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication14
{
    public abstract class Item
    {
        private string tenMonAn;
        private int donGia;

        public string TenMonAn
        {
            get
            {
                return tenMonAn;
            }

            set
            {
                tenMonAn = value;
            }
        }

        public int DonGia
        {
            get
            {
                return donGia;
            }

            set
            {
                donGia = value;
            }
        }

        public abstract MonAn Clone();

    }
}
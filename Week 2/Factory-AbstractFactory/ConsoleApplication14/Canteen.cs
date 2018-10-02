using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication14
{
    public class Canteen
    {
        protected Dictionary<string, Item> monAnSample = new Dictionary<string, Item>();

        public virtual void KhoiTaoDanhSach()
        {
            monAnSample.Clear();
        }

        public MonAn CungCap(string TenMon)
        {
            /*for (int i = 0; i < monAnSample.Count; i++)
                if (monAnSample[i].TenMonAn == TenMon)
                    return monAnSample[i].Clone();*/
            foreach (string tenMon in monAnSample.Keys)
            {
                if (tenMon == TenMon)
                    return monAnSample[tenMon].Clone();

            }
            return null;
        }

    }
}
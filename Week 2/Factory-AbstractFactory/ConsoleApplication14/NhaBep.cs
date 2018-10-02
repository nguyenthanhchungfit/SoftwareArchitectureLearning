using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication14
{
    public class NhaBep : Canteen
    {
        /*public MonAn CungCap(string TenMon)
        {
            if ("Com suon" == TenMon)
                return new ComSuon();
            else if ("Hu tieu" == TenMon)
                return new HuTieu();
            else if ("Pizza" == TenMon)
                return new Pizza();
            return null;
        }*/

        //        Dictionary<string, MonAn> monAnSample = new Dictionary<string, MonAn>();

        /*      public void MoNhaHang()
              {
                  monAnSample.Clear();
                  MonAn monan;
                  monan = new ComSuon();
                  monan.DonGia = 20000;
                  monan.TenMonAn = "Com sinh vien";
                  monAnSample.Add(monan.TenMonAn, monan);
                  monan = new ComSuon();
                  monan.DonGia = 20000;
                  monan.TenMonAn = "Com giao vien";
                  monAnSample.Add(monan.TenMonAn, monan);
                  monan = new Pizza();
                  monan.DonGia = 20000;
                  monan.TenMonAn = "Pizza, cay cap do 7";
                  monAnSample.Add(monan.TenMonAn, monan);
                  monan = new Pizza();
                  monan.DonGia = 20000;
                  monan.TenMonAn = "Pizza, khong cay";
                  monAnSample.Add(monan.TenMonAn, monan);
              }
              */
        public override void KhoiTaoDanhSach()
        {
            base.KhoiTaoDanhSach();
            MonAn monan;
            monan = new ComSuon();
            monan.DonGia = 20000;
            monan.TenMonAn = "Com sinh vien";
            monAnSample.Add(monan.TenMonAn, monan);
            monan = new ComSuon();
            monan.DonGia = 20000;
            monan.TenMonAn = "Com giao vien";
            monAnSample.Add(monan.TenMonAn, monan);
            monan = new Pizza();
            monan.DonGia = 20000;
            monan.TenMonAn = "Pizza, cay cap do 7";
            monAnSample.Add(monan.TenMonAn, monan);
            monan = new Pizza();
            monan.DonGia = 20000;
            monan.TenMonAn = "Pizza, khong cay";
            monAnSample.Add(monan.TenMonAn, monan);

        }
/*        public MonAn CungCap(string TenMon)
        {
            /*for (int i = 0; i < monAnSample.Count; i++)
                if (monAnSample[i].TenMonAn == TenMon)
                    return monAnSample[i].Clone();*/
  /*          foreach (string tenMon in monAnSample.Keys)
            {
                if (tenMon == TenMon)
                    return monAnSample[tenMon].Clone();

            }
            return null;
        }

        public MonAn CungCapMonAnVuaTuiTien(int GiaToiDa)
        {
            /*for (int i = 0; i < monAnSample.Count; i++)
                if (monAnSample[i].TenMonAn == TenMon)
                    return monAnSample[i].Clone();*/

    /*        foreach (MonAn monan in monAnSample.Values)
                if (Helper.CheckConstraint(monan, GiaToiDa, "Kiem tra theo gia toi da"))
                    return monan.Clone();
            return null;
        }

        public MonAn CungCapMonAnTheoYeuCau4_0(string strNLPQuery)
        {
            return null;
        }
        */

    }
}
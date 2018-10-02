using System;

namespace DemoDistributedObject
{
    internal class CSinhVien: CObject
    {       

        public CSinhVien(string HoTen, string MSSV, float DTB)
        {
            this.Handle = ClientObjectManager.CreateObject("SStudent");
            this.DTB = DTB;
            this.HoTen = HoTen;
            this.MSSV = MSSV;
        }

        public float DTB {
            get
            {
                return float.Parse(ClientObjectManager.GetObjectAttribute(this.Handle, "GPA"));
            }
            set
            {
                ClientObjectManager.SetObjectAttribute(this.Handle, "GPA", value.ToString());
            }
        }


        public string HoTen
        {
            get
            {
                return ClientObjectManager.GetObjectAttribute(this.Handle, "Name");
            }
            set
            {
                ClientObjectManager.SetObjectAttribute(this.Handle, "Name", value.ToString());
            }
        }
        public string MSSV { get; private set; }
    }
}
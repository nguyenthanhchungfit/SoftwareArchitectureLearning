using System;
using System.Collections.Generic;
using System.Text;

namespace DemoDistributedObject
{
    class Program
    {
        static void Main(string[] args)
        {
            CSinhVien sv1 = new CSinhVien("Nguyen Van A", "19876543", 9.87f);
            Console.WriteLine(sv1.HoTen);
            sv1.DTB = float.Parse(Console.ReadLine());
            Console.WriteLine(sv1.DTB.ToString());
            CSinhVien sv2 = new CSinhVien("","",0.00f);
            sv2.Attach(sv1.Detach());
            Console.WriteLine(sv2.HoTen);

        }
    }
}

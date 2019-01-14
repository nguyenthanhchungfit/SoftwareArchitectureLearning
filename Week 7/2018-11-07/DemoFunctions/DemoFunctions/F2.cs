using System;
using System.Collections.Generic;
using System.Text;

namespace DemoFunctions
{
    public class F2 : MyFunction
    {
        public override ExtraData Execute(ExtraData inputIntent)
        {
            int x;
            x = (int)inputIntent.GetExtra("v0");
            Console.WriteLine(x.ToString());
            ExtraData res = new ExtraData();
            return res;
        }

    }
}
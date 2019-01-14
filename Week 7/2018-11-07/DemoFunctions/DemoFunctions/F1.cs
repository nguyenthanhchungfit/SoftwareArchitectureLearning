using System;
using System.Collections.Generic;
using System.Text;

namespace DemoFunctions
{
    public class F1 : MyFunction
    {
        public override ExtraData Execute(ExtraData inputIntent)
        {
            int x;
            int.TryParse(Console.ReadLine(), out x);
            ExtraData res = new ExtraData();
            res.SetExtra("v0", x);
            return res;
        }
    }
}
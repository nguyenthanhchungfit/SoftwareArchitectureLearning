using System;
using System.Collections.Generic;
using System.Text;

namespace DemoFunctions
{
    public class F4 : MyFunction
    {
        public override ExtraData Execute(ExtraData inputIntent)
        {
            int x, y;
            x = (int)inputIntent.GetExtra("v0");
            y = (int)inputIntent.GetExtra("v1");
            int z = (x>=y)?x:y;
            ExtraData res = new ExtraData();
            res.SetExtra("v0", z);
            return res;
        }
    }
}
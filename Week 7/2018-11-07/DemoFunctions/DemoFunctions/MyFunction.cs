using System;
using System.Collections.Generic;
using System.Text;

namespace DemoFunctions
{
    public abstract class MyFunction
    {
        public virtual ExtraData Execute(ExtraData inputIntent)
        {
            return new ExtraData();
        }
    }
}
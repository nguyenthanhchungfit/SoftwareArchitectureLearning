using System;
using System.Collections.Generic;
using System.Text;

namespace DemoDistributedObject
{
    public abstract class CObject
    {
        protected int Handle;
        internal void Attach(int handle)
        {
            Handle = handle;
        }

        internal int Detach()
        {
            int handle = Handle;
            Handle = 0;

            return handle;
        }

    }
}
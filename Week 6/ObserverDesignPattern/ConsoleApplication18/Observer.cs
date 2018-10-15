using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication18
{
    public class Observer
    {
        internal virtual void Notify(Subject subject)
        {

        }

        public void Subscribe(Subject s)
        {
            s.Subscribe(this);
        }
    }
}
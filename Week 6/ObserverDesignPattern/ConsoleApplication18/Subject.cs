using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication18
{
    public class Subject
    {
        protected List<Observer> fanclub = new List<Observer>();
        public bool Subscribe(Observer o)
        {
            fanclub.Add(o);
            return true;
        }
        

        public void NotifyAll()
        {
            foreach (Observer o in fanclub)
            {
                o.Notify(this);
            }
        }
    }
}
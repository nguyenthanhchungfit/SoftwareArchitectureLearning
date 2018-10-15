using System;

namespace ConsoleApplication19
{
    internal class DummyChecker : ContentCompatibilityChecker
    {
        internal override bool Check(News news, Student student)
        {
            Random r = new Random();
            int x = r.Next() % 3;
            if (x == 0)
                return false;
            return true;
        }
    }
}
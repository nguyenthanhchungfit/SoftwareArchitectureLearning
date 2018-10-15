using System;

namespace ConsoleApplication19
{
    internal class ContentCompatibilityChecker
    {
        internal virtual bool Check(News news, Student student)
        {
            return true;
        }
    }
}
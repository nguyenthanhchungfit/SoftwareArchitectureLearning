using System;
using System.Collections.Generic;
using System.Text;

namespace DemoFunctions
{
    class Program
    {
        static void Main(string[] args)
        {
            MyEngine engine = new MyEngine();
            engine.ReadScript(@"MyScript.txt");
            engine.ExecuteProgram();
        }
    }
}

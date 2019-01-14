using System.Diagnostics;

namespace DemoFunctions
{
    internal class F5 : MyFunction
    {
        public override ExtraData Execute(ExtraData inputIntent)
        {
            Process process = new Process();
            process.StartInfo.FileName = @"f:\Teaching\1\Teaching\2012-2013\Term1\Android\windows_phone_8_development_internals.pdf";
            process.StartInfo.WindowStyle = ProcessWindowStyle.Maximized;
            process.Start();
            return base.Execute(inputIntent);
        }
    }
}
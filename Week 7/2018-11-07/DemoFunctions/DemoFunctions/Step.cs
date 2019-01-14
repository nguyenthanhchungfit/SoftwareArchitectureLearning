using System;
using System.IO;

namespace DemoFunctions
{
    internal class Step
    {
        internal string strFunctionName;
        public Mapping[] mappingInput;
        public Mapping[] mappingOutput;
        internal void ReadInputMapping(StreamReader sr)
        {
            mappingInput = ParseMapping(sr);
        }

        private Mapping[] ParseMapping(StreamReader sr)
        {
            Mapping[] mapping;
            string s = sr.ReadLine();
            string[] tokens = s.Split(' ');

            int n = int.Parse(tokens[0]);
            mapping = new Mapping[n];
            for (int i = 0; i < n; i++)
            {
                mapping[i] = new Mapping();
                mapping[i].Source = tokens[2 * i + 1];
                mapping[i].Target = tokens[2 * i + 2];
            }
            return mapping;
        }

        internal void ReadOutputMapping(StreamReader sr)
        {
            mappingOutput = ParseMapping(sr);
        }
    }
}
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace DemoFunctions
{
    public class MyEngine
    {

        private Step[] program;
        private Dictionary<string, MyFunction> f = new Dictionary<string, MyFunction>();
        public MyEngine()
        {
            f.Add("Nhap", new F1());
            f.Add("Xuat", new F2());
            f.Add("Tong", new F3());
            f.Add("Max", new F4());
            f.Add("Notepad", new F5());
        }

        public void ExecuteProgram()
        {
            Dictionary<string, object> globalVariables = new Dictionary<string, object>();
            for (int i=0; i<program.Length; i++)
            {
                ExecuteStep(program[i], globalVariables);
            }
        }

        private void ExecuteStep(Step step, Dictionary<string, object> globalVariables)
        {
            MyFunction func = GetFunctionByName(step.strFunctionName);
            ExtraData input = PrepareInputIntent(globalVariables, step.mappingInput);
            ExtraData output = func.Execute(input);
            ProcessOutputIntent(globalVariables, output, step.mappingOutput);
        }

        private void ProcessOutputIntent(Dictionary<string, object> globalVariables, ExtraData output, Mapping[] mappingOutput)
        {
            // intent ==> global
            string strSource, strTarget;
            for (int i = 0; i < mappingOutput.Length; i++)
            {
                strSource = mappingOutput[i].Source;
                strTarget = mappingOutput[i].Target;
                if (globalVariables.ContainsKey(strTarget))
                    globalVariables[strTarget] = output.GetExtra(strSource);
                else
                    globalVariables.Add(strTarget, output.GetExtra(strSource));
                // Broker
            }

        }

        private ExtraData PrepareInputIntent(Dictionary<string, object> globalVariables, Mapping[] mappingInput)
        {
            // copy from global --> intent
            ExtraData input = new ExtraData();
            string strSource, strTarget;
            for (int i=0; i< mappingInput.Length; i++)
            {
                strSource = mappingInput[i].Source;
                strTarget = mappingInput[i].Target;
                input.SetExtra(strTarget, globalVariables[strSource]);
                // Broker
            }
            return input;
        }

        private MyFunction GetFunctionByName(string strFunctionName)
        {
            return f[strFunctionName];
        }

        public bool ReadScript(string strFilename)
        {
            try
            {
                StreamReader sr = new StreamReader(strFilename);
                int nSteps = int.Parse(sr.ReadLine());
                program = new Step[nSteps];
                for (int i = 0; i < nSteps; i++)
                {
                    program[i] = ReadStep(sr);
                }
                sr.Close();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
                return false;
            }
        }

        private Step ReadStep(StreamReader sr)
        {
            Step res = new Step();
            res.strFunctionName = sr.ReadLine();
            res.ReadInputMapping(sr);
            res.ReadOutputMapping(sr);
            return res;
        }
    }
}
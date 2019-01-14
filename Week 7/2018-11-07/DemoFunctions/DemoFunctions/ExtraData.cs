using System.Collections.Generic;

namespace DemoFunctions
{
    public class ExtraData
    {
        public Dictionary<string, object> variables = new Dictionary<string, object>();
        public object GetExtra(string strVariableName)
        {
            if (variables.ContainsKey(strVariableName))
                return variables[strVariableName];
            return null;
        }

        public object SetExtra(string strVariableName, object newValue)
        {
            object oldValue;
            if (variables.ContainsKey(strVariableName))
            {
                oldValue = variables[strVariableName];
                variables[strVariableName] = newValue;
                return oldValue;
            }
            else
            {
                variables.Add(strVariableName, newValue);
                return newValue;
            }
        }

    }
}
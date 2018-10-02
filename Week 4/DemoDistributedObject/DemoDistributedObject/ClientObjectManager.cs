using System;
using System.Collections.Generic;
using System.Text;

namespace DemoDistributedObject
{
    public class ClientObjectManager
    {
        internal static string GetObjectAttribute(int handle, string strAttributeName)
        {
            return ServerObjectManager.GetObjectAttribute(handle, strAttributeName);
        }

        internal static int CreateObject(string strClassName)
        {
            return ServerObjectManager.CreateObject(strClassName);
        }

        internal static bool SetObjectAttribute(int handle, string strAttributeName, string newValue)
        {
           return ServerObjectManager.SetObjectAttribute(handle, strAttributeName, newValue);
        }
    }
}
using System;
using System.Collections.Generic;

namespace DemoDistributedObject
{
    internal class ServerObjectManager
    {
        private static int NextAvailableHandle=1;
        private static Dictionary<int, SObject> objects 
            = new Dictionary<int, SObject>();

        internal static int RegisterObject(SObject sObject)
        {
            sObject.Handle = NextAvailableHandle++;
            objects.Add(sObject.Handle, sObject);
            return sObject.Handle;
        }

        internal static int CreateObject(string strClassName)
        {
            SObject obj = null;
            switch (strClassName)
            {
                case "SStudent":
                    obj = new SStudent();
                    break;
                case "SCourse":
                    obj = new SCourse();
                    break;
            }
            return obj.Handle;
        }

        private static SObject FindObjectByHandle(int handle)
        {
            if (objects.ContainsKey(handle))
                return objects[handle];
            return null;
        }

        public static bool SetObjectAttribute(int handle, 
            string sAttributeName, 
            string newValue)
        {
            try
            {
                SObject obj = FindObjectByHandle(handle);
                return obj.SetAttribute(sAttributeName, newValue);
            }
            catch(Exception)
            {
                return false;
            }
            return true;
        }


        public static string GetObjectAttribute(int handle,
            string sAttributeName)
        {
            try
            {
                SObject obj = FindObjectByHandle(handle);
                return obj.GetAttribute(sAttributeName);
            }
            catch (Exception)
            {
                return "";
            }
            return "";
        }




    }
}
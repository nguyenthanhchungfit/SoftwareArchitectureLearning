using System;
using System.Collections.Generic;
using System.Text;

namespace DemoDistributedObject
{
    public abstract class SObject
    {
        protected int handle;

        public int Handle
        {
            get
            {
                return handle;
            }

            set
            {
                handle = value;
            }
        }

        public SObject()
        {
            Handle = ServerObjectManager.RegisterObject(this);
        }
        /// <summary>
        /// 
        /// </summary>
        /// <param name="sAttributeName"></param>
        /// <param name="newValue"></param>
        /// <returns></returns>
        public virtual bool SetAttribute(string sAttributeName, string newValue)
        {
            return true; 
        }
        /// <summary>
        /// 
        /// </summary>
        /// <param name="sAttributeName"></param>
        /// <returns></returns>
        public virtual string GetAttribute(string sAttributeName)
        {
            return "";
        }
    }
}
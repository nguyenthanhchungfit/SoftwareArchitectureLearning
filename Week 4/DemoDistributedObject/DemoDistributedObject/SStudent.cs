using System;
using System.Collections.Generic;
using System.Text;

namespace DemoDistributedObject
{
    public class SStudent : SObject
    {
        protected string StudentID;
        protected string Name;
        protected float GPA;
        public override string GetAttribute(string sAttributeName)
        {
            switch (sAttributeName)
            {
                case "StudentID":
                case "studentid":
                case "student id":
                    return StudentID;
                case "Name":
                    return Name;
                case "GPA":
                case "DTB":
                    return GPA.ToString();
            }
            return base.GetAttribute(sAttributeName);
        }
        public override bool SetAttribute(string sAttributeName, string newValue)
        {
            switch (sAttributeName)
            {
                case "StudentID":
                case "studentid":
                case "student id":
                     StudentID = newValue;
                    return true;
                case "Name":
                    Name = newValue;
                    return true;
                case "GPA":
                case "DTB":
                    float.TryParse(newValue, out GPA);
                    return true;
            }

            return base.SetAttribute(sAttributeName, newValue);
        }
    }
}
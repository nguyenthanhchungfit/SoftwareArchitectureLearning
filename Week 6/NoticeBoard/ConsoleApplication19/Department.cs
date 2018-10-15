using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication19
{
    public class Department
    {
        public string Name;
        private string v;

        public Department(string name)
        {
            Name = name;
        }

        internal void PostNews(NoticeBoard noticeboard, string content)
        {

            News news = new News(content);
            noticeboard.PostNews(this, news);

        }
    }
}
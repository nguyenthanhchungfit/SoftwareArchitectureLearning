using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication19
{
    public class Student
    {
        public string Name;
        public Dictionary<string, string> Keywords = new Dictionary<string, string>();

        public Student(string name, string[] keywords)
        {
            Name = name;
            List2Dictionary(keywords);
        }

        private void List2Dictionary(string[] keywords)
        {
            for (int i = 0; i < keywords.Length; i++)
                Keywords.Add(keywords[i], keywords[i]);

        }

        internal void Subscribe(NoticeBoard noticeBoard)
        {
            noticeBoard.Subscribe(this);
        }

        internal void Notify(News news)
        {
            Console.WriteLine("Student " + Name + " reads " + news.Content);
        }
    }
}
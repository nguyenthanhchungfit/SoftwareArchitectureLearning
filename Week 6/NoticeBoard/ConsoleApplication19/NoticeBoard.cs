using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication19
{
    public class NoticeBoard
    {
        public Dictionary<int, object> newsfeed = new Dictionary<int, object>();
        public Dictionary<int, object> subscribers = new Dictionary<int, object>();

        private int nextSubscriberHandle = 1;
        private int nextNewsHandle = 1;

        public int Subscribe(Student student)
        {
            subscribers.Add(nextSubscriberHandle, student);
            return nextSubscriberHandle++;
        }

        public int PostNews(Department department, News news)
        {
            newsfeed.Add(nextNewsHandle, news);
            foreach (int handle in subscribers.Keys)
                if (IsCompatible(news, (Student)subscribers[handle]))
                    ((Student)subscribers[handle]).Notify(news);
            return nextNewsHandle++;
        }

        ContentCompatibilityChecker checker = new KeywordMatcher();//DummyChecker();

        private bool IsCompatible(News news, Student student)
        {
            return checker.Check(news, student);
        }
    }
}
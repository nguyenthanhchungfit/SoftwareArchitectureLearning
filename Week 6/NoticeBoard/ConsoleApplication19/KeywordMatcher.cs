namespace ConsoleApplication19
{
    internal class KeywordMatcher : ContentCompatibilityChecker
    {
        internal override bool Check(News news, Student student)
        {
            string[] tokens = news.Content.Split(' ');
            for (int i = 0; i < tokens.Length; i++)
                if (student.Keywords.ContainsKey(tokens[i]))
                    return true;
            return false;
        }
    }
}
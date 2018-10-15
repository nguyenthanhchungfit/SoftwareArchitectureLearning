using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApplication19
{
    class Program
    {
        static void Main(string[] args)
        {
            Student sv1 = new Student("Nguyen Van A", new string[] { "scholarship", "tuition", "course" });
            Student sv2 = new Student("Nguyen Van B", new string[] { "game", "mobile", "web", "blockchain" });

            NoticeBoard noticeBoard = new NoticeBoard();
            sv1.Subscribe(noticeBoard);
            sv2.Subscribe(noticeBoard);


            Department KEDept = new Department("Knowledge Engineering");
            Department ISDept = new Department("Information System");
            Department CSDept = new Department("Computer Science");
            Department SEDept = new Department("Software Engineering");
            Department NTDept = new Department("Network & Telecommunication");
            Department RVDept = new Department("Robot Vision...");

            SEDept.PostNews(noticeBoard, "internship for mobile and game courses");
        }
    }
}


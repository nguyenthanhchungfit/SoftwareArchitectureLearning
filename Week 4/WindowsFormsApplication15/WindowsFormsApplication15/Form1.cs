using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication15
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            MyServiceReference.ServiceSoapClient s = new MyServiceReference.ServiceSoapClient();
            int x = s.TinhTong(5, 100);
            MessageBox.Show(x.ToString());
        }
    }
}

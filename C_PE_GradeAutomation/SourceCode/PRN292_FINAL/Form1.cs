using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using ClosedXML.Excel;
using Microsoft.WindowsAPICodePack.Dialogs;


namespace PRN292_FINAL
{
    public partial class Form1 : Form
    {
        private Process process = new Process();
        private bool outputNotMatch = true;
        private bool runtimeOut = false;
        private bool emptySolution = true;
        private string noteMessage = "";
        private double Qmark = 0;
        private double TotalMark = 0;
        private double paperMark = 0;


        public Form1()
        {
            InitializeComponent();
        }
        private void Form1_Load(object sender, EventArgs e)
        {
            dgvScoreBoard.Columns.Add("stuId", "StuId");
            dgvScoreBoard.Columns.Add("stuName", "StuName");
            dgvScoreBoard.Columns.Add("examCode", "Paper No");
            dgvScoreBoard.Columns.Add("stuMark", "Mark");
            dgvScoreBoard.Columns.Add("paperMark", "PaperMark");
            dgvScoreBoard.Columns.Add("reasons", "Note");

        }
        private double getPaperMark(string tcPath, string papperNo, string QuestionNo)
        {
            paperMark = 0;
            var testCaseFolder = new DirectoryInfo(tcPath.ToString());
            foreach (var ExamCodeTestCase in testCaseFolder.GetDirectories())
            {

                if (ExamCodeTestCase.Name.Equals(papperNo))
                {
                    foreach (var QuestionFolder in ExamCodeTestCase.GetDirectories())
                    {

                        foreach (var txtTestCaseFile in QuestionFolder.GetFiles("*.txt"))
                        {
                            string testcasePath = tcPath.ToString() + "\\" + ExamCodeTestCase.Name + "\\Q" + QuestionNo + "\\" + txtTestCaseFile.Name;

                            if (File.Exists(testcasePath))
                            {

                                string[] lines = System.IO.File.ReadAllLines(testcasePath);


                                int markPos = 0;

                                for (int i = 0; i < lines.Length; i++)
                                {

                                    if (lines[i].Equals("MARK:"))
                                    {
                                        markPos = i;

                                    }
                                }


                                Qmark = Convert.ToDouble(lines[markPos + 1].ToString());
                                paperMark += Qmark;
                            }
                        }
                    }
                }

            }
            return paperMark;

        }



        private void GradeStuSolution(string path)
        {
            dgvScoreBoard.Rows.Clear();
            dgvScoreBoard.Refresh();
            int count = 0;
            process.StartInfo.CreateNoWindow = true;

            process.StartInfo.UseShellExecute = false;
            process.StartInfo.RedirectStandardOutput = true;
            process.StartInfo.RedirectStandardInput = true;


            if (txtExamFolder.Text == "".Trim() && txtTCFolder.Text == "".Trim()) { MessageBox.Show("Both paths are REQUIRED!!!", "Warning", MessageBoxButtons.OK); }
            else
            {
                var folderPath = new DirectoryInfo(path);

                //cho data từ folder vào dgvScoreBoard
                foreach (var ExamCodeFolder in folderPath.GetDirectories())
                {

                    foreach (var StudentIDFolder in ExamCodeFolder.GetDirectories())
                    {

                        dgvScoreBoard.Rows.Add();
                        string studentid = StudentIDFolder.Name;
                        string studentName = studentid.Substring(studentid.Length - 8, 8);
                        studentid = studentid.Substring(0, studentid.Length - 8);
                        dgvScoreBoard.Rows[count].Cells["stuId"].Value = studentName.ToUpper();
                        dgvScoreBoard.Rows[count].Cells["stuName"].Value = studentid;

                        foreach (var studentQFolder in StudentIDFolder.GetDirectories())
                        {
                            dgvScoreBoard.Rows[count].Cells["examCode"].Value = ExamCodeFolder.Name;
                            foreach (var SolutionFolder in studentQFolder.GetDirectories())
                            {
                                emptySolution = false;

                                process.StartInfo.WindowStyle = ProcessWindowStyle.Hidden;
                                //compile và chạy code bằng cmd
                                //string run = "a";
                                //string studentExamPath = path.ToString() + "\\" + studCodeFolder.Name + "\\" + studentsFolder.Name + "\\" + studentQFolder.Name + "\\run\\Q" + studentsFolder.Name + ".c";
                                string studentExamPath_Grading = path.ToString() + "\\" + ExamCodeFolder.Name + "\\" + StudentIDFolder.Name + "\\" + studentQFolder.Name + "\\" + SolutionFolder.Name + "\\run\\Q" + studentQFolder.Name + ".exe";
                                string studentExamPath_CheckingFolderFormat = path.ToString() + "\\" + ExamCodeFolder.Name + "\\" + StudentIDFolder.Name + "\\" + studentQFolder.Name + "\\" + SolutionFolder.Name + "\\src\\Q" + studentQFolder.Name + ".c";
                                if (!File.Exists(studentExamPath_CheckingFolderFormat))
                                {

                                    emptySolution = true;

                                }
                                if (!File.Exists(studentExamPath_Grading))
                                {

                                    emptySolution = true;


                                }
                                string compileAndRunSolutionCMD = "/C" +
                                     //+ " & gcc " + studentExamPath
                                     "  " + studentExamPath_Grading;

                                process.StartInfo.FileName = "CMD.exe";
                                process.StartInfo.Arguments = compileAndRunSolutionCMD;

                                //Get CMD input line - followed by Question folder

                                compareSolutionTestCase(txtTCFolder.Text, studentQFolder.Name, ExamCodeFolder.Name);


                                dgvScoreBoard.Rows[count].Cells["stuMark"].Value = TotalMark;


                                dgvScoreBoard.Rows[count].Cells["reasons"].Value = noteMessage;
                            }
                            dgvScoreBoard.Rows[count].Cells["paperMark"].Value = getPaperMark(txtTCFolder.Text, ExamCodeFolder.Name, studentQFolder.Name);

                        }

                        count++;
                        TotalMark = 0;
                        noteMessage = "";


                    }

                }
                MessageBox.Show("Grading Completed!", "Message", MessageBoxButtons.OK, MessageBoxIcon.Information);

            }


        }
        private void compareSolutionTestCase(string tcPath, string QuestionNo, string papperNo)
        {
            double specifiedQMark = 0;


            var testCaseFolder = new DirectoryInfo(tcPath.ToString());
            //lấy data từ file testcase *.txt và so sánh với bài làm
            foreach (var ExamCodeTestCase in testCaseFolder.GetDirectories())
            {

                if (ExamCodeTestCase.Name.Equals(papperNo))
                {
                    foreach (var QuestionFolder in ExamCodeTestCase.GetDirectories())
                    {
                        if (QuestionFolder.Name.Equals("Q" + QuestionNo) && emptySolution == false)
                        {
                            foreach (var txtTestCaseFile in QuestionFolder.GetFiles("*.txt"))
                            {
                                runtimeOut = false;
                                process.Start();
                                specifiedQMark = 0;

                                string outputTC = "";
                                string[] lines = System.IO.File.ReadAllLines(tcPath.ToString() + "\\" + ExamCodeTestCase.Name + "\\Q" + QuestionNo + "\\" + txtTestCaseFile.Name);

                                int outputPos = 0;
                                int inputPos = 0;
                                int markPos = 0;
                                StreamWriter myStreamWriter = process.StandardInput;

                                for (int i = 0; i < lines.Length; i++)
                                {
                                    if (lines[i].Equals("INPUT:"))
                                    {
                                        inputPos = i;
                                    }
                                    if (lines[i].Equals("OUTPUT:"))
                                    {
                                        outputPos = i;
                                    }
                                    if (lines[i].Equals("MARK:"))
                                    {
                                        markPos = i;

                                    }
                                }
                                for (int j = inputPos + 1; j < outputPos; j++)
                                {

                                    myStreamWriter.WriteLine(lines[j]);

                                }

                                for (int k = outputPos + 1; k < markPos; k++)
                                {
                                    if (k == markPos - 1) { outputTC += (lines[k]); }
                                    else
                                    {
                                        outputTC += (lines[k]) + "\n";

                                    }

                                }

                                Qmark = Convert.ToDouble(lines[markPos + 1].ToString());
                                //Get CMD output line
                                try
                                {
                                    string result = process.StandardOutput.ReadToEnd();


                                    //int wordLength = result.Length;
                                    File.WriteAllText(tcPath.ToString() + "\\Result.txt", result);
                                    string[] resultLines = File.ReadAllLines(tcPath.ToString() + "\\Result.txt");
                                    string trimmedResult = "";
                                    for (int rLine = 0; rLine < resultLines.Length; rLine++)
                                    {
                                        if (rLine == resultLines.Length - 1) { trimmedResult += (resultLines[rLine]); }
                                        else
                                        {
                                            trimmedResult += resultLines[rLine] + "\n";
                                        }
                                    }

                                    //so sánh output - result(TC)
                                    if (trimmedResult.TrimEnd(' ').Equals(outputTC.TrimEnd(' ').ToString()))
                                    {
                                        TotalMark += Qmark;
                                        specifiedQMark += Qmark;
                                        outputNotMatch = false;
                                    }
                                    else if (!trimmedResult.TrimEnd(' ').Equals(outputTC.TrimEnd(' ').ToString()))
                                    {
                                        outputNotMatch = true;

                                    }



                                    process.WaitForExit();

                                }
                                catch (OutOfMemoryException ex)
                                {
                                    noteMessage += "[Q" + QuestionNo + "=0]->Pottential infinite loop. ";
                                    break;
                                }
                                if (!outputNotMatch)
                                {
                                    noteMessage += "[Q" + QuestionNo + "=" + specifiedQMark.ToString() + "] ";

                                }
                                else if (outputNotMatch)
                                {
                                    noteMessage += "[Q" + QuestionNo + "=" + specifiedQMark.ToString() + "] " + txtTestCaseFile.Name + "->Output not match. ";

                                }
                            }
                        }
                        else if (emptySolution == true)
                        {
                            noteMessage += "[Q" + QuestionNo + "=" + specifiedQMark.ToString() + "]->Wrong Question folder name ";
                            break;
                        }
                    }
                }

            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            CommonOpenFileDialog dialog = new CommonOpenFileDialog();
            dialog.IsFolderPicker = true;
            if (dialog.ShowDialog() == CommonFileDialogResult.Ok)
            {
                txtExamFolder.Text = dialog.FileName;
            }


        }

        private void cbxPath_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void btnTCBrowser_Click(object sender, EventArgs e)
        {
            CommonOpenFileDialog dialog = new CommonOpenFileDialog();
            dialog.IsFolderPicker = true;
            if (dialog.ShowDialog() == CommonFileDialogResult.Ok)
            {
                txtTCFolder.Text = dialog.FileName;
                // compareSolutionTestCase(txtTCFolder.Text);
            }
        }

        private void btnGrade_Click(object sender, EventArgs e)
        {

            GradeStuSolution(txtExamFolder.Text);

        }

        private void dgvScoreBoard_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        static SqlConnection GetConnection()
        {
            return new SqlConnection(ConfigurationManager.ConnectionStrings["PRN292"].ToString());
        }
        // select-> Disconnected
        public static DataTable GetDataNySQL(string sql)
        {
            SqlCommand cmd = new SqlCommand(sql, GetConnection());
            SqlDataAdapter da = new SqlDataAdapter();
            da.SelectCommand = cmd;
            DataSet ds = new DataSet(); //Database cache
            da.Fill(ds);
            return ds.Tables[0];
        }
        //insert, update, delete -> connected
        public static int ExecuteSQL(string sql, params SqlParameter[] sqlParameter)
        {
            SqlCommand cmd = new SqlCommand(sql, GetConnection());
            cmd.Parameters.AddRange(sqlParameter);
            cmd.Connection.Open();
            int result = cmd.ExecuteNonQuery();
            cmd.Connection.Close();
            return result;
        }
        private void button3_Click(object sender, EventArgs e)
        {
            DataTable table = new DataTable();
            using (SaveFileDialog sfd = new SaveFileDialog() { Filter = "Excel Match|*.xlsx" })
            {
                if (sfd.ShowDialog() == DialogResult.OK)
                {
                    try
                    {
                        using (XLWorkbook workbook = new XLWorkbook())
                        {
                            workbook.Worksheets.Add(GetDataNySQL("SELECT * FROM Student"));
                            workbook.SaveAs(sfd.FileName);

                        }
                        MessageBox.Show("Export complete!", "Message", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        if (System.IO.File.Exists(sfd.FileName))
                        {
                            System.Diagnostics.Process.Start(sfd.FileName);
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show(ex.Message, "Message", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }

                }
            }
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            int count = 0;
            DataTable table = new DataTable();
            foreach (DataGridViewRow row in dgvScoreBoard.Rows)
            {
                if (row.Cells[0].Value != null)
                {
                    string studentid = row.Cells[0].Value.ToString();
                    string studentName = row.Cells[1].Value.ToString();
                    int paperno = Convert.ToInt32(row.Cells[2].Value.ToString());
                    float mark = float.Parse(row.Cells[3].Value.ToString());
                    float paperMark = float.Parse(row.Cells[4].Value.ToString());
                    string note = row.Cells[5].Value.ToString();
                    //string studentName = studentid.Substring(studentid.Length - 8, 8);
                    //studentid = studentid.Substring(0, studentid.Length - 9);
                    SqlParameter[] parameters = new SqlParameter[]
            {
                    new SqlParameter ("@Studentid",studentid),
                    new SqlParameter ("@Paperno",paperno),
                    new SqlParameter ("@Mark",mark),
                    new SqlParameter ("@PaperMark",paperMark),
                    new SqlParameter ("@Note",note),
                    new SqlParameter ("@StudentName",studentName)

            };
                    string sql = "INSERT INTO [dbo].[Student]([StudentId],[Mark],[PaperMark],[PaperNo],[Note] ,[StudentName],[Date]) VALUES(@StudentId,@Mark,@PaperMark,@PaperNo,@Note,@StudentName,GETDATE())";
                    try
                    {
                        ExecuteSQL(sql, parameters);

                    }
                    catch
                    {
                        count++;
                    }
                }

            }
            if (count == 0)
            {
                MessageBox.Show("Import successful");
            }
            else
            {
                if (count < 2)
                {
                    MessageBox.Show("Import complete \n(Duplicate " + count + " exam )");
                }
                else
                {
                    MessageBox.Show("Import complete \n(Duplicate " + count + " exams )");
                }
            }


        }
    }
}

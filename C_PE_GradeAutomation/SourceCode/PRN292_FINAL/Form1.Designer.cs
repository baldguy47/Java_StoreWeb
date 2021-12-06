
namespace PRN292_FINAL
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.label1 = new System.Windows.Forms.Label();
            this.btnFileBrowser = new System.Windows.Forms.Button();
            this.dgvScoreBoard = new System.Windows.Forms.DataGridView();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.btnTCBrowser = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.btnGrade = new System.Windows.Forms.Button();
            this.txtExamFolder = new System.Windows.Forms.TextBox();
            this.txtTCFolder = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.dgvScoreBoard)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(15, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(83, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "ExamCode Path";
            // 
            // btnFileBrowser
            // 
            this.btnFileBrowser.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnFileBrowser.Location = new System.Drawing.Point(720, 10);
            this.btnFileBrowser.Name = "btnFileBrowser";
            this.btnFileBrowser.Size = new System.Drawing.Size(63, 23);
            this.btnFileBrowser.TabIndex = 2;
            this.btnFileBrowser.Text = "Browse";
            this.btnFileBrowser.UseVisualStyleBackColor = true;
            this.btnFileBrowser.Click += new System.EventHandler(this.button1_Click);
            // 
            // dgvScoreBoard
            // 
            this.dgvScoreBoard.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.dgvScoreBoard.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dgvScoreBoard.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvScoreBoard.EditMode = System.Windows.Forms.DataGridViewEditMode.EditOnF2;
            this.dgvScoreBoard.Location = new System.Drawing.Point(12, 129);
            this.dgvScoreBoard.Name = "dgvScoreBoard";
            this.dgvScoreBoard.Size = new System.Drawing.Size(784, 342);
            this.dgvScoreBoard.TabIndex = 3;
            this.dgvScoreBoard.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvScoreBoard_CellContentClick);
            // 
            // button2
            // 
            this.button2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.button2.Location = new System.Drawing.Point(603, 477);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(83, 27);
            this.button2.TabIndex = 4;
            this.button2.Text = "Import";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click_1);
            // 
            // button3
            // 
            this.button3.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.button3.Location = new System.Drawing.Point(714, 477);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(82, 27);
            this.button3.TabIndex = 5;
            this.button3.Text = "Export";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // btnTCBrowser
            // 
            this.btnTCBrowser.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnTCBrowser.Location = new System.Drawing.Point(720, 51);
            this.btnTCBrowser.Name = "btnTCBrowser";
            this.btnTCBrowser.Size = new System.Drawing.Size(63, 23);
            this.btnTCBrowser.TabIndex = 8;
            this.btnTCBrowser.Text = "Browse";
            this.btnTCBrowser.UseVisualStyleBackColor = true;
            this.btnTCBrowser.Click += new System.EventHandler(this.btnTCBrowser_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(15, 56);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(87, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "Test Case Folder";
            // 
            // btnGrade
            // 
            this.btnGrade.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnGrade.Location = new System.Drawing.Point(704, 94);
            this.btnGrade.Name = "btnGrade";
            this.btnGrade.Size = new System.Drawing.Size(92, 29);
            this.btnGrade.TabIndex = 9;
            this.btnGrade.Text = "Start Grading";
            this.btnGrade.UseVisualStyleBackColor = true;
            this.btnGrade.Click += new System.EventHandler(this.btnGrade_Click);
            // 
            // txtExamFolder
            // 
            this.txtExamFolder.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txtExamFolder.Location = new System.Drawing.Point(115, 12);
            this.txtExamFolder.Name = "txtExamFolder";
            this.txtExamFolder.Size = new System.Drawing.Size(599, 20);
            this.txtExamFolder.TabIndex = 10;
            // 
            // txtTCFolder
            // 
            this.txtTCFolder.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txtTCFolder.Location = new System.Drawing.Point(115, 53);
            this.txtTCFolder.Name = "txtTCFolder";
            this.txtTCFolder.Size = new System.Drawing.Size(599, 20);
            this.txtTCFolder.TabIndex = 11;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(813, 516);
            this.Controls.Add(this.txtTCFolder);
            this.Controls.Add(this.txtExamFolder);
            this.Controls.Add(this.btnGrade);
            this.Controls.Add(this.btnTCBrowser);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.dgvScoreBoard);
            this.Controls.Add(this.btnFileBrowser);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Auto Marking System";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgvScoreBoard)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btnFileBrowser;
        private System.Windows.Forms.DataGridView dgvScoreBoard;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Button btnTCBrowser;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button btnGrade;
        private System.Windows.Forms.TextBox txtExamFolder;
        private System.Windows.Forms.TextBox txtTCFolder;
    }
}


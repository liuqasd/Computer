import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class numComputer extends JFrame implements ActionListener{
    JPanel pan_button = new JPanel();
    JPanel pan_textfield = new JPanel();
    String num = "";
    String str1 = "";    //存储所有点击按键的结果显示于上面的文本框
    double result = 0;
    char sign = 0;
    public JTextArea ta_num = new JTextArea(2,36);
    public JTextArea ta_num1 = new JTextArea(1,36);
    public numComputer() {
        iniFrame();
        iniButton();
        iniTextField();

        Container con = getContentPane();
        con.setLayout(null);
        con.add(pan_textfield);
        con.add(pan_button);
        con.setLayout(new GridLayout(2,1));

        setVisible(true);
    }
    public void iniFrame() {
        setTitle("My Number Computer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 660);
        setLocation(500,150);
    }
    public void iniButton() {
        JButton b_dot = new JButton(".");
        JButton b_equ = new JButton("=");
        JButton b_div = new JButton("/");
        JButton b_mul = new JButton("*");
        JButton b_sub = new JButton("-");
        JButton b_plu = new JButton("+");

        JButton b_0 = new JButton("0");
        JButton b_1 = new JButton("1");
        JButton b_2 = new JButton("2");
        JButton b_3 = new JButton("3");
        JButton b_4 = new JButton("4");
        JButton b_5 = new JButton("5");
        JButton b_6 = new JButton("6");
        JButton b_7 = new JButton("7");
        JButton b_8 = new JButton("8");
        JButton b_9 = new JButton("9");

        buttonAdd(b_div, b_mul, b_4, b_5, b_6, b_7, b_8, b_9);
        buttonAdd(b_sub, b_plu, b_dot, b_0, b_equ, b_1, b_2, b_3);

        pan_button.setBackground(Color.BLACK);
        pan_button.setLayout(new GridLayout(4,4,1,1));
        pan_button.setSize(398,358);

        b_dot.addActionListener(new B_dot());
        b_equ.addActionListener(new B_equ());
        b_div.addActionListener(new B_div());
        b_mul.addActionListener(new B_mul());
        b_sub.addActionListener(new B_sub());
        b_plu.addActionListener(new B_plu());

        b_0.addActionListener(new B_0());
        b_1.addActionListener(new B_1());
        b_2.addActionListener(new B_2());
        b_3.addActionListener(new B_3());
        b_4.addActionListener(new B_4());
        b_5.addActionListener(new B_5());
        b_6.addActionListener(new B_6());
        b_7.addActionListener(new B_7());
        b_8.addActionListener(new B_8());
        b_9.addActionListener(new B_9());
    }

    private void buttonAdd(JButton b_div, JButton b_mul, JButton b_4, JButton b_5, JButton b_6, JButton b_7, JButton b_8, JButton b_9) {
        pan_button.add(b_7);
        pan_button.add(b_8);
        pan_button.add(b_9);
        pan_button.add(b_div);
        pan_button.add(b_4);
        pan_button.add(b_5);
        pan_button.add(b_6);
        pan_button.add(b_mul);
    }

    public void iniTextField() {
        pan_textfield.add(ta_num);
        pan_textfield.add(ta_num1);
        pan_textfield.setSize(398,298);

        ta_num.setFont(new java.awt.Font("宋体", Font.BOLD, 18));
        ta_num.setEnabled(false); //只读
        ta_num.setText("请输入：");//显示内容

        ta_num1.setFont(new java.awt.Font("宋体", Font.BOLD, 18));
        ta_num1.setEnabled(false); //只读
        ta_num1.setText("0");//显示内容
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 自动生成的方法存根

    }
    public class judge{
        public void jdg(char a) {
            if(a == 0)//表示没有按运算键
                if(num.isEmpty())
                    result = 0;//而且没有输入值，如直接按等于号，
                else
                    result = Double.parseDouble(num);//有输入值按等于号
            else
                //考虑sign=加减乘除的四种情况，b作为计算结果显示在下面的文本框
                switch (a) {
                    case '+':
                        result += Double.parseDouble(num);
                        break;
                    case '-':
                        result -= Double.parseDouble(num);
                        break;
                    case '*':
                        result *= Double.parseDouble(num);
                        break;
                    case '/':
                        double aa;
                        aa = Double.parseDouble(num);
                        if(aa == 0) {
                            ta_num1.setText("除数不能为0");
                            num = "";
                            str1 = "";
                            result = 0;
                            break;
                        }
                        result = result/aa;
                        break;
                }
            ta_num1.setText(String.valueOf(result));
        }
    }
    public class B_dot implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += ".";
            ta_num.setText(num);
        }
    }
    public class B_equ extends judge implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int r;
            r = str1.length();
            if(r > 0 && num.isEmpty())
            {
                char[] a = str1.toCharArray();
                if(a[r-1] < 48 || a[r-1]>57)
                {
                    ta_num.setText("0");
                    ta_num1.setText(String.valueOf(result));
                    return;
                }
            }
            this.jdg(sign);
            ta_num.setText(String.valueOf(result));
            str1 = "";
            num = String.valueOf(result);
            sign = '=';
        }//等于号之后全部归0，返回初始状态
    }
    public class B_div extends judge implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int r;
            r = str1.length();
            if(r > 0 && num.isEmpty())//str1不为空，num为空
            {
                char[] a = str1.toCharArray();
                if(a[r-1] < 48 || a[r-1] > 57)//acsii码；判断最后一个是不是运算符
                {
                    a[str1.length()-1] = 47;
                    str1 = String.valueOf(a);
                    ta_num.setText(str1);
                    sign = '/';//是的话就变成除号/
                    return;
                }
            }
            if(num.isEmpty())//下空
            {
                str1 = "0";
                ta_num.setText("0");
            }//初始状态按÷，
            str1 += num+ "÷";    //把num和运算符号加在str1后面
            ta_num.setText(str1);
            this.jdg(sign);//执行上一个运算符；
            sign = '/';
            ta_num.setText(str1);
            num = "";//这时num没用了，就置空方便下次输入
        }
    }
    public class B_mul extends judge implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int r;
            r = str1.length();
            if(r > 0 && num.isEmpty())
            {
                char[] a = str1.toCharArray();
                if(a[r-1] < 48 || a[r-1] > 57)
                {
                    a[str1.length()-1] = 42;
                    str1 = String.valueOf(a);
                    ta_num.setText(str1);
                    sign = '*';
                    return;
                }
            }
            if(num.isEmpty())//下空
            {
                str1 = "1";
                ta_num1.setText("0");
                result = 1;
            }//初始状态按*，
            str1 += num + "*";
            ta_num.setText(str1);
            this.jdg(sign);//执行上一个运算符；
            sign = '*';
            ta_num.setText(str1);
            num = "";
        }
    }
    public class B_sub extends judge implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int r;
            r = str1.length();
            if(r > 0 && num.isEmpty())
            {
                char[] a = str1.toCharArray();
                if(a[r-1] < 48 || a[r-1] > 57)
                {
                    a[str1.length()-1] = 45;
                    str1 = String.valueOf(a);
                    ta_num.setText(str1);
                    sign = '-';
                    return;
                }
            }
            if(num.isEmpty())//下空
            {
                str1 = "0";
                ta_num1.setText("0");
            }//初始状态按-，
            str1 += num + "-";
            ta_num.setText(str1);
            this.jdg(sign);//执行上一个运算符；
            sign = '-';
            ta_num.setText(str1);
            num = "";
        }
    }
    public class B_plu extends judge implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int r;
            r = str1.length();
            if(r > 0 && num.isEmpty())
            {
                char[] a = str1.toCharArray();
                if(a[r-1] < 48 || a[r-1] > 57)
                {
                    a[str1.length()-1] = 43;
                    str1 = String.valueOf(a);
                    ta_num.setText(str1);
                    sign = '+';
                    return;
                }
            }//对连续出现两个运算符号进行操作
            if(num.isEmpty())//下空
            {
                str1 = "0";
                ta_num1.setText("0");
            }//初始状态按+，
            str1 += num + "+";
            ta_num.setText(str1);
            this.jdg(sign);//执行上一个运算符；
            sign = '+';
            ta_num.setText(str1);
            num = "";
        }
    }

    public class B_0 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 0;
            ta_num.setText(str1+num);
        }
    }
    public class B_1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 1;
            ta_num.setText(str1 + num);
        }
    }
    public class B_2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 2;
            ta_num.setText(str1 + num);
        }
    }
    public class B_3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 3;
            ta_num.setText(str1 + num);
        }
    }
    public class B_4 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 4;
            ta_num.setText(str1 + num);
        }
    }
    public class B_5 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 5;
            ta_num.setText(str1 + num);
        }
    }
    public class B_6 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 6;
            ta_num.setText(str1 + num);
        }
    }
    public class B_7 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 7;
            ta_num.setText(str1 + num);
        }
    }
    public class B_8 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 8;
            ta_num.setText(str1 + num);
        }
    }
    public class B_9 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num += 9;
            ta_num.setText(str1 + num);
        }
    }

    public static void main(String[]  args)
    {
        @SuppressWarnings("unused")
        numComputer f = new numComputer();
    }
}

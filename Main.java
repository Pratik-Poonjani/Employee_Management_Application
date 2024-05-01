package Intermediate.Employee_Management_Application;

import com.toedter.calendar.JDateChooser;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Random;

// STARTING WINDOW
class Main extends JFrame {
    Main() {
        JLabel title = new JLabel("Employee Management Application");
        title.setFont(new Font("MONOSPACED", Font.BOLD, 30));
        title.setBounds(200, 40, 600, 50);
        title.setForeground(new Color(255, 255, 255));
        add(title);

        ImageIcon a1 = new ImageIcon(ClassLoader.getSystemResource("Intermediate/Employee_Management_Application/employee-icons/Dashboard.gif"));
        Image a2 = a1.getImage().getScaledInstance(1000, 550, Image.SCALE_DEFAULT);
        ImageIcon a3 = new ImageIcon(a2);
        JLabel image = new JLabel(a3);
        image.setBounds(0, 0, 1000, 550);
        add(image);

        setSize(1000, 550);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        try {
            Thread.sleep(2500);
            setVisible(false);
            new Login_Page();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

// LOGIN PAGE
class Login_Page extends JFrame implements ActionListener {

    JTextField user_name;
    JPasswordField pass_word;
    JButton login, back;

    Login_Page() {

        JLabel username = new JLabel("Username");
        username.setBounds(660, 120, 100, 30);
        username.setForeground(Color.WHITE);
        add(username);

        user_name = new JTextField();
        user_name.setBounds(660, 160, 200, 30);
        add(user_name);

        JLabel password = new JLabel("Password");
        password.setBounds(660, 210, 100, 30);
        password.setForeground(Color.WHITE);
        add(password);

        pass_word = new JPasswordField();
        pass_word.setBounds(660, 250, 200, 30);
        add(pass_word);

        login = new JButton("LOGIN");
        login.setBounds(660, 310, 200, 30);
        login.setBackground(new Color(0, 157, 152));
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        back = new JButton("BACK");
        back.setBounds(660, 360, 200, 30);
        back.setBackground(new Color(0, 157, 152));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon b1 = new ImageIcon(ClassLoader.getSystemResource("Intermediate/Employee_Management_Application/employee-icons/Login.gif"));
        Image b2 = b1.getImage().getScaledInstance(500, 450, Image.SCALE_DEFAULT);
        ImageIcon b3 = new ImageIcon(b2);
        JLabel img2 = new JLabel(b3);
        img2.setBounds(32, 32, 500, 450);
        add(img2);


        ImageIcon b4 = new ImageIcon(ClassLoader.getSystemResource("Intermediate/Employee_Management_Application/employee-icons/Login2.jpg"));
        Image b5 = b4.getImage().getScaledInstance(1000, 550, Image.SCALE_DEFAULT);
        ImageIcon b6 = new ImageIcon(b5);
        JLabel img1 = new JLabel(b6);
        img1.setBounds(0, 0, 1000, 550);
        add(img1);

        setSize(1000, 550);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            try {
                String username = user_name.getText();
                String password = pass_word.getText();

                Database_Connect connect = new Database_Connect();
                String query = "select * from login where username = '" + username + "' and password = '" + password + "'";
                ResultSet resultSet = connect.statement.executeQuery(query);
                if (resultSet.next()) {
                    setVisible(false);
                    new Home_Page();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Login_Page();
    }
}

// MAIN CLASS
class Home_Page extends JFrame {
    Home_Page() {

        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("Intermediate/Employee_Management_Application/employee-icons/Manage.jpg"));
        Image c2 = c1.getImage().getScaledInstance(1000, 550, Image.SCALE_DEFAULT);
        ImageIcon c3 = new ImageIcon(c2);
        JLabel img = new JLabel(c3);
        img.setBounds(0, 0, 1000, 550);
        add(img);

        JLabel heading = new JLabel("Employee Management Application");
        heading.setBounds(50, 70, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        img.add(heading);

        JButton add = new JButton("ADD Employee");
        add.setBounds(125, 185, 150, 40);
        add.setForeground(Color.WHITE);
        add.setBackground(new Color(0, 157, 152));
        add.addActionListener(e -> {
            new Add_Employee();
            setVisible(false);
        });
        img.add(add);

        JButton view = new JButton("VIEW Employee");
        view.setBounds(125, 255, 150, 40);
        view.setForeground(Color.WHITE);
        view.setBackground(new Color(0, 157, 152));
        view.addActionListener(e -> {
            new View_Employee();
            setVisible(false);
        });
        img.add(view);

        JButton remove = new JButton("REMOVE Employee");
        remove.setBounds(125, 325, 150, 40);
        remove.setForeground(Color.WHITE);
        remove.setBackground(new Color(0, 157, 152));
        remove.addActionListener(e -> new Remove_Employee());
        img.add(remove);

        JButton back = new JButton("BACK");
        back.setBounds(125, 395, 150, 40);
        setForeground(Color.WHITE);
        back.setBackground(new Color(0, 157, 152));
        back.setForeground(Color.WHITE);
        back.addActionListener(e -> new Login_Page());
        img.add(back);

        setSize(1000, 550);

        setLocationRelativeTo(null);

        setLayout(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Home_Page();
    }
}

// DATABASE CONNECTION
class Database_Connect {
    Connection connection;
    Statement statement;

    public Database_Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_table", "root", "1234");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ADD EMPLOYEE
class Add_Employee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);
    JTextField tname, tfname, taddress, tphone, taadhar, temail, tsalary, tdesignation;
    JLabel tempid;
    JDateChooser tdob;
    JButton add, back;
    JComboBox Boxeducation;

    Add_Employee() {

        getContentPane().setBackground(new Color(0, 157, 152));

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(375, 30, 500, 50);
        heading.setFont(new Font("Plain", Font.BOLD, 25));
        heading.setForeground(Color.white);
        add(heading);

        //  ****** NAME ******
        JLabel name = new JLabel("Name");
        name.setBounds(100, 150, 150, 30);
        name.setFont(new Font("Courier", Font.PLAIN, 20));
        add(name);
        tname = new JTextField();
        tname.setBounds(250, 150, 150, 30);
        add(tname);

        //  ****** FATHER NAME ******
        JLabel fname = new JLabel("Father's Name");
        fname.setBounds(550, 150, 150, 30);
        fname.setFont(new Font("Courier", Font.PLAIN, 20));
        add(fname);
        tfname = new JTextField();
        tfname.setBounds(750, 150, 150, 30);
        add(tfname);

        //  ****** DOB ******
        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(100, 200, 150, 30);
        dob.setFont(new Font("Courier", Font.PLAIN, 20));
        add(dob);
        tdob = new JDateChooser();
        tdob.setBounds(250, 200, 150, 30);
        add(tdob);

        //  ****** AADHAR No. ******
        JLabel aadhar = new JLabel("Aadhar Number");
        aadhar.setBounds(550, 200, 150, 30);
        aadhar.setFont(new Font("Courier", Font.PLAIN, 20));
        add(aadhar);
        taadhar = new JTextField();
        taadhar.setBounds(750, 200, 150, 30);
        add(taadhar);

        //  ****** EMAIL ******
        JLabel email = new JLabel("Email");
        email.setBounds(100, 250, 150, 30);
        email.setFont(new Font("Courier", Font.PLAIN, 20));
        add(email);
        temail = new JTextField();
        temail.setBounds(250, 250, 150, 30);
        add(temail);

        //  ****** PHONE No. ******
        JLabel phone = new JLabel("Phone");
        phone.setBounds(550, 250, 150, 30);
        phone.setFont(new Font("Courier", Font.PLAIN, 20));
        add(phone);
        tphone = new JTextField();
        tphone.setBounds(750, 250, 150, 30);
        add(tphone);

        //  ****** ADDRESS ******
        JLabel address = new JLabel("Address");
        address.setBounds(100, 300, 150, 30);
        address.setFont(new Font("Courier", Font.PLAIN, 20));
        add(address);
        taddress = new JTextField();
        taddress.setBounds(250, 300, 150, 30);
        add(taddress);

        //  ****** EDUCATION ******
        JLabel education = new JLabel("Higest Education");
        education.setBounds(550, 300, 150, 30);
        education.setFont(new Font("Courier", Font.PLAIN, 20));
        add(education);
        String[] items = {"BBA", "B.Tech", "BCA", "BA", "BSC", "B.COM", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        Boxeducation = new JComboBox(items);
        Boxeducation.setBounds(750, 300, 150, 30);
        add(Boxeducation);

        //  ****** Designation ******
        JLabel designation = new JLabel("Designation");
        designation.setBounds(100, 350, 150, 30);
        designation.setFont(new Font("Courier", Font.PLAIN, 20));
        add(designation);
        tdesignation = new JTextField();
        tdesignation.setBounds(250, 350, 150, 30);
        add(tdesignation);

        //  ****** SALARY ******
        JLabel salary = new JLabel("Salary");
        salary.setBounds(550, 350, 150, 30);
        salary.setFont(new Font("Courier", Font.PLAIN, 20));
        add(salary);
        tsalary = new JTextField();
        tsalary.setBounds(750, 350, 150, 30);
        add(tsalary);


        //  ****** Employee ID ******
        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(100, 400, 150, 30);
        empid.setFont(new Font("Courier", Font.PLAIN, 20));
        add(empid);
        tempid = new JLabel("" + number);
        tempid.setBounds(250, 400, 150, 30);
        tempid.setFont(new Font("Courier", Font.PLAIN, 20));
        tempid.setForeground(Color.yellow);
        add(tempid);

        //  ****** ADD Button ******
        add = new JButton("ADD");
        add.setBounds(325, 450, 150, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        //  ****** BACK Button ******
        back = new JButton("BACK");
        back.setBounds(525, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // Main Screen
        setSize(1000, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = tname.getText();
            String fname = tfname.getText();
            String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
            String aadhar = taadhar.getText();
            String email = temail.getText();
            String phone = tphone.getText();
            String address = taddress.getText();
            String education = (String) Boxeducation.getSelectedItem();
            String designation = tdesignation.getText();
            String salary = tsalary.getText();
            String empID = tempid.getText();

            try {
                Database_Connect connect1 = new Database_Connect();
                String query = "insert into employeedata values('" + name + "', '" + fname + "', '" + dob + "', '" + aadhar + "' ,'" + email + "', '" + phone + "', '" + address + "', '" + education + "', '" + designation + "','" + salary + "', '" + empID + "')";
                connect1.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Home_Page();

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home_Page();
        }
    }

    public static void main(String[] args) {
        new Add_Employee();
    }
}

// VIEW EMPLOYEE
class View_Employee extends JFrame implements ActionListener {

    JTable table;
    Choice choiceEMP;
    JButton searchbtn, print, update, back;

    View_Employee() {

        getContentPane().setBackground(new Color(163, 255, 188));
        JLabel search = new JLabel("Search by employee ID ");
        search.setFont(new Font("SOLID", Font.PLAIN, 14));
        search.setBounds(600, 20, 150, 20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(770, 20, 200, 30);
        add(choiceEMP);

        try {
            Database_Connect connect2 = new Database_Connect();
            ResultSet resultSet = connect2.statement.executeQuery("select * from employeedata");
            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            Database_Connect connect3 = new Database_Connect();
            ResultSet resultSet = connect3.statement.executeQuery("select * from employeedata");
            table.setModel(DbUtils.resultSetToTableModel(resultSet)); // rs2xml.jar file needed to be solving error
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 90, 1000, 550);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(600, 60, 80, 20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(700, 60, 80, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(800, 60, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(900, 60, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(1000, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //  Search Button Work Condition
        if (e.getSource() == searchbtn) {
            String query = "select * from employeedata where empId = '" + choiceEMP.getSelectedItem() + "'";
            try {
                Database_Connect connect4 = new Database_Connect();
                ResultSet resultSet = connect4.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        // Print Button Work Condition
        else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        // Print Button Update Condition
        else if (e.getSource() == update) {
            setVisible(false);
            new Update_Employee(choiceEMP.getSelectedItem());
        }
        // Print Button Remove Condition
        else {
            setVisible(false);
            new Home_Page();
        }
    }

    public static void main(String[] args) {
        new View_Employee();
    }
}

// UPDATE EMPLOYEE
class Update_Employee extends JFrame implements ActionListener {
    JTextField teducation;
    JTextField taddress;
    JTextField tphone;
    JTextField temail;
    JTextField tsalary;
    JTextField tdesignation;
    JLabel tempid;
    JButton add, back;
    String number;

    Update_Employee(String number) {

        this.number = number;
        getContentPane().setBackground(new Color(75, 225, 128));

        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(355, 30, 500, 50);
        heading.setFont(new Font("Plain", Font.BOLD, 25));
        heading.setForeground(Color.white);
        add(heading);

        //  ****** NAME ******
        JLabel name = new JLabel("Name");
        name.setBounds(100, 150, 150, 30);
        name.setFont(new Font("Courier", Font.PLAIN, 20));
        add(name);
        JLabel tname = new JLabel();
        tname.setBounds(250, 150, 150, 30);
        tname.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(tname);

        //  ****** FATHER NAME ******
        JLabel fname = new JLabel("Father's Name");
        fname.setBounds(550, 150, 150, 30);
        fname.setFont(new Font("Courier", Font.PLAIN, 20));
        add(fname);
        JLabel tfname = new JLabel();
        tfname.setBounds(750, 150, 150, 30);
        tfname.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(tfname);

        //  ****** DOB ******
        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(100, 200, 150, 30);
        dob.setFont(new Font("Courier", Font.PLAIN, 20));
        add(dob);
        JLabel tdob = new JLabel();
        tdob.setBounds(250, 200, 150, 30);
        tdob.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(tdob);

        //  ****** AADHAR No. ******
        JLabel aadhar = new JLabel("Aadhar Number");
        aadhar.setBounds(550, 200, 150, 30);
        aadhar.setFont(new Font("Courier", Font.PLAIN, 20));
        add(aadhar);
        JLabel taadhar = new JLabel();
        taadhar.setBounds(750, 200, 150, 30);
        taadhar.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(taadhar);

        //  ****** EMAIL ******
        JLabel email = new JLabel("Email");
        email.setBounds(100, 250, 150, 30);
        email.setFont(new Font("Courier", Font.PLAIN, 20));
        add(email);
        temail = new JTextField();
        temail.setBounds(250, 250, 150, 30);
        add(temail);

        //  ****** PHONE No. ******
        JLabel phone = new JLabel("Phone");
        phone.setBounds(550, 250, 150, 30);
        phone.setFont(new Font("Courier", Font.PLAIN, 20));
        add(phone);
        tphone = new JTextField();
        tphone.setBounds(750, 250, 150, 30);
        add(tphone);

        //  ****** ADDRESS ******
        JLabel address = new JLabel("Address");
        address.setBounds(100, 300, 150, 30);
        address.setFont(new Font("Courier", Font.PLAIN, 20));
        add(address);
        taddress = new JTextField();
        taddress.setBounds(250, 300, 150, 30);
        add(taddress);

        //  ****** EDUCATION ******
        JLabel education = new JLabel("Higest Education");
        education.setBounds(550, 300, 150, 30);
        education.setFont(new Font("Courier", Font.PLAIN, 20));
        add(education);
        teducation = new JTextField();
        teducation.setBounds(750, 300, 150, 30);
        add(teducation);

        //  ****** Designation ******
        JLabel designation = new JLabel("Designation");
        designation.setBounds(100, 350, 150, 30);
        designation.setFont(new Font("Courier", Font.PLAIN, 20));
        add(designation);
        tdesignation = new JTextField();
        tdesignation.setBounds(250, 350, 150, 30);
        add(tdesignation);

        //  ****** SALARY ******
        JLabel salary = new JLabel("Salary");
        salary.setBounds(550, 350, 150, 30);
        salary.setFont(new Font("Courier", Font.PLAIN, 20));
        add(salary);
        tsalary = new JTextField();
        tsalary.setBounds(750, 350, 150, 30);
        add(tsalary);

        //  ****** Employee ID ******
        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(100, 400, 150, 30);
        empid.setFont(new Font("Courier", Font.PLAIN, 20));
        add(empid);
        tempid = new JLabel();
        tempid.setBounds(250, 400, 150, 30);
        tempid.setFont(new Font("Courier", Font.PLAIN, 20));
        tempid.setForeground(Color.RED);
        add(tempid);

        try {
            Database_Connect connect7 = new Database_Connect();
            String query = "select * from employeedata where empId = '" + number + "'";
            ResultSet resultSet = connect7.statement.executeQuery(query);
            while (resultSet.next()) {
                tname.setText(resultSet.getString("name"));
                tfname.setText(resultSet.getString("fname"));
                tdob.setText(resultSet.getString("dob"));
                taadhar.setText(resultSet.getString("aadhar"));
                temail.setText(resultSet.getString("email"));
                tphone.setText(resultSet.getString("phone"));
                teducation.setText(resultSet.getString("education"));
                taddress.setText(resultSet.getString("address"));
                tempid.setText(resultSet.getString("empID"));
                tdesignation.setText(resultSet.getString("designation"));
                tsalary.setText(resultSet.getString("salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  ****** UPDATE Button ******
        add = new JButton("UPDATE");
        add.setBounds(325, 450, 150, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        //  ****** BACK Button ******
        back = new JButton("BACK");
        back.setBounds(525, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // Main Screen
        setSize(1000, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String salary = tsalary.getText();
            String address = taddress.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = teducation.getText();
            String designation = tdesignation.getText();

            try {
                Database_Connect connect6 = new Database_Connect();
                String query = "update employeedata set salary = '" + salary + "', address = '" + address + "', phone = '" + phone + "', email = '" + email + "', education = '" + education + "', designation = '" + designation + "' where empId = '" + number + "'";
                connect6.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new Home_Page();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
            new View_Employee();
        }
    }

    public static void main(String[] args) {
        new Update_Employee("");
    }
}

// REMOVE EMPLOYEE
class Remove_Employee extends JFrame implements ActionListener {
    Choice choiceEMPID;
    JButton delete, back;

    Remove_Employee() {


        JLabel label = new JLabel("Employee ID");
        label.setBounds(80, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(220, 50, 150, 30);
        add(choiceEMPID);

        try {
            Database_Connect connect8 = new Database_Connect();
            ResultSet resultSet = connect8.statement.executeQuery("select * from employeedata");
            while (resultSet.next()) {
                choiceEMPID.add(resultSet.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JLabel labelName = new JLabel("Name");
        labelName.setBounds(80, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(220, 100, 100, 30);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(80, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelPhone);

        JLabel textPhone = new JLabel();
        textPhone.setBounds(220, 150, 100, 30);
        add(textPhone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(80, 200, 100, 30);
        labelemail.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelemail);

        JLabel textEmail = new JLabel();
        textEmail.setBounds(220, 200, 100, 30);
        add(textEmail);

        try {
            Database_Connect connect9 = new Database_Connect();
            ResultSet resultSet = connect9.statement.executeQuery("select * from employeedata where empId = '" + choiceEMPID.getSelectedItem() + "'");
            while (resultSet.next()) {
                textName.setText(resultSet.getString("name"));
                textPhone.setText(resultSet.getString("phone"));
                textEmail.setText(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(e -> {
            try {
                Database_Connect connect11 = new Database_Connect();
                ResultSet resultSet = connect11.statement.executeQuery("select * from employeedata where empId = '" + choiceEMPID.getSelectedItem() + "'");
                while (resultSet.next()) {
                    textName.setText(resultSet.getString("name"));
                    textPhone.setText(resultSet.getString("phone"));
                    textEmail.setText(resultSet.getString("email"));
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 275, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 275, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Intermediate/Employee_Management_Application/employee-icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(600, 80, 200, 200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("Intermediate/Employee_Management_Application/employee-icons/Wall.jpg"));
        Image i22 = i11.getImage().getScaledInstance(1000, 550, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel image = new JLabel(i33);
        image.setBounds(0, 0, 1000, 550);
        add(image);

        setSize(1000, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                Database_Connect connect12 = new Database_Connect();
                String query = "delete from employeedata where empId = '" + choiceEMPID.getSelectedItem() + "'";
                connect12.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
                setVisible(false);
                new Home_Page();

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Remove_Employee();
    }
}
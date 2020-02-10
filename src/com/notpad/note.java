package com.notpad;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class note extends JFrame implements ActionListener {
    JMenuBar jmb;//菜单条组件
    JMenu menu1, menu2, menu3, menu4, menu5;//主菜单：文件、编辑、格式、查看、帮助
    JMenuItem item1, item2, item3, item4, item5, item6;//子菜单：新建、打开、保存、另存为、页面设置、打印、退出
    JMenu xinjian;//二级菜单
    JMenuItem file, project;//二级菜单
    JTextArea jta;

    public static void main(String[] args) {
        note txt = new note();
    }

    public note() {
        //新建菜单条
        jmb = new JMenuBar();
        //新建菜单分支
        menu1 = new JMenu("文件(F)");
        menu1.setMnemonic('F');
        menu2 = new JMenu("编辑(E)");
        menu2.setMnemonic('E');
        menu3 = new JMenu("格式(O)");
        menu3.setMnemonic('O');
        menu4 = new JMenu("查看(V)");
        menu4.setMnemonic('V');
        menu5 = new JMenu("帮助(H)");
        menu5.setMnemonic('H');
        item1 = new JMenuItem("打开(O)");
        item1.setMnemonic('O');
        //设置ctrl快捷组合键
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_DOWN_MASK));
        //open打开注册监听
        item1.addActionListener(this);
        item1.setActionCommand("open");
        item2 = new JMenuItem("保存(S)");
        item2.setMnemonic('S');
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
        //save保存注册监听
        item2.addActionListener(this);
        item2.setActionCommand("save");
        item3 = new JMenuItem("另存为(A)");
        item3.setMnemonic('A');
        item4 = new JMenuItem("页面设置(U)");
        item4.setMnemonic('U');
        item5 = new JMenuItem("打印(P)");
        item5.setMnemonic('P');
        item6 = new JMenuItem("退出(X)");
        item6.setMnemonic('X');
        //extit退出的注册监听
        item6.addActionListener(this);
        item6.setActionCommand("exit");

        //文件--新建--子目录
        xinjian = new JMenu("新建");
        file = new JMenuItem("文件");
        project = new JMenuItem("工程");


        //输入界面
        jta = new JTextArea();

        //将菜单项添加到菜单上
        xinjian.add(file);
        xinjian.add(project);

        menu1.add(xinjian);
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.addSeparator();//添加分割线
        menu1.add(item4);
        menu1.add(item5);
        menu1.addSeparator();
        menu1.add(item6);

        this.setJMenuBar(jmb);
        //将菜单添加到菜单条上
        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);
        jmb.add(menu4);
        jmb.add(menu5);

        JScrollPane jsp = new JScrollPane(jta);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(jsp);


        this.setTitle("Java记事本");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("open")) {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("打开");
            jfc.showOpenDialog(null);
            jfc.setVisible(true);
            String filename = jfc.getSelectedFile().getAbsolutePath();
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader(filename);
                br = new BufferedReader(fr);
                String s = "";
                String sText = "";
                while ((s = br.readLine()) != null) {
                    sText += s + "\r\n";
                }
                jta.setText(sText);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else if (actionEvent.getActionCommand().equals("save")) {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("保存");
            jfc.showSaveDialog(null);
            jfc.setVisible(true);
            String filename = jfc.getSelectedFile().getAbsolutePath();
            FileWriter fw = null;
            BufferedWriter bw = null;
            try {
                fw = new FileWriter(filename);
                bw = new BufferedWriter(fw);
                bw.write(this.jta.getText());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else if (actionEvent.getActionCommand().equals("exit")) {
            System.exit(0);

        }
    }
}

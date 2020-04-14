package com.twu.biblioteca2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CustomizedDialog
{
    public static Map<String, String> showCustomDialog(final Frame owner, Component parentComponent)
    {
        final Map<String, String> user = new HashMap<String, String>();

        // 创建一个模态对话框
        final JDialog dialog = new JDialog(owner, "Login", true);
        // 设置对话框的宽高
        dialog.setSize(300, 150);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);

        JLabel number = new JLabel("Library Number:");
        JLabel password = new JLabel("Password:");

        final JTextField numberInput = new JTextField(8);
        final JTextField passwordInput = new JTextField(8);

        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String number = numberInput.getText();
                String password = passwordInput.getText();

                user.put("libraryNumber", number);
                user.put("password", password);
                dialog.dispose();
            }
        });

        JButton cancelBtn = new JButton("Exit");
        cancelBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
                //dialog.dispose();
            }
        });

        JPanel panel = new JPanel();             // for input area
        JPanel panel2 = new JPanel();            // for button area
        //JPanel panel3 = new JPanel();            // for error message area
        GridLayout layout = new GridLayout(2, 2);
        panel.setLayout(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //panel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        panel.add(number);
        panel.add(numberInput);
        panel.add(password);
        panel.add(passwordInput);

        panel2.add(okBtn);
        panel2.add(cancelBtn);

        //panel3.add(new JLabel("Library number or password is wrong"));

        dialog.getContentPane().add(BorderLayout.NORTH, panel);
        dialog.getContentPane().add(BorderLayout.SOUTH, panel2);
        //dialog.getContentPane().add(BorderLayout, panel3);
        dialog.setVisible(true);

        return user;
    }
}

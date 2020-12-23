/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import static java.lang.System.out;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import manage.tree.RedBlackTree;


/**
 *
 * @author hp
 */

public class TreeFrame extends JFrame {
  
    JButton insert_button,delete_button,find_button,clear_button,print_button;
    JTextField insert_text,delete_text,find_text;
    JPanel p1,p2;
    
     


    public TreeFrame() throws PrinterException
    {
        this.setTitle("RedBlackTree");
        this.setVisible(true);
        this.setSize(5000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        p1=new JPanel();
        p2=new JPanel();
        p1.setBackground(Color.LIGHT_GRAY);
        p2.setBackground(Color.GRAY);
        this.setLayout(null);
        p1.setBounds(0, 0,5000, 100);
        p2.setBounds(0, 0,5000,700);
        add(p1);add(p2);
        
         insert_button=new JButton("Insert");
         delete_button=new JButton("Delete");
         find_button=new JButton("Find");
         clear_button=new JButton("Clear");
         print_button=new JButton("Print");
         
         insert_text=new JTextField();
         delete_text=new JTextField();
         find_text=new JTextField();
         
         p1.setLayout(null);
         
         insert_text.setBounds(10,20, 90, 25);
         insert_button.setBounds(105,20, 70, 25);
         delete_text.setBounds(180,20, 90, 25);
         delete_button.setBounds(275,20,70, 25);
         find_text.setBounds(350,20,90, 25);
         find_button.setBounds(445,20,70,25);
         clear_button.setBounds(520,20,70,25);
         print_button.setBounds(600,20,70,25);
         
         p1.add(insert_text);p1.add(insert_button);
         p1.add(delete_text);p1.add(delete_button);
         p1.add(find_text);p1.add(find_button);
         p1.add(clear_button);
         p1.add(print_button);
         
        insert_button.setBackground(Color.BLACK);
        insert_button.setForeground(Color.WHITE);
        
        delete_button.setBackground(Color.BLACK);
        delete_button.setForeground(Color.WHITE);
        
        find_button.setBackground(Color.BLACK);
        find_button.setForeground(Color.WHITE);
        
        clear_button.setBackground(Color.BLACK);
        clear_button.setForeground(Color.WHITE);
        
        print_button.setBackground(Color.BLACK);
        print_button.setForeground(Color.WHITE);
       
        actions action=new actions();
        
        insert_button.addActionListener(action);
        delete_button.addActionListener(action);
        find_button.addActionListener(action);
        clear_button.addActionListener(action);
        print_button.addActionListener(action);
        
    }
    public class actions implements ActionListener
    {
        RedBlackTree main_tree;

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if(ae.getSource()==insert_button)
            {
                String inserted=insert_text.getText();
                insert_text.setText(" ");
                int num_inserted=Integer.parseInt(inserted);
                main_tree.insert(num_inserted);
            }
            else if(ae.getSource()==delete_button)
            {
                String deleted=delete_text.getText();
                delete_text.setText(" ");
                int num_deleted=Integer.parseInt(deleted);
                main_tree.delete(num_deleted);
            }
            else if(ae.getSource()==find_button)
            {
                String found=find_text.getText();
                find_text.setText(" ");
                int num_found=Integer.parseInt(found);
                main_tree.searchTree(num_found);
            }
            else if(ae.getSource()==clear_button)
            {
                main_tree.clear();
            }
            else if(ae.getSource()==print_button)
            {
                main_tree.print();
            }
            
        }
        
    }
}
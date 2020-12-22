/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author pc
 */
public class TreeFrame extends JFrame implements ActionListener{
    JButton b1,b2,b3,b4;
    JLabel l1;
    JTextField t1,t2;
    JPanel p1,p2;
    
    public TreeFrame()
    {  //to display frame
        this.setTitle("RedBlackTree");
        this.setVisible(true);
        this.setSize(1500, 1500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        p1=new JPanel();
        p2=new JPanel();
        p1.setBackground(Color.LIGHT_GRAY);
        p2.setBackground(Color.GRAY);
        this.setLayout(null);
        p1.setBounds(0, 0,1500, 100);
        p2.setBounds(0, 0,1500,1500);
         add(p1);add(p2);
         b1=new JButton("Insert");
         b2=new JButton("Delete");
         b3=new JButton("Clear");
         b4=new JButton("Print");
         l1=new JLabel();
         t1=new JTextField();
         t2=new JTextField();
         p1.setLayout(null);
         p2.setLayout(null);
         t1.setBounds(10,20, 90, 25);
         b1.setBounds(105,20, 70, 25);
         t2.setBounds(180,20, 90, 25);
         b2.setBounds(275,20,70, 25);
         b3.setBounds(350,20,90, 25);
         b4.setBounds(445,20,70,25);
         p1.add(t1);p1.add(b1);
         p1.add(t2);p1.add(b2);
         p1.add(b3);
         p1.add(b4);
         l1.setBounds(150, 100, 1000, 500);
         p2.add(l1);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
      //even action
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        
    }
    String node = new String(); 
    @Override
    public void actionPerformed(ActionEvent ae) {
         
        if(ae.getSource()==b1)
        {
            int n=1;
            for(int i=0;i<50;i++)
                    {
                        node=node+" "+n;
                        l1.setText(" "+node);
                        n++;
                    }
           
           // node=node+" "+t1.getText().toString();
           
            t1.setText(" ");

        }
        if(ae.getSource()==b2)
        {
          
        }
    
    }
    
}

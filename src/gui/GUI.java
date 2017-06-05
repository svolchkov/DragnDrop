/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sergeyv
 */
public class GUI extends JFrame implements ActionListener {
    
    
    JPanel panel;
    JPanel iconPanel;

    public GUI(){
        setTitle("DropZone");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iconPanel = new JPanel();
        panel = new JPanel();
        // iconPanel.setOpague(true);
        // panel.setBounds(0, 0, 400, 400);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        // setBackground(Color.BLUE);
        JButton button = new JButton("Exit");
        button.addActionListener(
                new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        } //To change body of generated methods, choose Tools | Templates.
            }
        );
        
        
        MyDropListener myDropListener = new MyDropListener(iconPanel);
        
        new DropTarget (iconPanel,myDropListener);
        
        JButton clear = new JButton("Clear");
        clear.addActionListener(
                new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Set <JLabel> set = new HashSet<JLabel>();
                            set.addAll(myDropListener.labels.keySet());
                            for (JLabel j: set){
                                Container parent = j.getParent();
                                parent.remove(j);
                                parent.validate();
                                parent.repaint();
                                myDropListener.labels.remove(j);
                            }
                        } //To change body of generated methods, choose Tools | Templates.
            }
        );
        
        panel.add(clear,BorderLayout.SOUTH);
        panel.add(button,BorderLayout.SOUTH);
        getContentPane().add(iconPanel, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.PAGE_END);
        setSize(400,400);
        setLocationRelativeTo(null);
        
        

            
//        iconPanel.setDropTarget(new DropTarget(){
//            @Override
//            public synchronized void drop(DropTargetDropEvent dtde) {
//                // handle drop outside current table (e.g. add row)
//                super.drop(dtde);
//                }
//            });
        
        // String s = "c:/filetr/SFR.xlsx";
        // JFileChooser chooser = new JFileChooser();

        // File file = new File(s);
        
        
        
        setVisible(true);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new GUI ();
        // frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

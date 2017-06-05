/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.*;
import javax.swing.*;
// import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.event.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
/**
 *
 * @author sergeyv
 */
public class MyDropListener implements DropTargetListener{
    
    JPanel dropTarget;
    // List <JLabel> labels;
    Map<JLabel,String> labels;
    int labelCounter = 0;
    
    public MyDropListener(JPanel j){
        this.dropTarget = j;
        labels = new HashMap<JLabel,String>();
    } 
    
     @Override
    public void drop(DropTargetDropEvent event) {

        // Accept copy drops
        event.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = event.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {

            try {

                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {

                    // Get all of the dropped files
                   List<File> files = (List) transferable.getTransferData(flavor);

                    // Loop them throughIterator<String> i = someList.iterator(); i.hasNext();
                    for (File file: files) {
                        // File file = i.next();

                        // Print out the file path
                        String fileName = file.getName();
                        // System.out.println("File path is '" + fileName + "'.");
                        try{
                                sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(file);
                                Icon icon = new ImageIcon(sf.getIcon(true));
           // Icon icon = chooser.getIcon(file);
            // Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
            // Image image = ((ImageIcon) icon).getImage();
        // show the icon
                                CustomLabel ficon = new CustomLabel(fileName, icon, SwingConstants.LEFT, file);
                                labels.put(ficon, fileName);
                                ficon.setTransferHandler(new FileTransferHandler (ficon));
                                ficon.addMouseListener(new MouseAdapter()
                                {
                                public void mouseClicked(MouseEvent e)  
                                {  
//                                    saySomething("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
//                                    if (e.getClickCount() == 2)  // double click
//                                    {
//                                        try{
//                                            Desktop.getDesktop().open(file);
//                                        }
//
//                                        catch(IOException ex){
//                                            ex.printStackTrace();
//                                        }
//                                    }
                                }
                               public void mousePressed(MouseEvent e) {
                                    if (e.getClickCount() == 2)  // double click
                                    {
                                        try{
                                            Desktop.getDesktop().open(file);
                                        }

                                        catch(IOException ex){
                                            ex.printStackTrace();
                                        }
                                    }
                                    else{
                                        TransferHandler handler = ficon.getTransferHandler();
                                        handler.exportAsDrag(ficon, e, TransferHandler.COPY);
                                    }
//                                    saySomething("Mouse pressed; # of clicks: "
//                    + e.getClickCount(), e);
                                    //JComponent c = (JComponent) e.getSource();
//                                    File f = File.
 
                                    }
                                
                                
                            }
                        );
            
            // Component c = event.getDropTargetContext().getComponent();
            // Object target = event.getSource();
            // Component c = ((DropTargetContext) source).getComponent();
            // JPanel j = (JPanel)c;
                        this.dropTarget.add(ficon);
                        this.dropTarget.revalidate();
                        this.dropTarget.repaint();
                            }
                        catch (FileNotFoundException ex){
                                ex.printStackTrace();
                        }
                    }
                    
                }
                else if(flavor.isFlavorTextType()){
                    // System.out.println("this is text");
                    String text = (String) transferable.getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor);
                    String[] strings = text.split("\\r?\\n");
                    // System.out.println(text);
                    File file = File.createTempFile("TXT", ".txt", null);
                    sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(file);
                    Icon icon = new ImageIcon(sf.getIcon(true));
           // Icon icon = chooser.getIcon(file);
            // Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
            // Image image = ((ImageIcon) icon).getImage();
        // show the icon
                                String fileName = file.getName();
//                                JLabel ficon = new JLabel(fileName, icon, SwingConstants.LEFT);
//                                labels.put(ficon, fileName);
                                WriteToFile writer = new WriteToFile(file.getPath());
                                writer.writeTextFile(strings);
                                CustomLabel ficon = new CustomLabel(fileName, icon, SwingConstants.LEFT, file);
                                labels.put(ficon, fileName);
                                ficon.setTransferHandler(new FileTransferHandler (ficon));
                                ficon.addMouseListener(new MouseAdapter()
                                {
                                public void mouseDoubleClicked(MouseEvent e)  
                                {  
//                                    if (e.getClickCount() == 2)  // double click
//                                    {
//                                        try{
//                                            Desktop.getDesktop().open(file);
//                                        }
//
//                                        catch(IOException ex){
//                                            ex.printStackTrace();
//                                        }
//                                    }                                    
                                }
                                public void mousePressed(MouseEvent e) {
                                    //JComponent c = (JComponent) e.getSource();
//                                    File f = File.
                                     if (e.getClickCount() == 2)  // double click
                                    {
                                        try{
                                            Desktop.getDesktop().open(file);
                                        }

                                        catch(IOException ex){
                                            ex.printStackTrace();
                                        }
                                    }
                                    else{
                                        TransferHandler handler = ficon.getTransferHandler();
                                        handler.exportAsDrag(ficon, e, TransferHandler.COPY);
                                    }
                                    }                               
                                
                            }
                        );
            
            // Component c = event.getDropTargetContext().getComponent();
            // Object target = event.getSource();
            // Component c = ((DropTargetContext) source).getComponent();
            // JPanel j = (JPanel)c;
                        this.dropTarget.add(ficon);
                        this.dropTarget.revalidate();
                        this.dropTarget.repaint();
                            
                    
                    break;
                }
                                        
                else if(transferable.isDataFlavorSupported(java.awt.datatransfer.DataFlavor.imageFlavor))
                            {
                                try {
    // retrieve image
                                    // Image image = (Image) transferable.getTransferData(java.awt.datatransfer.DataFlavor.imageFlavor);
                                    Image image = (Image) transferable.getTransferData(java.awt.datatransfer.DataFlavor.imageFlavor);
                                    // System.out.println("Hi");
                                    BufferedImage bi = (BufferedImage) image;
                                    // int width = bi.getWidth(null);
                                    final int WIDTH = 32;
                                    final int HEIGHT = 32;
                                    int height = bi.getHeight(null);
                                    BufferedImage dest = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);

                                    // Draw the image on to the buffered image
                                    Graphics2D bGr = dest.createGraphics();
                                    bGr.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, 
                                            RenderingHints.VALUE_RENDER_QUALITY));
                                    // bGr.setColor(Color.WHITE);
                                    // bGr.fillRect(0, 0, width, height);
                                    bGr.drawImage(image, 0,0, WIDTH,HEIGHT, null);
                                    bGr.dispose();
                                   
                                    
                                    // String fileName = "saved.png";
                                    File file = File.createTempFile("IMG", ".png", null);
                                    sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(file);
                                    // Icon icon = new ImageIcon(sf.getIcon(true));
                                    
               // Icon icon = chooser.getIcon(file);
                // Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
                // Image image = ((ImageIcon) icon).getImage();
            // show the icon        
                                    Icon icon = new ImageIcon(dest);
//                                    JLabel ficon = new JLabel(file.getName(), icon, SwingConstants.LEFT);
//                                    labels.put(ficon, file.getName());
                                    String fileName = file.getName();
                                    CustomLabel ficon = new CustomLabel(fileName, icon, SwingConstants.LEFT, file);
                                    labels.put(ficon, fileName);
                                    ficon.setTransferHandler(new FileTransferHandler (ficon));                                  
                                    
                                    // Image resizedImage = image.getScaledInstance(ficon.getWidth(), ficon.getHeight(), Image.SCALE_FAST);
                                    
                                    // ficon.setIcon(icon);
                                    ficon.addMouseListener(new MouseAdapter()
                                    {
                                    public void mouseClicked(MouseEvent e)  
                                    {  
//                                        if (e.getClickCount() == 2)  // double click
//                                        {
//                                           try{
//                                               Desktop.getDesktop().open(file);
//                                           }
//
//                                           catch(IOException ex){
//                                               ex.printStackTrace();
//                                           }
//                                        }

                                }
                                   public void mousePressed(MouseEvent e) {
                                    //JComponent c = (JComponent) e.getSource();
//                                    File f = File.
                                    if (e.getClickCount() == 2)  // double click
                                    {
                                        try{
                                            Desktop.getDesktop().open(file);
                                        }

                                        catch(IOException ex){
                                            ex.printStackTrace();
                                        }
                                    }
                                    else{
                                        TransferHandler handler = ficon.getTransferHandler();
                                        handler.exportAsDrag(ficon, e, TransferHandler.COPY);
                                    }
                                    } 
                                
                            }
                        );
            
            // Component c = event.getDropTargetContext().getComponent();
            // Object target = event.getSource();
            // Component c = ((DropTargetContext) source).getComponent();
            // JPanel j = (JPanel)c;
                                    this.dropTarget.add(ficon);
                                    this.dropTarget.revalidate();
                                    this.dropTarget.repaint();
                                    ImageIO.write(bi, "png", file);

                                    }catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                
                                break;
                                // System.out.println("It's an image");
                                
                            }    
            } catch (Exception e) {

                // Print out the error stack
                e.printStackTrace();

            }
        }

        // Inform that the drop is complete
        event.dropComplete(true);

    }
    
    @Override
    public void dragEnter(DropTargetDragEvent event) {
    }

    @Override
    public void dragExit(DropTargetEvent event) {
    }

    @Override
    public void dragOver(DropTargetDragEvent event) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent event) {
    }
    
    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                        + e.getComponent().getClass().getName()
                        + ".");
    }
}

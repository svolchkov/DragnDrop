/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author sergeyv
 */
public class CustomLabel extends JLabel {
    
    public File labelFile;
    
    public CustomLabel(String text, Icon icon, int horizontalAlignment, File file){
        super(text,icon,horizontalAlignment);
        this.labelFile = file;
    }

    CustomLabel(String fileName, Icon icon, int LEFT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 *
 * @author sergeyv
 */
public class WriteToFile {
    
    private String path; 
    private boolean appendToFile = false;
    private String[] text;

    
    
    public WriteToFile(String fileName){
        path = fileName;
    }
    
    public WriteToFile(String fileName, boolean appendValue){
        path = fileName;
        appendToFile = appendValue;
    }
    
    public void writeTextFile(String[] textStrings) throws IOException{
        text = textStrings;
        FileWriter write = new FileWriter( path , appendToFile);
        PrintWriter printLine = new PrintWriter( write );
        for (String textString : textStrings){
            printLine.println(textString);
        }
        printLine.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.datatransfer.Transferable;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author sergeyv
 */
public class FileTransferHandler extends TransferHandler {

    FileTransferHandler(JComponent jComponent) {
        this.createTransferable(jComponent);
    }
        @Override
        public Transferable createTransferable(JComponent c) { 
            CustomLabel customLabel = (CustomLabel) c;
            return new FileTransferable(customLabel.labelFile);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }
 }



package encodeproject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Katie, Kate
 */
public class encodeDecodeGUI extends javax.swing.JFrame {
    
    public Iterator itShow;
    //private Timer showTimer;
    private String outFilePath;
    private File chosenFileEncode; 
    private String encodeFileName;
    

    public encodeDecodeGUI() {
        initComponents();
        BetterButton.setSelected(true);
        createBufferStrategy(2);
        ImageDisplay.setDoubleBuffered(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encodeDecodeFileChooser = new javax.swing.JFileChooser();
        QualityButtons = new javax.swing.ButtonGroup();
        OutFileChooser = new javax.swing.JFileChooser();
        mainPanel = new java.awt.Panel();
        checkbox1 = new java.awt.Checkbox();
        SelectOutFile = new javax.swing.JButton();
        getFileForEncodeButton = new javax.swing.JButton();
        getFileForDecodeButton = new javax.swing.JButton();
        GoodButton = new javax.swing.JRadioButton();
        BetterButton = new javax.swing.JRadioButton();
        BestButton = new javax.swing.JRadioButton();
        videoViewPanel = new java.awt.Panel();
        ImageDisplay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Encoder");
        setPreferredSize(new java.awt.Dimension(552, 672));
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(153, 204, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(532, 632));

        checkbox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        checkbox1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        checkbox1.setLabel("  Include Subdirectories");
        checkbox1.setName("IncludeSubdirs"); // NOI18N

        SelectOutFile.setText("Name Video and Encode!");
        SelectOutFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectOutFileActionPerformed(evt);
            }
        });

        getFileForEncodeButton.setText("Select Source Directory");
        getFileForEncodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFileForEncodeButtonActionPerformed(evt);
            }
        });

        getFileForDecodeButton.setText("View Video");
        getFileForDecodeButton.setToolTipText("");
        getFileForDecodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFileForDecodeButtonActionPerformed(evt);
            }
        });

        GoodButton.setBackground(new java.awt.Color(153, 204, 255));
        QualityButtons.add(GoodButton);
        GoodButton.setText("Good");
        GoodButton.setBorder(null);
        GoodButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        BetterButton.setBackground(new java.awt.Color(153, 204, 255));
        QualityButtons.add(BetterButton);
        BetterButton.setText("Better");
        BetterButton.setBorder(null);
        BetterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BetterButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        BestButton.setBackground(new java.awt.Color(153, 204, 255));
        QualityButtons.add(BestButton);
        BestButton.setText("Best");
        BestButton.setBorder(null);
        BestButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        videoViewPanel.setBackground(new java.awt.Color(51, 51, 51));
        videoViewPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        ImageDisplay.setPreferredSize(new java.awt.Dimension(512, 512));

        javax.swing.GroupLayout videoViewPanelLayout = new javax.swing.GroupLayout(videoViewPanel);
        videoViewPanel.setLayout(videoViewPanelLayout);
        videoViewPanelLayout.setHorizontalGroup(
            videoViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoViewPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ImageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        videoViewPanelLayout.setVerticalGroup(
            videoViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, videoViewPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ImageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(videoViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getFileForEncodeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(GoodButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BetterButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BestButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(SelectOutFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(getFileForDecodeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getFileForEncodeButton)
                    .addComponent(SelectOutFile)
                    .addComponent(getFileForDecodeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BestButton)
                        .addComponent(BetterButton)
                        .addComponent(GoodButton)))
                .addGap(15, 15, 15)
                .addComponent(videoViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("SleepWhileInLoop")
    private void getFileForDecodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFileForDecodeButtonActionPerformed
        
        encodeDecodeFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnValue = encodeDecodeFileChooser.showOpenDialog(this.mainPanel);

        if (returnValue == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            getFileForDecodeButton.setEnabled(false);
            File chosenFileDecode = encodeDecodeFileChooser.getSelectedFile();
            String decodeFileName = chosenFileDecode.toString();
            System.out.println("Chosen file was " + decodeFileName);
            Decoder decoder = new Decoder();
            decoder.decode(decodeFileName);

            itShow = decoder.bufferedImages.iterator();

            ImageIcon image = new ImageIcon((BufferedImage) itShow.next());
            //ImageDisplay.setDoubleBuffered(true);
            ImageDisplay.setIcon(image);
                  
            // display the array of images

          Thread doShowVideo = new Thread() 
          {
              @Override
              public void run() 
                  {
                     while (itShow.hasNext())
                     {
                          ImageIcon image = new ImageIcon((BufferedImage) itShow.next());
                          ImageDisplay.setIcon(image);
                         try 
                            {
                                Thread.sleep(100);
                            } 
                         catch (InterruptedException ex) 
                            {
                                 Logger.getLogger(encodeDecodeGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                     }
                    ImageDisplay.setIcon(null);
                    getFileForDecodeButton.setEnabled(true);

                  }  // end run
             }; // end thread

            doShowVideo.start();  
            decoder = null;
            
        } // end if
         
    }//GEN-LAST:event_getFileForDecodeButtonActionPerformed

    
    private void getFileForEncodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFileForEncodeButtonActionPerformed
        encodeDecodeFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        encodeDecodeFileChooser.setDialogTitle("Choose Folder Containing Movie Frames");        
        int returnValue = encodeDecodeFileChooser.showOpenDialog(this.mainPanel);

        if (returnValue == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            chosenFileEncode = encodeDecodeFileChooser.getSelectedFile();
            encodeFileName = chosenFileEncode.toString();
        }
    }//GEN-LAST:event_getFileForEncodeButtonActionPerformed

    private void SelectOutFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectOutFileActionPerformed

    OutFileChooser.setDialogTitle("Save Movie As...");   

    int returnValue = OutFileChooser.showSaveDialog(this.mainPanel);
    if (returnValue == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            if (chosenFileEncode == null)
            {
                String message = "No Source Image Directory Selected!";
                JOptionPane.showMessageDialog(new JFrame(), message, "Encode Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            outFilePath = OutFileChooser.getSelectedFile().toString();
            if (OutFileChooser.getSelectedFile().isFile())
            {
                OutFileChooser.getSelectedFile().delete();
            }

        

    System.out.println("encoding!");
    SelectOutFile.setEnabled(false);

    // user specifies quality using radio buttons.  medium is default
    byte quality = 1;  

    if (GoodButton.isSelected())
    {
        quality = 0;
    }            
    else if (BestButton.isSelected())
    {
        quality = 2;
    }

    // user specifies whether or not to include subdirectories
    boolean includeSubDirs = false;
    if (checkbox1.getState())
    {
        includeSubDirs = true;
    }

    if (outFilePath == null)
    {
        outFilePath = chosenFileEncode + "/myVideoDefault";
    }

    Encoder encoder = new Encoder(quality, includeSubDirs);
    encoder.encodeImagesFromFolder(chosenFileEncode,encodeFileName, outFilePath);    

    JOptionPane.showMessageDialog(new JFrame(), "Done Encoding!", "Done",JOptionPane.PLAIN_MESSAGE);
    
    outFilePath = null;
    SelectOutFile.setEnabled(true);
        
    }

    }//GEN-LAST:event_SelectOutFileActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(encodeDecodeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(encodeDecodeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(encodeDecodeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(encodeDecodeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new encodeDecodeGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BestButton;
    private javax.swing.JRadioButton BetterButton;
    private javax.swing.JRadioButton GoodButton;
    private javax.swing.JLabel ImageDisplay;
    private javax.swing.JFileChooser OutFileChooser;
    private javax.swing.ButtonGroup QualityButtons;
    private javax.swing.JButton SelectOutFile;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JFileChooser encodeDecodeFileChooser;
    private javax.swing.JButton getFileForDecodeButton;
    private javax.swing.JButton getFileForEncodeButton;
    private java.awt.Panel mainPanel;
    private java.awt.Panel videoViewPanel;
    // End of variables declaration//GEN-END:variables
}

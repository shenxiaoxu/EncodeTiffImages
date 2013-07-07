package encodeproject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Encoder {
    
    // list of files to encode
    //ArrayList<String> paths;
    
    // image quality
    private byte quality;
    
    // whether or not to include subdirectories
    private boolean includeSubDirs;
    
    // String of raw image data (before runlength encoding) 
    private byte[] rawBytes;
    private ByteArrayOutputStream rawStream;
    
    // compressed data (run length compression)
    private byte[] compressedStream;
    
    
    public Encoder(final byte inputQuality, final boolean include)
    {
        rawStream = new ByteArrayOutputStream();
        quality = inputQuality;
        includeSubDirs = include;
        rawStream.write(quality);
        //paths = new ArrayList<String>();
    }
    
     /*read all files in folder - based on http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
    * for each file, make Image encode, write bytes to rawstream
    * 
    */
    public void encodeImagesFromFolder(File folder, String filename, String outfilepath) {
    boolean hasImages = false;
    for (File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            if (includeSubDirs)  // if requested by user, encode any images in subdirectories
            {
                encodeImagesFromFolder(fileEntry, filename, outfilepath);
            }
            else  // if inclusion not requested by user, skip subdirectories
            {
            continue;
            }
        }
        else if(fileEntry.isHidden())
        {
            continue;  // skip hidden files
        }    
        else {
            // for each file, create image object, encode into byte array, add to rawstream
            Image currentImage = new Image();
            String path = folder + "/" + fileEntry.getName();
            //paths.add(path);
            String fileExtension = fileEntry.getName().substring(fileEntry.getName().lastIndexOf(".")+1,fileEntry.getName().length());
     
            // supported file types are tiff bmp gif png jpeg
            if (fileExtension.equals("jpg")||fileExtension.equals("tif")||fileExtension.equals("tiff")||fileExtension.equals("bmp")||fileExtension.equals("gif")||fileExtension.equals("png"))
            {
                currentImage.makeYUV(path); 
                hasImages = true;
            }
            else
            {
                //System.out.println(path + " skipped");
                continue;
            }
                currentImage.encodeAll(quality);  
           
            try 
            {
                rawStream.write(currentImage.getEncodedYUV());
            } 
            catch (IOException ex) { System.out.println("Error writing to raw stream."); }
            }
        }
            
        if (!hasImages)
        {
            String message = "No Images Files Found!";
            JOptionPane.showMessageDialog(new JFrame(), message, "Encode Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // rawbytes is the byte arrays from all the images
        rawBytes = rawStream.toByteArray();
        
        // compress the byte array
        compressedStream = Compress.compress(rawBytes);
           
        // write to output file
        
        try 
        {
            BufferedOutputStream outstream = new BufferedOutputStream(new FileOutputStream(outfilepath));
            outstream.write(compressedStream);
            outstream.flush();
            outstream.close();

        } 
        catch (FileNotFoundException ex) { System.out.println("Problem writing to file."); } 
        catch (IOException ex) { System.out.println("Problem writing to file."); }
        
        System.out.println("done encoding!");
        
    }   
    
  
            }

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encodeproject;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Kate
 */
public class Decoder {
    
    // array of image objects, each holds data for one image
    //private ArrayList<Image> images;
    public ArrayList<BufferedImage> bufferedImages;
    
    // String of encoded image data (before runlength/huffman encoding) 
    private byte[] rawStream;
    
    // compressed String of encoded data
    private byte[] compressedStream;
    
    // quality of input images
    private byte quality;
    
    public Decoder()
    {
        bufferedImages = new ArrayList<BufferedImage>();
    }
    
    
    /*
     * decode
     * reads bytes from file, assigns to images, decodes each image
     * 
     * reading from file based on: http://stackoverflow.com/questions/6340999/create-an-arraylist-of-bytes
     * 
     */
    public void decode(String fileToDecode)
    {  
        try 
        {
            InputStream input = new BufferedInputStream(new FileInputStream(fileToDecode));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] dataBuffer = new byte[EncodeProject.totalElements];
            int size = 0;
            while ((size = input.read(dataBuffer)) != -1) {
                out.write(dataBuffer, 0, size);
            }
            compressedStream = out.toByteArray();
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Input file not found.");
        } catch (IOException ex) {
            System.out.println("Error reading input file.");
        }
              
        rawStream = Compress.decompress(compressedStream);
        
        quality = rawStream[0];
        int rawStreamLength = rawStream.length - 1;
        int rawStreamIndex = 1;
        
        // create buffered image array from byte array
        while ( rawStreamIndex < rawStreamLength )
        {
            Image currentImage;
            currentImage = new Image();
            System.arraycopy(rawStream, rawStreamIndex, currentImage.encodedYUV, 0, EncodeProject.elementsPerImage);
            currentImage.decodeAll(quality);
            currentImage.makeBufferedImage();
            bufferedImages.add(currentImage.myBuff);
            rawStreamIndex += EncodeProject.elementsPerImage;
        }
        
        System.out.println("Done decoding!");
        
    }
    
}

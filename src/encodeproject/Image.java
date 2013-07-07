
package encodeproject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

/**
 *
 * @authors Kate, Mike Bhatt, XiaoXu Shen
 */
public class Image {
	
    // arrays of raw YUV input data
    private int YData[][];
    private int CrData[][];
    private int CvData[][];
    
    // encoded int array produced from performing DCT, quantize, and zigzag on Y, U and V data
    private byte encodedY[]; 
    private byte encodedCr[];
    private byte encodedCv[];
    
    public byte encodedYUV[];
    
    // image objects
    private PlanarImage myImage;
    public BufferedImage myBuff;
    
    // constructor - makes empty image object
    public Image()
    {
        YData = new int[EncodeProject.imageHeight][EncodeProject.imageWidth];
        CrData = new int[EncodeProject.downSampleHeight][EncodeProject.downSampleWidth];
        CvData = new int[EncodeProject.downSampleHeight][EncodeProject.downSampleWidth];
        
        encodedY = new byte[EncodeProject.Ysize]; 
        encodedCr = new byte[EncodeProject.UVsize];
        encodedCv = new byte[EncodeProject.UVsize];
        
        encodedYUV = new byte[EncodeProject.elementsPerImage];
    }
    
    /*
     * show me
     * 
     *
    public void showMe()
    {
        JFrame frame = new JFrame("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(EncodeProject.imageWidth, EncodeProject.imageHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon(myBuff);
        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
        frame.add(label1);
        frame.validate();
        frame.setVisible(true);
    }*/
    
    
    public void makeBufferedImage()
    {
        myBuff = new BufferedImage(EncodeProject.imageWidth, EncodeProject.imageHeight, BufferedImage.TYPE_INT_RGB); 

        int c, d, e, y, u, v, r, g, b ,rgb;

        // convert YUV back to RGB
        for (int ii = 0; ii < EncodeProject.imageHeight; ii+=2)
        {	
            for (int jj = 0; jj < EncodeProject.imageWidth;jj+=2)
            {
                u = CrData[ii/2][jj/2];
                v = CvData[ii/2][jj/2];
                
                for (int iiy = ii; iiy < ii+2; iiy++)
                {
                    for (int jjy = jj; jjy < jj+2; jjy++)
                    {
                        y = YData[iiy][jjy];
                        
                        //http://msdn.microsoft.com/en-us/library/ms893078.aspx
                        // http://svn.xiph.org/trunk/w3d/yuv.c
                        c = y - 16; // + 128;
                        d = u - 128;
                        e = v - 128;
                        r = ( 298*c + 409*e + 128) >> 8;
                        r = ((r > 255) ? 255 : (r < 0) ? 0 : r);
                        g = ( 298*c - 100*d - 208*e + 128) >> 8;
                        g = ((g > 255) ? 255 : (g < 0) ? 0 : g);
                        b = ( 298*c + 516*d + 128) >> 8;
                        b = ((b > 255) ? 255 : (b < 0) ? 0 : b);
                        int pixel = new Color(r,g,b).getRGB();
                        myBuff.setRGB(iiy,jjy,new Color(r,g,b).getRGB());
                        //rgb = ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff);
                        //myBuff.setRGB(ii,jj, rgb);
                    }
                } 
            }  
        }
    }  
    
    /*make YUV
     * 
     * takes image file name, 
     * reads image from tiff and converts to YUV color space
     * 
     */
    //public void makeYUV(File folder, String myfile)
    public void makeYUV(String path)
    {   
        // read in tiff file
        myImage = JAI.create("fileload", path);
        myBuff = myImage.getAsBufferedImage();
        
        int y, cr, cv, iids, jjds;

        // read through image, convert RGB to YUV.  downsample Cr and Cv
        for (int ii = 0; ii < EncodeProject.imageWidth; ii++)
        {
            iids = ii/2;  // downsampling index		
            for (int jj = 0; jj < EncodeProject.imageHeight;jj++)
            {
                jjds = jj/2;  // downsampling index

                // get color data from current image
                Color c = new Color(myBuff.getRGB(ii, jj));

                // calculate Y value, round and scale to fit into 8 bits. 
                // here's the original:
                // y = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                y = (int) (66 * c.getRed() + 129 * c.getGreen() + 25 * c.getBlue() + 128) >> 8;  
                // shift into range [16,235] and store
                YData[ii][jj] = y + 16; // - 128;

                if((ii%2==0) && (jj%2==0))  // downsample cr and cv
                 {
                    // calculate cr and cv, round and scale to 8 bits.
                    // here are the originals:
                    // cr = (int) (-0.14713 * c.getRed() + -0.28886 * c.getGreen() + 0.436 * c.getBlue());
                    // cv = (int) (0.615 * c.getRed() + -0.51499 * c.getGreen() + -0.10001 * c.getBlue());
                    cr = (int) (-38 * c.getRed() + -74 * c.getGreen() + 112 * c.getBlue() + 128) >> 8;
                    cv = (int) (112 * c.getRed() + -94 * c.getGreen() + -18 * c.getBlue() + 128) >> 8;
                    // shift into range [0,255] and store
                    CrData[iids][jjds] = cr + 128;
                    CvData[iids][jjds] = cv + 128;  
                }	 
            }  
        }
    }
    
     /* decodeAll
     * converts byte array into Y,U, and V matrices 
     * 
     */     
    public void decodeAll(final byte quality)
    {   
        int inputIndex = 0;
        System.arraycopy(encodedYUV, inputIndex, encodedY, 0, EncodeProject.Ysize);
        inputIndex += EncodeProject.Ysize;
        System.arraycopy(encodedYUV, inputIndex, encodedCr, 0, EncodeProject.UVsize);
        inputIndex += EncodeProject.UVsize;
        System.arraycopy(encodedYUV, inputIndex, encodedCv, 0, EncodeProject.UVsize);        
        
        decodeMe(encodedY, YData, EncodeProject.imageWidth, EncodeProject.imageHeight, quality);
        decodeMe(encodedCr, CrData, EncodeProject.downSampleWidth, EncodeProject.downSampleHeight, quality);
        decodeMe(encodedCv, CvData, EncodeProject.downSampleWidth, EncodeProject.downSampleHeight, quality);       

    }
    
    /*
     * encodes Y, Cr and Cv matrices and stores as int arrays
     * copies all into a byte array for output to runlength encoder 
     * 
     */     
    public void encodeAll(final byte quality)
    {   
        encodedY = encodeMe(YData, EncodeProject.imageWidth, EncodeProject.imageHeight, quality);
        encodedCr = encodeMe(CrData, EncodeProject.downSampleWidth, EncodeProject.downSampleHeight, quality);
        encodedCv = encodeMe(CvData, EncodeProject.downSampleWidth, EncodeProject.downSampleHeight, quality);
        
        System.arraycopy(encodedY, 0, encodedYUV, 0, EncodeProject.Ysize);
        System.arraycopy(encodedCr, 0, encodedYUV, EncodeProject.Ysize, EncodeProject.UVsize);
        System.arraycopy(encodedCv, 0, encodedYUV, EncodeProject.Ysize+EncodeProject.UVsize, EncodeProject.UVsize);        

    }
    
    
	/* encodeMe:
			breaks data into 8x8 blocks, applies DCT and Quantization
			reads the block in a zigzag (upper left to lower right)
			writes result of each block to output string
	*/ 
    
    private byte[] encodeMe(final int input[][], final int width, final int height, final byte quality)
    {
            int elementsPerBlock = EncodeProject.blockSize*EncodeProject.blockSize;
            int block[][] = new int[EncodeProject.blockSize][EncodeProject.blockSize];
            byte output[] = new byte[width*height];
            int outIndex = 0;

            // go through the input by 8x8 blocks
            for (int ii=0; ii<height; ii +=EncodeProject.blockSize)
            {
                    for (int jj=0; jj<width; jj += EncodeProject.blockSize)
                    {
                            for (int kk=0; kk<EncodeProject.blockSize; kk++)
                            {
                                    for (int ll=0; ll<EncodeProject.blockSize; ll++)
                                    {
                                            block[kk][ll] = input[ii+kk][jj+ll];  // copy 8x8 block of input
                                    }
                            }

                            block = DCT.FDCT(block);  // perform DCT on block
                            /*for (int p=0; p<8; p++)
                            {
                                for (int q=0; q<8; q++)
                                {
                                    System.out.print(block[p][q]+" ");
                                }
                                System.out.print("\n");
                            }
                                System.out.print("\n");*/
                            block = Quantize(block, quality);  // perform quantization on block
                            int temp[] = Zigzag(block);  // read block from upper left to lower right                 
                            
                            // store elements into output array
                            for (int i=0; i<elementsPerBlock; i++ )
                            {
                                output[outIndex] = (byte) temp[i];
                                outIndex++;
                            }
                    }
            }
     return output;
    } // endfunction encodeMe

    /*
     * decodeMe
     * does all the encodeMe stuff in reverse
     */
    private void decodeMe(final byte input[], int output[][], final int width, final int height, final byte quality)
    {
        int inputIndex = 0;
        int elementsPerBlock = EncodeProject.blockSize*EncodeProject.blockSize;
        
        // put elements from the byte array back into the matrix
        for (int ii=0; ii<height; ii +=EncodeProject.blockSize)
        {
                for (int jj=0; jj<width; jj += EncodeProject.blockSize)
                {
                    // get next chunk of bytes, copy into block
                    // inverse quantize and inverse DCT, copy to output matrix
                    
                    int Block[][] = new int[EncodeProject.blockSize][EncodeProject.blockSize];
                    int temp[] = new int[elementsPerBlock];
                    for (int tempIndex = 0; tempIndex < elementsPerBlock; tempIndex++)
                    {
                        temp[tempIndex] = (int) input[inputIndex];
                        inputIndex++;
                    }
                    
                    unZigzag(temp, Block);
                    Block = deQuantize(Block, quality);                    
                    Block = DCT.IDCT(Block);
                    
                    // copy block to output matrix
                    for (int mm=0; mm<EncodeProject.blockSize; mm++)
                    {
                            for (int nn=0; nn<EncodeProject.blockSize; nn++)
                            {
                                output[ii+mm][jj+nn] = Block[mm][nn];
                            }
                    }
                    
                } // jj loop
        } // ii loop
        
    } // endfunction decodeMe
    
    
    

  /* item by item division by global qmatrix; round results */
    public int[][] Quantize(int inputData[][], final byte quality) {
        int outputData[][] = new int[EncodeProject.blockSize][EncodeProject.blockSize];
        int i,j;
        
        double result;
        
            for (i = 0; i < EncodeProject.blockSize; i++) {
                for (j = 0; j < EncodeProject.blockSize; j++) {
                    if (quality == 0)
                    {result = inputData[i][j] / EncodeProject.qmatrix0[i][j];}
                    else if (quality == 1)
                    {result = inputData[i][j] / EncodeProject.qmatrix1[i][j];}
                    else 
                    {result = inputData[i][j] / EncodeProject.qmatrix2[i][j];}
                    outputData[i][j] = (int) (Math.round(result));
                }
            }
        return outputData;
    }
   /* item by item multiply by global qmatrix; round results */
    public int[][] deQuantize(int inputData[][], final byte quality) {
        int outputData[][] = new int[EncodeProject.blockSize][EncodeProject.blockSize];
        int i,j;
        
        double result;
        
            for (i = 0; i < EncodeProject.blockSize; i++) {
                for (j = 0; j < EncodeProject.blockSize; j++) {
                    if (quality == 0)
                    {result = inputData[i][j] * EncodeProject.qmatrix0[i][j];}
                    else if (quality == 1)
                    {result = inputData[i][j] * EncodeProject.qmatrix1[i][j];}
                    else
                    {result = inputData[i][j] * EncodeProject.qmatrix2[i][j];}
                    outputData[i][j] = (int) (Math.round(result));
                }
            }
        return outputData;
    }
  
 /* reads block from upper left to lower right, stores as ?? */
    private int[] Zigzag(int input[][])
    {
        int output[] = new int [EncodeProject.blockSize*EncodeProject.blockSize];
        int outIndex = 0;
        int col = 0;
        int row = 0;
        
        // do first two
        output[outIndex] = input[row][col];
        outIndex++;
        col++;
        output[outIndex] = input[row][col];
        outIndex++;
        
        // set limit conditions
        int max = EncodeProject.blockSize - 1;
        
        int rowinc = -1;
        int colinc = 1;

        while (!(row == max && col == max))
        {
            // reverse direction (up&right <--> down&left)
            rowinc *= -1;
            colinc *= -1;
            row+= rowinc;
            col+= colinc;
            do
            {
                output[outIndex] = input[row][col];
                outIndex++;              
                 row += rowinc;
                 col += colinc;
            }
            while (row <= max && col <=max && col >= 0 && row >= 0);
            
            
            // when you go out of bounds, correct and switch direction
            if (row < 0)
                {row = 0;}
            else if (row > max)
            {
                row = max;
                col += 2;
            }
            else if (col < 0)
                {col = 0;}
            else if (col > max)
            {
                col = max;
                row += 2;
            }
            output[outIndex] = input[row][col];
            outIndex++;
        }
    return output;    
    } // endfunction Zigzag
    
    
    /* reads array back into block from upper left to lower right */
    private void unZigzag(final int input[], int output[][] )
    {
        int outIndex = 0;
        int col = 0;
        int row = 0;
        
        // do first two
        output[row][col] = input[outIndex];
        outIndex++;
        col++;
        output[row][col] = input[outIndex];
        outIndex++;
        
        // set limit conditions
        int max = EncodeProject.blockSize - 1;
        
        int rowinc = -1;
        int colinc = 1;

        while (!(row == max && col == max))
        {
            // reverse direction (up&right <--> down&left)
            rowinc *= -1;
            colinc *= -1;
            row+= rowinc;
            col+= colinc;
            do
            {
                output[row][col] = input[outIndex];;
                outIndex++;              
                 row += rowinc;
                 col += colinc;
            }
            while (row <= max && col <=max && col >= 0 && row >= 0);
            
            
            // when you go out of bounds, correct and switch direction
            if (row < 0)
                {row = 0;}
            else if (row > max)
            {
                row = max;
                col += 2;
            }
            else if (col < 0)
                {col = 0;}
            else if (col > max)
            {
                col = max;
                row += 2;
            }
            output[row][col] = input[outIndex];;
            outIndex++;
        }      
    } // endfunction unZigzag
    
    
        public byte[] getEncodedYUV()
    {
        return encodedYUV;
    }
        
        
        
}

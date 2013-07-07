
package encodeproject;

// this class contains constants needed by various other classes
// if there is a better place for them they can be moved
// but then you have to update where they are referenced

public class EncodeProject {    
    /*
     * constants for entire project (should not change)
     * 
     */
    
    // block size for DCT, quantization (should always be 8)
    public static final int blockSize = 8;
    
    // image sizes (given in assignment description)
    public static final int imageWidth = 512; 
    public static final int imageHeight = 512; 
    
    public static final int downSampleHeight = (imageHeight)/2;
    public static final int downSampleWidth = (imageWidth)/2;
    
    public static final int numberOfImages = 100; 
    
    public static final int Ysize = imageWidth*imageHeight;
    public static final int UVsize = downSampleWidth*downSampleHeight; 
    
    public static final int elementsPerImage = Ysize + 2*UVsize;
    public static final int totalElements = numberOfImages*(elementsPerImage);
    
    
    // quantization matrix
    
    // low quality qmatrix
    public static final int qmatrix0[][] = 
            {{17,25,60,110,110,110,110,110},
            {25,60,110,110,110,110,110,110},
            {60,110,110,110,110,110,110,110},
            {110,110,110,110,110,110,110,110},
            {110,110,110,110,110,110,110,110},
            {110,110,110,110,110,110,110,110},
            {110,110,110,110,110,110,110,110},
            {110,110,110,110,110,110,110,110}}; 
       
    // mid quality qmatrix
    public static final int qmatrix1[][] = 
            {{17,18,24,47,99,99,99,99},
            {18,21,26,66,99,99,99,99},
            {24,26,56,99,99,99,99,99},
            {47,66,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99}}; 
    
        // best quality qmatrix
        public static final int qmatrix2[][] = 
            {{16,11,10,16,24,40,51,61},
            {12,12,14,19,26,58,60,55},
            {14,13,16,24,40,57,69,56},
            {14,17,22,29,51,87,80,62},
            {18,22,37,56,68,109,103,77},
            {24,35,55,64,81,104,113,92},
            {49,64,78,87,103,121,120,101},
            {72,92,95,98,112,100,103,99}}; 
   
}

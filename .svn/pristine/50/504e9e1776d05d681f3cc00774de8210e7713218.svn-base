/*
 * by Michael Shen and Kate Thurmer
 */
package encodeproject;

import java.io.ByteArrayOutputStream;

public class Compress {
    
    // compress a byte array using run length encoding
        public static byte[] compress(byte[] raw){
        ByteArrayOutputStream compressed = new ByteArrayOutputStream();
        
        int length = raw.length;
        
        for (int ii = 0; ii < length; )
        {
            if (raw[ii] != 0)
            {
            compressed.write(raw[ii]);
            ii++;
            continue;
            }
            
            int runLength = 1;
            int jj = ii+1;
            
            while(jj < length && raw[jj] == 0){
                runLength++;
                jj++;
            }
            
            ii = jj;
            
            compressed.write((byte)0);
            
            while( runLength > 127 )
            {
                compressed.write((byte) 127);
                compressed.write((byte) 0);
                runLength -= 127;
            }
            
            compressed.write((byte) runLength);
            
            
        }
       
        return compressed.toByteArray();

    }
    /*
     * Decompress the byte array
     */
   
    public static byte[] decompress(byte compressed[]){
        
        ByteArrayOutputStream decompressed = new ByteArrayOutputStream();

        int length = compressed.length;
        
        for (int ii = 0; ii < length; ii++){
            if(compressed[ii] == 0)
            {
                ii++;
                int numZeroes = compressed[ii];
                for (int z = 0; z < numZeroes; z++)
                {
                    decompressed.write((byte) 0);
                }
            }

            else
            {
                decompressed.write(compressed[ii]);
            }
        }
        return decompressed.toByteArray();
    } 
}


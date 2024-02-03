package com.qrgenerator.qrgenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class QrCodeGenerator {
//here we have defined two method both are performing same operation but generateQRCodeImage method is saving it 
	//in the provided path as well
    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
    	try{
    		
	    	//qrcodewriter has ability to generate and decode QR code, here we are generating QR for certain links 
	    	//basically encoding provided links
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        //bitmatrix to decide color of each pixel in barcode
	        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	        Path path = FileSystems.getDefault().getPath(filePath);
	        //this matrixtoimagewriter will map url with  the generated QR code
	        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        //sets color in barcode pixels
        MatrixToImageConfig config = new MatrixToImageConfig(0xFFFF0000, 0xFFD3D3D3);
        //MatrixToImageWriter is class which provide utility methods for converting bitmatrix into bufferimage
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,config);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }

}
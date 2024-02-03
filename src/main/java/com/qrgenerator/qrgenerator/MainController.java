package com.qrgenerator.qrgenerator;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;

@Controller
public class MainController {

    private static final String QR_CODE_IMAGE_PATH_FOR_MEDIUM = "./src/main/resources/static/img/QRCode_1.png";
    private static final String QR_CODE_IMAGE_PATH_FOR_LINKEDIN = "./src/main/resources/static/img/QRCode_2.png";
    private static final String QR_CODE_IMAGE_PATH_FOR_GITHUB = "./src/main/resources/static/img/QRCode_3.png";

    @GetMapping("/")
    public String getQRCode(Model model){
        String medium="https://medium.com/@aartikumarisingh3002/";
        String github="https://github.com/Aarti002/";
        String linkedin="https://www.linkedin.com/in/linkinurl-aarti-k/";
        
        
        byte[] medium_image = new byte[0];
        byte[] linkedin_image = new byte[0];
        byte[] github_image = new byte[0];
        try {
            // Generate and Return Qr Code in Byte Array
        	medium_image=QrCodeGenerator.getQRCodeImage(medium, 250, 250);
        	linkedin_image=QrCodeGenerator.getQRCodeImage(linkedin, 250, 250);
        	github_image=QrCodeGenerator.getQRCodeImage(github, 250, 250);
        	// Generate and Save Qr Code Image in static/image folder
//            QrCodeGenerator.generateQRCodeImage(medium,250,250, QR_CODE_IMAGE_PATH_FOR_MEDIUM);
//            QrCodeGenerator.generateQRCodeImage(linkedin, 230, 230, QR_CODE_IMAGE_PATH_FOR_LINKEDIN);
//            QrCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH_FOR_GITHUB);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode1 = Base64.getEncoder().encodeToString(medium_image);
        String qrcode2 = Base64.getEncoder().encodeToString(linkedin_image);
        String qrcode3 = Base64.getEncoder().encodeToString(github_image);

        model.addAttribute("medium",medium);
        model.addAttribute("github",github);
        model.addAttribute("linkedin",linkedin);
        
        model.addAttribute("qrcode1",qrcode1);
        model.addAttribute("qrcode2",qrcode2);
        model.addAttribute("qrcode3",qrcode3);

        return "qrcode";
    }
}
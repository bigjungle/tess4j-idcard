package com.weizhixi;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test {

    public static void main(String[] args) throws Exception{
        //testEn();
        testZh();
    }

    //使用英文字库 - 识别图片
    public static void testEn() throws Exception {
        File imageFile = new File("./en.png");
        BufferedImage image = ImageIO.read(imageFile);
        //对图片进行处理
        image = convertImage(image);
        ITesseract instance = new Tesseract();//JNA Interface Mapping
        String result = instance.doOCR(image); //识别
        System.out.println(result);
    }

    //使用中文字库 - 识别图片
    public static void testZh() throws Exception {
       // File imageFile = new File("./zh2.png");
        File imageFile = new File("./idcard.png");

        BufferedImage image = ImageIO.read(imageFile);
        //对图片进行处理
        image = convertImage(image);
        ITesseract instance = new Tesseract();//JNA Interface Mapping
        instance.setLanguage("chi_sim");//使用中文字库
        String result = instance.doOCR(image); //识别
        System.out.println(result);
    }



    //对图片进行处理 - 提高识别度
    public static BufferedImage convertImage(BufferedImage image) throws Exception {
        //按指定宽高创建一个图像副本
        //image = ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight());
        //图像转换成灰度的简单方法 - 黑白处理
        image = ImageHelper.convertImageToGrayscale(image);
        //图像缩放 - 放大n倍图像
        image = ImageHelper.getScaledInstance(image, image.getWidth() * 3, image.getHeight() * 3);
        return image;
    }

}

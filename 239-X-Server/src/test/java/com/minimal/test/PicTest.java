package com.minimal.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by administrator on 2020/4/4.
 */
public class PicTest {
    public static void main(String args[]) throws IOException {
//        /**
//         * 要处理的图片目录
//         */
//        File dir = new File("e:/pic/");
//        /**
//         * 列出目录中的图片，得到数组
//         */
//        File[] files = dir.listFiles();
//        /**
//         * 遍历数组
//         */
//        for (int x = 0; x < files.length; x++) {
//            /**
//             * 定义一个RGB的数组，因为图片的RGB模式是由三个 0-255来表示的 比如白色就是(255,255,255)
//             */
//            int[] rgb = new int[3];
//            /**
//             * 用来处理图片的缓冲流
//             */
//            BufferedImage bi = null;
//            try {
//                /**
//                 * 用ImageIO将图片读入到缓冲中
//                 */
//                bi = ImageIO.read(files[x]);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            /**
//             * 得到图片的长宽
//             */
//            int width = bi.getWidth();
//            int height = bi.getHeight();
//            int minx = bi.getMinX();
//            int miny = bi.getMinY();
//            System.out.println("正在处理：" + files[x].getName());
//            /**
//             * 这里是遍历图片的像素，因为要处理图片的背色，所以要把指定像素上的颜色换成目标颜色
//             * 这里 是一个二层循环，遍历长和宽上的每个像素
//             */
//            for (int i = minx; i < width; i++) {
//                for (int j = miny; j < height; j++) {
//                    // System.out.print(bi.getRGB(jw, ih));
//                    /**
//                     * 得到指定像素（i,j)上的RGB值，
//                     */
//                    int pixel = bi.getRGB(i, j);
//                    /**
//                     * 分别进行位操作得到 r g b上的值
//                     */
//                    rgb[0] = (pixel & 0xff0000) >> 16;
//                    rgb[1] = (pixel & 0xff00) >> 8;
//                    rgb[2] = (pixel & 0xff);
//                    /**
//                     * 进行换色操作，我这里是要把蓝底换成白底，那么就判断图片中rgb值是否在蓝色范围的像素
//                     */
//                    if (rgb[0] < 155 && rgb[0] > 0 && rgb[1] < 256 && rgb[1] > 105 && rgb[2] < 256 && rgb[2] > 105) {
//                        /**
//                         * 这里是判断通过，则把该像素换成白色
//                         */
//                        bi.setRGB(i, j, 0xffffff);
//                    }
//
//                }
//            }
//            System.out.println("\t处理完毕：" + files[x].getName());
//            System.out.println();
//            /**
//             * 将缓冲对象保存到新文件中
//             */
//            FileOutputStream ops = new FileOutputStream(new File("e:/pic/"+x+".jpg"));
//            ImageIO.write(bi, "jpg", ops);
//            ops.flush();
//            ops.close();
//        }
        //指定的图片路径
        FileInputStream dir = new FileInputStream("E:/pic/test.jpg");
        //新建一个长度为3的数组，负责保存rgb的值
        int[] rgb = new int[3];
        //通过ImageIO.read()方法来返回一个BufferedImage对象，可以对图片像素点进行修改
        BufferedImage bImage  = ImageIO.read(dir);
        //获取图片的长宽高
        int width = bImage.getWidth();
        int height = bImage.getHeight();
        int minx = bImage.getMinTileX();
        int miny = bImage.getMinTileY();
        //遍历图片的所有像素点，并对各个像素点进行判断，是否修改
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = bImage.getRGB(i, j);
                //获取图片的rgb
                rgb[0] = (pixel & 0xff0000) >>16;
                rgb[1] = (pixel & 0xff00) >>8;
                rgb[2] = (pixel & 0xff) ;
                //进行判断，如果色素点在指定范围内，则进行下一步修改
                if (rgb[0]<110&&rgb[0]>50&& rgb[1]<30&&rgb[1]>10 && rgb[2]<50&&rgb[2]>25) {        //修改像素点，0x007ABB是证件照的蓝色背景色
                    bImage.setRGB(i, j, 0x007ABB);
                }
            }
        }
        //输出照片保存在本地
        FileOutputStream ops;
        try {
            ops = new FileOutputStream(new File("E:/pic/1.jpg"));
            //这里写入的“jpg”是照片的格式，根据照片后缀有所不同
            ImageIO.write(bImage, "jpg", ops);
            ops.flush();
            ops.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
package com.random.verification;

import link.hefang.helpers.RandomHelper;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author admin
 * @ClassName 生成随机的验证码
 * @date 2020/2/28
 * @Version 1.0
 **/
public class Demo {

    public static void main(String[] args) {

        /**
         * 创建一个BufferedImage图片缓冲区对象并指定它宽高和类型 这样相当于创建一个画板，然后可以在上面画画
         */
        BufferedImage bi = new BufferedImage(145, 55, BufferedImage.TYPE_INT_RGB);

        /**
         * 要生成图片的类型,可以是JPG GIF JPEG PNG等...成生成图片的保存路径和图片名称
         */
        final File file = new File("D:\\idea-workspace\\verification_code\\src\\resources\\certified_chart.png");

        try {
            if(file.exists()) {//测试由这个抽象路径名表示的文件或目录的存在，如果存在返回true
                //执行清空
                file.delete();
                //createNewFile创建扩展名
                file.createNewFile();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        writeImage(bi,"png",file);
    };

    /**
     *
     * @param bi
     * @param phototype
     * @param file
     * @return 渲染图像
     */
    public static boolean writeImage(BufferedImage bi,String phototype,File file){//RandomHelper这个类是自己封的，可以查找资料

        //生成随机字符(4位字符)
        final String letter = RandomHelper.letter(4, true);
        /**
         * for(String s: ss)
         * 就是便利集合，相当于for（int i=0;i<ss.size();i++）{ s=ss[i] }
         */
        //使用Final修饰符修饰的变量的特点：该变量会变成常亮，值不能被改变。
        final int width = 145;
        final int height = 55;
        //createGraphics返回这个方法用来画图像
        Graphics g = bi.getGraphics();
        //设置图像的背景颜色
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        //画干扰点
        for (int i = 0;i<500;i++){
            //x y循环设置轨迹
            final int x = RandomHelper.integer(0,width);
            final int y = RandomHelper.integer(0,height);
            //循环获取下面设置的随机颜色
            final Color color = randColor();
            //设置图像的xy颜色
            g.setColor(color);
            //设置xy的长度
            g.drawLine(x,y,x,y);
        }
        //画验证码
        for (int i = 0;i<letter.length();i++){
            //设置字符大小(大小是随机的)
            final int foutsize = RandomHelper.integer(28,45);
            g.setColor(randColor());//随机颜色
            g.setFont(new Font("Consolas",Font.TRUETYPE_FONT,foutsize));//设置什么风格的字体
            //设置字体随机波动
            g.drawString(letter.charAt(i) + "",20 + i *20,RandomHelper.integer(30,50));
        }
        //画干扰线
        for (int i = 0;i<20;i++){
            //设置起点坐标
            final int x = RandomHelper.integer(0,width);
            final int y = RandomHelper.integer(0,height);
            //设置终点坐标
            final int x1 = RandomHelper.integer(0,width);
            final int y1 = RandomHelper.integer(0,height);
            //循环获取下面设置的随机颜色
            final Color color = randColor();
            //设置图像线得颜色
            g.setColor(color);
            //起点连接终点形成干扰线
            g.drawLine(x,y,x1,y1);
        }
        boolean v = false;
        try {
            v = ImageIO.write(bi,phototype,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    };

    /**
     *
     * @return 随机染色
     */
    public static Color randColor(){

        return new Color(
                RandomHelper.integer(35,199),
                RandomHelper.integer(35,199),
                RandomHelper.integer(35,199)
        );
    };
}

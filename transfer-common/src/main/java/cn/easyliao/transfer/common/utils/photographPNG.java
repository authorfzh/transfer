package cn.easyliao.transfer.common.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
  
public class photographPNG {  
	
	
 /**
  * 去掉图片白色背景
  * 他会在源目录下生成一个相同名字但后缀为png的图片
  * @param path
  */
    public static void convert(String path) {  
        // TODO Auto-generated constructor stub  
        try {  
        	//从path中读到内存
            BufferedImage image = ImageIO.read(new File(path));  
            ImageIcon imageIcon = new ImageIcon(image);  
            BufferedImage bufferedImage = new BufferedImage(  
                    imageIcon.getIconWidth(), imageIcon.getIconHeight(),  
                    BufferedImage.TYPE_4BYTE_ABGR);  
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();  
            g2D.drawImage(imageIcon.getImage(), 0, 0,  
                    imageIcon.getImageObserver());  
            int alpha = 0;  
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage  
                    .getHeight(); j1++) {  
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage  
                        .getWidth(); j2++) {  
                    int rgb = bufferedImage.getRGB(j2, j1);  
                    if (colorInRange(rgb))  
                        alpha = 0;  
                    else  
                        alpha = 255;  
                    rgb = (alpha << 24) | (rgb & 0x00ffffff);  
                    bufferedImage.setRGB(j2, j1, rgb);  
                }  
            }  
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());  
            // 生成图片为PNG  
            String outFile = path.substring(0, path.lastIndexOf("."));  
            ImageIO.write(bufferedImage, "png", new File(outFile + ".png"));  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    public static OutputStream streamConvert(InputStream in) {  
    	// TODO Auto-generated constructor stub  
    	try {  
    		
    		BufferedImage image = ImageIO.read(in);  
    		ImageIcon imageIcon = new ImageIcon(image);  
    		BufferedImage bufferedImage = new BufferedImage(  
    				imageIcon.getIconWidth(), imageIcon.getIconHeight(),  
    				BufferedImage.TYPE_4BYTE_ABGR);  
    		Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();  
    		g2D.drawImage(imageIcon.getImage(), 0, 0,  
    				imageIcon.getImageObserver());  
    		int alpha = 0;  
    		for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage  
    				.getHeight(); j1++) {  
    			for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage  
    					.getWidth(); j2++) {  
    				int rgb = bufferedImage.getRGB(j2, j1);  
    				if (colorInRange(rgb))  
    					alpha = 0;  
    				else  
    					alpha = 255;  
    				rgb = (alpha << 24) | (rgb & 0x00ffffff);  
    				bufferedImage.setRGB(j2, j1, rgb);  
    			}  
    		}  
    		g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());  
    		// 生成图片为PNG  
    	
    				ByteArrayOutputStream out = new ByteArrayOutputStream();
    				ImageIO.write(bufferedImage, "PNG", out);
    				return out;
 
    	} catch (IOException e) {  
    		// TODO Auto-generated catch block  
    		e.printStackTrace();  
    	}
		return null;  
    }  
    
    public static boolean colorInRange(int color) {  
        int red = (color & 0xff0000) >> 16;  
        int green = (color & 0x00ff00) >> 8;  
        int blue = (color & 0x0000ff);  
        if (red >= color_range && green >= color_range && blue >= color_range)  
            return true;  
        return false;  
    }  
    public static int color_range = 210;  //设置这个图片值
    public static Pattern pattern = Pattern.compile("[0-9]*");  
  
    public static boolean isNo(String str) {  
        return pattern.matcher(str).matches();  
    }  
  
}  
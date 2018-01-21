package com.wode.common.util.image;



import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * 
 * @author mengkaixuan
 *
 */
public class ImageMarkLogoByIcon {
	  public static void main(String[] args) throws IOException { 
	         String srcImgPath = "D:/img/1353483737071.jpg"; 
	         String srcImgPath2 = "D:/img/1353485124758_38_50.jpg"; 
	         String srcImgPath3 = "D:/img/1353485124758_55_73.jpg"; 
	         String iconPath = "D:/img/水印花.jpg"; 
	         String big = "D:/img/big.jpg"; 
	         String center = "D:/img/center.jpg";
	         String small = "D:/img/small.jpg";
//	         String targerPath2 = "D:/img/bb.jpg"; 
	         // 给图片添加水印 
	         ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, big); 
	         ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath2, center); 
	         ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath3, small); 
	         // 给图片添加水印,水印旋转-45 
	/*         ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath2, 
	                 -45); */
	
    } 
	  
	     /**
	      * 给图片添加水印
	     * @param iconPath 水印图片路径
	     * @param srcImgPath 源图片路径
	     * @param targerPath 目标图片路径
	     */ 
	     public static void markImageByIcon(String iconPath, String srcImgPath, 
	             String targerPath) { 
	         markImageByIcon(iconPath, srcImgPath, targerPath, null); 
	     } 
	  
	     /**
	      * 给图片添加水印、可设置水印图片旋转角度
	     * @param iconPath 水印图片路径
	     * @param srcImgPath 源图片路径
	     * @param targerPath 目标图片路径
	     * @param degree 水印图片旋转角度
	     */ 
	     public static void markImageByIcon(String iconPath, String srcImgPath, 
	             String targerPath, Integer degree) { 
	         OutputStream os = null; 
	         try { 
	             Image srcImg = ImageIO.read(new File(srcImgPath)); 
	             BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), 
	                     srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
	             System.out.println(buffImg.getWidth()+"原图片的宽度");
	             // 得到画笔对象 
	             // Graphics g= buffImg.getGraphics(); 
	             Graphics2D g = buffImg.createGraphics(); 
	  
	             // 设置对线段的锯齿状边缘处理 
	             g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
	                     RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
	  
	             g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg 
	                     .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 
	  
	             if (null != degree) { 
	                 // 设置水印旋转 
	                 g.rotate(Math.toRadians(degree), 
	                         (double) buffImg.getWidth() / 2, (double) buffImg 
	                                 .getHeight() / 2); 
	             } 
	  
	             // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度 
	             ImageIcon imgIcon = new ImageIcon(iconPath); 
	             // 得到Image对象。 
	             Image img = imgIcon.getImage(); 
//	             System.out.println("imgwidth:"+img.getWidth((ImageObserver) srcImg));
	             float alpha = 0.5f; // 透明度 
	             g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 
	                     alpha)); 
	  
	             //一下代码是确定水印花的位置
	             Integer srcWidth = buffImg.getWidth(); //得到图片的宽度(你要在那个图片放水印花的图片  大图)
	             Integer srcHeigth = buffImg.getHeight();//得到大图的告诉
	             /*Integer iconWidth = imgIcon.getIconWidth();//得到小图的位置 也就是水印花的宽度
	             Integer iconHeigth = imgIcon.getIconHeight();//得到小图的高度 也就是水印花的宽度
	*/            

	           Integer iconWidth = srcWidth/4;//得到小图的位置 也就是水印花的宽度
	           Integer iconHeigth = srcHeigth/5;//得到小图的高度 也就是水印花的宽度
	           Integer x = (int) (srcWidth - iconWidth) ;//得到他要显示的位置x
	           Integer y = (int)(srcHeigth - iconHeigth) ;//得到显示的y坐标
	            
//	           g.drawImage(img, x-90, y-60, null); 
	           g.drawImage(img,  (int)(x-x*0.1), (int)(y-y*0.1), iconWidth, iconHeigth, null);  
	
	             g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
	  
	             g.dispose(); 
	  
	             os = new FileOutputStream(targerPath); 
	  
	             // 生成图片 
	             ImageIO.write(buffImg, "JPG", os); 
	  
	             System.out.println("图片完成添加Icon印章。。。。。。"); 
	         } catch (Exception e) { 
	             e.printStackTrace(); 
	         } finally { 
	             try { 
	                 if (null != os) 
	                     os.close(); 
	             } catch (Exception e) { 
	                 e.printStackTrace(); 
	             } 
	         } 
	     } 
}
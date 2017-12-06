package com.mingsheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("public")
public class PublicController {

    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = { "/getVerificationCode" }, method = RequestMethod.GET)
    public void paintCheckCode(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        // 指定生成的响应是图片
        response.setContentType("image/jpeg");
        int width = 110;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 创建BufferedImage类的对象
        Graphics g = image.getGraphics(); // 创建Graphics类的对象
        Graphics2D g2d = (Graphics2D) g; // 通过Graphics类的对象创建一个Graphics2D类的对象
        Random random = new Random(); // 实例化一个Random对象
        Font mFont = new Font("华文宋体", Font.BOLD, 30); // 通过Font构造字体
        g.setColor(getRandColor(200, 250)); // 改变图形的当前颜色为随机生成的颜色
        g.fillRect(0, 0, width, height); // 绘制一个填色矩形
        // 画一条折线
        BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // 创建一个供画笔选择线条粗细的对象
        g2d.setStroke(bs); // 改变线条的粗细
        g.setColor(Color.DARK_GRAY); // 设置当前颜色为预定义颜色中的深灰色
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        for (int j = 0; j < 3; j++) {
            xPoints[j] = random.nextInt(width - 1);
            yPoints[j] = random.nextInt(height - 1);
        }
        g.drawPolyline(xPoints, yPoints, 3);
        // 生成并输出随机的验证文字
        g.setFont(mFont);
        String sRand = "";
        int itmp = 0;
        for (int i = 0; i < 4; i++) {
            if (random.nextInt(2) == 1) {
                itmp = random.nextInt(26) + 65; // 生成A~Z的字母
            } else {
                itmp = random.nextInt(10) + 48; // 生成0~9的数字
            }
            char ctmp = (char) itmp;
            sRand += String.valueOf(ctmp);
            Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
            g.setColor(color);
            g.drawString(String.valueOf(ctmp), 30 * i-3 , 22);
        }
        // 将生成的验证码保存到Session中
        HttpSession session = request.getSession(true);
        session.setAttribute("verCode", sRand);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    /**
     * 获取随机颜色
     * @param s
     * @param e
     * @return
     */
    public Color getRandColor(int s, int e) {
        Random random = new Random();
        if (s > 255) {
            s = 255;
        }
        if (e > 255) {
            e = 255;
        }
        int r = s + random.nextInt(e - s);
        int g = s + random.nextInt(e - s);
        int b = s + random.nextInt(e - s);
        return new Color(r, g, b);
    }
}

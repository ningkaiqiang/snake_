package com.nkq.snake;

import java.net.URL;
import javax.swing.ImageIcon;

public class Data {
    // 存储数据
    // 顶部边栏
    public static URL haederurl = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(haederurl);
    // 头部
    public static URL upurl = Data.class.getResource("/statics/up.png");
    public static ImageIcon up = new ImageIcon(upurl);
    public static URL downurl = Data.class.getResource("/statics/down.png");
    public static ImageIcon down = new ImageIcon(downurl);
    public static URL lefturl = Data.class.getResource("/statics/left.png");
    public static ImageIcon left = new ImageIcon(lefturl);
    public static URL righturl = Data.class.getResource("/statics/right.png");
    public static ImageIcon right = new ImageIcon(righturl);
    // 身体部分
    public static URL bodyurl = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyurl);
    // 食物
    public static URL foodurl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodurl);
}

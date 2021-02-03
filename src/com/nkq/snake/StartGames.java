package com.nkq.snake;

import javax.swing.*;

public class StartGames{
    public static void main(String[] args) {
        // 绘制静态窗口
        JFrame frame = new JFrame("贪吃蛇Games");
        // 设置界面大小
        frame.setBounds(10, 10, 900, 725);
        // 设置不可随意切换窗口大小
        frame.setResizable(false);
        // 设置窗口可关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 绘制游戏面板
        frame.add(new GamePanel());
        // 使窗口可见
        frame.setVisible(true);
    }
}
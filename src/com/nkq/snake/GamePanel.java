package com.nkq.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private static final long serialVersionUID = -7366555274441064555L;


    // 设置数值
    int length;
    int foodX;
    int foodY;
    int score;
    String fx;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    Boolean isStart;
    Boolean isFail;
    Timer timer = new Timer(100, this);
    Random random = new Random();


    // 初始化数值
    public void init() {
        length = 3;
        score = 0;
        snakeX[0] = 100;snakeY[0] = 100;
        snakeX[1] = 75;snakeY[1] = 100;
        snakeX[2] = 50;snakeY[2] = 100;
        fx = "R";
        isStart = false;
        isFail = false;
        foodX = 25 + 25*random.nextInt(34);
        foodY = 75 + 25*random.nextInt(24);
    }


    // 构造器调用初始化函数
    public GamePanel() {
        init();
        this.setFocusable(true); // 设置可监听
        this.addKeyListener(this); // 添加监听
        timer.start();
    }


    // 画板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 清理屏幕
        this.setBackground(Color.DARK_GRAY);// 设置背景颜色
        // 绘制顶部广告栏目
        Data.header.paintIcon(this, g, 25, 11);
        // 绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        // 绘制静态🐍
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]); // 蛇的身体通过length控制
        }
        // 画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        g.setColor(Color.WHITE);
        g.setFont(new Font("pingfang", Font.BOLD, 15));
        g.drawString("score:"+score+" size:"+length, 715, 40);

        if (!isStart) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("pingfang", Font.BOLD, 40));
            g.drawString("Press space bar to START", 200, 300);
        }

        if(isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("苹方", Font.BOLD, 40));
            g.drawString("Game Over !!", 300, 300);
        }
    }


    // 监听键盘事件
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(isFail){
                isFail = false;
                init();
            }else{
                isStart = !isStart;
            }
            repaint();
        }
        // 方向判定
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            fx = "L";
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            fx = "R";
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            fx = "U";
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            fx = "D";
        }
    }


    // 定时器，监听事件，帧 执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && !isFail){
            for(int i = length-1;i > 0;i--){ // 身体向前移动
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }

            // 撞墙判定
            if(fx.equals("R")){
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            }else if(fx.equals("L")){
                snakeX[0] = snakeX[0] - 25;
                if(snakeX[0] < 25){
                    snakeX[0] = 850;
                }
            }else if (fx.equals("U")){
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 75){
                    snakeY[0] = 650;
                }
            }else if (fx.equals("D")){
                snakeY[0] = snakeY[0] + 25;
                if(snakeY[0] > 650){
                    snakeY[0] = 75;
                }
            }

            if(snakeX[0]==foodX && snakeY[0]==foodY){
                // 增加长度
                length++;
                // 增加分数
                score = score + 10;
                // 重新生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }

            for(int i = 1;i < length;i++){
                if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    // 失败判定
                    isFail = true;
                }
            }

            repaint();
        }
        timer.start();
    }



    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

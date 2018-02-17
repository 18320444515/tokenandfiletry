package com.yangchun.tokenandfiletry.utils;

import java.util.Scanner;

/**
 * @author tianyi
 * @date 2018-02-17 17:21
 */
public class TempUtil {

    static class Point {      //创建链表结点类
        private char Alpha;  //存数据
        private Point Next;  //存下个结点
        Point(char alpha,Point next){  //构造方法赋初值
            this.Alpha=alpha;
            this.Next=next;
        }
        //getter+setter
        public char getAlpha() {
            return Alpha;
        }
        public void setAlpha(char alpha) {
            Alpha = alpha;
        }
        public Point getNext() {
            return Next;
        }
        public void setNext(Point next) {
            Next = next;
        }
    }

    static public void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("以空格分隔，请在下方输入行数n和长度m:");
            Integer n = scanner.nextInt();
            Integer m = scanner.nextInt();   //获取n和m
            //System.out.println("n="+n+",m="+m);
            if (n<=0||m<=0){
                System.out.println("无内容，请重新输入！");
                continue;
            }
            char Q[] = new char[m];
            Point startPoint = new Point('A', null);  //初始点
            Point head = startPoint;  //临时起始点
            Q[0]='A';  //与链表同步，把‘A’存进去
            for (int i = 1; i<m ; i++) {  //注意A点已经存储，从B开始处理
                Q[i] = (char) ('A' + i);
                head.setNext(new Point(Q[i],null));
                head=head.getNext();
            }head.setNext(startPoint);  //最后将普通链表首尾相接成一个循环链表

            for (int i=0;i<n;i++){  //输出n行
                //startPoint=head; //初始化startPoint
                if (i==0) {  //A点情况特殊，无能为力，只好另定义一种情况
                    do {
                        head = head.getNext();
                    } while (head.getAlpha() != Q[0] && head != startPoint); //获取当行的起始点
                }else {
                    do {
                        head = head.getNext();
                    } while (head.getAlpha() != Q[m - i ] && head != startPoint); //获取当行的起始点
                }
                startPoint=head;  //更新当前行的起始点
                do {
                    System.out.printf(String.valueOf(head.getAlpha()));
                    head=head.getNext();
                }while (head!=startPoint);
                System.out.println();
            }
            System.out.println("本次体验结束\n\n");
        }
    }
}

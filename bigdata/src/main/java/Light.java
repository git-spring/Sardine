
/*
 *  @author:   liudw
 *  @date:  2020-10-12
 */

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * 长时间无动作，鼠标自动点击
 */
public class Light {
    public static void main(String[] args) throws AWTException, InterruptedException {

        System.out.println("此窗口勿关闭...");

        while (true) {
            if (mouseInfo()) {
                try {

                    Robot robot = new Robot();
                    Random random = new Random();
                    int a = 0;
                    robot.delay(500);
                    robot.mouseMove(1400, 1050);
                    a = Math.abs(random.nextInt()) % 100 + 10;
                    robot.delay(a);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    getCurrentTime();
                    a = Math.abs(random.nextInt()) % 50 + 10;
                    robot.delay(a);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static Boolean mouseInfo() throws InterruptedException {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point location = pointerInfo.getLocation();
        double x = location.getX();
        double y = location.getY();

        Thread.sleep(120000);
        PointerInfo pointerInfo1 = MouseInfo.getPointerInfo();
        Point location1 = pointerInfo1.getLocation();
        double x1 = location1.getX();
        double y1 = location1.getY();

        // 判断鼠标位置
        if (x == x1 | y == y1) {
            return true;
        }
        return false;
    }

    public static void getCurrentTime(){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(cal.getTime());
        System.out.println(format);
    }

}
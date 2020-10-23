
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
import java.util.Random;

/**
 *  长时间无动作，鼠标自动移位
 */
public class Light {
	public static void main(String[] args) throws AWTException, InterruptedException {

		System.out.println("此窗口勿关闭...");

		while (true) {
			if (!mouseInfo()) {
				try {

					Robot robot = new Robot();
					Random random = new Random();
					int a = 0;
					robot.delay(3000);
					robot.mouseMove(1200, 700);
					a = Math.abs(random.nextInt()) % 100 + 50;
					robot.delay(a);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					a = Math.abs(random.nextInt()) % 50 + 50;
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
			return false;
		}
		return true;
	}

}
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * @description:
 * @author: Netter
 * @date: 2022-05-04 21:27
 */
public class sendMsg {

    public static void main(String[] args) throws AWTException {
        String sentence = "浅淡世界，伴随最后的词语，静静地绽放散落";// 定义要发送的话
        Robot robot = new Robot();// 创建Robot对象
        robot.delay(3000);//预留出打开窗口的时间
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        //String[] authors = sentence.split("[,]");// 字符串根据,分割
        for (int j = 0;j<45; j++) {//循环次数
            //for (int i = 0; i < authors.length; i++) {
            //    String sentencet = authors[i];
                String sentencet = sentence;
                    Transferable tText = new StringSelection(sentencet);
                clip.setContents(tText, null);
                // 按下了ctrl+v，完成粘贴功能
                robot.keyPress(KeyEvent.VK_CONTROL);// 按下Control键
                robot.keyPress(KeyEvent.VK_V);// 按下V键
                robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键
                //robot.delay(5300);// 延迟一秒再发送
                robot.delay(6000);// 延迟一秒再发送
                robot.keyPress(KeyEvent.VK_ENTER);// 回车
            //}
        }
    }


}

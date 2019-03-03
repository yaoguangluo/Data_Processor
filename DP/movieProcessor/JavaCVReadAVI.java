package javacv;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.FrameGrabber.Exception;
public class JavaCVReadAVI extends JPanel{
	public void run() throws Exception, InterruptedException {
		FFmpegFrameGrabber ffmpegFrameGrabber = FFmpegFrameGrabber.createDefault("C:/Users/Administrator/Desktop/deta/detasource/videoProcess/webwxgetvideo.avi");
		ffmpegFrameGrabber.start();
		int fflength = ffmpegFrameGrabber.getLengthInFrames();
		int maxStamp = (int) (ffmpegFrameGrabber.getLengthInTime()/1000000);
		int count = 0;
		while (true) {
			Frame nowFrame = ffmpegFrameGrabber.grabImage();
			int startStamp = (int) (ffmpegFrameGrabber.getTimestamp() * 1.0/1000000);
			double present = (startStamp * 1.0 / maxStamp) * 100;
			if (nowFrame == null) {
				System.out.println("!!! Failed cvQueryFrame");
				continue;
			}
			Java2DFrameConverter paintConverter = new Java2DFrameConverter();
			BufferedImage difImage = paintConverter.getBufferedImage(nowFrame, 1);
			paint(difImage);
			Thread.sleep(25);
		}
	}
	public static void main(String[] argv) throws Exception, InterruptedException{
		Simple t = new Simple();
		t.setVisible(true);
		t.setPreferredSize(new Dimension(800,300));
		JFrame fr = new JFrame();
		fr.setSize(800, 600);
		fr.add(t);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.run();		
	}
	@SuppressWarnings("unused")
	public void paint(Image img2){
		Graphics g = this.getGraphics();
		Graphics2D gg = (Graphics2D)g;
		g.drawImage(img2, 0, 0,300, 600,this);
	}
}

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RangePanel extends JFrame {

	public class DrawPanel extends JPanel {

		RTC.RangeData rangeData;
		double rangeMax;
		
		Point center;
		double pixel_to_meter;
		double maxRange;
		
		public DrawPanel() {
			super();
			rangeMax = 10.0; // 10 m
			center = new Point(0, 0);
		}

		public void setRangeData(RTC.RangeData rangeData) {
			this.rangeData = rangeData;
		}

		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			Dimension d = super.getSize();
			g2d.drawLine(0, d.height / 2, d.width, d.height / 2);
			g2d.drawLine(d.width / 2, 0, d.width / 2, d.height);

			maxRange = -100;
			int maxIndex = -1;
			for (int i = 0; i < rangeData.ranges.length; i++) {
				if (rangeData.ranges[i] > maxRange) {
					maxRange = rangeData.ranges[i];
					maxIndex = i;
				}
			}

			center.x = d.width/2;
			center.y = d.height/2;
			if (d.width > d.height) {
				pixel_to_meter = rangeMax / d.width * 2;
			} else {
				pixel_to_meter = rangeMax / d.height * 2;
			}

			for (int i = 0; i < rangeData.ranges.length; i++) {
				double angle = rangeData.config.minAngle
						+ rangeData.config.angularRes * i;
				plot(g2d, angle, rangeData.ranges[i]);
			}
		}

		private void plot(Graphics2D g2d, double angle, double distance) {
			//Point start = center;
			double x = distance * Math.cos(-angle) / pixel_to_meter + center.x;
			double y = distance * Math.sin(-angle) / pixel_to_meter + center.y;
			g2d.drawLine(center.x, center.y, (int)x, (int)y);
		}
	}

	DrawPanel contentPane;

	public RangePanel() {
		super("Range Panel");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new DrawPanel();
		setContentPane(contentPane);
	}

	public void setRangeData(RTC.RangeData rangeData) {
		contentPane.setRangeData(rangeData);
		repaint();
	}

	public static void main(String[] arg) {
		RangePanel p = new RangePanel();
		p.setVisible(true);

		RTC.RangeData range = new RTC.RangeData(new RTC.Time(), new double[100], 
				new RTC.RangerGeometry(), new RTC.RangerConfig());
		range.config.angularRes = Math.PI / 100;
		range.config.minAngle = -90 / 180.0 * Math.PI;
		range.config.maxAngle = +90 / 180.0 * Math.PI;
		//range.ranges = new double[100];
		for (int i = 0; i < range.ranges.length; i++) {
			range.ranges[i] = 5.0;
		}

		p.setRangeData(range);
	}
}

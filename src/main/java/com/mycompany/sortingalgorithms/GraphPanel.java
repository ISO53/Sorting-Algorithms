package com.mycompany.sortingalgorithms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Yusuf Ihsan Simsek
 */
public class GraphPanel extends JPanel {

    private final int BASIC_ARRAY_SIZE = 10;
    
    private ArrayList<double[]> data;  // double[] = {X, Y} and X is loop counts, Y is seconds
    private ArrayList<double[]> pointLocations;
    private final Color graphColor;
    private Color lineColor;
    private boolean isBasic;

    public GraphPanel() {
        data = new ArrayList<>();
        pointLocations = new ArrayList<>();
        graphColor = new Color(204, 204, 204);
        isBasic = false;
        this.setBackground(new Color(51, 51, 51));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        drawGraphInformations(g2D);

        drawGraphPoints(g2D);

        drawGraphLine(g2D);
        
        drawGraphCorner(g2D);

        g2D.dispose();
    }

    public void drawGraphPoints(Graphics2D g2D) {
        int loop = data.isEmpty() ? 0 : data.size();
        g2D.setColor(lineColor);
        if (!isBasic) {
            for (int i = 0; i < loop; i++) {
                g2D.fillOval((int) (pointLocations.get(i)[0] - 4), (int) (pointLocations.get(i)[1] - 4), 8, 8);
            }
        }
    }

    public void drawGraphLine(Graphics2D g2D) {
        int xPoints[] = getXPoints();
        int yPoints[] = getYPoints();

        int thickness = 3;
        int alpha = 254;

        for (int j = 0; j < 7; j++) {
            g2D.setColor(new Color(lineColor.getRed(), lineColor.getGreen(), lineColor.getBlue(), alpha));
            g2D.setStroke(new BasicStroke(thickness));
            g2D.drawPolyline(xPoints, yPoints, xPoints.length);

            alpha = ((alpha == 254) ? 15 : (alpha - 2));
            thickness += 3;
        }
    }

    public void drawGraphInformations(Graphics2D g2D) {

        int height = this.getParent().getHeight();
        int width = this.getParent().getWidth();

        int loop = data.isEmpty() ? 0 : data.size();

        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(graphColor);
        g2D.drawLine(50, 50, 50, height - 50);
        g2D.drawLine(50, height - 50, width - 50, height - 50);

        if (isBasic) {
            double biggestDataX = biggestX(data);
            double biggestPointX = biggestX(pointLocations);
            
            double biggestDataY = biggestY(data);
            double biggestPointY = biggestY(pointLocations);
            
            for (int i = 1; i < BASIC_ARRAY_SIZE + 1; i++) {
                g2D.setColor(graphColor);
                
                int ptX = (int) (biggestPointX - (biggestPointX / BASIC_ARRAY_SIZE) * i) + 50;
                int ptY = (int) (biggestPointY - (biggestPointY / BASIC_ARRAY_SIZE) * i) + 50;
                
                double dtX = biggestDataX - (biggestDataX / (double) BASIC_ARRAY_SIZE) * (double) i;
                double dtY = biggestDataY / (double) BASIC_ARRAY_SIZE * (double) i;
                
                dtY = Double.valueOf(new DecimalFormat("###.#####").format(dtY).replace(',', '.'));
                
                g2D.drawLine(ptX, height - 50 + 5, ptX, height - 50 - 5);
                g2D.drawLine(50 + 5, ptY, 50 - 5, ptY);

                g2D.setColor(lineColor);
                g2D.drawString(dtX + "", ptX - 10, height - 50 + 5 + 20);
                g2D.drawString(dtY + "", 50 + 10 - 60, ptY + 5);
            }
        } else {
            for (int i = 0; i < loop; i++) {
                
                int ptX = (int) pointLocations.get(i)[0];
                int ptY = (int) pointLocations.get(i)[1];
                
                g2D.setColor(graphColor);
                g2D.drawLine(ptX, height - 50 + 5, ptX, height - 50 - 5);
                g2D.drawLine(50 + 5, ptY, 50 - 5, ptY);

                g2D.setColor(lineColor);
                g2D.drawString(data.get(i)[0] + "", ptX - 10, height - 50 + 5 + 20);
                g2D.drawString(data.get(i)[1] + "", 50 + 5 - 60, ptY + 5);
            }
        }
    }
    
    public void drawGraphCorner(Graphics2D g2D) {
        int height = this.getParent().getHeight();
        g2D.setColor(graphColor);
        g2D.fillOval(50 - 5, height - 50 - 5, 10, 10);
    }

    public void arrangePointLocations() {
        int height = this.getParent().getHeight();
        int width = this.getParent().getWidth();

        int lineWidth = width - 100;
        int lineHeight = height - 100;

        double xGap = (lineWidth - 20) / sumX(data) * ((sumX(data) / biggestX(data)));
        double yGap = (lineHeight - 20) / sumY(data) * ((sumY(data) / biggestY(data)));

        pointLocations.clear();

        for (int i = 0; i < data.size(); i++) {
            double X = 50 + data.get(i)[0] * xGap;
            double Y = height - 50 - data.get(i)[1] * yGap;
            pointLocations.add(new double[]{X, Y});
        }
    }

    public void printArray() {
        for (double[] d : data) {
            System.out.println(d[0] + " " + d[1]);
        }
    }

    public int[] getXPoints() {
        int xPoints[] = new int[pointLocations.size()];
        for (int i = 0; i < pointLocations.size(); i++) {
            xPoints[i] = (int) pointLocations.get(i)[0];
        }
        return xPoints;
    }

    public int[] getYPoints() {
        int yPoints[] = new int[pointLocations.size()];
        for (int i = 0; i < pointLocations.size(); i++) {
            yPoints[i] = (int) pointLocations.get(i)[1];
        }
        return yPoints;
    }

    public double sumX(ArrayList<double[]> arrayList) {
        double sum = 0;
        for (double[] list : arrayList) {
            sum += list[0];
        }
        return sum;
    }

    public double sumY(ArrayList<double[]> arrayList) {
        double sum = 0;
        for (double[] list : arrayList) {
            sum += list[1];
        }
        return sum;
    }

    public double biggestX(ArrayList<double[]> arrayList) {
        double biggest = 0;
        for (double[] list : arrayList) {
            if (biggest < list[0]) {
                biggest = list[0];
            }
        }
        return biggest;
    }

    public double biggestY(ArrayList<double[]> arrayList) {
        double biggest = 0;
        for (double[] list : arrayList) {
            if (biggest < list[1]) {
                biggest = list[1];
            }
        }
        return biggest;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setData(ArrayList<double[]> data) {
        this.data = data;
    }

    public void setIsBasic(boolean isBasic) {
        this.isBasic = isBasic;
    }
}

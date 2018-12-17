

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Target {

	   static int score = 0;

	   int n = 0;
	   Graphics g1;
	   JFrame frame;
	   String gameName = "Target game";

	   Random random = new Random();

	   // âũ��
	   int WIDTH = 800;
	   int HEIGHT = 600;

	   // target ������
	   int speed = 30;

	   // target �̵� �ӵ� ����
	   int dx = 5;
	   int dy = -5;

	   // target ũ��
	   int x = 0;
	   int y = 200;

	   int radius = 50; // ������

	   // Ŭ���ϴ� ���� ��ǥ
	   int mx = 0;
	   int my = 0;

	   // �� �߽� ��ǥ������ �� ����
	   int fx = 0;
	   int fy = 0;
	   // (0 0)
	   JLabel label = new JLabel("Score = " + String.valueOf(score));

	   // (100,100)
	   public Target() {

	      
	      
	      frame = new JFrame(gameName); // ������ܿ� ���� �̸� ��������
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      // layout����
	      frame.setLayout(new BorderLayout());
	      frame.add(label, BorderLayout.NORTH);
	      label.setFont(new Font("Defualt", Font.BOLD, 30));
	      frame.getContentPane().add(new MyPanel(), BorderLayout.CENTER);

	      // ���콺 Ŭ��
	      frame.addMouseListener(new MyListener());

	      // ������ ũ�� ����
	      frame.setSize(WIDTH, HEIGHT);
	      // �����ֱ�
	      frame.setVisible(true);

	   }

	   public void start() {

	      while (true) {

	         // �߽��� x,y�� ���� ��� ����
	         fx = x + radius;
	         fy = y + radius;

	         // if (((x < 0) || (x > WIDTH - radius * 2)) || ((y < 0) || (y > HEIGHT - radius
	         // * 2)))
	         // break;

	         // ���� ������ ������ ������ ���� ��ŭ �� �����ٰ� ƨ�ܳ���
	         if ((x + dx > WIDTH - radius * 2) || x + dx < 0)
	            dx = -dx;
	         if ((y + dy > HEIGHT - radius * 2) || y + dy < 0)
	            dy = -dy;

	         // �����̰� ��(dx,dy�� ���� ��� ����)
	         x += dx;
	         y += dy;

	         frame.repaint();

	         try {
	            Thread.sleep(speed);
	         } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }

	   }

	   private class MyListener implements MouseListener {
	      JFrame j = frame; // ��� ��� ���� ���� ������

	      @Override
	      public void mouseClicked(MouseEvent mouse) {
	         // TODO Auto-generated method stub

	      }

	      @Override
	      public void mousePressed(MouseEvent mouse) {
	         // ���콺 ��������
	         
	         // ���콺�� ������ ��ǥ
	         mx = (int) mouse.getX();
	         my = (int) mouse.getY() - 30;

	         // if( ((x-radius)<=mx|(mx<=x+radius))&&((y-radius)<=my|(my<=y+radius)) ) {
	         if (x <= mx && mx <= fx + radius && y <= my && my <= fy + radius) {
	            // x <= mx <= fx + radius���콺�� ���� x��ǥ�� ���ȿ� �ִٸ�
	            // y <= my <= fy + radius���콺�� ���� y��ǥ�� ���ȿ� �ִٸ�

	            // ���� ��Ÿ���� ���� ��ǥ
	            x = random.nextInt(WIDTH - 100);
	            y = random.nextInt(HEIGHT - 100);

	            //�����̻� ���� ���ų� �۾����� ����
	            if (radius < 17)
	               radius = 16;
	            else
	               radius -= 1.5;

	            if (speed < 4)
	               speed = 4;
	            else
	               speed -= 1.8;

	            // �����ǿ� ������ �������� ������
	            label.setText("Score = " + String.valueOf(++score));

//	            System.out.println("mx:" + mx + " " + "my:" + my);
//	            System.out.println("x:" + fx + " " + "y:" + fy);
//	            System.out.println();
	            
	         //������ ����ȭ���� ������ ���   
	         } else {
	            //+++++++++++++++++++++++++++++++++++++++++++++++++++++
	            //n�� 1�� �Ǹ� ���
	            if (n == 1)
	               return;
	            try {
	               ++n;  //�ٸ��� �������� n�� ���� �����ش�
	               Button but1 = new Button("����");
	               Button but2 = new Button("����");

	               // ��ư�� ������� ��ġ-------------------------------
	               frame.setLayout(new FlowLayout());
	               frame.add(but1);
	               frame.add(but2);

	               frame.setVisible(true);

	               dx = 0;
	               dy = 0;

	               Thread.sleep(1000);

	               but1.addActionListener(new ActionListener() {

	                  @Override
	                  public void actionPerformed(ActionEvent e) {
	                     if (e.getSource() == but1) {
	                        //++++++++++++++++++++++++++++++++++++++++++++++++++++
	                        //���� ���������� n=0�� ���ش� 
	                        n = 0;
	                        //���� ��ư ������ ��ư ���� ���� ��ư ����
	                        frame.remove(but1);
	                        frame.remove(but2);
	                        //������ ��ǥ �ӵ� ũ�� ���� �ʱ�ȭ
	                        radius = 50;
	                        dx = 5;
	                        dy = -5;
	                        x = 0;
	                        y = 100;
	                        speed = 30;
	                        
	                        // ������Ҷ� ���� 0���� �ʱ�ȭ�ؼ� ������
	                        label.setText("Score = "+String.valueOf(0));
	                        //�׸���
	                        frame.setContentPane(new MyPanel());
	                        frame.repaint();
	                        // ���̾ƿ�
	                        frame.add(label, BorderLayout.NORTH);

	                        frame.setVisible(true);
	                        score = 0;// score reset

	                     }
	                  }
	               });
	               //������ư ��������
	               but2.addActionListener(new ActionListener() {

	                  @Override
	                  public void actionPerformed(ActionEvent e) {
	                     if (e.getSource() == but2) {
	                        System.exit(0);//�����Լ�
	                     }
	                  }
	               });

	            } catch (Exception e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	         }

	      }

	      @Override
	      public void mouseReleased(MouseEvent e) {
	         // TODO Auto-generated method stub

	      }

	      @Override
	      public void mouseEntered(MouseEvent e) {
	         // TODO Auto-generated method stub

	      }

	      @Override
	      public void mouseExited(MouseEvent e) {
	         // TODO Auto-generated method stub

	      }

	   }

	   private class MyPanel extends JPanel {
	   
	      public void paintComponent(Graphics g) {
	         
	         super.paintComponent(g);
	         //����
	         g.setColor(Color.RED);
	         //ä���
	         g.fillOval(x, y, radius * 2, radius * 2);

	      }
	   }

	}
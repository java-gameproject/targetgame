

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

	   // 창크기
	   int WIDTH = 800;
	   int HEIGHT = 600;

	   // target 빠르기
	   int speed = 30;

	   // target 이동 속도 조절
	   int dx = 5;
	   int dy = -5;

	   // target 크기
	   int x = 0;
	   int y = 200;

	   int radius = 50; // 반지름

	   // 클릭하는 곳의 좌표
	   int mx = 0;
	   int my = 0;

	   // 원 중심 좌표값으로 쓸 변수
	   int fx = 0;
	   int fy = 0;
	   // (0 0)
	   JLabel label = new JLabel("Score = " + String.valueOf(score));

	   // (100,100)
	   public Target() {

	      
	      
	      frame = new JFrame(gameName); // 좌측상단에 게임 이름 나오게함
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      // layout설정
	      frame.setLayout(new BorderLayout());
	      frame.add(label, BorderLayout.NORTH);
	      label.setFont(new Font("Defualt", Font.BOLD, 30));
	      frame.getContentPane().add(new MyPanel(), BorderLayout.CENTER);

	      // 마우스 클릭
	      frame.addMouseListener(new MyListener());

	      // 프레임 크기 지정
	      frame.setSize(WIDTH, HEIGHT);
	      // 보여주기
	      frame.setVisible(true);

	   }

	   public void start() {

	      while (true) {

	         // 중심점 x,y에 의해 계속 변함
	         fx = x + radius;
	         fy = y + radius;

	         // if (((x < 0) || (x > WIDTH - radius * 2)) || ((y < 0) || (y > HEIGHT - radius
	         // * 2)))
	         // break;

	         // 원의 지름을 빼주지 않으면 지름 만큼 더 나갓다가 튕겨나옴
	         if ((x + dx > WIDTH - radius * 2) || x + dx < 0)
	            dx = -dx;
	         if ((y + dy > HEIGHT - radius * 2) || y + dy < 0)
	            dy = -dy;

	         // 움직이게 함(dx,dy로 인해 계속 변함)
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
	      JFrame j = frame; // 없어도 상관 없는 문장 같은데

	      @Override
	      public void mouseClicked(MouseEvent mouse) {
	         // TODO Auto-generated method stub

	      }

	      @Override
	      public void mousePressed(MouseEvent mouse) {
	         // 마우스 눌렀을때
	         
	         // 마우스가 누르는 좌표
	         mx = (int) mouse.getX();
	         my = (int) mouse.getY() - 30;

	         // if( ((x-radius)<=mx|(mx<=x+radius))&&((y-radius)<=my|(my<=y+radius)) ) {
	         if (x <= mx && mx <= fx + radius && y <= my && my <= fy + radius) {
	            // x <= mx <= fx + radius마우스로 누른 x좌표가 원안에 있다면
	            // y <= my <= fy + radius마우스로 누른 y좌표가 원안에 있다면

	            // 원이 나타나는 랜덤 좌표
	            x = random.nextInt(WIDTH - 100);
	            y = random.nextInt(HEIGHT - 100);

	            //일정이상 빨라 지거나 작아지면 유지
	            if (radius < 17)
	               radius = 16;
	            else
	               radius -= 1.5;

	            if (speed < 4)
	               speed = 4;
	            else
	               speed -= 1.8;

	            // 점수판에 점수를 증가시켜 보여줌
	            label.setText("Score = " + String.valueOf(++score));

//	            System.out.println("mx:" + mx + " " + "my:" + my);
//	            System.out.println("x:" + fx + " " + "y:" + fy);
//	            System.out.println();
	            
	         //원말고 바탕화면을 눌렀을 경우   
	         } else {
	            //+++++++++++++++++++++++++++++++++++++++++++++++++++++
	            //n이 1이 되면 대기
	            if (n == 1)
	               return;
	            try {
	               ++n;  //다른거 눌렀을때 n을 증가 시켜준다
	               Button but1 = new Button("시작");
	               Button but2 = new Button("정지");

	               // 버튼을 순서대로 배치-------------------------------
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
	                        //원을 눌렀을때는 n=0을 해준다 
	                        n = 0;
	                        //시작 버튼 누르면 버튼 시작 정지 버튼 삭제
	                        frame.remove(but1);
	                        frame.remove(but2);
	                        //반지름 좌표 속도 크기 변수 초기화
	                        radius = 50;
	                        dx = 5;
	                        dy = -5;
	                        x = 0;
	                        y = 100;
	                        speed = 30;
	                        
	                        // 재시작할때 점수 0으로 초기화해서 보여줌
	                        label.setText("Score = "+String.valueOf(0));
	                        //그리기
	                        frame.setContentPane(new MyPanel());
	                        frame.repaint();
	                        // 레이아웃
	                        frame.add(label, BorderLayout.NORTH);

	                        frame.setVisible(true);
	                        score = 0;// score reset

	                     }
	                  }
	               });
	               //정지버튼 눌렀을때
	               but2.addActionListener(new ActionListener() {

	                  @Override
	                  public void actionPerformed(ActionEvent e) {
	                     if (e.getSource() == but2) {
	                        System.exit(0);//정지함수
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
	         //색갈
	         g.setColor(Color.RED);
	         //채우기
	         g.fillOval(x, y, radius * 2, radius * 2);

	      }
	   }

	}
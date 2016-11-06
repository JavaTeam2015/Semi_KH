package A0_cheol;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;






public class PuyoFrametest extends JFrame // ��ü �г� �Դϴ�

{
   PuyoPanel myPanel;        // �гβ�����°�
   
   public PuyoFrametest()//������ �Դϴ�.JF��
   { super("dd?");//�̸�
   setBounds(20, 20, 920, 690);//ũ�� ��ǥ
   setLayout(null);
   makeGUI();
   setVisible(true);
   }  


   void makeGUI()// �� �޼ҵ�� ���� �÷����� JP���� ��� �ϱ� ���ؼ� ��������ϴ�
   {
      Container c = getContentPane();  // �����̳� =>�� ����  �����̰� ���� �����  ������ �ȿ� �ֱ� ���� �����.

      myPanel = new PuyoPanel();// JP�� �ÿ� �ϱ� ���ؼ� new �� ���� �ߴ�.
      myPanel.setBounds(5,20,280,600);// �÷���â�� ����� ũ�� �� ��ǥ
      
      c.add(myPanel, "Center");
      //�������̳ʸ� ����ؼ� ����� ���̰� �װ� ����� �ְٴ� �� ����.


   }
   ///////////////////////////////////////////////////////////////
   //�Ǵٸ� �ξ� Ŭ����.
   class Block//�� Ŭ������ ������ְ�.
   {

      int  centerX,centerY;// ���� x,y�� �� ��ǥ�� ���� �ϱ����ؼ�.
      int radius;//����!>_<
      Color color;    //Į��.
   
      Block(int centerX, int centerY, int radius, Color color)
      {// �����ڸ� ����� �� �����ھȿ��� 4������ �����͸� �ְ� �Ѵ�.
         this.centerX= centerX;   
         this.centerY= centerY;
         this.radius = radius;
         this.color  = color;     


      }


      public void paint(Graphics g)//����Ʈ �޼ҵ� ����� �׷����� �Ѵ�.
      {
         g.setColor(color);//���� ���� ����.
         g.fillOval(centerX-radius,centerY-radius, 2*radius,2*radius);
         //���� �׸��� �� �׸���  �װͿ� ��ġ�� ����.
      }
   }

/////////////////////////////////////////////////////////////////////////////////////
   //�������� ����
   //������ �̵�
   class BlockPipe{//


      LinkedList blocks;// ��ũ�� ����Ʈ�� �����
      public void paint(Graphics g){
         blocks= new LinkedList();// �����ڸ� ����� ������ ���κ� ���Ǹ� ���ش�.
         Iterator itr= blocks.iterator();

         while(itr.hasNext()) // d���� ���������鼭 ���� �����ش�.
         {
   
            Object element= itr.next();// ������Ʈ ���� �����. itr.next �������� ������´�.
            Block b = (Block) element;//���� b ��  ���������� ������ȯ �����ػ��·� element�� �ٲ㼭 �־��ش�.
            b.paint(g);//  �� b��  �׷������� �̾��ؤ�
   
         }
      }

//      public int getSize()
//      {
//         return blocks.size();
//      }
   }
   
   //����� ¦ a,b
   class BlockPair{// ���� Ŭ������ ���� 

      Block blockA;  // �� ũ�� �� ������ġ�� ����� ���� 1�� ��
      Block blockB;// ���� ���� 2�� ��
   
      
      public void paint(Graphics g)
      {
         blockA.paint(g);// ���� �ִ� ������ ��������
         blockB.paint(g);// ���� �ִ� ������ ��������
      }

   }

   interface GameParameters{
 
      int width=252;// ���� �÷��� JP �����  ũ��
      int height=width*2+0;//����ũ�� * 2 �Ѱ�.
      int numpi = 4;
      Color colors[]={Color.RED,Color.BLUE,Color.GREEN, Color.YELLOW};// �� ��ü��  ������.
      //���� �����̴� ��
      int RIGHT=1,LEFT=2,DOWN=3, UP=4;
      //������ ���ڵ�.
      
   }

   public class PuyoGame  implements GameParameters {
	   // ������  �������̽� ���� ���� ������� ��� �ϱ� ���ؼ�,
      BlockPair   currentpair;   // ���1,2, ������ ���� ���� ������ ���� ���ؼ�.
      BlockPipe   pipes[];       // // ������ ���� �� �������� �迭�� ����.
      long   score=0;// �� ���·� �ް� score�� �ʱⰪ 0�� �ش�.


      public void StartGame()//������ ��ŸƮ
      {

         currentpair =new BlockPair();//1,2 ���� ���Ӱ� ���Ǹ� �ؼ� currentpair   �������ش�.
         pipes =new BlockPipe[numpi];//�� �������迭�� ����ϰ� �׾ȿ� numpi(4)���� ���� �������.
         //�װ� pipes ��� �Ұ��̴�.

         for(int i =0;i<numpi;++i)// 4���� ������ 
            pipes[i]=  new BlockPipe();//        

         ReleasePair();//�޼ҵ�� ���� ����.

      }


      public void ReleasePair()
      {

         Random r= new Random();//�������� ����� r��

         int color_index=r.nextInt(4); //������ ���� 4������ �� �迭��  �������� �ְ�
         // �װ� int ���׷� �ٲ㼭 �ش�.
         Block blockA  = // A���� ũ�� �� ��
               new Block(70, 20 ,23, colors[color_index]); 

         int color_index2=r.nextInt(4); //2��° ���� ��

         Block blockB = //�ι��� ���� ũ�� �פ� ��.
               new Block(70,65, 23, colors[color_index2]);


         currentpair.blockA =blockA;//������ ���� ������.
         currentpair.blockB=blockB;
      }    
//      A0.PuyoFrametest$Block@7530d0a???
//      A0.PuyoFrametest$Block@27bc2616???

      public void Render(Graphics g)
      {
         g.setColor(Color.BLACK);

         currentpair.paint(g);

         for(int i =0;i<numpi;++i)
            pipes[i].paint(g);     

      }         


      boolean MoveBlock(Block blk){


         blk.centerY+=1;

         return false;

      }

      //���� blockPair ����
      void MovePair(){


         //����� �����δ�
         boolean ba = MoveBlock(currentpair.blockA);
         boolean bb = MoveBlock(currentpair.blockB); 
         
      }


      public void ProcessKey(KeyEvent e){


         int key= e.getKeyCode();

         switch(key){


         case KeyEvent.VK_LEFT:
            MovePairTo(LEFT);
            break;

         case KeyEvent.VK_RIGHT:
            MovePairTo(RIGHT);
            break;

         case KeyEvent.VK_UP:
            
            break;

         case KeyEvent.VK_DOWN:
            MovePairTo(DOWN);
            break;     

         case KeyEvent.VK_SPACE:
            break;     

         }                           


      }



   void MovePairTo(int dir){

         switch(dir)
         {

         case DOWN:
         {

            currentpair.blockA.centerY+=4;
            currentpair.blockB.centerY+=4;
            break;
         }

         case LEFT :
         {

            currentpair.blockA.centerX-=4;
            currentpair.blockB.centerX-=4;
            break;
         }   

         case RIGHT : 
         {

            currentpair.blockA.centerX+=4;
            currentpair.blockB.centerX+=4;
            break;
         }
         }
      }

   }
   
   
////////////////////////////////////////////////////////////////////////////
   class PuyoPanel extends JPanel implements Runnable, GameParameters
   {// ������ �÷� â�� �ִ� JP��!
      int PWIDTH = width; 
      int PHEIGHT = height; 


      Thread animator;           
      boolean running = false;   
   

      PuyoFrametest topFrame;
      PuyoGame myPuyo;


      int score = 0;



      Graphics dbg; 
      Image dbImage = null;

      public PuyoPanel()
      {


         setBackground(Color.white);

         setPreferredSize( new Dimension(PWIDTH, PHEIGHT));

         //setBounds(20, 30, 150, 150);
         setFocusable(true);


         myPuyo= new PuyoGame();
         myPuyo.StartGame();

         startGame();

         addKeyListener( new KeyAdapter() {

            public void keyPressed(KeyEvent e)
            { 
               myKeyPressed(e); }
         });
      }  

       void myKeyPressed(KeyEvent e)
      { 

         myPuyo.ProcessKey(e);
      }



      void startGame()

      { 
         if (animator == null || !running) {
            animator = new Thread(this);
            animator.start();
         }
      } 



      public void run()

      {

         Graphics g;


         running = true;

         while(running) {
            gameUpdate(); 
            gameRender();   // ���ۿ� ����.
            paintScreen();  // ȭ�� ���۸� �׸�

            try {
               Thread.sleep(14);

            } catch (Exception e) {
               // TODO: handle exception
            }
         }
      }


       void gameUpdate() 
      { 
         myPuyo.MovePair();

      }  


       void gameRender()
      {
         if (dbImage == null){
            dbImage = createImage(PWIDTH, PHEIGHT);

            return;

         }

         else{
            dbg = dbImage.getGraphics();
         }

         //���
         dbg.setColor(Color.white);
         dbg.fillRect (0, 0, PWIDTH, PHEIGHT);

         myPuyo.Render(dbg);


      }  
      void paintScreen()

      { 
         Graphics g;
         try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null))
               g.drawImage(dbImage, 0, 0, null);

            g.dispose();
         }
         catch (Exception e)
         { 

         }
      } 
   }
   public    static void main(String args[])
   { 

      new PuyoFrametest();    
   }

}
package giun;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.Font;
    import java.awt.FontMetrics;
    import java.awt.Graphics;
    //import java.awt.Image;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import javax.swing.JPanel;
    import java.util.Random;
//import javax.swing.ImageIcon;
    import javax.swing.Timer;
        
public class GiunPanel extends JPanel implements ActionListener{
    // private Image apple;
     
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 100;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 4;
    int appleEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    
     GiunPanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.pink);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGiun(); 
        //loadImages();
     }
     //public void loadImages(){
     //   ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ransanmoi/icons/apple.png"));
     //   apple  = i1.getImage();
    // }
     
     public void startGiun(){
        newApple();
        running = true;
        timer = new Timer(DELAY, (ActionListener) this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        draw(g);
    }
     public void draw(Graphics g) {
         if(running) {
        // ve bang, ran, moi
        g.setColor(Color.GRAY);
            for(int i=0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
        }
           // g.drawImage(apple, appleX, appleY, this);
        
            for(int i=0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
                g.setColor(Color.red);
                g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
        
            for(int i = 0; i < bodyParts; i++){
                if(i == 0){
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } 
            else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                  }
                }
            else {
                gameOver(g);
            }
    }
    
    public void newApple(){
        appleX = random.nextInt((int) SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt((int) SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        
    }
    
    public void move() {
        for(int i=bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        
        switch(direction){
            case 'U':
                y[0] -= UNIT_SIZE;
                break;
            case 'D':
                y[0] += UNIT_SIZE;
                break;
            case 'L':
                x[0] -= UNIT_SIZE;
                break;
            case 'R':
                x[0] += UNIT_SIZE;
                break;
        }  
    }
    
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;
            appleEaten++;
            newApple();
        }
    }
    
    public void checkCollisions() {
        // check if head colides with the body
        for(int i = bodyParts; i > 0; i--){
            if((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
                break;
            }
        }
        // check head touch boders
        if(x[0] < 0) running = false;
        if(x[0] > SCREEN_WIDTH) running = false;
        if(y[0] < 0) running = false;
        if(y[0] > SCREEN_HEIGHT) running = false; 
        
        if(!running) timer.stop();
    }
    
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
	g.setFont( new Font("Brush Script Std",Font.BOLD, 100));
	FontMetrics metrics2 = getFontMetrics(g.getFont());
	g.drawString("Die", (SCREEN_WIDTH - metrics2.stringWidth("Die"))/2, SCREEN_HEIGHT/2); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //private void loadImages() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') direction = 'L';
                    break;
                
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') direction = 'R';
                    break;
                    
                case KeyEvent.VK_UP:
                    if(direction != 'D') direction = 'U';
                    break;
                    
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') direction = 'D';
                    break;    
            }
        }
        
    }
}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}

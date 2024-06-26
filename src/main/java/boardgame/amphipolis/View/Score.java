package boardgame.amphipolis.View;


import boardgame.amphipolis.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that will find the winner of the game and put informations on a window(points)
 */
public class Score {
 private int[] p=new int[4];
 private ImageIcon frame_icon = new ImageIcon("src/main/resources/Images/tile_back.png");
 private int first,last,second;
 private JButton exit;
private JLabel[] p_points;
 private JPanel gamepanel;
 private JFrame  Scoreboard= new JFrame("Scoreboard");

    /**
     * <b>constructor</b>: Constructs a new window and initialize some buttons
     * a panel and texts.<br />
     * <b>postcondition</b>: Creates and initializes a panel some buttons and texts
     * @param controller an object Controller
     */
public Score(Controller controller){
    exit=new JButton("Exit");
    p_points=new JLabel[4];
    for(int i=0;i<4;i++) {
        p_points[i] = new JLabel();
        p_points[i].setForeground(Color.WHITE);
    }
    gamepanel = new JPanel(new GridBagLayout());
    GridBagConstraints f2 = new GridBagConstraints();
    f2.fill=GridBagConstraints.HORIZONTAL;
    controller.pos_order_statues(controller.getCreatedboard().current_players);
    pointsgenerate(controller);
   f2.weighty=1;
    f2.gridx=0;
    f2.gridy=0;
    gamepanel.add(p_points[0],f2);
    f2.gridy=1;
    f2.gridx=0;
    gamepanel.add(p_points[1],f2);
    f2.gridy=2;
    gamepanel.add(p_points[2],f2);
    f2.gridy=3;
    gamepanel.add(p_points[3],f2);
    f2.gridy=4;
    exit.addActionListener(new exitListener());
    exit.setBackground(Color.WHITE);
    gamepanel.add(exit,f2);
    gamepanel.setBackground(Color.DARK_GRAY);
    Scoreboard.setIconImage(frame_icon.getImage());
    Scoreboard.add(gamepanel);
    Scoreboard.setResizable(false);
    Scoreboard.setBounds(800,300,400,400);
    Scoreboard.setVisible(true);

}

    /**
     * <b>transformer(mutative)</b>: Calculates the points of each player sets the Winner and adds them to the panel
     * <p><b>Postcondition:</b> Calculates the points of each player sets the Winner and adds them to the panel</p>
     * @param controller an object Controller
     */
public void pointsgenerate(Controller controller){
   for(int i=0;i<4;i++) {
        p[i] = controller.getCreatedboard().current_players.get(i).pointscalculator(controller.getPeak1(i), controller.getPeak2(i));
    }
    for(int i=0;i<4;i++){
        if(p[i]==maxpoints()){
            if(controller.getCreatedboard().current_players.get(i).getPlayer_name().isEmpty()){
                p_points[i].setText(controller.getCreatedboard().current_players.get(i).getPlayer_color()+" Player's  Points: "+p[i]+"       !!!!!WINNER!!!!!");
            }
            else{
            p_points[i].setText(controller.getCreatedboard().current_players.get(i).getPlayer_name()+" Points: "+p[i]+"       !!!!!WINNER!!!!!");

            }
        }else{
            if(controller.getCreatedboard().current_players.get(i).getPlayer_name().isEmpty()){
                p_points[i].setText(controller.getCreatedboard().current_players.get(i).getPlayer_color()+" Player's  Points: "+p[i]);
            }else {
                p_points[i].setText(controller.getCreatedboard().current_players.get(i).getPlayer_name() + " Points: " + p[i]);
            }
        }
    }
}

    /**
     * <b>accessor(selector)</b>:Returns the max points between 4 players <br />
     * <p><b>Postcondition:</b> Returns the max points between four players </p>
     * @return the max of 4 numbers(int)
     */
public int maxpoints(){
   first= Math.max(p[0],p[1]);
    second=Math.max(p[2],p[3]);
    last=Math.max(first,second);
    return last;
}
}

/**
 *A class that do some actions when the exit button is pressed
 */
class exitListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
    System.exit(0);
    }
}


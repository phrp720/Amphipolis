package boardgame.amphipolis.View;

import boardgame.amphipolis.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


/**
 *A class that opens that creates the first window of the game in which
 * the players adds their nicknames.
 */
public class MenuDialog {
    public ImageIcon frame_icon = new ImageIcon("src/main/resources/Images/tile_back.png");
    private Controller newgame;
    Default def = new Default();
    private JLabel[] p = new JLabel[4];
    private JTextField[] pname = new JTextField[4];
    private JButton Start;
    private JPanel menupanel;
    JFrame Menu_ = new JFrame("Starting Menu");

    //BackgroundPanel bg=new BackgroundPanel();


    /**
     * <b>constructor</b>: Constructs a new window(The first of the game) and initialize some buttons
     * a panel and texts.<br />
     * <b>postcondition</b>: Creates and initializes a panel some buttons and texts
     */
    public MenuDialog() {
        newgame=new Controller();
        menupanel = new JPanel(new GridBagLayout());
        GridBagConstraints constrain = new GridBagConstraints();
        for (int i = 0; i < 4; i++) {
            p[i] = new JLabel();
            pname[i] = new JTextField();
        }
        for (int i = 0; i < 4; i++) {
            def.enableDefaultValue( "Enter your nickname",pname[i]);
        }
        Start = new JButton("Start");
        Start.setBackground(Color.WHITE);
        p[0].setText("Player 1");
        p[0].setForeground(Color.black);
        p[1].setText("Player 2");
        p[1].setForeground(Color.red);
        p[2].setText("Player 3");
        p[2].setForeground(Color.green);
        p[3].setText("Player 4");
        p[3].setForeground(Color.blue);
        for (int i = 0; i < 4; i++) {
            p[i].setHorizontalAlignment(JLabel.CENTER);
        }
        constrain.fill = GridBagConstraints.ABOVE_BASELINE_LEADING;


        constrain.gridx = 0;
        constrain.gridy = 0;
        constrain.weighty = 0.5;
        constrain.weightx = 1;
        menupanel.add(p[0], constrain);
        constrain.gridx = 0;
        constrain.gridy = 1;
        menupanel.add(p[1], constrain);
        constrain.gridx = 0;
        constrain.gridy = 2;
        menupanel.add(p[2], constrain);
        constrain.gridx = 0;
        constrain.gridy = 3;
        menupanel.add(p[3], constrain);
        //JTextfield

        constrain.gridy = 0;
        constrain.ipady = 20;
        constrain.gridx = 1;
        menupanel.add(pname[0], constrain);
        constrain.gridx = 1;
        constrain.gridy = 1;
        menupanel.add(pname[1], constrain);
        constrain.gridx = 1;
        constrain.gridy = 2;
        menupanel.add(pname[2], constrain);
        constrain.gridx = 1;
        constrain.gridy = 3;
        menupanel.add(pname[3], constrain);
        constrain.gridx = 0;
        constrain.gridy = 4;
        constrain.gridwidth = 50;
        constrain.fill = GridBagConstraints.CENTER;
        //Start Button
        Start.addActionListener(new ActionListener() {
            /**
             * @param e
             */
            public void actionPerformed(ActionEvent e)
            {  for(int i=0;i<4;i++) {
                if(pname[i].getText().equals("Enter your nickname")){
                    pname[i].setText("");
                }
                newgame.getCreatedboard().current_players.get(i).setPlayer_name(pname[i].getText()); //sets the names of the players
            }Start.setEnabled(false);
            GraphicsUI gamaki=new GraphicsUI(newgame);
            Menu_.dispatchEvent(new WindowEvent(Menu_, WindowEvent.WINDOW_CLOSING));

            }
        });
        for(int i=0;i<4;i++){
            newgame.getCreatedboard().current_players.get(i).setPlayer_name(pname[i].getText());
        }
        menupanel.add(Start, constrain);
        menupanel.setBackground(Color.DARK_GRAY);
        Menu_.add(menupanel);
        Menu_.setIconImage(frame_icon.getImage());
        Menu_.setResizable(false);
        Menu_.setVisible(true);
        Menu_.setBounds(800,300,400,400);
    }
}





package boardgame.amphipolis.View;


import boardgame.amphipolis.Controller.Controller;
import boardgame.amphipolis.Enums.Colors.AmphorasColors;
import boardgame.amphipolis.Enums.Colors.MosaicColor;
import boardgame.amphipolis.Enums.Skeleton_Details.*;
import boardgame.amphipolis.Model.tiles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *A class that creates and sets the main frame of the game
 */
public class GraphicsUI {
    private  ImageIcon frame_icon=new ImageIcon("src/main/resources/Images/tile_back.png");
    private boolean check = false,flag=true,areachosen=false;
    private int counter=0,counter2=0,counter3=0;
    private int random_tile,index=0;
    private int[] index_;
    private int times=0;
    private int[] tile_id;
    private boolean chosen=false;
    private  int random_number;
    private JButton[][] invitems;
    private JButton button;
    private JButton EndTurn;
    private JButton Draw;
    private JButton assistant;
    private JButton digger;
    private JButton professor;
    private JButton archaeologist;
    private JLabel backround, player, playername, invname;
    private JButton[] items;;
    private int taken=0;
    private int[] character_taken=new int[4];
    private int drawpressed=0;
    private Controller controller;
    private int[] character_pick=new int[4];
     private JPanel parent,parent_2,parent_3,parent_4;
     private JFrame gui = new JFrame("Amphipolis");
     private JPanel game, mainpanel2, inventory;
     private JLayeredPane mainpanel1;
     private JPanel tleft, bleft, tright, bright, center;

    /**
     * <b>constructor</b>: Constructs a window of the game and initialize some buttons
     * <b>postcondition</b>: Creates and initializes the panels of the game and  some buttons
     */
    public GraphicsUI(Controller controller) {
        this.controller=controller;
        items = new JButton[135];
        invitems=new JButton[4][100];
        index_=new int[4];
        tile_id=new int[135];
        backround = new JLabel(smoothsize("src/main/resources/Images/background.png", 930, 1030));
        //Buttons
        EndTurn = new JButton("End Turn");
        EndTurn.setEnabled(false);
        button = new JButton();
        Draw = new JButton("Draw");
        assistant = new JButton(smoothsize("src/main/resources/Images/assistant.png", 250, 280));
        digger = new JButton(smoothsize("src/main/resources/Images/digger.png", 250, 280));
        professor = new JButton(smoothsize("src/main/resources/Images/professor.png", 250, 280));
        archaeologist = new JButton(smoothsize("src/main/resources/Images/archaeologist.png", 250, 280));
        for(int i=0;i<4;i++){
            character_taken[i]=0;
            character_pick[i]=0;
        }
        //Labels
        player = new JLabel("Player " + (controller.getCreatedboard().getPlayer_id() + 1));
        playername = new JLabel(controller.getCreatedboard().current_players.get((controller.getCreatedboard().getPlayer_id())).getPlayer_name());
        invname = new JLabel("My inventory: ");
        player.setHorizontalAlignment(JLabel.CENTER);
        playername.setHorizontalAlignment(JLabel.CENTER);
        //Change the size of JLabels
        player.setFont(player.getFont().deriveFont(40.0f));
        playername.setFont(player.getFont().deriveFont(30.0f));
        //initiallize the panels
        game = new JPanel(new GridLayout(0,2));
        mainpanel1 = new JLayeredPane();
        mainpanel2 = new JPanel(new GridBagLayout());
        inventory = new JPanel(new FlowLayout());
        tleft = new JPanel();
        tright = new JPanel();
        bleft = new JPanel();
        bright = new JPanel();
        center = new JPanel();
        GridBagConstraints f2 = new GridBagConstraints();
        inventory.add(invname,f2);
        //Color of backround
        mainpanel1.setBackground(Color.lightGray);
        mainpanel2.setBackground((Color.lightGray));
        // Right panel init(mainpanel2)
        f2.fill = GridBagConstraints.BOTH;
        f2.gridx = 0;
        f2.gridy = 2;
        mainpanel2.add(assistant, f2);
        f2.gridx = 1;
        f2.gridy = 2;
        mainpanel2.add(digger, f2);
        f2.gridy = 3;
        f2.gridx = 0;
        mainpanel2.add(professor, f2);
        f2.gridx = 1;
        f2.gridy = 3;
        mainpanel2.add(archaeologist, f2);
        f2.ipadx = 250;
        f2.ipady = 20;      //make this component tall
        f2.weightx = 0;
        f2.gridwidth = 30;
        f2.gridy = 4;
        f2.gridx = 0;
        mainpanel2.add(Draw, f2);
        f2.gridy = 5;
        f2.gridx = 0;
        mainpanel2.add(EndTurn, f2);
        f2.gridy = 0;
        f2.gridx = 0;
        mainpanel2.add(player, f2);
        f2.gridy = 1;
        mainpanel2.add(playername, f2);
        f2.gridwidth=5;
        f2.gridy = 6;
        f2.ipadx=0;
         f2.ipady=5;
        f2.gridx = 0;
        f2.fill = GridBagConstraints.FIRST_LINE_END;
        inventory.setPreferredSize(new Dimension(570,200));
       mainpanel2.add(inventory, f2);
        //Left panel init(mainpanel1)
        backround.setBounds(0, 0, 930, 1030);
        mainpanel1.add(backround, JLayeredPane.DEFAULT_LAYER);
        tleft.setBackground(new Color(0, 0, 0, 0));
        tleft.setBounds(20, 19, 290, 320);
        mainpanel1.add(tleft, JLayeredPane.PALETTE_LAYER);
        tright.setBackground(new Color(0, 0, 0, 0));
        tright.setBounds(620, 19, 290, 320);
        mainpanel1.add(tright, JLayeredPane.PALETTE_LAYER);
        center.setBackground(new Color(0, 0, 0, 0));
        center.setBounds(327, 450, 290, 300);
        mainpanel1.add(center, JLayeredPane.PALETTE_LAYER);
        bleft.setBackground(new Color(0, 0, 0, 0));
        bleft.setBounds(20, 690, 290, 320);
        mainpanel1.add(bleft, JLayeredPane.PALETTE_LAYER);
       bright.setBackground(new Color(0, 0, 0, 0));
        bright.setBounds(620, 690, 290, 320);
        mainpanel1.add(bright, JLayeredPane.PALETTE_LAYER);
        digger.addActionListener(new diggerListener());
        archaeologist.addActionListener(new archaeologistListener());
        professor.addActionListener(new professorListener());
        assistant.addActionListener(new assistantListener());
       // ImageTilesInit(controller);
        FirstFour(controller);
        //Tiles buttons

        //End Turn button
        EndTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    EndTurn.setEnabled(false);
                    controller.getCreatedboard().next_player_id();
                playername.setText(controller.getCreatedboard().current_players.get((controller.getCreatedboard().getPlayer_id())).getPlayer_name());
                    if(controller.getCreatedboard().getPlayer_id() + 1==1){
                        player.setForeground(Color.black);
                    }else if(controller.getCreatedboard().getPlayer_id() + 1==2){
                        player.setForeground(Color.red);
                    }else if(controller.getCreatedboard().getPlayer_id() + 1==3){
                        player.setForeground(Color.green);
                    }else if(controller.getCreatedboard().getPlayer_id() + 1==4){
                        player.setForeground(Color.blue);
                    }
                player.setText("Player " + (controller.getCreatedboard().getPlayer_id() + 1));
                    Draw.setEnabled(true);
                    assistant.setEnabled(false);
                    digger.setEnabled(false);
                    archaeologist.setEnabled(false);
                    professor.setEnabled(false);
                    for(int i=0;i<4;i++) {
                        if (controller.getCreatedboard().current_players.get((controller.getCreatedboard().getPlayer_id())).player_Heroes.get(i).getUsed_character()){
                            if(i==0){
                                assistant.setEnabled(true);
                            }else if(i==2){
                                digger.setEnabled(true);
                            }else if(i==1){
                                archaeologist.setEnabled(true);
                            }else if(i==3){
                                professor.setEnabled(true);
                            }
                        }
                    }
                    for(int i=0;i<4;i++){
                        character_pick[i]=0;
                    }
                    if(controller.getCreatedboard().getPlayer_id()!=0){
                    for(int i=0;i<index_[controller.getCreatedboard().getPlayer_id()-1];i++){
                        inventory.remove(invitems[controller.getCreatedboard().getPlayer_id()-1][i]);
                    }}else{
                        for(int i=0;i<index_[3];i++){
                            inventory.remove(invitems[3][i]);
                        }
                    }
                    if(index_[controller.getCreatedboard().getPlayer_id()]!=0){
                        for(int i=0;i<index_[controller.getCreatedboard().getPlayer_id()];i++){
                        inventory.add(invitems[controller.getCreatedboard().getPlayer_id()][i]);}
                    }

                    taken=0;
                    drawpressed=0;
                    chosen=false;
                    flag=true;
                    counter=0;
                    counter2=0;
                    counter3=0;
                    areachosen=false;
                    gui.invalidate();
                    gui.validate();
                    gui.repaint();

                }
        });
        //Draw button
        Draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EndTurn.setEnabled(true);
                drawpressed=1;
                DraworScore(controller);
                DraworScore(controller);
                DraworScore(controller);
                DraworScore(controller);
                Draw.setEnabled(false);

            }
        });
        //Add the 2 panels to the main panel(game)
        //mainpanel1
        game.add(mainpanel1);
        game.add(mainpanel2);
        //add the main panel to the frame
        gui.add(game);
        gui.setIconImage(frame_icon.getImage());
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gui.setResizable(false);
        gui.setVisible(true);


    }

    /**
     *  <b>transformer(mutative)</b>:Draw a random tile from the bag and checks if the tomb has 16 landslides
     * <p><b>Post-condition:</b> :draws randomly a tile from the bag and then checks if the game is over
     * @param controller an object Controller
     */
     public void DraworScore(Controller controller){
        random_number = (int) (Math.random() * ((controller.getCreatedboard().getbag().tilesinbag.size()-1) - 0 + 1)) + (0);

        if (controller.getCreatedboard().getbag().tilesinbag.get( random_number) instanceof MosaicTile){
            Icon_Setter(random_number,controller);
            tleft.add(items[index-1]);
            controller.getCreatedboard().getMosaic().add((MosaicTile) controller.getCreatedboard().getbag().tilesinbag.get( random_number) );
            items[index-1].addActionListener(new button_items(controller.getCreatedboard().getMosaic().size()-1,tile_id[index-1],index-1));
            gui.validate();
            gui.repaint();
        }
        else if(controller.getCreatedboard().getbag().tilesinbag.get( random_number) instanceof SkeletonTile){
            Icon_Setter(random_number,controller);
            controller.getCreatedboard().getSkeletons().add((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get( random_number) );
            items[index-1].addActionListener(new button_items(controller.getCreatedboard().getSkeletons().size()-1,tile_id[index-1],index-1));
            bright.add(items[ index-1]);
            gui.repaint();
            gui.validate();
        }else if(controller.getCreatedboard().getbag().tilesinbag.get( random_number) instanceof Landslide){
            Icon_Setter(random_number,controller);
            controller.getCreatedboard().getLandslide().add((Landslide) controller.getCreatedboard().getbag().tilesinbag.get( random_number) );
            items[index-1].addActionListener(new button_items(controller.getCreatedboard().getLandslide().size()-1,tile_id[index-1],index-1));
            center.add(items[ index-1]);
            gui.repaint();
            gui.validate();
        }
        else if(controller.getCreatedboard().getbag().tilesinbag.get( random_number) instanceof AmphoraTile){
            Icon_Setter(random_number,controller);
            controller.getCreatedboard().getAmphoras().add((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get( random_number) );
            items[index-1].addActionListener(new button_items(controller.getCreatedboard().getAmphoras().size()-1,tile_id[index-1],index-1));
            bleft.add(items[index-1]);
            gui.repaint();
            gui.validate();
        }
        else if(controller.getCreatedboard().getbag().tilesinbag.get( random_number) instanceof SphinxTile){
            Icon_Setter(random_number,controller);
            controller.getCreatedboard().getStatues().add((StatueTile)  controller.getCreatedboard().getbag().tilesinbag.get( random_number));
            items[index-1].addActionListener(new button_items(controller.getCreatedboard().getStatues().size()-1,tile_id[index-1],index-1));
            tright.add(items[index-1]);
            gui.repaint();
            gui.validate();
        }
        else if(controller.getCreatedboard().getbag().tilesinbag.get( random_number) instanceof CaryatidTile){
            Icon_Setter(random_number,controller);
            controller.getCreatedboard().getStatues().add((StatueTile)  controller.getCreatedboard().getbag().tilesinbag.get( random_number));
            items[index-1].addActionListener(new button_items(controller.getCreatedboard().getStatues().size()-1,tile_id[index-1],index-1));
            tright.add(items[ index-1]);
            gui.repaint();
            gui.validate();
        }
        controller.getCreatedboard().getbag().draw_plaque(random_number, controller.getCreatedboard().getbag().tilesinbag);
        if(times==0){
            if (controller.getCreatedboard().EndofgameCheck(controller.getCreatedboard().getLandslide())) {
                Draw.setEnabled(false);
                Score score = new Score(controller);
                times++;
                gui.dispose();
            }

        }
    }

    /**
     *  <b>accessor(selector)</b>Take an image and resize it
     *   <p><b>Post-condition:</b>Take the image of the path and returns it with the prefered size
     * @param path the path of the image
     * @param width the preferred width
     * @param height the preferred height
     * @return a ImageIcon with our preferred size
     */
    //Resize the image
  public   ImageIcon smoothsize(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        return new ImageIcon(icon.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH));
    }

    /**
     *<b>transformer(mutative)</b>:Adds the first four (different-type) tiles on the board
     * <p><b>Post-condition:</b>:Pick randomly four different type tiles from the bag and add them to the board
     * @param controller an object Controller
     */
 //sets the first four tiles on the board
   public  void FirstFour(Controller controller) {
        random_tile = (int) (Math.random() * ((controller.getCreatedboard().getbag().tilesinbag.size()-1)- 0) + 1) + (0); //Generates a random tile from bag
        while (!controller.getCreatedboard().StartofgameCheck(controller.getCreatedboard().getSkeletons(), controller.getCreatedboard().getAmphoras(), controller.getCreatedboard().getStatues(), controller.getCreatedboard().getMosaic())) {
            if (controller.getCreatedboard().getbag().tilesinbag.get(random_tile) instanceof MosaicTile && controller.getCreatedboard().getMosaic().isEmpty()) {              //Check if the item that we took from the created board is a Mosaic
                controller.getCreatedboard().getMosaic().add((MosaicTile) controller.getCreatedboard().getbag().tilesinbag.get(random_tile));  //Adds a mosaic in the createdboard at the field of mosaics
                Icon_Setter(random_tile,controller);
                items[index-1].addActionListener(new button_items(controller.getCreatedboard().getMosaic().size()-1,tile_id[index-1],index-1));
                tleft.add(items[index-1]);
                check = true;
            } else if (controller.getCreatedboard().getbag().tilesinbag.get(random_tile) instanceof SkeletonTile && controller.getCreatedboard().getSkeletons().isEmpty()) {           //Check if the item that we took from the created board is a Skeleton
                controller.getCreatedboard().getSkeletons().add((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(random_tile));  //Adds a skeleton in the createdboard at the field of skeletons
                check = true;
                Icon_Setter(random_tile,controller);
                items[index-1].addActionListener(new button_items(controller.getCreatedboard().getSkeletons().size()-1,tile_id[index-1],index-1));
                bright.add(items[index-1]);
            } else if (controller.getCreatedboard().getbag().tilesinbag.get(random_tile) instanceof AmphoraTile && controller.getCreatedboard().getAmphoras().isEmpty()) {       //Check if the item that we took from the created board is an Amphora
                controller.getCreatedboard().getAmphoras().add((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get(random_tile));  //Adds a amphora in the createdboard at the field of amphoras
                check = true;
                Icon_Setter(random_tile,controller);
                items[index-1].addActionListener(new button_items(controller.getCreatedboard().getAmphoras().size()-1,tile_id[index-1],index-1));
                bleft.add(items[index-1]);
            } else if (controller.getCreatedboard().getbag().tilesinbag.get(random_tile) instanceof StatueTile && controller.getCreatedboard().getStatues().isEmpty()) {        //Check if the item that we took from the created board is a Statue
                controller.getCreatedboard().getStatues().add((StatueTile) controller.getCreatedboard().getbag().tilesinbag.get(random_tile)); //Adds a mosaic in the createdboard at the field of statues
                check = true;
                Icon_Setter(random_tile,controller);
                items[index-1].addActionListener(new button_items(controller.getCreatedboard().getStatues().size()-1,tile_id[index-1],index-1));
                tright.add(items[index-1]);
            }
            if (check) {
                controller.getCreatedboard().getbag().draw_plaque(random_tile, controller.getCreatedboard().getbag().tilesinbag);
                check = false;
            }
            random_tile = (int) (Math.random() * ((controller.getCreatedboard().getbag().tilesinbag.size()-1)- 0) + 1) + (0);

        }
    }

    /**
     * <b>transformer(mutative)</b>:Sets an icon to the tile that was drawn from the bag
     * <p><b>Post-condition:</b>:Sets a specific icon to the tile that was drawn randomly from the bag
     * @param i the random position of the tile that we draw
     * @param controller an object Controller
     */
    //Everytime we drug an item it setts an icon for this tile
   public void Icon_Setter(int i,Controller controller){
        if (controller.getCreatedboard().getbag().tilesinbag.get(i) instanceof AmphoraTile) {
            tile_id[index]=2;
            if (((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == AmphorasColors.blue) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/amphora_blue.png", 30, 30));
            } else if (((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == AmphorasColors.brown) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/amphora_brown.png", 30, 30));
            } else if (((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == AmphorasColors.green) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/amphora_green.png", 30, 30));
            } else if (((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == AmphorasColors.purple) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/amphora_purple.png", 30, 30));
            } else if (((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == AmphorasColors.red) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/amphora_red.png", 30, 30));
            } else if (((AmphoraTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == AmphorasColors.yellow) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/amphora_yellow.png", 30, 30));
            }
        } else if (controller.getCreatedboard().getbag().tilesinbag.get(i) instanceof MosaicTile) {
            tile_id[index]=0;
            if (((MosaicTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == MosaicColor.yellow) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/mosaic_yellow.png", 30, 30));
            } else if (((MosaicTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == MosaicColor.red) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/mosaic_red.png", 30, 30));
            } else if (((MosaicTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getColor() == MosaicColor.green){
                items[index] = new JButton(smoothsize("src/main/resources/Images/mosaic_green.png", 30, 30));
            }
        } else if (controller.getCreatedboard().getbag().tilesinbag.get(i) instanceof SphinxTile) {
            tile_id[index]=3;
            items[index] = new JButton(smoothsize("src/main/resources/Images/sphinx.png", 30, 30));
        } else if (controller.getCreatedboard().getbag().tilesinbag.get(i) instanceof CaryatidTile) {
            tile_id[index]=3;
            items[index] = new JButton(smoothsize("src/main/resources/Images/caryatid.png", 30, 30));
        } else if (controller.getCreatedboard().getbag().tilesinbag.get(i) instanceof SkeletonTile) {
            tile_id[index]=1;
            if (((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSize_() == Skeleton_size.big && ((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSide_() == Skeleton_Side.upperside) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/skeleton_big_top.png", 30, 30));
            } else if (((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSize_() == Skeleton_size.big && ((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSide_() == Skeleton_Side.bottomside) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/skeleton_big_bottom.png", 30, 30));
            } else if (((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSize_() == Skeleton_size.small && ((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSide_() == Skeleton_Side.upperside) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/skeleton_small_top.png", 30, 30));
            } else if (((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSize_() == Skeleton_size.small && ((SkeletonTile) controller.getCreatedboard().getbag().tilesinbag.get(i)).getSide_() == Skeleton_Side.bottomside) {
                items[index] = new JButton(smoothsize("src/main/resources/Images/skeleton_small_bottom.png", 30, 30));
            }
        } else if (controller.getCreatedboard().getbag().tilesinbag.get(i) instanceof Landslide) {
            tile_id[index]=8;
            items[index] = new JButton(smoothsize("src/main/resources/Images/landslide.png", 40, 40));
        }
        index++;
    }



    /**
     * A class that do some actions when the digger  button is pressed
     */

    class diggerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!chosen &&drawpressed==1&& areachosen) {
                digger.setEnabled(false);
                chosen = true;
                controller.getCreatedboard().current_players.get((controller.getCreatedboard().getPlayer_id())).player_Heroes.get(2).setUsed_character(false);
                character_pick[1] = 1;
            }
        }
    }


    /**
     * A class that do some actions when assistant button is pressed
     */
    class assistantListener implements ActionListener {

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!chosen &&drawpressed==1&& areachosen){
                assistant.setEnabled(false);
                controller.getCreatedboard().current_players.get((controller.getCreatedboard().getPlayer_id())).player_Heroes.get(0).setUsed_character(false);
                character_pick[0]=1;
                chosen=true;
            }
        }
    }

    /**
     * A class that do some actions when the professor button is pressed
     */
    class professorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!chosen &&drawpressed==1&& areachosen){
                 professor.setEnabled(false);
                 controller.getCreatedboard().current_players.get((controller.getCreatedboard().getPlayer_id())).player_Heroes.get(3).setUsed_character(false);
                 character_pick[2]=1;
                 chosen=true;
            }
        }
    }

    /**
     * A class that do some actions when the archaeologist button is pressed
     */
    class archaeologistListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!chosen &&drawpressed==1&& areachosen) {
                archaeologist.setEnabled(false);
                controller.getCreatedboard().current_players.get((controller.getCreatedboard().getPlayer_id())).player_Heroes.get(1).setUsed_character(false);
                character_pick[3] = 1;
                chosen=true;
            }
        }
    }

    /**
     * A class that do some actions when a tile from the board is pressed
     */
  public  class button_items implements  ActionListener{
        int pos;
        int tile_type;
        int button_pos;

        /**
         * b>constructor</b>:It initialises some useful parameters for the button
         * <b>post-condition</b>:creates and initialises some useful parameters
         * for the button(index,tile_type,button_pos)
         * @param index the index of the tile in a specific area on the board
         * @param tile_type the type of the tile
         * @param button_pos the position of the button
         */
       public button_items(int index,int tile_type,int button_pos){
           this.tile_type=tile_type;
           pos=index;
           this.button_pos=button_pos;

       }

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            button = (JButton) e.getSource();
            parent = (JPanel) ((JButton) e.getSource()).getParent();
            if (drawpressed == 1) {
                if (!parent.equals(center)) {
                    if (taken == 0) {
                        areachosen=true;
                        inventory.add(button);
                        invitems[controller.getCreatedboard().getPlayer_id()][  index_[controller.getCreatedboard().getPlayer_id()]]=button;
                        index_[controller.getCreatedboard().getPlayer_id()]++;
                        inventoryadder(tile_id[button_pos],pos);
                        if (parent == tleft) {
                            tleft.remove((JButton) e.getSource());
                        } else if (parent == tright) {
                            tright.remove((JButton) e.getSource());
                        } else if (parent == bright) {
                            bright.remove((JButton) e.getSource());
                        } else if (parent == bleft) {
                            bleft.remove((JButton) e.getSource());
                        }
                        gui.validate();
                        gui.repaint();
                        parent_2 = parent;
                        taken++;
                    } else if (taken == 1) {
                        if (parent.equals(parent_2)) {
                            parent.remove((JButton) e.getSource());
                            inventory.add(button);
                            inventoryadder(tile_id[button_pos],pos);
                            invitems[controller.getCreatedboard().getPlayer_id()][index_[controller.getCreatedboard().getPlayer_id()]]=button;
                            index_[controller.getCreatedboard().getPlayer_id()]++;
                            gui.validate();
                            gui.repaint();
                            taken++;
                        }
                    }
                    if(taken==2||taken==1) {
                        if (flag) {
                            if (character_pick[0] == 1) {
                                parent.remove((JButton) e.getSource());
                                inventory.add(button);
                                invitems[controller.getCreatedboard().getPlayer_id()][index_[controller.getCreatedboard().getPlayer_id()]]=button;
                                index_[controller.getCreatedboard().getPlayer_id()]++;
                                inventoryadder(tile_id[button_pos],pos);
                                character_pick[0] = 0;
                                flag = false;
                                gui.validate();
                                gui.repaint();

                            }
                            if (character_pick[1] == 1) {
                                if (counter!=2) {
                                    if (parent == parent_2) {
                                        parent.remove((JButton) e.getSource());
                                        inventory.add(button);
                                        invitems[controller.getCreatedboard().getPlayer_id()][index_[controller.getCreatedboard().getPlayer_id()]]=button;
                                        index_[controller.getCreatedboard().getPlayer_id()]++;
                                        inventoryadder(tile_id[button_pos],pos);
                                        counter++;
                                        gui.validate();
                                        gui.repaint();
                                    }
                                } else {
                                    flag = false;
                                    counter = 0;
                                }
                            }
                            if (character_pick[2] == 1) {
                                if (counter2==0) {
                                    parent_3=parent;
                                    if (parent != parent_2) {
                                        parent.remove((JButton) e.getSource());
                                        inventory.add(button);
                                        invitems[controller.getCreatedboard().getPlayer_id()][  index_[controller.getCreatedboard().getPlayer_id()]]=button;
                                        index_[controller.getCreatedboard().getPlayer_id()]++;
                                        inventoryadder(tile_id[button_pos],pos);
                                        counter2++;
                                        gui.validate();
                                        gui.repaint();
                                    }
                                }else if(counter2==1){
                                    parent_4=parent;
                                    if (parent != parent_3&&parent!=parent_2) {
                                        parent.remove((JButton) e.getSource());
                                        inventory.add(button);
                                        invitems[controller.getCreatedboard().getPlayer_id()][  index_[controller.getCreatedboard().getPlayer_id()]]=button;
                                        index_[controller.getCreatedboard().getPlayer_id()]++;
                                        inventoryadder(tile_id[button_pos],pos);
                                        counter2++;
                                        gui.validate();
                                        gui.repaint();
                                    }
                                } else if(counter2==2){
                                    if (parent != parent_3&&parent!=parent_2&&parent!=parent_4) {
                                        parent.remove((JButton) e.getSource());
                                        inventory.add(button);
                                        invitems[controller.getCreatedboard().getPlayer_id()][  index_[controller.getCreatedboard().getPlayer_id()]]=button;
                                        index_[controller.getCreatedboard().getPlayer_id()]++;
                                        inventoryadder(tile_id[button_pos],pos);
                                        counter2++;
                                        gui.validate();
                                        gui.repaint();
                                    }
                                }
                                else {
                                    flag = false;
                                }
                            }
                            if (character_pick[3] == 1) {
                                if(counter3==0){
                                    parent_3=parent;
                                    if (parent_3 != parent_2) {
                                        parent.remove((JButton) e.getSource());
                                        inventory.add(button);
                                        invitems[controller.getCreatedboard().getPlayer_id()][  index_[controller.getCreatedboard().getPlayer_id()]]=button;
                                        index_[controller.getCreatedboard().getPlayer_id()]++;
                                        inventoryadder(tile_id[button_pos],pos);
                                        gui.validate();
                                        gui.repaint();
                                        counter3++;
                                    }
                                }else if(counter3==1){
                                    if(parent==parent_3){
                                        parent.remove((JButton) e.getSource());
                                        inventory.add(button);
                                        invitems[controller.getCreatedboard().getPlayer_id()][  index_[controller.getCreatedboard().getPlayer_id()]]=button;
                                        index_[controller.getCreatedboard().getPlayer_id()]++;
                                        inventoryadder(tile_id[button_pos],pos);
                                        gui.validate();
                                        gui.repaint();
                                        flag = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

}

    /**
     * <b>transformer(mutative)</b>:Adds to the player's inventory the tile that he chose
     * <p><b>Post-condition:</b>:Adds the tile with position __index to the player's inventory
     * @param tile_type the type of the tile
     * @param __index the index of the tile in a specific area on the board
     */
        public     void inventoryadder(int tile_type,int __index){
                if(tile_type==0){
                    controller.getCreatedboard().current_players.get(controller.getCreatedboard().getPlayer_id()).player_items.add(controller.getCreatedboard().getMosaic().get(__index));
                }else if(tile_type==1){
                    controller.getCreatedboard().current_players.get(controller.getCreatedboard().getPlayer_id()).player_items.add(controller.getCreatedboard().getSkeletons().get(__index));
                }else if(tile_type==2){
                    controller.getCreatedboard().current_players.get(controller.getCreatedboard().getPlayer_id()).player_items.add(controller.getCreatedboard().getAmphoras().get(__index));
                }else if((tile_type==3)){
                    controller.getCreatedboard().current_players.get(controller.getCreatedboard().getPlayer_id()).player_items.add(controller.getCreatedboard().getStatues().get(__index));
                }
            }
            }












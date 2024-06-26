package boardgame.amphipolis.Controller;


import boardgame.amphipolis.Enums.Colors.PlayerColor;
import boardgame.amphipolis.Enums.StatuePeak;
import boardgame.amphipolis.Model.Board;
import boardgame.amphipolis.Model.Player;
import boardgame.amphipolis.Model.tiles.CaryatidTile;
import boardgame.amphipolis.Model.tiles.SphinxTile;

import java.util.ArrayList;

/**
 * The Class Controller is the masterpiece of the game.
 * It controls all functions executed and sets the things into an order to start the game
 * successfully
 */
public class Controller {
    private Player player_1,player_2,player_3,player_4;
    private Board createdboard;
    private int[] caryatid_counter=new int[4];
    private int[] sphinx_counter=new int[4];
    private StatuePeak[] peak1=new StatuePeak[4];
    private StatuePeak[] peak2=new StatuePeak[4];
    private int[] first_min,first_max,second_max,second_min;
    private  int[] end_max,end_min;

    //NEWWWWW
    private  int check_1=0,check_2=0;


    /**
     *<b>constructor</b>: Constructs a new Controller who makes initializes and creations
     * in order to meet the requirements to start the game .<br />
     * <b>postcondition</b>: constructs a new controller,create 4 new players,new Board
     * and bag.More specific, it sets the things into an order to start the game
     * successfully without problems
     */
    public Controller(){
        player_1=new Player(PlayerColor.black,"PLAYER 1");
        player_2=new Player(PlayerColor.blue,"PLAYER 2");
        player_3=new Player(PlayerColor.green,"PLAYER 3");
        player_4=new Player(PlayerColor.red,"PLAYER 4");
        createdboard=new Board(4);

        createdboard.current_players.add(player_1);
        createdboard.current_players.add(player_2);
        createdboard.current_players.add(player_3);
        createdboard.current_players.add(player_4);
        first_min=new int[2];
        first_max=new int[2];
        second_max=new int[2];
        second_min =new int[2];
        end_max=new int[2];
        end_min=new int[2];


    }

    /**
     * <b>accessor(selector)</b>:Returns the position of a player in relation with his caryatids.<br />
     * <p><b>Postcondition:</b> returns the position of the player(medium,smaller,bigger) in relation with his caryatids </p>
     * @param i Index
     * @return the value peak1[i](StatuePeak) in which i is the index
     */
    public StatuePeak getPeak1(int i) {
        return peak1[i];
    }

    /**
     * <b>accessor(selector)</b>:Returns the position of a player in relation with his sphinxs.<br />
     * <p><b>Postcondition:</b> returns the position of the player(medium,smaller,bigger) in relation with his sphinxs </p>
     * @param i Index
     * @return the value peak2[i](StatuePeak) in which i is the index
     */
    public StatuePeak getPeak2(int i){
        return peak2[i];
    }

    /**
     * <b>accessor(selector)</b>:Returns the board of the game<br />
     * <p><b>Postcondition:</b> returns the game's board</p>
     * @return the object createdboard(Board)
     */
    public Board getCreatedboard() {
        return createdboard;
    }

    /**
     * <b>transformer(mutative)</b>: It finds who has the most Sphinx and who the most Caryatids and sets the suitable attribute<br />
     * <b>postcondition</b>:Finds the smaller medium and bigger player in relation with their caryatids and sphinx
     * @param players  an arraylist with the players of the game
     */
    // Finds which player has the most Sphinx and who has the most caryatids
    public void pos_order_statues(ArrayList<Player> players) {
        for(int i=0;i<4;i++){
            caryatid_counter[i]=0;
            sphinx_counter[i]=0;
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < players.get(j).player_items.size(); i++) {
                if (createdboard.current_players.get(j).player_items.get(i) instanceof CaryatidTile) {
                    caryatid_counter[j]++;
                } else if (createdboard.current_players.get(j).player_items.get(i)  instanceof SphinxTile) {
                    sphinx_counter[j]++;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (caryatid_counter[i] == 0) {
                check_1++;
            }
            if (sphinx_counter[i] == 0) {
                check_2++;
            }
        }
        if (check_1 != 4) {
            first_max[0] = Math.max(caryatid_counter[0], caryatid_counter[1]);
            second_max[0] = Math.max(caryatid_counter[2], caryatid_counter[3]);
            end_max[0] = Math.max(first_max[0], second_max[0]);
            first_min[0] = Math.min(caryatid_counter[0], caryatid_counter[1]);
            second_min[0] = Math.min(caryatid_counter[2], caryatid_counter[3]);
            end_min[0] = Math.min(first_min[0], second_min[0]);
        }
        if(check_2!=4) {
            first_max[1] = Math.max(sphinx_counter[0], sphinx_counter[1]);
            second_max[1] = Math.max(sphinx_counter[2], sphinx_counter[3]);
            end_max[1] = Math.max(first_max[1], second_max[1]);
            first_min[1] = Math.min(sphinx_counter[0], sphinx_counter[1]);
            second_min[1] = Math.min(sphinx_counter[2], sphinx_counter[3]);
            end_min[1] = Math.min(first_min[1], second_min[1]);
        }
        for(int i=0;i<4;i++){
            if(check_1!=4){
                if(caryatid_counter[i]<end_max[0]&&caryatid_counter[i]>end_min[0]){
                    peak1[i]=StatuePeak.medium;
                }else if(caryatid_counter[i]==end_max[0]){
                    peak1[i]=StatuePeak.bigger;
                }else if(caryatid_counter[i]==end_min[0]){
                    peak1[i]=StatuePeak.smaller;
                }
            }else{
                peak1[i]=StatuePeak.none;
            }
            if(check_2!=4){
                if(sphinx_counter[i]<end_max[1]&&sphinx_counter[i]>end_min[1]){
                    peak2[i]=StatuePeak.medium;
                }else if(sphinx_counter[i]==end_max[1]){
                    peak2[i]=StatuePeak.bigger;
                }else if(sphinx_counter[i]==end_min[1]){
                    peak2[i]=StatuePeak.smaller;
                }
            }else{
                peak2[i]=StatuePeak.none;
            }
        }
    }
}

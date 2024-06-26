package boardgame.amphipolis.Model;


import boardgame.amphipolis.Enums.Colors.*;
import boardgame.amphipolis.Enums.Skeleton_Details.Skeleton_Side;
import boardgame.amphipolis.Enums.Skeleton_Details.Skeleton_size;
import boardgame.amphipolis.Model.tiles.*;

import java.util.ArrayList;

/**
 *Board Class describes the board of the game and provides modification methods
 * and in game check functions.
 */
public class Board {
    protected int reallandslides=16;
    protected  static int player_id=0; // Current player who plays
    protected ArrayList<SkeletonTile> skeletons=new ArrayList<SkeletonTile>();
    protected ArrayList<AmphoraTile>  amphoras=new ArrayList<AmphoraTile>();
    protected ArrayList<MosaicTile> mosaic=new ArrayList<MosaicTile>();
    protected ArrayList<StatueTile>  statues=new ArrayList<StatueTile>();
    protected ArrayList<Landslide> landslide=new ArrayList<Landslide>();
    public ArrayList<Player>  current_players=new ArrayList<Player>();
    protected Bag currentbag=new Bag();

    /**
     * <b>constructor</b>: Constructs a new  Bord <br />
     * <b>postcondition</b>: Creates and initializes a board.Sets the game with the HostgamePlayers condition in mind.Also initializes the object currentbag(Bag)
     * and the arraylist  tilesinbag of this object to fill the bag with tiles before the game begins.
     * @param HostgamePlayers is the number of player that will play the game
     */
public Board(int HostgamePlayers){
    if(HostgamePlayers==4){
    for(int i=0;i<9;i++){
        currentbag.tilesinbag.add(new MosaicTile(MosaicColor.red));
        currentbag.tilesinbag.add(new MosaicTile(MosaicColor.yellow));
        currentbag.tilesinbag.add(new MosaicTile(MosaicColor.green));
    }
    for(int i=0;i<12;i++){
        currentbag.tilesinbag.add(new CaryatidTile() );
        currentbag.tilesinbag.add(new SphinxTile() );
    }
    for(int i=0;i<24;i++){
        currentbag.tilesinbag.add(new Landslide());
    }
    for(int i=0;i<10;i++){
        currentbag.tilesinbag.add(new SkeletonTile(Skeleton_size.big, Skeleton_Side.upperside));
        currentbag.tilesinbag.add(new SkeletonTile(Skeleton_size.big, Skeleton_Side.bottomside));
    } for(int i=0;i<5;i++){
        currentbag.tilesinbag.add(new SkeletonTile(Skeleton_size.small, Skeleton_Side.upperside));
        currentbag.tilesinbag.add(new SkeletonTile(Skeleton_size.small, Skeleton_Side.bottomside));
    }for(int i=0;i<5;i++){
        currentbag.tilesinbag.add(new AmphoraTile(AmphorasColors.brown));
        currentbag.tilesinbag.add(new AmphoraTile(AmphorasColors.yellow));
        currentbag.tilesinbag.add(new AmphoraTile(AmphorasColors.red));
        currentbag.tilesinbag.add(new AmphoraTile(AmphorasColors.green));
        currentbag.tilesinbag.add(new AmphoraTile(AmphorasColors.purple));
        currentbag.tilesinbag.add(new AmphoraTile(AmphorasColors.blue));
    }
}

}



    /**
     * <b>accessor(selector)</b>: Returns an arraylist with all the landslides that are on board right now. <br />
     * <b>postcondition</b>: Returns an arraylist with all the landslides that are on board right now.
     * @return  the arraylist landslide(ArrayList)
     */
    public ArrayList<Landslide> getLandslide() {
        return landslide;
    }

    /**
     * <b>accessor(selector)</b>: Returns an arraylist with all the amphoras that are on board right now. <br />
     * <b>postcondition</b>: Returns an arraylist with all the amphoras that are on board right now.
     * @return the arraylist amphoras(Arraylist).
     */
    public ArrayList<AmphoraTile> getAmphoras() {
        return amphoras;
    }
    /**
     * <b>accessor(selector)</b>: Returns an arraylist with all the mosaics that are on board right now. <br />
     * <b>postcondition</b>: Returns an arraylist with all the mosaics that are on board right now.
     * @return the arraylist mosaic(Arraylist).
     */
    public ArrayList<MosaicTile> getMosaic() {
        return mosaic;
    }

    /**
     * <b>accessor(selector)</b>: Returns an arraylist with all the skeletons that are on board right now. <br />
     * <b>post-condition</b>: Returns an arraylist with all the skeletons that are on board right now.
     * @return the arraylist skeletons(Arraylist).
     */
    public ArrayList<SkeletonTile> getSkeletons() {
        return skeletons;
    }

    /***
     * <b>accessor(selector)</b>: Returns the  arraylist statues which contains the statues that are on board. <br />
     * <b>postcondition</b>:  Returns the  arraylist statues which contains the statues that are on board.
     * @return the arraylist  statues(ArrayList)
     */
    public ArrayList<StatueTile> getStatues() {
        return statues;
    }

    /**
     *<b>accessor(selector)</b>: Returns the object currentbag which contains the tiles that are in the bag. <br />
     * <b>postcondition</b>:  Returns the  object currentbag which contains the tiles that are in the bag.
     * @return the object currentbag(Bag)
     */
    public Bag getbag(){return currentbag;}

    /**
     * <b>transformer(mutative)</b>: Gives the turn to the next player<br />
     * <p><b>Postcondition:</b> goes to the nexts player's turn</p>
     *
     */
    public void next_player_id(){      // Gives the turn to the next player
        if(player_id<3){
            player_id++;}
        else if(player_id==3){
            player_id=0;
        }

    }

    /**
     * <b>accessor(selector)</b>:Returns the player who is playing at the moment
     *  <b>post-condition</b>:Returns the id of the player's turn
     * @return the integer player_id(int)
     */
    public int getPlayer_id(){return player_id;}

    /**
     * * <b>Observer</b>:Returns if the game has reached to the end<br />
     * <p><b>Postcondition:</b> Returns if the game ends </p>
     * @param landslide_ the number of landslides that are on board
     * @return true if the game reached to the end and false otherwise
     */
    public boolean EndofgameCheck(ArrayList<Landslide> landslide_){ //Checks if the game is over(if the 16 land slides are filled)
        return landslide_.size() == reallandslides;
    }

    /**
     * b>Observer</b>:Returns if the game meets the requirements to start<br />
     * <p><b>Postcondition:</b> Returns if the game is ready to start </p>
     * @param skeletons a arraylist of skeletons that are currently on board
     * @param amphora a arraylist of amphoras that are currently on board
     * @param statue a arraylist of statues that are currently on board
     * @param mosaic a arraylist of mosaics that are currently on board
     * @return true if it meets the requirements to start and false otherwise
     */
    // checks if there is at least one plaque at every site
    public boolean StartofgameCheck(ArrayList<SkeletonTile> skeletons,ArrayList<AmphoraTile> amphora,ArrayList<StatueTile> statue,ArrayList<MosaicTile> mosaic){
        return skeletons.size() == 1 && amphora.size() == 1 && statue.size() == 1 && mosaic.size() == 1;
    }
}

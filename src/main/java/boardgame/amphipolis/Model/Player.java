package boardgame.amphipolis.Model;


import boardgame.amphipolis.Enums.Colors.AmphorasColors;
import boardgame.amphipolis.Enums.Colors.MosaicColor;
import boardgame.amphipolis.Enums.Colors.PlayerColor;
import boardgame.amphipolis.Enums.Skeleton_Details.*;
import boardgame.amphipolis.Enums.StatuePeak;
import boardgame.amphipolis.Model.Characters.Character;
import boardgame.amphipolis.Model.Characters.*;
import boardgame.amphipolis.Model.tiles.*;

import java.util.ArrayList;

/**
 *Class Player describes the attributes of a player
 * provides modification methods and methods that help pointing out the winner
 */
public class Player{

   protected PlayerColor player_color;
   protected String player_name;
   public ArrayList<Character> player_Heroes;
   public  ArrayList<Tile> player_items=new ArrayList<>();

    /**
     * <b>constructor</b>: Constructs a new Player with the given
     *parameter color and name.<br />
     *<b>post-condition</b>: Creates and initializes a player with the given
     *color and name_ and initialize the ArrayList player_Heroes which
     * contains of the four heroes that the player have.
     * @param color the color of the player
     * @param name_ the name of the player
     */
   public  Player(PlayerColor color,String name_){
        player_Heroes=new ArrayList<>();
        player_color=color;
        player_name=name_;
        player_Heroes.add(new ASSISTANT());
        player_Heroes.add(new DIGGER());
        player_Heroes.add(new PROFESSOR());
        player_Heroes.add(new ARCHAEOLOGIST());

    }


    /**
     *<b>transformer(mutative)</b>: It sets the name of a player <br />
     *<b>postcondition</b>:the name of the player is changed to id
     * @param player_name the name of the player
     */
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    /**
     *  <b>accessor(selector)</b>:Returns the color of a player <br />
     * <p><b>Postcondition:</b> returns the color of a player </p>
     * @return the value player_color(PLayerColor)
     */
    public PlayerColor getPlayer_color() {
        return player_color;
    }

    /**
     *  <b>accessor(selector)</b>:Returns the name of a player <br />
     * <p><b>Postcondition:</b> returns the name of a player </p>
     * @return the value name(String)
     */
    public String getPlayer_name() {
        return player_name;
    }


    /**
     * <b>accessor(selector)</b>:Returns the total points of a player <br />
     * <p><b>Postcondition:</b> returns the total points of a player </p>
     * @param statuesize_1 the placement of the player in relation to his caryatids
     * @param statuesize_2 the placement of the player in relation to his sphinxs
     * @return the value points(int)
     */
    public int pointscalculator(StatuePeak statuesize_1, StatuePeak statuesize_2) {
        int points = 0, mosaicpoints = 0;
        int last_mosaics = 0;
        int bupper = 0, supper = 0, bbottom = 0, sbottom = 0;  //big upper pieces,small upper pieces,big bottom pieces,small bottom pieces.
        int bskeletons=0 , sskeletons=0 ; // big skeletons that have been created,small skeletons tha have been created
        int nopairparent=0, nopairchild = 0, family_pairs = 0, bpairs=0, skeletonpoints = 0; // big skeletons that aren't paired,small skeletons that aren't paired,pairs of family,pairs of big skeletons without child,total of skeleton points
        int statuepoint , amphorapoints = 0,sphinx_point=0,caryatid_point=0;
        int[] amphora_color = new int[6];
        ArrayList<MosaicTile> mosaic_ = new ArrayList<>();
        ArrayList<SkeletonTile> skeleton_ = new ArrayList<>();
        ArrayList<SphinxTile> sphinx_ = new ArrayList<>();
        ArrayList<CaryatidTile> caryatid_ = new ArrayList<>();
        ArrayList<AmphoraTile> amphora_ = new ArrayList<>();
        int[] mosaic_colors = new int[3];
        int item_counter = 0;
        if(!player_items.isEmpty()){

            for (int i = 0; i < player_items.size(); i++) {
                if (player_items.get(i) instanceof MosaicTile) {
                    mosaic_.add((MosaicTile) player_items.get(i));
                } else if (player_items.get(i) instanceof SkeletonTile) {
                    skeleton_.add((SkeletonTile) player_items.get(i));
                } else if (player_items.get(i) instanceof SphinxTile) {
                    sphinx_.add((SphinxTile) player_items.get(i));
                } else if (player_items.get(i) instanceof CaryatidTile) {
                    caryatid_.add((CaryatidTile) player_items.get(i));
                } else if (player_items.get(i) instanceof AmphoraTile) {
                    amphora_.add((AmphoraTile) player_items.get(i));
                }

            }
                //Mosaic Points Calculator
            if(!mosaic_.isEmpty()){
                for (int i = 0; i < mosaic_.size(); i++) {
                    if (mosaic_.get(i).getColor() == MosaicColor.green) {
                        mosaic_colors[0]++;
                    } else if (mosaic_.get(i).getColor() == MosaicColor.red) {
                        mosaic_colors[1]++;
                    } else {
                        mosaic_colors[2]++;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    mosaicpoints = mosaicpoints + (mosaic_colors[i] / 4) * 4;   //separates the same color mosaics in pairs of 4
                    last_mosaics = last_mosaics + mosaic_colors[i] % 4;  // adds the mosaics that couldn't be paired in a integer
                }
                mosaicpoints = mosaicpoints + (last_mosaics / 4) * 2;
            } else{
                    mosaicpoints=0;
            }
            //Skeletons Points Calculation
            for (SkeletonTile skeletonTile : skeleton_) {
                if (skeletonTile.getSize_() == Skeleton_size.big && skeletonTile.getSide_() == Skeleton_Side.bottomside) {
                    bbottom++;
                } else if (skeletonTile.getSize_() == Skeleton_size.big && skeletonTile.getSide_() == Skeleton_Side.upperside) {
                    bupper++;
                } else if (skeletonTile.getSize_() == Skeleton_size.small && skeletonTile.getSide_() == Skeleton_Side.bottomside) {
                    sbottom++;
                } else if (skeletonTile.getSize_() == Skeleton_size.small && skeletonTile.getSide_() == Skeleton_Side.upperside) {
                    supper++;
                }
            }
            if (bupper!=bbottom){
                bskeletons = (Math.min(bupper, bbottom));
            }else{
                bskeletons=bupper;
            }
            if(supper!=sbottom){
                sskeletons = (Math.min(supper, sbottom));}
            else{
                sskeletons=sbottom;
            }
            bpairs = bskeletons / 2;
            nopairparent = bskeletons % 2;
            if(sskeletons!=0&&bpairs>0){
                nopairchild = bpairs % sskeletons;
                family_pairs = bpairs - nopairchild;
            }else if(sskeletons==0){
                nopairparent=nopairparent+bpairs*2;
            }
            else{

                nopairchild=sskeletons;

            }
            skeletonpoints = family_pairs * 6 + nopairchild + nopairparent;

            //Statues points
            if(!caryatid_.isEmpty()) {
                if (statuesize_1 != StatuePeak.none) {
                    if (statuesize_1 == StatuePeak.bigger) {
                        caryatid_point = 6;
                    } else if (statuesize_1 == StatuePeak.medium) {
                        caryatid_point = 3;
                    }
                }
            }
            if ((!sphinx_.isEmpty())) {
                if (statuesize_2 != StatuePeak.none) {
                    if (statuesize_2 == StatuePeak.bigger) {
                        sphinx_point = 6;
                    } else if (statuesize_2 == StatuePeak.medium) {
                        sphinx_point = 3;
                    }
                }
            }
            statuepoint=sphinx_point+caryatid_point;

            //Amphoreas points
            for (int i = 0; i < amphora_.size(); i++) {
                if (amphora_.get(i).getColor() == AmphorasColors.blue) {
                    amphora_color[0]++;
                } else if (amphora_.get(i).getColor() == AmphorasColors.red) {
                    amphora_color[1]++;
                } else if (amphora_.get(i).getColor() == AmphorasColors.brown) {
                    amphora_color[2]++;
                } else if (amphora_.get(i).getColor() == AmphorasColors.green) {
                    amphora_color[3]++;
                } else if (amphora_.get(i).getColor() == AmphorasColors.yellow) {
                    amphora_color[4]++;
                } else if (amphora_.get(i).getColor() == AmphorasColors.purple) {
                    amphora_color[5]++;
                }
            }
            while (amphora_color[0] != 0 || amphora_color[1] != 0 || amphora_color[2] != 0 || amphora_color[3] != 0 || amphora_color[4] != 0 ||amphora_color[5] != 0){
                for (int i = 0; i < 6; i++) {
                    if (amphora_color[i] > 0) {
                        amphora_color[i]--;
                        item_counter++;
                    }
                }
                if (item_counter == 6) {
                    amphorapoints = amphorapoints + 6;
                } else if (item_counter == 5) {
                    amphorapoints = amphorapoints + 4;
                } else if (item_counter == 4) {
                    amphorapoints = amphorapoints + 2;
                } else if (item_counter == 3) {
                    amphorapoints = amphorapoints + 1;
                }
                item_counter = 0;
            }
            points=statuepoint+mosaicpoints+amphorapoints+skeletonpoints;
        }else{
            points=0;
        }
     return points;
    }

}

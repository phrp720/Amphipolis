package boardgame.amphipolis.Model;


import boardgame.amphipolis.Model.tiles.Tile;

import java.util.ArrayList;

/**
 * Class Bag describes the bag of the game that contains the tiles
 */
public class Bag {
     public ArrayList<Tile> tilesinbag=new ArrayList<>();

    /**
     * <b>accessor(selector)</b>:Returns a tile ArrayList in which the drew item is deleted   <br />
     * <p><b>Postcondition:</b> Returns a tile ArrayList in which the drew item is deleted </p>
     *
     * @param plaque_pos the position of the plaque in the arraylist
     * @param bagthings  the arraylist that contains tiles
     */
    public void draw_plaque(int plaque_pos, ArrayList<Tile> bagthings) {
        bagthings.remove(plaque_pos);
    }
}

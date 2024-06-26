package boardgame.amphipolis.Model.tiles;


import boardgame.amphipolis.Enums.Colors.AmphorasColors;

/**
 *The Class AmphoraTile  describes a tile of the game with the name amphora
 * and gives specific information about its appearance
 */
public class AmphoraTile extends FindingTile {
   protected AmphorasColors color;

    /**
     *<b>constructor</b>: Constructs a new AmphoraTile with the given
     * parameter color.<br />
     * <b>postcondition</b>: Creates and initializes a amphora with the given
     * color
     * @param color_ the color of the amphora
     */
    public AmphoraTile(AmphorasColors color_){
        this.color=color_;
    }

    /**
     * <b>accessor(selector)</b>: Returns the color of a Amphora. <br />
     * <b>postcondition</b>: Returns the color of a Amphora.
     * @return the value color(AmphorasColors)
     */
    public AmphorasColors getColor() {
        return color;
    }
}

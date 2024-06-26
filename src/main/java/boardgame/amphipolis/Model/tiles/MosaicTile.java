package boardgame.amphipolis.Model.tiles;

import boardgame.amphipolis.Enums.Colors.MosaicColor;

/**
 * The Class MosaicTile  describes a tile of the game with the name mosaic
 * and gives specific information about its appearance
 */
public class MosaicTile extends FindingTile{
MosaicColor color;

    /**
     *<b>constructor</b>: Constructs a new MosaicTile with the given
     * parameter color.<br />
     * <b>postcondition</b>: Creates and initializes a mosaic with the given
     * color
     * @param color_ the color of the mosaic
     */
public MosaicTile(MosaicColor color_){
    color=color_;
}

    /**
     * <b>accessor(selector)</b>: Returns the color of a mosaic. <br />
     * <b>postcondition</b>: Returns the color of a mosaic.
     * @return the value color(MosaicColor)
     */
    public MosaicColor getColor() {
        return color;
    }
}

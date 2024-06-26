package boardgame.amphipolis.Model.tiles;

import boardgame.amphipolis.Enums.Skeleton_Details.Skeleton_Side;
import boardgame.amphipolis.Enums.Skeleton_Details.Skeleton_size;

/**
 * The Class SkeletonTile  describes a tile of the game with the name skeleton
 * and gives specific informations about its appearance
 */
public class SkeletonTile extends  FindingTile{
    protected Skeleton_size size_;
    protected Skeleton_Side side_;

    /**
     * <b>constructor</b>: Constructs a new SkeletonTile with the given
     * parameter sizeofskeleton and sideofskeleton.<br />
     * <b>postcondition</b>: Creates and initializes a skeleton with the given
     * size and side of him
     * @param sizeofskeleton the size of the skeleton
     * @param sideofskeleton the side of the skeleton
     */
   public  SkeletonTile(Skeleton_size sizeofskeleton,Skeleton_Side sideofskeleton){
        this.size_=sizeofskeleton;
        this.side_=sideofskeleton;
    }

    /**
     * <b>accessor(selector)</b>: Returns the side of a skeleton. <br />
     * <b>postcondition</b>: Returns the side of a skeleton.
     * @return the value side_(Skeleton_Side)
     */
    public Skeleton_Side getSide_() {
        return side_;
    }

    /**
     * <b>accessor(selector)</b>: Returns the size of a skeleton. <br />
     * <b>postcondition</b>: Returns the size of a skeleton.
     * @return the value size_(Skeleton_size)
     */
    public Skeleton_size getSize_() {
        return size_;
    }
}

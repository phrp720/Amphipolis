package boardgame.amphipolis.Model.Characters;

/**
 *The abstract Class character describes the main characteristics of a character
 */
public abstract  class Character {
    protected boolean used_character; // 1 if we can use a character and 0 if we can't use him

    /**
     * <b>constructor</b>: Constructs a Character with the given
     *parameter number of tiles(not)  ability(ablt)   and usability(usable).<br />
     *<b>postcondition</b>: Creates and initializes a Character with the given
     *number of tiles that he can take,his ability and his usability at the moment.
     * @param usable the availability of player
     */
     public Character(boolean usable){
        used_character=usable;

    }


    /**
     *<b>Observer</b>: Returns the availability of a character. <br />
     *<b>postcondition</b>: Returns if the character is used or not.
     * @return true if the character isn't used and false otherwise
     */
    public boolean getUsed_character() {
        return used_character;
    }

    /**
     * <b>transformer(mutative)</b>:Sets the availability  of a character
     * <b>postcondition</b>:Sets the availability of a character,if true the character is available otherwise he isn't
     * @param usability  the availability of character
     */
public void setUsed_character(boolean usability){
used_character=usability;
}
}
package org.mjd.hackerrank;

public enum Corner
{
 TOP_LEFT,
 TOP_RIGHT,
 BOTTOM_LEFT,
 BOTTOM_RIGHT;

    private static final String DOWN_IDENTIFIER = "DOWN";
    private static final String UP_IDENTIFIER = "UP";
    private static final String RIGHT_IDENTIFIER = "RIGHT";
    private static final String LEFT_IDENTIFIER = "LEFT";


    /**
     * @return the horizontal direction from centre to this corner.
     */
    public String horizontalDirection()
    {
        if ((this == TOP_LEFT) || (this == BOTTOM_LEFT))
        {
            return LEFT_IDENTIFIER;
        }
        return RIGHT_IDENTIFIER;
    }

    /**
     * @return @return the vertical direction from centre to this corner.
     */
    public String verticalDirection()
    {
        if ((this == TOP_LEFT) || (this == Corner.TOP_RIGHT))
        {
            return UP_IDENTIFIER;
        }
        return DOWN_IDENTIFIER;
    }

    /**
     * Finds which corner this index corresponds to assuming a flattened grid array.
     * 
     * @param index
     * @param gridSize
     * @return {@link Corner}
     */
    public static Corner findCorner(int index, int gridSize)
    {
        Corner result;
        if (index == 0)
        {
            result = TOP_LEFT;
        }
        else if (index + 1 == gridSize)
        {
            result = TOP_RIGHT;
        }
        else if (index == ((gridSize * gridSize) - gridSize))
        {
            result = BOTTOM_LEFT;
        }
        else if (index + 1 == gridSize * gridSize)
        {
            result = BOTTOM_RIGHT;
        }
        else
        {
            throw new IllegalStateException("Not a corner index! The princess must be in a corner so the game " +
                            "state is invalid.");
        }
        return result;
    }
}
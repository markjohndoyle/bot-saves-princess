import java.util.Scanner;


/**
 * Princess Peach is trapped in one of the four corners of a square grid. You are in the center of the grid and can move
 * one step at a time in any of the four directions. Can you rescue the princess?
 * 
 * Input format
 * 
 * The first line contains an odd integer N (3 <= N < 100) denoting the size of the grid. This is followed by an NxN
 * grid. Each cell is denoted by '-' (ascii value: 45). The bot position is denoted by 'm' and the princess position is
 * denoted by 'p'.
 * 
 * Grid is indexed using Matrix Convention
 * 
 * Output format
 * 
 * Print out the moves you will take to rescue the princess in one go. The moves must be separated by '\n', a newline.
 * The valid moves are LEFT or RIGHT or UP or DOWN.
 * 
 * Complete the function displayPathtoPrincess which takes in two parameters - the integer N and the character array
 * grid. The grid will be formatted exactly as you see it in the input, so for the sample input the princess is at
 * grid[2][0]. The function shall output moves (LEFT, RIGHT, UP or DOWN) on consecutive lines to rescue/reach the
 * princess. The goal is to reach the princess in as few moves as possible.
 * 
 * The above sample input is just to help you understand the format. The princess ('p') can be in any one of the four
 * corners.
 * 
 */
public class BotSearch
{
    private static final String PRINCESS_IDENTIFIER = "p";


    private static void displayPathtoPrincess(int gridSize, String[] grid, int princessIndex)
    {
        printMovesToCorner(calculateMoveCount(gridSize), findCorner(princessIndex, gridSize));
    }

    /**
     * Calculates the number of moves required to get to the corner using the following formula:
     * 
     * \frac{n}{2}-\frac{1}{2}
     * 
     * where n = the grid width or height.
     *  
     * @param gridSize
     * @return
     */
    private static int calculateMoveCount(int gridSize)
    {
        return (gridSize / 2) - (1 / 2);
    }

    private static void printMovesToCorner(int moveCount, Corner corner)
    {
        String horizontalDirection = corner.horizontalDirection();
        String verticalDirection = corner.verticalDirection();
        for (int i = 0; i < moveCount; i++)
        {
            System.out.println(horizontalDirection);
            System.out.println(verticalDirection);
        }

    }


    public static enum Corner
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
    }


    /**
     * Finds which corner this index corresponds to assuming a flattened grid array.
     * 
     * @param index
     * @param gridSize
     * @return
     */
    private static Corner findCorner(int index, int gridSize)
    {
        Corner result;
        if (index == 0)
        {
            result = Corner.TOP_LEFT;
        }
        else if (index + 1 == gridSize)
        {
            result = Corner.TOP_RIGHT;
        }
        else if (index == ((gridSize * gridSize) - gridSize))
        {
            result = Corner.BOTTOM_LEFT;
        }
        else if (index + 1 == gridSize * gridSize)
        {
            result = Corner.BOTTOM_RIGHT;
        }
        else
        {
            throw new IllegalStateException("Not a corner index! The princess mus tbe in a corner so the game " +
                            "state is invalid.");
        }
        return result;
    }

    private static boolean isPrincess(String gridElem)
    {
        if (gridElem.equals(PRINCESS_IDENTIFIER))
        {
            return true;
        }
        return false;
    }

    /**
     * Aggressive method starts the route finding as soon as the princess is input.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        try (Scanner in = new Scanner(System.in))
        {
            int gridSize;
            gridSize = in.nextInt();
            String grid[] = new String[gridSize * gridSize];
            int rowInc = 0;
            for (int i = 0; i < gridSize * gridSize; i++)
            {
                String row = in.next();
                for (char elem : row.toCharArray())
                {
                    int flattenedIndex = i + rowInc;
                    grid[flattenedIndex] = Character.toString(elem);
                    if (isPrincess(grid[flattenedIndex]))
                    {
                        displayPathtoPrincess(gridSize, grid, flattenedIndex);
                        return;
                    }
                    rowInc++;
                }
                rowInc--;
            }

        }

    }
}
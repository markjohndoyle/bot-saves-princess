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
    private static void displayPathtoPrincess(int gridSize, String[] grid, int princessIndex)
    {
        int moveCount = (gridSize / 2) - (1 / 2);
        moveToCorner(moveCount, findCorner(princessIndex, gridSize));
    }

    private static void moveToCorner(int moveCount, Corner corner)
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

        public String horizontalDirection()
        {
            if ((this == TOP_LEFT) || (this == BOTTOM_LEFT))
            {
                return "LEFT";
            }
            return "RIGHT";
        }

        public String verticalDirection()
        {
            if ((this == TOP_LEFT) || (this == Corner.TOP_RIGHT))
            {
                return "UP";
            }
            return "DOWN";
        }
    }


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
            throw new IllegalStateException("Not a corner index!");
        }
        return result;
    }

    private static boolean isPrincess(String gridElem)
    {
        if (gridElem.equals("p"))
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
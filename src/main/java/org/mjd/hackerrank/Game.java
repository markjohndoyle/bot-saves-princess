package org.mjd.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;


public class Game extends Observable
{

    private static final String PRINCESS_IDENTIFIER = "p";

    private int gridSize;

    private String grid[][];


    public void setGridSize(int newGridSize)
    {
        gridSize = newGridSize;
        grid = new String[gridSize][gridSize];
    }

    public void parseGridRow(String gridRow, int row)
    {
        int column = 0;
        for (char elem : gridRow.toCharArray())
        {
            grid[column][row] = Character.toString(elem);
            if (isPrincess(grid[column][row]))
            {
                List<String> directions =
                    createMovementInstructions(calculateMoveCount(gridSize), Corner.findCorner(column + row, gridSize));
                setChanged();
                notifyObservers(directions);
            }
            column++;
        }
        column = 0;
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

    private static List<String> createMovementInstructions(int moveCount, Corner corner)
    {
        List<String> directions = new ArrayList<>(moveCount);
        String horizontalDirection = corner.horizontalDirection();
        String verticalDirection = corner.verticalDirection();
        for (int i = 0; i < moveCount; i++)
        {
            directions.add(horizontalDirection);
            directions.add(verticalDirection);
        }
        return directions;
    }

    /**
     * Starts the game by reading in the game data.
     */
    public void start()
    {
        readInGameData();
    }

    private void readInGameData()
    {
        try (Scanner in = new Scanner(System.in))
        {
            setGridSize(Integer.parseInt(in.next()));

            for (int row = 0; row < gridSize; row++)
            {
                parseGridRow(in.next(), row);
            }
        }
    }

}

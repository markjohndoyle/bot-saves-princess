package org.mjd.hackerrank;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


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

    /**
     * Aggressive method starts the route finding as soon as the princess is input.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        DirectionReceiver writer = new DirectionReceiver();
        game.addObserver(writer);
        game.start();
    }


    public static class DirectionReceiver implements Observer
    {
        @Override
        public void update(Observable o, Object arg)
        {
            displayPathtoPrincess(List.class.cast(arg));
        }
    }


    private static void displayPathtoPrincess(List<String> directions)
    {
        for (String nextDirection : directions)
        {
            System.out.println(nextDirection);
        }
    }

}
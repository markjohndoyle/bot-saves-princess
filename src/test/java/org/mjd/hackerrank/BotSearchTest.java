package org.mjd.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class BotSearchTest
{

    private String[][] testGrid;

    @Mock
    private Observer mockDirectionListener;


    @Before
    public void setUpGrid()
    {
        testGrid = new String[][] {
                                    { "-", "-", "-" },
                                    { "-", "m", "-" },
                                    { "p", "-", "-" } };
                                    
    }

    @Test
    public void test()
    {
        Game gameUnderTest = new Game();
        gameUnderTest.addObserver(mockDirectionListener);
        gameUnderTest.setGridSize(3);
        gameUnderTest.parseGridRow("--p", 0);
        
        List<String> expectedDirections = new ArrayList<>();
        expectedDirections.add("RIGHT");
        expectedDirections.add("UP");
        verify(mockDirectionListener).update(eq(gameUnderTest), eq(expectedDirections));
    }
    
}

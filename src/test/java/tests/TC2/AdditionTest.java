package tests.TC2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdditionTest extends BaseTest {

    @Test
    public void testTwoNumberAddition(){
        int result = 6 + 6;
        Assert.assertEquals(result,12,"Result should be 7");
    }

    @Test
    public void testThreeNumberAddition(){
        int result = 6 + 4 + 2;
        Assert.assertEquals(result,12,"Result should be 12");
    }
}

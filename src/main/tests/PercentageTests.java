import com.omega13.codecademy.validation.Validation;
import org.junit.Assert;
import org.junit.Test;

public class PercentageTests {
    private Validation validation = new Validation();

    @Test
    public void PercentageTrue() throws Exception{
        Assert.assertTrue(validation.PercentageValidation(50));
    }

    @Test
    public void PercentageBoundriesZero() throws Exception {
        Assert.assertTrue(validation.PercentageValidation(0));
    }

    @Test
    public void PercentageBoundriesHundred() throws Exception {
        Assert.assertTrue(validation.PercentageValidation(100));
    }

    @Test
    public void PercentageFalse() throws Exception {
        Assert.assertFalse(validation.PercentageValidation(105));
    }

    @Test
    public void PercentageFalseNegative() throws Exception {
        Assert.assertFalse(validation.PercentageValidation(-15));
    }
}

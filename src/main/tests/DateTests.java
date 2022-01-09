import com.omega13.codecademy.validation.Validation;
import org.junit.Assert;
import org.junit.Test;

public class DateTests {
    private Validation validation = new Validation();

    @Test
    public void DateTrue() throws Exception{
        Assert.assertTrue(validation.DateValidation(10, 10, 2015));
    }

    @Test
    public void DateLeapYear() throws Exception{
        Assert.assertTrue(validation.DateValidation(29, 2, 2016));
    }

    @Test
    public void DateNotLeapYear() throws Exception{
        Assert.assertTrue(validation.DateValidation(28, 2, 2015));
    }

    @Test
    public void DateFalse() throws Exception{
        Assert.assertFalse(validation.DateValidation(31, 4, 2020));
    }
}

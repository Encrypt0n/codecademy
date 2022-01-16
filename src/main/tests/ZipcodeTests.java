import com.omega13.codecademy.validation.Validation;
import org.junit.Assert;
import org.junit.Test;

public class ZipcodeTests {
    private Validation validation = new Validation();

    @Test
    public void ZipcodeTrue() throws Exception{
        Assert.assertTrue(validation.ZipcodeValidation("1234 AB"));
    }

    @Test
    public void ZipcodeStartsWithZero() throws Exception{
        Assert.assertFalse(validation.ZipcodeValidation("0123 AB"));
    }

    @Test
    public void ZipcodeNoSpace() throws Exception{
        Assert.assertFalse(validation.ZipcodeValidation("1234AB"));
    }

    @Test
    public void ZipcodeNoUpperCase() throws Exception{
        Assert.assertFalse(validation.ZipcodeValidation("1234 ab"));
    }
}

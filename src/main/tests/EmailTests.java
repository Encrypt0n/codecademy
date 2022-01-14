import com.omega13.codecademy.validation.Validation;
import org.junit.Assert;
import org.junit.Test;

public class EmailTests {
    private Validation validation = new Validation();

    @Test
    public void EmailTestTrue() throws Exception {
        Assert.assertTrue(validation.EmailValidation("student@avans.nl"));

    }

    @Test
    public void EmailTestFalse() throws Exception {
        Assert.assertFalse(validation.EmailValidation("student@avans"));
    }
}

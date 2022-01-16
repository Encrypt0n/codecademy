import com.omega13.codecademy.validation.Validation;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateTests {
    private Validation validation = new Validation();

    @Test
    public void DateTrue() throws Exception{
        Assert.assertTrue(validation.DateValidation(LocalDate.now()));
    }

    @Test
    public void DateFalse() throws Exception{
        Assert.assertFalse(validation.DateValidation(LocalDate.now().plusDays(1)));
    }
}

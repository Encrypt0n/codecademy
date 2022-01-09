import com.omega13.codecademy.validation.Validation;
import org.junit.Assert;
import org.junit.Test;

public class URLTests {
    private Validation validation = new Validation();

    @Test
    public void URLTestHttps() throws Exception{
        Assert.assertTrue(validation.URLValidation("https://www.google.com"));
    }

    @Test
    public void URLTestHttp() throws  Exception{
        Assert.assertTrue(validation.URLValidation("http://www.google.com"));
    }

    @Test
    public void UrlTestFail() throws Exception{
        Assert.assertFalse(validation.URLValidation("www.google.com"));
    }
}

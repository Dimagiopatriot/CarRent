package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {


    @Test
    public void testNameRegEx(){
        Validator validator = Validator.getInstance();
        boolean actual = validator.validateNameOrSurname("Огієнко");
        assertEquals(true, actual);
    }
}

package Tests;

import GUI.controllers.homePageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageControllerTest {

    private homePageController controller;

    @BeforeEach
    public void setUp() {
        controller = new homePageController();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(controller.isEmpty(""));
        assertTrue(controller.isEmpty("   "));
        assertFalse(controller.isEmpty("text"));
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(controller.isValidEmail("test@example.com"));
        assertFalse(controller.isValidEmail("invalid-email"));
    }

    @Test
    public void testIsValidPostalCode() {
        assertTrue(controller.isValidPostalCode("1234AB"));
        assertFalse(controller.isValidPostalCode("12AB34"));
        assertFalse(controller.isValidPostalCode("1234"));
    }

    @Test
    public void testIsValidBirthDate() {
        LocalDate validDate = LocalDate.of(2000, 2, 1);
        LocalDate futureDate = LocalDate.now().plusDays(1);

        assertTrue(controller.isValidBirthDate(validDate));
        assertFalse(controller.isValidBirthDate(futureDate));
    }
}

import controller.PatientController;
import controller.SupplyController;
import model.Patient;
import model.PatientSupply;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class MainTest {
    private PatientController p_c ;
    private SupplyController s_c ;

    @BeforeEach
    public void setup() {
        p_c = PatientController.getInstance();
        s_c = SupplyController.getInstance();
    }
    @Test
    public void AddEmptyPatientIdNotExecuted() {
        Exception thrown = Assertions.assertThrows(SQLException.class, () -> {
            Patient patient = p_c.insertPatient(" ", "test", "2000-01-01", "054-75214587", "street", "1", "1");
        });
    }
    @Test
    public void AddTestPatientExecuted() {
        Assertions.assertDoesNotThrow(() -> {
            Patient patient = p_c.insertPatient("1", "test", "2000-01-01", "054-75214587", "street", "1", "1");
        });
    }
    @Test
    public void AddDuplicateTestPatientNotExecuted() {
        Exception thrown = Assertions.assertThrows(SQLException.class, () -> {
            Patient patient1 = p_c.insertPatient("1", "test1", "2000-01-01", "054-75214587", "street", "2", "2");
        });
        //Assertions.assertFalse(!thrown.getMessage().contains("Duplicate entry"), "Another same Patien inserted to DB");
    }
    @Test
    public void GetSupplyForExistedPatient() {
        Assertions.assertDoesNotThrow(() -> {
            List<PatientSupply> p_s = s_c.getSupplyForPatient("123");
            Assertions.assertEquals(p_s.get(0).id, "123");
        });
    }

    @Test
    public void DeleteTestPatientExecuted() {
        Assertions.assertDoesNotThrow(() -> {
            p_c.deletePatient ("1");
        });
    }
    @Test
    public void DeleteNotExistTestPatientNotExecuted() {
        Assertions.assertDoesNotThrow(() -> {
            p_c.deletePatient ("1");
        });
    }
}

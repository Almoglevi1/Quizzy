package il.ac.hit.quizzy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCSVQuizFilesDAOTest {
    private SimpleCSVQuizFilesDAO daoInstance;

    @BeforeEach
    void setUp() {
        /** Initialize the DAO instance before each test */
        daoInstance = (SimpleCSVQuizFilesDAO) SimpleCSVQuizFilesDAO.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance_shouldReturnValidInstance() {
        /** Ensure that the DAO instance is not null */
        assertNotNull(daoInstance);
    }
}

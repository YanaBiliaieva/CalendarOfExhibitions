package exhibitions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import exhibitions.controller.ConfigurationManager;
import exhibitions.controller.command.CommandResult;
import exhibitions.controller.command.LoginCommand;
import exhibitions.util.H2ConnectionManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginCommandTest {
    private static final H2ConnectionManager testDbManager = H2ConnectionManager.getH2ConnectionManager();

    private static final String INSERT_DEFAULTS_FILE_PATH =
            "src\\test\\resources\\users.sql";

    private static final String EXPECTED_RESULT = ConfigurationManager.getProperty("path.page.index");

    private static final String CORRECT_LOGIN = "admin";
    private static final String CORRECT_PASS = "12345";

    private static CommandResult result;

    @BeforeAll
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
        H2ConnectionManager.releaseDataSource();
    }

    @BeforeEach
    void setUp() {
        testDbManager.executeSQLScriptsFromFile(INSERT_DEFAULTS_FILE_PATH);
    }

    @AfterEach
    void tearDown() throws SQLException {
        result = null;
        testDbManager.truncateTable("users");
        testDbManager.truncateTable("roles");
    }

    @Test
    void execute_admin_credentials_return_admin_page_redirect() {
        HttpSession mockSession = mock(HttpSession.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

        when(mockRequest.getMethod()).thenReturn("POST");
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getParameter("login")).thenReturn(CORRECT_LOGIN);
        when(mockRequest.getParameter("password")).thenReturn(CORRECT_PASS);
        result = new LoginCommand().execute(mockRequest, mockResponse);
        assertNotNull(result);
        assertEquals(EXPECTED_RESULT, result.property);
    }
}


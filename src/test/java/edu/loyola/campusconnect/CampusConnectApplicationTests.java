package edu.loyola.campusconnect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.datasource.url=jdbc:h2:mem:bootstrap;DB_CLOSE_DELAY=-1")
class CampusConnectApplicationTests {
    @LocalServerPort
    private int port;

    @Test
    void startsHttpServer() throws Exception {
        assertTrue(port > 0, "The embedded HTTP server must bind an available port");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:" + port + "/__bootstrap_probe__"))
                .GET()
                .build();
        HttpResponse<Void> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.discarding());
        assertEquals(404, response.statusCode(), "An unmapped probe must return HTTP 404");
    }
}

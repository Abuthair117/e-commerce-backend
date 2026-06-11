package fellow.dev.demo.chatbot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fellow.dev.demo.ecomentity.Productentity;
import fellow.dev.demo.ecomrepo.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class chatbotservice {

    @Autowired
    Productrepo productrepo;

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {

        List<Productentity> products = productrepo.findAll();

        String productData = products.stream()
                .map(p -> p.getName() + " - ₹" + p.getPrice())
                .reduce("", (a, b) -> a + "\n" + b);

        String prompt = """
    You are an e-commerce assistant.

    ONLY use the product list below.
    Do NOT invent new products.

    PRODUCTS:
    %s

    USER QUESTION:
    %s
    """.formatted(productData, message);

        return callOllama(prompt);
    }

    private String callOllama(String prompt) {
        try {
            URL url = new URL("http://localhost:11434/api/generate");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            // IMPORTANT: escape quotes in prompt
            String safePrompt = prompt
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", " ")
                    .replace("\r", " ");

            String Json = """
          {
            "model": "mistral",
            "prompt": "%s",
            "stream": false
          }
""".formatted(safePrompt);

            // send request
            try (OutputStream os = conn.getOutputStream()) {
                os.write(Json.getBytes("UTF-8"));
                os.flush();
            }

            // IMPORTANT: handle error response properly
            InputStream stream;
            if (conn.getResponseCode() >= 400) {
                stream = conn.getErrorStream();
            } else {
                stream = conn.getInputStream();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(stream));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            String result = response.toString();

            ObjectMapper mapper = new ObjectMapper();

            JsonNode json = mapper.readTree(result);

            String clean = json.get("response").asText();

            return clean;



        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

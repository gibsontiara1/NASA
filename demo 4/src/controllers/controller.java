import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class NasaController {

    private static final String NASA_API_KEY = 6hKQ0cjNQiJdLhRMZkfSjb1UVlBGv7EZrOthvGE4;

    @GetMapping("/nasa/apod")
    public ResponseEntity<String> getNasaApod(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String start_date,
            @RequestParam(required = false) String end_date,
            @RequestParam(required = false) Integer count,
            @RequestParam(required = false) Boolean thumbs
    ) {
        try {
            String apiUrl = buildApiUrl(date, start_date, end_date, count, thumbs);
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(apiUrl, String.class);
            return ResponseEntity.ok(result);
        } catch (HttpClientErrorException e) {
            // Handle HTTP client errors (4xx)
            return ResponseEntity.status(e.getRawStatusCode())
                    .body("Error from NASA API: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    private String buildApiUrl(String date, String start_date, String end_date, Integer count, Boolean thumbs) {
        StringBuilder apiUrlBuilder = new StringBuilder("https://api.nasa.gov/planetary/apod?api_key=");
        apiUrlBuilder.append(NASA_API_KEY);

        if (date != null) {
            apiUrlBuilder.append("&date=").append(date);
        }

        if (start_date != null) {
            apiUrlBuilder.append("&start_date=").append(start_date);
        }

        if (end_date != null) {
            apiUrlBuilder.append("&end_date=").append(end_date);
        }

        if (count != null) {
            apiUrlBuilder.append("&count=").append(count);
        }

        if (thumbs != null) {
            apiUrlBuilder.append("&thumbs=").append(thumbs);
        }

        return apiUrlBuilder.toString();
    }
}

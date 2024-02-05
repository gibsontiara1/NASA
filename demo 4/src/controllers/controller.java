import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NasaController {

    @Value("${nasa.api.key}")
    private String apiKey;  // Load API key from application.properties or application.yml

    @GetMapping("/nasa/apod")
    public NasaApiResponse getNasaPictureOfTheDay(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String start_date,
            @RequestParam(required = false) String end_date,
            @RequestParam(required = false) Integer count,
            @RequestParam(required = false) Boolean thumbs
    ) {
        // Construct NASA APOD API URL based on provided query parameters
        String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;

        if (date != null) {
            apiUrl += "&date=" + date;
        }

        if (start_date != null) {
            apiUrl += "&start_date=" + start_date;
        }

        if (end_date != null) {
            apiUrl += "&end_date=" + end_date;
        }

        if (count != null) {
            apiUrl += "&count=" + count;
        }

        if (thumbs != null) {
            apiUrl += "&thumbs=" + thumbs;
        }

        // Make a request to the NASA APOD API using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, NasaApiResponse.class);
    }
}
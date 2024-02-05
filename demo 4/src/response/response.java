public class NasaApiResponse {
    private String status;
    private String message;

    public NasaApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}


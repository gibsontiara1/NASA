package com.example.demo.service.nasa.nasa;
import com.example.demo.domain.NasaApiResponse;

public interface NasaService {
    NasaApiResponse getNasaSingle(String date, String thumbs);

    NasaApiResponse[] getNumNasaMulti(String count, String thumb);

    NasaApiResponse[] getDateRangeNasaMulti(String thumbs, String end_date, String start_date);

}
package com.example.demo.service.nasa.impl;

import com.example.demo.domain.NasaApiResponse;
import com.example.demo.service.nasa.nasa.NasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public abstract class NasaServiceImpl implements NasaService{
    private final String NASA_API = "https://api.nasa.gov/planetary/apod?api_key=6hKQ0cjNQiJdLhRMZkfSjb1UVlBGv7EZrOthvGE4";

    @Override
    public NasaApiResponse getNasaSingle(String date, String thumbs){
        RestTemplate restTemplate = new RestTemplate();
        String params = "";
        if(date != null && thumbs != null){
            params = "&date=" + date + "&thumbs=" +thumbs;
        } else if(date != null){
            params = "&date=" + date;
        } else if(thumbs != null){
            params = "&thumbs=" + thumbs;
        }
        return restTemplate.getForObject(NASA_API + params, NasaApiResponse.class);
    }

    @Override
    public NasaApiResponse[] getNumNasaMulti(String count, String thumbs){
        String params = "&count=" + count + (thumbs != null ? "&thumbs=" +thumbs : "");
        return getData(params);
    }

    @Override
    public NasaApiResponse[] getDateRangeNasaMulti(String thumbs, String end_date, String start_date){
        String params = "&start_date=" + start_date + (thumbs != null ? "&thumbs=" + thumbs : "");
        if(end_date != null){
            params += "&end_date=" + end_date;
        }
        return getData(params);
    }

    private NasaApiResponse[] getData (String params){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NasaApiResponse[]> resp = restTemplate.getForEntity(NASA_API + params, NasaApiResponse[].class);
        return resp.getBody();
    }
}

package com.project.spring_search.util;

import com.project.spring_search.repository.ItemDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {
    public String search(String searchItem) throws JSONException {
            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Naver-Client-Id", "IcL8m8HInIjkAecFkPK7");
            headers.add("X-Naver-Client-Secret", "yiG1Qzgl6V");
            String body = "";

            HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
            ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + searchItem, HttpMethod.GET, requestEntity, String.class);
            HttpStatusCode httpStatus = responseEntity.getStatusCode();
            int status = httpStatus.value();
            String response = responseEntity.getBody();
            System.out.println("Response status: " + status);
            System.out.println(response.getClass().getName());

            return response;
        }

    public List<ItemDto> fromJSONtoItems(String result) throws JSONException {
        JSONObject rjson = new JSONObject(result);
        JSONArray items = rjson.getJSONArray("items");
        List<ItemDto> itemLIst = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);

            ItemDto itemDto = new ItemDto(itemJson);

            itemLIst.add(itemDto);
        }

        return itemLIst;
    }
}

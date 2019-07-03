package com.coditas.Assignment_3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    List<String> repoName;

    public int getRepositoryCount(String username) {
        int count=0;
        try {
            String url = "https://api.github.com/users/" + username;
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String sourcecode = EntityUtils.toString(result.getEntity(), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            RepositoriesCount repo = mapper.readValue(sourcecode, RepositoriesCount.class);
            count=Integer.parseInt(repo.getPublic_repos());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<String> getRepositoryName(String username) {
        repoName = new ArrayList<>();
        try {
            String url = "https://api.github.com/users/" + username + "/repos";
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String sourcecode = EntityUtils.toString(result.getEntity(), "UTF-8");
            JSONArray arr = new JSONArray(sourcecode);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                System.out.println(obj.getString("name"));
                repoName.add(obj.getString("name"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repoName;
    }
}
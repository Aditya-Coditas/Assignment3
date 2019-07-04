package com.coditas.Assignment_3;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    List<String> repoName;
    List<String> projName;
    List<String> userName;

    public HttpResponse getHttpResponse(String url)
    {
        HttpResponse result = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            result = httpClient.execute(request);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getRepositoryCount(String username) {
        int count=0;
        try {
            String url = "https://api.github.com/users/" + username;
            HttpResponse result = this.getHttpResponse(url);
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
            HttpResponse result = this.getHttpResponse(url);
            String sourcecode = EntityUtils.toString(result.getEntity(), "UTF-8");
            JSONArray arr = new JSONArray(sourcecode);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                repoName.add(obj.getString("name"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repoName;
    }

    public String isRepoPresent(String username,String repo)
    {
        ArrayList<String> str= (ArrayList<String>) this.getRepositoryName(username);
          for(int i=0;i<str.size();i++)
          {
              if(str.get(i).equals(repo))
                  return repo+" Is Present !!!!!!";
          }
          return "Unable To find this "+repo;
    }

    public List<String>  getProjectNames(String username)
    {
        projName=new ArrayList<>();
        try {
            String url = "https://gitlab.com/api/v4/users/"+username+"/projects";
            HttpResponse result =  this.getHttpResponse(url);
            String sourcecode = EntityUtils.toString(result.getEntity(), "UTF-8");
            JSONArray arr = new JSONArray(sourcecode);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
             projName.add(obj.getString("name"));
            }
         } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return projName;
    }

    public List<String> getProjectUser(int no)
    {
        userName=new ArrayList<>();
        String url="https://gitlab.com/api/v4/projects/"+no+"/users";
        String name = null;
        try{
            HttpResponse result=this.getHttpResponse(url);
            String sourcecode = EntityUtils.toString(result.getEntity(), "UTF-8");
            JSONArray arr = new JSONArray(sourcecode);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                userName.add(obj.getString("name"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userName;
    }


    public String getLanguagedUsed(int id)
    {
        String url="https://gitlab.com/api/v4/projects/"+id+"/languages";
        String sourcecode=null;
        try {
            HttpResponse result = this.getHttpResponse(url);
            sourcecode = EntityUtils.toString(result.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourcecode;
    }

    public String isProjectPresent(String username,String project)
    {
             ArrayList<String> str= (ArrayList<String>) this.getProjectNames(username);
             for(int i=0;i<str.size();i++)
             {
                 if(str.get(i).equals(project))
                     return project+" The Project is Present !!!!";
             }
          return project+" is Not Present !!!!" ;
    }

    public int projectCount(String username)
    {
        JSONArray arr = null;
        try {
            String url = "https://gitlab.com/api/v4/users/" + username + "/projects";
            HttpResponse result = this.getHttpResponse(url);
            String sourcecode = EntityUtils.toString(result.getEntity(), "UTF-8");
            arr = new JSONArray(sourcecode);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return arr.length();
    }

}
package com.coditas.Assignment_3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    Service serv;

    @GetMapping("/coutReposiory/{username}")
    public int getRepositoryCount(@PathVariable String username)
    {
       return serv.getRepositoryCount(username);
    }

    @GetMapping("/reposioryName/{username}")
    public List<String> getRepositoryName(@PathVariable String username)
    {
       return serv.getRepositoryName(username);
    }

    @GetMapping("/isRepositoryPresent/{username}/{repo}")
    public String isRepoPresent(@PathVariable String username,@PathVariable String repo)
    {
       System.out.println(username);
       System.out.println(repo);
        return serv.isRepoPresent(username,repo);
    }

    @GetMapping("/getProjects/{username}")
    public List<String> getProjectsNames(@PathVariable String username)
    {
         return serv.getProjectNames(username);
    }

    @GetMapping("/getUserDetails/{id}")
    public List<String>  getUserDeails(@PathVariable int id)
    {
         return serv.getProjectUser(id);
    }

    @GetMapping("/getUsedLanguages/{id}")
    public String getLanguagedUsed(@PathVariable int id)
    {
       return  serv.getLanguagedUsed(id);
    }

    @GetMapping("/isProjectPrsent/{username}/{project}")
    public String isProjectPresent(@PathVariable String username,@PathVariable String project)
    {
        return serv.isProjectPresent(username,project);
    }

    @GetMapping("/projectCount/{username}")
    public int projectCount(@PathVariable String username)
    {
        return serv.projectCount(username);
    }

}

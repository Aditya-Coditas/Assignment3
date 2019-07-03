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


}

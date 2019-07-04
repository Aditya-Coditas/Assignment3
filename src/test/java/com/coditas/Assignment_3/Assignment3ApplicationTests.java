package com.coditas.Assignment_3;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Assignment3ApplicationTests {

    public static Service serv;
    @BeforeClass
    public static void  init()
    {
        serv=new Service();
    }

   @Test
	public void githubRepositorycountV()
   {
     assertEquals(serv.getRepositoryCount("Aditya-Coditas"),9);
   }


    @Test
    public void githubRepositorycountInV()
    {
        assertNotEquals(serv.getRepositoryCount("Aditya-Coditas"),5);
    }



}

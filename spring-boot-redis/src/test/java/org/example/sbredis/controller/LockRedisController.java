package org.example.sbredis.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LockRedisController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deductStock() throws Exception {
        /*MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/info?currentPage=1&pageSize=10")).andReturn();
        System.out.println(result.getResponse().getContentAsString());*/

        /*mockMvc.perform(MockMvcRequestBuilders.post("/deductStock")
                .param("currentPage", "1")
                .param("pageSize", "10")).andReturn();*/

        for (int i=0; i<10; i++) {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/deductStock")).andReturn();
            System.out.println(result.getResponse());
        }

    }

}

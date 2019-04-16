package cn.transfer.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*=applicationContext.yml")
@WebAppConfiguration("src/main/resources") //1
public class testSsologinCotroller {
	private MockMvc mockMvc; //2
	
	
	
	@Autowired 
	WebApplicationContext wac; //4
	
    @Autowired 
    MockHttpSession session; //5
    
    @Autowired 
    MockHttpServletRequest request; //6
    
    @Before //7
    public void setup() {
    	mockMvc =
    			MockMvcBuilders.webAppContextSetup(this.wac).build(); //2
    	}
	
	@Test
	public void testNormalController() throws Exception{
		//mockMvc.perform(get("/normal")) //8
		mockMvc.perform(get("/licensingInformation"))
				.andExpect(status().isOk())//9
				.andExpect(view().name("page"))//10
				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11
				.andExpect(model().attribute("aa", "aa1"))
				.andExpect(model().attribute("msg", "aa1"));//12
				
	}
	
	

}

package com.flash.app;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.flash.app.controller.FlashSaleController;
import com.flash.app.entity.Product;
import com.flash.app.entity.Users;
import com.flash.app.model.PurchaseOrder;
import com.flash.app.service.IFlashSaleService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = FlashSaleController.class, secure = false)
public class FlashAppApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	IFlashSaleService flashSaleService;
		
	@Test
	public void testGetProducts() throws Exception{
		
		Product product = new Product(1,"Mocktest","Test from Mock", 2000.0);		
		
		Mockito.when(flashSaleService.getItem(Mockito.anyInt())).thenReturn(product);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/ecomm/webapp/product/1");

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testFlashSaleProducts() throws Exception{
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/ecomm/webapp/products/flashSale/1000").accept(
				MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testRegister() throws Exception {
		
		Users user = new Users();
		user.setUsername("testmoc@mail.com");
		user.setMobileNumber("9010010010");

		Mockito.when(flashSaleService.registerUser(Mockito.any(Users.class),Mockito.anyInt())).thenReturn(true);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/ecomm/webapp/register/flashSale/1000")
				.accept(MediaType.APPLICATION_JSON).content("{\"username\":\"viswa@gmail.com\",\"mobilenumber\":\"3487378484\"}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());


	}
	
	@Test
	public void testCheckOut() throws Exception {
		
		Mockito.when(flashSaleService.placeOrder(Mockito.any(PurchaseOrder.class))).thenReturn("Order Placed");

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/ecomm/webapp/order/checkout")
				.accept(MediaType.APPLICATION_JSON).content("{\"username\" : \"angira@gmail.com\",\"productId\" : 5,\"quantity\" : 1,\"flashSaleId\" : 1000 }")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());


	}
	
}

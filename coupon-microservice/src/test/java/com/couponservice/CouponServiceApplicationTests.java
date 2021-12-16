package com.couponservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.couponservice.entity.Coupon;
import com.couponservice.repository.CouponRepository;
import com.couponservice.service.CouponService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceApplicationTests {
	
	
	@Autowired
	CouponService service;
	
	@MockBean
	CouponRepository repository;
	
	 @Test
	  public void findAllTest()
	  {
	  when(repository.findAll()).thenReturn(Stream.of
	    (new Coupon("1","food",2,"20%","swiggy"),new Coupon("2","electronics",1,"10%","boat"))
	    .collect(Collectors.toList()));
	  assertEquals(2,service.findAll().size());
	  }
	 @Test
	 public void saveTest() 
	 {

	   Coupon coupon = new Coupon("3","fashion",1,"35%","ajio");
	   when(repository.save(coupon)).thenReturn(coupon);
	   assertEquals(coupon,service.save(coupon));
		
	 }
	

	
}

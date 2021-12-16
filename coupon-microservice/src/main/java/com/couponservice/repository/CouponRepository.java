package com.couponservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.couponservice.entity.Coupon;

public interface CouponRepository extends MongoRepository<Coupon, String> {

	List<Optional<Coupon>> findByCategory(String category);

	List<Optional<Coupon>> findByCompanyName(String companyName);

}

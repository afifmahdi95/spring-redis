package com.example.springredis.repository;

import com.example.springredis.entity.MasterPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface MasterPriceRepository extends JpaRepository<MasterPrice, BigInteger> {
}

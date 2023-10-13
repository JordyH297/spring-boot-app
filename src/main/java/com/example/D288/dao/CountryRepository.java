package com.example.D288.dao;
import com.example.D288.entity.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin("http://localhost:4200")

public interface CountryRepository extends JpaRepository<Country,Long> {

}

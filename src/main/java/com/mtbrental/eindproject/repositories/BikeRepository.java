package com.mtbrental.eindproject.repositories;

import com.mtbrental.eindproject.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
}

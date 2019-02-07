package com.daria.repo;

import com.daria.domain.Messege;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface MessegeRepo extends CrudRepository<Messege, Long> {


    List<Messege> findByTag(String filter);
}

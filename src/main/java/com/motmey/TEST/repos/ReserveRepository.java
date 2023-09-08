package com.motmey.TEST.repos;


import com.motmey.TEST.Models.Patients;
import com.motmey.TEST.Models.Reserve;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReserveRepository extends CrudRepository<Reserve, Long>
{
    @Query("SELECT p FROM Reserve p WHERE CONCAT(p.name, ' ', p.surname, ' ', p.middle_name, ' ', p.history , ' ', p.blood_type , ' ', p.medical_policy , ' ', p.born_data, ' ', p.email) LIKE %?1%")
    public List<Reserve> findAll(String keyword);
}

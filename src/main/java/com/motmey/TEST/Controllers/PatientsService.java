package com.motmey.TEST.Controllers;


import java.util.List;

import com.motmey.TEST.Models.Patients;
import com.motmey.TEST.repos.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientsService {
    @Autowired
    private PatientsRepository repo;

    public List<Patients> listAll(String keyword)
    {
        if (keyword != null) {
            return repo.findAll(keyword);
        }
        return (List<Patients>) repo.findAll();
    }

    public void save(Patients patients)
    {
        repo.save(patients);
    }

    public Patients get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
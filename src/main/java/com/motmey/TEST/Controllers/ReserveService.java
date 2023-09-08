package com.motmey.TEST.Controllers;

import java.util.List;

import com.motmey.TEST.Models.Reserve;
import com.motmey.TEST.repos.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReserveService
{
    @Autowired
    private ReserveRepository repos;

    public List<Reserve> listAll(String keyword)
    {
        if (keyword != null) {
            return repos.findAll(keyword);
        }
        return (List<Reserve>) repos.findAll();
    }



    public Reserve get(Long id) {
        return repos.findById(id).get();
    }


}
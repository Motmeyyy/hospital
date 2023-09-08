package com.motmey.TEST.Controllers;


import com.motmey.TEST.Models.Patients;
import com.motmey.TEST.Models.Reserve;
import com.motmey.TEST.Models.Time;
import com.motmey.TEST.repos.PatientsRepository;
import com.motmey.TEST.repos.ReserveRepository;
import com.motmey.TEST.repos.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@Controller
public class PatientsController
{
    @Autowired
    private PatientsService service;
    @Autowired
    private ReserveService servicee;
    @Autowired
    private TimeService serviceee;
    @Autowired
    private TimeRepository TRep;
    @Autowired
    private PatientsRepository PRep;
    @Autowired
    private ReserveRepository RRep;

    @GetMapping("/Reserved")
    public String Reserved(Model model, @Param("keyword") String keyword)
    {
        Iterable<Reserve> AllReserved = RRep.findAll();
        model.addAttribute("Reserved", AllReserved);

        List<Reserve> listReserved = servicee.listAll(keyword);
        model.addAttribute("listReserved", listReserved);
        model.addAttribute("keyword", keyword);

        return "reserve";
    }




    @GetMapping("/Patients")
    public String Patients(Model model, @Param("keyword") String keyword)
    {
        Iterable<Patients> AllPatients = PRep.findAll();
        model.addAttribute("Patients", AllPatients);

        List<Patients> listPatients = service.listAll(keyword);
        model.addAttribute("listPatients", listPatients);
        model.addAttribute("keyword", keyword);

        return "pats";
    }

    @GetMapping("/Patients/add")
    public String Patientss(Model model)
    {
        return "pats-add";
    }

    @PostMapping("/Patients/add")
    public String PatientsAdd(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String middle_name,
                              @RequestParam Integer YoB,
                              @RequestParam String group,
                              @RequestParam String policy,
                              @RequestParam String history,
                              Model model)
    {
        Patients pats = new Patients(name,surname,middle_name,history,group,policy,YoB);
        PRep.save(pats);
        return"redirect:/Patients";
    }

    @GetMapping("/Patients/{id}")
    public String Cards(@PathVariable (value="id") long id, Model model)
    {
        if(!PRep.existsById(id))
            return"redirect:/Patients";
        Optional<Patients> pats = PRep.findById(id);
        ArrayList<Patients> res = new ArrayList<>();
        pats.ifPresent(res::add);
        model.addAttribute("CardID", res);
        return "pats-cards";
    }
    @GetMapping("/Reserved/{id}")
    public String ResAll(@PathVariable (value="id") long id, Model model)
    {
        if(!RRep.existsById(id))
            return"redirect:/Reserved";
        Optional<Reserve> pats = RRep.findById(id);
        ArrayList<Reserve> res = new ArrayList<>();
        pats.ifPresent(res::add);
        model.addAttribute("ResAll", res);
        return "ResF";
    }

    @GetMapping("/Patients/{id}/edit")
    public String CardsEdit(@PathVariable (value="id") long id, Model model)
    {
        if(!PRep.existsById(id))
            return"redirect:/Patients";

        Optional<Patients> pats = PRep.findById(id);
        ArrayList<Patients> res = new ArrayList<>();
        pats.ifPresent(res::add);
        model.addAttribute("CardID", res);
        return "card-edit";

    }

    @PostMapping("/Patients/{id}/edit")
    public String CardUpdate(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String middle_name,
                              @RequestParam Integer YoB,
                              @RequestParam String group,
                              @RequestParam String policy,
                              @RequestParam String history,
                             @PathVariable (value="id") long id,
                              Model model)
    {
        Patients pats = PRep.findById(id).orElseThrow();
        pats.setName(name);
        pats.setSurname(surname);
        pats.setMiddle_name(middle_name);
        pats.setBorn_data(YoB);
        pats.setBlood_type(group);
        pats.setMedical_policy(policy);
        pats.setHistory(history);
        PRep.save(pats);
        return"redirect:/Patients";
    }

    @GetMapping("/Patients/{id}/reserve")
    public String Reserve(@PathVariable (value="id") long id, Model model)
    {


        Optional<Patients> pats = PRep.findById(id);
        ArrayList<Patients> res = new ArrayList<>();
        pats.ifPresent(res::add);
        model.addAttribute("Card", res);


        Optional<Reserve> pats1 = RRep.findById(id);
        ArrayList<Reserve> res1 = new ArrayList<>();
        pats1.ifPresent(res1::add);
        model.addAttribute("res", res1);

        List<Time> test = new ArrayList<>();
        model.addAttribute("test", test);
        List<Time> time = serviceee.listAlll();
        model.addAttribute("Time", time);



        return "reserve-add";
    }

    @PostMapping("/Patients/{id}/reserve")
    public String ReserveAdd(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String middle_name,
                             @RequestParam Integer YoB,
                             @RequestParam String group,
                             @RequestParam String policy,
                             @RequestParam String history,
                             @RequestParam String email,
                             @RequestParam String doctor,

                             @PathVariable (value="id") long id,
                             Model model)
    {



        Reserve patss = new Reserve(name, surname, middle_name, history, group, policy, email,YoB,doctor);
        Time pats = TRep.findById(id).orElseThrow();


        patss.setName(name);
        patss.setSurname(surname);
        patss.setMiddle_name(middle_name);
        patss.setBorn_data(YoB);
        patss.setBlood_type(group);
        patss.setMedical_policy(policy);
        patss.setHistory(history);
        patss.setEmail(email);
        patss.setDoctor(doctor);
        TRep.delete(pats);
        RRep.save(patss);

        return"redirect:/Reserved";
    }

    @PostMapping("/Patients/{id}/remove")
    public String CardRemove(
                             @PathVariable (value="id") long id,
                             Model model)
    {
        Patients pats = PRep.findById(id).orElseThrow();
        PRep.delete(pats);
        return"redirect:/Patients";
    }

    @PostMapping("/Reserved/{id}/remove")
    public String ReserveRemove(
            @PathVariable (value="id") long id,
            Model model)
    {
        Reserve pats = RRep.findById(id).orElseThrow();

        RRep.delete(pats);
        return"redirect:/Reserved";
    }

    @GetMapping("/Reserved/{id}/edit")
    public String ReserveEdit(@PathVariable (value="id") long id, Model model)
    {
        if(!RRep.existsById(id))
            return"redirect:/Reserved";

        Optional<Reserve> pats = RRep.findById(id);
        ArrayList<Reserve> res = new ArrayList<>();
        pats.ifPresent(res::add);
        model.addAttribute("Res", res);

        List<Time> test = new ArrayList<>();
        model.addAttribute("test", test);
        List<Time> time = serviceee.listAlll();
        model.addAttribute("Time", time);

        return "reserve-edit";

    }

    @PostMapping("/Reserved/{id}/edit")
    public String ReserveUpdate(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String middle_name,
            @RequestParam Integer YoB,
            @RequestParam String group,
            @RequestParam String policy,
            @RequestParam String history,
            @RequestParam String doctor,
            @RequestParam String email,
                             @PathVariable (value="id") long id,
                             Model model)
    {

        Reserve pats = RRep.findById(id).orElseThrow();
        pats.setName(name);
        pats.setSurname(surname);
        pats.setMiddle_name(middle_name);
        pats.setBorn_data(YoB);
        pats.setBlood_type(group);
        pats.setMedical_policy(policy);
        pats.setHistory(history);
        pats.setEmail(email);
        pats.setDoctor(doctor);
        RRep.save(pats);
        return"redirect:/Reserved";
    }



}


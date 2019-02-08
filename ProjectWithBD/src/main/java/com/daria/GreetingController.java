package com.daria;

import com.daria.domain.Messege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.daria.repo.MessageRepo;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepo messegeRepo;


    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model){
       Iterable<Messege> messeges =  messegeRepo.findAll();
        model.put("messages", messeges);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
       Messege messege =  new Messege(text, tag);

       messegeRepo.save(messege);

        Iterable<Messege> messeges =  messegeRepo.findAll();
        model.put("messages", messeges);
        return "main";
    }


    @PostMapping("filter")
    public String filter(@RequestParam String filter,  Map<String, Object> model){
        Iterable<Messege> messages;


List<Messege> messeges = messegeRepo.findByTag(filter);
      if (filter != null && filter.isEmpty()){
          messages = messegeRepo.findByTag(filter);
      }
else {
          messeges = (List<Messege>) messegeRepo.findAll();
        }

        model.put("messages", messeges);
        return "main";
    }

}
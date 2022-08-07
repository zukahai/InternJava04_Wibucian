package com.java04.wibucian.controllers.admin;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.java04.wibucian.models.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/delete")
public class TestController {
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable String id) {
            //return Json {check:true, value:test};
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", true);
            map.put("value", "test");
            return ResponseEntity.ok().body(map);

    }
}

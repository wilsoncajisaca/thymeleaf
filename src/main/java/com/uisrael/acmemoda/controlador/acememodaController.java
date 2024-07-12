package com.uisrael.acmemoda.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class acememodaController {
    @GetMapping("/index")
    public String index() {
        return "redirect:/pedido/listar-pedido";
    }
}

package com.springdemo.testverifica.controller;

import com.springdemo.testverifica.model.*;
import com.springdemo.testverifica.repository.*;
import com.springdemo.testverifica.service.ClienteService;
import com.springdemo.testverifica.service.CorsoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ClienteService clienteService;
    private final CorsoService corsoService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false)  String msg) {
        model.addAttribute("msg", msg);
        return "index";
    }

    @GetMapping("/Clienti")
    public String Clienti(Model model, HttpSession session) {
        Cliente Cliente= (Cliente) session.getAttribute("Cliente");
        if (Cliente != null) {
            model.addAttribute("Clienti",clienteService.getClienti());
            model.addAttribute("ClienteSelezionato", null);
            return "Clienti";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(RedirectAttributes redirectAttributes, Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        //
        Cliente Cliente=clienteService.getClienteByUsernameAndPassword(username, password);
        if (Cliente != null) {
            session.setAttribute("Cliente", Cliente);
            return "redirect:/riservato";
        }else{
            redirectAttributes.addAttribute("msg", "Credenziali errate");
            return "redirect:/";
        }
    }

    @GetMapping("/riservato")
    public String riservato(RedirectAttributes redirectAttributes,Model model, HttpSession session, @RequestParam(required = false)  String msg) {
        Cliente Cliente= (Cliente) session.getAttribute("Cliente");
        model.addAttribute("msg", msg);
        model.addAttribute("err", "");

        if (Cliente != null) {
            return "riservato";
        }else{
            redirectAttributes.addAttribute("msg", "Accesso non autorizzato");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/registrazione")
    public String toRegPage(){
        return "/registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazione(RedirectAttributes redirectAttributes, Model model, HttpSession session,
                                @RequestParam("username") String username,
                                @RequestParam("cognome") String cognome,
                                @RequestParam("nome") String nome,
                                @RequestParam("citta") String citta,
                                @RequestParam("mail") String telefono,
                                @RequestParam("password") String pass,
                                @RequestParam("confPassword") String confPass){
        // Data di riferimento: 1 gennaio 1900
        //LocalDate dataDiRiferimento = LocalDate.of(1900, 1, 1);
        if (!pass.equals(confPass)){
            redirectAttributes.addAttribute("msg", "La password non corrisponde");
        }
        if (!pass.contains("0") && !pass.contains("1") && !pass.contains("2") && !pass.contains("3") && !pass.contains("4") && !pass.contains("5") && !pass.contains("6") && !pass.contains("7") && !pass.contains("8") && !pass.contains("9")){
            redirectAttributes.addAttribute("msg", "La password deve contenere almeno un numero");
        }
        if (!pass.contains("!") && !pass.contains("@") && !pass.contains("#") && !pass.contains("$") && !pass.contains("%") && !pass.contains("^") && !pass.contains("&") && !pass.contains("*") && !pass.contains("(") && !pass.contains(")")){
            redirectAttributes.addAttribute("msg", "La password deve contenere almeno un carattere speciale");
        }
        else {
            if (clienteService.getClienteByUsername(username) != null) {
                redirectAttributes.addAttribute("msg", "Username già esistente");
            }
            /*
            LocalDate date = LocalDate.parse(data);
            if (date.isBefore(dataDiRiferimento)) {
                redirectAttributes.addAttribute("msg", "Data di nascita non valida");
            }
            int admin = 0;
            if (citta.equals("vaccaBoia")){
                admin = 1;
            }
            */
            Cliente Cliente = new Cliente(username, pass, cognome, nome, citta, telefono);
            if (Cliente != null) {
                clienteService.aggiungiCliente(Cliente);
                session.setAttribute("Cliente", Cliente);
                return "redirect:/riservato";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/VaiACorsi")
    public String corsi(Model model, HttpSession session) {
        Cliente Cliente= (Cliente) session.getAttribute("Cliente");
        if (Cliente != null) {
            return "redirect:/corsi";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/corsi")
    public String corsi(RedirectAttributes redirectAttributes,Model model, HttpSession session, @RequestParam(required = false)  String msg) {
        Cliente Cliente= (Cliente) session.getAttribute("Cliente");
        model.addAttribute("elencoCorsi", corsoService.getCorsi());
        model.addAttribute("msg", msg);
        model.addAttribute("err", "");

        if (Cliente != null) {
            return "corsi";
        }else{
            redirectAttributes.addAttribute("msg", "Accesso non autorizzato");
            return "redirect:/";
        }
    }








    @ControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public String handleException(Exception ex) {
            // Log dell'errore
            ex.printStackTrace();
            return "error";  // Restituisce una pagina di errore personalizzata
        }
    }

    @Controller
    public class FaviconController {
        @RequestMapping("favicon.ico")
        @ResponseBody
        void returnNoFavicon() {}
    }
}

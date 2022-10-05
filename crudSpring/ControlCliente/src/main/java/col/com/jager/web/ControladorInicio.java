
package col.com.jager.web;

import col.com.jager.domain.Persona;
import col.com.jager.servicio.PersonaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaServicio personaServicio;
    
    @GetMapping("/")
    public String inicio(Model model){
        
        var personas = personaServicio.listarPersonas();
               
        log.info("ejecuando el controlador spring MVC"); 
        model.addAttribute("personas", personas);
        return "index";
        
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(Persona persona){
        personaServicio.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
       persona = personaServicio.encontrarPersona(persona);
       model.addAttribute("persona", persona);
       return "modificar";     
    }
    
    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona){
        personaServicio.eliminar(persona);
        return "redirect:/";
    }
}
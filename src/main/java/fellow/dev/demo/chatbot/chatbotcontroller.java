package fellow.dev.demo.chatbot;

import fellow.dev.demo.ecomcontroller.Productcontrol;
import fellow.dev.demo.ecomentity.Productentity;
import fellow.dev.demo.ecomrepo.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class chatbotcontroller {

    @Autowired
    chatbotservice chatservice;

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatservice.chat(message);
    }

}

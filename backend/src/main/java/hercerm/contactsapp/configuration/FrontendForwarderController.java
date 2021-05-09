package hercerm.contactsapp.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendForwarderController {
    @RequestMapping(value = "/{_:^(?!index[.]html|api).+$}")
    public String forward() {
        return "forward:/";
    }
}
package hercerm.btcontacts.bootstrapper;

import hercerm.btcontacts.domain.contact.Contact;
import hercerm.btcontacts.domain.contact.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrapper implements CommandLineRunner {

    private final ContactRepository contactRepository;

    public Bootstrapper(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Contact hernan = new Contact("Hernan", "Cervera", "hernan@example.com");
        Contact gladys = new Contact("Gladys", "Cervera", "gladys@example.com");

        contactRepository.save(hernan);
        contactRepository.save(gladys);
    }
}

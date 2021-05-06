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
        Contact hernan = new Contact("Hernán", "Cervera", "hernan@example.com");
        Contact roman = new Contact("Roman", "Polanski", "roman@example.com");
        Contact darren = new Contact("Darren", "Aronofsky", "darren@example.com");
        Contact vincent = new Contact("Vincent", "Vega", "vincent@example.com");
        Contact sigmund = new Contact("Sigmund", "Freud", "signumd@example.com");
        Contact richard = new Contact("Richard", "Feynman", "richard@example.com");
        Contact carol = new Contact("Carol", "Dweck", "carol@example.com");
        Contact friedrich = new Contact("Friedrich", "Nietzsche", "friedrich@example.com");
        Contact stanley = new Contact("Stanley", "Kubrick", "stanley@example.com");
        Contact katniss = new Contact("Katniss", "Everdeen", "katsniss@example.com");
        Contact marty = new Contact("Marty", "McFly", "marty@example.com");
        Contact rick = new Contact("Rick", "Sanchez", "rick@example.com");
        Contact erich = new Contact("Erich", "Fromm", "rick@example.com");

        contactRepository.save(hernan);
        contactRepository.save(roman);
        contactRepository.save(darren);
        contactRepository.save(vincent);
        contactRepository.save(sigmund);
        contactRepository.save(richard);
        contactRepository.save(carol);
        contactRepository.save(friedrich);
        contactRepository.save(stanley);
        contactRepository.save(katniss);
        contactRepository.save(marty);
        contactRepository.save(richard);
        contactRepository.save(rick);
        contactRepository.save(erich);
    }
}

package hercerm.btcontacts.domain.contact;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDTO.Response.Public> getAllContacts() {
        return contactRepository.findAll().stream().map(contact -> {
            return new ContactDTO.Response.Public(
                    contact.getContactId(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getCompany(),
                    contact.getPhoneNumber()
                    );
        }).collect(Collectors.toList());
    }
}

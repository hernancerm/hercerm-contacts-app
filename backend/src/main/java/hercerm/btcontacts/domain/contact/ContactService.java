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
        var contactToPublic = ContactMapper.Response.PublicMapper.INSTANCE;
        return contactRepository.findAll().stream()
                .map(contactToPublic::contactToPublic)
                .collect(Collectors.toList());
    }
}

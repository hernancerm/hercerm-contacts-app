package hercerm.btcontacts.domain.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Page<ContactDTO.Response.Public> getContactsPaginated(Pageable pageable) {
        var contactToPublic = ContactMapper.Response.PublicMapper.INSTANCE;
        return contactRepository.findAll(pageable).map(contactToPublic::contactToPublic);
    }
}

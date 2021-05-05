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

    public Page<ContactDto.Response.Public> getContactsPaginated(Pageable pageable) {
        var requestPublicMapper = ContactMapper.Response.PublicMapper.INSTANCE;
        return contactRepository.findAll(pageable).map(requestPublicMapper::contactToPublic);
    }

    public ContactDto.Response.Public createContact(ContactDto.Request.Create contactDto) {
        var requestCreateMapper = ContactMapper.Request.CreateMapper.INSTANCE;
        var responsePublicMapper = ContactMapper.Response.PublicMapper.INSTANCE;
        Contact savedContact = contactRepository.save(requestCreateMapper.createToContact(contactDto));
        return responsePublicMapper.contactToPublic(savedContact);
    }
}

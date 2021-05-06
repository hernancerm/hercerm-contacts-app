package hercerm.btcontacts.domain.contact;

import hercerm.btcontacts.errors.ContactNotFoundException;
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

    public ContactDto.Response.Public updateContact(ContactDto.Request.Create contactDto, long contactId) {
        var responsePublicMapper = ContactMapper.Response.PublicMapper.INSTANCE;
        var requestCreateMapper = ContactMapper.Request.CreateMapper.INSTANCE;

        contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
        Contact contact = requestCreateMapper.createToContact(contactDto);
        contact.setContactId(contactId);

        Contact updatedContact = contactRepository.save(contact);
        return responsePublicMapper.contactToPublic(updatedContact);
    }

    void deleteContact(long contactId) {
        contactRepository.findById(contactId).ifPresent(contactRepository::delete);
    }
}

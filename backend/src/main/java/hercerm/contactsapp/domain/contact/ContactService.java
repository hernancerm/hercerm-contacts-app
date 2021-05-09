package hercerm.contactsapp.domain.contact;

import hercerm.contactsapp.errors.ContactNotFoundException;
import hercerm.contactsapp.errors.InvalidContactException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    public ContactDto.Response.Public findById(long contactId) {
        var responsePublicMapper = ContactMapper.Response.PublicMapper.INSTANCE;
        Contact contact = contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
        return responsePublicMapper.contactToPublic(contact);
    }

    public ContactDto.Response.Public createContact(ContactDto.Request.Base contactDto) {
        validateContact(contactDto, new HashMap<>());

        var requestCreateMapper = ContactMapper.Request.CreateMapper.INSTANCE;
        var responsePublicMapper = ContactMapper.Response.PublicMapper.INSTANCE;

        Contact savedContact = contactRepository.save(requestCreateMapper.createToContact(contactDto));
        return responsePublicMapper.contactToPublic(savedContact);
    }

    public ContactDto.Response.Public updateContact(ContactDto.Request.Base contactDto, long contactId) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("CONTACT_ID", Long.toString(contactId));
        validateContact(contactDto, metadata);

        var responsePublicMapper = ContactMapper.Response.PublicMapper.INSTANCE;
        var requestCreateMapper = ContactMapper.Request.CreateMapper.INSTANCE;

        contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
        Contact contact = requestCreateMapper.createToContact(contactDto);
        contact.setContactId(contactId);

        Contact updatedContact = contactRepository.save(contact);
        return responsePublicMapper.contactToPublic(updatedContact);
    }

    public void deleteContact(long contactId) {
        contactRepository.findById(contactId).ifPresent(contactRepository::delete);
    }

    private void validateContact(ContactDto.Request.Base contactDto, Map<String, String> metadata) {
        Map<String, String> errors = new HashMap<>();

        if (missingValue(contactDto.getFirstName())) {
            errors.put("firstName", "First name is required");
        } else if (!contactDto.getFirstName().matches("[a-zA-Z\\s]+")) {
            errors.put("firstName", "Should only contain words, not numbers");
        }

        if (missingValue(contactDto.getLastName())) {
            errors.put("lastName", "Last name is required");
        } else if (!contactDto.getLastName().matches("[a-zA-Z\\s]+")) {
            errors.put("lastName", "Should only contain words, not numbers");
        }

        if (missingValue(contactDto.getEmail())) {
            errors.put("email", "Email is required");
        } else if (!contactDto.getEmail().matches("[\\w\\d]+@[\\w\\d]+[.]\\w+")) {
            errors.put("email", "Invalid email format");
        } else {
            contactRepository.findContactByEmail(contactDto.getEmail()).ifPresent(contact -> {
                if (metadata.containsKey("CONTACT_ID")) {
                    long contactId = Long.parseLong(metadata.get("CONTACT_ID"));
                    if (contactId != contact.getContactId()) {
                        errors.put("email", "Another contact has the same email");
                    }
                } else {
                    errors.put("email", "Another contact has the same email");
                }
            });
        }

        if (!missingValue(contactDto.getCompany())) {
            if (!contactDto.getCompany().matches("[a-zA-Z0-9\\s_.-]+")) {
                errors.put("company", "Should only contain words, not numbers");
            }
        }

        if (!missingValue(contactDto.getPhoneNumber())) {
            if (!contactDto.getPhoneNumber().matches("[0-9]+")) {
                errors.put("phoneNumber", "Should only contain numbers");
            } else {
                contactRepository.findContactByPhoneNumber(contactDto.getPhoneNumber()).ifPresent(contact -> {
                    if (metadata.containsKey("CONTACT_ID")) {
                        long contactId = Long.parseLong(metadata.get("CONTACT_ID"));
                        if (contactId != contact.getContactId()) {
                            errors.put("phoneNumber", "Another contact has the same phone number");
                        }
                    } else {
                        errors.put("phoneNumber", "Another contact has the same phone number");
                    }
                });
            }
        }

        if (!errors.isEmpty()) {
            throw new InvalidContactException(errors);
        }
    }

    private <T> boolean missingValue(T attribute) {
        return attribute == null || attribute.toString().isBlank();
    }
}

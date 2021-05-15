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

    public Page<Contact> getContactsPaginated(Pageable pageable, String searchTerm) {
        if (searchTerm == null)
            return contactRepository.findAllOrdered(pageable);
        else
            return contactRepository.findPaginatedBySearchTerm(searchTerm, pageable);
    }

    public Contact findById(long contactId) {
        return contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
    }

    public Contact createContact(Contact contact) {
        validateContact(contact, new HashMap<>());
        return contactRepository.saveAndFlush(contact);
    }

    public Contact updateContact(Contact contact, long contactId) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("CONTACT_ID", Long.toString(contactId));
        validateContact(contact, metadata);

        contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
        contact.setContactId(contactId);

        return contactRepository.saveAndFlush(contact);
    }

    public void deleteContact(long contactId) {
        contactRepository.findById(contactId).ifPresent(contactRepository::delete);
    }

    private void validateContact(Contact contact, Map<String, String> metadata) {
        Map<String, String> errors = new HashMap<>();

        if (missingValue(contact.getFirstName())) {
            errors.put("firstName", "First name is required");
        } else if (!contact.getFirstName().matches(Contact.NAMES_PATTERN)) {
            errors.put("firstName", "Should only contain words, not numbers");
        }

        if (missingValue(contact.getLastName())) {
            errors.put("lastName", "Last name is required");
        } else if (!contact.getLastName().matches(Contact.NAMES_PATTERN)) {
            errors.put("lastName", "Should only contain words, not numbers");
        }

        if (missingValue(contact.getEmail())) {
            errors.put("email", "Email is required");
        } else if (!contact.getEmail().matches(Contact.EMAIL_PATTERN)) {
            errors.put("email", "Invalid email format");
        } else {
            contactRepository.findContactByEmail(contact.getEmail()).ifPresent(foundContact -> {
                if (metadata.containsKey("CONTACT_ID")) {
                    long contactId = Long.parseLong(metadata.get("CONTACT_ID"));
                    if (contactId != foundContact.getContactId()) {
                        errors.put("email", "Another contact has the same email");
                    }
                } else {
                    errors.put("email", "Another contact has the same email");
                }
            });
        }

        if (!missingValue(contact.getCompany())) {
            if (!contact.getCompany().matches(Contact.COMPANY_PATTERN)) {
                errors.put("company", "Should only contain words, not numbers");
            }
        }

        if (!missingValue(contact.getPhoneNumber())) {
            if (!contact.getPhoneNumber().matches(Contact.PHONE_PATTERN)) {
                errors.put("phoneNumber", "Should only contain numbers");
            } else {
                contactRepository.findContactByPhoneNumber(contact.getPhoneNumber()).ifPresent(foundContact -> {
                    if (metadata.containsKey("CONTACT_ID")) {
                        long contactId = Long.parseLong(metadata.get("CONTACT_ID"));
                        if (contactId != foundContact.getContactId()) {
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

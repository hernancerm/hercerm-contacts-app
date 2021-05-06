package hercerm.btcontacts.domain.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ContactController.BASE_URL)
public class ContactController {

    public static final String BASE_URL = "/api/contacts";

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public Page<ContactDto.Response.Public> getContactsPaginated(
            @PageableDefault(size = 10) Pageable pageable) {
        return contactService.getContactsPaginated(pageable);
    }

    @GetMapping("/{contactId}")
    public ContactDto.Response.Public findContactById(@PathVariable long contactId) {
        return contactService.findById(contactId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto.Response.Public createContact(@Valid @RequestBody ContactDto.Request.Create contactDto) {
        return contactService.createContact(contactDto);
    }

    @PutMapping("/{contactId}")
    public ContactDto.Response.Public updateContact(
            @Valid @RequestBody ContactDto.Request.Create contactDto, @PathVariable long contactId) {
        return contactService.updateContact(contactDto, contactId);
    }

    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable long contactId) {
        contactService.deleteContact(contactId);
    }
}

package hercerm.contactsapp.domain.contact;

import hercerm.contactsapp.errors.InvalidContactException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
            @PageableDefault(size = 10) Pageable pageable,
            // By default request params are required. When optional, if not
            // provided their value is set to null.
            @RequestParam(name = "query", required = false) String searchTerm) {
        return contactService.getContactsPaginated(pageable, searchTerm);
    }

    @GetMapping("/{contactId}")
    public ContactDto.Response.Public findContactById(@PathVariable long contactId) {
        return contactService.findById(contactId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto.Response.Public createContact(@RequestBody ContactDto.Request.Base contactDto) {
        return contactService.createContact(contactDto);
    }

    @PutMapping("/{contactId}")
    public ContactDto.Response.Public updateContact(
            @RequestBody ContactDto.Request.Base contactDto, @PathVariable long contactId) {
        return contactService.updateContact(contactDto, contactId);
    }

    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable long contactId) {
        contactService.deleteContact(contactId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidContactException.class)
    public Map<String, String> handleValidationExceptions(InvalidContactException e) {
        return e.getErrors();
    }
}

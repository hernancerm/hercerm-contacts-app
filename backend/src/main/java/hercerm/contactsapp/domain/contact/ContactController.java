package hercerm.contactsapp.domain.contact;

import hercerm.contactsapp.errors.InvalidContactException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * Problems I see with springdoc:
 * - @Operation(responses = {...}) overrides the auto-generated success response.
 *   This requires a manual entry of @ApiResponse, which has duplicate information
 *   regarding the annotation @ResponseStatus and the return type of the method.
 * - Pageable query params documentation does not work according to:
 *   - https://springdoc.org/#spring-data-rest-support or
 *   - https://www.baeldung.com/spring-rest-openapi-documentation#exposing-pagination-information
 * - Setting the schema of an @ApiResponse is too verbose (nesting within @Content).
 * - Map.class schema does not seem to work, it is registered as string.
 */

@RestController
@RequestMapping(ContactController.BASE_URL)
@Tag(name = "Contacts")
public class ContactController {

    public static final String BASE_URL = "/api/contacts";

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @Operation(summary = "get contacts paginated")
    public Page<Contact> getContactsPaginated(
            @PageableDefault(size = 10) Pageable pageable,
            // By default request params are required. When optional, if not
            // provided their value is set to null.
            @RequestParam(name = "query", required = false) String searchTerm) {
        return contactService.getContactsPaginated(pageable, searchTerm);
    }

    @GetMapping("/{contactId}")
    @Operation(summary = "get a contact by id", parameters = {
            @Parameter( // This is how parameters are documented.
                    name = "contactId",
                    in = ParameterIn.PATH,
                    required = true)
    })
    public Contact findContactById(@PathVariable long contactId) {
        return contactService.findById(contactId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "create a contact",
            responses = { // Here are the possible responses documented.
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Contact.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid contact",
                            content = @Content(schema = @Schema(implementation = Map.class)))
            })
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @PutMapping("/{contactId}")
    @Operation(summary = "update a contact")
    public Contact updateContact(
            @RequestBody Contact contact, @PathVariable long contactId) {
        return contactService.updateContact(contact, contactId);
    }

    @DeleteMapping("/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "hard delete a contact")
    public void deleteContact(@PathVariable long contactId) {
        contactService.deleteContact(contactId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidContactException.class)
    public Map<String, String> handleValidationExceptions(InvalidContactException e) {
        return e.getErrors();
    }
}

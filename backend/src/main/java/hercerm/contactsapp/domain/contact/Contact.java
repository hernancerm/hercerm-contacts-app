package hercerm.contactsapp.domain.contact;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
public class Contact {

    // "\\p{L}" identifies all Unicode characters belonging to the 'letter' category.
    // http://www.regular-expressions.info/unicode.html#prop
    public static final String NAMES_PATTERN = "^\\p{L}[\\p{L}\\s]*\\p{L}$";
    public static final String EMAIL_PATTERN = "^[\\w\\d.]+@[\\w\\d]+[.][a-z]+$";
    public static final String COMPANY_PATTERN = "^\\S[\\S\\s]*\\S$";
    public static final String PHONE_PATTERN = "^[0-9]+$";

    @Id
    @GeneratedValue
    private long contactId;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = Contact.NAMES_PATTERN, message = "Should only contain words, not numbers")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Should only contain words, not numbers")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Pattern(regexp = Contact.EMAIL_PATTERN, message = "Invalid email format")
    private String email;

    @Pattern(regexp = Contact.COMPANY_PATTERN, message = "Should only contain letters or numbers")
    private String company;
    @Column(unique = true)
    @Pattern(regexp = Contact.PHONE_PATTERN, message = "Should only contain numbers")
    private String phoneNumber;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

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

    @Id
    @GeneratedValue
    private long contactId;

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Should only contain words, not numbers")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Should only contain words, not numbers")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @javax.validation.constraints.Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "[a-zA-Z0-9\\s_.-]+", message = "Should only contain letters or numbers")
    private String company;
    @Column(unique = true)
    @Pattern(regexp = "[0-9]+", message = "Should only contain numbers")
    private String phoneNumber;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

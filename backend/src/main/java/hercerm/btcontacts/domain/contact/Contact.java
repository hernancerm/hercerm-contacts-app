package hercerm.btcontacts.domain.contact;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue
    private long contactId;

    private String firstName;
    private String lastName;
    private String email;

    private String company;
    private int phoneNumber;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

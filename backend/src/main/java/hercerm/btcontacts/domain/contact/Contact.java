package hercerm.btcontacts.domain.contact;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue
    private long contactId;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String company;
    @Column(unique = true)
    private String phoneNumber;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

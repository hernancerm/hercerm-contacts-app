package hercerm.btcontacts.domain.contact;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum ContactDTO {;

    // TODO: Add validations
    private interface ContactId { @NotNull long getContactId(); }
    private interface FirstName { @NotNull String getFirstName(); }
    private interface LastName { @NotNull String getLastName(); }
    private interface Email { String getEmail(); }
    private interface Company { String getCompany(); }
    private interface PhoneNumber { int getPhoneNumber(); }

    public enum Response {;
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Public implements ContactId, FirstName, LastName, Email,
                Company, PhoneNumber {
            public long contactId;
            public String firstName;
            public String lastName;
            public String email;
            public String company;
            public int phoneNumber;
        }
    }
}

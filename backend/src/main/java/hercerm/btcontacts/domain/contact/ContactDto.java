package hercerm.btcontacts.domain.contact;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum ContactDto {;

    // TODO: Add validations
    private interface ContactId { @NotNull long getContactId(); }
    private interface FirstName { @NotNull String getFirstName(); }
    private interface LastName { @NotNull String getLastName(); }
    private interface Email { String getEmail(); }
    private interface Company { String getCompany(); }
    private interface PhoneNumber { String getPhoneNumber(); }

    public enum Request {;
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Create implements FirstName, LastName, Email, Company, PhoneNumber {
            public String firstName;
            public String lastName;
            public String email;
            public String company;
            public String phoneNumber;
        }
    }

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
            public String phoneNumber;
        }
    }
}

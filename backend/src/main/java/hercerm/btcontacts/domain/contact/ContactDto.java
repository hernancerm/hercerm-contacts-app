package hercerm.btcontacts.domain.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public enum ContactDto {;

    // TODO: Review validations, these might not be right
    private interface ContactId { @NotBlank long getContactId(); }
    private interface FirstName { @NotBlank @Pattern(regexp = "[a-zA-Z\\s]+") String getFirstName(); }
    private interface LastName { @NotBlank @Pattern(regexp = "[a-zA-Z\\s]+") String getLastName(); }
    private interface Email { @NotBlank @NotBlank @javax.validation.constraints.Email String getEmail(); }
    private interface Company { @Pattern(regexp = "[a-zA-Z0-9\\s_.-]+") String getCompany(); }
    private interface PhoneNumber { @Pattern(regexp = "[0-9]+") String getPhoneNumber(); }

    public enum Request {;
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Create implements FirstName, LastName, ContactDto.Email, Company, PhoneNumber {
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
        public static class Public implements ContactId, FirstName, LastName, ContactDto.Email,
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

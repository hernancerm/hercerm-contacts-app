package hercerm.btcontacts.domain.contact;

import lombok.Data;

public enum ContactDto {;

    private interface ContactId { long getContactId(); }
    private interface FirstName { String getFirstName(); }
    private interface LastName { String getLastName(); }
    private interface Email { String getEmail(); }
    private interface Company { String getCompany(); }
    private interface PhoneNumber { String getPhoneNumber(); }

    public enum Request {;
        @Data
        public static class Base implements FirstName, LastName, ContactDto.Email, Company, PhoneNumber {
            public String firstName;
            public String lastName;
            public String email;
            public String company;
            public String phoneNumber;
        }
    }

    public enum Response {;
        @Data
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

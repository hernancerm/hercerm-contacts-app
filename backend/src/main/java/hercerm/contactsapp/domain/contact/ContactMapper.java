package hercerm.contactsapp.domain.contact;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public enum ContactMapper {;

    public enum Request {;
        @Mapper
        public interface CreateMapper {
            CreateMapper INSTANCE = Mappers.getMapper(CreateMapper.class);
            Contact createToContact(ContactDto.Request.Base contactDto);
        }
    }

    public enum Response {;
        @Mapper
        public interface PublicMapper {
            PublicMapper INSTANCE = Mappers.getMapper(PublicMapper.class);
            ContactDto.Response.Public contactToPublic(Contact contact);
            Contact publicToContact(ContactDto.Response.Public contactDto);
        }
    }
}

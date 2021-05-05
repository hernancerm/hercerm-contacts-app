package hercerm.btcontacts.domain.contact;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public enum ContactMapper {;

    public enum Response {;
        @Mapper
        public interface PublicMapper {
            PublicMapper INSTANCE = Mappers.getMapper(PublicMapper.class);
            ContactDTO.Response.Public contactToPublic(Contact contact);
        }
    }
}

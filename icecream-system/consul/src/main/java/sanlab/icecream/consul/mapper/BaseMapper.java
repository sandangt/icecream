package sanlab.icecream.consul.mapper;

import org.mapstruct.Named;

import java.util.UUID;

public interface BaseMapper {
    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return String.valueOf(uuid);
    }

    @Named("stringToUuid")
    default UUID stringToUuid(String str) {
        try {
            return UUID.fromString(str);
        } catch (Exception ignored) {
            return null;
        }
    }
}

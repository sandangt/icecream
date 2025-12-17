package sanlab.icecream.fundamentum.dto.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuditLogDto {
    private UUID id;
    private String userId;
    private String email;
    private String username;
    private String content;
}

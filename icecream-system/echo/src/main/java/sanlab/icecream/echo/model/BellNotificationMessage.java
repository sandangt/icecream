package sanlab.icecream.echo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.TableName.BELL_NOTIFICATION_MESSAGE;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = BELL_NOTIFICATION_MESSAGE)
public class BellNotificationMessage implements Auditable {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private UUID userId;
    private String email;
    private Boolean seen;
    private String title;
    private String content;
    private String type;
    private Long createdAt;

}

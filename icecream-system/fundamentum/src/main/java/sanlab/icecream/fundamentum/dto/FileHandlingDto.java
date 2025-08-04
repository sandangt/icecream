package sanlab.icecream.fundamentum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sanlab.icecream.fundamentum.constant.EFileHandlingAction;
import sanlab.icecream.fundamentum.constant.EFileType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileHandlingDto {

    private String relativePath;
    private EFileHandlingAction action;
    private EFileType fileType;

}

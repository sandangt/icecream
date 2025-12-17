package sanlab.icecream.fundamentum.exception;

import java.io.Serializable;

public interface IcErrorModel extends Serializable {

    String getCode();
    String getMsg();
    int getRSocketCode(); // Should be < -2 or > 769

}

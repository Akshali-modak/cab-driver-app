package cab.driver.app.model;

import lombok.*;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private int id;
    private String name;
    private String address;
    private int mobile;
    private String emailId;
}

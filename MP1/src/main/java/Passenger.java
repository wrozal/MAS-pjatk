import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Passenger implements Serializable {

    private static final long serialVersionUID = 7777695709816246107L;

    @NonNull
    private String name;
    @NonNull
    private String surname;
    private String phoneNumber = null;
    @NonNull
    private String email;

    @Override
    public String toString() {
        return this.phoneNumber == null ?
                String.format("Passenger(name=%s, surname=%s, email=%s)", this.name, this.surname, this.email) :
                String.format("Passenger(name=%s, surname=%s, phoneNumber=%s, email=%s)", this.name, this.surname,
                        this.phoneNumber, this.email);
    }

}

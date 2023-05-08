import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Viewer {

    private static List<Viewer> allViewers = new ArrayList<>();

    private String name;
    private String peselNumber;

    @Setter(AccessLevel.NONE)
    private List<Ticket> tickets;

    public Viewer(String name, String peselNumber) {
        setUp();
        setName(name);
        setPeselNumber(peselNumber);
        allViewers.add(this);
    }

    public void setUp() {
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) throws Exception {
        if (tickets.contains(ticket)) {
            throw new Exception("You already have this ticket, there is no need to add it again.");
        }
        for (Viewer viewer : allViewers) {
            if (viewer.getTickets().contains(ticket)) {
                throw new Exception("Someone already owns this ticket!");
            }
        }
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
            ticket.removeViewer();
        }
    }

}

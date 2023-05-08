import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(exclude = {"viewer"})
public class Ticket {

    private Viewer viewer;

    private BigDecimal price;

    private Ticket(BigDecimal price) {
        setPrice(price);
    }

    public static Ticket createTicket(Viewer viewer, BigDecimal price) throws Exception {
        if (viewer == null) {
            throw new Exception("The ticket must have the owner!");
        }
        Ticket ticket = new Ticket(price);
        viewer.addTicket(ticket);
        ticket.setViewer(viewer);

        return ticket;
    }

    public void setViewer(Viewer viewer) {
        if(this.viewer == null) {
            this.viewer = viewer;
        }
    }

    public void removeViewer() {
        if(viewer != null) {
            viewer.removeTicket(this);
            viewer = null;
        }
    }

}

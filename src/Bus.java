
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Bus {
    private String company;
    private LocalDateTime timeDeparture;
    private LocalDateTime timeArrival;

    public Bus() {
        this("", LocalDateTime.of(LocalDate.of(0, 0, 0), LocalTime.of(0, 0, 0, 0)),
                LocalDateTime.of(LocalDate.of(0, 0, 0), LocalTime.of(0, 0, 0, 0)));
    }

    public Bus(String company, LocalDateTime timeDeparture, LocalDateTime timeArrival) {
        this.company = company;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
    }

    public String getCompany() {
        return company;
    }

    public LocalDateTime getTimeDeparture() {
        return timeDeparture;
    }

    public LocalDateTime getTimeArrival() {
        return timeArrival;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(company).append(" ");
        sb.append(timeDeparture.toLocalTime()).append(" ");
        sb.append(timeArrival.toLocalTime());
        return sb.toString();
    }
}

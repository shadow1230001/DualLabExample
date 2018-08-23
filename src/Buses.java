import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Buses {
    private List<Bus> listOfBuses;

    public Buses() {
        this(new ArrayList<>());
    }

    public Buses(List<Bus> listOfBuses) {
        this.listOfBuses = listOfBuses;
    }

    public void readFromFile(String path) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(path)).useDelimiter("[\\s:]+")) {
            Bus bus;
            String name;
            LocalTime timeDep;
            LocalTime timeArr;
            while (sc.hasNext()) {
                name = sc.next();
                timeDep = LocalTime.of(sc.nextInt(), sc.nextInt(), 0, 0);
                timeArr = LocalTime.of(sc.nextInt(), sc.nextInt(), 0, 0);
                bus = timeArr.isBefore(timeDep) ?
                        new Bus(name,
                                LocalDateTime.of(LocalDate.now(), timeDep),
                                LocalDateTime.of(LocalDate.now().plusDays(1), timeArr)) :
                        new Bus(name,
                                LocalDateTime.of(LocalDate.now(), timeDep),
                                LocalDateTime.of(LocalDate.now(), timeArr));
                if (ChronoUnit.MINUTES.between(bus.getTimeDeparture(), bus.getTimeArrival()) < 60) {
                    listOfBuses.add(bus);
                }
            }
        }
    }

    public void sortListOfBusesByTA() {
        listOfBuses.sort(new Comparator<Bus>() {
            @Override
            public int compare(Bus o1, Bus o2) {
                return o1.getTimeArrival().compareTo(o2.getTimeArrival());
            }
        });
    }

    public void checkingEfficientBusesByTA() {
        for (int i = 0; i < listOfBuses.size() - 1; i++) {
            if (listOfBuses.get(i).getTimeArrival().equals(listOfBuses.get(i + 1).getTimeArrival())) {
                if (listOfBuses.get(i).getTimeDeparture().equals(listOfBuses.get(i + 1).getTimeDeparture())) {
                    listOfBuses.remove(listOfBuses.get(i).getCompany().equals("Grotty") ? listOfBuses.get(i) : listOfBuses.get(i + 1));
                    i--;
                } else {
                    listOfBuses.remove(listOfBuses.get(i).getTimeDeparture().isBefore(listOfBuses.get(i + 1).getTimeDeparture()) ?
                            listOfBuses.get(i) : listOfBuses.get(i + 1));
                    i--;
                }
            }
        }
    }

    public void sortListOfBusesByTD() {
        listOfBuses.sort(new Comparator<Bus>() {
            @Override
            public int compare(Bus o1, Bus o2) {
                return o1.getTimeDeparture().compareTo(o2.getTimeDeparture());
            }
        });
    }

    public void checkingEfficientBusesByTD() {
        for (int i = 0; i < listOfBuses.size() - 1; i++) {
            if (listOfBuses.get(i).getTimeDeparture().equals(listOfBuses.get(i + 1).getTimeDeparture())) {
                listOfBuses.remove(listOfBuses.get(i).getTimeArrival().isAfter(listOfBuses.get(i + 1).getTimeArrival()) ?
                        listOfBuses.get(i) : listOfBuses.get(i + 1));
                i--;
            }
        }
    }

    public void checkingTheBest() {
        for (int i = 0; i < listOfBuses.size() - 1; i++) {
            if (listOfBuses.get(i + 1).getTimeArrival().isBefore((listOfBuses.get(i).getTimeArrival()))) {
                listOfBuses.remove(listOfBuses.get(i));
                i--;
            }
        }
    }

    public void printFile(String path) throws FileNotFoundException {
        try (PrintStream ps = new PrintStream(path)) {
            List<Bus> posh = listOfBuses.stream().filter(item -> item.getCompany().equals("Posh")).collect(Collectors.toList());
            for (int i = 0; i < posh.size() - 1; i++) {
                ps.println(posh.get(i));
            }
            if (posh.size() != 0) {
                ps.print(posh.get(posh.size() - 1));
            }
            List<Bus> grotty = listOfBuses.stream().filter(item -> item.getCompany().equals("Grotty")).collect(Collectors.toList());
            if (posh.size() != 0 && grotty.size() != 0) {
                ps.print("\n\n");
            }
            for (int i = 0; i < grotty.size() - 1; i++) {
                ps.println(grotty.get(i));
            }
            if (grotty.size() != 0) {
                ps.print(grotty.get(grotty.size() - 1));
            }
        }
    }

    public void printListOfBuses() {
        System.out.println("our buses");
        listOfBuses.forEach(System.out::println);
    }
}

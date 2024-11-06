import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Trip {
    private int id;
    private String start;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalDate date;
    private int bookedSeats;
    private double price;
    private Employee driver;
    private Train train;
    private ArrayList<Passenger> passengers;

    
}

public class Main {
	public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
        for (Terminal terminal : airport.getTerminals()) 
		{ 
			for (Flight flight : terminal.getFlights()) 
			{ 
				if (flight.getType() == Flight.Type.ARRIVAL) continue;
				if ((timeToMinutes(System.currentTimeMillis()) + 120) >= timeToMinutes(flight.getDate().getTime())) {
					flights.add(flight);
				}
			}	
		}
		return flights;
	}
	
	public static long timeToMinutes(long timeMillis) {
		return timeMillis / 1000 / 60;
	}
}
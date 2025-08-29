
import java.io.*;
import java.util.*;


public class Main {
	
	public Main() {
		stationReader();
		trainLineReader();
		listStation();
		listTrainLine();
		stationTrainLine();
		trainLineStation();
	}
	
	private Map<String, Station> stations = new HashMap<String, Station>();//Ideas from this repository: "https://github.com/Shouchen-G/WellingtonTrains/blob/main/src/TrainSystemUI.java"
	private Map<String, TrainLine> trainLines = new HashMap<String, TrainLine>();

	
	public void stationReader() {//Upload stations' data
        try {
            Scanner scanner = new Scanner(new File("stations.data"));
            while(scanner.hasNext()) {
            	String stationName = scanner.next();
            	int zone = scanner.nextInt();
            	double dist = scanner.nextDouble();
            	Station station = new Station(stationName, zone, dist);
                stations.put(stationName, station); 
            }
                scanner.close();
         }catch(IOException e) {
        	 System.out.println("Error loading file: " + e.getMessage());
       } 
}	
	public void trainLineReader() {
		    try (Scanner scan = new Scanner(new File("train-lines.data"))) {//Upload train-lines' data
		        while (scan.hasNext()) {
		            String trainLineName = scan.next().trim(); 
		            // Create a line object and put it into the map
		            TrainLine trainLine = new TrainLine(trainLineName);
		            trainLines.put(trainLineName, trainLine);

		            // Open the corresponding -stations.data file
		            File stationsFile = new File(trainLineName + "-stations.data");
		            try (Scanner sc1 = new Scanner(stationsFile)) {
		                while (sc1.hasNext()) {
		                    String stationName = sc1.nextLine().trim();
		                    Station s = stations.get(stationName);
		                    if (s == null) {
		                        s = new Station(stationName, 0, 0.0);
		                        stations.put(stationName, s);
		                    }
		                    trainLine.addStation(s);   //Add a station to a line
		                    s.addTrainLine(trainLine); // Station also adds this line (bidirectional)
		                }
		            }
		        }
		    } catch (IOException e) {
		        System.out.println("Error loading train lines: " + e.getMessage());
		    }
	}
		public void listTrainLine() {
			System.out.println("Train lines: ");
			for (Map.Entry<String, TrainLine> entry : trainLines.entrySet()) {
				System.out.println(entry.getValue().getName());
			}
	}
		
		public void listStation() {
			System.out.println("Station Names: ");
			for (Map.Entry<String, Station> entry : stations.entrySet()) {
				System.out.println(entry.getValue().getName());
			}
			
	}
	//List the train lines that go through a given station
	public void stationTrainLine() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter station: ");
		String stationInput = scanner.nextLine().trim();
		// Find the station object for the input station name
		Station specStation = stations.get(stationInput);
		
		if (specStation == null) {
	        for (String key : stations.keySet()) {
	            if (key.equalsIgnoreCase(stationInput)) {
	            	specStation = stations.get(key);
	                break;
	            }
	        }
	    }

	    if (specStation == null) {
	        System.out.println("Station " + stationInput + " not found in stations map.");
	        return;
	    }
		// Returns the line set to which it belongs
		Set<TrainLine> lines = specStation.getTrainLines();
		if(lines.isEmpty()) {
			System.out.println("Station " + stationInput + " is not on train line.");
		}else {
			for(TrainLine line: lines) {
				System.out.println(" - " + line.getName());
			}
		}
	}
	//List the stations along a given train line
	public void trainLineStation() {
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter train line name: ");
	    String input = sc.nextLine().trim();

	    // 1. 先直接查
	    TrainLine line = trainLines.get(input);

	    // 2. 如果大小写不同，手动匹配一次
	    if (line == null) {
	        for (String key : trainLines.keySet()) {
	            if (key.equalsIgnoreCase(input)) {
	                line = trainLines.get(key);
	                break;
	            }
	        }
	    }

	    // 3. 没找到
	    if (line == null) {
	        System.out.println("Train line '" + input + "' not found.");
	        return;
	    }
	    // 4. 找到，列出所有站
	    List<Station> stationList = line.getStations();
	    if (stationList.isEmpty()) {
	        System.out.println("No stations recorded for this line.");
	        return;
	    }

	    System.out.println("Stations on line " + line.getName() + ":");
	    for (Station st : stationList) {   
	        System.out.println(" - " + st.getName());
	    }

	}

	
	public static void main(String[] args)  {
		new Main();
		
	}
}

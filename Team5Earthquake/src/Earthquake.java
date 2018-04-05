import java.util.Comparator;

public class Earthquake {

	//establish our variables that encompass the earthquake object
	private String time = "";
	private double latitude = 0f;
	private double longitude = 0f;
	private double depth = 0f;
	private double mag = 0f;
	private String magType = "";
	private int nst = 0;
	private double gap = 0f;
	private double dMin = 0f;
	private double rms = 0f;
	private String net = "";
	private String id = "";
	private String updated = "";
	private String place = "";
	private String type = "";
	private double horError = 0f;
	private double depthError = 0f;
	private double magError = 0f;
	private int magNst = 0;
	private String status = "";
	private String locSource = "";
	private String magSource = "";

	//no-arg constructor
	public Earthquake() {
	}
	
	//constructor that takes every variable, and then sets the values of the object
	public Earthquake (String time, double latitude, double longitude, double depth, double mag, String magType,
			int nst, double gap, double dMin, double rms, String net, String id, String updated, String place,
			String type, double horError, double depthError, double magError, 
			int magNst, String status, String locSource, String magSource) {
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.depth = depth;
		this.mag = mag;
		this.magType = magType;
		this.nst = nst;
		this.gap = gap;
		this.dMin = dMin;
		this.rms = rms;
		this.net = net;
		this.id = id;
		this.updated = updated;
		this.place = place;
		this.type = type;
		this.horError = horError;
		this.depthError = depthError;
		this.magError = magError;
		this.magNst = magNst;
		this.status = status;
		this.locSource = locSource;
		this.magSource = magSource;
	}

	//getters 
	public String getTime() {
		return time;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getDepth() {
		return depth;
	}

	public double getMag() {
		return mag;
	}

	public String getMagType() {
		return magType;
	}

	public int getNst() {
		return nst;
	}

	public double getGap() {
		return gap;
	}

	public double getdMin() {
		return dMin;
	}

	public double getRms() {
		return rms;
	}

	public String getNet() {
		return net;
	}

	public String getId() {
		return id;
	}

	public String getUpdated() {
		return updated;
	}

	public String getPlace() {
		return place;
	}

	public String getType() {
		return type;
	}

	public double getHorError() {
		return horError;
	}

	public double getDepthError() {
		return depthError;
	}

	public double getMagError() {
		return magError;
	}

	public int getMagNst() {
		return magNst;
	}

	public String getStatus() {
		return status;
	}

	public String getLocSource() {
		return locSource;
	}

	public String getMagSource() {
		return magSource;
	}

	//override our toString method so when you print the earthquake object, you will see this information about it.
	@Override
	public String toString() {
		return "\n\nEarthquake: \n Time: " + time + "\n Latitude: " + latitude + "\n Longitude: " + longitude +
				"\n Depth: " + depth + "\n Magnitude: " + mag + "\n Magnitude Type: " + magType + "\n Number of Stations Contributing to Location: " + nst
				+ "\n Gap Between Stations: " + gap + "\n Distance from Epicenter to Nearest Station: " + dMin + "\n Accuracy of Predicted Arrival Times to Observed Arrival Times: " + rms
				+ "\n Network Contributor ID: " + net + "\n Earthquake ID: " + id + "\n Updated Date/Time: " + updated + "\n Location Earthquake Occured: " + place
				+ "\n Type of Event: " + type + "\n Horizontal Error (km): " + horError + "\n Depth Error (km): " + depthError
				+ "\n Magnitude Error (km): " + magError + "\n Amount of Stations that Determined Magnitude: " + magNst + "\n Reviewed Status: " + status
				+ "\n Location Source: " + locSource + "\n Magnitude Source: " + magSource;
	}

	// Test Code for Testing Early Earthquakes
//	public static void main(String[] args) {		
//		
//				Earthquake earth1  = new Earthquake("2018-03-08T17:58:27.780Z", 33.4915f,
//						-116.783f, 4.24f, 0.76f, "ml", 25, 48f, 0.08307f, 0.18f,
//						"ci", "ci37890583", "2018-03-08T18:02:00.885Z",
//						"9km NE of Aguanga, CA", "earthquake", 0.27f, 0.59f,
//						0.17f, 22, automatic, "ci", "ci");		
//				System.out.println(earth1);	
//	}

	public static Comparator<Earthquake> EquDepthComparator = new Comparator<Earthquake>() {
		public int compare(Earthquake arg0, Earthquake arg1) {
			// TODO Auto-generated method stub
			if (arg0.getDepth() < arg1.getDepth()) return -1;
			if (arg0.getDepth() > arg1.getDepth()) return 1;
			return 0;
		}
	};

	public static Comparator<Earthquake> EquMagComparator = new Comparator<Earthquake>() {
		public int compare(Earthquake arg0, Earthquake arg1) {
			// TODO Auto-generated method stub
			if (arg0.getMag() < arg1.getMag()) return -1;
			if (arg0.getMag() > arg1.getMag()) return 1;
			return 0;
		}
	};

	public static Comparator<Earthquake> EquLocComparator = new Comparator<Earthquake>() {
		public int compare(Earthquake arg0, Earthquake arg1) {
			// TODO Auto-generated method stub
			String quake1 = arg0.getPlace().toUpperCase();
			String quake2 = arg1.getPlace().toUpperCase();

			//ascending order
			return quake1.compareTo(quake2);
		}
	};
	
	public static Comparator<Earthquake> EquDateComparator = new Comparator<Earthquake>() {
		public int compare(Earthquake arg0, Earthquake arg1) {
			// TODO Auto-generated method stub
			String quake1 = arg0.getTime();
			
			String quake2 = arg1.getTime();

			//ascending order
			return quake1.compareTo(quake2);
		}
	};
	
	public static Comparator<Earthquake> EquStatusComparator = new Comparator<Earthquake>() {
		public int compare(Earthquake arg0, Earthquake arg1) {
			// TODO Auto-generated method stub
			String quake1 = arg0.getStatus().toUpperCase();
			String quake2 = arg1.getStatus().toUpperCase();

			//ascending order
			return quake1.compareTo(quake2);
		}
	};
}

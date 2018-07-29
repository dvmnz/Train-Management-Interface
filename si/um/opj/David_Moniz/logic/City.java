package si.um.opj.David_Moniz.logic;


public class City {
		
		private String city;
		private String country;

		/**
		 * Default constructor 
		 */
		public City() {
		}
		/**
		 * Parameterized constructor(1):
		 * @param city
		 * @param country
		 */
		public City(String city,String country) {
			this.city = city;
			this.country = country;
		}
		/**
		 * Get City name
		 * @return
		 */
		public String getcity() {
			return city;
		}
		/**
		 * Set City name
		 * @param city
		 */
		public void setcity(String city) {
			this.city = city;
		}
		/**
		 * Get Country name
		 * @return
		 */
		public String getcountry() {
			return country;
		}
		/**
		 * 
		 * 
		 * Set Country name
		 * @param country
		 */
		public void setcountry(String country) {
			this.country = country;
		}
		/**
		 * Override toString
		 */
		@Override
		public String toString() {
			return "City is " + city + " and country is " + country;
		}
}	
		

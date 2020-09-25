package hw1;

/* @author Donald Calhoun
 *  A Location object just represents a place name along with a cost per night for lodging. There is
	a constructor for initializing the location name and lodging cost and there are several accessor
	methods
 */

public class Location {
	
	//Creating variables to use later
	private String name;
	private int cost;
	
	// Define the cost of a postcard rounded to nearest int
	public static final double RELATIVE_COST_OF_POSTCARD = 0.05;
	
	/* Creates new location with a set cost per night
	 * @param givenName
	 * 	 the name of the city
	 * @param givenLodgingCost
	 * 	 cost to stay in the city
	 */
	public Location(String givenName, int givenLodgingCost) {
		name = givenName;
		cost = givenLodgingCost;
	}
	
	/*Returns this location's name.
	 * @return
	 * 	 name of the city
	 */
	public String getName() {
		return name;
	}
	
	/*Returns this location's lodging cost per night.
	 * @return
	 * 	 the cost to stay
	 */
	public int lodgingCost() {
		return cost;
	}
	
	/*Returns the cost to send a postcard from this location. The value is a percentage of
	the lodging cost specified by the constant RELATIVE_COST_OF_POSTCARD,
	rounded to the nearest integer.
	* @return
	* 	 cost to send a postcard
	*/
	public int costToSendPostcard() {
		return (int)(Math.round((double)(RELATIVE_COST_OF_POSTCARD * cost)));
	}
	
	/*Returns the number of nights of lodging in this location that a backpacker could
	afford with the given amount of money.
	* @param funds
	* 	 the money you have
	* @return
	* 	 the maximum nights you can stay
	*/
	public int maxLengthOfStay(int funds) {
		return funds/cost;
	}
	
	/*Returns the number of postcards that a backpacker could afford to send from this
	location with the given amount of money.
	* @param funds
	* 	 the money you have
	* @return
	* 	 the max number of postcards
	*/
	public int maxNumberOfPostcards(int funds) {
		return funds/costToSendPostcard();
	}
	

}

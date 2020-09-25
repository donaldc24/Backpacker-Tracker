package hw1;

/* @author Donald Calhoun
 * A Backpacker object models a student bumming around Europe, possibly with very little
	money. She has a certain amount of money and a current location, represented by a Location
	object. We assume that she has a rail pass so there is no cost to go from one place to another.
	(We also do not consider the need to eat in this model!) The basic operation is to visit a location
	for a given number of nights. The name of the location, and the number of nights spent there, are
	appended to a string referred to as the "journal". Each location has a certain lodging cost for
	staying there. If the backpacker doesn't have enough money for lodging, she has to sleep at the
	train station for some of those nights. The backpacker's funds can be replenished by calling
	home. However, the backpacker's parents are stuck at home working full time and may not have
	much sympathy for the backpacker running out of money while gallivanting around Europe like
	the free spirit that she is. The amount they actually send is proportional to the number of
	postcards the backpacker has sent to them since the last time she asked for money. Of course,
	sending a postcard costs money too! If things are so bad that the backpacker doesn't even have
	enough money to send a postcard home from her current location, we say that the backpacker is
	"SOL
 */

public class Backpacker {
	
	//Creating variables to use later
	private int funds;
	private int nights;
	private int nightsAtTrain;
	private int numPostcards;
	private Location locate;
	private String currentL;
	private String journal;
	
	// multiplier based on how many postcards sent home
	public static final int SYMPATHY_FACTOR = 30;

	/*Constructs a backpacker starting out with the given amount of money in the given
	location.
	* @param initialFunds
	* 	 the money you start with
	* @param initialLocation
	* 	 the place you start at
	*/
	public Backpacker(int initialFunds, Location initialLocation) {
		funds = initialFunds;
		locate = initialLocation;
		currentL = initialLocation.getName();
		nightsAtTrain = 0;
		journal = currentL + "(start)";
	}

	/*Returns the name of the backpacker's current location.
	 * @return
	 * 	 the current location
	 */
	public String getCurrentLocation() {
		return currentL;
	}

	/*Returns the amount of money the backpacker currently has.
	 * @return
	 * 	 the current funds
	 */
	public int getCurrentFunds() {
		return funds;
	}
	
	/*Returns the backpacker's journal. The journal is a string of comma-separated
	values of the form locationname(number_of_nights) containing the cities visited
	by the backpacker, in the order visited, along with the number of nights spent in
	each.
	* @return
	* 	 the journal of where you have been
	*/
	public String getJournal() {
		return journal;
	}

	/*Returns true if the backpacker does not have enough money to send a postcard
	from the current location.
	* @return 
	* 	 true or false that you don't have enough to send postcard
	*/
	public boolean isSOL() {
		return locate.costToSendPostcard() > funds;
	}

	/*Returns the number of nights the backpacker has spent in a train station when
	visiting a location without enough money for lodging.
	* @return
	* 	 the nights you spend at the train station
	*/
	public int getTotalNightsInTrainStation() {
		return nightsAtTrain;
	}

	/*Simulates a visit by this backpacker to the given location for the given number of
	nights. The backpacker's funds are reduced based on the number of nights of
	lodging purchased.
	* @param c
	* 	 the object Location, where you are
	* @param numNights
	* 	 the number of nights you plan to stay
	*/
	public void visit(Location c, int numNights) {
		locate = c;
		currentL = c.getName();
		nights = numNights;
		
		// finding if nights is greater than maxLengthOfStay you can afford
		int maxStay = Math.min(nights,locate.maxLengthOfStay(funds));
		if (maxStay < nights) {
			// if maxStay is less than nights then add to nightsAtTrain
			nightsAtTrain = nightsAtTrain + (nights-maxStay);
		}

		// Update journal with new location
		journal = journal + "," + currentL + "(" + nights + ")";
		// Update funds
		funds = funds - (maxStay * locate.lodgingCost());
	}

	/*Sends the given number of postcards, if possible, reducing the backpacker's funds
	appropriately and increasing the count of postcards sent. If there is not enough
	money, sends as many postcards as possible without allowing the funds to go
	below zero.
	* @param howMany
	* 	 the number of postcards you want to send home
	*/
	public void sendPostcardsHome(int howMany) {
		// Checking to make sure I don't get negative number
		int numCards = Math.min(funds/locate.costToSendPostcard(), howMany);
		
		// Updating funds and numPostcards
		funds = funds - (locate.costToSendPostcard() * numCards);
		numPostcards = numPostcards + numCards;
	}

	/*Increases the backpacker's funds by an amount equal to SYMPATHY_FACTOR 
	times the number of postcards sent since the last call to this method, and resets the
	count of the number of postcards sent back to zero.*/
	public void callHomeForMoney() {
		// calculating how much money to add to funds and setting numPostcards back to zero
		funds = funds + (SYMPATHY_FACTOR * numPostcards);
		numPostcards = 0;
	}

}

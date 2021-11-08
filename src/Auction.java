import java.util.*;

public class Auction {
	
	private String description;
	private double timeRemaining;
	private User seller;
	private double price;
	private User highestBidder;
	private User previousBidder;
	private ArrayList<User> users = new ArrayList<User>();
	
	public Auction (String des, double time, User seller, double price, User winner, User previous)
	{
		description = des;
		timeRemaining = time;
		this.seller = seller; 
		this.price = price; 
		highestBidder = winner;
		previousBidder = previous;
	}
	
	public void endAuction()
	{
		System.out.println ("Congrats to " + highestBidder.getName() + " on winning this auction for " + price + " dollars!");
		seller.giveMoney(price);
		System.out.println (seller.getName() + " now has " + seller.getMoney() + " dollars in their account");
		for (int k = 0; k < users.size(); k++)
		{
			System.out.println (users.get(k).getName() + " has " + users.get(k).getMoney() + " dollars in their account");
		}
	}
	
	public String getInfo ()
	{
		String output = "";
		output += "product description: " + description +" " + "\n";
		output += "time left on auction: " + timeRemaining + " " + "\n";
		output += "name of seller: " + seller.getName() + " " + "\n";
		output += "price of item: " + price + "\n";
		if (highestBidder != null)
		{
			output += "highestBidder: " + highestBidder.getName() + "\n";
		}
		else
		{
			output+= "there are no bids yet on this item" + "\n";
		}
		
		return output;
	}
	
	public double getTimeRemaining()
	{
		return timeRemaining;
	}
	
	public void bid (double bidAmount, String name)
	// split bid into 2 modifer functions: 
	// check if address is a user 
	// check if address has sufficient funds
	{
		User u = null;
		for (int k = 0; k < users.size(); k++)
		{
			if (users.get(k).getName().equals(name))
			{
				u = users.get(k);
			}
			else if (k == users.size()-1)
			{
				break;
			}
		}
		if (u.allowPurchase(price) && bidAmount > price)
		{
			previousBidder = highestBidder;
			u.takeMoney(bidAmount);
			highestBidder = u;
			if (previousBidder != null)
			{
				previousBidder.giveMoney(price);
			}
			price = bidAmount;
			timeRemaining+=5;
		}
		else if (u.allowPurchase((price)) && bidAmount <= price)
		{
			System.out.println ("You need to bid higher than the current price");
		}
		else
		{
			System.out.println ("You have insufficent funds");
		}
	}
	
	public void addUser (String name, double money)
	{
		users.add(new User (name,money));
		System.out.println (name + " was added to auction");
	}
	
	public void setTime(int newTime)
	{
		timeRemaining = newTime;
	}
	
	public static void main (String [] args)
	{
		Auction a = new Auction ("Baseball", 10, new User ("Alex", 20), 3, null, null);
		System.out.println (a.getInfo());
		a.addUser("Tyler", 50);
		a.addUser("Zane", 10);
		a.addUser ("Matteo", 0);
		a.bid(4, "Tyler");
		System.out.println (a.getInfo());
		a.bid(10, "Zane");
		System.out.println (a.getInfo());
		a.bid(20, "Matteo");
		a.bid(20, "Tyler");
		System.out.println (a.getInfo());
		a.setTime(0);
		a.endAuction();
	}
}

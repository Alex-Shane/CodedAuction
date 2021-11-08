
public class User {
	private String userName;
	private double amountMoney;
	
	public User (String name, double money)
	{
		userName = name;
		amountMoney = money;
	}
	
	public boolean allowPurchase (double price)
	{
		if (amountMoney >= price)
		{
			return true;
		}
		return false;
	}
	
	public String getName()
	{
		return userName;
	}
	
	public double getMoney()
	{
		return amountMoney;
	}
	
	public void takeMoney (double spent)
	{
		amountMoney = amountMoney-spent;
	}
	
	public void giveMoney (double recovered)
	{
		amountMoney = amountMoney+recovered;
	}
}

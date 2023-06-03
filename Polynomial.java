

public class Polynomial
{
	double [] coefs;
	
	public Polynomial()
	{
		this.coefs = new double[0];
	}
	
	public Polynomial (double [] newcoefs)
	{
		this.coefs = newcoefs;
	}
	
	public Polynomial add (Polynomial other)
	{
		int length = 0;
		if (other.coefs.length > coefs.length)
		{
			length = other.coefs.length;
		}
		else
		{
			length = coefs.length;
		}
		double[] poly = new double[length];
		for (int i  = 0; i < coefs.length; i++)
		{
			poly[i] = coefs[i];
		}
		for (int i = 0; i < length; i++)
		{
			poly[i] += other.coefs[i];
		}
		Polynomial p = new Polynomial(poly);
		return p;
	}
	
	public double evaluate (double num)
	{
		double sum = 0;
		for (int i = 0; i < coefs.length; i++)
		{
			sum += coefs[i] * Math.pow(num, i );
		}
		return sum;
	}
	
	public boolean hasRoot (double num)
	{
		double sum = evaluate(num);
		return sum == 0;
	}
}
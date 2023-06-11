import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
public class Polynomial
{
	double [] coefs;
	int [] exps;
	
	public Polynomial()
	{
		this.coefs = null;
		this.exps = null;
	}
	
	public Polynomial (double [] newcoefs, int [] newexps)
	{
		this.coefs = newcoefs;
		this.exps = newexps;
	}
	
	public Polynomial (File f) throws Exception
	{
		Scanner scan = new Scanner(f);
		if (!scan.hasNextLine())
		{
			this.coefs = null;
			this.exps = null;
		}
		else
		{
			String poly = scan.nextLine();
			poly = poly.replace("-","+-" );
			String[] terms = poly.split("\\+");
			this.coefs = new double[terms.length];
			this.exps = new int[terms.length];
			for (int i = 0; i < terms.length; i++)
			{
				String[] term = terms[i].split("x");
				this.coefs[i] = Double.parseDouble(term[0]);
				if (term.length <= 1)
				{
					this.exps[i] = 0;
				}
				else
				{
					this.exps[i] = Integer.parseInt(term[1]);
				}
			}
		}
		scan.close();
	}

	private int[] fillup (int[] fill)
	{
		for (int i = 0; i < fill.length; i++)
		{
			fill[i] = i;
		}
		return fill;
	}


	
	private int max(int[] dub)
	{
		int m = dub[0];
		for (int i = 0; i < dub.length; i++)
		{
			if (dub[i] > m)
			{
				m = dub[i];
			}
		}
		return m;
	}

	public Polynomial add (Polynomial other)
	{
		int m1 = max(this.exps);
		int m2 = max(other.exps);
		int[] newexps = new int[Math.max(m1, m2) + 1];
		double [] newcoefs = new double[Math.max(m1, m2) + 1];
		newexps = fillup(newexps);
		for (int i = 0; i < this.coefs.length; i++)
		{
			newcoefs[this.exps[i]] = this.coefs[i];
		}
		for (int i = 0; i < other.coefs.length; i++)
		{
			newcoefs[other.exps[i]] += other.coefs[i];
		}
		int count = 0;
		for (int i = 0; i < newcoefs.length; i++)
		{
			if (newcoefs[i] != 0)
			{
				count++;
			}
		}
		int[] nexps = new int[count];
		double[] ncoefs = new double[count];
		count = 0;
		for (int i = 0; i < newexps.length; i++)
		{
			if (newcoefs[i] != 0)
			{
				ncoefs[count] = newcoefs[i];
				nexps[count] = i;
				count++;
			}
		}
		Polynomial p = new Polynomial(ncoefs, nexps);
		return p;
	}
	
	public double evaluate (double num)
	{
		double sum = 0;
		for (int i = 0; i < coefs.length; i++)
		{
			sum += coefs[i] * Math.pow(num, exps[i]);
		}
		return sum;
	}
	
	public boolean hasRoot (double num)
	{
		double sum = evaluate(num);
		return sum == 0;
	}
	

	private int maxSum(int[] arr)
	{
		int sum = 0;
		for (int i = 0; i < this.exps.length; i++)
		{
			for (int j = 0; j < arr.length; j++)
			{
				if (this.exps[i] + arr[j] > sum)
				{
					sum = this.exps[i] + arr[j];
				}
			}		
		}
		return sum;
	}

	public Polynomial multiply(Polynomial other)
	{
		int max = maxSum(other.exps);
		int[] tempexps = new int[max + 1];
		double [] tempcoefs = new double[max + 1];
		tempexps = fillup(tempexps);
		for (int i = 0; i < this.exps.length; i++)
		{
			for (int j = 0; j < other.exps.length; j++)
			{
				tempcoefs[this.exps[i] + other.exps[j]] += this.coefs[i] * other.coefs[j];
			}
		}
		int count = 0; 
		for (int i = 0; i < tempcoefs.length; i++)
		{
			if (tempcoefs[i] != 0)
			{
				count++;
			}
		}
		double[] newcoefs = new double[count];
		int[] newexps = new int[count];
		count = 0;
		for (int i = 0; i < tempcoefs.length; i++)
		{
			if (tempcoefs[i] != 0)
			{
				newcoefs[count] = tempcoefs[i];
				newexps[count] = tempexps[i];
				count++; 
			}
		}
		Polynomial p = new Polynomial(newcoefs, newexps);
		return p;
	}
	public void saveToFile (String filename) throws Exception
	{
		File f = new File(filename);
		String save = "";
		for (int i = 0; i < this.coefs.length; i++)
		{
			save += this.coefs[i];
			if (this.exps[i] != 0)
			{
				save += "x" + this.exps[i];
			}
			save += "+";
		}
		save = save.replace("+-", "-");
		save = save.substring(0, save.length() - 1);
		FileWriter fw = new FileWriter(f);
		fw.write(save);
		fw.close();
	}
}


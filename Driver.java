import java.io.File;
public class Driver 
{
	public static void main(String [] args) throws Exception
	{
		/* 
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		*/
		double[] a1 = {6, -2, 5, 8};
		int [] b1 = {0, 1, 4, 8};
		Polynomial p1 = new Polynomial(a1, b1);
		double[] a2 = {3, 4, -7, -5, 9};
		int [] b2 = {2, 2, 3, 6, 3};
		Polynomial p2 = new Polynomial(a2, b2);
		Polynomial p3 = p1.add(p2);
		printPoly(p3);
		System.out.println(p2.evaluate(3));
		if (p2.hasRoot(0));
		{
			System.out.println("0 is a root of p2");
		}
		double[] a3 = {3, 2, 5};
		int[] b3 = {0, 2, 1};
		double[] a4 = {2, 4, 1};
		int[] b4 = {2, 3, 4};
		Polynomial p4 = new Polynomial(a3, b3);
		Polynomial p5 = new Polynomial(a4, b4);
		Polynomial p6 = p4.multiply(p5);
		printPoly(p6);
		File f = new File("newtext.txt");
		Polynomial p7 = new Polynomial(f);
		printPoly(p7);
		p4.saveToFile("newtext.txt");
	}

	public static void printPoly (Polynomial p)
	{
		System.out.println("Printing Coefficients:");
		for (int i = 0; i < p.coefs.length; i++)
		{
			System.out.println(p.coefs[i]);
		}
		System.out.println("Printing Exponents:");
		for (int i = 0; i < p.exps.length; i++)
		{
			System.out.println(p.exps[i]);
		}
	}
}
package friends;

import java.util.ArrayList;
import java.io.File;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Tester
{
	public static void main (String[] args) throws IOException
	{
		File f = new File("sample.txt");
		Scanner sc = new Scanner(f);
		Graph g = new Graph(sc);
		
		
		ArrayList<String> ar = Friends.shortestChain(g, "rachel", "tom");
		
		for (int w = 0; w < ar.size(); w++)
		{
			if (w == ar.size() - 1)
			{
				System.out.print(ar.get(w));
				break;
			}
			System.out.print(ar.get(w) + " -- ");
		}
		
		System.out.println("\n\n");
		
		ArrayList<ArrayList<String>> ar2 = Friends.cliques(g, "penn state");
		System.out.print("[");
		for (int x = 0; x < ar2.size(); x++)
		{
			System.out.print("( ");
			for (int i = 0; i < ar2.get(x).size(); i++)
			{
				if (i == ar2.get(x).size() - 1)
				{
					System.out.print(ar2.get(x).get(i));
				}
				else
				System.out.print(ar2.get(x).get(i) + ", ");
			}
			
			System.out.print(" )");
		}
		System.out.print("]");
		
		System.out.println("\n\n");
		
		ArrayList<String> connect = Friends.connectors(g);
		
		System.out.print("[");
		
		for (int p = 0; p < connect.size(); p++)
		{
			if (p == connect.size() - 1)
			{
				System.out.print(connect.get(p));
				break;
			}
			System.out.print(connect.get(p) + ", ");
		}
		System.out.print("]");
	}
	
	
}
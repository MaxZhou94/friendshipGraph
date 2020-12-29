package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		
		ArrayList<String> ar = new ArrayList<String>();
		
		if (g == null || p1 == null || p2 == null)
		{
			return null;
		}
		
		p1 = p1.toLowerCase();
		p2 = p2.toLowerCase();
		
		Queue<Person> q = new Queue<Person>();
		q.enqueue(g.members[g.map.get(p1)]);
		
		int graphLength = g.members.length;
		boolean[] checkVisited = new boolean[graphLength];
		
		Person[] visited = new Person[graphLength];
		
		// BFS
		while(!q.isEmpty())
		{
			Person p = q.dequeue();
			
			checkVisited[g.map.get(p.name)] = true;

			if (p.first == null)
			{
				return null;
			}
			
			Friend f = p.first;
			
			while (f != null)
			{
				if (checkVisited[f.fnum] == false)
				{
					checkVisited[f.fnum] = true;
					visited[f.fnum] = p;
					Person newF = g.members[f.fnum];
					q.enqueue(newF);
					
					if (g.members[f.fnum].name.contentEquals(p2))
					{
						p = g.members[f.fnum];
						
						while (!p.name.equals(p1))
						{
							ar.add(0, p.name);
							p = visited[g.map.get(p.name)];
						}
						
						ar.add(0, p1);
						return ar;
					}
				}
				f = f.next;
			}
		}
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return null;
	}

	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		
		if (g == null || school == null)
		{
			return null;
		}
		
		ArrayList<ArrayList<String>> ar = new ArrayList<ArrayList<String>>();
		ArrayList<String> visited = new ArrayList<String>();
		
		for (int i = 0; i < g.members.length; i++)
		{
			Person p = g.members[i];
			
			if (p.school == null)
			{
//System.out.println("Not in school");
				continue;
			}
			
			if (!(p.school).equals(school))
			{
//System.out.println("Different school");
				continue;
			}
			
			boolean inVisited = false;
			for (int visitedIndex = 0; visitedIndex < visited.size(); visitedIndex++)
			{
				if ((p.name).equals(visited.get(visitedIndex)))
				{
					inVisited = true;
					break;
				}
				
			}
			if (inVisited == true)
			{
				continue;
			}
			
			else
			{
				ArrayList<String> bfsArray = bfs(p, school, g);
				for (int c = 0; c < bfsArray.size(); c++)
				{
					visited.add(bfsArray.get(c));
				}
				ar.add(bfsArray);
//System.out.println("Master array size: " + ar.size());
			}
		}
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return ar;
		
	}
	
	public static ArrayList<String> bfs(Person start, String school, Graph g)
	{
		ArrayList<String> ar = new ArrayList<String>();
		Queue<Person> q = new Queue<Person>();
//System.out.println("starting size" + ar.size());

		if (start.school == null)
		{
			return null;
		}
		q.enqueue(start);

		while (!q.isEmpty())
		{
			Person p = q.dequeue();
			
			for (int i = 0; i < ar.size(); i++)
			{
				if ((p.name).equals(ar.get(i)))
				{
					return ar;
				}
			}
			
			ar.add(p.name);
			
//System.out.println("bfs array size: " + ar.size());
//System.out.println("Dequeued person: " + p.name);

			if (p.first == null)
			{
				continue;
			}
			
			Friend f = p.first;
			
			while (f != null)
			{
				if (g.members[f.fnum].school == null)
				{
					break;
				}
				else if (!g.members[f.fnum].school.equals(school))
				{
					break;
				}
				
				Person newF = g.members[f.fnum];
				
				
				
				
				q.enqueue(newF);
				f = f.next;
			}
		}
		
		return ar;
	}

	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) 
	{
		if (g == null)
		{
			return null;
		}
		
		ArrayList<String> connect = new ArrayList<String>();
		ArrayList<Boolean> visited = new ArrayList<Boolean>();
		for (int i = 0; i < g.members.length; i++)
		{
			boolean bol = false;
			visited.add(bol);
		}
		ArrayList<String> last = new ArrayList<String>();
		ArrayList<Integer> dfs = new ArrayList<Integer>();
		for (int i = 0; i < g.members.length; i++)
		{
			int x = 0;
			dfs.add(x);
		}
		ArrayList<Integer> before = new ArrayList<Integer>();
		for (int i = 0; i < g.members.length; i++)
		{
			int x = 0;
			before.add(x);
		}

		int i = 0;
		
		while (i < g.members.length)
		{
			if (visited.get(i) == false)
			{
				Person start = g.members[i];
				int[] count = {0, 0};
				boolean proceed = true;
				connect = search(g, connect, start, visited, count, last, dfs, before, proceed);
			}
			i++;
		}
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return connect;
		
	}
	
	

	public static ArrayList<String> search(Graph g, ArrayList<String> connect, Person start, 
			ArrayList<Boolean> visited, int[] count, ArrayList<String> last, ArrayList<Integer> dfs, 
			ArrayList<Integer> before, boolean proceed)
	{
		visited.set(g.map.get(start.name), true);
		Friend f = start.first;
		dfs.set(g.map.get(start.name), count[0]);		
		before.set(g.map.get(start.name), count[1]);
		
		while (f != null)
		{
			if (visited.get(f.fnum) == false)
			{
				for (int i = 0; i < 2; i++)
				{
					count[i]++;
				}
				
				connect = search(g, connect, g.members[f.fnum], visited, count, last, dfs, before, false);
				
				if (dfs.get(g.map.get(start.name)) <= before.get(f.fnum))
				{
					if (connect.contains(start.name) == false && last.contains(start.name))
					{
						connect.add(start.name);
					}
					else if (connect.contains(start.name) == false && proceed == false)
					{
						connect.add(start.name);
					}
				}
				else
				{
					int startNumber = before.get(g.map.get(start.name));
					int friendNumber = before.get(f.fnum);
					
					if (startNumber < friendNumber)
					{
						before.set(g.map.get(start.name), startNumber);
					}
					else
					{
						before.set(g.map.get(start.name), friendNumber);
					}
				}
				last.add(start.name);
			}
			else
			{
				int startNumber = before.get(g.map.get(start.name));
				int friendNumber = dfs.get(f.fnum);
				
				if (startNumber < friendNumber)
				{
					before.set(g.map.get(start.name), startNumber);
				}
				else
				{
					before.set(g.map.get(start.name), friendNumber);
				}
			}
			f = f.next;
		}
		
		return connect;
	}
	
}


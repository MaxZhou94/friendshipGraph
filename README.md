# friendshipGraph

Constructed an undirected graph that to represent Friendships (e.g. Facebook).

A friendship graph is an undirected graph with no self loops or multiple edges. The vertices in the graphs represent two kinds of people: students and non-students. Each vertex will store the name of the person. If the person is a student, the name of the school will also be stored.

Here is a sample friendship graph:


    (Sam,rutgers) --- (jane,rutgers) ----- (bob,rutgers)      (sergei,rutgers)
                          |                   |                   |
                          |                   |                   |
                  (kaitlin,rutgers)      (samir) ----- (aparna,rutgers)
                           |                                  |
                           |                                  |
    (ming,penn state) --- (nick,penn state) ------- (ricardo,penn state)
                              |
                              |
                        (heather,penn state)
                        
                     (michelle,cornell) ---- (rachel)
                            |
                            |
      (rich,ucla) --- (tom,ucla)
      
      
      
      
Implemented various graph algorithms (e.g., djikstra's, shortest path, DFS, BFS) in order to find:

  1. Shortest path: Intro chain
  
      - For example, if 'sam' wants an intro to 'aparna' through friends and friends of friends, there are two possible chains of intros:
      
            - sam --- jane --- kaitlin --- nick --- ricardo --- aparna
                                OR
            - sam --- jane --- bob --- samir --- aparna
            
      - The second chain is preferable since it is shorter.
      
      
   2. Cliques: Student cliques at a school
   
       - Students tend to form cliques with their friends, which creates islands that do not connect with each other.
       
       - Example: sam, jane, bob, and kaitlin form a 'Rutgers' clique.
    
    
   3. Connectors: Friends who keep friends together
       
       - Definition: In an undirected graph, vertex 'v' is a connector is there are at least two other vertices 'x' and 'w' for which every path between 'x' and 'w' goes                          through 'v'. 
       
       - If jane were to leave rutgers, sam would no longer be able to connect with anyone else.  Therefore jane is a connector.
       
       - samir is not a connector. Even if he were to leave, everyoe else could still "reach" whoever they could when samir was there, even though they may have to go through          a longer chain.
       
            
            

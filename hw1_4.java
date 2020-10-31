import java.io.BufferedReader;
import java.io.*;
import java.nio.*;
import java.util.*;
import java.lang.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class hw1_4
{
	static int i,j,k,w,h,lx,ly,maxdif,no_target;
	static HashMap< Integer, String> pathsmap = new HashMap<>();
	//static HashMap< String, String> parentmap = new HashMap<>();


/*	static Node head;
	static Node last;
	static class Node
	{
		Integer[] data;
		//Node prev;
		Node next;

		public Node(Integer[] data) 
		{
			this.data = data;
		}
	}
*/
	public static void find_path(int[][][] parent, int tx, int ty, int tc)
	{
/*		int a = tx;
		int b = ty;
		int px,py;
		//ArrayList<ArrayList<Integer>> x = new ArrayList<ArrayList<Integer>>();
		while(parent[a][b][0]!=-1)
		{
			px = parent[a][b][0];
			py = parent[a][b][1];

			System.out.println(px+" "+py);
			a=px;
			b=py;
		}
*/

		ArrayList<Integer[]> paths = new ArrayList<Integer[]>(); 
		Integer[] seq= new Integer[2]; 
		Integer[] q = new Integer[2];
		int count = 0;
		String path = "";
		/*
		Integer[] p = new Integer[2];
		p[0] = 2;
		p[1] = 3;
		paths.add(p);
		seq = paths.get(0);
		System.out.println(seq[0]+" "+seq[1]);
		p = null;

		Integer[] p = new Integer[2];
		p[0] = 5;
		p[1] = 6;
		paths.add(p);
		seq = paths.get(0);
		System.out.println(seq[0]+" "+seq[1]);
		seq = paths.get(1);
		System.out.println(seq[0]+" "+seq[1]);
		System.out.println();
		p[0] = 8;
		p[1] = 9;
		paths.add(p);
		seq = paths.get(0);
		System.out.println(seq[0]+" "+seq[1]);
		*/
		q[0] = tx;
		q[1] = ty;
		paths.add(q);
		//System.out.println("A");		
		seq = paths.get(0);
		//System.out.println(seq[0]+" "+seq[1]);
		while(parent[tx][ty][0] != -1)
		{
			Integer[] p = new Integer[2];	
			p[0] = parent[tx][ty][0];
			p[1] = parent[tx][ty][1];
/*			System.out.println(p[0]);
			System.out.println(p[1]);
			System.out.println();
*/			count++; 
			paths.add(p);
			tx = p[0];
			ty = p[1];		
			p=null;


		}
/*
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("B");		
		seq = paths.get(0);
		System.out.println(seq[0]+" "+seq[1]);

		System.out.println("C");		
		seq = paths.get(1);
		System.out.println(seq[0]+" "+seq[1]);
		seq = paths.get(2);
		System.out.println(seq[0]+" "+seq[1]);
		
*/
		//System.out.println(paths.size());
		
		for(i=paths.size()-1;i>=0;i--)    
		{
			seq = paths.get(i);
		   	for(j=0;j<2;j++)
		   	{
		   		if(j == 0)
		   		{
		   			path = path + seq[j]+",";
		   			//System.out.print(myString[j]+","); 
		   		}
		   		else
		   		{
		   			path = path + seq[j];	
		   			//System.out.print(myString[j]); 
		   		}
		   	}
		   	if(i != 0)
		   		path = path + " ";
		}
		//System.out.println(path);
		//System.out.println(count);
		pathsmap.put(tc,path);
		//System.out.println(pathsmap);
		String p = pathsmap.get(tc);
		//System.out.println(p);


/*
		ArrayList<Integer[]> outerArr = new ArrayList<Integer[]>(); 
		
		Integer[] myString1= {3,4};  
		outerArr.add(myString1);
		Integer[] myString2= {1,2};
		outerArr.add(myString2);
		int t1 = 6;
		int t2 = 5;
		Integer[] t3 = new Integer[2];
		t3[0] = t1;
		t3[1] = t2;
		outerArr.add(t3);
		
		String path="";
		int c = 1;
		for(int i=outerArr.size()-1;i>=0;i--)    
		{
			Integer[] myString= new Integer[2]; 
		   	myString=outerArr.get(i);
		   	for(int j=0;j<2;j++)
		   	{
		   		if(j == 0)
		   			{
		   				path = path + myString[j]+",";
		   				//System.out.print(myString[j]+","); 
		   			}
		   		else
		   		{
		   			path = path + myString[j];	
		   			//System.out.print(myString[j]); 
		   		}
		   	}
		   	path = path + " ";
		   	//System.out.print(" ");
		}
		System.out.println(path);
		paths.put(c,path);
		//System.out.println(paths);
		String p = paths.get(c);
		//System.out.println(p);

*/

	}
	public static void print_path(int[][] targets) throws IOException
	{
		FileWriter fw = new FileWriter("output.txt");
		for (i = 0; i < no_target; i++) 
		{
			if(targets[i][2] == -1)
			{
				if(i == no_target-1)
					fw.write("FAIL");	
				else
					fw.write("FAIL"+System.getProperty( "line.separator" ));	
			}
			else if(pathsmap.containsKey(targets[i][2]))
			{
				String p = pathsmap.get(targets[i][2]);
				//System.out.println(p);

				fw.write(p+System.getProperty( "line.separator" ));	
			}
			else
			{
				if(i == no_target-1)
					fw.write("FAIL");	
				else
					fw.write("FAIL"+System.getProperty( "line.separator" ));	
			}		
		}
 		fw.close();
	}

	public static void bfs(int[][] targets, int[][] elevation) throws IOException
	{
		System.out.println("BFS");

		Integer[] current = new Integer[2];	
		Integer[] first = new Integer[2];	

		//int[] child = new int[2];

		Integer cx,cy,diff,chx,chy,count=0;


		cx = lx;
		cy = ly;
		current[0] = lx;
		current[1] = ly;	
		//ArrayList<ArrayList<Integer>> x = new ArrayList<ArrayList<Integer> >();
		//Queue<List<Integer>> q = new LinkedList<List<Integer>>();
		int[][] visited = new int[h][w];
		Queue<Integer[]> q = new LinkedList<>();

		//q.addAll(Arrays.asList(cx,cy));
		q.add(current);
		int[][][] parent = new int[w][h][2];

		
		parent[cx][cy][0] = -1;
		parent[cx][cy][1] = -1;

		//parentmap.put(Arrays.toString(current),"[-1, -1]");

/*		child[0] = 2;
		child[1] = 3;

		HashMap< int[], int[]> parents = new HashMap<>();
		parents.put(current,child);
		int b = parents.get(current)[0];
		int c = parents.get(current)[1];
		System.out.println(b);
*/		

		
		while (!q.isEmpty())
		{
			if(count == no_target)
				break;

			//cx = q.remove();
			//cy = q.remove();
			/*cx = current[0];
			cy = current[1];
			*/

			current = q.remove();
			cx = current[0];
			cy = current[1];
			//Node first_node = new Node(first);
			//head = first_node;
			//CHECK FOR TARGET HERE
		
			for(i=0; i<k; i++)
			{
				if (targets[i][0] == cx && targets[i][1] == cy && !pathsmap.containsKey(targets[i][2]))
				{

					find_path(parent, targets[i][0], targets[i][1], targets[i][2]);
					//find_path3(targets[i][0], targets[i][1], targets[i][2]);
					
/*					Integer[] tarray = new Integer[2];
					tarray[0] = targets[i][0];
					tarray[1] = targets[i][1];
					Node target_node = new Node(tarray);
					find_path2(head, targets[i][2]);

*/

					//targets[i][0] = -1;
					//targets[i][1] = -1;
					
					count++;
				}				
			}

			visited[cy][cx] = 1;
			
			
			int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
			for (int[] direction : directions) 
			{
       	 		chx = cx + direction[0];
        		chy = cy + direction[1];
        		if(chx >=0 && chx < w && chy >= 0 && chy < h && visited[chy][chx] != 1)
            	{
            		diff = Math.abs(elevation[cy][cx] - elevation[chy][chx]);
					if(diff <= maxdif)
					{
						Integer[] child = new Integer[2];
						child[0] = chx;
						child[1] = chy;
						q.add(child);
						visited[chy][chx] = 1;
						
						parent[chx][chy][0] = cx;
						parent[chx][chy][1] = cy;

/*
						String parentstring = Arrays.toString(current);
						String childstring = Arrays.toString(child);

						parentmap.put(childstring,parentstring);
*/


            		
//						System.out.println(cx+" "+cy+" -> "+chx+" "+chy);					
					
					/*
						Node new_node = new Node(child);
						new_node.next = head;
						//new_node.prev = null;
						//head.prev = new_node;
						System.out.println("A");
						//System.out.println(new_node.data[0]+" "+new_node.data[1]);
						//System.out.println("B");
						//System.out.println(head.data[0]+" "+head.data[1]);
						
						Node new_node2 = new_node.next;
						System.out.println(new_node.data[0]+" "+new_node.data[1]+" -> "+new_node2.data[0]+" "+new_node2.data[1]);

*/
					}            		
            	}	
            }		
        }

/*        System.out.println(head.data[0]+" "+head.data[1]);		
		Integer[] t= new Integer[2];
        t[0] = 6;
        t[1] = 3;
        Node r = new Node(t);
        r.next = head;
        System.out.println(r.data[0]+" "+r.data[1]);
		r = r.next;
		System.out.println(r.data[0]+" "+r.data[1]);
		
        while(r != null)
        {
        	System.out.println();
  	        System.out.println(r.data[0]+" "+r.data[1]);
  	        r = r.next;
        	//System.out.println(last.data[0] + " ");
        	//last = last.prev;
        }

*/
		
	//	print_path(targets);
		
 		
	}	
	

	public static void ucs(int[][] targets, int[][] elevation) throws IOException
	{
		System.out.println("UCS");
 		
 		int[][] distance = new int[h][w];
 		int[][] visited = new int[h][w];
		int[][][] parent = new int[w][h][2];
		/*for(i = 0; i < h; i++)
		{
			for(j = 0; j < w; j++)
			{
				parent[j][i][0] = -1;
				parent[j][i][1] = -1;
			}
		}
*/

		//VERIFY THAT THE ABOVE ASSIGNEMENT IS NOT REQUIRED

		//Queue<Integer[]> q = new LinkedList<>();
		ArrayList<Integer[]> q = new ArrayList<Integer[]>();
 		Integer[] current = new Integer[2];	
		Integer cx, cy, chx, chy, diff, xelement, yelement, count = 0;

		current[0] = cx = lx;
		current[1] = cy = ly;
		q.add(current);
		parent[cx][cy][0] = -1;
		parent[cx][cy][1] = -1;
		//int m = 1, n = 1, iteration = 1;	
		
		while (!q.isEmpty())
		{
			if(count == k)
				break;
			
	/*		System.out.println();
			System.out.print("Queue = ");
			for (int i=0; i<q.size(); i++) 
            	System.out.print(q.get(i)[0]+" "+q.get(i)[1]+"\t");
	*/		
            current = q.remove(0);
			cx = current[0];
			cy = current[1];
	/*			System.out.println();
			System.out.println("Current = "+cx+" "+cy);
	*/					
			//CHECK FOR TARGET NODE HERE

			for(i=0; i<k; i++)
			{
				if (targets[i][0] == cx && targets[i][1] == cy && !pathsmap.containsKey(targets[i][2]))
				{
					find_path(parent, targets[i][0], targets[i][1], targets[i][2]);
					//targets[i][0] = -1;
					//targets[i][1] = -1;
					count++;
	//				System.out.println("Target cost= "+distance[cy][cx]);
				}				
			}

			visited[cy][cx] = 1;
			

//			int[][] directions1 = new int[][]{{-1,0}, {0,-1}, {0,1}, {1,0}};
//			int[][] directions2 = new int[][]{{-1,-1}, {-1,1}, {1,-1}, {1,1}};
			//int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
			int[][] directions = new int[][]{{-1,0},  {0,1}, {0,-1}, {1,0}, {1,-1}, {-1,-1},  {-1,1}, {1,1}};

				
			for(int[] direction : directions)
			{
				chx = cx + direction[0];
				chy = cy + direction[1];
	//			System.out.println(cx+" "+cy+"->"+chx+" "+chy);

				if(chx >=0 && chx < w && chy >= 0 && chy < h && (chx!=parent[cx][cy][0] || chy!=parent[cx][cy][1]))
            	{
            		diff = Math.abs(elevation[cy][cx] - elevation[chy][chx]);
					if(diff <= maxdif)
					{
	//					System.out.println("Inside");
            			Integer[] child = new Integer[2];
						child[0] = chx;
						child[1] = chy;
						int dist, position, x, y;

						if(Math.abs(direction[0]) == Math.abs(direction[1]))
							dist = distance[cy][cx] + 14;
						else
							dist = distance[cy][cx] + 10;
						
	//					System.out.println(cx+" "+cy+"->"+chx+" "+chy+" "+dist);
						
						//visited[chy][chx] = 1;
						//parent[chx][chy][0] = cx;
						//parent[chx][chy][1] = cy;
						
	//					System.out.println("Child "+child[0]+" "+child[1]);
						
						if(q.isEmpty() && distance[chy][chx] <= 0)
						{
	//						System.out.println("Empty");	
							distance[chy][chx] = dist;
							parent[chx][chy][0] = cx;
							parent[chx][chy][1] = cy;
							q.add(child);
						}	
							

						//else if (q.contains(child))
						else
						{
							int contains = 0;
							for(i = 0; i < q.size(); i++)
							{
								/*
								System.out.println("q.get(i)[0] ="+q.get(i)[0]);
								System.out.println("child[0] ="+child[0]);
								System.out.println("q.get(i)[1] ="+q.get(i)[1]);
								System.out.println("child[1] ="+child[1]);
								*/
								/*System.out.println("q.get(i)[0] ="+q.get(i)[0].getClass().getName());
								System.out.println("child[0] ="+child[0].getClass().getName());
								System.out.println("q.get(i)[1] ="+q.get(i)[1].getClass().getName());
								System.out.println("child[1] ="+child[1].getClass().getName());
								*/
								/*
								if(q.get(i)[0] == child[0])
								{
									System.out.println("TRUE 1");
								} 
								System.out.println(" "+(q.get(i)[1]-child[1]));
								
								if(q.get(i)[1] == child[1])
								{
									System.out.println("TRUE 2");
								}
								*/
								//System.out.println();
								
								if((q.get(i)[0] - child[0] ==0) && (q.get(i)[1] - child[1]==0))
								{
									contains = 1;	
	//								System.out.println("Contains");
									break;
								}
							}
							//n++;
							if(contains == 1)
							{
								position = i;//q.indexOf(child);
	/*							System.out.println("New distance: "+dist);
								System.out.println("Old distance: "+distance[chy][chx]);
	*/							
								if(dist < distance[chy][chx])
								{
	//								System.out.println("New distance is less");
									
									
									q.remove(position);
									parent[chx][chy][0] = cx;
									parent[chx][chy][1] = cy;
									//visited[y][x] = 0;
									//add the element in the appropriate position
									for (i=0; i<q.size();i++)
		 							{
										xelement = q.get(i)[0];
										yelement = q.get(i)[1];
										//System.out.println("distance= "+dist);

										if(dist < distance[yelement][xelement])
										{
											distance[chy][chx] = dist;
											q.add(i,child);
											break;
										}
									}
									if(i == q.size())
									{
										distance[chy][chx] = dist;
										q.add(child);
									}							
								}
							}
							else
							{
	/*							System.out.println("Does not contain");
								System.out.println("New distance: "+dist);
								System.out.println("Old distance: "+distance[chy][chx]);
								
	*/							if(dist < distance[chy][chx] || visited[chy][chx] == 0)
								{
									
	//								System.out.println("Does not contain_Inside");
									//System.out.println("New distance is less");
									visited[chy][chx] = 0;
									parent[chx][chy][0] = cx;
									parent[chx][chy][1] = cy;

									for (i=0; i<q.size();i++)
		 							{
										xelement = q.get(i)[0];
										yelement = q.get(i)[1];
										//System.out.println("distance= "+dist);

										if(dist < distance[yelement][xelement])
										{
											distance[chy][chx] = dist;
											q.add(i,child);
											break;
										}
									}
									if(i == q.size())
									{
										distance[chy][chx] = dist;
										q.add(child);
									}	
								}
								
							}

						}
					}
				}
			}
		}
		
/*
		for(i=133;i<143;i++)
		{
			for (j=7;j<11;j++ ) 
			{
				System.out.print(distance[i][j]+" ");
			}
			System.out.println();		
		}
*/


		//print_path(targets); 




/*		for(i=0; i<k; i++)
		{
			cx = targets[i][0];
			cy = targets[i][1];
			System.out.println("Target cost= "+distance[cy][cx]);
		}
*/		//System.out.println("Target cost= "+distance[56][2]);
		
	}
	

	public static void astar(int[][] targets, int[][] elevation) throws IOException
	{
		final long startTime = System.nanoTime();
		System.out.println("A*");

		
		ArrayList<Integer[]> q = new ArrayList<Integer[]>();
 		Integer[] current = new Integer[2];	
		Integer cx, cy, chx, chy, diff, xelement, yelement, count = 0, diffx, diffy;
		System.out.println(no_target);
		for (int tc = 0; tc < no_target; tc++)
		{
					
			int[][] distance_h = new int[h][w];
 			int[][] distance = new int[h][w];
 			int[][] visited = new int[h][w];
			int[][][] parent = new int[w][h][2];
			int[][] heuristic = new int[h][w];
			q.clear();
			current[0] = cx = lx;
			current[1] = cy = ly;
			q.add(current);
			parent[cx][cy][0] = -1;
			parent[cx][cy][1] = -1;
			
			int tx,ty;
			tx = targets[tc][0];
			ty = targets[tc][1];
			if(tx >= w || ty >= h)
				continue;
	
	//		System.out.println("Targets = "+tx+" "+ty);
			
			while(!q.isEmpty())
			{
	/*			System.out.print("Queue = ");
				for (int i=0; i<q.size(); i++) 
            		System.out.print(q.get(i)[0]+" "+q.get(i)[1]+"\t");
	*/	
				//ONLY 1 TARGET PRINTING, ADD A BREAK CONDITION MAYBE 
				//CHECK WHY ONYL 1 TARGET BY GOING THROUGH THE OUTPUT
				//ALSO CHECK IF ANS IS RIGHT	
				current = q.remove(0);
				cx = current[0];
				cy = current[1];
				visited[cy][cx] = 1;
	//			System.out.println();
	//			System.out.println("Current = "+cx+" "+cy);
			
				if (tx == cx && ty == cy)
				{
	/*				System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("Target Found");
	*/				find_path (parent, tx, ty, targets[tc][2]);
	//				System.out.println("Target cost = "+distance[ty][tx]);
					break;
				}

				int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
				
				for(int[] direction : directions)
				{
					chx = cx + direction[0];
					chy = cy + direction[1];
	//				System.out.println("A "+cx+" "+cy+"->"+chx+" "+chy);
					if(chx >=0 && chx < w && chy >= 0 && chy < h && visited[chy][chx] != 1)
	            	{
	            		diff = Math.abs(elevation[cy][cx] - elevation[chy][chx]);
						if(diff <= maxdif)
						{
	//						System.out.println("Inside");
							Integer[] child = new Integer[2];
							child[0] = chx;
							child[1] = chy;
							int dist,dist_h, position, x, y;

							//Calculating heursitic
							diffx = Math.abs(tx - chx);
							diffy = Math.abs(ty - chy);
							int difference = Math.abs(elevation[chy][chx] - elevation[ty][tx]);


							//heuristic[chy][chx] = 10 * (diffx + diffy) + (14 - 2 * 10) * Math.min(diffx,diffy) + difference;  STANFORD....NOT WORKING
								            		
							heuristic[chy][chx] = 14 * Math.min(diffx, diffy) + 10 * (Math.max(diffx,diffy) -  Math.min(diffx, diffy)) + difference; //
	//						System.out.println("Heuristic : "+heuristic[chy][chx]);
							
							//heuristic[chy][chx] = 14 * Math.min(diffx, diffy) + 10 * Math.abs(diffy - diffx) + difference; MAYBE WORKING
							
							//heuristic[chy][chx] = 14 * Math.max(diffx, diffy) + 10 * Math.min(diffy,diffx)+ diff;

							if(Math.abs(direction[0]) == Math.abs(direction[1]))
							{
								dist = distance[cy][cx] + 14 + diff; 
								dist_h = dist + heuristic[chy][chx] ; 
							}	
							else
							{
								dist = distance[cy][cx] + 10 + diff;
								dist_h = dist + heuristic[chy][chx] ; 
							}
								
							
	/*						System.out.println("Distance : "+dist);
							System.out.println("Distance with heuristic: "+dist_h);
							System.out.println("Child "+child[0]+" "+child[1]);
	*/						
							if(q.isEmpty() && distance[chy][chx] <= 0)
							{
	//							System.out.println("Empty");	
								distance[chy][chx] = dist;
								distance_h[chy][chx] = dist_h;
								parent[chx][chy][0] = cx;
								parent[chx][chy][1] = cy;
								q.add(child);
							}

							else
							{
								int contains = 0;
								for(i = 0; i < q.size(); i++)
								{
									//if(q.get(i)[0] == child[0] && q.get(i)[1] == child[1])
									if((q.get(i)[0] - child[0] ==0) && (q.get(i)[1] - child[1]==0))
									{
										contains = 1;	
	//									System.out.println("Contains");
										break;
									}
								}
								if(contains == 1)
								{
									position = i;
	//								System.out.println("New distance: "+dist);
	//								System.out.println("Old distance: "+distance[chy][chx]);
								
									if(dist_h < distance_h[chy][chx])
									{
	//									System.out.println("New distance is less ");
										q.remove(position);
										parent[chx][chy][0] = cx;
										parent[chx][chy][1] = cy;
										distance[chy][chx] = dist;
										distance_h[chy][chx] = dist_h;
										
												
										for (i=0; i<q.size();i++)
			 							{
											xelement = q.get(i)[0];
											yelement = q.get(i)[1];
	//										System.out.println("Distance = "+dist);

											if(dist_h < distance_h[yelement][xelement])
											{
												q.add(i,child);
												break;
											}
										}
										if(i == q.size())
										{
											q.add(child);
										}							
									}
								}
								else
								{
	//								System.out.println("Does not contain");
									
									if(dist_h < distance_h[chy][chx] || visited[chy][chx] == 0)
									{
	//									System.out.println("New distance is less ");
										visited[chy][chx] = 0;
										parent[chx][chy][0] = cx;
										parent[chx][chy][1] = cy;
										distance[chy][chx] = dist;
										distance_h[chy][chx] = dist_h;
												
										for (i=0; i<q.size();i++)
			 							{
											xelement = q.get(i)[0];
											yelement = q.get(i)[1];
	//										System.out.println("Distance= "+dist);

											if(dist_h < distance_h[yelement][xelement])
											{
												q.add(i,child);
												break;
											}
										}
										if(i == q.size())
										{
											q.add(child);
										}	
									}
								}
							}
						}
					}
				}
			}

		/*	System.out.println("Distance Matrix = ");

			for(i=0;i<h;i++)
			{
				for (j=0;j<w ;j++ ) 
				{
					System.out.print(distance[i][j]+" ");
				}
				System.out.println();		
			}

			System.out.println("Heuristic Matrix = ");

			for(i=0;i<h;i++)
			{
				for (j=0;j<w ;j++ ) 
				{
					System.out.print(heuristic[i][j]+" ");
				}
				System.out.println();		
			}
	
		
		System.out.println();		
		*/
		}
		//print_path(targets); 

		
	}


	public static void main(String[] args) throws IOException
	{
		final long startTime = System.nanoTime();
		hw1_4 obj = new hw1_4();
		String filename = "input7_1.txt";
		List<String> file = new ArrayList<String>();
		Charset charset = Charset.forName("ISO-8859-1");
		
		
/*		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);

		String algo_type = Files.readAllLines(filename.get(1));
		String wh = Files.readAllLines(Paths.get(filename)).get(2);
		int w = Integer.parseInt(wh.substring(0,1));
		int h = Integer.parseInt(wh.substring(2));
*/
		
		file = Files.readAllLines(Paths.get(filename), charset);
		String algo_type = file.get(0);
		
		String wh = file.get(1);
		String[] whs = wh.split(" ");
		w = Integer.parseInt(whs[0]);
		h = Integer.parseInt(whs[1]);
		
//		System.out.println(algo_type + " " + w + " "+h);
		
		String land = file.get(2);
		String[] lands = land.split(" ");
		lx = Integer.parseInt(lands[0]);
		ly = Integer.parseInt(lands[1]);
		
		maxdif = Integer.parseInt(file.get(3));
		
		no_target = Integer.parseInt(file.get(4));
		int[][] targets = new int[no_target][3];
		int[][] fulltargets = new int[no_target][3];
		k=0;
	/*	for(i=0; i<no_target; i++)
		{
			String t = file.get(5+i);
			String[] a = t.split(" "); 
			
			fulltargets[i][0] = Integer.parseInt(a[0]);
			fulltargets[i][1] = Integer.parseInt(a[1]);
			fulltargets[i][2] = i+1;
				
			if(Integer.parseInt(a[0]) >= w || Integer.parseInt(a[1]) >= h)				
			{
				fulltargets[i][2] = -1;
				continue;
			}
			targets[k][0] = Integer.parseInt(a[0]);
			targets[k][1] = Integer.parseInt(a[1]);
			targets[k][2] = i+1;
			k++;

			for(j=0; j<2; j++)
			{
				if(Integer.parseInt(a[j]) >= w || Integer.parseInt(a[j]) >= h)
					continue;
				targets[i][j] = Integer.parseInt(a[j]);
			}
		
		}
*/
/*		
		for(i = 0; i < no_target; i++ )
		{
			System.out.println(fulltargets[i][0]+" "+fulltargets[i][1]+" "+fulltargets[i][2]);
		}
		System.out.println();

		for(i = 0; i < no_target; i++ )
		{
			System.out.println(targets[i][0]+" "+targets[i][1]+" "+targets[i][2]);
		}
*/

		int[][] elevation = new int[h][w];
		for(i=0; i<h; i++)
		{
			String line = file.get(5+no_target+i);
			String[] a = line.trim().split("\\s+"); 
			for(j=0; j<w; j++)
			{
				elevation[i][j] = Integer.parseInt(a[j]);
				//System.out.println(j);
				//System.out.println(elevation[i][j]);
				//System.out.println();
			}
		}

		if(algo_type.charAt(0) == 'B')
		{
			for(i=0; i<no_target; i++)
		{
			String t = file.get(5+i);
			String[] a = t.split(" "); 
			
			fulltargets[i][0] = Integer.parseInt(a[0]);
			fulltargets[i][1] = Integer.parseInt(a[1]);
			fulltargets[i][2] = i+1;
				
			if(Integer.parseInt(a[0]) >= w || Integer.parseInt(a[1]) >= h)				
			{
				fulltargets[i][2] = -1;
				continue;
			}
			targets[k][0] = Integer.parseInt(a[0]);
			targets[k][1] = Integer.parseInt(a[1]);
			targets[k][2] = i+1;
			k++;
		}
			obj.bfs(targets, elevation);
			print_path(fulltargets);	
		}
		if(algo_type.charAt(0) == 'U')
		{
			for(i=0; i<no_target; i++)
		{
			String t = file.get(5+i);
			String[] a = t.split(" "); 
			
			fulltargets[i][0] = Integer.parseInt(a[0]);
			fulltargets[i][1] = Integer.parseInt(a[1]);
			fulltargets[i][2] = i+1;
				
			if(Integer.parseInt(a[0]) >= w || Integer.parseInt(a[1]) >= h)				
			{
				fulltargets[i][2] = -1;
				continue;
			}
			targets[k][0] = Integer.parseInt(a[0]);
			targets[k][1] = Integer.parseInt(a[1]);
			targets[k][2] = i+1;
			k++;
		}
		obj.ucs(targets, elevation);
		print_path(fulltargets);	
		}
		if(algo_type.charAt(0) == 'A')
		{
			for(i=0; i<no_target; i++)
		{
			String t = file.get(5+i);
			String[] a = t.split(" "); 
			targets[i][2] = i+1;
			for(j=0; j<2; j++)
			{
				targets[i][j] = Integer.parseInt(a[j]);
			}
		}
		obj.astar(targets, elevation);
		print_path(targets);
		}
		//print_path(fulltargets);	
		final long duration = (System.nanoTime() - startTime)/1000000;
		System.out.println("Time taken = "+duration);
	}
}

	
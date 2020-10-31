import java.io.BufferedReader;
import java.io.*;
import java.nio.*;
import java.util.*;
import java.lang.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class homework
{
	static int i,j,k,w,h,lx,ly,maxdif,no_target;
	static HashMap< Integer, String> pathsmap = new HashMap<>();
	//static HashMap< String, String> parentmap = new HashMap<>();

	public static void find_path(int[][][] parent, int tx, int ty, int tc)
	{
		ArrayList<Integer[]> paths = new ArrayList<Integer[]>(); 
		Integer[] seq= new Integer[2]; 
		Integer[] q = new Integer[2];
		int count = 0;
		String path = "";
		q[0] = tx;
		q[1] = ty;
		paths.add(q);
		seq = paths.get(0);
	
		while(parent[tx][ty][0] != -1)
		{
			Integer[] p = new Integer[2];	
			p[0] = parent[tx][ty][0];
			p[1] = parent[tx][ty][1];
			count++; 
			paths.add(p);
			tx = p[0];
			ty = p[1];		
			p=null;


		}
		for(i=paths.size()-1;i>=0;i--)    
		{
			seq = paths.get(i);
		   	for(j=0;j<2;j++)
		   	{
		   		if(j == 0)
		   		{
		   			path = path + seq[j]+",";
		   		}
		   		else
		   		{
		   			path = path + seq[j];	
		   		}
		   	}
		   	if(i != 0)
		   		path = path + " ";
		}
		//System.out.println(count);
		pathsmap.put(tc,path);
		String p = pathsmap.get(tc);

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
		Integer cx,cy,diff,chx,chy,count=0;
		cx = lx;
		cy = ly;
		current[0] = lx;
		current[1] = ly;	

		int[][] visited = new int[h][w];
		Queue<Integer[]> q = new LinkedList<>();

		q.add(current);
		int[][][] parent = new int[w][h][2];
		
		parent[cx][cy][0] = -1;
		parent[cx][cy][1] = -1;

		while (!q.isEmpty())
		{
			if(count == k)
				break;

			current = q.remove();
			cx = current[0];
			cy = current[1];
			
			for(i=0; i<k; i++)
			{
				if (targets[i][0] == cx && targets[i][1] == cy && !pathsmap.containsKey(targets[i][2]))
				{

					find_path(parent, targets[i][0], targets[i][1], targets[i][2]);
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
					}            		
            	}	
            }		
        }
    }	
	

	public static void ucs(int[][] targets, int[][] elevation) throws IOException
	{
		System.out.println("UCS");
 		
 		int[][] distance = new int[h][w];
 		int[][] visited = new int[h][w];
		int[][][] parent = new int[w][h][2];
		ArrayList<Integer[]> q = new ArrayList<Integer[]>();
 		Integer[] current = new Integer[2];	
		Integer cx, cy, chx, chy, diff, xelement, yelement, count = 0;

		current[0] = cx = lx;
		current[1] = cy = ly;
		q.add(current);
		parent[cx][cy][0] = -1;
		parent[cx][cy][1] = -1;
		
		while (!q.isEmpty())
		{
			if(count == k)
				break;
	        current = q.remove(0);
			cx = current[0];
			cy = current[1];
	
			for(i=0; i<k; i++)
			{
				if (targets[i][0] == cx && targets[i][1] == cy && !pathsmap.containsKey(targets[i][2]))
				{
					find_path(parent, targets[i][0], targets[i][1], targets[i][2]);
					count++;
					System.out.println("Target cost= "+distance[cy][cx]);

				}				
			}

			visited[cy][cx] = 1;
			int[][] directions = new int[][]{{-1,0},  {0,1}, {0,-1}, {1,0}, {1,-1}, {-1,-1},  {-1,1}, {1,1}};

				
			for(int[] direction : directions)
			{
				chx = cx + direction[0];
				chy = cy + direction[1];
				if(chx >=0 && chx < w && chy >= 0 && chy < h && (chx!=parent[cx][cy][0] || chy!=parent[cx][cy][1]))
            	{
            		diff = Math.abs(elevation[cy][cx] - elevation[chy][chx]);
					if(diff <= maxdif)
					{
	        			Integer[] child = new Integer[2];
						child[0] = chx;
						child[1] = chy;
						int dist, position, x, y;

						if(Math.abs(direction[0]) == Math.abs(direction[1]))
							dist = distance[cy][cx] + 14;
						else
							dist = distance[cy][cx] + 10;
						
						if(q.isEmpty() && distance[chy][chx] <= 0)
						{
							distance[chy][chx] = dist;
							parent[chx][chy][0] = cx;
							parent[chx][chy][1] = cy;
							q.add(child);
						}	
							
						else
						{
							int contains = 0;
							for(i = 0; i < q.size(); i++)
							{
								if((q.get(i)[0] - child[0] ==0) && (q.get(i)[1] - child[1]==0))
								{
									contains = 1;	
									break;
								}
							}
							if(contains == 1)
							{
								position = i;
								if(dist < distance[chy][chx])
								{
									q.remove(position);
									parent[chx][chy][0] = cx;
									parent[chx][chy][1] = cy;
									for (i=0; i<q.size();i++)
		 							{
										xelement = q.get(i)[0];
										yelement = q.get(i)[1];
	
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
								if(dist < distance[chy][chx] || visited[chy][chx] == 0)
								{
									
									visited[chy][chx] = 0;
									parent[chx][chy][0] = cx;
									parent[chx][chy][1] = cy;

									for (i=0; i<q.size();i++)
		 							{
										xelement = q.get(i)[0];
										yelement = q.get(i)[1];
	
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
	}
	public static void astar(int[][] targets, int[][] elevation) throws IOException
	{
		final long startTime = System.nanoTime();
		System.out.println("A*");

		
		ArrayList<Integer[]> q = new ArrayList<Integer[]>();
 		Integer[] current = new Integer[2];	
		Integer cx, cy, chx, chy, diff, xelement, yelement, count = 0, diffx, diffy;
		for (int tc = 0; tc < k; tc++)
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
	/*		if(tx >= w || ty >= h)
				continue;
	*/
			while(!q.isEmpty())
			{
				current = q.remove(0);
				cx = current[0];
				cy = current[1];
				visited[cy][cx] = 1;
				if (tx == cx && ty == cy)
				{
					find_path (parent, tx, ty, targets[tc][2]);
					System.out.println("Target cost = "+distance[ty][tx]);
					break;
				}

				int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
				
				for(int[] direction : directions)
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
							int dist,dist_h, position, x, y;

							diffx = Math.abs(tx - chx);
							diffy = Math.abs(ty - chy);
							int difference = Math.abs(elevation[chy][chx] - elevation[ty][tx]);
							            		
							heuristic[chy][chx] = 14 * Math.min(diffx, diffy) + 10 * (Math.max(diffx,diffy) -  Math.min(diffx, diffy)) + difference; //
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
								
							if(q.isEmpty() && distance[chy][chx] <= 0)
							{
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
									if((q.get(i)[0] - child[0] ==0) && (q.get(i)[1] - child[1]==0))
									{
										contains = 1;	
										break;
									}
								}
								if(contains == 1)
								{
									position = i;
									if(dist_h < distance_h[chy][chx])
									{
										q.remove(position);
										parent[chx][chy][0] = cx;
										parent[chx][chy][1] = cy;
										distance[chy][chx] = dist;
										distance_h[chy][chx] = dist_h;
										
												
										for (i=0; i<q.size();i++)
			 							{
											xelement = q.get(i)[0];
											yelement = q.get(i)[1];
	
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
									if(dist_h < distance_h[chy][chx] || visited[chy][chx] == 0)
									{
										visited[chy][chx] = 0;
										parent[chx][chy][0] = cx;
										parent[chx][chy][1] = cy;
										distance[chy][chx] = dist;
										distance_h[chy][chx] = dist_h;
												
										for (i=0; i<q.size();i++)
			 							{
											xelement = q.get(i)[0];
											yelement = q.get(i)[1];
	
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
		}
	}


	public static void main(String[] args) throws IOException
	{
		final long startTime = System.nanoTime();
		homework obj = new homework();
		String filename = "input_shrey.txt";
		List<String> file = new ArrayList<String>();
		Charset charset = Charset.forName("ISO-8859-1");
		
		file = Files.readAllLines(Paths.get(filename), charset);
		String algo_type = file.get(0);
		
		String wh = file.get(1);
		String[] whs = wh.split(" ");
		w = Integer.parseInt(whs[0]);
		h = Integer.parseInt(whs[1]);
		
		String land = file.get(2);
		String[] lands = land.split(" ");
		lx = Integer.parseInt(lands[0]);
		ly = Integer.parseInt(lands[1]);
		
		maxdif = Integer.parseInt(file.get(3));
		
		no_target = Integer.parseInt(file.get(4));
		int[][] targets = new int[no_target][3];
		int[][] fulltargets = new int[no_target][3];
		k=0;
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

		int[][] elevation = new int[h][w];
		for(i=0; i<h; i++)
		{
			String line = file.get(5+no_target+i);
			String[] a = line.trim().split("\\s+"); 
			for(j=0; j<w; j++)
			{
				elevation[i][j] = Integer.parseInt(a[j]);
			}
		}
		if(algo_type.equals("BFS"))
		{
			obj.bfs(targets, elevation);
		}
		if(algo_type.equals("UCS"))
		{
			obj.ucs(targets, elevation);
		}
		if(algo_type.equals("A*"))
		{
			obj.astar(targets, elevation);
		}
		print_path(fulltargets);	
		final long duration = (System.nanoTime() - startTime)/1000000;
		System.out.println("Time taken = "+duration);
	}
}
package pa2;

import java.io.*;
import java.util.*;


public class Terasort {
	
    public static int Chunckdata(String inputfilename) throws NumberFormatException, InterruptedException, IOException
    {
     
    	   int size_of_file = 0;
       //To input a character that need to be sorted from disk/memory
	       File filename = new File(inputfilename);
	       if(filename.exists())
	          {
	              size_of_file = (int) filename.length();
	          }
	       long start,end,compute_time;
	       start = System.currentTimeMillis();
	       FileReader fread = null;
	       BufferedReader bread = null;
	       double chunk_size = 1000000;
	       int number_of_charperline = 100;
	       fread = new FileReader(inputfilename);  //to read the input file
	       bread = new BufferedReader(fread); //Pass the fileread object to the bufferedread
	       int chunck_data = (int) (size_of_file/(chunk_size*number_of_charperline));  //To calculate the data to be chunked
	       int i = 0;
	       String current_line; 
	       ArrayList<String> line = new ArrayList<String>();
	       if(i > chunk_size)
	         {
	    	   System.out.println("The file cannot be sorted \n");
	         }
		    	if(i < chunk_size)
				{   
		    		 int var = 0;
			         while((current_line = bread.readLine()) != null)
			           { 
			               line.add(current_line);
			              // Mergesort merge = new Mergesort();
			               Merge_sort(line,0,line.size()-1,var );
			               var++;
			               line = new ArrayList<>();
			              // i++;
			           }
			         if (line.size() == chunck_data || line.size() != 0 && line.size() < chunck_data ) {
			        	     //Mergesort merge = new Mergesort();
			                 Merge_sort(line,0,line.size()-1,var );
			                line = new ArrayList<>();
			              //  var++;
			            }
		         }
	        bread.close();
	        end = System.currentTimeMillis();
	        compute_time = end - start;
	        System.out.println("My external sort has taken" + inputfilename  + "ms" + compute_time);
	return 0;
     }
    

     public static void Merge_sort(ArrayList<String> elements, int elements_in_left, int elements_in_right, int var) throws IOException
     {
          
          Collections.sort(elements);
          String temp_file = "/tmp/intermediate" + var + ".txt";
          FileWriter fwrite = new FileWriter(temp_file);
          for(int j=0; j < elements.size(); j++)
          {
        	  fwrite.write(elements.get(j) + "\n"); 
	  }  
          fwrite.close(); 
     }

    // public static void Filenumber(){
     //  file_inc++;
    // }

    /* public static void Threadcount(int number_of_threads, ArrayList<String> line) throws InterruptedException
     {
            Thread[] threadid = new Thread[5];
            ArrayList<String>current_file = null;
            int tid = 0;
            if(tid < number_of_threads)
            {
               current_file = new ArrayList<String>();
               if(current_file == null)
               {
               System.out.println("No file is present");
               }
            }
               int value = 0;
            for(int i = 0;i<=0;i++)
            for (int j = value; j < (line.size()/number_of_threads); j++)
            {
                if(j < line.size())
                {
                  System.out.println("Cannot be merged");
                }
                if (j >= line.size())
                current_file.add(line.get(j));
                j++;
            }
           int file_inc;
			//threadid[tid] = new Mergesort1(current_file, directory,file_inc,current_file.size());
           threadid[tid].start();
            //file_inc++;
           Filenumber();
            for (int i = 0; i < number_of_threads; i++) {
            	threadid[tid].join();
            }
     }*/
     
     public static void files_merge(int noOfFiles,String outputFile) throws Exception
     {       
    	 
             String treekey; String treevalue;
             String current_file;
             ArrayList<String> list = new ArrayList<String>();
    	     FileReader fread = null;
    	     //BufferedWriter[] bwrite = null;
    	     BufferedReader bread[]= null;
    	     TreeMap<String, String> tmap = new TreeMap<String, String>();
    	     int num_file;
    	     for( num_file = 0; num_file < noOfFiles; num_file++)
    	       {
    	          fread = new FileReader("/tmp" + num_file + ".txt");
    	    	  bread[num_file] = new BufferedReader(fread);
    	          if(tmap.size() > 0) 
    	          {
    	        	 while((current_file = bread[num_file].readLine()) != null)
    	        	 {
    	        		 list.add(current_file);
    	        		 treekey = current_file.substring(1,10);
    	        		 treevalue = num_file+ current_file.substring(10);
    	        		 tmap.put(treekey, treevalue);
    	        	 }
    	          }
    	       	}
    	     FileWriter fwrite = new FileWriter(outputFile);
    	     BufferedWriter  bwrite = new BufferedWriter(fwrite);
    	    // while(tmap != null)  
    	     while(!tmap.isEmpty())
    	        {
    	            String keystr = tmap.keySet().iterator().next();
    	            String val = tmap.get(keystr);
    	            StringTokenizer st =new StringTokenizer(tmap.get(keystr));
    	            int filenum = Integer.parseInt(st.nextToken());
    	            tmap.remove(keystr);
    	            bwrite.write(keystr+st.nextToken());
    	            bwrite.write("\n");
    	           
    	            bwrite.flush();
    	            bwrite.close();
    	            keystr = bread[filenum].readLine();
    	            if(keystr == null )
    	            {
    	            	System.out.println("The Tree map has no values \n");
    	            }
    	            if(keystr != null )
    	            {
    	                String key = keystr.substring(0,10);
    	                val = filenum+keystr.substring(10);
    	                tmap.put(key, val);
    	            }
    	         }
     }
     
     public static String filename;
     public static int thread;
     public static String threadValue;
     public String getfilename() {
 		return filename;
 	}
     
     public int getthread() {
 		return thread;
 	}
     
     
     public static void main(String[] args) throws Exception
     {
    	 
    	BufferedReader in_file = new BufferedReader(new InputStreamReader(System.in));
 		System.out.println("Please enter the file to be sorted: ");
 		filename = in_file.readLine();
        String outputfile = "/tmp/output.txt";
          long startTime = System.currentTimeMillis();
          int noOfFiles= Chunckdata(filename);  //Function to divide the files into chunks
          files_merge(noOfFiles,outputfile);    // Merge the temp files into output file from temp dir
          long endTime = System.currentTimeMillis();
          int time = (int) ((double)(endTime-startTime)/1000);
          System.out.println(thread +" The compute time of mysort = " +time+ " seconds");
     }
  }
 






	
	
	

     
     

 	

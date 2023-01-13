/**
@author Hridika Banik <a 
href="mailto:hridika.banik@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version 1.3
@since 1.0
*/

import java.io.*;
import java.util.*;

public class Assign1{
    public static void main(String[] args){

        int arr_size = Integer.parseInt(args[1]); 
        String arr_order = args[0].toLowerCase();
        String arr_algorithm = args[2].toLowerCase(); 
        String outputFile = args[3].toLowerCase();
 
        
        if(!(arr_order.equals("ascending")) && !(arr_order.equals("descending")) && !(arr_order.equals("random"))) {
            System.out.println("The inputted sorting order is invalid");
            System.exit(1);
        }
        if(!(arr_algorithm.equals("insertion")) && !(arr_algorithm.equals("merge")) && !(arr_algorithm.equals("selection")) && !(arr_algorithm.equals("quick"))){
            System.out.println("The inputted sorting algorithm is invalid");
            System.exit(1);
        }
        if(arr_size < 0){
            System.out.println("The inputted array size is invalid");
            System.exit(1);
        }

        OrderofArray(arr_order, arr_size, arr_algorithm, outputFile);
    }


    //This method is used in generating an array in the order that the user wants.
    public static void OrderofArray(String order, int size, String algorithm, String fileName){
        int[] newarray = new int[size];
        if (order.equals("descending")){
            int x = size-1;
            for(int y = 0; x>0; y+=1,x-=1 ){
                newarray[y]=x; 
            }
        }
        else if (order.equals("ascending")){
            for(int p = 0; p < size; p += 1){
               newarray[p]=p; 
            }
        }
        else if (order.equals("random")){
            Random num = new Random();
            newarray = num.ints(0, Integer.MAX_VALUE).limit(size).toArray(); 
            //Upper and lower limit for random numbers to be chosen between is Integer.MAX_VALUE and 0 respectively.  
        }
        else{
            System.out.println("Please enter a valid generation method. The available methods are: ascending, descending, and random.");
            System.exit(0);
        }


        
        if(algorithm.equals("insertion")){
            /*The code for measuring the execution time was sourced from the link below:
            https://stackoverflow.com/questions/3382954/measure-execution-time-for-a-java-method/3383047
            */
            long time;
            long starttime = System.nanoTime();
            insertionSort(order, newarray, algorithm, fileName);
            long endtime = System.nanoTime();
            time = endtime - starttime;
            /*reference: The code for printing out numbers to an external .txt file is sourced from the link below:
         * https://stackoverflow.com/questions/1375217/saving-an-array-to-a-text-file-in-java 
         */
            try {                                            
                PrintWriter newFile = new PrintWriter(fileName);    
                                            
                for (int i=0; i < newarray.length ; i+=1)
                {
                    newFile.println(newarray[i]);
                }
                newFile.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("File does not exist.");
            }
                                            
            System.out.println("The Time taken is: " + time + "E-9 sec, for the " + order + " array of size " + newarray.length +" and " 
            + algorithm + " sorting method.");
        }
        else if(algorithm.equals("merge")){
            long time;
            long starttime = System.nanoTime();
            mergeSort(newarray, 0, (size-1),order, algorithm, fileName);
            /*The code for measuring the execution time was sourced from the link below:
            https://stackoverflow.com/questions/3382954/measure-execution-time-for-a-java-method/3383047
            */
            long endtime = System.nanoTime();
            time = endtime - starttime;
            /*reference: The code for printing out numbers to an external .txt file is sourced from the link below:
            * https://stackoverflow.com/questions/1375217/saving-an-array-to-a-text-file-in-java */
            try {
                PrintWriter newFile = new PrintWriter(fileName);    
        
                for (int i=0; i < newarray.length ; i+=1)
                {
                    newFile.println(newarray[i]);
                }
                newFile.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("File does not exist.");
            }
        
            System.out.println("Time taken is: " + time + "E-9 sec, for the" + order + " array of size " + newarray.length +" and " 
                + algorithm + " sorting method.");

        }
        else if (algorithm.equals("selection")){
            /*The code for measuring the execution time was sourced from the link below:
            https://stackoverflow.com/questions/3382954/measure-execution-time-for-a-java-method/3383047
            */
            long time;                                                    
            long starttime = System.nanoTime();
            selectionSort(order, newarray, algorithm, fileName);
            long endtime = System.nanoTime();
            time = endtime - starttime;
            /*reference: The code for printing out numbers to an external .txt file is sourced from the link below:
            * https://stackoverflow.com/questions/1375217/saving-an-array-to-a-text-file-in-java */
            try {
                PrintWriter newFile = new PrintWriter(fileName);    
        
                for (int i=0; i < newarray.length ; i+=1)
                {
                    newFile.println(newarray[i]);
                }
                newFile.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("File does not exist.");
            }
        
            System.out.println("Time taken is: " + time + "E-9 sec, for the" + order + " array of size " + newarray.length +" and " 
                + algorithm + " sorting method.");
            


        }
        else if (algorithm.equals("quick")){
            long time;
            long starttime = System.nanoTime();
            quickSort(newarray, 0, (size-1),order,algorithm,fileName);
            long endtime = System.nanoTime();
            time = endtime - starttime;
            /*reference: The code for printing out numbers to an external .txt file is sourced from the link below:
            * https://stackoverflow.com/questions/1375217/saving-an-array-to-a-text-file-in-java */
            try {
                PrintWriter newFile = new PrintWriter(fileName);    
        
                for (int i=0; i < newarray.length ; i+=1)
                {
                    newFile.println(newarray[i]);
                }
                newFile.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("File does not exist.");
            }
        
            System.out.println("Time taken is: " + time + "E-9 sec, for the" + order + " array of size " + newarray.length +" and " 
                + algorithm + " sorting method.");

        }
        else{
            System.out.println("Please enter a valid sorting method. The available methods are: insertion, merge, selection and quick sort.");
            System.exit(0);

        }

    }
    
    public static void selectionSort(String order, int[] array, String algorithm, String fileName){
        

        /* This selectionSort code was sourced from the 'LectureNotesJanuary24' file, page-2 from the CPSC-319 W2022 D2L Shell.
         * The link is :  https://d2l.ucalgary.ca/d2l/le/content/426519/viewContent/5151501/View */ 

        for(int p=0; p<array.length-1; p+=1){
            int min = p;
            for (int q=p+1; q<array.length; q+=1){
                if (array[q] < array[min])
                min=q;
            }
            int temp=array[min];
            array[min]=array[p];
            array[p]=temp;
        }
    }

    public static void insertionSort(String order, int[] array, String algorithm, String fileName){

        /* This insertionSort code was sourced from the 'LectureNotesJanuary26' file, page-1 from the CPSC-319 W2022 D2L Shell.
         * The link is :  https://d2l.ucalgary.ca/d2l/le/content/426519/viewContent/5158253/View */

        for (int y = 1, z; y < array.length; y++) {
            int temp = array[y];
            for (z = y; z > 0 && temp < array[z-1]; z--){
                array[z] = array[z-1];
            }
            array[z] = temp;
        }
        
    }
    public static void mergeSort(int[] array, int start, int end, String order, String algorithm, String fileName)
    {
        if(start < end)
        {
            int middle = start + (end-start)/2;
            mergeSort(array, start, middle,order,algorithm,fileName);
            mergeSort(array, middle+1, end,order,algorithm,fileName);
            merge(array,start,middle,end);
        }
    }
    private static void merge(int[] array,int s,int m,int e) 
    /*The code of MergeSort is sourced from the link below:
    https://www.geeksforgeeks.org/merge-sort/
    */
    {
        int n1 = m - s + 1;
        int n2 = e - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = array[s + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[m + 1 + j];
        int i = 0, j = 0;
        int k = s;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            }
            else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int array[], int start, int end,String order, String algorithm, String fileName)
    /*The code of quickSort is sourced from the link below:
    https://www.geeksforgeeks.org/quick-sort/
    */
    {   
        int[] spare = new int[end - start + 1];
        int t = -1;
        spare[++t] = start;
        spare[++t] = end;
        while (t >= 0) 
        {
            end = spare[t--];
            start = spare[t--];
            int pivot = Parser(array, start, end);
            if (pivot - 1 > start) 
            {
                spare[++t] = start;
                spare[++t] = pivot - 1;
            }
            if (pivot + 1 < end) 
            {
                spare[++t] = pivot + 1;
                spare[++t] = end;
            }}
        
    }
    
    private static int Parser(int array[], int low, int high) 
    {/*The code of Parser is sourced from the link below:
    https://www.geeksforgeeks.org/quick-sort/
    */
        int pivot = array[high];
        int small = (low - 1); 
        for (int l = low; l <= high - 1; l++) {
            if (array[l] <= pivot) {
                small= small + 1;
                int a = array[small];
                array[small] = array[l];
                array[l] = a;
            }
        }
        int a = array[small + 1];
        array[small + 1] = array[high];
        array[high] = a;
        return small + 1;
    }
    
}



    
    

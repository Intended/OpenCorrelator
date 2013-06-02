/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datacorrelator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Alan
 */
public class DataSet {
    
    public ArrayList<Float> data;
    public ArrayList<Integer> year;
    public String dataName;
    
    public DataSet(){
        data = new ArrayList<Float>();
        year = new ArrayList<Integer>();
    }
    
    public void addDataPoint(int Year, float Datapoint){
        year.add(new Integer(Year));
        data.add(new Float(Datapoint));
    }
    
    public void populate(String file_path) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(file_path));
        ArrayList<String> input = new ArrayList<String>();
        while(scanner.hasNext()){
            input.add(scanner.nextLine());
        }
        
        dataName = input.get(0).split(",")[1];
        for(int i = 1; i < input.size(); i++){
            String[] strs = input.get(i).split(",");
            int vara = Integer.valueOf(strs[0]);
            float varb = Float.parseFloat(strs[1]);
            this.addDataPoint(vara,varb);
        }
    }
    
    /////
    /////
    /////

    public static int sets_remaining_in_file = 0;
    private static String current_file_path = "";
    private static int column_index = 0;

    public static void readFile(String file_path) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(file_path));
        current_file_path = file_path;
        String input = "";
        if(scanner.hasNext())
            input = scanner.nextLine();
        String[] setlabels = input.split(",");
        sets_remaining_in_file = setlabels.length - 1;
        column_index = 0;
    }

    public static void importData(ArrayList<DataSet> datasets) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(current_file_path));
        sets_remaining_in_file--;
        column_index++;

        ArrayList<String> input = new ArrayList<>();
        while(scanner.hasNext())
            input.add(scanner.nextLine());

        DataSet nData = new DataSet();
        nData.dataName = (input.get(0).split(","))[column_index];
        
        int vara;
        float varb;
        for(int i = 1; i < input.size(); i++){
            String[] temp = input.get(i).split(",");
           
            if(temp.length > column_index){
                if(temp[0] != "" && temp[column_index] != ""){
                    vara = Integer.valueOf(temp[0]);
                    varb = Float.parseFloat(temp[column_index]);
                    nData.addDataPoint(vara,varb);
                }
            }
                
        }

        datasets.add(nData);
    }
}

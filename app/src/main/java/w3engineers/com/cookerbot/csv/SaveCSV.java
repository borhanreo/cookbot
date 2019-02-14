package w3engineers.com.cookerbot.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Borhan Uddin on 4/7/2017.
 */

public class SaveCSV {
    File file;
    public SaveCSV(File file){
        this.file = file;
    }
    public void save(List<String[]> list){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to create File " + e);
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            for(int i = 0; i < list.size(); i++){
                String[] row = list.get(i);
                for(int j = 0; j < row.length; j++)
                {
                    writer.write(row[j]);
                    if(j != (row.length - 1)){
                        writer.write(',');
                    }
                    else{
                        writer.write('\n');
                    }
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to File " + e);
        }
    }

    public void firstTimeWrite(ArrayList<String> arrayList){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to create File " + e);
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            for(int i = 0; i < arrayList.size(); i++){
                writer.write(arrayList.get(i));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to File " + e);
        }
    }
}

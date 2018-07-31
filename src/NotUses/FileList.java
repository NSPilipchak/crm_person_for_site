package NotUses;

import java.io.File;

/**
 * Created by hammer on 04.07.2017.
 */
public class FileList {
    public static void main (String args[]){
        String folder = "537";
        String list[] = new File("D://docs" + "/" + folder).list();
        for(int i = 0 ; i < list.length ; i++){
            System.out.println(list[i]);
        }

    }
}

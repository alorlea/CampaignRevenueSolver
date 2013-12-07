/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import optimizer.utils.DataReader;

/**
 *
 * @author Alberto Lorente Leal, <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class CampaignOptimizer {

    public static void main(String[] args) {
        File input = new File(args[0]);
        try {
            DataReader reader = new DataReader(new FileInputStream(input));
            
        } catch (FileNotFoundException e) {
            System.out.println("Error, the file could not be found");
            System.exit(1);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author pongs
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableHeaderBackground {
   public static void main(String[] args) {
      Integer[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
      String[] cols = {"A", "B", "C"};

      JTable table = new JTable(data, cols);

      JTableHeader header = table.getTableHeader();
      header.setBackground(new Color(180, 205, 230));
      header.setForeground(Color.yellow);

      JOptionPane.showMessageDialog(null, new JScrollPane(table));
   }
}
package Utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class FormatoTabla implements TableCellRenderer{
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	Font normal = new Font( "Arial",Font.PLAIN,14 );
    Font negrilla = new Font( "Helvetica",Font.BOLD,14 );
    Font cursiva = new Font( "Times new roman",Font.ITALIC,14 );


	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
	        table, value, isSelected, hasFocus, row, column);
	    Color foreground, background;
	    renderer.setEnabled(table == null || table.isEnabled()); 	 
	    ((JLabel) renderer). setHorizontalAlignment(2);
	    table.setFont(cursiva);
	    if (isSelected) {
	      foreground = Color.WHITE;
	      background = Color.darkGray;
	    } else {
	      if (row % 2 == 0) {
	        foreground = Color.BLACK;
	        background = Color.ORANGE;
	      } else {
	        foreground = Color.BLACK;
	        background = Color.WHITE;
	      }
	    }
	    renderer.setForeground(foreground);
	    renderer.setBackground(background);
	    return renderer;
	  }
	

}

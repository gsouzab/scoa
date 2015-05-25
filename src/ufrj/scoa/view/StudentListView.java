package ufrj.scoa.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class StudentListView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private static final int ESPACAMENTO_CELULA = 30;
	
	DefaultTableModel studentModel;

	/**
	 * Create the panel.
	 */
	public StudentListView() {
		setLayout(null);
		
		studentModel = new DefaultTableModel() {
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		
		studentModel.addColumn("Nome"); 
		studentModel.addColumn("CPF"); 
		studentModel.addColumn("Curso");
		studentModel.addColumn("Email");
		
		table = new JTable(studentModel);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(67, 164, 494, 249);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 90, 600, 350);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		

		add(scrollPane);
		
		JLabel lblAlunosCadastrados = new JLabel("ALUNOS CADASTRADOS");
		lblAlunosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlunosCadastrados.setFont(new Font("Arial", Font.BOLD, 15));
		lblAlunosCadastrados.setBounds(61, 48, 508, 37);
		add(lblAlunosCadastrados);

	}

	public DefaultTableModel getStudentModel() {
		return this.studentModel;
	}
	
	public void resizeColumnWidth(JTable table) {
		for (int column = 0; column < table.getColumnCount(); column++)
		{
		    TableColumn tableColumn = table.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		 
		    for (int row = 0; row < table.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		        Component c = table.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);
		 
		        //  We've exceeded the maximum width, no need to check other rows
		 
		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		 
		    tableColumn.setPreferredWidth(preferredWidth + ESPACAMENTO_CELULA);
		}
	}

	public JTable getTable() {
		return table;
	}
}

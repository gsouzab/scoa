package ufrj.scoa.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ufrj.scoa.model.VO.Course;
import ufrj.scoa.model.VO.Student;
import javax.swing.JList;

public class StudentListView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ESPACAMENTO_CELULA = 30;
	
	private DefaultListModel<Student> model;

	/**
	 * Create the panel.
	 */
	public StudentListView() {
		setLayout(null);
		
		model = new DefaultListModel<Student>();
		JList<Student> list = new JList<Student>(model);
		
		JLabel lblAlunosCadastrados = new JLabel("Resultado da busca por alunos");
		lblAlunosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlunosCadastrados.setFont(new Font("Arial", Font.BOLD, 15));
		lblAlunosCadastrados.setBounds(61, 48, 508, 37);
		add(lblAlunosCadastrados);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(20, 90, 600, 350);
		add(scrollPane);

	}

	public DefaultListModel<Student> getModel() {
		return model;
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
}

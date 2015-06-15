package ufrj.scoa.view.room;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ufrj.scoa.model.VO.Room;
import javax.swing.JButton;

public class RoomListView extends JPanel {


	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private DefaultListModel<Room> model;
	private JButton btnExcluir;
	private JList<Room> list;
	
	
	
	public RoomListView() {
		setLayout(null);
		
		model = new DefaultListModel<Room>();
		list = new JList<Room>(model);
		
		JLabel lblCursos = new JLabel("Resultado da busca por salas");
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setFont(new Font("Arial", Font.BOLD, 15));
		lblCursos.setBounds(60, 12, 508, 37);
		add(lblCursos);

		JScrollPane scrollPane;scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 90, 616, 235);
		add(scrollPane);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(12, 337, 103, 25);
		add(btnExcluir);
		
	}

	public DefaultListModel<Room> getModel() {
		return model;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	public JList<Room> getList() {
		return list;
	}

	public void setModel(DefaultListModel<Room> model) {
		this.model = model;
	}

	public void setList(JList<Room> list) {
		this.list = list;
	}
	
	public void resetList() {
		this.model.removeAllElements();
	}
	
	
}

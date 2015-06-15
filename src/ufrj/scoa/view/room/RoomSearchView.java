package ufrj.scoa.view.room;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class RoomSearchView extends JPanel {
    

	private static final long serialVersionUID = 1L;
    private JTextField tfBuilding;
    private JTextField tfNumber;
    private JTextField tfFloor;
    private JButton btnBuscar;
    private JButton btnVoltar;
    
    /**
     * Create the frame.
     */
    
    public RoomSearchView() {
        
        tfBuilding = new JTextField();
        tfBuilding.setFont(new Font("Arial", Font.PLAIN, 13));
        tfBuilding.setBounds(139, 25, 483, 28);
        tfBuilding.setColumns(10);
        
        JLabel lblBuilding = new JLabel("Prédio");
        lblBuilding.setFont(new Font("Arial", Font.BOLD, 12));
        lblBuilding.setBounds(10, 31, 119, 15);
        
        JLabel lblNumber = new JLabel("Número");
        lblNumber.setFont(new Font("Arial", Font.BOLD, 12));
        lblNumber.setBounds(10, 70, 119, 15);
        
        JLabel lblFloor = new JLabel("Andar");
        lblFloor.setFont(new Font("Arial", Font.BOLD, 12));
        lblFloor.setBounds(10, 113, 119, 15);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));
        btnBuscar.setBounds(400, 420, 110, 29);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVoltar.setBounds(520, 420, 110, 29);
        setLayout(null);
        add(tfBuilding);
        
        tfFloor = new JTextField();
        tfFloor.setBounds(139, 105, 119, 28);
        add(tfFloor);
        tfFloor.setColumns(10);
        
        tfNumber = new JTextField();
        tfNumber.setFont(new Font("Arial", Font.PLAIN, 13));
        tfNumber.setBounds(139, 64, 119, 28);
        tfNumber.setColumns(10);
        add(tfNumber);
        add(lblBuilding);
        add(lblNumber);
        add(lblFloor);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 397, 649, 12);
        add(separator);
        add(btnBuscar);
        add(btnVoltar);
    }

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	public JTextField getTfBuilding() {
		return tfBuilding;
	}

	public JTextField getTfNumber() {
		return tfNumber;
	}

	public JTextField getTfFloor() {
		return tfFloor;
	}
	
	public String getWindowTitle() {
		return "Buscar Salas";
	}

	
}

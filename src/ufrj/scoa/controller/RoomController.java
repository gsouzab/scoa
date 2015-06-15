package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.RoomDAO;
import ufrj.scoa.model.VO.Room;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.room.RoomCreationView;
import ufrj.scoa.view.room.RoomListView;
import ufrj.scoa.view.room.RoomSearchView;

public class RoomController implements ActionListener {
	
	private ScoaBaseController baseController;
	private RoomCreationView roomCreationView;
	private RoomListView roomListView;
	private RoomSearchView roomSearchView;
	
	public RoomController(ScoaBaseController baseController) {
		this.baseController = baseController;
		
		this.roomCreationView = new RoomCreationView();
		this.roomCreationView.getBtnSalvar().addActionListener(this);
		this.roomCreationView.getBtnCancelar().addActionListener(this);
		
		this.roomSearchView = new RoomSearchView();
		this.roomSearchView.getBtnBuscar().addActionListener(this);
		this.roomSearchView.getBtnVoltar().addActionListener(this);		
		
		this.roomListView = new RoomListView();
		this.roomListView.getBtnExcluir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == this.roomCreationView.getBtnSalvar()) {
			saveRoom();
			
		} else if(event.getSource() == this.roomCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		} else if(event.getSource() == this.roomSearchView.getBtnBuscar()) {
			searchRooms();
			this.baseController.getBaseFrame().changePanel(roomListView, "Resultado da busca por salas");
			
		} else if(event.getSource() == this.roomSearchView.getBtnVoltar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem vindo ao SCOA");
			
		} else if(event.getSource() == this.roomListView.getBtnExcluir()) {
			int option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o curso selecionado?", "Excluir curso", JOptionPane.YES_NO_OPTION);

			if(option == 0) {
				deleteRoom(roomListView.getList().getSelectedValue());
			}
			
		}
	}
	
	private void saveRoom() {
		String building = this.roomCreationView.getTfBuilding().getText();
		int number = Integer.valueOf(this.roomCreationView.getTfNumber().getText());
		int floor = Integer.valueOf(this.roomCreationView.getTfFloor().getText());
		
		if(this.validateCreateFields(building,floor,number)) {
			Room newRoom = new Room(building,number,floor);
			RoomDAO roomDAO = new RoomDAO();
			roomDAO.save(newRoom);
			
			JOptionPane.showMessageDialog(null, "Sala salva com sucesso");
			clearFieldsCreationView();
			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	

	private void searchRooms() {
		String building = this.roomSearchView.getTfBuilding().getText();
		int number = this.roomSearchView.getTfNumber().getText().length() > 0 ? Integer.valueOf(this.roomSearchView.getTfNumber().getText()) : 0;
		int floor = this.roomSearchView.getTfFloor().getText().length() > 0 ? Integer.valueOf(this.roomSearchView.getTfFloor().getText()) : 0;
		
		RoomDAO roomDAO = new RoomDAO();
		ArrayList<Room> roomList = roomDAO.search(building, number, floor);
		
		this.roomListView.setList(roomList);
		
	}
	
	private void deleteRoom(Room room) {
		int roomId = room.getId();
		
		RoomDAO roomDAO = new RoomDAO();
		roomDAO.delete(roomId);
		
		searchRooms();
		
	}
	
	private boolean validateCreateFields(String building, int floor, int number) {
		return building.length() > 0 && floor > 0 && number > 0;
	}
	
	private void clearFieldsCreationView() {
		this.roomCreationView.getTfBuilding().setText("");;
		this.roomCreationView.getTfFloor().setText("");
		this.roomCreationView.getTfNumber().setText("");
	}
	
	public RoomCreationView getRoomCreationView() {
		return roomCreationView;
	}

	public RoomListView getCoursesListView() {
		return roomListView;
	}
	
	
	public RoomSearchView getRoomSearchView() {
		return roomSearchView;
	}

}

package ufrj.scoa.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ufrj.scoa.model.DAO.ClassDAO;
import ufrj.scoa.model.DAO.StudentDAO;
import ufrj.scoa.model.DAO.StudentDisciplineDAO;
import ufrj.scoa.model.VO.Student;
import ufrj.scoa.model.VO.StudentDiscipline;
import ufrj.scoa.util.Constants;
import ufrj.scoa.view.WelcomeView;
import ufrj.scoa.view.classes.StudentDisciplineCreationView;
import ufrj.scoa.model.VO.Class;

public class StudentDisciplineController implements ActionListener {

	private StudentDisciplineCreationView studentDisciplineCreationView;
	private ScoaBaseController baseController;
	private ClassDAO classDAO = new ClassDAO();
	private StudentDAO studentDAO = new StudentDAO();
		

	public StudentDisciplineController(ScoaBaseController baseController) {

		this.baseController = baseController;
		this.studentDisciplineCreationView = new StudentDisciplineCreationView(studentDAO.searchStudent("","","","","",""),classDAO.listActiveClasses(),baseController.getCurrentUser());
		this.studentDisciplineCreationView.getBtnSalvar().addActionListener(this);
		this.studentDisciplineCreationView.getBtnCancelar().addActionListener(this);
		

	}

	public StudentDisciplineCreationView getStudentDisciplineCreationView() {
		return studentDisciplineCreationView;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.studentDisciplineCreationView.getBtnSalvar()) {
			save();
			
		} else if(event.getSource() == this.studentDisciplineCreationView.getBtnCancelar()) {
			this.baseController.getBaseFrame().changePanel(new WelcomeView(), "Bem-vindo ao SCOA");
			
		}
		
	}

	private void save() {
		Class selectedClass = (Class) this.studentDisciplineCreationView.getSelectedClassComboBox().getSelectedItem();
		Student selectedStudent = (Student) this.studentDisciplineCreationView.getSelectedStudentComboBox().getSelectedItem();
		
		if(validateCreateFields(selectedClass, selectedStudent)) {
			StudentDiscipline newStudentDiscipline = new StudentDiscipline(selectedStudent ,selectedClass);
			
			if(StudentDisciplineDAO.jaInscrito(newStudentDiscipline)) {
				JOptionPane.showMessageDialog(null, "Já existe uma requisição de inscrição nessa matéria.");
				
			} else if(StudentDisciplineDAO.notasLiberadas(selectedClass.getId())){
				JOptionPane.showMessageDialog(null, "Não é possível se inscrever nessa matéria pois as notas finais já foram liberadas!");
			}
			else {
				
				if(baseController.getCurrentUser().getRole() == Constants.ROLE_STUDENT) {
					StudentDisciplineDAO.save(newStudentDiscipline, baseController.getCurrentUser().getRole());
					JOptionPane.showMessageDialog(null, "Inscrição requisitada com sucesso. Acompanhe o pedido através do menu gerenciar inscrições.");
				}
				else {
					StudentDisciplineDAO.save(newStudentDiscipline, baseController.getCurrentUser().getRole());
					JOptionPane.showMessageDialog(null, "Aluno inscrito com sucesso!");
				}
			}
			
			
			

			
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos marcados com (*)");
		}
	}
	
	private boolean validateCreateFields(Class selectedClass, Student selectedStudent) {
		
		return selectedClass != null && selectedStudent != null;
	}
	

	
}

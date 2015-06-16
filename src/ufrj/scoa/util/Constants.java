package ufrj.scoa.util;

public final class Constants {
	
	public static final int ROLE_ADMINISTRATOR = 1;
	public static final int ROLE_SECRETARY = 2;
	public static final int ROLE_PROFESSOR = 3;
	public static final int ROLE_STUDENT = 4;
	
	public static final String STRING_ADMINISTRATOR = "Administrador";
	public static final String STRING_SECRETARY = "Secretário(a)";
	public static final String STRING_PROFESSOR = "Professor(a)";
	public static final String STRING_STUDENT = "Aluno(a)";

	//constantes para o estado do pedido de inscrição em disciplinas
	public static final int STUDENT_CLASS_PENDENT = 0;
	public static final int STUDENT_CLASS_APPROVED = 1;
	public static final int STUDENT_CLASS_DENIED = 2;
	
	public static final String[] STUDENT_CLASS_STRING = {"Pendente","Aprovado", "Negado"};
}

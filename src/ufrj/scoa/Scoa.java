package ufrj.scoa;

import ufrj.scoa.controller.ScoaBaseController;
import ufrj.scoa.view.ScoaBaseFrame;
import ufrj.scoa.view.WelcomeView;

public class Scoa {

	public static void main(String[] args) {
		ScoaBaseFrame baseFrame = new ScoaBaseFrame();
		ScoaBaseController baseController = new ScoaBaseController(baseFrame);
		
		baseFrame.changePanel(new WelcomeView(), "Bem vindo ao SCOA");
		
		
		

	}

}

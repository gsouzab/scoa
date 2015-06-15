package ufrj.scoa;

import ufrj.scoa.controller.AccessController;
import ufrj.scoa.controller.ScoaBaseController;
import ufrj.scoa.view.WelcomeView;

public class Scoa {

	public static void main(String[] args) {
		ScoaBaseController baseController = new ScoaBaseController();
		
		AccessController accessController = new AccessController(baseController);
		
		baseController.getBaseFrame().changePanel(new WelcomeView(), "Login - SCOA");

	}

}

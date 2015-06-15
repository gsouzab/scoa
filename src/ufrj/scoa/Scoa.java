package ufrj.scoa;

import ufrj.scoa.controller.AccessController;
import ufrj.scoa.controller.ScoaBaseController;

public class Scoa {

	public static void main(String[] args) {
				
		
		ScoaBaseController baseController = new ScoaBaseController();
		
		AccessController accessController = new AccessController(baseController);
		
		baseController.getBaseFrame().changePanel(accessController.getLoginView(), "Login - SCOA", false);

	}

}

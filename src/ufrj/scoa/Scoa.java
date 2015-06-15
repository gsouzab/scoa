package ufrj.scoa;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import ufrj.scoa.controller.AccessController;
import ufrj.scoa.controller.ScoaBaseController;
import ufrj.scoa.view.WelcomeView;

public class Scoa {

	public static void main(String[] args) {
				
		
		ScoaBaseController baseController = new ScoaBaseController();
		
		AccessController accessController = new AccessController(baseController);
		
		baseController.getBaseFrame().changePanel(accessController.getLoginView(), "Login - SCOA", false);

	}

}

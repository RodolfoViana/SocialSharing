package Classes;
import java.util.ArrayList;
import java.util.List;



import easyaccept.EasyAcceptFacade;


public class TestandoEasyAceppt {
	
	public static void main(String[] args) {		
		 List<String> files = new ArrayList<String>();
		 files.add("US01.txt");
		 files.add("US02.txt");
		 files.add("US03.txt");
		 files.add("US04.txt");
		 files.add("US05.txt");
		 files.add("US06.txt");
         files.add("US07.txt");
         files.add("US08.txt");
         files.add("US09.txt");
         files.add("US10.txt");
         files.add("US11.txt");
         files.add("US12.txt");
         files.add("US13.txt");
         files.add("US14.txt");
         files.add("US15.txt");
         files.add("US16.txt");
         files.add("US17_18.txt");
         files.add("US19.txt");
//         files.add("US20.txt");
         Fachada rede  = new Fachada();
         
    
         
         EasyAcceptFacade eaFacade = new EasyAcceptFacade(rede, files);
         eaFacade.executeTests();
         System.out.println(eaFacade.getCompleteResults());
	}
}

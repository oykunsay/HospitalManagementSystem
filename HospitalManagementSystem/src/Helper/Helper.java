package Helper;

import javax.swing.JOptionPane;

public class Helper {
	public static void showMsg(String str) {
		String msg;
		
		switch(str) {
		case "fill":
			msg = "Please fill all the blanks!";
			break;
		case "success":
			msg = "Operation is done!";
			break;
		case "error":
			msg = "A problem occured.";
			break;
		default:
			msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		String msg;
		switch(str){
		case "sure":
			msg = "Do you want to continue?";
			break;
		default:
			msg = str;
			break;
		}
		
		int res = JOptionPane.showConfirmDialog(null, msg,"ATTENTION!", JOptionPane.YES_NO_OPTION);
		if(res == 0) {
			return true;
		}else {
			return false;
		}
		}
}

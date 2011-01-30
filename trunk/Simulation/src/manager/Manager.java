package manager;

import view.MainWindow;

public class Manager {

	public static MainWindow window;
	public static Model model;

	public static void main(String[] args) {
		window = MainWindow.getInstance();
		window.init();
		
		model = Model.getInstance();
		model.init();
	}
}

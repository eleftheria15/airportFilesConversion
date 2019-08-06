package Threads;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

import files.Work;


public class excelThread extends Thread {

	private JPanel panel;
	private JProgressBar progressBar;
	private JTextPane leTextPane;
	private JLabel labelProgressBar;
	private File file;
	private JButton button;

	public excelThread(JPanel lePanel, JProgressBar leProgressBar, JTextPane leTextPane, JLabel leProgressLbl,
			File leFile,JButton leBtn) {
		super();
		this.panel = lePanel;
		this.progressBar = leProgressBar;
		this.leTextPane = leTextPane;
		this.labelProgressBar = leProgressLbl;
		this.file = leFile;
		this.button=leBtn;
	}

	@Override
	public void run() {
		button.setEnabled(false);
		Work mshsbroc = new Work();
		labelProgressBar.setText("Reading excel file...");
		String now = mshsbroc.validateAndFillData(file.getPath());
		if (now.equals("true")) {
			progressBar.setValue(40);
			leTextPane.setText("Successfully imported data from excel file!");
		} else {
			// output errors
			leTextPane.setText("Errors:\n" + now);
			labelProgressBar.setText("Failed to read excel file!");
			JOptionPane.showMessageDialog(panel, "Failed to import from file due to reading errors",
					"Import failure!", JOptionPane.ERROR_MESSAGE);
			progressBar.setValue(0);
			button.setEnabled(true);
			return;
		}

		labelProgressBar.setText("Formating Database Table Records...");
		progressBar.setValue(50);

//		if (!formatMsg.equalsIgnoreCase("Successfully deleted all records from all tables")) {
//			resetDB(mshsbroc);
//			return;
//		}
		labelProgressBar.setText("Inserting Aerodrom...");
		String insertBB = mshsbroc.insertAerodromi();
		leTextPane.setText(leTextPane.getText() + "\n" + insertBB);
		progressBar.setValue(80);

		if (!insertBB.equalsIgnoreCase("Successfully inserted")) {
			resetDB(mshsbroc);
			return;
		}
		labelProgressBar.setText("Inserting Let...");
		String insertB = mshsbroc.insertLet();
		leTextPane.setText(leTextPane.getText() + "\n" + insertB);
		progressBar.setValue(90);

		if (!insertB.equalsIgnoreCase("Successfully inserted")) {
			resetDB(mshsbroc);
			return;
		}

		
		labelProgressBar.setText("Inserting Putnik...");
		String insertD = mshsbroc.insertPutnik();
		leTextPane.setText(leTextPane.getText() + "\n" + insertD);
		progressBar.setValue(70);

		if (!insertD.equalsIgnoreCase("Successfully inserted")) {
			resetDB(mshsbroc);
			return;
		}

		labelProgressBar.setText("Import Complete!");
		leTextPane.setText(leTextPane.getText() + "\n" + "Import Successfull! ");
		progressBar.setValue(100);
		JOptionPane.showMessageDialog(panel, "Success!", "Import Success!", JOptionPane.INFORMATION_MESSAGE);
		button.setEnabled(true);
	}

	public void resetDB(Work m) {

		labelProgressBar.setText("Import Failure!");
		leTextPane.setText(leTextPane.getText() + "\n" + "Import Failed! Database rollback initiated!");
		
		JOptionPane.showMessageDialog(panel, "Failed to import from file due to Database insert errors.",
				"Import failure!", JOptionPane.ERROR_MESSAGE);
		button.setEnabled(true);

	}

}

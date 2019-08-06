package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Threads.excelThread;
import files.Work;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class importExcel extends JFrame {

	private JPanel contentPanel;
	private JLabel backgroundImportExcel;
	private JLabel lblLogo;
	private JButton btnBrowse;
	private JTextField textField;
	private JLabel lblProcess;
	private JProgressBar progressBar;
	private JScrollPane scrollPane;
	private JButton okButton;
	private JFrame frame;
	private JFileChooser fc;
	private static File file;
	private JTextPane txtpnMsg;
	private JButton btnNewButton;
	private String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					importExcel frame = new importExcel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public importExcel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(importExcel.class.getResource("/img/mainFrameIcon.png")));
		setTitle("Import Excel to SQLDatabase");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 505, 528);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane());
		contentPanel.add(getOkButton());
		contentPanel.add(getLblProcess());
		contentPanel.add(getProgressBar());
		contentPanel.add(getTextField());
		contentPanel.add(getLblLogo());
		contentPanel.add(getBtnNewButton());
		JButton btnBrowse_1 = new JButton("Browse");
		btnBrowse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIManager.put("FileChooser.readOnly", Boolean.TRUE);
				JFileChooser chooser = new JFileChooser();

				chooser.setAcceptAllFileFilterUsed(false);

				FileNameExtensionFilter filter = new FileNameExtensionFilter("MS Office Excel 1997-2003 file", "xls");

				chooser.setFileFilter(filter);

				int returnVal = chooser.showOpenDialog(contentPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// Do stuff with chosen file
					file = chooser.getSelectedFile();
					textField.setText(chooser.getSelectedFile().getPath());
					path = chooser.getSelectedFile().getPath();
				}
			}
		});
		btnBrowse_1.setBounds(21, 113, 102, 23);
		contentPanel.add(btnBrowse_1);
		contentPanel.add(getBackgroundImportExcel());
	}

	{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{

			getRootPane().setDefaultButton(okButton);
		}
		{

		}

	}

	private JLabel getBackgroundImportExcel() {
		if (backgroundImportExcel == null) {
			backgroundImportExcel = new JLabel("");
			backgroundImportExcel.setIcon(new ImageIcon(importExcel.class.getResource("/img/background.jpg")));
			backgroundImportExcel.setBounds(0, 0, 489, 489);
		}
		return backgroundImportExcel;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(importExcel.class.getResource("/img/logo_1.png")));
			lblLogo.setBounds(197, -15, 102, 124);
		}
		return lblLogo;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(150, 113, 304, 22);
			textField.setColumns(10);
		}
		return textField;
	}

	private JLabel getLblProcess() {
		if (lblProcess == null) {
			lblProcess = new JLabel("Loading...");
			lblProcess.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			lblProcess.setBounds(21, 147, 241, 23);
		}
		return lblProcess;
	}

	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setBounds(21, 181, 433, 23);
		}
		return progressBar;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 225, 469, 179);
			scrollPane.setViewportView(getTxtpnMsg());
		}
		return scrollPane;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("Import");
			okButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			okButton.setForeground(new Color(0, 0, 0));
			okButton.setBackground(new Color(50, 205, 50));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (textField.getText().toString().equalsIgnoreCase("") || textField.getText() == null) {
						JOptionPane.showMessageDialog(contentPanel, "Please choose a valid Excel file!",
								"No file selected", JOptionPane.WARNING_MESSAGE);

					} else {

						String file = textField.getText().toString();
						if (file.contains(".")) {
							String ext = file.split("\\.")[1];
							if (!ext.equalsIgnoreCase("xls")) {
								JOptionPane.showMessageDialog(contentPanel,
										"Please choose a valid Excel file with .xls extention. \n(.xlsx or other formats are not supported)",
										"Invalid file format", JOptionPane.WARNING_MESSAGE);
							} else {

								excelThread trd = new excelThread((JPanel) getContentPane(), getProgressBar(),
										getTxtpnMsg(), getLblProcess(), importExcel.file, okButton);
								trd.start();

							}
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Please choose a valid Excel file!",
									"Invalid filename", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
			okButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			okButton.setBounds(77, 437, 111, 29);
		}
		return okButton;
	}

	private JTextPane getTxtpnMsg() {
		if (txtpnMsg == null) {
			txtpnMsg = new JTextPane();
			txtpnMsg.setText("Result: Please select excel file!");
			txtpnMsg.setEditable(false);
		}
		return txtpnMsg;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Delete ");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(frame, "Are you sure u want deleta all from database?", "Delete?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						Work m = new Work();
						String delete = m.deleteAllFromDataBase();
						m.deleteAllFromDataBase();
						txtpnMsg.setText(txtpnMsg.getText() + "\n" + delete);
						progressBar.setValue(0);
						

					}

				}
			});
			btnNewButton.setBackground(new Color(255, 0, 0));
			btnNewButton.setForeground(new Color(255, 255, 240));
			btnNewButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			btnNewButton.setBounds(250, 437, 152, 29);
		}
		return btnNewButton;
	}
}

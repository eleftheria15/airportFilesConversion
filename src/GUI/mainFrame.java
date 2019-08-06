package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Threads.PlaneThread;
import database_conn.DataBConn;
import export.XML;
import files.Work;

import lists.Aerodrom;
import lists.Let;
import lists.Putnik;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

public class mainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264964734987504486L;
	private JPanel contentPane;
	private static JPanel animationPanel;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmExcelToSql;
	private JMenuItem ResultToXml;
	private JMenuItem ResultToJSON;
	private JMenuItem ResultToPDF;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JLabel pregledTabelaText;
	private JSeparator separator;
	private JButton btnAerodrom;
	private JButton btnLet;
	private JButton btnPutnik;
	private JLabel lblAnimationPanelBackground;
	private JTextField textFieldSearch;
	private JButton btnNewButton;
	private JComboBox comboBoxSort;
	private JTable table;
	static PlaneThread p;
	private JComboBox<String> comoBoxSort;
	private String currentSql = null;
	private JFrame mainFrame;
	private String currentTable = null;
	private JToggleButton toggleButtonSort;
	private JScrollPane scrollPane;
	private JToggleButton toggleButtonColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
	 */
	public mainFrame() throws InterruptedException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainFrame.class.getResource("/img/mainFrameIcon.png")));
		setTitle("Informacioni sistem - Aerodrom");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 510);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel_1());
		contentPane.add(getLeftPanel());
		contentPane.add(getPanel_1_1());
		contentPane.add(getComboBoxSort());
		contentPane.add(getTextFieldSearch());
		contentPane.add(getBtnSearch());

		mainFrame = this;
		p = new PlaneThread();
		p.setPanel(animationPanel);
		contentPane.add(getToggleButtonSort());
		contentPane.add(getToggleButtonColor());

		p.start();

	}

	private JPanel getPanel_1() {
		if (animationPanel == null) {
			animationPanel = new JPanel();
			animationPanel.setBounds(0, 0, 879, 110);
			animationPanel.setLayout(null);

			JLabel lblCloud_1 = new JLabel("");
			lblCloud_1.setBounds(222, 0, 38, 27);
			animationPanel.add(lblCloud_1);
			lblCloud_1.setIcon(new ImageIcon(mainFrame.class.getResource("/img/cloud_2.png")));

			JLabel lblCloud_2 = new JLabel("");
			lblCloud_2.setIcon(new ImageIcon(mainFrame.class.getResource("/img/cloud_2.png")));
			lblCloud_2.setBounds(290, 11, 38, 27);
			animationPanel.add(lblCloud_2);

			JLabel lblClound_3 = new JLabel("");
			lblClound_3.setIcon(new ImageIcon(mainFrame.class.getResource("/img/cloud_2.png")));
			lblClound_3.setBounds(537, 0, 38, 27);
			animationPanel.add(lblClound_3);

			JLabel lblCloud_4 = new JLabel("");
			lblCloud_4.setIcon(new ImageIcon(mainFrame.class.getResource("/img/cloud_2.png")));
			lblCloud_4.setBounds(586, 11, 38, 27);
			animationPanel.add(lblCloud_4);

			JLabel lblSun = new JLabel("");
			lblSun.setIcon(new ImageIcon(mainFrame.class.getResource("/img/sun_5.png")));
			lblSun.setBounds(823, 0, 46, 46);
			animationPanel.add(lblSun);
			// animationPanel.add(getLblAnimationPanelBackground());
		}
		return animationPanel;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
			menuBar.add(getMnNewMenu_1());
		}
		return menuBar;
	}

	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("Import");
			mnNewMenu.setIcon(null);
			mnNewMenu.add(getMntmExcelToSql());
		}
		return mnNewMenu;
	}

	private JMenu getMnNewMenu_1() {
		if (mnNewMenu_1 == null) {
			mnNewMenu_1 = new JMenu("Export");
			mnNewMenu_1.add(getResultToXml());
			mnNewMenu_1.add(getResultToJSON());
			mnNewMenu_1.add(getResultToPDF());
		}
		return mnNewMenu_1;
	}

	private JMenuItem getMntmExcelToSql() {
		if (mntmExcelToSql == null) {
			mntmExcelToSql = new JMenuItem("Excel to SQL database");
			mntmExcelToSql.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					importExcel dialog = new importExcel();
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
			});
			mntmExcelToSql.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			mntmExcelToSql.setBackground(Color.LIGHT_GRAY);
			mntmExcelToSql.setIcon(new ImageIcon(mainFrame.class.getResource("/img/Excel-to-mysql_4.png")));
		}
		return mntmExcelToSql;
	}

	private JMenuItem getResultToXml() {
		if (ResultToXml == null) {
			ResultToXml = new JMenuItem("Result to XML");
			ResultToXml.setBackground(Color.LIGHT_GRAY);
			ResultToXml.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			ResultToXml.setIcon(new ImageIcon(mainFrame.class.getResource("/img/result_to_Xml.png")));
			ResultToXml.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (currentSql != null) {
						String path;
						JFileChooser c = new JFileChooser();
						// Demonstrate "Save" dialog:
						int rVal = c.showSaveDialog(mainFrame);
						if (rVal == JFileChooser.APPROVE_OPTION) {
							String fileName = (c.getSelectedFile().getName());
							String filePath = (c.getCurrentDirectory().toString());
							path = filePath + "\\" + fileName;
							Work m = new Work();
							List<Aerodrom> aerodromi;
							List<Putnik> putnici;
							List<Let> letovi;

							switch (currentTable) {
							case "aerodrom":
								aerodromi = new ArrayList<>();
								m.getAirport(aerodromi, currentSql);
								if (aerodromi.size() != 0) {
									if (m.exportToXML(aerodromi, path) && XML.airportValidation(path + ".xml")
											.equalsIgnoreCase("Validation successfull")) {
										JOptionPane.showMessageDialog(mainFrame,
												"Export and validation was successfull",
												"Export and Validation Success", JOptionPane.INFORMATION_MESSAGE);

									} else {
										JOptionPane.showMessageDialog(mainFrame, "Validation Failed",
												"Failed to validate", JOptionPane.ERROR_MESSAGE);
									}
									;
								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;
							case "let":
								letovi = new ArrayList<>();
								m.getFligt(letovi, currentSql);
								if (letovi.size() != 0) {
									if (m.exportToXML(letovi, path) && XML.flightValidation(path + ".xml")
											.equalsIgnoreCase("Validation successfull")) {
										JOptionPane.showMessageDialog(mainFrame,
												"Export and validation was successfull",
												"Export and Validation Success", JOptionPane.INFORMATION_MESSAGE);

									} else {
										JOptionPane.showMessageDialog(mainFrame, "Validation Failed",
												"Failed to validate", JOptionPane.ERROR_MESSAGE);
									}

								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;
							case "putnik":
								putnici = new ArrayList<>();
								m.getPutnici(putnici, currentSql);
								if (putnici.size() != 0) {
									if (m.exportToXML(putnici, path) && XML.passangerValidation(path + ".xml")
											.equalsIgnoreCase("Validation successfull")) {
										JOptionPane.showMessageDialog(mainFrame,
												"Export and validation was successfull",
												"Export and Validation Success", JOptionPane.INFORMATION_MESSAGE);

									} else {
										JOptionPane.showMessageDialog(mainFrame, "Validation Failed",
												"Failed to validate", JOptionPane.ERROR_MESSAGE);
									}

								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;

							default:
								break;
							}

						}
						if (rVal == JFileChooser.CANCEL_OPTION) {
							return;
						}

					} else {
						JOptionPane.showMessageDialog(mainFrame, "Please choose results first", "Nothing to export",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		}
		return ResultToXml;
	}

	private JMenuItem getResultToJSON() {
		if (ResultToJSON == null) {
			ResultToJSON = new JMenuItem("Result to JSON");
			ResultToJSON.setBackground(Color.LIGHT_GRAY);
			ResultToJSON.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			ResultToJSON.setIcon(new ImageIcon(mainFrame.class.getResource("/img/result_to_json.png")));
			ResultToJSON.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {

					if (currentSql != null) {

						String path;
						JFileChooser c = new JFileChooser();
						// Demonstrate "Save" dialog:
						int rVal = c.showSaveDialog(mainFrame);
						if (rVal == JFileChooser.APPROVE_OPTION) {
							String fileName = (c.getSelectedFile().getName());
							String filePath = (c.getCurrentDirectory().toString());
							path = filePath + "\\" + fileName;
							Work m = new Work();
							List<Aerodrom> aerodromi;
							List<Putnik> putnici;
							List<Let> letovi;

							switch (currentTable) {
							case "aerodrom":
								aerodromi = new ArrayList<>();
								m.getAirport(aerodromi, currentSql);
								if (aerodromi.size() != 0) {
									if (m.exportToJSON(aerodromi, path)) {
										JOptionPane.showMessageDialog(mainFrame, "Export was successfull",
												"Export Success", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(mainFrame, "Export Failed", "Failed to export",
												JOptionPane.ERROR_MESSAGE);
									}
									;
								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;
							case "let":
								letovi = new ArrayList<>();
								m.getFligt(letovi, currentSql);
								if (letovi.size() != 0) {
									if (m.exportToJSON(letovi, path)) {
										JOptionPane.showMessageDialog(mainFrame, "Export was successfull",
												"Export Success", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(mainFrame, "Export Failed", "Failed to export",
												JOptionPane.ERROR_MESSAGE);
									}
									;
								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;
							case "putnik":
								putnici = new ArrayList<>();
								m.getPutnici(putnici, currentSql);
								if (putnici.size() != 0) {
									if (m.exportToJSON(putnici, path)) {
										JOptionPane.showMessageDialog(mainFrame, "Export was successfull",
												"Export Success", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(mainFrame, "Export Failed", "Failed to export",
												JOptionPane.ERROR_MESSAGE);
									}

								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;

							default:
								break;
							}
						}
						if (rVal == JFileChooser.CANCEL_OPTION) {
							return;
						}

					} else {
						JOptionPane.showMessageDialog(mainFrame, "Please choose results first", "Nothing to export",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		}
		return ResultToJSON;
	}

	private JMenuItem getResultToPDF() {
		if (ResultToPDF == null) {
			ResultToPDF = new JMenuItem("Result to PDF");
			ResultToPDF.setBackground(Color.LIGHT_GRAY);
			ResultToPDF.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			ResultToPDF.setIcon(new ImageIcon(mainFrame.class.getResource("/img/toPDF.png")));
			ResultToPDF.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (currentSql != null) {

						String path;
						JFileChooser c = new JFileChooser();
						// Demonstrate "Save" dialog:
						int rVal = c.showSaveDialog(mainFrame);
						if (rVal == JFileChooser.APPROVE_OPTION) {
							String fileName = (c.getSelectedFile().getName());
							String filePath = (c.getCurrentDirectory().toString());
							path = filePath + "\\" + fileName;
							Work m = new Work();
							List<Aerodrom> aerodromi;
							List<Putnik> putnici;
							List<Let> letovi;

							switch (currentTable) {
							case "aerodrom":
								aerodromi = new ArrayList<>();
								m.getAirport(aerodromi, currentSql);
								if (aerodromi.size() != 0) {
									if (m.exportToPDF(aerodromi, path)) {
										JOptionPane.showMessageDialog(mainFrame, "Export was successfull",
												"Export Success", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(mainFrame, "Export Failed", "Failed to export",
												JOptionPane.ERROR_MESSAGE);
									}
									;
								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;

							case "putnik":
								putnici = new ArrayList<>();
								m.getPutnici(putnici, currentSql);
								if (putnici.size() != 0) {
									if (m.exportToPDF(putnici, path)) {
										JOptionPane.showMessageDialog(mainFrame, "Export was successfull",
												"Export Success", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(mainFrame, "Export Failed", "Failed to export",
												JOptionPane.ERROR_MESSAGE);
									}

								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;
							case "let":
								letovi = new ArrayList<>();
								m.getFligt(letovi, currentSql);
								if (letovi.size() != 0) {
									if (m.exportToPDF(letovi, path)) {
										JOptionPane.showMessageDialog(mainFrame, "Export was successfull",
												"Export Success", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(mainFrame, "Export Failed", "Failed to export",
												JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(mainFrame, "There is no data to export",
											"No data for export", JOptionPane.WARNING_MESSAGE);
								}
								break;
							default:
								break;
							}
						}
						if (rVal == JFileChooser.CANCEL_OPTION) {
							return;
						}
					} else {
						JOptionPane.showMessageDialog(mainFrame, "Please choose results first", "Nothing to export",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			});
		}
		return ResultToPDF;
	}

	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setBackground(UIManager.getColor("Button.background"));
			leftPanel.setBorder(new MatteBorder(4, 1, 1, 2, (Color) new Color(0, 0, 0)));
			leftPanel.setBounds(0, 107, 229, 353);
			leftPanel.setLayout(null);
			leftPanel.add(getPregledTabelaText());
			leftPanel.add(getSeparator());
			leftPanel.add(getBtnAerodrom());
			leftPanel.add(getBtnLet());
			leftPanel.add(getBtnPutnik());

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(45, 297, 134, 2);
			leftPanel.add(separator_1);
		}
		return leftPanel;
	}

	private JPanel getPanel_1_1() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setBounds(227, 139, 652, 335);
			rightPanel.setLayout(null);
			rightPanel.add(getTable());
			rightPanel.add(getScrollPane(), "name_22490641154825");
		}
		return rightPanel;
	}

	private JLabel getPregledTabelaText() {
		if (pregledTabelaText == null) {
			pregledTabelaText = new JLabel("Pregled tabela");
			pregledTabelaText.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			pregledTabelaText.setBounds(58, 55, 131, 32);
		}
		return pregledTabelaText;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(45, 85, 134, 2);
		}
		return separator;
	}

	private JButton getBtnAerodrom() {
		if (btnAerodrom == null) {
			btnAerodrom = new JButton("Aerodrom");
			btnAerodrom.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			btnAerodrom.setBackground(UIManager.getColor("Button.background"));
			btnAerodrom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DataBConn conn = new DataBConn();
					if (conn.open()) {

						currentTable = "aerodrom";
						currentSql = "SELECT * FROM aerodrom";
						Work m = new Work();
						m.fillComboBox(comboBoxSort, currentTable);
						DefaultTableModel model = m.getDTMAirport(currentSql);
						getTable().setModel(model);
					} else {
						JOptionPane.showMessageDialog(mainFrame, "Database error", "Failed connecting to database",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});
			btnAerodrom.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			btnAerodrom.setBounds(58, 120, 108, 32);
		}
		return btnAerodrom;
	}

	private JButton getBtnLet() {
		if (btnLet == null) {
			btnLet = new JButton("Let");
			btnLet.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			btnLet.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			btnLet.setBounds(58, 180, 108, 32);
			btnLet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DataBConn conn = new DataBConn();
					if (conn.open()) {
						currentTable = "let";
						currentSql = "SELECT * FROM let";
						Work m = new Work();
						m.fillComboBox(comboBoxSort, currentTable);
						DefaultTableModel model = m.getDTMFligt(currentSql);
						getTable().setModel(model);
					} else {
						JOptionPane.showMessageDialog(mainFrame, "Database error", "Failed connecting to database",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnLet;
	}

	private JButton getBtnPutnik() {
		if (btnPutnik == null) {
			btnPutnik = new JButton("Putnik");
			btnPutnik.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			btnPutnik.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DataBConn conn = new DataBConn();
					if (conn.open()) {
						currentTable = "putnik";
						currentSql = "SELECT * FROM putnik";
						Work m = new Work();
						m.fillComboBox(comboBoxSort, currentTable);
						DefaultTableModel model = m.getDTMPutnici(currentSql);
						getTable().setModel(model);
					} else {
						JOptionPane.showMessageDialog(mainFrame, "Database error", "Failed connecting to database",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnPutnik.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			btnPutnik.setBounds(58, 237, 108, 32);
		}
		return btnPutnik;
	}

	private JTextField getTextFieldSearch() {
		if (textFieldSearch == null) {
			textFieldSearch = new JTextField();
			textFieldSearch.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
			textFieldSearch.setBorder(new MatteBorder(4, 1, 1, 1, (Color) new Color(0, 0, 0)));
			textFieldSearch.setBounds(472, 107, 349, 31);
			textFieldSearch.setColumns(10);
			textFieldSearch.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						getBtnSearch().doClick();
					}
				}
			});
		}
		return textFieldSearch;
	}

	private JButton getBtnSearch() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setBorder(new MatteBorder(4, 1, 1, 1, (Color) new Color(0, 0, 0)));
			btnNewButton.setBounds(818, 107, 61, 31);
			btnNewButton.setIcon(new ImageIcon(mainFrame.class.getResource("/img/searchIcon.png")));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (currentTable == null) {
						JOptionPane.showMessageDialog(mainFrame, "Please select a table first", "No data",
								JOptionPane.WARNING_MESSAGE);
					} else {

						String col = getComboBoxSort().getSelectedItem().toString();
						String query = getTextFieldSearch().getText();
						String order = getToggleButtonSort().isSelected() ? "DESC" : "ASC";

						currentSql = "select * from `" + currentTable + "` where `" + col + "` like '%" + query
								+ "%' ORDER BY `" + col + "` " + order + "";
						switch (currentTable) {
						case "putnik":
							getTable().setModel(new Work().getDTMPutnici(currentSql));
							break;
						case "aerodrom":
							getTable().setModel(new Work().getDTMAirport(currentSql));
							break;
						case "let":
							getTable().setModel(new Work().getDTMFligt(currentSql));
							break;
						default:
							JOptionPane.showMessageDialog(mainFrame, "Search", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}

					}
				}
			});
		}
		return btnNewButton;
	}

	private JComboBox<String> getComboBoxSort() {
		if (comboBoxSort == null) {
			comboBoxSort = new JComboBox();
			comboBoxSort.setBorder(new MatteBorder(4, 1, 1, 1, (Color) new Color(0, 0, 0)));
			comboBoxSort.setBounds(227, 107, 108, 31);
			comboBoxSort.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						getBtnSearch().doClick();
					}
				}
			});
		}
		return comboBoxSort;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBorder(null);
			table.setFont(new Font("Segoe UI Emoji", Font.BOLD, 11));
			table.setBounds(0, 0, 664, 324);

		}
		return table;
	}

	private JToggleButton getToggleButtonSort() {
		if (toggleButtonSort == null) {
			toggleButtonSort = new JToggleButton("");
			toggleButtonSort.setBorder(new MatteBorder(4, 1, 1, 1, (Color) new Color(0, 0, 0)));
			toggleButtonSort.setBounds(334, 107, 61, 31);
			toggleButtonSort.setSelectedIcon(new ImageIcon(mainFrame.class.getResource("/img/sort_icon.png")));
			toggleButtonSort.setIcon(new ImageIcon(mainFrame.class.getResource("/img/sort_icon_2.png")));
			comboBoxSort.setBorder(new MatteBorder(4, 1, 1, 1, (Color) new Color(0, 0, 0)));
		}
		return toggleButtonSort;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTable());
			scrollPane.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
			scrollPane.setBounds(0, 0, 652, 324);
		}
		return scrollPane;
	}

	private JToggleButton getToggleButtonColor() {
		if (toggleButtonColor == null) {
			toggleButtonColor = new JToggleButton("Color");
			toggleButtonColor.setBackground(UIManager.getColor("Button.background"));
			toggleButtonColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color[] colors = { Color.green, Color.red, Color.BLUE, Color.BLUE, Color.CYAN };
					int random = 0;
					Random r = new Random();
					for (int j = 0; j < colors.length; j++) {
						random = r.nextInt(j + 1);

					}
					if (toggleButtonColor.isSelected()) {
						table.setSelectionBackground(colors[random]);
					} else if (!toggleButtonColor.isSelected()) {
						table.setSelectionBackground(new Color(255, 255, 255));
					}
				}
			});

			toggleButtonColor.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
			toggleButtonColor.setBorder(new MatteBorder(4, 1, 1, 1, (Color) new Color(0, 0, 0)));
			toggleButtonColor.setBounds(394, 107, 79, 31);

		}
		return toggleButtonColor;
	}
}

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Interface extends JFrame {
	
	private String data = "";
	private JPanel contentPane;
	private JTextField search_bar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Ajouter/Modifier");
		btnNewButton.setBounds(0, 233, 695, 23);
		contentPane.add(btnNewButton);
		
		
		
		final JList list = new JList();
		
		
		
		JButton btnNewButton_1 = new JButton("Charger");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data = "";
				JFileChooser fileChooser = new JFileChooser();
				
				int reponse = fileChooser.showOpenDialog(null);
				
				if(reponse == JFileChooser.APPROVE_OPTION) {
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					try {
						Scanner readFile = new Scanner(file, "UTF-8");
						while(readFile.hasNextLine()) {
							data += readFile.nextLine();
							data += "--";
							
						}
						
						
						Fichier fichier = new Fichier(data);
						
						

						final String[] wordTab = new String[fichier.getWords().size()];
						
						for(int i=0;i<fichier.getWords().size();i++) {
							wordTab[i] = fichier.getWords().get(i);
						}
						
						
						
						list.setModel(new AbstractListModel() {
							
							//String[] values = wordTab;
							String[] values = wordTab;
							
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
						
						

						
						readFile.close();
						
						
						
					} catch (FileNotFoundException e1) {
						System.out.print("error" + e1);
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		btnNewButton_1.setBounds(251, 11, 107, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		list.setBounds(520, 45, 165, 177);
		contentPane.add(list);
		
		scrollPane.setViewportView(list);
		scrollPane.setBounds(520, 45, 165, 177);
		contentPane.add(scrollPane);
		
		
		JButton btnNewButton_2 = new JButton("Enregistrer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_2.setBounds(372, 11, 109, 23);
		contentPane.add(btnNewButton_2);
		
		search_bar = new JTextField();
		search_bar.setBounds(5, 45, 228, 38);
		contentPane.add(search_bar);
		search_bar.setColumns(10);
		
		JTextArea def_word = new JTextArea();
		def_word.setBounds(241, 45, 269, 177);
		contentPane.add(def_word);
		
		JTextArea reuslt_word = new JTextArea();
		reuslt_word.setBounds(5, 94, 228, 127);
		contentPane.add(reuslt_word);
	}
}

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Controller.BoardController;

public class SwingView extends JFrame {

	private static final long serialVersionUID = 1L;
	private BoardController controller;
	private JPanel contentPane;
	private JLabel currentPlayerLabel;
	private JLabel adviceLabel;
	private JButton[][] btn;
	
	// allow two-way communication between view and controller
	public void setController(BoardController controller) {
		this.controller = controller;
	}

	// Create the JFrame Window
    public SwingView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 320, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));        					
		contentPane.add(createCurrentPlayerLabel());
		contentPane.add(createTicTacToeBoard(actionListenerButtons())); 
		contentPane.add(createAdviceLabel());
		setContentPane(contentPane);
	}
	
	private JLabel createCurrentPlayerLabel() {
		currentPlayerLabel = new JLabel();
		currentPlayerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		currentPlayerLabel.setText("Waiting for console...");
		return currentPlayerLabel;
	}
	
	private JLabel createAdviceLabel() {
		adviceLabel = new JLabel();
		adviceLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 8));
		adviceLabel.setText("Hint: ");
		return adviceLabel;
	}

	private JPanel createTicTacToeBoard(ActionListener buttonListener) {
		
		btn = new JButton[3][3];
	    JPanel btn_panel = new JPanel();
	    
        btn_panel.setLayout(new GridLayout(3, 3));
        btn_panel.setBackground(new Color(0, 0, 0));
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				
				btn[row][col] = new JButton();
				btn[row][col].setPreferredSize(new Dimension(100, 100));
				btn[row][col].putClientProperty("row", row); // Set row index as client property
				btn[row][col].putClientProperty("col", col); // Set column index as client property
				btn[row][col].addActionListener(buttonListener);
	            btn_panel.add(btn[row][col]);
			}
		}
		return btn_panel;
	}
	
	private ActionListener actionListenerButtons() {
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				JButton clickedButton = (JButton) event.getSource();
	            int row = (int) clickedButton.getClientProperty("row");
	            int col = (int) clickedButton.getClientProperty("col");
	            
	            controller.getUserInput(row, col);	            
	            }
		};
		
		return buttonListener;
	}

	public void printBoard(char[][] board) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				
				btn[row][col].setText(String.valueOf(board[row][col]));
	            if (board[row][col] != ' ') btn[row][col].setEnabled(false);
			}
		}
	}
		
	public void printMessage(String message) {
	    SwingUtilities.invokeLater(() -> { currentPlayerLabel.setText(message); });
	}

	public void printAdvice(String suggestBestMoveToUser) {
		SwingUtilities.invokeLater(() -> { adviceLabel.setText(suggestBestMoveToUser); });
	}

}

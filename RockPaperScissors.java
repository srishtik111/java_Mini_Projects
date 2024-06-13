import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissors extends JFrame implements ActionListener {

    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, playerChoiceLabel, computerChoiceLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0;

    public RockPaperScissors() {
        // Set up the frame
        setTitle("Rock Paper Scissors Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.CYAN); // Set background color to green

        // Create button panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.CYAN);

        // Add buttons
        rockButton = createButton("Rock");
        paperButton = createButton("Paper");
        scissorsButton = createButton("Scissors");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        // Add labels
        resultLabel = new JLabel("Choose Rock, Paper, or Scissors", SwingConstants.CENTER);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 20f));
        playerChoiceLabel = new JLabel("", SwingConstants.CENTER);
        computerChoiceLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: Player 0 - 0 Computer", SwingConstants.CENTER);

        // Set font size for player and computer choice labels
        Font choiceFont = new Font("Arial", Font.BOLD, 25);
        playerChoiceLabel.setFont(choiceFont);
        computerChoiceLabel.setFont(choiceFont);

        // Arrange components in the panel
        panel.add(scoreLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(resultLabel, BorderLayout.CENTER);

        JPanel choicesPanel = new JPanel(new FlowLayout());
        choicesPanel.setBackground(Color.GREEN);
        choicesPanel.add(playerChoiceLabel);
        choicesPanel.add(computerChoiceLabel);

        panel.add(choicesPanel, BorderLayout.NORTH);

        // Add panel to frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(150, 50));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();
        String computerChoice = getComputerChoice();

        playerChoiceLabel.setText("Player Choice: " + playerChoice);
        computerChoiceLabel.setText(", Computer Choice: " + computerChoice);

        String result = determineWinner(playerChoice, computerChoice);
        resultLabel.setText(result);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 29f));
        updateScore(result);
        scoreLabel.setText("Score: Player " + playerScore + " - " + computerScore + " Computer");
    }

    private String getComputerChoice() {
        String[] choices = { "Rock", "Paper", "Scissors" };
        Random random = new Random();
        int index = random.nextInt(choices.length);
        return choices[index];
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a Tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "You Win!";
        } else {
            return "You Lose!";
        }
    }

    private void updateScore(String result) {
        if (result.equals("You Win!")) {
            playerScore++;
        } else if (result.equals("You Lose!")) {
            computerScore++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissors());
    }
}

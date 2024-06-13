import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGame extends JFrame implements ActionListener {

    private JLabel questionLabel, scoreLabel;
    private JRadioButton option1, option2, option3, option4;
    private JButton nextButton;
    private ButtonGroup optionsGroup;
    private int currentQuestionIndex = 0, score = 0;

    private String[] questions = {
            "Which planet is known as the Red Planet?",
            "What is the capital of France?",
            "Who wrote 'To Kill a Mockingbird'?",
            "What is the largest ocean on Earth?",
            "Who painted the Mona Lisa?"
    };

    private String[][] options = {
            { "Earth", "Mars", "Jupiter", "Saturn" },
            { "Berlin", "Madrid", "Paris", "Lisbon" },
            { "Harper Lee", "J.K. Rowling", "Ernest Hemingway", "Jane Austen" },
            { "Atlantic", "Indian", "Arctic", "Pacific" },
            { "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet" }
    };

    private String[] correctAnswers = {
            "Mars", "Paris", "Harper Lee", "Pacific", "Leonardo da Vinci"
    };

    public QuizGame() {
        // Set up the frame
        setTitle("Quiz Game");
        setSize(500, 400); // Increased height to accommodate the question
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1)); // Adjusted layout to accommodate the score

        // Initialize components
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.addActionListener(this);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to frame
        add(questionLabel);
        add(option1);
        add(option2);
        add(option3);
        add(option4);
        add(nextButton);
        add(scoreLabel);

        // Display the first question
        loadQuestion();

        // Make the frame visible
        setVisible(true);
    }

    private JRadioButton createOptionButton() {
        JRadioButton button = new JRadioButton();
        button.setBackground(Color.BLUE); // Set button background color to blue
        button.setForeground(Color.WHITE); // Set text color to white
        return button;
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
            option1.setText(options[currentQuestionIndex][0]);
            option2.setText(options[currentQuestionIndex][1]);
            option3.setText(options[currentQuestionIndex][2]);
            option4.setText(options[currentQuestionIndex][3]);
            optionsGroup.clearSelection();
        } else {
            showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Over! Your score is: " + score);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            checkAnswer();
            currentQuestionIndex++;
            loadQuestion();
        }
    }

    private void checkAnswer() {
        String selectedAnswer = null;
        if (option1.isSelected()) {
            selectedAnswer = option1.getText();
        } else if (option2.isSelected()) {
            selectedAnswer = option2.getText();
        } else if (option3.isSelected()) {
            selectedAnswer = option3.getText();
        } else if (option4.isSelected()) {
            selectedAnswer = option4.getText();
        }

        if (selectedAnswer != null && selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            score++;
        }

        scoreLabel.setText("Score: " + score);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizGame());
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 * Login Page GUI Application
 * Implements email, password, and login button with input validation.
 */
public class LoginPage extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel statusLabel;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public LoginPage() {
        setTitle("User Login");
        setSize(420, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with GridBagLayout for clean alignment
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Account Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Email Label & Field
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(18);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(emailField, gbc);

        // Password Label & Field
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(18);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(passwordField, gbc);

        // Button Panel (Login & Reset)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Status / Message Label
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridy = 4;
        panel.add(statusLabel, gbc);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == resetButton) {
            handleReset();
        }
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty()) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Please enter your email address.");
            emailField.requestFocus();
            return;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Please enter a valid email address.");
            emailField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Please enter your password.");
            passwordField.requestFocus();
            return;
        }

        if (password.length() < 6) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Password must be at least 6 characters.");
            passwordField.requestFocus();
            return;
        }

        // Successful validation
        statusLabel.setForeground(new Color(34, 139, 34));
        statusLabel.setText("Login successful! Welcome, " + email);
        JOptionPane.showMessageDialog(
            this,
            "Authentication successful for: " + email,
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void handleReset() {
        emailField.setText("");
        passwordField.setText("");
        statusLabel.setText("");
        emailField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage frame = new LoginPage();
            frame.setVisible(true);
        });
    }
}

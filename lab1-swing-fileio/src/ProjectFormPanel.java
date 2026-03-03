import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {
    private JTextField txtProjectName, txtTeamLeader, txtStartDate;
    private JComboBox<String> comboTeamSize, comboProjectType;
    private JButton btnSave, btnClear;

    public ProjectFormPanel() {
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Project Name:"));
        txtProjectName = new JTextField();
        add(txtProjectName);

        add(new JLabel("Team Leader:"));
        txtTeamLeader = new JTextField();
        add(txtTeamLeader);

        add(new JLabel("Team Size:"));
        comboTeamSize = new JComboBox<>(new String[]{"1-3", "4-6", "7-10", "10+"});
        add(comboTeamSize);

        add(new JLabel("Project Type:"));
        comboProjectType = new JComboBox<>(new String[]{"Web", "Mobile", "Desktop", "API"});
        add(comboProjectType);

        add(new JLabel("Start Date:"));
        txtStartDate = new JTextField();
        add(txtStartDate);

        btnSave = new JButton("Save");
        btnClear = new JButton("Clear");
        add(btnSave);
        add(btnClear);

        btnSave.addActionListener(e -> {
            if (txtProjectName.getText().isEmpty() || txtTeamLeader.getText().isEmpty() || txtStartDate.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            } else {
                saveToFile();
            }
        });

        btnClear.addActionListener(e -> {
            txtProjectName.setText("");
            txtTeamLeader.setText("");
            txtStartDate.setText("");
            comboTeamSize.setSelectedIndex(0);
            comboProjectType.setSelectedIndex(0);
        });
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true))) {
            String recordTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("=== Project Entry ===\n");
            writer.write("Project Name : " + txtProjectName.getText() + "\n");
            writer.write("Team Leader : " + txtTeamLeader.getText() + "\n");
            writer.write("Team Size : " + comboTeamSize.getSelectedItem() + "\n");
            writer.write("Project Type : " + comboProjectType.getSelectedItem() + "\n");
            writer.write("Start Date : " + txtStartDate.getText() + "\n");
            writer.write("Record Time : " + recordTime + "\n");
            writer.write("======\n");
            JOptionPane.showMessageDialog(this, "Success!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
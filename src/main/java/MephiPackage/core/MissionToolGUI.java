package MephiPackage.core;

import MephiPackage.objects.Mission;
import MephiPackage.report.*;

import javax.swing.*;
import java.awt.*;

public class MissionToolGUI extends JFrame {

    private Mission mission;
    private ReportConfig reportConfig;
    private JTextArea textArea;
    private ReportConfigPanel configPanel;
    private JComboBox<String> reportTypeCombo;

    public MissionToolGUI(Mission mission) {
        this(mission, new ReportConfig());
    }

    public MissionToolGUI(Mission mission, ReportConfig reportConfig) {
        this.mission = mission;
        this.reportConfig = reportConfig;
        setTitle("Информация о миссии");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        generateReport();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Тип отчёта:"));

        reportTypeCombo = new JComboBox<>(new String[]{"summary", "detailed", "risk", "customizable"});
        reportTypeCombo.addActionListener(e -> generateReport());
        topPanel.add(reportTypeCombo);

        JButton configButton = new JButton("Настройки");
        configButton.addActionListener(e -> showConfigDialog());
        topPanel.add(configButton);

        add(topPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void generateReport() {
        String selectedType = (String) reportTypeCombo.getSelectedItem();
        ReportGenerator generator;

        if ("customizable".equals(selectedType)) {
            generator = new CustomizableReportGenerator(reportConfig);
        } else {
            generator = ReportFactory.getGenerator(selectedType);
        }

        String report = generator.generate(mission);
        textArea.setText(report);
        textArea.setCaretPosition(0);
    }

    private void showConfigDialog() {
        JDialog dialog = new JDialog(this, "Настройки отчёта", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);

        ReportConfigPanel panel = new ReportConfigPanel(reportConfig);
        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            reportConfig = panel.getUpdatedConfig();
            dialog.dispose();
            generateReport();
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}
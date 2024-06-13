import java.awt.*;
import java.awt.event.*;

class BinaryToDecimal extends Frame implements ActionListener {
    TextField ip, op;
    Label ipl, opl;
    Button convertb;

    BinaryToDecimal() {
        setLayout(new FlowLayout());
        setBackground(Color.magenta); // Use Color.magenta instead of color.Magenta

        ipl = new Label("Enter binary number ");
        ip = new TextField(30);
        opl = new Label("Decimal number");
        op = new TextField(30);
        op.setEditable(false);
        convertb = new Button("Convert");
        convertb.setBackground(Color.cyan); // Use Color.cyan instead of color.CYAN

        // Ensure correct order of adding components
        add(ipl);
        add(ip);
        add(opl);
        add(op);
        add(convertb);

        convertb.addActionListener(this);

        setTitle("Binary to Decimal converter");
        setSize(400, 200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String num = ip.getText();
        try {
            int decimal = Integer.parseInt(num, 2);
            op.setText(String.valueOf(decimal));
        } catch (NumberFormatException nae) {
            op.setText("Invalid binary number");
        }
    }

    public static void main(String[] args) {
        new BinaryToDecimal();
    }
}

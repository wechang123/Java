package swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingObserverFrame extends JFrame {

    public SwingObserverFrame() {
        JButton button = new JButton("정말 해도 될까?");
        button.addActionListener(new AngelListener()); // external class 
        //button.addActionListener(new DevilListener()); // external class 
        button.addActionListener(new ActionListener() { // anonoymous class 
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("당연하지. 그냥 저질러 버려!");
            }
        }); 
        button.addActionListener(event -> System.out.println("할지 말지 고민이네~")); // lambda     
        this.getContentPane().add(BorderLayout.CENTER, button);
        this.setSize(200, 200);
        this.setVisible(true);
    }
}

package org.hsm.view.room;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hsm.control.Control;
import org.hsm.model.hedspiObject.HedspiObject;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoomViewPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private HedspiObject room;

	/**
	 * Create the panel.
	 */
	public RoomViewPane() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JPanel panel = new JPanel();
		add(panel, "2, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblName = DefaultComponentFactory.getInstance().createLabel("Name");
		panel.add(lblName, "1, 1, right, default");
		
		textField = new JTextField();
		panel.add(textField, "3, 1, fill, default");
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 4, center, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (room == null)
					return;
				String name = textField.getText();
				String message = (String)Control.getInstance().getData("saveRoomName", room, name);  
				if (message == null){
					JOptionPane.showMessageDialog(null, "Save room's name success", "Save success", JOptionPane.INFORMATION_MESSAGE);
					room.setName(name);
				}
				else
					JOptionPane.showMessageDialog(null, "Save room's name failed\nMessage: " + message, "Save failed", JOptionPane.WARNING_MESSAGE);
			}
		});
		panel_1.add(btnSave, "2, 2");
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRoom(room);
			}
		});
		panel_1.add(btnReset, "4, 2");
		
		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (room == null)
					return;
				String name = (String) Control.getInstance().getData("reloadRoomName", room);
				if (name == null)
					JOptionPane.showMessageDialog(null, "Reload room's name failed", "Reload failed", JOptionPane.WARNING_MESSAGE);
				else{
					JOptionPane.showConfirmDialog(null, "Reload room's name success", "Reload success", JOptionPane.INFORMATION_MESSAGE);
					textField.setText(name);
					room.setName(name);
				}
			}
		});
		panel_1.add(btnReload, "6, 2");
	}

	public void setRoom(HedspiObject value) {
		if (value != null){
			room = value;
			textField.setText(value.getName());
		}
	}
}
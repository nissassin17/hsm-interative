package org.hsm.view.student;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;

import org.hsm.control.Control;
import org.hsm.model.hedspiObject.HedspiObject;
import org.hsm.model.hedspiObject.Student;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class StudentViewPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OtherInfoPane panelStudentOther;
	private ContactPane panelContact;
	private HedspiObject student;

	/**
	 * Create the panel.
	 */
	public StudentViewPane() {
		super();
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("109dlu:grow"),
				ColumnSpec.decode("98dlu:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		panelContact = new ContactPane();
		add(panelContact, "2, 2, fill, fill");
		
		JPanel panel = new JPanel();
		add(panel, "3, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		panelStudentOther = new OtherInfoPane();
		panel.add(panelStudentOther, "1, 2, fill, fill");
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave, "1, 4, center, default");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (student == null)
					return;
				Student st = new Student(student.getId(),
						panelContact.getFirst(),
						panelContact.getLast(),
						panelContact.isMale(),
						panelContact.getDob(),
						panelContact.getEmails(),
						panelContact.getPhones(),
						panelContact.getNote(),
						panelContact.getHome(),
						panelContact.getDistrict(),
						panelStudentOther.getPoint(),
						panelStudentOther.getMssv(),
						panelStudentOther.getYear(),
						panelStudentOther.getHedspiClass());
				
				String message = (String) Control.getInstance().getData("updateStudent", student, st);
				if (message == null){
					JOptionPane.showMessageDialog(null, "Save student successful", "Save success", JOptionPane.INFORMATION_MESSAGE);
					student.setName(st.getName());
				}
				else
					JOptionPane.showMessageDialog(null, "Save student failed\nMessage: " + message, "Save failed", JOptionPane.WARNING_MESSAGE);
			}
		});
		
	}

	public void setStudent(Student student) {
		this.student = student;
		panelContact.setContact(student);
		panelStudentOther.setStudent(student);
	}
}

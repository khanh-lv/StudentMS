/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import entity.Attendance;
import entity.ClassObj;
import entity.Schedule;
import entity.Student;
import entity.Subject;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author khanh
 */
public class AttendanceForm extends javax.swing.JFrame {

    /**
     * Creates new form AttendanceForm
     */
    public AttendanceForm() {
        initComponents();
    }
    private ClassObj c;
    private List<Attendance> attendances;

    public AttendanceForm(ClassObj c) {
        this.c = c;
        initComponents();
        try {
            loadComboBox();
            jDateChooser.setDate(new Date());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thực hiện truy vấn. Vui lòng kiểm tra lại", "Mesage", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxSubject = new javax.swing.JComboBox<>();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAttendance = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();

        setTitle("Attendance form");

        tblAttendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sinh viên", "Họ tên", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAttendance);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnShow.setText("Show");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShow)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        int choose = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đóng cửa sổ này", "Mesage", JOptionPane.WARNING_MESSAGE);
        if (choose == 0) {
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        Subject subject;
        try {
            subject = Subject.getSubjectByName(cbxSubject.getSelectedItem().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(jDateChooser.getDate());
            Schedule schedule = Schedule.getSchedule(c.getId(), subject.getId());

            int rowCount = tblAttendance.getRowCount();
            int flag = 1;
            if (rowCount > 0) {
                for (int i = 0; i < rowCount; i++) {
                    Student student = Student.getStudent(tblAttendance.getModel().getValueAt(i, 1).toString());
                    Attendance a;
                    if (Attendance.getAttendance(student, schedule, date) == null) {
                        boolean status = (boolean) tblAttendance.getModel().getValueAt(i, 3);
                        if (status) {
                            a = new Attendance(student, schedule, date, 1);
                        } else {
                            a = new Attendance(student, schedule, date, 0);
                        }
                        if (Attendance.insert(a) == null) {
                            flag = 0;
                            break;
                        }
                    } else {
                        a = Attendance.getAttendance(student, schedule, date);
                        boolean status = (boolean) tblAttendance.getModel().getValueAt(i, 3);
                        if (status) {
                            a.setStatus(1);
                        } else {
                            a.setStatus(0);
                        }
                        if (!Attendance.update(a)) {
                            flag = 0;
                            break;
                        }
                    }
                }
                if (flag == 1) {
                    JOptionPane.showMessageDialog(null, "Cập nhật điểm danh thành công", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật điểm danh thất bại", "Message", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thực hiện truy vấn. Vui lòng kiểm tra lại", "Mesage", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex.getMessage());
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        try {
            if (cbxSubject.getSelectedItem() != null) {
                Subject subject = Subject.getSubjectByName(cbxSubject.getSelectedItem().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(jDateChooser.getDate());
                Schedule schedule = Schedule.getSchedule(c.getId(), subject.getId());
                if (schedule != null) {
                    loadTable(schedule, date);
                } else {
                    JOptionPane.showMessageDialog(null, "Môn bạn chọn chưa có trong lịch học của lớp.", "Mesage", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thực hiện truy vấn. Vui lòng kiểm tra lại", "Mesage", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_btnShowActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AttendanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AttendanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AttendanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AttendanceForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendanceForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox<String> cbxSubject;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAttendance;
    // End of variables declaration//GEN-END:variables

    private void loadComboBox() throws SQLException {
        cbxSubject.removeAllItems();
        List<Subject> subjects = Subject.getSubjectbyClass(c.getId());
        for (Subject s : subjects) {
            if (s.getStatus() == 1) {
                cbxSubject.addItem(s.getSubjectName());
            }
        }
    }

    private void loadTable(Schedule schedule, String date) throws SQLException {
        List<Student> students = Student.getAllStudent(c.getClassNo());
        DefaultTableModel model = (DefaultTableModel) tblAttendance.getModel();
        model.setRowCount(0);
        int count = 1;
        for (Student s : students) {
            if (s.getStatus() == 1) {
                if (Attendance.getAttendance(s, schedule, date) == null) {
                    model.addRow(new Object[]{
                        count, s.getRollNo(), s.getFullName(), false
                    });
                } else {
                    Attendance attendance = Attendance.getAttendance(s, schedule, date);
                    if (attendance.getStatus() == 1) {
                        model.addRow(new Object[]{
                            count, s.getRollNo(), s.getFullName(), true
                        });
                    } else {
                        model.addRow(new Object[]{
                            count, s.getRollNo(), s.getFullName(), false
                        });
                    }
                }

            }
        }
    }

}

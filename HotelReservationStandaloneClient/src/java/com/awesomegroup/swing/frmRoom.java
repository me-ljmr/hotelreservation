/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.swing;

import com.awesomegroup.entity.Room;
import com.awesomegroup.entity.RoomPhotoGallery;
import com.awesomegroup.entity.RoomService;
import com.awesomegroup.entity.RoomType;
import com.awesomegroup.entity.Service;
import com.awesomegroup.sessionbean.RoomPhotoGalleryRemote;
import com.awesomegroup.sessionbean.RoomSessionBeanRemote;
import com.awesomegroup.sessionbean.RoomTypeSessionBeanRemote;
import com.awesomegroup.sessionbean.ServiceSessionBeanRemote;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.swing.table.DefaultTableModel;
import com.awesomegroup.utility.CheckListItem;
import com.awesomegroup.utility.CheckListRenderer;
import com.awesomegroup.utility.ComboBoxItem;
import com.awesomegroup.utility.FileChooserDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
/**
 *
 * @author lujamanandhar
 */
public class frmRoom extends javax.swing.JFrame {

    /**
     * Creates new form frmRoom
     */
     @EJB
    private static RoomSessionBeanRemote roomremote;
     
     @EJB
     private static RoomTypeSessionBeanRemote roomtyperemote;
     
     @EJB
     private static RoomPhotoGalleryRemote roomphotoremote;
     
     @EJB
    private static ServiceSessionBeanRemote serviceremote;
    
    private char selMode = 'C';
    private int selId = -1;
    // for populating in a list and combo
    private List<Service> services;
    private List<RoomType> roomtypes;
    private List<byte[]> files;
    
    private List<Room> rooms;
    
    private Collection<RoomPhotoGallery> roompictures;
    
    public frmRoom() {
        initComponents();
        roompictures = new ArrayList<>();
        loadServices();
        loadRoomTypes();
        
        loadData();
        setSelMode('C');
    }
    private void loadServices(){
        services = serviceremote.getAll();
        CheckListItem[] items=new CheckListItem[services.size()];
        CheckListItem item;
        int i=0;
        for(Service service : services){
            item = new CheckListItem(service.getId(),service.getTitle()); 
            items[i] = item;
            i++;
        }
        lstServices.setListData(items);
        lstServices.setCellRenderer(new CheckListRenderer());
        lstServices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstServices.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
              JList list = (JList) event.getSource();
              int index = list.locationToIndex(event.getPoint());// Get index of item
                                                                 // clicked
              CheckListItem item = (CheckListItem) list.getModel()
                  .getElementAt(index);

              item.setSelected(!item.isSelected()); // Toggle selected state
              list.repaint(list.getCellBounds(index, index));// Repaint cell
 

            }
    });
    }
    
    private void loadRoomTypes(){
        
        roomtypes = roomtyperemote.getAll();
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         
        for(RoomType roomtype : roomtypes){
            model.addElement(new ComboBoxItem(roomtype.getId(), roomtype.getTitle() ) );
        }
        this.cmbRoomType.setModel(model);
            
    }
    private void loadData(){
          rooms = roomremote.getAll();
        tblRooms.setModel(buildTableModel(rooms));
        
        
    }
    private void uncheckAllServices(){
        CheckListItem item;
        for(int x=0;x<services.size();x++){
            item = (CheckListItem) lstServices.getModel()
                  .getElementAt(x);
             item.setSelected(false); 
             lstServices.repaint(lstServices.getCellBounds(x, x));
        }
        
    }
    private void setSelMode(char mode){
        this.selMode = mode;
        if(mode=='C'){
            btnCancel.setVisible(false);
            txtRoomNumber.setText("");
            spFloor.setValue(0);
            cmbRoomType.setSelectedIndex(-1);
            uncheckAllServices();
            lstPictures.removeAll();
            btnAddUpdate.setText("Add to List");
            selId = -1;
            tblRooms.setEnabled(true);
        }else{
            btnAddUpdate.setText("Update");
            tblRooms.setEnabled(false);
            btnCancel.setVisible(true);
        }
    }
    public static DefaultTableModel buildTableModel(List list)
    { 
        // names of columns
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Id");
        columnNames.add("Room Number");
        columnNames.add("Floor ");
        columnNames.add("Room Type");
        columnNames.add("Price"); 
        // data of the table
        Vector<Vector<Object>> data = new Vector<>();

        for (Object obj:list){ 
            Vector<Object> vector = new Vector<>();
            Room room = (Room)obj;
            vector.add(room.getId());
            vector.add(room.getRoomNumber());
            vector.add(room.getFloor());
            vector.add(room.getRoomTypeId().getTitle());
            vector.add(room.getRoomTypeId().getRate());
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames){
                public boolean isCellEditable(int row, int column)
                {
                  return false;//This causes all cells to be not editable
                }
        };

    }
    private void updateInsertRoomInfo(){
        Room room;
        if(selId == -1){
            room = new Room();
        }
        else{
            room = (Room)roomremote.get(selId);
        }
        room.setFloor(Integer.parseInt(spFloor.getValue().toString()));
        room.setRoomNumber(txtRoomNumber.getText());
        room.setRoomTypeId(roomtypes.get(cmbRoomType.getSelectedIndex()));
        
         List<RoomService> roomservicecollection = new ArrayList<>();
          int x=0;
          CheckListItem item;
         RoomService roomservice=null;
         List<RoomPhotoGallery> roompictures;
         for (Service service : services) {
            item = (CheckListItem)lstServices.getModel().getElementAt(x);
            if(item.isSelected()){
                roomservice = new RoomService();
                roomservice.setRoomId(room);
                roomservice.setServiceId(service);
                roomservicecollection.add(roomservice);
            }
            x++;
        }
        room.setRoomServiceCollection(roomservicecollection);
        
        roomremote.save(room);
        RoomPhotoGallery rpg;
         
        if (files!=null){
            for(byte[] eachfile :files){
                rpg = new RoomPhotoGallery(); 
                System.out.print(eachfile);
                rpg.setPhoto(eachfile);
                rpg.setPhotoTitle("Nuga Picture");
                rpg.setRoomId(room);
                //roomphotoremote.save(rpg);
                  
            }
        }
        //room.setRoomPhotoGalleryCollection(roompictures);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRoomNumber = new javax.swing.JTextField();
        btnAddUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cmbRoomType = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstServices = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        spFloor = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRooms = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstPictures = new javax.swing.JList<>();
        btnDeleteSelectedPicture = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnAddPictures = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Room Number");

        jLabel2.setText("Floor Number");

        jLabel3.setText("Room Type");

        btnAddUpdate.setText("Add a room");
        btnAddUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lstServices);

        jLabel4.setText("Amenities:");

        tblRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRoomsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRooms);

        jScrollPane3.setViewportView(lstPictures);

        btnDeleteSelectedPicture.setText("x");
        btnDeleteSelectedPicture.setToolTipText("Delete selected picture");
        btnDeleteSelectedPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSelectedPictureActionPerformed(evt);
            }
        });

        jLabel6.setText("Pictures:");

        btnAddPictures.setBackground(new java.awt.Color(255, 102, 102));
        btnAddPictures.setText("+");
        btnAddPictures.setToolTipText("Add pictures");
        btnAddPictures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPicturesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(32, 32, 32)
                                .addComponent(txtRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddPictures, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteSelectedPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete)
                            .addComponent(btnCancel)
                            .addComponent(btnAddUpdate)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(cmbRoomType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(387, 387, 387)
                                .addComponent(spFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(123, 123, 123)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddUpdate, btnCancel, btnDelete});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnAddUpdate)
                    .addComponent(spFloor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(jLabel3)
                    .addComponent(cmbRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(btnDeleteSelectedPicture)
                            .addComponent(jLabel6)
                            .addComponent(btnAddPictures))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteSelectedPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSelectedPictureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteSelectedPictureActionPerformed

    private void tblRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRoomsMouseClicked
        // TODO add your handling code here:
        
         if(evt.getClickCount()==1){
            final JTable target = (JTable)evt.getSource();
            final int row = target.getSelectedRow();
            final int column = target.getSelectedColumn();

            showSelected(row);
        }
        
    }//GEN-LAST:event_tblRoomsMouseClicked

    private void btnAddUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUpdateActionPerformed
        // TODO add your handling code here:
        try{
            updateInsertRoomInfo();
            loadData();
            JOptionPane.showMessageDialog(this, "Data saved successfully");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR:" + ex.getMessage());
             java.util.logging.Logger.getLogger(frmRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnAddUpdateActionPerformed

    private void btnAddPicturesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPicturesActionPerformed
        // TODO add your handling code here:
        FileChooserDialog filechooser =new FileChooserDialog();
        files = filechooser.getFileFromChooser();
         
        filechooser = null;
        
    }//GEN-LAST:event_btnAddPicturesActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        setSelMode('C');
    }//GEN-LAST:event_btnCancelActionPerformed
    private void showSelected(int row){
        setSelMode('U');
        Room selRoom = rooms.get(row);
        selId = Integer.parseInt(this.tblRooms.getValueAt(row, 0).toString());
         txtRoomNumber.setText(this.tblRooms.getValueAt(row, 1).toString());
         spFloor.setValue(Integer.parseInt(this.tblRooms.getValueAt(row, 2).toString()));
         int x=0,y=0,z=0;
         for(RoomType rt:roomtypes){
              
             if(Objects.equals(rt.getId(), selRoom.getRoomTypeId().getId())){
                 cmbRoomType.setSelectedIndex(x); 
                 break;
             }
             x++;
         }
         x=0;
         CheckListItem item;
         for(Service sv:services){
             item = (CheckListItem) lstServices.getModel()
                  .getElementAt(x);
             item.setSelected(false);  
             lstServices.repaint(lstServices.getCellBounds(x, x));
             for(RoomService rs:selRoom.getRoomServiceCollection()){
                if(Objects.equals(item.getId(), rs.getServiceId().getId())){
                     
                    item.setSelected(true); // Toggle selected state
                    lstServices.repaint(lstServices.getCellBounds(x, x));
                }
                y++;
             }
             x++;
         }
       
    }
    
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
            java.util.logging.Logger.getLogger(frmRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRoom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPictures;
    private javax.swing.JButton btnAddUpdate;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteSelectedPicture;
    private javax.swing.JComboBox cmbRoomType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> lstPictures;
    private javax.swing.JList lstServices;
    private javax.swing.JSpinner spFloor;
    private javax.swing.JTable tblRooms;
    private javax.swing.JTextField txtRoomNumber;
    // End of variables declaration//GEN-END:variables
}

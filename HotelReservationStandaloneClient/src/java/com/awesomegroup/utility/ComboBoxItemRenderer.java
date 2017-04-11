/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomegroup.utility;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author lujamanandhar
 */
public class ComboBoxItemRenderer extends BasicComboBoxRenderer{
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            if (value != null)
            {
                ComboBoxItem item = (ComboBoxItem)value;
                setText( item.getDescription().toUpperCase() );
            }

            if (index == -1)
            {
                ComboBoxItem item = (ComboBoxItem)value;
                setText( "" + item.getId() );
            }


            return this;
        }
}

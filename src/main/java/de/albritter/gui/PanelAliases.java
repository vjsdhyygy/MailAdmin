
/*
 * This file is part of MailAdmin.
 *
 *     MailAdmin is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     MailAdmin is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with MailAdmin.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.albritter.gui;

import de.albritter.gui.tables.DataTable;
import de.albritter.utils.EventHandler;
import de.albritter.utils.TableSelectionEvent;
import lombok.Getter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class PanelAliases extends JPanel implements TableSelectionEvent {
    @Getter
    private final JTextField textSourceUsername;
    @Getter
    private final JTextField textDestination;
    @Getter
    private final JCheckBox chckbxActive;
    @Getter
    private final JComboBox comboBoxDomain;
    @Getter
    private final JSpinner spinnerID;

    /**
     * Create the panel.
     */
    public PanelAliases() {
        EventHandler.registerForSelectionChangeEvent(this);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 29, 0};
        gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblId = new JLabel("ID");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.EAST;
        gbc_lblId.insets = new Insets(0, 0, 5, 5);
        gbc_lblId.gridx = 0;
        gbc_lblId.gridy = 0;
        add(lblId, gbc_lblId);

        spinnerID = new JSpinner();
        GridBagConstraints gbc_spinnerID = new GridBagConstraints();
        gbc_spinnerID.insets = new Insets(0, 0, 5, 5);
        gbc_spinnerID.ipadx = 20;
        gbc_spinnerID.anchor = GridBagConstraints.NORTHWEST;
        gbc_spinnerID.gridx = 1;
        gbc_spinnerID.gridy = 0;
        add(spinnerID, gbc_spinnerID);

        JLabel lblSourceMailadress = new JLabel("ת������");
        GridBagConstraints gbc_lblSourceMailadress = new GridBagConstraints();
        gbc_lblSourceMailadress.anchor = GridBagConstraints.EAST;
        gbc_lblSourceMailadress.insets = new Insets(0, 0, 5, 5);
        gbc_lblSourceMailadress.gridx = 0;
        gbc_lblSourceMailadress.gridy = 1;
        add(lblSourceMailadress, gbc_lblSourceMailadress);

        textSourceUsername = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 1;
        add(textSourceUsername, gbc_textField);
        textSourceUsername.setColumns(10);

        JLabel lblAT1 = new JLabel("@");
        GridBagConstraints gbc_lblAT1 = new GridBagConstraints();
        gbc_lblAT1.insets = new Insets(0, 0, 5, 5);
        gbc_lblAT1.anchor = GridBagConstraints.EAST;
        gbc_lblAT1.gridx = 2;
        gbc_lblAT1.gridy = 1;
        add(lblAT1, gbc_lblAT1);

        comboBoxDomain = new JComboBox<>(new String[]{"tset", "foi13.de"});
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 3;
        gbc_comboBox.gridy = 1;
        add(comboBoxDomain, gbc_comboBox);

        JLabel lblDestination = new JLabel("�յ�����");
        GridBagConstraints gbc_lblDestination = new GridBagConstraints();
        gbc_lblDestination.anchor = GridBagConstraints.EAST;
        gbc_lblDestination.insets = new Insets(0, 0, 5, 5);
        gbc_lblDestination.gridx = 0;
        gbc_lblDestination.gridy = 2;
        add(lblDestination, gbc_lblDestination);

        textDestination = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.gridwidth = 3;
        gbc_textField_1.insets = new Insets(0, 0, 5, 5);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 2;
        add(textDestination, gbc_textField_1);
        textDestination.setColumns(10);

        chckbxActive = new JCheckBox("����");
        GridBagConstraints gbc_chckbxActive = new GridBagConstraints();
        gbc_chckbxActive.anchor = GridBagConstraints.WEST;
        gbc_chckbxActive.insets = new Insets(0, 0, 0, 5);
        gbc_chckbxActive.gridx = 1;
        gbc_chckbxActive.gridy = 3;
        add(chckbxActive, gbc_chckbxActive);

    }

    public void updateCombobox(String[] domains) {
        comboBoxDomain.setModel(new DefaultComboBoxModel<>(domains));
    }

    @Override
    public void selectionChange(DataTable table) {
        for (int i = 0; i < table.getColumnCount(); i++) {

            int selectedRow = table.getSelectedRow();
            switch ((String) table.getColumnModel().getColumn(i).getHeaderValue()) {
                case "ID":
                    spinnerID.setValue(table.getValueAt(selectedRow, i));
                    break;
                case "ת���û�":
                    textSourceUsername.setText((String) table.getValueAt(selectedRow, i));
                    break;

                case "ת������":
                    comboBoxDomain.setSelectedItem(table.getValueAt(selectedRow, i));
                    break;

                case "�յ��û�":
                    textDestination.setText((String) table.getValueAt(selectedRow, i) + '@');
                    break;
                case "�յ�����":
                    textDestination.setText(textDestination.getText() + table.getValueAt(selectedRow, i));
                    break;
                case "����":
                    //  System.out.println(table.getValueAt(selectedRow, i));
                    chckbxActive.setSelected((Boolean) table.getValueAt(selectedRow, i));
                    break;

            }
        }
    }
}

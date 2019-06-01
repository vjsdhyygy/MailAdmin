
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

import de.albritter.gui.tables.AliasTable;
import de.albritter.gui.tables.DomainTable;
import de.albritter.gui.tables.MailboxTable;
import de.albritter.gui.tables.TLSTable;
import de.albritter.sql.MySQLHandler;
import de.albritter.utils.EventHandler;
import lombok.Getter;
import lombok.Setter;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class WindowMain extends JFrame {
    @Getter
    private final PanelMailbox panelMailbox;
    @Getter
    private final PanelTLS panelTLS;
    @Getter
    private final PanelAliases panelAliases;
    @Getter
    private final PanelDomain panelDomain;
    @Getter
    private final JTabbedPane tabbedPane;
    @Getter
    private JButton btnAdd;
    @Getter
    private JButton btnUpadte;
    @Getter
    private JButton btnRemove;
    private JPanel swapPanel;
    private JPanel InputField;
    @Getter
    private JPanel pTLS;
    @Getter
    private JPanel pAliases;
    @Getter
    private JPanel pMailboxes;
    @Getter
    private JPanel pDomains;
    @Setter
    private String[] domains;

    /**
     * Create the frame.
     */
    public WindowMain() {
        setDefaultCloseOperation(3);
        panelAliases = new PanelAliases();
        panelDomain = new PanelDomain();
        panelMailbox = new PanelMailbox();
        panelTLS = new PanelTLS();

        InputField = new JPanel();
        getContentPane().add(InputField, BorderLayout.SOUTH);
        GridBagLayout gbl_InputField = new GridBagLayout();
        gbl_InputField.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_InputField.rowHeights = new int[]{0, 0, 0, 0};
        gbl_InputField.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        gbl_InputField.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        InputField.setLayout(gbl_InputField);

        swapPanel = new PanelDomain();
        GridBagConstraints gbc_swapPanel = new GridBagConstraints();
        gbc_swapPanel.gridheight = 3;
        gbc_swapPanel.gridwidth = 9;
        gbc_swapPanel.insets = new Insets(0, 0, 0, 5);
        gbc_swapPanel.fill = GridBagConstraints.BOTH;
        gbc_swapPanel.gridx = 0;
        gbc_swapPanel.gridy = 0;
        InputField.add(swapPanel, gbc_swapPanel);

        Box verticalBox = Box.createVerticalBox();
        GridBagConstraints gbc_verticalBox = new GridBagConstraints();
        gbc_verticalBox.anchor = GridBagConstraints.NORTHEAST;
        gbc_verticalBox.gridwidth = 2;
        gbc_verticalBox.gridheight = 3;
        gbc_verticalBox.insets = new Insets(0, 0, 5, 5);
        gbc_verticalBox.gridx = 9;
        gbc_verticalBox.gridy = 0;
        InputField.add(verticalBox, gbc_verticalBox);

        ApplyListener applyListener = new ApplyListener(this);
        btnAdd = new JButton("����");
        btnAdd.addActionListener(applyListener);
        verticalBox.add(btnAdd);
        btnAdd.setSelected(true);
        btnUpadte = new JButton("�޸�");
        btnUpadte.addActionListener(applyListener);
        verticalBox.add(btnUpadte);
        btnRemove = new JButton("ɾ��");
        btnRemove.addActionListener(applyListener);
        verticalBox.add(btnRemove);


        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addChangeListener(new TabbedItemListener(tabbedPane));
        pDomains = new DomainTable();
        tabbedPane.addTab("�����б�", null, pDomains, null);


        pMailboxes = new MailboxTable();
        tabbedPane.addTab("�����û�", null, pMailboxes, null);

        pAliases = new AliasTable();
        tabbedPane.addTab("����ת��", null, pAliases, null);

        pTLS = new TLSTable();
        tabbedPane.addTab("TLS", null, pTLS, null);
        this.updateTables();

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowMain frame = new WindowMain();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void swapPanel(JPanel panel) {
        InputField.remove(swapPanel);
        swapPanel = panel;
        GridBagConstraints gbc_swapPanel = new GridBagConstraints();
        gbc_swapPanel.gridheight = 3;
        gbc_swapPanel.gridwidth = 9;
        gbc_swapPanel.insets = new Insets(0, 0, 5, 5);
        gbc_swapPanel.fill = GridBagConstraints.BOTH;
        gbc_swapPanel.gridx = 0;
        gbc_swapPanel.gridy = 0;
        InputField.add(swapPanel, gbc_swapPanel);
        this.revalidate();
        this.repaint();
    }

    public void updateTables() {
        EventHandler.updateDomainTable(MySQLHandler.getDomains());
        EventHandler.updateMailboxTable(MySQLHandler.getMailboxes());
        EventHandler.updateAliasTable(MySQLHandler.getAliases());
        EventHandler.updateTLSTable(MySQLHandler.getTlsPolicies());
        panelAliases.updateCombobox(MySQLHandler.getCurrentDomainSet());
        panelMailbox.updateCombobox(MySQLHandler.getCurrentDomainSet());
    }

}

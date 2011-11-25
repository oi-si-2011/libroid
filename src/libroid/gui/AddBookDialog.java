package libroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

class AddBookDialog extends JFrame implements ActionListener{
    private FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File f) {
            String fileName = f.getName().toLowerCase();
            return (f.isDirectory()) ||
                    fileName.endsWith(".epub") ||
                    fileName.endsWith(".pdf") ||
                    fileName.endsWith(".mobi")
                    ? true : false;
        }

        @Override
        public String getDescription() {
            return "Ebook files";
        }
    };
    private JFileChooser fileChooser = new JFileChooser();
    private File file;

    private String uri;

    public AddBookDialog() {
        fileChooser.setFileFilter(fileFilter);
        setSize(200, 200);
        setLocation(GUIUtil.getLocationForScreenCenter(getSize()));
    }

    public void actionPerformed(ActionEvent e) {
        fileChooser.showOpenDialog(this);
        file = fileChooser.getSelectedFile();
        if(file != null){
            setVisible(true);
        }
    }
}
package presentacion.menuPrincipal;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image image = null;
        private Image intermediateImage = null;
        private Icon icon;

        public Imagen() {
                super();
        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getImage() != null) {
                        if (intermediateImage == null
                                        || intermediateImage.getWidth(null) != getWidth()
                                        || intermediateImage.getHeight(null) != getHeight()) {
                                intermediateImage = createImage(getWidth(), getWidth());
                                Graphics gImg = intermediateImage.getGraphics();
                                gImg.drawImage(getImage(), 0, 0, getWidth(), getWidth(), null);
                        }
                        g.drawImage(intermediateImage, 0, 0, null);
                }
        }

        private Image getImage() {
                return image;
        }

        private void setImage(Image image) {
                this.image = image;
        }

        public Icon getIcon() {
                return icon;
        }

        public void setIcon(Icon icon) {
                this.icon = icon;
                setImage(((ImageIcon) icon).getImage());
        }

}

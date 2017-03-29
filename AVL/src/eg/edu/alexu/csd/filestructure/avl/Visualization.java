package eg.edu.alexu.csd.filestructure.avl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Visualization {

	static IAVLTree<Integer> tree;

	public static void main(String[] args) {
		tree = new AVLTree<Integer>();
		new GUI();
	}

	static class GUI extends JFrame implements ActionListener {
		private JPanel pnl;
		private JButton btn0, btn1, btn2, btn3;

		public GUI() {
			this.setTitle("AVL Tree");
			this.setSize(400, 100);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pnl = new JPanel();
			btn0 = new JButton("Insert");
			btn1 = new JButton("Find");
			btn2 = new JButton("Delete");
			btn3 = new JButton("Show Tree");
			btn0.addActionListener(this);
			btn1.addActionListener(this);
			btn2.addActionListener(this);
			btn3.addActionListener(this);
			pnl.add(btn0);
			pnl.add(btn1);
			pnl.add(btn2);
			pnl.add(btn3);

			this.add(pnl);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn0) {
				String s = JOptionPane.showInputDialog("Enter an Integer");
				tree.insert(Integer.parseInt(s));
			} else if (e.getSource() == btn1) {
				String s = JOptionPane.showInputDialog("Enter an Integer");
				if (!tree.search(Integer.parseInt(s)))
					JOptionPane.showMessageDialog(null, "Not Found");
				else
					JOptionPane.showMessageDialog(null, "Found");
			} else if (e.getSource() == btn2) {
				String s = JOptionPane.showInputDialog("Enter a word");
				if (!tree.search(Integer.parseInt(s)))
					JOptionPane.showMessageDialog(null, "Not Found");
				else {
					tree.delete(Integer.parseInt(s));
					JOptionPane.showMessageDialog(null, "Removed");
				}
			} else if (e.getSource() == btn3) {
				JFrame f = new JFrame("AVL Tree");
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
					}
				});
				Drawtree applet = new Drawtree();
				f.getContentPane().add("Center", applet);
				Toolkit tk = Toolkit.getDefaultToolkit();
				int xSize = ((int) tk.getScreenSize().getWidth());
				applet.init(((AVLTree<Integer>) tree).root, xSize - 50);
				f.pack();
				f.setSize(new Dimension(xSize, 600));
				f.setVisible(true);
			}
		}
	}

	static class Drawtree extends JApplet {

		int width;
		Node<Integer> root = null;
		Graphics2D g2;

		public void init(Node<Integer> node, int x) {
			root = node;
			width = x;
		}

		public void paint(Graphics g) {
			g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
			getSize();
			inorder(root, 0, width, 80, -1);
		}

		public void draw(int x1, int x2, int y, String n, int d) {
			g2.setPaint(Color.black);
			int x = (x1 + x2) / 2;
			if (d == 1)
				g2.draw(new Line2D.Double(x2, y - 30, x + 15, y));
			else if (d == 2)
				g2.draw(new Line2D.Double(x + 15, y, x1 + 30, y - 30));
			g2.setPaint(Color.black);
			Shape circle = new Ellipse2D.Double((x1 + x2) / 2, y, 40, 40);
			g2.draw(circle);
			g2.fill(circle);
			g2.setPaint(Color.red);
			g2.drawString(n, x + 15, y + 23);
		}

		void inorder(Node<Integer> r, int x1, int x2, int y, int state) {
			if (r == null)
				return;
			inorder((Node<Integer>) r.getLeftChild(), x1, (x1 + x2) / 2, y + 40, 1);
			if (r.equals(((AVLTree<Integer>) tree).root)) {
				draw(x1, x2, y, r.getValue() + "", 0);
			} else {
				draw(x1, x2, y, r.getValue() + "", 0);
			}
			inorder((Node<Integer>) r.getRightChild(), (x1 + x2) / 2, x2, y + 40, 2);
			if (state == 1)
				draw(x1, x2, y, r.getValue() + "", 1);
			else if (state == 2)
				draw(x1, x2, y, r.getValue() + "", 2);
		}
	}

}

package lr_in_the_well.alexis_puska;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lr_in_the_well.alexis_puska.domain.Level;

public class App extends JFrame {

	private static final long serialVersionUID = -8256444946608935363L;

	public static void main(String[] args) {
		System.out.println("Hello World!");
		App app = new App();
		app.ShowPNG();
		app.writeJson();
	}

	public void writeJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Level> file = new ArrayList<>();
		Level level = new Level(1, true, 1, 1, 1);
		file.add(level);
		try {

			System.out.println(objectMapper.writeValueAsString(file));
			ClassLoader classLoader = getClass().getClassLoader();
			File f = new File(classLoader.getResource("json_level_parser.json").getFile());
			List<Level> ls = objectMapper.readValue(f, List.class);
			ls.add(level);
			ls.add(level);
			ls.add(level);
			ls.add(level);
			ls.add(level);
			ls.add(level);
			ls.add(level);
			ls.add(level);
			ls.add(level);
			System.out.println("size : " + ls.size() + "     " + objectMapper.writeValueAsString(ls));

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ShowPNG() {
		JPanel panel = new JPanel();
		panel.setSize(500, 640);
		panel.setBackground(Color.CYAN);
		ClassLoader classLoader = getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(classLoader.getResource("image_menu.png"));
		JLabel label = new JLabel();
		label.setIcon(icon);
		panel.add(label);
		this.getContentPane().add(panel);
		// this.setLayout(new BorderLayout());
		// this.add(panel, BorderLayout.NORTH);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(400, 400);
	}

	private void loadSnakeSprites() {
		BufferedImage bigImg;
		BufferedImage bigImgEnnemies;
		try {
			bigImg = ImageIO.read(new File("src/ressources/snake.png"));
			bigImgEnnemies = ImageIO.read(new File("src/ressources/snake2.png"));

			// The above line throws an checked IOException which must be
			// caught.

			final int rows = 8;
			final int cols = 4;
			final int rowsEnnemies = 4;
			final int colsEnnemies = 1;
			BufferedImage[] sprites = new BufferedImage[(rows * cols)];
			BufferedImage[] ennemieSprites = new BufferedImage[(rowsEnnemies * colsEnnemies)];

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					// sprites[(i * cols) + j] = bigImg.getSubimage(j * width, i
					// * height, width, height);
				}
			}
			for (int i = 0; i < rowsEnnemies; i++) {
				for (int j = 0; j < colsEnnemies; j++) {
					// ennemieSprites[(i * colsEnnemies) + j] = bigImgEnnemies
					// .getSubimage(j * widthEnnemies, i * heightEnnemies,
					// widthEnnemies, heightEnnemies);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

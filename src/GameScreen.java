import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class GameScreen extends Application {
	private ArrayList<TranslateTransition> translators=new ArrayList<>();
	public Button menu;
	private boolean isPaused=false;
	private boolean blank_click=true;
	private Stage primaryStage2;
	private ImageView sunflower_sun;
	@FXML
	private Button shooter_button;
	@FXML
	private AnchorPane Anchor;
	@FXML
	public ImageView lawnmower;
	@FXML
	private ImageView shooter_gif;
	@FXML
	public ImageView sidebar_shooter;
	@FXML
	private ImageView sunflower_gif;
	@FXML
	public ImageView sidebar_sunflower;
	@FXML
	private ImageView walnut_gif;
	@FXML
	public ImageView sidebar_walnut;
	@FXML
	public javafx.scene.control.Label sun;
	// @FXML
	// public ImageView zombie_gif;
	@FXML
	public ImageView cherry_img;
	@FXML
	public ImageView repeeater_img;
	@FXML
	public ImageView falling_sun;
	@FXML
	public ImageView CherryBomb_gif;

	public Lawn lawn = new Lawn();

	private int[] y_coord = { 50, 180, 310, 420, 540 };

	private double lastrun = 0.0;

	@FXML
	public ProgressBar slider;
	private boolean isPlaced = false;
	private int sunCount = 200;
	@FXML
	public Stage primaryStage;
	@FXML
	public Scene scene;
	private int curGif;
	public EventHandler<MouseEvent> e;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private boolean pea_spawnable;

	public GameScreen() {

	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// primaryStage.setScene(scene);
		// primaryStage.show();
	}

	@FXML
	public void move_shooter(MouseEvent e) {
		// shooter_gif.setVisible(true);
		if (isPlaced == false) {
			double x = e.getX() - 80;
			double y = e.getY() - 80;
			double[] curr = lawn.correct_layout(x, y);
			shooter_gif.setX(curr[0] - 70);
			shooter_gif.setY(curr[1] - 50);
			System.out.println(e.getSource());
		}
	}

	public void move(MouseEvent e) {
		if (curGif == 0) {
			move_shooter(e);
		} else if (curGif == 1) {
			move_sunflower(e);
		} else if (curGif == 2) {
			move_walnut(e);
		} else if (curGif == 3) {
			move_CherryBomb(e);
		}
	}

	@FXML
	public void move_sunflower(MouseEvent e) {
		// shooter_gif.setVisible(true);
		if (isPlaced == false) {
			double x = e.getX() - 80;
			double y = e.getY() - 80;
			double[] curr = lawn.correct_layout(x, y);
			sunflower_gif.setX(curr[0] - 70);
			sunflower_gif.setY(curr[1] - 50);
			System.out.println(e.getSource());
		}
	}

	@FXML
	public void move_CherryBomb(MouseEvent e) {
		// shooter_gif.setVisible(true);
		if (isPlaced == false) {
			double x = e.getX();
			double y = e.getY();
			double[] curr = lawn.correct_layout(x, y);
			CherryBomb_gif.setX(curr[0] - 70);
			CherryBomb_gif.setY(curr[1] - 50);
			System.out.println(e.getSource());
		}
	}

	@FXML
	public void move_walnut(MouseEvent e) {
		// shooter_gif.setVisible(true);

		if (isPlaced == false) {
			double x = e.getX() - 80;
			double y = e.getY() - 80;
			double[] curr = lawn.correct_layout(x, y);
			// if(x>)
			walnut_gif.setX(curr[0]);
			walnut_gif.setY(curr[1]);
			// walnut_gif.setX(x);
			// walnut_gif.setY(y);
			// System.out.println((e.getX()-80)+" "+(e.getY() - 80));
		}
		// if(isPlaced==true){
		// int lane=1;
		// Walnut w=new Walnut()
		// }
	}

	@FXML
	public void spawn_shooter(javafx.event.ActionEvent actionEvent) {
		if (sunCount >= 100) {
			pea_spawnable = true;
			Image i = new Image("shooter_gif.gif");
			shooter_gif = new ImageView(i);
			shooter_gif.setScaleX(0.5);
			shooter_gif.setScaleY(0.5);
			Anchor.getChildren().add(shooter_gif);
			sunCount = sunCount - 100;
			sun.setText("" + sunCount);
			curGif = 0;
			isPlaced = false;
			blank_click=false;
			System.out.println(actionEvent.getSource());
		}
	}

	@FXML
	public void spawn_sunflower(javafx.event.ActionEvent actionEvent) {
		if (sunCount >= 50) {
			pea_spawnable = true;
			Image i = new Image("sunflower_gif.gif");
			sunflower_gif = new ImageView(i);
			sunflower_gif.setScaleX(0.5);
			sunflower_gif.setScaleY(0.5);
			Anchor.getChildren().add(sunflower_gif);
			sunCount = sunCount - 50;
			sun.setText("" + sunCount);
			checkOpacity();
			curGif = 1;
			isPlaced = false;
			blank_click=false;

			System.out.println(actionEvent.getSource());
		}
	}

	@FXML
	public void spawn_CherryBomb(javafx.event.ActionEvent actionEvent) {
		if (sunCount >= 150) {
			pea_spawnable = true;
			Image i = new Image("CherryBomb_gif.gif");
			CherryBomb_gif = new ImageView(i);
			CherryBomb_gif.setScaleX(0.4);
			CherryBomb_gif.setScaleY(0.4);
			Anchor.getChildren().add(CherryBomb_gif);
			sunCount = sunCount - 150;
			sun.setText("" + sunCount);
			checkOpacity();
			curGif = 3;
			isPlaced = false;
			blank_click=false;
			System.out.println(actionEvent.getSource());
		}
	}

	@FXML
	public void spawn_walnut(javafx.event.ActionEvent actionEvent) {
		if (sunCount >= 50) {
			pea_spawnable = true;
			Image i = new Image("walnut_gif.gif");
			walnut_gif = new ImageView(i);
			walnut_gif.setScaleX(0.7);
			walnut_gif.setScaleY(0.7);
			Anchor.getChildren().add(walnut_gif);
			sunCount = sunCount - 50;
			sun.setText("" + sunCount);
			checkOpacity();
			curGif = 2;
			isPlaced = false;
			System.out.println(actionEvent.getSource());
		}
	}

	public void checkOpacity() {
		if (sunCount < 50) {
			sidebar_sunflower.setOpacity(0.5);
			sidebar_walnut.setOpacity(0.5);
		} else {
			sidebar_sunflower.setOpacity(1);
			sidebar_walnut.setOpacity(1);
		}
		if (sunCount < 100) {
			sidebar_shooter.setOpacity(0.5);
		} else {
			sidebar_shooter.setOpacity(1);
		}

		if (sunCount < 150) {
			cherry_img.setOpacity(0.5);
		} else {
			cherry_img.setOpacity(1);
		}
		// repeeater_img.setOpacity(0.5);
		// cherry_img.setOpacity(0.5);

	}

	// public void put(MouseEvent e) {
	// isPlaced = true;
	// }

	public void FallingSun() {
		slider.setTranslateX(0);
		moveSlider();
		this.setupTimeline();
		setupSunflowerSunTimeline();
	}

	// 50,180,310,440,570
	@FXML
	public void inGameMenu(ActionEvent e) throws IOException, InterruptedException {
		if(!isPaused) {
			for (TranslateTransition i : translators) {
				i.pause();
			}
			isPaused = true;
			primaryStage2 = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("P1.fxml"));
			Scene scene = new Scene(root);
			this.primaryStage2.setTitle("Pause Screen");
			this.primaryStage2.setScene(scene);
			this.primaryStage2.showAndWait();
			for (TranslateTransition i : translators) {
				i.play();
			}
			isPaused = false;
		}
	}

	// @Override
	// public void initialize(URL location, ResourceBundle resources) {
	// TranslateTransition translatorObj = new
	// TranslateTransition(Duration.seconds(38), zombie_gif);
	// translatorObj.setToX(-880);
	// translatorObj.setAutoReverse(true);
	// translatorObj.play();
	// }

//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		TranslateTransition translatorObj = new TranslateTransition(Duration.seconds(38), zombie_gif);
//		translators.add(translatorObj);
//		// translatorObj.setDuration(Duration.seconds(10));
//		translatorObj.setToX(-880);
//		translatorObj.setAutoReverse(true);
//		// translatorObj.setCycleCount(Animation.INDEFINITE);
//
//		translatorObj.play();
//	}

	@FXML
	public void movelawnmover() {
		TranslateTransition translatorObj = new TranslateTransition(Duration.seconds(10), lawnmower);
		translators.add(translatorObj);
		// translatorObj.setDuration(Duration.seconds(10));
		translatorObj.setToX(1200);
		// translatorObj.setAutoReverse(true);
		// translatorObj.setCycleCount(Animation.INDEFINITE);
		System.out.println("Click detected");
		// translatorObj.setCycleCount(Animation.INDEFINITE);

		translatorObj.play();
	}

	public void setupTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(20), new TimeHandler());
		Timeline timeline = new Timeline(kf);

		KeyFrame kfz = new KeyFrame(Duration.seconds(20), new ZombieTimeHandler());
		Timeline timelinezombies = new Timeline(kfz);

		timelinezombies.setCycleCount(Animation.INDEFINITE);
		timeline.setCycleCount(Animation.INDEFINITE);
		timelinezombies.play();
		timeline.play();
//		System.out.println(1);
	}

	private class ZombieTimeHandler implements EventHandler<ActionEvent> {

		private ImageView getZombiegif(int type) {
			if (type == 0) {
				return new ImageView("zombie_normal.gif");
			} else {
				return new ImageView("Conehead_Zombie.gif");
			}
		}

		public void handle(ActionEvent event) {
			if (System.currentTimeMillis() - lastrun > 12000) {
				lastrun = System.currentTimeMillis();
				Random r = new Random();
				int lane = r.nextInt(5);
				int zombie_type = r.nextInt(2);// TODO -> change to 4

				ImageView im = getZombiegif(zombie_type);
				lawn.SpawnZombies(zombie_type, lane, im);
				im.setLayoutX(887);
				im.setLayoutY(lawn.getSpawn_points()[lane]);
				Anchor.getChildren().add(im);

				TranslateTransition translatorObj = new TranslateTransition(Duration.seconds(38), im);
				translatorObj.setToX(-880);
				translatorObj.setAutoReverse(true);
				translatorObj.play();
			}
		}
	}

	private class TimeHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent event) {
			Random r = new Random();

			if(!isPaused) {
				Image i = new Image("falling_sun.jpg");
				falling_sun = new ImageView(i);
				falling_sun.setLayoutX(Math.abs(r.nextInt()) % 900);
				falling_sun.setScaleY(0.5);
				falling_sun.setScaleX(0.5);
				falling_sun.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
				Anchor.getChildren().add(falling_sun);

				int c = Math.abs(r.nextInt());
				c = c % 5;
				TranslateTransition translatorObj = new TranslateTransition(Duration.seconds(5), falling_sun);
				translators.add(translatorObj);
				translatorObj.setToY(+(lawn.getY_coord())[c]);
				translatorObj.play();
			}

		}

	}

	EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if(!isPaused) {
				sunCount += 25;
				sun.setText("" + sunCount);
				Object i = e.getSource();

				// ImageView i=e.getSource();
				((ImageView) i).setVisible(false);
			}
		}
	};

	public void put(MouseEvent e) {
		isPlaced = true;
		if(!blank_click) {
			if (curGif == 0) {
				System.out.println(shooter_gif.getY());
				lawn.createObject(lawn.calcLane(shooter_gif.getY()+50), curGif, shooter_gif);
			} else if (curGif == 1) {

				lawn.createObject(lawn.calcLane(sunflower_gif.getY()+50), curGif, sunflower_gif);
			} else if (curGif == 2) {
				lawn.createObject(lawn.calcLane(walnut_gif.getY()), curGif, walnut_gif);
			} else if (curGif == 3) {
				lawn.createObject(lawn.calcLane(CherryBomb_gif.getY()+50), curGif, CherryBomb_gif);
			}
			blank_click=true;
		}
		lawn.displayChar();
		System.out.println(lawn.getActiveChars().size());
		if (pea_spawnable && curGif == 0) {
			ImageView img = new Pea().getPea();
			Anchor.getChildren().add(img);
			img.setX(shooter_gif.getX()+120);
			img.setY(shooter_gif.getY()+80);
			TranslateTransition translatorObj = new TranslateTransition(Duration.seconds(5), img);
			translators.add(translatorObj);
			translatorObj.setToX(1200);
			translatorObj.setCycleCount(Animation.INDEFINITE);
			translatorObj.play();
			pea_spawnable = false;
		}
		Timeline t = new Timeline();

	}

	@FXML
	public void moveSlider() {

		Timeline task = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(slider.progressProperty(), 0)),
				new KeyFrame(Duration.seconds(60), new KeyValue(slider.progressProperty(), 1)));
		task.playFromStart();
	}
	public void giveSun(ImageView image) {
		if (!isPaused) {
			Image i = new Image("falling_sun.jpg");
			falling_sun = new ImageView(i);
//			falling_sun.setLayoutX(Math.abs(r.nextInt()) % 900);

			falling_sun.setScaleY(0.5);
			falling_sun.setScaleX(0.5);

		}
	}
		private class SunflowerHandler implements EventHandler<ActionEvent> {

			public void handle(ActionEvent event) {
//				Random r = new Random();

				if(!isPaused) {
//					ArrayList<ImageView> a=new ArrayList<>();
					Image i = new Image("falling_sun.jpg");

//					sunflower_sun.setLayoutX(sunflower_gif.getX());
//					sunflower_sun.setLayoutY(sunflower_gif.getY());
					for(Character p:lawn.getActiveChars()){
						if(p instanceof Sunflower){
							sunflower_sun = new ImageView(i);
							sunflower_sun.setLayoutX((p.getPosition())[0]);
							sunflower_sun.setLayoutY((p.getPosition())[1]);
							sunflower_sun.setScaleY(0.5);
							sunflower_sun.setScaleX(0.5);
							sunflower_sun.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
							Anchor.getChildren().add(sunflower_sun);

//					int c = Math.abs(r.nextInt());
//					c = c % 5;
							TranslateTransition translatorObj = new TranslateTransition(Duration.seconds(1), sunflower_sun);
							translators.add(translatorObj);
							translatorObj.setToY(sunflower_sun.getY()+45);
							translatorObj.play();
//							a.add(sunflower_sun);
						}
					}




				}

			}

		}
		public void setupSunflowerSunTimeline(){
			KeyFrame kf = new KeyFrame(Duration.seconds(5), new SunflowerHandler());
			Timeline timeline = new Timeline(kf);
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
		}

}

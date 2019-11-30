import java.io.Serializable;

import javafx.scene.image.ImageView;

<<<<<<< HEAD
public class Character implements Cloneable {
    protected boolean exists;
    protected float health;
    protected final int lane;
    protected double[] position;
    protected ImageView image;

    public Character(float health,int lane, double[] position,ImageView image) {
        this.exists = true;
        this.health = health;
        this.lane = lane;
        this.position = position;
        this.image=image;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Character)super.clone();
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getLane() {
        return lane;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
=======
public class Character {

	protected boolean exists;
	protected float health;
	protected final int lane;
	protected double[] position;
	transient protected ImageView image;

	public Character(float health, int lane, double[] position, ImageView image) {
		this.exists = true;
		this.health = health;
		this.lane = lane;
		this.position = position;
		this.image = image;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getLane() {
		return lane;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}
>>>>>>> 3a69ead87b51b35c88b5e00ec66086610406ddb8
}

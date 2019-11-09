package cpsc362.project.astroblaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.soap.Text;

public class AstroBlaster extends ApplicationAdapter implements InputProcessor, ApplicationListener{
	SpriteBatch batch;
	Texture background;
	Texture craft;
	Texture play;
	Texture pause;
	Texture projectile;

	TextureAtlas textureAtlas;
	float elapsedTime = 0;

	float velocity = 0;
	float craftY = 0;;
	BitmapFont font;
	BitmapFont font2;
	BitmapFont font3;
//	BitmapFont pause;

	boolean paused = true;
	int playAnimation = 0;
	Animation<TextureRegion> animation;

	ArrayList<Integer> rockXs = new ArrayList<Integer>();
	ArrayList<Integer> rockYs = new ArrayList<Integer>();
	ArrayList<Integer> rockXs2 = new ArrayList<Integer>();
    ArrayList<Integer> rockYs2 = new ArrayList<Integer>();

    ArrayList<Integer> playX = new ArrayList<Integer>();
    ArrayList<Integer> playY = new ArrayList<Integer>();

	ArrayList<Integer> bgX = new ArrayList<Integer>();
	ArrayList<Integer> projectileX = new ArrayList<Integer>();
	ArrayList<Integer> projecttileY = new ArrayList<Integer>();

	Texture[] rock;

	Random random;
	int rockCount = 0;
	int projectileCount = 0;
	int scores = 0;
	int lives = 3;

	float temp = 0;
	float alpha = 1f;


	@Override
	public void create () {
		batch = new SpriteBatch();

		background = new Texture("jungle-bg1.jpg");
		bgX.add(background.getWidth());

		craft = new Texture("craft3.png");
		craftY = Gdx.graphics.getHeight() / 2 - craft.getHeight() / 2;

        play = new Texture("play.png");
        pause = new Texture("pause.png");

        projectile = new Texture("rocket3.png");

		rock = new Texture[2];
		rock[0] = new Texture("rock.png");
        rock[1] = new Texture("rock2.png");
		random = new Random();

//		textureAtlas = new TextureAtlas(Gdx.files.internal("f16.png"));
//		animation = new Animation<TextureRegion>(0.33f, textureAtlas.getRegions());

		Gdx.input.setInputProcessor(this);
	}

	public void makeRock(){
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		rockYs.add((int)height);
		rockXs.add(Gdx.graphics.getWidth());
	}

	public void makeRock2(){
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		rockYs2.add((int)height);
		rockXs2.add(Gdx.graphics.getWidth());
	}

	public void makeProjectile(){
		float height = craftY;
		projecttileY.add((int)height);
		projectileX.add(craft.getWidth());
	}

    public void hidePlay(){
        float height = Gdx.graphics.getHeight() / 3;
        playY.add((int)height);
        playX.add(Gdx.graphics.getWidth() / 3);
    }

	@Override
	public void render () {
		batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//		elapsedTime += Gdx.graphics.getDeltaTime();
//		batch.draw(animation.getKeyFrame(elapsedTime, true), 0, 0);

//		for(int i = 0; i < 1; i++) {
//
//			batch.draw(background, 0, 0, background.getWidth() * 3, Gdx.graphics.getHeight());
//			bgX.set(i, bgX.get(i) - 10);
//		}

//		if(moving == 0){
//			moving = Gdx.graphics.getHeight() / 2 - craft.getHeight() / 2;
//		}
//
//		craftY = moving;

		// make rock slowly
		if(rockCount < 150){
			rockCount++;
		}else{
			rockCount = 0;
			makeRock();
		}

		for(int i =  0; i < rockXs.size(); i++){
			batch.draw(rock[0], rockXs.get(i), rockYs.get(i));
			rockXs.set(i, rockXs.get(i) - 6); // get slowly overtime rock appear
		}


		// make another rock
		if(rockCount < 150){
			rockCount++;
		}else{
			rockCount = 0;
			makeRock2();
		}

		for(int i =  0; i < rockXs2.size(); i++){
			batch.draw(rock[1], rockXs2.get(i), rockYs2.get(i));
			rockXs2.set(i, rockXs2.get(i) - 4); // get slowly overtime rock appear
		}

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(3);
		font.draw(batch, "Score: " + scores, Gdx.graphics.getWidth() - 350, Gdx.graphics.getHeight() - 50);

		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(3);
		font2.draw(batch, "Lives: " + lives, Gdx.graphics.getWidth() - 550, Gdx.graphics.getHeight() - 50);

//		font3 = new BitmapFont();
//		font3.setColor(Color.WHITE);
//		font3.getData().setScale(3);
//		font3.draw(batch, String.valueOf(temp), 300, 200);

		if(!paused) {
            batch.draw(pause, Gdx.graphics.getWidth()  / 2 - pause.getWidth() / 2, Gdx.graphics.getHeight() / 2 - pause.getWidth()/2);
            playAnimation = 0;
		}else{
			if(playAnimation < 20) {
				batch.draw(play, Gdx.graphics.getWidth() / 2 - play.getWidth() / 2, Gdx.graphics.getHeight() / 2 - play.getWidth() / 2);
				playAnimation++;
//				hidePlay();
//				for (int i = 0; i < playX.size(); i++) {
//					batch.draw(play, playX.get(i), playY.get(i));
//					playX.set(i, playX.get(i) - 50); // get slowly overtime rock appear
//				}
			}
//			batch.draw(play, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        }

		batch.draw(craft, 0, craftY);

		if(projectileCount < 50)
			projectileCount++;
		else {
		    projectileCount = 0;
			makeProjectile();
		}

		for (int i = 0; i < projectileX.size(); i++) {
			batch.draw(projectile, projectileX.get(i), projecttileY.get(i));
			projectileX.set(i, projectileX.get(i) + 10);
		}
		//batch.draw(projectile, 0, craftY);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		paused = false;
	    pause();
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		paused = true;
	    resume();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		craftY = Gdx.graphics.getHeight() - screenY;

		if(craftY <= 0){
			craftY = 0 - craftY / 3;
		}
		if(craftY >= Gdx.graphics.getHeight() - craft.getHeight()){
			craftY = Gdx.graphics.getHeight() - craft.getHeight() / 3;
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void pause () {
		super.pause();
		Gdx.graphics.setContinuousRendering(paused);
		Gdx.graphics.requestRendering();
	}

	@Override
	public void resume () {
		super.resume();
		Gdx.graphics.setContinuousRendering(paused);

	}

}

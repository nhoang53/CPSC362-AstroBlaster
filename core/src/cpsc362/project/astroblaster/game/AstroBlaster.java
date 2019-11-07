package cpsc362.project.astroblaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.soap.Text;

public class AstroBlaster extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture background;
	Texture craft;
	float velocity = 0;
	float craftY = 0;;
	BitmapFont font;
	BitmapFont font2;
	BitmapFont font3;

	ArrayList<Integer> rockXs = new ArrayList<Integer>();
	ArrayList<Integer> rockYs = new ArrayList<Integer>();
	Texture rock;

	Random random;
	int rockCount = 0;

	float temp = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("jungle-bg2.jpg");
		craft = new Texture("craft.png");
		craftY = Gdx.graphics.getHeight() / 2 - craft.getHeight() / 2;
		Gdx.input.setInputProcessor(this);

		rock = new Texture("rock3.png");
		random = new Random();
	}

	public void makeRock(){
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		rockYs.add((int)height);
		rockXs.add(Gdx.graphics.getWidth());
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//		if(moving == 0){
//			moving = Gdx.graphics.getHeight() / 2 - craft.getHeight() / 2;
//		}
//
//		craftY = moving;

		// make rock slowly
		if(rockCount < 100){
			rockCount++;
		}else{
			rockCount = 0;
			makeRock();
		}

		for(int i =  0; i < rockXs.size(); i++){
			batch.draw(rock, rockXs.get(i), rockYs.get(i));
			rockXs.set(i, rockXs.get(i) - 6); // get slowly overtime rock appear
		}

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(3);
		font.draw(batch, String.valueOf(craftY), Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100);

		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(3);
		font2.draw(batch, "Max height: " + Gdx.graphics.getHeight(), Gdx.graphics.getWidth() - 350, 100);

//		font3 = new BitmapFont();
//		font3.setColor(Color.WHITE);
//		font3.getData().setScale(3);
//		font3.draw(batch, String.valueOf(temp), 300, 200);

		batch.draw(craft, 0, craftY);
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		craftY = Gdx.graphics.getHeight() - screenY;

		if(craftY <= 0){
			craftY = 0;
		}
		if(craftY >= Gdx.graphics.getHeight() - craft.getHeight()){
			craftY = Gdx.graphics.getHeight() - craft.getHeight();
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

}

package cpsc362.project.astroblaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AstroBlaster extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture background;
	Texture craft;
	float velocity = 0;
	float craftY = 0;
	float touchY = 0;
	boolean dragging;

	float temp = 0;
	BitmapFont font;
	BitmapFont font2;
	BitmapFont font3;
	BitmapFont font4;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("starfield_alpha.png");
		craft = new Texture("craft.png");
		craftY = Gdx.graphics.getHeight() / 2 - craft.getHeight() / 2;
		Gdx.input.setInputProcessor(this);
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

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(3);
		font.draw(batch, String.valueOf(craftY), 100, 200);

		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(3);
		font2.draw(batch, "Max height: " + Gdx.graphics.getHeight(), 700, 200);

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
		craftY = screenY;
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

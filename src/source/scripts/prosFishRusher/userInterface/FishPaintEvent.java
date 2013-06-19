package source.scripts.prosFishRusher.userInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.powerbot.script.methods.Skills;
import source.scriptFramework.abstractUserInterface.AbstractPaintEvent;
import source.scriptFramework.execution.event.Event;
import source.scripts.prosFishRusher.ProsFishRusherScript;
public class FishPaintEvent extends AbstractPaintEvent {
	public final Image hideButton = getImage("http://t2.gstatic.com/images?q=tbn:ANd9GcRHmkRpJu3sk-JUEAGkJ2Gp3R5OrZpwspFP_GA2S3cCKZjP_jS7"), 
			unHideButton = getImage("http://t1.gstatic.com/images?q=tbn:ANd9GcRHr0Wz9h1erBX1DvuL_Kkclwt6090A2nqUmmjmurcs6M6Y1MBQ"),
			paintImage = getImage("http://i591.photobucket.com/albums/ss356/pugnoses/paint_zps80e2978f.png");
	public static int xpGained = 0, fishFished = 0,profit = 0,startXp,fishXp = 0, fishPrice = 0;
	public static long startTime;
	public static String status = "";
	
	public FishPaintEvent(Event container) {
		super(container);
	}

	@Override
	public void drawPaint() {
		g2D.setRenderingHints( new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF));
		xpGained = getCtx().skills.getExperience(Skills.FISHING)-startXp;
		fishFished = (int) (xpGained/fishXp);
		profit = fishFished*fishPrice;
		if(((ProsFishRusherScript)getScript()).showPaint()){	
			long currentTimeMilli = System.currentTimeMillis()-startTime;
			long milliToSecs = TimeUnit.MILLISECONDS.toMinutes(currentTimeMilli);
			g2D.drawImage(paintImage, 2, 250, null);
			g2D.setColor(Color.CYAN);
			g2D.setFont(new Font("Arial", 0, 13));
			g2D.drawString("todo time", 100,362);//time
			g2D.drawString("" + profit, 55, 382);//profit
			g2D.drawString("" + fishFished, 240, 362);
			g2D.drawString("" + xpGained, 431, 362);
			g2D.drawString(status, 15, 420);
			g2D.drawString("("+ (getCtx().skills.getLevel(Skills.FISHING)+1) + "): " //xp to level
					+ getExperienceToNextLevel(Skills.FISHING), 432, 402);

			if(fishFished > 0) {
				g2D.drawString("" + (int)Math.round(profit/((double)milliToSecs/60)), 75, 402);//profit/hr

				g2D.drawString("" + (int)Math.round(fishFished/((double)milliToSecs/60)), 260, 382);//fish/hr

				/*g2D.drawString(Time.format((long) (((double) Skills.getExperienceToLevel(Skills.FISHING, 
								Skills.getRealLevel(Skills.FISHING)+1) * 3600000.0) 
								/ (double)(3600000d / (double) (System.currentTimeMillis()-startTime)
										* (double) (Skills.getExperience(Skills.FISHING) - startXp)))), 285, 402);//time to level*/
				g2D.drawString("Todo time to level", 285, 402);
				
				g2D.drawString("" + (int)Math.round(xpGained/((double)milliToSecs/60)), 412, 382);//xp/hr
			} else {
				g2D.drawString("waiting", 75, 402);

				g2D.drawString("waiting", 260, 382);

				g2D.drawString("waiting", 285, 402);
				

				g2D.drawString("waiting", 412, 382);
			}
		}
		if(hideButton != null && unHideButton != null) {
			g2D.drawImage(((ProsFishRusherScript)getScript()).showPaint() ?  hideButton : unHideButton, 7, 459, null);

		}
	}
	
	public int getExperienceToLevel(final int SKILL_ID, int finish){
		int currentExperience = getCtx().skills.getExperience(SKILL_ID);
		return (getCtx().skills.getExperienceAt(finish+1) - currentExperience);
	}
	
	public int getExperienceToNextLevel(final int SKILL_ID){
		int currentExperience = getCtx().skills.getExperience(SKILL_ID);
		return ((getCtx().skills.getExperienceAt(getCtx().skills.getRealLevel(SKILL_ID)+1))-currentExperience);
	}

	@Override
	public boolean accept() {
		return getCtx().game.isLoggedIn();
	}
	
	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch(IOException e) {
			return null;
		}
	}

}

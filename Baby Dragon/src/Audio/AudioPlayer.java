package Audio;

import javax.sound.sampled.*;

//this class is used to load,play,pause the audio...its objects are called in various classes to play audio
public class AudioPlayer 
{
	private Clip clip;
	public AudioPlayer(String s)//String s is the audio file we want to load
	{
	try
	{
		AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));//taking audio file as stream file
		AudioFormat baseFormat = ais.getFormat();//converting the stream file into base format  of audio
		
		AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),16,baseFormat.getChannels(),baseFormat.getChannels()*2, baseFormat.getSampleRate(),false);
		//pcm-Pulse code Modulation
		AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
		clip = AudioSystem.getClip();//takes the audio in computer readable format
		clip.open(dais);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		}
	}
	public void play()
	{
		if (clip == null)//to check clip is actually there
			return;
		stop();
		clip.setFramePosition(0);//to start from starting
		clip.start();//starts audio
			
	}
	public void stop()
	{
		if(clip.isRunning())clip.stop();
	}
	public void loop()
	{
		clip.loop(10);
	}//the audio will repeat itself 10 times ubtil and unless it is stopped

	public void close()
	{
		stop();//stops the audio
		clip.close();//closes the audio
	}
}

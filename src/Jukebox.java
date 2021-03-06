/*
 *    Copyright (c) The League of Amazing Programmers 2013-2018
 *    Level 1
 */


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javazoom.jl.player.advanced.AdvancedPlayer;

/* 1. Download the JavaZoom jar from here: http://bit.ly/javazoom
 * 2. Right click your project and add it as an External JAR (Under Java Build Path > Libraries).*/

public class Jukebox implements Runnable, ActionListener {
	JFrame J = new JFrame();
	JPanel P = new JPanel();
	JPanel P2 = new JPanel();
	JButton b1 = new JButton();
	JButton b2 = new JButton();
	JButton b3 = new JButton();
	JLabel JL1 = loadImage("freesound.jpg");
	JLabel JL2 = loadImage("Wings.jpg");
	JLabel JL3 = loadImage("Glorious.png");
	String song1 = "262259__shadydave__snowfall-final.mp3";
	   Song s1 = new Song(song1);
	   String song2 = "WINGS.mp3";
	   Song s2 = new Song(song2);
	   String song3 = "Glorious.mp3";
	   Song s3 = new Song(song3);
	   Song songplaying;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Jukebox());
		Jukebox J = new Jukebox();
		J.showButton();
	}

           public void run() {

		// 3. Find an mp3 on your computer or on the Internet.
		// 4. Create a Song
        	   

		// 5. Play the Song
        	  
		/*
		 * 6. Create a user interface for your Jukebox so that the user can 
		 * choose which song to play. You can use can use a different button for
		 * each song, or a picture of the album cover. When the button or album
		 * cover is clicked, stop the currently playing song, and play the one
		 * that was selected.
		 */
        	   
          }
	/* Use this method to add album covers to your Panel. */
	private JLabel loadImage(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		return new JLabel(icon);
	}
	public void showButton() {
		J.add(P);
		J.setVisible(true);
		P.add(JL1);
		P.add(b1);
		P.add(JL2);
		P.add(b2);
		P.add(JL3);
		P.add(b3);
		b1.setText("Freesound Music");
		b2.setText("Wing$");
		b3.setText("Glorious");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		J.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(songplaying != null) {
			songplaying.stop();
		}
		if(e.getSource() == b1){
			songplaying = s1;
		}
		else if(e.getSource()== b2) {
			songplaying = s2;
		}
		else if(e.getSource()== b3) {
			songplaying = s3;
		}
		songplaying.play();
	}
}

class Song {

	private int duration;
	private String songAddress;
	private AdvancedPlayer mp3Player;
	private InputStream songStream;

	/**
	 * Songs can be constructed from files on your computer or Internet
	 * addresses.
	 * 
	 * Examples: <code> 
	 * 		new Song("everywhere.mp3"); 	//from default package 
	 * 		new Song("/Users/joonspoon/music/Vampire Weekend - Modern Vampires of the City/03 Step.mp3"); 
	 * 		new	Song("http://freedownloads.last.fm/download/569264057/Get%2BGot.mp3"); 
	 * </code>
	 */
	public Song(String songAddress) {
		this.songAddress = songAddress;
	}

	public void play() {
		loadFile();
		if (songStream != null) {
			loadPlayer();
			startSong();
		} else
			System.err.println("Unable to load file: " + songAddress);
	}

	public void setDuration(int seconds) {
		this.duration = seconds * 100;
	}

	public void stop() {
		if (mp3Player != null)
			mp3Player.close();
	}

	private void startSong() {
		Thread t = new Thread() {
			public void run() {
				try {
					if (duration > 0)
						mp3Player.play(duration);
					else
						mp3Player.play();
				} catch (Exception e) {
				}
			}
		};
		t.start();
	}

	private void loadPlayer() {
		try {
			this.mp3Player = new AdvancedPlayer(songStream);
		} catch (Exception e) {
		}
	}

	private void loadFile() {
		if (songAddress.contains("http"))
			this.songStream = loadStreamFromInternet();
		else
			this.songStream = loadStreamFromComputer();
	}

	private InputStream loadStreamFromInternet() {
		try {
			return new URL(songAddress).openStream();
		} catch (Exception e) {
			return null;
		}
	}

	private InputStream loadStreamFromComputer() {
		try {
			return new FileInputStream(songAddress);
		} catch (FileNotFoundException e) {
			return this.getClass().getResourceAsStream(songAddress);
		}
	}
}


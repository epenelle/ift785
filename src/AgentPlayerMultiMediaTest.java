import AgentPlayer.Agents.AgentPlayerMultiMedia;
import AgentPlayer.MediaPlayer.Players.PlayerMusique;
import AgentPlayer.MediaPlayer.Players.PlayerVideo;
import AgentPlayer.States.*;
import Multimedia.Musique;
import Multimedia.Ownership.Bought;
import Multimedia.Ownership.Rental;
import Multimedia.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentPlayerMultiMediaTest {


    final int NB_MAX_DEFAUT = 10;
    final int RESOLUTION_DEFAUT = 1080;

    Musique musique1;
    Musique musique2;
    Video video1;
    Video video2;

    AgentPlayerMultiMedia apmMusique;
    AgentPlayerMultiMedia apmMusiqueLoue;
    AgentPlayerMultiMedia apmVideo;
    AgentPlayerMultiMedia apmVideoLoue;




    @BeforeEach
    void setUp() {

        musique1 =  new Musique("Billie Jean", "Micheal Jackson", true, new Bought());
        musique2 = new Musique("Thriller", "Micheal Jackson", true, new Rental(NB_MAX_DEFAUT));
        video1 = new Video("Never gonna give you up", "Rick Astley", true, new Bought(), RESOLUTION_DEFAUT);
        video2 = new Video("Numa numa", "famous youtuber", true, new Rental(NB_MAX_DEFAUT), RESOLUTION_DEFAUT);

        apmMusique = new AgentPlayerMultiMedia();
        apmMusiqueLoue = new AgentPlayerMultiMedia();
        apmVideo = new AgentPlayerMultiMedia();
        apmVideoLoue = new AgentPlayerMultiMedia();

    }

    void selectMultimediaForAll() {
        apmMusique.selectMultimedia(musique1);
        apmMusiqueLoue.selectMultimedia(musique2);
        apmVideo.selectMultimedia(video1);
        apmVideoLoue.selectMultimedia(video2);
    }

    void setStates(State state) {
        // On met l'etat a started
        apmMusique.setState(state);
        apmMusiqueLoue.setState(state);
        apmVideo.setState(state);
        apmVideoLoue.setState(state);
    }

//    void setDefaultPlayers() {
//        // On met les players par defaut
//        apmMusique.setPlayer(new iTunes());
//        apmMusiqueLoue.setPlayer(new iTunes());
//        apmVideo.setPlayer(new QuickTime());
//        apmVideoLoue.setPlayer(new QuickTime());
//    }

    @Test
    void startEtat() {

        // Before
        assertInstanceOf(Created.class, apmMusique.getState());
        assertInstanceOf(Created.class, apmMusiqueLoue.getState());
        assertInstanceOf(Created.class, apmVideo.getState());
        assertInstanceOf(Created.class, apmVideoLoue.getState());

        // start
        selectMultimediaForAll();
        apmMusique.clickStart();
        apmMusiqueLoue.clickStart();
        apmVideo.clickStart();
        apmVideoLoue.clickStart();

        // Apres
        assertInstanceOf(Started.class, apmMusique.getState());
        assertInstanceOf(Started.class, apmMusiqueLoue.getState());
        assertInstanceOf(Started.class, apmVideo.getState());
        assertInstanceOf(Started.class, apmVideoLoue.getState());
    }

    @Test
    void startPlayerInsantiated() {
        // Avant
        assertNull(apmMusique.getPlayer());
        assertNull(apmMusiqueLoue.getPlayer());
        assertNull(apmVideo.getPlayer());
        assertNull(apmVideoLoue.getPlayer());

        // start
        selectMultimediaForAll();
        apmMusique.clickStart();
        apmMusiqueLoue.clickStart();
        apmVideo.clickStart();
        apmVideoLoue.clickStart();

        // Apres
        assertInstanceOf(PlayerMusique.class, apmMusique.getPlayer());
        assertInstanceOf(PlayerMusique.class, apmMusiqueLoue.getPlayer());
        assertInstanceOf(PlayerVideo.class, apmVideo.getPlayer());
        assertInstanceOf(PlayerVideo.class, apmVideoLoue.getPlayer());
    }

    @Test
    void startLocationTerminee() {
        selectMultimediaForAll();
        apmMusiqueLoue.getPlayer().getMultimedia().getOwnership().setJoue(NB_MAX_DEFAUT);
        apmMusiqueLoue.clickStart();
        assertInstanceOf(Created.class, apmMusiqueLoue.getState());
    }

    @Test
    void pauseWhenStarted() {



        // Contexte
        selectMultimediaForAll();
        setStates(new Started());
//        setDefaultPlayers();

        apmMusique.clickPause();
        apmMusiqueLoue.clickPause();
        apmVideo.clickPause();
        apmVideoLoue.clickPause();

        // Apres
        assertInstanceOf(Paused.class, apmMusique.getState());
        assertInstanceOf(Paused.class, apmMusiqueLoue.getState());
        assertInstanceOf(Paused.class, apmVideo.getState());
        assertInstanceOf(Paused.class, apmVideoLoue.getState());
    }

    @Test
    void pauseWhenStopped() {

        // Contexte
        selectMultimediaForAll();
        setStates(new Stopped());

        apmMusique.clickPause();
        apmMusiqueLoue.clickPause();
        apmVideo.clickPause();
        apmVideoLoue.clickPause();

        // Apres
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmVideo.getState());
        assertInstanceOf(Stopped.class, apmVideoLoue.getState());
    }

    @Test
    void pauseCompteurJoue() {

        // Contexte
        selectMultimediaForAll();
        setStates(new Started());

        apmMusique.clickPause();
        apmMusiqueLoue.clickPause();
        apmVideo.clickPause();
        apmVideoLoue.clickPause();

        // Apres
        assertEquals(0, apmMusique.getPlayer().getMultimedia().getOwnership().getJoue()); // doit demeurer inchange
        assertEquals(1, apmMusiqueLoue.getPlayer().getMultimedia().getOwnership().getJoue()); // doit incremente
        assertEquals(0, apmVideo.getPlayer().getMultimedia().getOwnership().getJoue()); // doit demeurer inchange
        assertEquals(1, apmVideoLoue.getPlayer().getMultimedia().getOwnership().getJoue()); // doit incremente

    }

    @Test
    void resume() {

        // Contexte
        selectMultimediaForAll();
        setStates(new Paused());

        apmMusique.clickResume();
        apmMusiqueLoue.clickResume();
        apmVideo.clickResume();
        apmVideoLoue.clickResume();

        // Apres
        assertInstanceOf(Resumed.class, apmMusique.getState());
        assertInstanceOf(Resumed.class, apmMusique.getState());
        assertInstanceOf(Resumed.class, apmVideo.getState());
        assertInstanceOf(Resumed.class, apmVideoLoue.getState());

    }

    @Test
    void resumeWhenNotPaused() {

        // Contexte
        selectMultimediaForAll();

        setStates(new Created());
        apmMusique.clickResume();
        apmMusiqueLoue.clickResume();
        apmVideo.clickResume();
        apmVideoLoue.clickResume();
        assertInstanceOf(Created.class, apmMusique.getState());
        assertInstanceOf(Created.class, apmMusique.getState());
        assertInstanceOf(Created.class, apmVideo.getState());
        assertInstanceOf(Created.class, apmVideoLoue.getState());

        setStates(new Started());
        apmMusique.clickResume();
        apmMusiqueLoue.clickResume();
        apmVideo.clickResume();
        apmVideoLoue.clickResume();
        assertInstanceOf(Started.class, apmMusique.getState());
        assertInstanceOf(Started.class, apmMusique.getState());
        assertInstanceOf(Started.class, apmVideo.getState());
        assertInstanceOf(Started.class, apmVideoLoue.getState());

        setStates(new Resumed());
        apmMusique.clickResume();
        apmMusiqueLoue.clickResume();
        apmVideo.clickResume();
        apmVideoLoue.clickResume();
        assertInstanceOf(Resumed.class, apmMusique.getState());
        assertInstanceOf(Resumed.class, apmMusique.getState());
        assertInstanceOf(Resumed.class, apmVideo.getState());
        assertInstanceOf(Resumed.class, apmVideoLoue.getState());

        setStates(new Stopped());
        apmMusique.clickResume();
        apmMusiqueLoue.clickResume();
        apmVideo.clickResume();
        apmVideoLoue.clickResume();
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmVideo.getState());
        assertInstanceOf(Stopped.class, apmVideoLoue.getState());

    }

    @Test
    void resumeCompteurJoue() {

        // Contexte
        selectMultimediaForAll();
        setStates(new Paused());


        apmMusique.clickResume();
        apmMusiqueLoue.clickResume();
        apmVideo.clickResume();
        apmVideoLoue.clickResume();

        assertEquals(0, apmMusique.getPlayer().getMultimedia().getOwnership().getJoue());
        assertEquals(-1, apmMusiqueLoue.getPlayer().getMultimedia().getOwnership().getJoue());
        assertEquals(0, apmVideo.getPlayer().getMultimedia().getOwnership().getJoue());
        assertEquals(-1, apmVideoLoue.getPlayer().getMultimedia().getOwnership().getJoue());
    }

    @Test
    void stop() {

        // Contexte : AgentPlayer.States.Started
        selectMultimediaForAll();
        setStates(new Started());

        apmMusique.clickStop();
        apmMusiqueLoue.clickStop();
        apmVideo.clickStop();
        apmVideoLoue.clickStop();

        // Apres
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmVideo.getState());
        assertInstanceOf(Stopped.class, apmVideoLoue.getState());

        // Contexte : AgentPlayer.States.Resumed
        setStates(new Resumed());

        apmMusique.clickStop();
        apmMusiqueLoue.clickStop();
        apmVideo.clickStop();
        apmVideoLoue.clickStop();

        // Apres
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmVideo.getState());
        assertInstanceOf(Stopped.class, apmVideoLoue.getState());
    }

    @Test
    void stopWhenNotStarted() {

        selectMultimediaForAll();

        setStates(new Created());
        apmMusique.clickStop();
        apmMusiqueLoue.clickStop();
        apmVideo.clickStop();
        apmVideoLoue.clickStop();
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmVideo.getState());
        assertInstanceOf(Stopped.class, apmVideoLoue.getState());

        setStates(new Paused());
        apmMusique.clickStop();
        apmMusiqueLoue.clickStop();
        apmVideo.clickStop();
        apmVideoLoue.clickStop();
        assertInstanceOf(Paused.class, apmMusique.getState());
        assertInstanceOf(Paused.class, apmMusique.getState());
        assertInstanceOf(Paused.class, apmVideo.getState());
        assertInstanceOf(Paused.class, apmVideoLoue.getState());

        setStates(new Stopped());
        apmMusique.clickStop();
        apmMusiqueLoue.clickStop();
        apmVideo.clickStop();
        apmVideoLoue.clickStop();
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmMusique.getState());
        assertInstanceOf(Stopped.class, apmVideo.getState());
        assertInstanceOf(Stopped.class, apmVideoLoue.getState());

    }

    @Test
    void stopCompteurJoue() {
        // Contexte
        selectMultimediaForAll();
        setStates(new Created());

        apmMusique.clickStop();
        apmMusiqueLoue.clickStop();
        apmVideo.clickStop();
        apmVideoLoue.clickStop();

        assertEquals( 0, apmMusique.getPlayer().getMultimedia().getOwnership().getJoue());
        assertEquals(1, apmMusiqueLoue.getPlayer().getMultimedia().getOwnership().getJoue());
        assertEquals(0, apmVideo.getPlayer().getMultimedia().getOwnership().getJoue());
        assertEquals(1, apmVideoLoue.getPlayer().getMultimedia().getOwnership().getJoue());
    }
}
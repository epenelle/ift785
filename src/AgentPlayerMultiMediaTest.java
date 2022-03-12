import AgentPlayer.Media.AgentPlayerMultiMedia;
import AgentPlayer.Media.AgentPlayerMusique;
import AgentPlayer.Media.AgentPlayerVideo;
import AgentPlayer.States.*;
import MediaPlayer.Players.PlayerMusique;
import MediaPlayer.Players.PlayerVideo;
import MediaPlayer.MacOS.QuickTime;
import MediaPlayer.MacOS.iTunes;
import Ownership.Bought;
import Ownership.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class AgentPlayerMultiMediaTest {

    AgentPlayerMusique apmMusique;
    AgentPlayerMusique apmMusiqueLoue;
    AgentPlayerMultiMedia apmVideo;
    AgentPlayerMultiMedia apmVideoLoue;
    int nbMaxParDefaut = 10;

    @BeforeEach
    void setUp() {

        apmMusique = new AgentPlayerMusique(
                "Billie Jean",
                "Meilleure chanson de son année.",
                new Bought()
        );

        apmMusiqueLoue = new AgentPlayerMusique(
                "Thriller",
                "Un des meilleurs hits de MJ's",
                new Rental(nbMaxParDefaut)
        );

        apmVideo = new AgentPlayerVideo(
                "Never gonna give you up",
                "Got Rick Rolled.",
                new Bought()
        );

        apmVideoLoue = new AgentPlayerVideo(
                "Numa numa",
                "Video tres drole",
                new Rental(nbMaxParDefaut)
        );

    }

    void setStates(State state) {
        // On met l'etat a started
        apmMusique.setState(state);
        apmMusiqueLoue.setState(state);
        apmVideo.setState(state);
        apmVideoLoue.setState(state);
    }

    void setDefaultPlayers() {
        // On met les players par defaut
        apmMusique.setPlayer(new iTunes());
        apmMusiqueLoue.setPlayer(new iTunes());
        apmVideo.setPlayer(new QuickTime());
        apmVideoLoue.setPlayer(new QuickTime());
    }
    @Test
    void startEtat() {

        // Before
        assertInstanceOf(Created.class, apmMusique.getState());
        assertInstanceOf(Created.class, apmMusiqueLoue.getState());
        assertInstanceOf(Created.class, apmVideo.getState());
        assertInstanceOf(Created.class, apmVideoLoue.getState());

        // start
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
        apmMusiqueLoue.ownership.setJoue(nbMaxParDefaut);
        apmMusiqueLoue.clickStart();
        assertInstanceOf(Created.class, apmMusiqueLoue.getState());
    }

    @Test
    void pauseWhenStarted() {

        // Contexte
        setStates(new Started());
        setDefaultPlayers();

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
        setStates(new Stopped());
        setDefaultPlayers();

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
        setStates(new Started());
        setDefaultPlayers();

        apmMusique.clickPause();
        apmMusiqueLoue.clickPause();
        apmVideo.clickPause();
        apmVideoLoue.clickPause();

        // Apres
        assertEquals(0, apmMusique.ownership.getJoue()); // doit demeurer inchange
        assertEquals(1, apmMusiqueLoue.ownership.getJoue()); // doit incremente
        assertEquals(0, apmVideo.ownership.getJoue()); // doit demeurer inchange
        assertEquals(1, apmVideoLoue.ownership.getJoue()); // doit incremente

    }

    @Test
    void resume() {

        // Contexte
        setStates(new Paused());
        setDefaultPlayers();

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
        setDefaultPlayers();

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
        setStates(new Paused());
        setDefaultPlayers();

        apmMusique.clickResume();
        apmMusiqueLoue.clickResume();
        apmVideo.clickResume();
        apmVideoLoue.clickResume();

        assertEquals(0, apmMusique.ownership.getJoue());
        assertEquals(-1, apmMusiqueLoue.ownership.getJoue());
        assertEquals(0, apmVideo.ownership.getJoue());
        assertEquals(-1, apmVideoLoue.ownership.getJoue());
    }

    @Test
    void stop() {

        // Contexte : AgentPlayer.States.Started
        setStates(new Started());
        setDefaultPlayers();

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

        setDefaultPlayers();

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
        setStates(new Created());
        setDefaultPlayers();

        apmMusique.clickStop();
        apmMusiqueLoue.clickStop();
        apmVideo.clickStop();
        apmVideoLoue.clickStop();

        assertEquals( 0, apmMusique.ownership.getJoue());
        assertEquals(1, apmMusiqueLoue.ownership.getJoue());
        assertEquals(0, apmVideo.ownership.getJoue());
        assertEquals(1, apmVideoLoue.ownership.getJoue());
    }
}
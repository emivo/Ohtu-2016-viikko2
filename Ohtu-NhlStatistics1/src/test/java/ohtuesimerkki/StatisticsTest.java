/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author airta
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findsPlayer() {
        Player p = stats.search("Semenko");
        assertEquals("Semenko", p.getName());
        assertEquals(4, p.getGoals());
        assertEquals(12, p.getAssists());
        assertEquals("EDM", p.getTeam());
    }
    
    @Test
    public void returnNullIfPlayerNotFound() {
        Player p = stats.search("Häkkinen");
        assertTrue(p == null);
    }

    @Test
    public void findsTeam() {
        List<Player> team = stats.team("EDM");
        assertTrue(team.size() == 3);
        for (Player p : team) {
            assertEquals("EDM", p.getTeam());
        }
    }
    
    @Test
    public void topScoresRightOrder() {
        List<Player> topScores = stats.topScorers(4);
        assertFalse(topScores.isEmpty());
        assertEquals(5, topScores.size());
        assertEquals(topScores.get(0).getName(), "Gretzky");
        assertEquals(topScores.get(1).getName(), "Lemieux");
        assertEquals(topScores.get(2).getName(), "Yzerman");
        assertEquals(topScores.get(3).getName(), "Kurri");
        assertEquals(topScores.get(4).getName(), "Semenko");
    }
    
    @Test
    public void topScoresFirst() {
        List<Player> top1 = stats.topScorers(0);
        assertFalse(top1.isEmpty());
        assertEquals("Gretzky",top1.get(0).getName());
        
    }
    
    
    @Test
    public void noTopScoresListIsEmpty() {
        assertTrue(stats.topScorers(-1).isEmpty()) ;
    }
}

package champollion;

import static champollion.TypeIntervention.CM;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");
                
                untel.ajouteEnseignement(java, 10, 0, 10);
                assertEquals(15 + 7.5, untel.heuresPrevuesPourUE(java), "untel a 22.5h de prévues");
                
                untel.ajouteEnseignement(java, 5, 5, 10);
                assertEquals(22.5 + 7.5 + 5 + 7.5, untel.heuresPrevuesPourUE(java), "untel a 42.5h de prévues");
		
	}
        
        @Test
        public void testExceptionHeureNegative() {
            assertThrows(IllegalArgumentException.class, () -> {
        		untel.ajouteEnseignement(java, -8, 0, 10); 
                }, "Cet appel doit lever une exception");
        }
        
        @Test
        public void testHeuresPrevues() {
            untel.ajouteEnseignement(java, 10, 10, 0);
            assertEquals(25, untel.heuresPrevues(), "untel a 25h de cours");
        }
        
        @Test
        public void testHeuresPlannifiees() {
            untel.ajouteEnseignement(java, 10, 0, 5);
            assertEquals(15 + 3.75, untel.heuresPlanifiees(), "untel a 18.75h plannifiees");
            untel.ajouteEnseignement(java, 10, 10, 0);
            assertEquals(18.75 + 15 + 10, untel.heuresPlanifiees(), "untel a 43.75h plannifiees");
            
            assertEquals(untel.heuresPlanifiees(), untel.heuresPrevues(), "untel a le même nomsbre d'heures prevu que d'heures plannifiees");
        }
        
        @Test
        public void testCountAjouteEnseignement() {
            untel.ajouteEnseignement(java, 10, 5, 10);
            assertEquals(25, untel.getListeInterventions().size(), "untel doit avoir 20 interventions programmées");
        }
        
        @Test
        public void testAjouteIntervention() {
            untel.ajouteIntervention(CM, java, 1);
            assertEquals(1, untel.getListeInterventions().size(), "untel doit avoir 1 intervation programmée");
        }
        
        @Test
        public void testPersonne() {
            assertEquals("untel", untel.getNom(), "untel");
            
            assertEquals("untel@gmail.com", untel.getEmail(), "untel");
        }
        
        @Test
        public void testSalle() {
            Salle s = new Salle("Amphi", 50);
            assertEquals("Amphi", s.getIntitule(), "Amphi");
            assertEquals(50, s.getCapacite(), "La capacité de l'amphi est de 50 places");
        }
	
        @Test
        public void testUE() {
            assertEquals("Programmation en java", java.getIntitule(), "java");
        }
        
}

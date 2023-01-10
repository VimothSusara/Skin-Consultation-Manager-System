import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WestminsterSkinConsultationManagerTest {

    char [] chars = {'1','2','3','4','5','6','7','8','9','-','/'};
    String[] doctors = {"Damith3\nSagara\n21-2-1998\n0723241243\nid1\nspe1\n",
                        "Janith\nKuma32ra\n3-4-1988\n0764545432\nid2\nspe2\n" ,
                        "Isara\nSanaka\n36-3-1997\n0762242432\nid3\nspe3\n" ,
                        "Gayan\nJanaka\n2-1-1989\n2453453542\nid4\nspe4\n" ,
                        "Janaka\nNadun\n4-10-1988\n0721245325\nid5\nspe5\n"};

    @Test
    public void checkAddNewDoctor(ArrayList<String> doctors){
        for(String s : doctors){

        }
    }

    @Test
    public void checkNameValidation(){
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        assertTrue(w.nameValidation("naleen", chars));
    }

    @Test
    public void checkDateValidation(){
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        assertTrue(w.dateValidation("21-3-2001"));
    }

    @Test
    public void checkMobiNoValidation(){
        WestminsterSkinConsultationManager w = new WestminsterSkinConsultationManager();
        assertTrue(w.mobiNoValidation("0712312123"));
    }

}
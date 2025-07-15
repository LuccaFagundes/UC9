import Dao.dinoDAO;
import Model.Dino;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("\n--- Testando Inserção ---");
        Dino mosassauro = new Dino("Mosassauro", "Mosasauridae", "Herbívoro", 68, 12, "Seguro");
        Dino quetzalcoatlus = new Dino("Quetzalcoatlus", "Quetzalcoatlus northropi", "Piscívoro", 68, 7, "Seguro");
        Dino dinossauroParaVirarVelociraptor = new Dino("Fakeossauro", "Mentira Mentirais", "Herbívoro", 69, 500, "Seguro");
        Dino dinossauroParaSerDeletar = new Dino("Inexistencia", "Voidicus Inexistencius", "Herbívoro", 90, 420, "Seguro");

        dinoDAO.setDinossauro(mosassauro);
        dinoDAO.setDinossauro(quetzalcoatlus);
        dinoDAO.setDinossauro(dinossauroParaVirarVelociraptor);
        dinoDAO.setDinossauro(dinossauroParaSerDeletar);


        System.out.println("\n--- Testando Listagem ---");
        List<Dino> listaDinos = dinoDAO.getDinossauro();


        System.out.println("\n--- Testando Atualização ---");
        Dino velociraptor = new Dino(3,"Velociraptor", "Velociraptor mongoliensis", "Carnívoro", 75, 5, "contido");
        dinoDAO.updateDinossauro(velociraptor);


        System.out.println("\n--- Testando remoção ---");
        dinoDAO.removerDinossauro(4);

        dinoDAO.getDinossauro();

    }
}
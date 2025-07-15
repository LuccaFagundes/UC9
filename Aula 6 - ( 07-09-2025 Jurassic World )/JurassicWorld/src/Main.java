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
        Dino velociraptor = new Dino("Velociraptors", "Velociraptor mongoliensis", "Carnivor", 75, 5, "Contido");

        dinoDAO.setDinossauro(mosassauro);
        dinoDAO.setDinossauro(quetzalcoatlus);
        dinoDAO.setDinossauro(velociraptor);


        System.out.println("\n--- Testando Listagem ---");
        List<Dino> listaDinos = dinoDAO.getDinossauro();


        System.out.println("\n--- Testando Atualização ---");


        System.out.println("\n--- Testando remoção ---");


        dinoDAO.getDinossauro();

    }
}

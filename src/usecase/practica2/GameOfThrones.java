package usecase.practica2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import material.Position;


import material.Position;
import material.tree.narytree.LinkedTree;

public class GameOfThrones {
    List<FamilyMember> listaFamiliares;

    public GameOfThrones() {
        this.listaFamiliares = new LinkedList<>();
    }

    public void loadFile(String pathToFile){
        String id, name, surname, genre;
        Integer age;
        String linea = "";
        String limit = "---------------------------------------------------------------------------------------";
        String[] linea_separada;
        FamilyMember familiar;

        try{
            File fichero = new File(pathToFile);
            Scanner sc = new Scanner(fichero);

            while(sc.hasNextLine()){
                linea = sc.nextLine();
                if(linea.contains("--")){
                    break;
                }
                //System.out.println("linea leida: "+linea);
                linea_separada = linea.split(" ");
                id = linea_separada[0];
                name = linea_separada[2];
                surname = linea_separada[3];
                genre = linea_separada[4];
                age = Integer.parseInt(linea_separada[5]);

                //System.out.println("Familiar recibido: "+id+name+surname+genre+linea_separada[5]);

                familiar = new FamilyMember(id, age, name,surname,genre);
                listaFamiliares.add(familiar );


                // Caputurados todos los nodos

            }
            int num_familias = Integer.parseInt(sc.nextLine());
            System.out.printf("Num fam : "+num_familias);
            FamilyMember[] roots = new FamilyMember[num_familias];

            /*for(int i=0; i<num_familias;i++){
                roots[i] =
            }*/

            sc.close();
        }catch (FileNotFoundException e){
            System.err.println("File not found..");
            e.getMessage();
        }
    }

    public LinkedTree<FamilyMember> getFamily(String surname){
        throw new RuntimeException("Not yet implemented");
    }

    public String findHeir(String surname){
        throw new RuntimeException("Not yet implemented");
    }

    public void changeFamily(String memberName, String newParent){
        throw new RuntimeException("Not yet implemented");
    }

    public boolean areFamily(String name1, String name2){
        throw new RuntimeException("Not yet implemented");
    }
}

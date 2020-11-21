package usecase.practica2;

import material.Position;
import material.tree.narytree.NAryTree;

import java.util.LinkedList;
import java.util.List;

public class exam {
    private NAryTree<Integer> tree;

    public exam(NAryTree<Integer> tree) {
        this.tree = tree;
    }

    public List<Integer> getNodesByLevel(int level){
        List<Integer> lista = new LinkedList<>();
        aux(1, level, tree.root(), tree, lista);
        return lista;
    }

    private void aux(int currentLevel, int level, Position<Integer> node, NAryTree<Integer> tree, List<Integer> list){
        if(currentLevel == level){
            list.add(node.getElement());
        }
        else if(currentLevel < level){
            for(Position<Integer> child: tree.children(node)){
                aux(currentLevel+1, level, child, tree, list);
            }
        }
    }
}
